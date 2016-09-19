	Drop table if exists Account;
	
	create table Account (
	userID integer auto_increment,
	passCode integer not null,
	accountName varchar(30) not null unique,
	userName varchar(30) not null,
	password varchar(30) not null,
	primary key(userID)
    
);