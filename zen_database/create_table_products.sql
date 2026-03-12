create table products(
id Serial Primary key,
name varchar(1000) NOt null,
price decimal(10,2),
link varchar(1000),
img_url varchar(1000),
color varchar(100),
category varchar(255),
subcategory varchar(255)
)