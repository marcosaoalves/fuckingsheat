user.select=select * from usuario where username = ?
user.insert=insert into usuario values (?, ?, ?, ?);
user.validateuserpass=select * from usuario where username = ? and pass = ?

user_access.select=select access from usuario_access where username = ?
user_access.insert=insert into usuario_access values (?, ?)