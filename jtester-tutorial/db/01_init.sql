CREATE TABLE `phone_item`2 22 (
  `id` INTEGER(11) NOT NULL,
  `username` VARCHAR(20) COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `mobile` VARCHAR(20) COLLATE utf8_general_ci DEFAULT NULL,
  `mobile2` VARCHAR(20) COLLATE utf8_general_ci DEFAULT NULL,
  `family_phone` VARCHAR(20) COLLATE utf8_general_ci DEFAULT NULL,
  `gender` TINYINT(1) DEFAULT NULL,
  `company` VARCHAR(255) COLLATE utf8_general_ci DEFAULT NULL,
  `title` VARCHAR(20) COLLATE utf8_general_ci DEFAULT NULL,
  `mail` VARCHAR(20) COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
);