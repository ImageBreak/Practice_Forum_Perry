# Practice_Forum_Perry
练手用的小型网络论坛项目，基于Maven创建的Java Web以及Mysql

### Mysql建表语句
1. category表
```
CREATE TABLE `category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

2. reply表
```
CREATE TABLE `reply` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `topic_id` int DEFAULT NULL,
  `floor` int DEFAULT NULL COMMENT '楼层编号，回复是不能删除的',
  `content` varchar(524) DEFAULT NULL COMMENT '回复内容',
  `user_id` int DEFAULT NULL,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '回复⼈名称',
  `user_img` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '回复⼈头像',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete` int DEFAULT NULL COMMENT '0是正常，1是禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

3. topic表
```
CREATE TABLE `topic` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `c_id` int DEFAULT NULL COMMENT '分类',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `pv` int DEFAULT NULL COMMENT '浏览量',
  `user_id` int DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `user_img` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `hot` int DEFAULT '0' COMMENT '是否热门 1是热门',
  `delete` int DEFAULT '0' COMMENT '0是未删除，1是已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

4. user表
```
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(32) DEFAULT NULL,
  `pwd` varchar(128) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `role` int DEFAULT NULL COMMENT '1是普通⽤户，2是管理员',
  `username` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

### 然后启动Mysql，修改一下database.properties的配置
### mvn package打包个war出来，复制到Tomcat的目录下

### 启动Tomcat，搞定


