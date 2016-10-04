create database seckill;

use seckill;

create table seckill(
seckill_id bigint not null AUTO_INCREMENT comment '商品库存ID',
name varchar(120) not null comment '商品名称',
number int not null comment '库存数量',
start_time timestamp not null comment '秒杀开始时间',
end_time timestamp not null comment '秒杀结束时间',
create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
primary key (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 default charset=utf8 comment='秒杀库存表';

insert into 
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀iPhone6',100,'2015-08-25 00:00:00','2015-08-26 00:00:00'),
  ('500元秒杀ipad2',200,'2015-08-25 00:00:00','2015-08-26 00:00:00'),
  ('300元秒杀小米4',300,'2015-08-25 00:00:00','2015-08-26 00:00:00'),
  ('200元秒杀红米note',400,'2015-08-25 00:00:00','2015-08-26 00:00:00');
  
  
--秒杀成功明细表
--用户登录认证相关信息
create table success_killed(
seckill_id bigint not null comment '秒杀商品ID',
user_phone bigint not null comment '用户手机号',
state tinyint not null default -1 comment '状态表示：-1:无效 0:成功 1:已付款 2:已发货',
create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
primary key(seckill_id,user_phone),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 default charset=utf8 comment='秒杀成功明细表';