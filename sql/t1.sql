CREATE TABLE `course_chapter_period`
(
    `id`           BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `gmt_create`   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `status_id`    TINYINT(4)  NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
    `sort`         INT(11)     NOT NULL COMMENT '排序',
    `course_id`    BIGINT(20)  NOT NULL COMMENT '课程ID',
    `chapter_id`   BIGINT(20)  NOT NULL COMMENT '章节ID',
    `period_name`  VARCHAR(64) NOT NULL COMMENT '课时名称',
    `period_desc`  VARCHAR(256)         DEFAULT NULL COMMENT '课时描述',
    `is_free`      TINYINT(4)  NOT NULL DEFAULT '1' COMMENT '是否免费(1:免费，0:收费)',
    `resource_id`  BIGINT(20)           DEFAULT NULL COMMENT '资源ID',
    PRIMARY KEY (`id`)
) COMMENT ='课程章节课时表';

CREATE TABLE website_carousel
(
    id              bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '轮播图id',
    gmt_create      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    gmt_modified    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    status_id       tinyint(4)   NOT NULL DEFAULT '1' COMMENT '状态(1:正常;0:禁用)',
    sort            int(11)      NOT NULL COMMENT '排序',
    carousel_title  varchar(64)  NOT NULL COMMENT '轮播图标题',
    carousel_img    varchar(255) NOT NULL COMMENT '轮播图图片路径',
    carousel_url    varchar(255)          DEFAULT NULL COMMENT '轮播图跳转链接',
    carousel_target varchar(16)           DEFAULT NULL COMMENT '轮播图跳转方式',
    begin_time      timestamp    NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
    end_time        timestamp    NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
    PRIMARY KEY (id)
) COMMENT ='网站轮播图表' ENGINE = InnoDB
                          DEFAULT CHARSET = utf8mb4;
