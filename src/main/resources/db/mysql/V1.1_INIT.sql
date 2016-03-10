CREATE TABLE IF NOT EXISTS `accessbyname` (
    `eventname` varchar(100) NOT NULL,
	`count` int(11) DEFAULT NULL,
	PRIMARY KEY (`eventname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `assignedevent` (
    `eventname` varchar(100) NOT NULL,
	`auditoriumname` varchar(50) NOT NULL,
	`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`id` int(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `auditoriums` (
	`name` varchar(50) NOT NULL,
	`numberOfSeats` int(11) DEFAULT NULL,
	`vipSeats` varchar(200) DEFAULT NULL,
	PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `bookedtickets` (
	`eventname` varchar(100) NOT NULL,
	`count` int(11) DEFAULT NULL,
	PRIMARY KEY (`eventname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `events` (
	`name` varchar(100) NOT NULL,
	`basePrice` double DEFAULT NULL,
	`rate` varchar(45) DEFAULT NULL,
	PRIMARY KEY (`name`),
	UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `pricequeried` (
	`eventname` varchar(100) NOT NULL,
	`count` int(11) DEFAULT NULL,
	PRIMARY KEY (`eventname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tickets` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`userid` int(11) DEFAULT NULL,
	`assignedeventid` int(11) DEFAULT NULL,
	`seat` int(11) DEFAULT NULL,
	`price` double DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `totaldiscounts` (
	`discountstrategy` varchar(100) NOT NULL,
	`count` int(11) DEFAULT NULL,
	PRIMARY KEY (`discountstrategy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `totaldiscountsforuser` (
	`userid` int(11) NOT NULL,
	`count` int(11) DEFAULT NULL,
	PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `users` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) DEFAULT NULL,
	`email` varchar(45) DEFAULT NULL,
	`birthDay` TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE KEY `id_UNIQUE` (`id`),
	UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;