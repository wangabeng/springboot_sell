# comment 不能用``
# 字段名 表名 都用飘号 其他不能用飘号


# 商品表NOT
CREATE TABLE IF NOT EXISTS `product_info` (
  `product_id` VARCHAR(32) NOT NULL,
  `product_name` VARCHAR(64) NOT NULL COMMENT "商品名称",
  `product_price` DECIMAL(8,2) NOT NULL COMMENT "单价",
  `product_stock` INT NOT NULL COMMENT "描述",
  `product_icon` VARCHAR(512) COMMENT "小图",
  `category_type` INT NOT NULL COMMENT "类目编号",
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "创建时间",
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT "修改时间",
  PRIMARY KEY (`product_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "商品表";


# 订单主表
CREATE TABLE IF NOT EXISTS `order_master`(
  `order_id` VARCHAR(32) NOT NULL,
  `buyer_name` VARCHAR(32) NOT NULL COMMENT "买家名字",
  `buyer_phone` VARCHAR(32) NOT NULL COMMENT "买家电话",
  `buyer_address` VARCHAR(128) NOT NULL COMMENT "买家地址",
  `buyer_openid` VARCHAR(64)  NULL COMMENT "买家微信openid",
  `order_amount` decimal(8,2) NOT NULL COMMENT "订单总金额",
  `order_status` tinyint(3) NOT NULL  DEFAULT "0" COMMENT "订单状态默认0未支付",
  `pay_status` tinyint(3) NOT NULL  DEFAULT "0" COMMENT "支付状态默认0未支付",
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "创建时间",
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT "修改时间",
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "订单表";


# 订单详情
CREATE TABLE IF NOT EXISTS `order_detail`(
  `detail_id` VARCHAR(32) NOT NULL,
  `order_id` VARCHAR(32) NOT NULL,
  `product_id` VARCHAR(32) NOT NULL,
  `product_name` VARCHAR(64) NOT NULL COMMENT "商品名称",
  `product_price` decimal(8,2) NOT NULL COMMENT "商品价格",
  `product_quantity` INT NOT NULL COMMENT "商品数量",
  `product_icon` VARCHAR(52) COMMENT "商品小图",
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "创建时间",
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT "修改时间",
  PRIMARY KEY (`detail_id`),
  key `idx_order_id` (`order_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "订单详情表";



# 类目表
CREATE TABLE IF NOT EXISTS `product_category`(
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(64) NOT NULL COMMENT "类目名称",
  `category_type` INT NOT NULL COMMENT "类目编号",
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "创建时间",
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT "修改时间",
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT "类目表";

# 卖家信息表
CREATE TABLE IF NOT EXISTS `seller_info`(
    `seller_id` varchar(32) not null,
    `username` varchar(32) not null,
    `password` varchar(32) not null,
    `openid` varchar(64) not null comment '微信openid',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key(`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '卖家信息表';