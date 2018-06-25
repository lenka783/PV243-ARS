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

INSERT INTO User (id, keycloakPrincipal, name, surname, email, password, isActive,dateOfBirth,address_id) VALUES (0, 'd805fffe-a3eb-4a9e-a839-7eb556215e3c', 'admin', 'admin', 'admin@ars.cz', 'admin', true,'1967-06-21',0);
INSERT INTO User (id, keycloakPrincipal, name, surname, email, password, isActive,dateOfBirth,address_id) VALUES (1, 'a2a46b10-2fcd-44bb-8a4e-0cb7d454c11d', 'admin', 'admin', 'admin123@ars.cz', 'admin', true,'1967-08-01',0);

INSERT INTO user_roles (user_id,roles) VALUES (0,'ADMIN');
INSERT INTO user_roles (user_id,roles) VALUES (0,'TENANT');
INSERT INTO user_roles (user_id,roles) VALUES (0,'HOST');
INSERT INTO user_roles (user_id,roles) VALUES (1, 'HOST');

INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (0,'Best vacation ever!',4,'APARTMENT',false,true,4, 1,2700);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (1,'Romantic holiday',9,'HOUSE',true,true,5,1,3750);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (2,'Family chill-out',8,'HOUSE',true,true,6,0,3000);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (3,'Best price in town',2,'ROOM',true,false,7,1,350);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (4,'Student home',1,'ROOM',false,false,8,0,300);

INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (0,'2018-07-31','2018-08-12',8,2,1);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (1,'2018-06-30','2018-07-25',1,4,0);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (2,'2018-04-30','2018-05-25',4,2,1);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (3,'2018-09-20','2018-09-25',1,4,0);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (4,'2018-09-30','2018-10-25',1,4,0);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (5,'2018-04-30','2018-05-25',2,3,0);
