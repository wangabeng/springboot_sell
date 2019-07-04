# 商品表NOT
CREATE TABLE IF NOT EXISTS `product_info` (
  `product_id` VARCHAR(32) NOT NULL,
  `product_name` VARCHA(64) NOT NULL COMMENT "商品名称",
  `product_price` DECIMAL(8,2) NOT NULL COMMENT "单价",
  `product_stock` INT NOT NULL COMMENT "描述",
  `product_icon` VARCHAR(512) COMMENT "小图",
  `category_type` INT NOT NULL COMMENT "类目编号",
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "创建时间",
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT "修改时间",
  PRIMARY KEY (`product_id`)
) COMMENT "商品表";


# 订单主表
CREATE TABLE IF NOT EXISTS `order_master`(
  `order_id` VARCHAR(32) NOT NULL,
  `buyer_name` VARCHA(32) NOT NULL COMMENT `买家名字`,
  `buyer_phone` VARCHA(32) NOT NULL COMMENT `买家电话`,
  `buyer_address` VARCHA(128) NOT NULL COMMENT `买家地址`,
  `buyer_openid` VARCHA(64)  NULL COMMENT `买家微信openid`,
  `order_amount` decimal(8,2) NOT NULL COMMENT `订单总金额`,
  `order_status` tinyint(3) NOT NULL  DEFAULT `0` COMMENT `订单状态默认0未支付`,
  `pay_status` tinyint(3) NOT NULL  DEFAULT `0` COMMENT `支付状态默认0未支付`,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT `创建时间`,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT `修改时间`,
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) COMMENT `订单表`;


# 订单详情
CREATE TABLE `order_detail` IF NOT EXIST (
  `detail_id` VARCHAR(32) NOT NULL,
  `order_id` VARCHA(32) NOT NULL,
  `product_id` VARCHA(32) NOT NULL,
  `product_name` VARCHA(64) NOT NULL COMMENT `商品名称`,
  `product_price` decimal(8,2) NOT NULL COMMENT `商品价格`,
  `product_quantity` INT NOT NULL COMMENT `商品数量`,
  `product_icon` VARCHAR(52) COMMENT `商品小图`,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT `创建时间`,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT `修改时间`,
  PRIMARY KEY (`detail_id`),
  key `idx_order_id` (`order_id`)
) COMMENT `订单详情表`;



# 类目表
CREATE TABLE `product_category` IF NOT EXIST (
  `category_id`INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHA(64) NOT NULL COMMENT `类目名称`,
  `category_type` INT NOT NULL COMMENT `类目编号`,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT `创建时间`,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT `修改时间`,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
) COMMENT "类目表";

