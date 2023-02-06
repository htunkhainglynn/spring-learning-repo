drop table if exists MEMBER;

create table MEMBER (
	loginId varchar(20) primary key,
	password varchar(20) not null,
	name varchar(20) not null,
	phone varchar(12),
	email varchar(40)
);