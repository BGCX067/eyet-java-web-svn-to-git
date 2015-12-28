DROP TABLE IF EXISTS `auth`;

CREATE TABLE `auth` (
  `id` int(11) NOT NULL auto_increment,
  `roleid` int(11) default NULL,
  `resourceid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `auth` */

insert  into `auth`(`id`,`roleid`,`resourceid`) values (1,1,3),(3,1,9),(4,2,9),(5,2,4),(6,2,5),(7,2,6),(8,2,7),(9,3,9),(10,3,4),(11,4,4),(13,3,10),(15,2,10);

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` int(11) NOT NULL auto_increment,
  `resuri` varchar(100) character set utf8 default NULL,
  `resdesc` varchar(100) character set utf8 default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `resource` */

insert  into `resource`(`id`,`resuri`,`resdesc`) values (1,'Index/*','默认'),(2,'Error/*','错误事件'),(3,'Member/*','会员管理'),(4,'Member/login','会员登陆'),(5,'Member/memlist','获取会员列表'),(6,'Member/tomemadd','进入-添加用户'),(7,'Member/memadd','添加用户'),(8,'Member/memdel','删除用户'),(9,'Admin/*','管理页面'),(10,'Member/quit','退出');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `rname` varchar(50) character set utf8 default NULL,
  `rdesc` varchar(100) character set utf8 default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`id`,`rname`,`rdesc`) values (1,'admin','会长'),(2,'officer','干事'),(3,'member','普通会员'),(4,'guest','临时访客');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(100) character set utf8 default NULL,
  `passwd` varchar(50) default NULL,
  `roleid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`passwd`,`roleid`) values (1,'admin','123',1),(2,'user','user',1),(6,'member','123',3);
