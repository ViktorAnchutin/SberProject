begin ;

CREATE TABLE devices
(   id BIGSERIAL NOT NULL ,
    name character varying(20) NOT NULL UNIQUE,
    status character varying(255) ,
    CONSTRAINT devices_pkey PRIMARY KEY (id)
);

insert into devices (name, status) values ('ATM1' , 'normal');
insert into devices (name, status) values ('ATM2' , 'normal');
insert into devices (name, status) values ('ATM3' , 'normal');


commit ;
