DROP TABLE usuario;

CREATE TABLE usuario 
	(username VARCHAR (40), 
	 pass VARCHAR (40),
	 name VARCHAR (200),
	 email VARCHAR (200));

ALTER TABLE usuario 
	ADD PRIMARY KEY (username);
	 
INSERT INTO usuario (username, pass, name, email) 
	   VALUES ('admin', 'admin', 'Admin do sistema', null);

DROP TABLE usuario_access;
	   
CREATE TABLE usuario_access 
	(username VARCHAR (40),
	 access VARCHAR (40));

INSERT INTO usuario_access (username, access) 
	   VALUES ('admin', 'Usuário');

DROP TABLE mp_product_company;
	   
CREATE TABLE mp_product_company (
       cd_product IDENTITY,
       ds_product VARCHAR(140) NULL,
       cd_product_internal VARCHAR(20) NULL,
       ds_mobile VARCHAR(24) NULL,
       qrcode VARCHAR(4000) NULL,
       price DECIMAL(10,2) NULL,
       ds_detail_description VARCHAR(600) NULL,
       valid_date DATE NULL,
       cd_company INTEGER NULL,
       delivery BOOLEAN NULL,
       rate_delivery DECIMAL(10,2) NULL,
       consumable BOOLEAN NULL,
       quantity_informed BOOLEAN NULL,
       parcel_quantity_card BOOLEAN NULL,
       image_product BLOB NULL
);
