INSERT INTO Address (id,street,city,state,country,postCode) VALUES (0,'Vajnorska','Bratislava',NULL,'Slovakia','99999' );
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (1,'P.O. Box 629, 3146 Orci, Street','Gatineau','QC','Kenya','3059');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (2,'451-8102 Vel Ave','Columbus','OH','Kuwait','09251');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (3,'4953 Convallis, Rd.','San Rafael','A','Trinidad and Tobago','3293');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (4,'401-8099 Lorem St.','Napier','NI','Montenegro','884123');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (5,'P.O. Box 154, 7903 Mauris Rd.','Quinta de Tilcoco','OHiggins','Pitcairn Islands','JE68 5CC');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (6,'P.O. Box 588, 7787 Risus, Rd.','Idaho Falls','Idaho','Yemen','84050-795');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (7,'8987 Eget Rd.','Boo','Stockholms län','Sweden','XU92 6EP');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (8,'P.O. Box 643, 1741 Mauris Avenue','Arrah','BR','French Polynesia','01870');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (9,'Ap #864-4420 Quisque Rd.','Gravilias','San José','Tunisia','5257');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (10,'3187 Sed, Av.','Tarragona','CA','Dominica','01298-686');

INSERT INTO User (id,name,surname,email,password,dateOfBirth,isActive,address_id) VALUES (0,'Quemby','Singleton','admin@ars.com','admin','1967-06-21',true,0);
INSERT INTO User (id,name,surname,email,password,dateOfBirth,isActive,address_id) VALUES (1,'Sonya','Lang','Nam.interdum@rutrummagna.org','WZS69BZC0SJ','1931-12-29',true,1);
INSERT INTO User (id,name,surname,email,password,dateOfBirth,isActive,address_id) VALUES (2,'Reece','Pacheco','vel@inmolestie.edu','STH44WWH6XU','1994-05-06',true,2);
INSERT INTO User (id,name,surname,email,password,dateOfBirth,isActive,address_id) VALUES (3,'Jescie','Rodriguez','gravida.sit@condimentumeget.org','MPN85TBO6SH','1977-05-04',true,3);

INSERT INTO user_roles (user_id,roles) VALUES (0,'ADMIN');
INSERT INTO user_roles (user_id,roles) VALUES (0,'TENANT');
INSERT INTO user_roles (user_id,roles) VALUES (0,'HOST');
INSERT INTO user_roles (user_id,roles) VALUES (1,'HOST');
INSERT INTO user_roles (user_id,roles) VALUES (1,'TENANT');
INSERT INTO user_roles (user_id,roles) VALUES (2,'TENANT');
INSERT INTO user_roles (user_id,roles) VALUES (3,'HOST');

INSERT INTO Offer (id,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id) VALUES (0,4,'APARTMENT',false,true,4, 1);
INSERT INTO Offer (id,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id) VALUES (1,9,'HOUSE',true,true,5,2);
INSERT INTO Offer (id,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id) VALUES (2,8,'HOUSE',true,true,6,2);
INSERT INTO Offer (id,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id) VALUES (3,2,'ROOM',true,false,7,1);
INSERT INTO Offer (id,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id) VALUES (4,1,'ROOM',false,false,8,2);

INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id,assignedId) VALUES (0,'2018-07-31','2018-08-12',8,2,3,2);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id,assignedId) VALUES (1,'2018-06-30','2018-07-25',1,4,1,4);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id,assignedId) VALUES (2,'2018-04-30','2018-05-25',4,2,0,2);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id,assignedId) VALUES (3,'2018-09-20','2018-09-25',1,4,0,4);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id,assignedId) VALUES (4,'2018-09-30','2018-10-25',1,4,0,4);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id,assignedId) VALUES (5,'2018-04-30','2018-05-25',2,3,0,3);