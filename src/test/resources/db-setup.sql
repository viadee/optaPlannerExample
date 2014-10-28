create table studiengang (
	id identity,
	name varchar(255) not null
);
create table vorlesung (
	id identity,
	name varchar(255) not null,
	studiengang_id int not null
);
alter table vorlesung add foreign key (studiengang_id) references studiengang(id);
create table raum (
	id identity,
	name varchar(255) not null
);

insert into studiengang(id, name) select * from csvread('src/test/resources/studiengang.csv');
insert into vorlesung(studiengang_id, name) select * from csvread('src/test/resources/vorlesung.csv');
insert into raum(name) select * from csvread('src/test/resources/raum.csv');