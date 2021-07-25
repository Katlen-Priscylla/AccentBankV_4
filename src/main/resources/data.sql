insert into endereco values(1,'centro','53610530','apto 01','81','n','xxx','Igarassu','Av. waldemar luiz do nascimento','218','xxx','PE');
insert into agencia values (1,'Igarassu','3003-4004',1);

insert into cliente(cpf,nome,telefone, id_endereco) values ('111222333','jezielle','98501020',1);
insert into cliente(cpf,nome,telefone,id_endereco) values ('111333222','katlen','98504090',1);

insert into conta_corrente(conta_corrente_numero,saldo,agencia_id_agencia,cliente_id_cliente) values('9292',100.00,1,1);
insert into conta_corrente(conta_corrente_numero,saldo,agencia_id_agencia,cliente_id_cliente) values('1110',2500.00,1,2);