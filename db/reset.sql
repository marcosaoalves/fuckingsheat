drop table usuario;
drop table usuario_access;

create table usuario 
	(username VARCHAR, 
	 pass VARCHAR,
	 name VARCHAR,
	 email VARCHAR);

create table usuario_access 
	(username VARCHAR,
	 access VARCHAR);
