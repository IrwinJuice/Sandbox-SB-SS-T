--
-- Table`role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `role`    VARCHAR(25)         DEFAULT 'VISITOR',
  primary key (`role_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  default charset = utf8;

--
-- Table `user`
--

drop table if exists `user`;
create table `user` (
  `user_id`   bigint(11) not null AUTO_INCREMENT,
  `email`     varchar(255)        default null,
  `name`      varchar(255)        default null,
  `last_name` varchar(255)        default null,
  `password`  varchar(255)        default null,
  primary key (`user_id`)
)
  ENGINE = InnoDB
  default charset = utf8;

--
-- Table `user_role`
--

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` BIGINT(11) NOT NULL,
  `role_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `user_role`
  ADD CONSTRAINT `FK_UR_User_ID`
FOREIGN KEY (user_id) REFERENCES `user` (`user_id`);

ALTER TABLE `user_role`
  ADD CONSTRAINT `FK_UR_Role_ID`
FOREIGN KEY (role_id) REFERENCES `role` (`role_id`);
