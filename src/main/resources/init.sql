DROP DATABASE IF EXISTS jkgl;
/*创建数据库，并设置编码*/
CREATE DATABASE jkgl DEFAULT CHARACTER SET utf8mb4;

USE jkgl;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`(
  `file_no` INT PRIMARY KEY AUTO_INCREMENT COMMENT '档案号',

  `username` VARCHAR(64) NOT NULL COMMENT '账号',
  `password` VARCHAR(64) NOT NULL COMMENT '密码',
  `name` VARCHAR(32) NOT NULL COMMENT '姓名',
  `gender` VARCHAR(32) COMMENT '性别',
  `tel` VARCHAR(32) COMMENT '手机号',
  `nation` VARCHAR(32) COMMENT '民族',
  `birthday` VARCHAR(32) COMMENT '出生日期',
  `email` VARCHAR(32) COMMENT '邮箱',
  `card_type` VARCHAR(32) COMMENT '证件类别',
  `cardID` VARCHAR(32) COMMENT '证件号',
  `house_tel` VARCHAR(32) COMMENT '家庭电话',
  `file_reason` VARCHAR(32) COMMENT '建档原因',
  `complication` VARCHAR(32) COMMENT '并发症',
  `house_address` VARCHAR(32) COMMENT '家庭地址',
  `unit_address` VARCHAR(32) COMMENT '单位地址',
  `edu_background` VARCHAR(32) COMMENT '文化程度',
  `position` VARCHAR(32) COMMENT '职业',
  `marital_status` VARCHAR(32) COMMENT '婚姻状况',
  `usual_type` VARCHAR(32) COMMENT '常住类型',
  `birthplace` VARCHAR(32) COMMENT '籍贾',
  `file_status` VARCHAR(32) COMMENT '档案状态',
  `adviser` INT COMMENT '顾问'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `sport`;
CREATE TABLE IF NOT EXISTS `sport`(
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '编码',

  `sport_name` VARCHAR(32) UNIQUE NOT NULL COMMENT '项目名',
  `sports_category` INT COMMENT '运动类别',
  `sport_risk` INT COMMENT '运动风险',
  `sport_time` INT COMMENT '运动时间',
  `sport_frequency` VARCHAR(32) COMMENT '运动频率',
  `tensile_site` VARCHAR(32) COMMENT '拉伸部位',
  `action_method` VARCHAR(32) COMMENT '动作方法'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='运动项目信息表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `sport_item`;
CREATE TABLE IF NOT EXISTS `sport_item`(
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '编码',

  `sport_name` INT NOT NULL COMMENT '项目名',
  `file_no` VARCHAR(32) COMMENT '档案号'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='用户运动项目信息表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `diet_record`;
CREATE TABLE IF NOT EXISTS `diet_record`(
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '编码',

  `time` VARCHAR(32) UNIQUE NOT NULL COMMENT '饮食时间',
  `times` DATE COMMENT '餐次',
  `detail` VARCHAR(64) COMMENT '饮食详细信息',
  `file_no` INT COMMENT '档案号'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='膳食记录表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `diet_storage`;
CREATE TABLE IF NOT EXISTS `diet_storage`(
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '编码',

  `name` VARCHAR(64) UNIQUE NOT NULL COMMENT '名称',
  `nick_name` VARCHAR(64) COMMENT '别名',
  `spell` VARCHAR(64) COMMENT '拼音',
  `energy` VARCHAR(64) COMMENT '热量(大卡)',
  `classification` VARCHAR(64) COMMENT '分类',
  `content_type` VARCHAR(64) COMMENT '含量类别',
  `fat` DECIMAL(10,2) COMMENT '脂肪(克)',
  `cellulose` DECIMAL(10,2) COMMENT '纤维素(克)',
  `vitamin_c` DECIMAL(10,2) COMMENT '维生素C(毫克)',
  `renieratene` DECIMAL(10,2) COMMENT '胡萝卜素(微克)',
  `riboflavin` DECIMAL(10,2) COMMENT '核黄素(毫克)',
  `cholesterol` DECIMAL(10,2) COMMENT '胆固醇(毫克)',
  `calcium` DECIMAL(10,2) COMMENT '钙(毫克)',
  `zinc` DECIMAL(10,2) COMMENT '锌(毫克)',
  `manganese` DECIMAL(10,2) COMMENT '锰(毫克)',
  `phosphorus` DECIMAL(10,2) COMMENT '磷(毫克)',
  `selenium` DECIMAL(10,2) COMMENT '硒(微克)',
  `carbohydrate` DECIMAL(10,2) COMMENT '碳水化合物(克)',
  `protein` DECIMAL(10,2) COMMENT '蛋白质(克)',
  `vitamin_a` DECIMAL(10,2) COMMENT '维生素A(微克)',
  `vitamin_e` DECIMAL(10,2) COMMENT '维生素E(毫克)',
  `thiamine` DECIMAL(10,2) COMMENT '硫胺素(毫克)',
  `niacin` DECIMAL(10,2) COMMENT '烟酸(毫克)',
  `magnesium` DECIMAL(10,2) COMMENT '镁(毫克)',
  `iron` DECIMAL(10,2) COMMENT '铁(毫克)',
  `copper` DECIMAL(10,2) COMMENT '铜(毫克)',
  `potassium` DECIMAL(10,2) COMMENT '钾(毫克)',
  `sodium` DECIMAL(10,2) COMMENT '钠(毫克)'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='膳食库信息表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `energy_measurement`;
CREATE TABLE IF NOT EXISTS `energy_measurement`(
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '编码',

  `food_storage_id` INT NOT NULL COMMENT '膳食库编码',
  `measurement` VARCHAR(64) COMMENT '度量单位',
  `energy` VARCHAR(64) COMMENT '热量(可食部分热量)'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='度量单位表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE IF NOT EXISTS `login_ticket`(
  `id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '表主键',

  `account_id` BIGINT UNSIGNED COMMENT '账户id',
  `role` TINYINT COMMENT '角色',
  `expired` DATETIME NOT NULL COMMENT '超时时间',
  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '是否有效',
  `ticket` VARCHAR(64) NOT NULL COMMENT '票据'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='token' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `adviser`;
