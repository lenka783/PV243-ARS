INSERT INTO Address (id,street,city,state,country,postCode) VALUES (100,'Vajnorska','Bratislava',NULL,'Slovakia','99999' );
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (101,'P.O. Box 629, 3146 Orci, Street','Gatineau','QC','Kenya','3059');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (102,'451-8102 Vel Ave','Columbus','OH','Kuwait','09251');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (103,'4953 Convallis, Rd.','San Rafael','A','Trinidad and Tobago','3293');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (104,'401-8099 Lorem St.','Napier','NI','Montenegro','884123');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (105,'P.O. Box 154, 7903 Mauris Rd.','Quinta de Tilcoco','OHiggins','Pitcairn Islands','JE68 5CC');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (106,'P.O. Box 588, 7787 Risus, Rd.','Idaho Falls','Idaho','Yemen','84050-795');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (107,'8987 Eget Rd.','Boo','Stockholms län','Sweden','XU92 6EP');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (108,'P.O. Box 643, 1741 Mauris Avenue','Arrah','BR','French Polynesia','01870');
INSERT INTO Address (id,street,city,state,country,postCode) VALUES (109,'Ap #864-4420 Quisque Rd.','Gravilias','San José','Tunisia','5257');

INSERT INTO User (id, keycloakPrincipal, name, surname, email, password, isActive,dateOfBirth,address_id) VALUES (200, 'd805fffe-a3eb-4a9e-a839-7eb556215e3c', 'Marian', 'Dolny', 'marian.dolny@ars.cz', 'admin', true,'1967-06-21',100);
INSERT INTO User (id, keycloakPrincipal, name, surname, email, password, isActive,dateOfBirth,address_id) VALUES (201, 'a2a46b10-2fcd-44bb-8a4e-0cb7d454c11d', 'Adam', 'Horny', 'adam.horny@ars.cz', 'admin', true,'1967-06-21',101);
INSERT INTO User (id, keycloakPrincipal, name, surname, email, password, isActive,dateOfBirth,address_id) VALUES (202, '348c4e83-5242-451c-85d4-7ef317ec46f3', 'Juraj', 'Stredny', 'juraj.stredny@ars.cz', 'admin', true,'1967-06-21',102);

INSERT INTO user_roles (user_id,roles) VALUES (200,'ADMIN');
INSERT INTO user_roles (user_id,roles) VALUES (200,'TENANT');
INSERT INTO user_roles (user_id,roles) VALUES (200,'HOST');
INSERT INTO user_roles (user_id,roles) VALUES (201,'TENANT');
INSERT INTO user_roles (user_id,roles) VALUES (202,'HOST');

INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (300,'Best vacation ever!',4,'APARTMENT',false,true,104, 200,2700);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (301,'Romantic holiday',9,'HOUSE',true,true,105,202,3750);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (302,'Family chill-out',8,'HOUSE',true,true,106,200,3000);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (303,'Best price in town',2,'ROOM',true,false,107,200,350);
INSERT INTO Offer (id,name,capacity,accommodationType,isAnimalFriendly,isSmokerFriendly,address_id,user_id,price) VALUES (304,'Student home',1,'ROOM',false,false,108,202,300);

INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (418,'2018-07-31','2018-08-12',8,302,201);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (419,'2018-06-30','2018-07-25',1,304,200);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (420,'2018-04-30','2018-05-25',4,302,201);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (421,'2018-09-20','2018-09-25',1,304,200);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (422,'2018-09-30','2018-10-25',1,300,201);
INSERT INTO Reservation (id,fromDate,toDate,numberOfPeople,offer_id,user_id) VALUES (423,'2018-04-30','2018-05-25',2,301,201);
