SET NAMES utf8mb4;
CREATE
database if NOT EXISTS `ship_data_system` default character set utf8mb4 collate utf8mb4_0900_ai_ci;
use
`ship_data_system`;

-- 表1：csv导入日志表
DROP TABLE IF EXISTS csv_enter_logs;
CREATE TABLE csv_enter_logs
(
    csv_enter_logs_id        INT PRIMARY KEY AUTO_INCREMENT COMMENT 'csv导入日志id',
    model_log_id             INT      DEFAULT NULL COMMENT '模型日志id，生成的csv的标识',
    csv_enter_logs           LONGTEXT COMMENT 'csv导入日志',
    csv_enter_number         INT      DEFAULT NULL COMMENT '数据量',
    csv_enter_success_number INT      DEFAULT NULL COMMENT '导入成功数量',
    csv_enter_logs_time      DATETIME DEFAULT NULL COMMENT '日志时间',
    csv_enter_user_id        INT      DEFAULT NULL COMMENT '导入人员id',
    deleted                  INT      DEFAULT NULL COMMENT '逻辑删除',
    INDEX                    idx_model_log_id (model_log_id),
    INDEX                    idx_user_id (csv_enter_user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='csv导入日志表';


-- 表2：国家地区表
DROP TABLE IF EXISTS country;
CREATE TABLE country
(
    country_id   INT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    country_name VARCHAR(32) DEFAULT NULL COMMENT '国家名称',
    english_name VARCHAR(64) DEFAULT NULL COMMENT '英文名称',
    INDEX        idx_country_name (country_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='国家地区表';

INSERT INTO country (country_name, english_name)
VALUES ('中国', 'China'),
       ('美国', 'United States');

-- 表3：数据1级分类表
DROP TABLE IF EXISTS device_class;
CREATE TABLE device_class
(
    device_class_id             INT PRIMARY KEY AUTO_INCREMENT COMMENT '数据类id、表id',
    device_class_name           VARCHAR(255) DEFAULT NULL COMMENT '数据类名称',
    device_class_describe       VARCHAR(255) DEFAULT NULL COMMENT '数据类描述',
    device_class_insql_time     DATETIME     DEFAULT NULL COMMENT '数据类信息入库时间',
    device_class_changesql_time DATETIME     DEFAULT NULL COMMENT '数据类信息修改时间',
    deleted                     INT          DEFAULT NULL COMMENT '数据类信息逻辑删除字段'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据1级分类表';

-- 表4：新闻、事件信息表
DROP TABLE IF EXISTS device;
CREATE TABLE device
(
    device_id             INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'id、表id',
    device_name           VARCHAR(255) DEFAULT NULL COMMENT '名称',
    device_class_id       INT          DEFAULT NULL COMMENT '分类id、外键',
    device_style_id       INT          DEFAULT NULL COMMENT '子分类id、外键',
    device_type_id        INT          DEFAULT NULL COMMENT '具体方向id、外键',
    device_use_year       INT          DEFAULT NULL COMMENT '投产年份',
    device_price          VARCHAR(255) DEFAULT NULL COMMENT '投入成本',
    device_using_unit     VARCHAR(255) DEFAULT NULL COMMENT '单位',
    device_country_id     INT          DEFAULT NULL COMMENT '所属国家id',
    device_location       VARCHAR(255) DEFAULT NULL COMMENT '具体地址',
    device_longitude      VARCHAR(255) DEFAULT NULL COMMENT '经度',
    device_latitude       VARCHAR(255) DEFAULT NULL COMMENT '纬度',
    device_img            VARCHAR(255) DEFAULT NULL COMMENT '图片地址',
    device_video          VARCHAR(255) DEFAULT NULL COMMENT '视频地址',
    device_introduce      TEXT COMMENT '详情介绍',
    device_news_link      TEXT COMMENT '相关新闻信息链接',
    device_news_title     VARCHAR(255) DEFAULT NULL COMMENT '相关新闻信息标题',
    device_news_time      DATETIME     DEFAULT NULL COMMENT '新闻时间',
    device_insql_time     DATETIME     DEFAULT NULL COMMENT '信息入库时间',
    device_changesql_time DATETIME     DEFAULT NULL COMMENT '信息修改时间',
    audit_flag            INT UNSIGNED DEFAULT NULL COMMENT '信息审核与否 0未审核 空也未审核 1已审核',
    deleted               INT UNSIGNED DEFAULT NULL COMMENT '信息逻辑删除字段',
    INDEX                 idx_class_id (device_class_id),
    INDEX                 idx_style_id (device_style_id),
    INDEX                 idx_type_id (device_type_id),
    INDEX                 idx_country_id (device_country_id),
    INDEX                 idx_audit_flag (audit_flag),
    INDEX                 idx_deleted (deleted)
    FOREIGN KEY (device_class_id) REFERENCES device_class(device_class_id),
    FOREIGN KEY (device_style_id) REFERENCES device_style (device_style_id),
    FOREIGN KEY (device_type_id) REFERENCES device_type (device_type_id),
    FOREIGN KEY (device_country_id) REFERENCES country (country_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻、事件信息表';

-- 表5：2级分类表
DROP TABLE IF EXISTS device_style;
CREATE TABLE device_style
(
    device_style_id             INT PRIMARY KEY AUTO_INCREMENT COMMENT '分类维度id、表id',
    device_style_name           VARCHAR(255) DEFAULT NULL COMMENT '分类名称',
    device_style_describe       VARCHAR(255) DEFAULT NULL COMMENT '2级分类描述',
    device_style_class_id       INT NOT NULL COMMENT '所属1级分类id、外键',
    device_style_insql_time     DATETIME     DEFAULT NULL COMMENT '2级分类信息入库时间',
    device_style_changesql_time DATETIME     DEFAULT NULL COMMENT '2级分类信息修改时间',
    deleted                     INT          DEFAULT NULL COMMENT '2级分类信息逻辑删除字段',
    INDEX                       idx_class_id (device_style_class_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='2级分类表';

-- 表6：3级分类表
DROP TABLE IF EXISTS device_type;
CREATE TABLE device_type
(
    device_type_id             INT PRIMARY KEY AUTO_INCREMENT COMMENT '分类id、表id',
    device_type_name           VARCHAR(255) DEFAULT NULL COMMENT '分类名称',
    device_type_describe       VARCHAR(255) DEFAULT NULL COMMENT '分类描述',
    device_type_style_id       INT          DEFAULT NULL COMMENT '分类所属式id、外键',
    device_type_insql_time     DATETIME     DEFAULT NULL COMMENT '分类信息入库时间',
    device_type_changesql_time DATETIME     DEFAULT NULL COMMENT '分类信息修改时间',
    deleted                    INT          DEFAULT NULL COMMENT '分类信息逻辑删除字段',
    INDEX                      idx_style_id (device_type_style_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='3级分类表';

-- 表7：上传文件表
DROP TABLE IF EXISTS doc;
CREATE TABLE doc
(
    doc_id         INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '文件id',
    doc_name       VARCHAR(255) DEFAULT NULL COMMENT '文件名 字面意思',
    doc_type       VARCHAR(255) DEFAULT NULL COMMENT '文件类型 比如csv excel等',
    doc_address    VARCHAR(255) DEFAULT NULL COMMENT '文件存储路径',
    import_state   VARCHAR(255) DEFAULT NULL COMMENT '导入状态 已录入 未录入',
    upload_time    DATETIME     DEFAULT NULL COMMENT '文件上传时间',
    import_time    DATETIME     DEFAULT NULL COMMENT '数据导入时间',
    upload_user_id INT          DEFAULT NULL COMMENT '上传人员id',
    import_user_id INT          DEFAULT NULL COMMENT '录入人员id',
    INDEX          idx_upload_user (upload_user_id),
    INDEX          idx_import_user (import_user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上传文件表';

-- 表8：用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id        BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    user_role      INT UNSIGNED  NOT NULL COMMENT '用户角色'
    user_name      VARCHAR(30)  NOT NULL COMMENT '用户账号',
    nick_name      VARCHAR(30)  NOT NULL COMMENT '用户昵称',
    email          VARCHAR(50)  DEFAULT NULL COMMENT '用户邮箱',
    sex            VARCHAR(1)   DEFAULT NULL COMMENT '用户性别（0男 1女 2未知）',
    avatar_address VARCHAR(100) DEFAULT NULL COMMENT '头像地址',
    password       VARCHAR(100) DEFAULT NULL COMMENT '密码',
    remark         VARCHAR(500) NOT NULL COMMENT '备注',
    phonenumber    VARCHAR(11)  NOT NULL COMMENT '手机号码',
    UNIQUE KEY uk_user_name (user_name),
    INDEX          idx_phonenumber (phonenumber)
    FOREIGN KEY (user_role) REFERENCES user_role(user_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT INTO user (user_role, user_name, nick_name, remark, phonenumber)
VALUES (1, 'root', '超级管理员', '超级管理员', '12345678901');

-- 表9：爬虫模型表
DROP TABLE IF EXISTS m_reptile_model;
CREATE TABLE m_reptile_model
(
    m_reptile_model_id             INT PRIMARY KEY AUTO_INCREMENT COMMENT '爬虫模型id',
    m_reptile_model_name           VARCHAR(255) DEFAULT NULL COMMENT '爬虫模型名称',
    m_reptile_model_introduce      TEXT COMMENT '爬虫模型介绍',
    m_reptile_model_web            VARCHAR(255) DEFAULT NULL COMMENT '爬取目标网址',
    m_reptile_model_state          VARCHAR(255) DEFAULT NULL COMMENT '爬虫模型状态 正在运行、未运行',
    m_reptile_model_script_address VARCHAR(255) DEFAULT NULL COMMENT '运行爬虫模型脚本地址',
    m_reptile_model_address        VARCHAR(255) DEFAULT NULL COMMENT '爬虫文件地址',
    m_reptile_model_time           DATETIME     DEFAULT NULL COMMENT '模型录入时间',
    deleted                        INT          DEFAULT NULL COMMENT '逻辑删除字段'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='爬虫模型表';

-- 表10：关键词表
DROP TABLE IF EXISTS keyword;
CREATE TABLE keyword
(
    keyword_id              INT(11) PRIMARY KEY AUTO_INCREMENT COMMENT '关键词id',
    model_id                INT(11) NOT NULL COMMENT '模型id',
    keyword_name            VARCHAR(255) DEFAULT NULL COMMENT '关键词内容',
    incremental_spider_time DATETIME     DEFAULT NULL COMMENT '用来判断增量爬虫的时间',
    use_flag                INT(11) DEFAULT NULL COMMENT '是否使用',
    keyword_describe        VARCHAR(255) DEFAULT NULL COMMENT '关键词内容描述',
    keyword_insert_time     DATETIME     DEFAULT NULL COMMENT '关键词插入时间',
    keyword_change_time     DATETIME     DEFAULT NULL COMMENT '关键词修改时间',
    deleted                 INT(11) DEFAULT NULL COMMENT '关键词逻辑删除字段',
    INDEX                   idx_model_id (model_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关键词表';

-- 表11： 用户角色表
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
    user_role_id   INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '用户角色id',
    user_role_name VARCHAR(255) DEFAULT NULL COMMENT '用户角色名称',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

INSERT INTO user_role (user_role_name)
VALUES ('管理员'),
       ('数据录入员'),
       ('数据审核员'),
       ('普通用户');

-- 表12：用户权限表
DROP TABLE IF EXISTS user_permission;
CREATE TABLE user_permission
(
    user_permission_id   INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '用户权限id',
    user_role            INT UNSIGNED NOT NULL COMMENT '用户角色id',
    user_permission_name VARCHAR(255) DEFAULT NULL COMMENT '用户权限名称',
    FOREIGN KEY (user_role) REFERENCES user_role (user_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限表';