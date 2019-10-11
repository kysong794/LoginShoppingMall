CREATE table T_ADMIN(
i_admin number not null primary key,
mid nvarchar2(30) not null,
mpw nvarchar2(50) not null,
nm nvarchar2(10) not null
);

insert into T_ADMIN
values (1,'admin','1212','관리자');

drop table t_admin;

create table T_MEMBER(
i_member number not null primary key,
mid nvarchar2(30) not null,
mpw nvarchar2(50) not null,
nm nvarchar2(10) not null,
sex number(1) not null ,
r_date date default sysdate
);

create table T_PRODUCT(
i_product number not null primary key,
nm nvarchar2(100) not null,
price number default 0,
pic nvarchar2(255),
qty number(4) default 0,
vn_sale number(1) default 0,
info nvarchar2(255) default''
);

drop table T_PRODUCT;

alter table T_PRODUCT add (info nvarchar2(255) default'');

create table t_PRODUCT_IMPORT(
i_pi number primary key,
i_product number,
qty number(4) not null,
i_dt date default sysdate,
CONSTRAINT fk_i_product_improt foreign key(i_product)
references t_product(i_product)
);

SELECT * FROM T_ADMIN WHERE MID = 'admin';

create table T_BASKET(
i_basket number not null PRIMARY KEY,
i_member number,
i_product number,
qty number(4) not null,
price number not null,
r_dt  date default sysdate,
foreign key(i_member)
references t_member(i_member),
foreign key(i_product)
references t_product(i_product)
);

create table T_pruchaes(
i_purchase number primary key,
i_member number,
i_product number,
qty number(4) not null,
price number not null,
r_dt date default sysdate,
FOREIGN key (i_member) references t_member(i_member),
foreign key (i_product) REFERENCES t_product (i_product));

alter table t_product_import
add CONSTRAINT fk_i_product_improt foreign key(i_product)
references t_product_import(i_product);

drop table t_PRODUCT_IMPORT;

drop table T_PURCHASE;

create table T_PURCHASE(
i_purchase number  primary key,
i_member number,
i_product number,
qty number(4) not null,
price number not null,
r_dt date DEFAULT sysdate,
FOREIGN key (i_member) references t_member(i_member),
foreign key (i_product) REFERENCES t_product (i_product)
);



select nvl(max(i_member),0)+1 from T_MEMBER;

select * from t_admin;

select * from t_member;

select * from t_product;

select * from t_product_import;

select * from t_basket;

drop table t_product;

drop table T_BASKET;

commit;

update t_product
set nm = 테스트2 ,price = 200,pic=aasdf
where i_product = 4;
 상품 수량
insert All
    into t_product_import values((select nvl(max(i_pi),0)+1 from t_product_import),);


select a.i_pi, b.nm,(b.price*a.qty) as sum,b.price,a.qty
from t_product_import A
inner join t_product B on a.i_product = b.i_product;

select a.pcode,a.pname,sum(amount*cost) as total
from TBL_PIZZA_01 A 
join TBL_SALELIST_01 B on a.pcode = b.pcode
group by a.pcode,a.pname;
                
select a.i_pi, b.nm,(b.price*a.qty) as sum,b.price,a.qty
from t_product_import A
inner join t_product B on a.i_product = b.i_product
order by i_pi desc;

select pic,nm,price from t_product;


select i_product,pic,nm,price from t_product;

select a.i_pi, b.nm,(b.price*a.qty) as sum,b.price,a.qty
from t_product_import A
inner join t_product B on a.i_product = b.i_product
order by i_pi desc;

select * from t_product
order by i_product desc;

update t_product
set qty = 1
where nm = 1 ;

select a.i_basket,a.i_member,b.nm,b.pic,a.i_product,a.price,(a.price*a.qty) as sumprice, a.qty,b.qty
from t_basket a
inner join t_product b on a.i_product = b.i_product
where i_member = 1
order by i_basket desc;

update t_product
set qty = qty - i_qty
where i_product = ?;

update t_member
set mpw = '1234'
where i_member = 1;

select a.i_purchase,a.i_member,a.i_product,b.pic,b.nm,a.price,(a.price*a.qty) as sumprice,a.qty,b.qty,b.vn_sale,to_char(a.r_dt,'YYYY-MM-DD')as r_dt
from t_purchase a
inner join t_product b on a.i_product = b.i_product
where i_member = 5
order by i_purchase desc;

select to_char(b.r_dt,'YYYY-MM-DD')as r_dt,a.i_product,a.nm,a.pic,a.price,a.qty,(a.price*a.qty)as sumprice
from t_product a
inner join t_purchase b on a.i_product = b.i_product
where to_char(r_dt,'yyyy-mm-dd') between '2019-09-18' and '2019-09-23'
order by r_dt desc;
group by 
select * from t_purchase;

select to_char(b.r_dt,'YYYY-MM')as r_dt,a.i_product,a.nm,a.pic,a.price,a.qty,(a.price*a.qty)as sumprice
from t_product a 
inner join t_purchase b on a.i_product = b.i_product
where to_char(r_dt,'yyyy') = ? and to_char(r_dt,'mm') = ? and a.i_product = ?
where to_char(r_dt,'yyyy-mm') = ? 
order by i_product desc ;

select to_char(r_dt,'yyyy-mm') as r_dt, pic, nm, b.price, sum(b.qty), sum(a.price*b.qty)
from t_product a
inner join t_purchase b
using(i_product)
group by to_char(r_dt,'yyyy-mm'), pic, nm, b.price ;


select A.*, B.nm ,b.pic,b.price from (select to_char(r_dt, 'yyyy-mm') as r_dt, i_product, sum(qty),sum(price) from t_purchase
group by to_char(r_dt, 'yyyy-mm'), i_product) A
INNER JOIN t_product B ON A.i_product = B.i_product;

select a.i_product,to_char (a.r_dt,'yyyy-mm') as r_dt,b.pic,b.nm,b.price,sum(a.qty) as qty,sum(b.price*a.qty) as sumprice
from t_purchase a
inner join t_product b on a.i_product = b.i_product
where to_char(a.r_dt,'yyyy-mm') = '2019-09' and a.i_product = 1
group by to_char (a.r_dt,'yyyy-mm'),a.i_product,b.pic,b.nm,b.price
order by a.i_product desc;

delete from t_product
where i_product = 5;

delete form t_product_improt
where i_product = 5;

DELETE FROM t_product A
WHERE EXISTS (
     SELECT * FROM t_product_improt B
     WHERE B.i_product = A.i_product
     and B.nm = A.nm
    );