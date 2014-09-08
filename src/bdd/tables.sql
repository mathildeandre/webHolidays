CREATE TABLE  Groups (
 id INT( 11 ) NOT NULL AUTO_INCREMENT ,
 name VARCHAR( 50 ) NOT NULL ,
 email VARCHAR( 60 ) NOT NULL ,
 pwd_admin VARCHAR( 92 ) NOT NULL ,
 pwd_members VARCHAR( 92 ) NOT NULL ,
 date_inscription DATETIME NOT NULL ,
 PRIMARY KEY ( id ),
 UNIQUE ( name )
);

