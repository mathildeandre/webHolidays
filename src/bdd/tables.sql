
drop table PersonChecked;
drop table ColumnDoodle;
drop table Doodle;
drop table PrivateThings;
drop table PublicThings;
drop table Beneficiaries;
drop table RowExpenses;
drop table Persons;
drop table Groups;

CREATE TABLE  Groups (
 idGroup INT( 11 ) NOT NULL AUTO_INCREMENT ,
 nameGroup VARCHAR( 50 ) NOT NULL ,
 email VARCHAR( 60 ) NOT NULL ,
 pwd_admin VARCHAR( 92 ) NOT NULL ,
 pwd_members VARCHAR( 92 ) NOT NULL ,
 date_inscription DATETIME NOT NULL ,
 PRIMARY KEY ( idGroup ),
 UNIQUE ( nameGroup )
);

CREATE TABLE Persons (
	idPerson INT(11) NOT NULL AUTO_INCREMENT,
	namePerson VARCHAR(50) NOT NULL,
	mailPerson VARCHAR(50),
	idGroup INT(11) NOT NULL,
	PRIMARY KEY (idPerson),
	UNIQUE (namePerson, idGroup),
	FOREIGN KEY (idGroup) REFERENCES Groups(idGroup)
);

CREATE TABLE RowExpenses (
	idRow INT(11) NOT NULL AUTO_INCREMENT,
	idBuyer INT(11) NOT NULL,
	amount INT(11) NOT NULL,
	description VARCHAR(250),
	idGroup INT(11) NOT NULL,
	PRIMARY KEY (idRow),
	FOREIGN KEY (idGroup) REFERENCES Groups(idGroup)
);

CREATE TABLE Beneficiaries (
	idRowExpenses INT(11) NOT NULL,
	idBenef INT(11) NOT NULL,
	PRIMARY KEY (idRowExpenses, idBenef),
	FOREIGN KEY (idRowExpenses) REFERENCES RowExpenses(idRow),
	FOREIGN KEY (idBenef) REFERENCES Persons(idPerson)
);

CREATE TABLE PrivateThings (
	idThing INT(11) NOT NULL,
	nameThing VARCHAR(50) NOT NULL,
	idGroup INT(11) NOT NULL,
	PRIMARY KEY (idThing),
	UNIQUE (nameThing, idGroup),
	FOREIGN KEY (idGroup) REFERENCES Groups(idGroup)
);

CREATE TABLE PublicThings (
	idThing INT(11) NOT NULL,
	nameThing VARCHAR(50) NOT NULL,
	idGroup INT(11) NOT NULL,
	idPerson INT(11) NOT NULL,
	PRIMARY KEY (idThing),
	FOREIGN KEY (idGroup) REFERENCES Groups(idGroup),
	FOREIGN KEY (idPerson) REFERENCES Persons(idPerson)
);

CREATE TABLE Doodle (
	idDoodle INT(11) NOT NULL,
	nameDoodle VARCHAR(50) NOT NULL,
	idGroup INT(11) NOT NULL,
	PRIMARY KEY (idDoodle),
	UNIQUE (nameDoodle, idGroup),
	FOREIGN KEY (idGroup) REFERENCES Groups(idGroup)
);

CREATE TABLE ColumnDoodle (
	idColumn INT(11) NOT NULL,
	nameColumn VARCHAR(50) NOT NULL,
	idDoodle INT(11) NOT NULL,
	isCheckBox TINYINT(1),
	PRIMARY KEY (idColumn),
	UNIQUE (nameColumn, idDoodle),
	FOREIGN KEY (idDoodle) REFERENCES Doodle(idDoodle)
);

CREATE TABLE PersonChecked (
	idColumnDoodle INT(11) NOT NULL,
	idPerson INT(11) NOT NULL,
	info VARCHAR(255),
	PRIMARY KEY (idColumnDoodle, idPerson),
	FOREIGN KEY (idColumnDoodle) REFERENCES ColumnDoodle(idColumn),
	FOREIGN KEY (idPerson) REFERENCES Persons(idPerson)
);


