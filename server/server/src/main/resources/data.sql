INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)VALUES ('123 Maple St','London','On', 'N1N-1N1','(555)555-5555','Trusted','ASUS','abc@supply.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('543 Sycamore Ave','Toronto','On', 'N1P-1N1','(999)555-5555','Trusted','DELL','bb@depot.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email) VALUES ('922 Oak St','London','On', 'N1N-1N1','(555)555-5599','Un Trusted','MSI','ss@underthetable.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)VALUES ('123 Oxford St','London','On', 'N2N-2N1','(555)555-5555','Trusted','Botao He Org.','bh@main.com');


INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G13080',1,'ASUS Geforce RTX3080',459.99,499.99,5,5,10,0);
INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G13090',1,'ASUS RTX3090',499.99,699.99,2,2,100,0);
INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G12080Ti',1,'ASUS Geforce RTX2080 Ti',200.99,399.99,2,2,5,0);
INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G23090',2,'DELL RTX3090',499.99,699.99,2,2,100,0);
INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G23070',2,'DELL RTX3070',399.99,599.99,2,2,50,0);
INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G33090',3,'MSI RTX3090',488.99,599.99,2,2,100,0);
INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G33070',3,'MSI RTX3070',369.99,499.99,2,2,50,0);

INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G43090',4,'BH RTX3090',369.99,399.99,2,2,50,0);
INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G43080',4,'BH Geforce RTX3080',459.99,499.99,5,5,10,0);

INSERT INTO Product (Id, Vendorid,Name,CostPrice,MSRP,ROP,EOQ,QOH,QOO) VALUES ('G42080Ti',4,'BH Geforce RTX2080 Ti',200.99,399.99,2,2,5,0);
