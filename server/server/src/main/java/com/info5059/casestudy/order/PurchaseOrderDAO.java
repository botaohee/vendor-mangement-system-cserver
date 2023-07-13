package com.info5059.casestudy.order;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Component
public class PurchaseOrderDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public Long create(PurchaseOrder clientord) {

        PurchaseOrder realOrder = new PurchaseOrder();
        realOrder.setPodate(new Date());
        realOrder.setVendorid(clientord.getVendorid());
        realOrder.setAmount(clientord.getAmount());
        entityManager.persist(realOrder);
        for(PurchaseOrderLineitem item :clientord.getItems()) {
            PurchaseOrderLineitem realItem = new PurchaseOrderLineitem();
            realItem.setPoid(realOrder.getId());
            realItem.setPrice(item.getPrice());
            realItem.setProductid(item.getProductid());
            realItem.setQty(item.getQty());
            entityManager.persist(realItem);
        }
        return realOrder.getId();

    }


    public PurchaseOrder findOne(Long id) {
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, id);
        if (purchaseOrder == null) {
            throw new EntityNotFoundException("Can't find order for ID "
                    + id);
        }
        return purchaseOrder;
    }

    public Iterable<PurchaseOrder> findByVendor(Long vendorId) {
        //ESTConverter();
        //System.err.println(new Date());
        return entityManager.createQuery("select r from PurchaseOrder r where r.vendorid = :id")
                .setParameter("id", vendorId)
                .getResultList();
    }

}
