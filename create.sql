create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table proveedor (codigo integer not null, apellidos varchar(255), nombre varchar(255), primary key (codigo)) engine=InnoDB;
