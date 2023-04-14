create table sys_user
(
    user_id      bigint(20) not null auto_increment comment '用户ID',
    user_name    varchar(30) not null comment '用户账号',
    nick_name    varchar(30) not null comment '用户昵称',
    user_type    varchar(2)   default '00' comment '用户类型（00系统用户）',
    phone_number varchar(11)  default '' comment '手机号码',
    sex          char(1)      default '0' comment '用户性别（0男 1女 2未知）',
    avatar       varchar(100) default '' comment '头像地址',
    password     varchar(100) default '' comment '密码',
    status       char(1)      default '0' comment '帐号状态（0正常 1停用）',
    del_flag     char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    login_date   datetime comment '最后登录时间',
    create_by    varchar(64)  default '' comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64)  default '' comment '更新者',
    update_time  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    remark       varchar(500) default null comment '备注',
    primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';

create table sys_role
(
    role_id             bigint(20) not null auto_increment comment '角色ID',
    role_name           varchar(30)  not null comment '角色名称',
    role_key            varchar(100) not null comment '角色权限字符串',
    role_sort           int(4) not null comment '显示顺序',
    data_scope          char(1)      default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    menu_check_strictly tinyint(1) default 1 comment '菜单树选择项是否关联显示',
    status              char(1)      not null comment '角色状态（0正常 1停用）',
    del_flag            char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by           varchar(64)  default '' comment '创建者',
    create_time         datetime comment '创建时间',
    update_by           varchar(64)  default '' comment '更新者',
    update_time         TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    remark              varchar(500) default null comment '备注',
    primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';

create table sys_user_role
(
    user_id bigint(20) not null comment '用户ID',
    role_id bigint(20) not null comment '角色ID',
    primary key (user_id, role_id)
) engine=innodb comment = '用户和角色关联表';

create table sys_dict_type
(
    dict_id     bigint(20) not null auto_increment comment '字典主键',
    dict_name   varchar(100) default '' comment '字典名称',
    dict_type   varchar(100) default '' comment '字典类型',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_id),
    unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';

create table sys_dict_data
(
    dict_code   bigint(20) not null auto_increment comment '字典编码',
    dict_sort   int(4) default 0 comment '字典排序',
    dict_label  varchar(100) default '' comment '字典标签',
    dict_value  varchar(100) default '' comment '字典键值',
    dict_type   varchar(100) default '' comment '字典类型',
    css_class   varchar(100) default null comment '样式属性（其他样式扩展）',
    list_class  varchar(100) default null comment '表格回显样式',
    is_default  char(1)      default 'N' comment '是否默认（Y是 N否）',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_code)
) engine=innodb auto_increment=100 comment = '字典数据表';