CREATE TABLE IF NOT EXISTS `adviser`(
  `id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '表主键',

  `account` VARCHAR(64) COMMENT '账户',
  `password` VARCHAR(64) COMMENT '密码',
  `introduce` VARCHAR(500) COMMENT '介绍',
  `salt` VARCHAR(64) COMMENT '盐'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='顾问信息表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin`(
  `id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '表主键',

  `account` VARCHAR(64) COMMENT '账户',
  `password` VARCHAR(64) COMMENT '密码',
  `email` VARCHAR(64) COMMENT '邮箱',
  `salt` VARCHAR(64) COMMENT '盐'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='管理员信息表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `health_scheme`;
CREATE TABLE IF NOT EXISTS `health_scheme`(
  `id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '表主键',

  `user_file_no` VARCHAR(64) COMMENT '用户档案号',
  `diet_storage_ids` VARCHAR(512) COMMENT '膳食编号[1,2,3]',
  `quantitys` VARCHAR(512) COMMENT '数量[1杯,1碗]',
  `date` DATE COMMENT '日期',
  `eat_time` TINYINT COMMENT '1:早餐 2:午餐 3:晚餐',
  `adviser_id` VARCHAR(64) COMMENT '顾问主键'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='健康方案表' AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `session_log`;
CREATE TABLE IF NOT EXISTS `session_log`(
  `id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '表主键',

  `user_file_no` BIGINT UNSIGNED COMMENT '用户档案号',
  `sender` BIGINT UNSIGNED COMMENT '发送者',
  `content` VARCHAR(512) COMMENT '内容',
  `time` TIMESTAMP COMMENT '时间',
  `adviser_id` BIGINT UNSIGNED COMMENT '顾问主键'
)ENGINE INNODB DEFAULT CHARSET=utf8 COMMENT='对话记录' AUTO_INCREMENT=1;