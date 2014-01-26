user.select=select * from usuario where username = ?
user.select.all=select * from usuario order by username
user.insert=insert into usuario values (?, ?, ?, ?);
user.update=update usuario set pass=?, name=?, email=? where username=?
user.validateuserpass=select * from usuario where username = ? and pass = ?

user_access.select=select access from usuario_access where username = ?
user_access.insert=insert into usuario_access values (?, ?)
user_access.delete=delete from usuario_access where username = ?

product.insert=insert into mp_product_company (ds_product, cd_product_internal, ds_mobile, qrcode, price, ds_detail_description, valid_date, cd_company, delivery, rate_delivery, consumable, quantity_informed, parcel_quantity_card, image_product) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
product.update=update mp_product_company set ds_product=?, cd_product_internal=?, ds_mobile=?, qrcode=?, price=?, ds_detail_description=?, valid_date=?, cd_company=?, delivery=?, rate_delivery=?, consumable=?, quantity_informed=?, parcel_quantity_card=?, image_product=? where cd_product=?
product.select=select * from mp_product_company where cd_company=? 