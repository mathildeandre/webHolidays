/* comment */

drop table Ingredients;
drop table Recipes;
drop table PersonChecked;
drop table ColumnDoodle;
drop table Doodle;
drop table PersonalThings;
drop table GroupThings;
drop table Beneficiaries;
drop table RowExpenses;
drop table BelongTo;
drop table Groups;
drop table ContactList;
drop table Persons;


CREATE TABLE Persons (
	id_person INT(11) NOT NULL AUTO_INCREMENT,
	login_person VARCHAR(50) NOT NULL,
	name_person VARCHAR(50) NOT NULL,
	pwd_person VARCHAR(90) NOT NULL,
	mail_person VARCHAR(50),
	is_new TINYINT(1),
	pwd_newbie VARCHAR(50),
	PRIMARY KEY (id_person),
	UNIQUE (login_person)
);


CREATE TABLE ContactList (
	id_person1 INT(11) NOT NULL,
	id_person2 INT(11) NOT NULL,
	PRIMARY KEY (id_person1, id_person2),
	FOREIGN KEY (id_person1) REFERENCES Persons(id_person),
	FOREIGN KEY (id_person2) REFERENCES Persons(id_person)
);

CREATE TABLE  Groups (
 id_group INT( 11 ) NOT NULL AUTO_INCREMENT ,
 name_group VARCHAR( 50 ) NOT NULL ,
 login_admin VARCHAR( 50 ) NOT NULL ,
 date_inscription DATETIME NOT NULL ,
 PRIMARY KEY ( id_group ),
 FOREIGN KEY (login_admin) REFERENCES Persons(login_person)
);

CREATE TABLE BelongTo (
	id_person INT(11) NOT NULL,
    id_group INT( 11 ) NOT NULL,
	has_rights TINYINT(1),
	PRIMARY KEY (id_person, id_group),
	FOREIGN KEY (id_person) REFERENCES Persons(id_person),
	FOREIGN KEY (id_group) REFERENCES Groups(id_group)
);

CREATE TABLE RowExpenses (
	id_row INT(11) NOT NULL AUTO_INCREMENT,
	id_buyer INT(11) NOT NULL,
	amount INT(11) NOT NULL,
	description VARCHAR(250),
	id_group INT(11) NOT NULL,
	PRIMARY KEY (id_row),
	FOREIGN KEY (id_buyer) REFERENCES Persons(id_person),
	FOREIGN KEY (id_group) REFERENCES Groups(id_group)
);

CREATE TABLE Beneficiaries (
	id_rowExpenses INT(11) NOT NULL,
	id_benef INT(11) NOT NULL,
	PRIMARY KEY (id_rowExpenses, id_benef),
	FOREIGN KEY (id_rowExpenses) REFERENCES RowExpenses(id_row),
	FOREIGN KEY (id_benef) REFERENCES Persons(id_person)
);

CREATE TABLE PersonalThings (
	id_thing INT(11) NOT NULL AUTO_INCREMENT,
	name_thing VARCHAR(50) NOT NULL,
	id_group INT(11) NOT NULL,
	PRIMARY KEY (id_thing),
	UNIQUE (name_thing, id_group),
	FOREIGN KEY (id_group) REFERENCES Groups(id_group)
);

CREATE TABLE GroupThings (
	id_thing INT(11) NOT NULL AUTO_INCREMENT,
	name_thing VARCHAR(50) NOT NULL,
	id_group INT(11) NOT NULL,
	id_person INT(11) NOT NULL,
	PRIMARY KEY (id_thing),
	/*FOREIGN KEY (id_person) REFERENCES Persons(id_person),*/
	FOREIGN KEY (id_group) REFERENCES Groups(id_group)
);

CREATE TABLE Doodle (
	id_doodle INT(11) NOT NULL AUTO_INCREMENT,
	name_doodle VARCHAR(50) NOT NULL,
	id_group INT(11) NOT NULL,
	PRIMARY KEY (id_doodle),
	UNIQUE (name_doodle, id_group),
	FOREIGN KEY (id_group) REFERENCES Groups(id_group)
);

CREATE TABLE ColumnDoodle (
	id_column INT(11) NOT NULL AUTO_INCREMENT,
	name_column VARCHAR(50) NOT NULL,
	id_doodle INT(11) NOT NULL,
	is_checkBox TINYINT(1),
	PRIMARY KEY (id_column),
	UNIQUE (name_column, id_doodle),
	FOREIGN KEY (id_doodle) REFERENCES Doodle(id_doodle)
);

CREATE TABLE PersonChecked (
	id_columnDoodle INT(11) NOT NULL,
	id_person INT(11) NOT NULL,
	info VARCHAR(255),
	PRIMARY KEY (id_columnDoodle, id_person),
	FOREIGN KEY (id_columnDoodle) REFERENCES ColumnDoodle(id_column),
	FOREIGN KEY (id_person) REFERENCES Persons(id_person)
);

CREATE TABLE Recipes (
	id_recipe INT(11) NOT NULL AUTO_INCREMENT,
	name_recipe VARCHAR(50) NOT NULL,
	nb_person INT(11) NOT NULL,
	description VARCHAR(250),
   type ENUM('starter', 'course', 'dessert'),
	PRIMARY KEY (id_recipe)
);

CREATE TABLE Ingredients (
	id_ingredient INT(11) NOT NULL AUTO_INCREMENT,
	name_ingredient VARCHAR(50) NOT NULL,
	quantity INT(11),
	unity ENUM('unit', 'kg', 'gram'),
	id_recipe INT(11),
	PRIMARY KEY (id_ingredient),
	FOREIGN KEY (id_recipe) REFERENCES Recipes(id_Recipe)
);