CREATE TABLE `resumes`
(
    `id`               int(11) NOT NULL AUTO_INCREMENT COMMENT '简历 ID',
    `user_id`          int(11) NOT NULL COMMENT '用户 ID',
    `name`             varchar(255) NOT NULL COMMENT '姓名',
    `gender`           tinyint(1) NOT NULL COMMENT '性别',
    `birthday`         date         NOT NULL COMMENT '出生日期',
    `phone`            varchar(255) NOT NULL COMMENT '电话号码',
    `email`            varchar(255) NOT NULL COMMENT '电子邮件',
    `picture`          varchar(255) not null comment '照片',
    `native_place`     varchar(50)  not null comment '籍贯',
    `education`        int          NOT NULL COMMENT '最高学历',
    `university`       varchar(255) NOT NULL COMMENT '毕业院校',
    `major`            int          NOT NULL COMMENT '专业类别',
    `political_status` int          not null comment '政治面貌',
    `graduation_time`  int          not null comment '毕业时间',
    `created_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='简历表';

CREATE TABLE `education_background`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`         bigint(20) NOT NULL COMMENT '用户ID',
    `school`          varchar(100) NOT NULL COMMENT '学校',
    `major_category`  int          not null comment '专业类别',
    `major_name`      varchar(50)  NOT NULL COMMENT '专业名称',
    `degree`          varchar(50)  NOT NULL COMMENT '学位',
    `start_time`      date         NOT NULL COMMENT '开始时间',
    `end_time`        date         NOT NULL COMMENT '结束时间',
    `comment`         varchar(200) DEFAULT NULL COMMENT '专业描述',
    `learning_method` int          not null comment '学习方式',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教育背景表';

CREATE TABLE `work_experience`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`         int(11) NOT NULL COMMENT '用户ID',
    `company_name`    varchar(255)  NOT NULL COMMENT '公司名称',
    `position_name`   varchar(255)  NOT NULL COMMENT '职位名称',
    `start_time`      date          NOT NULL COMMENT '开始时间',
    `end_time`        date          NOT NULL COMMENT '结束时间',
    `experience_type` int           not null comment '经历类型',
    `job_description` varchar(1000) NOT NULL COMMENT '工作描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工作/实习经历表';

CREATE TABLE `project_experience`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`       int(11) NOT NULL COMMENT '用户ID',
    `project_name`  varchar(255) NOT NULL COMMENT '项目/比赛名称',
    `team_size`     int          not null comment '团队规模',
    `hold_position` varchar(50)  not null comment '担任职务',
    `start_time`    datetime     NOT NULL COMMENT '开始时间',
    `end_time`      datetime     NOT NULL COMMENT '结束时间',
    `description`   text COMMENT '描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='比赛/项目经历表';

CREATE TABLE `student_work_experience`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT,
    `user_id`           int(11) NOT NULL COMMENT '用户ID',
    `school_name`       varchar(50)  not null comment '学校名称',
    `organization_name` varchar(255) NOT NULL COMMENT '组织名称',
    `position`          varchar(255) NOT NULL COMMENT '职位名称',
    `start_time`        date         NOT NULL COMMENT '开始时间',
    `end_time`          date         NOT NULL COMMENT '结束时间',
    `description`       text COMMENT '经历描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='学生工作经历表';

CREATE TABLE `honor`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     int(11) NOT NULL COMMENT '用户ID',
    `honor_name`  varchar(255) NOT NULL COMMENT '奖项名称',
    `honor_date`  varchar(50)           DEFAULT NULL COMMENT '获奖日期',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='荣誉奖励表';

CREATE TABLE `other_info`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     int                  DEFAULT NULL COMMENT '用户ID',
    `title`       varchar(50) not null comment '标题',
    `describe`    text        not null comment '描述',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='其他信息表';

CREATE TABLE `company`
(
    `id`          INT(11) NOT NULL AUTO_INCREMENT COMMENT '公司ID',
    `name`        VARCHAR(100) NOT NULL COMMENT '公司名称',
    `description` VARCHAR(500) NOT NULL COMMENT '公司描述',
    `address`     VARCHAR(200) NOT NULL COMMENT '公司地址',
    `phone`       VARCHAR(20)  NOT NULL COMMENT '联系电话',
    `email`       VARCHAR(50)  NOT NULL COMMENT '联系邮箱',
    `logo_url`    VARCHAR(200) NOT NULL COMMENT '公司LOGO的URL',
    `created_at`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='公司表';


CREATE TABLE IF NOT EXISTS job_openings
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY
    comment
    'ID',
    recruitment_info_id
    int
    not
    null
    comment
    '招聘简章ID',
    job_title
    VARCHAR
(
    255
) NOT NULL COMMENT '职位名称',
    work_location VARCHAR
(
    255
) NOT NULL COMMENT '工作地点',
    recruit_num INT NOT NULL COMMENT '招聘人数',
    salary_range VARCHAR
(
    255
) NOT NULL COMMENT '薪资范围',
    job_description TEXT NOT NULL COMMENT '岗位介绍',
    job_responsibilities text not null comment '工作职责',
    job_requirements text not null comment '岗位要求',
    recruitment_status VARCHAR
(
    255
) NOT NULL COMMENT '招聘状态',
    publication_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    end_date DATE NOT NULL COMMENT '截止时间',
    creator_id INT NOT NULL COMMENT '创建者ID'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='招聘职位表';

CREATE TABLE IF NOT EXISTS `recruitment_info`
(
    `id` int
(
    11
) NOT NULL AUTO_INCREMENT COMMENT '招聘信息ID',
    `company_id` int
(
    11
) NOT NULL COMMENT '公司ID',
    `recruit_target` varchar
(
    50
) NOT NULL COMMENT '招聘对象',
    `salary` decimal
(
    10,
    2
) NOT NULL COMMENT '薪资待遇',
    `recruit_num` int
(
    11
) NOT NULL COMMENT '招聘人数',
    `work_location` varchar
(
    100
) NOT NULL COMMENT '工作地点',
    `job_description` varchar
(
    500
) NOT NULL COMMENT '工作职责',
    `job_requirements` varchar
(
    500
) NOT NULL COMMENT '任职要求',
    `recruit_title` varchar
(
    50
) NOT NULL COMMENT '招聘标题',
    `publish_date` datetime NOT NULL COMMENT '发布日期',
    `expire_date` datetime NOT NULL COMMENT '截止日期',
    `status` tinyint
(
    1
) NOT NULL DEFAULT '0' COMMENT '状态（0：未发布，1：已发布）',
    PRIMARY KEY
(
    `id`
)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='招聘简章表';

CREATE TABLE `delivery_record`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`           int(11) NOT NULL COMMENT '用户ID',
    `position_id`       int(11) NOT NULL COMMENT '职位ID',
    `resume_id`         int(11) NOT NULL COMMENT '简历ID',
    `delivery_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '投递时间',
    `status`            tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：未处理，1：已处理，2：已通知）',
    `interview_time`    datetime          DEFAULT NULL COMMENT '面试时间',
    `interview_address` varchar(100)      DEFAULT NULL COMMENT '面试地点',
    `interviewer`       varchar(50)       DEFAULT NULL COMMENT '面试官',
    `interview_result`  tinyint(1) DEFAULT NULL COMMENT '面试结果（0：未知，1：通过，2：淘汰）',
    `feedback`          text COMMENT '反馈',
    `created_at`        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='投递记录表';