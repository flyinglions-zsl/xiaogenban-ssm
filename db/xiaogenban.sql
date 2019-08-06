create TABLE `Sys_user`(
`uid` int(11) not null auto_increment COMMENT '用户id',
`uname` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
`password` VARCHAR(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户密码',
`age` int(11) DEFAULT NULL COMMENT '年龄',
`sex` int(3) DEFAULT NULL COMMENT '性别',
`nickname` VARCHAR(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
`phone` VARCHAR(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电话号码',
`address` VARCHAR(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
`imag` BLOB DEFAULT NULL COMMENT '头像',
`lock_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0-正常，1-锁定',
`created_time` datetime DEFAULT NULL COMMENT '创建时间',
`updated_time` datetime DEFAULT NULL COMMENT '更新时间',
`Sys_rid` int(11) not null COMMENT '角色ID',
PRIMARY KEY(`uid`),
FOREIGN KEY(`Sys_rid`) REFERENCES Sys_role(`rid`),
KEY `idx_user` (`uname`,`age`,`address`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户表';


create table `Sys_role`(
`rid` int(11) not null AUTO_INCREMENT COMMENT '角色id',
`rname` varchar(64) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '角色名',
`created_time` datetime DEFAULT NULL COMMENT '创建时间',
`updated_time` datetime DEFAULT NULL COMMENT '更新时间',
`lock_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0-正常，1-锁定',
PRIMARY KEY(`rid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '角色表';


CREATE TABLE `sys_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `product_price` DOUBLE(10,2) COLLATE utf8mb4_bin DEFAULT '0.00' COMMENT '商品价格',
  `type_id`  int(11) DEFAULT NULL COMMENT '商品种类ID',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺ID',
  `created_time` datetime(6) COMMENT '创建时间',
  `updated_time` datetime(6) COMMENT '更新时间',
	`created_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
	`updated_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
  PRIMARY KEY (`product_id`),
	FOREIGN KEY(`shop_id`) REFERENCES sys_shop(`shop_id`),
	FOREIGN KEY(`type_id`) REFERENCES sys_product_type(`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='商品信息表';


CREATE TABLE `sys_product_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '种类ID',
  `type_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '种类名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `created_time` datetime(6) COMMENT '创建时间',
  `updated_time` datetime(6) COMMENT '更新时间',
	`created_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
	`updated_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='商品种类表';

CREATE TABLE `sys_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `shop_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '店铺名称',
  `shop_master` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '店铺属于者',
  `created_time` datetime(6) COMMENT '创建时间',
  `updated_time` datetime(6) COMMENT '更新时间',
	`created_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
	`updated_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='店铺信息表';


CREATE TABLE `sys_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `product_id` int(11) DEFAULT NULL COMMENT '商品信息(一对多)',
	`take_product_person` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人',
	`take_product_address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址',
	`pay_type` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付方式',
	`pay_type_id` int(11) DEFAULT NULL COMMENT '支付编号',
  `created_time` datetime(6) COMMENT '创建时间',
  `updated_time` datetime(6) COMMENT '更新时间',
	`created_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
	`updated_person` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
	`deal_status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '订单交易标记(0--待付款 1--已付款)',
	`anonymous_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '是否匿名购买标记(0--不匿名 1--匿名)',
  PRIMARY KEY (`order_id`),
	FOREIGN KEY (`product_id`) REFERENCES sys_product(`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

