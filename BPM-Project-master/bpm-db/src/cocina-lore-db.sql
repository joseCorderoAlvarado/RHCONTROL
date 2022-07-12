/* 
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
/**
 * Author:  Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 * Created: 19/04/2021
 */

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
-- SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE IF EXISTS `cocina_lore_db_arrebol`;

CREATE DATABASE IF NOT EXISTS `cocina_lore_db_arrebol` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `cocina_lore_db_arrebol`;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_OFFICE`
--
CREATE TABLE `CL_OFFICE`(
    `id` char(36) NOT NULL,
    `office_name` varchar(100) NOT NULL,
    `address` varchar(250),
    `office_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_office_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_office_uk` UNIQUE KEY (`office_name`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_ROLE`
--
CREATE TABLE `CL_ROLE`(
    `id` char(36) NOT NULL,
    `role_name` varchar(100) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_role_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_role_uk` UNIQUE KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_ROUTE`
--
CREATE TABLE `CL_ROUTE`(
    `id` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `route_name` varchar(25) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_route_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_route_uk` UNIQUE KEY (`id_office`,`route_name`),
    CONSTRAINT `cl_route_to_cl_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `CL_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_HUMAN_RESOURCES`
--
CREATE TABLE `CL_HUMAN_RESOURCE` (
  `id` char(36) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `second_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) NOT NULL,
  `middle_name` varchar(25) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `avatar` varchar(150) NOT NULL,
  `curp` varchar(20) DEFAULT NULL,
  `rfc` varchar(13) DEFAULT NULL,
  `ife` varchar(20) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `human_resource_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
  `id_role` char(36) NOT NULL,
  `payment` numeric(8,2) DEFAULT NULL,
  `imss` numeric(8,2) DEFAULT NULL,
  `created_by` char(36) DEFAULT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `person_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_human_resource_to_cl_role_fk` 
        FOREIGN KEY (`id_role`) REFERENCES `CL_ROLE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_HUMAN_RESOURCE_HAS_ROUTE`
--
CREATE TABLE `CL_HUMAN_RESOURCE_HAS_ROUTE`( 
    `id_human_resource` char(36) NOT NULL,
    `id_route` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_human_resource_has_route_pk` PRIMARY KEY (`id_human_resource`, `id_route`),
    CONSTRAINT `cl_human_resource_has_route_to_cl_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `CL_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `cl_human_resource_has_route_to_cl_route_fk` 
        FOREIGN KEY (`id_route`) REFERENCES `CL_ROUTE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_PEOPLE`
--
CREATE TABLE `CL_PEOPLE`( 
    `id` char(36) NOT NULL,
    `first_name` varchar(25) NOT NULL,
    `second_name` varchar(25) DEFAULT NULL,
    `last_name` varchar(25) NOT NULL,
    `middle_name` varchar(25) NOT NULL,
    `birthdate` date DEFAULT NULL,
    `thumbnail` varchar(250) NOT NULL,
    `phone_home` varchar(15) NOT NULL,
    `address_home` varchar(150) NOT NULL,
    `phone_business` varchar(15),
    `address_business` varchar(150),
    `company_name` varchar(150),
    `people_type` ENUM('CUSTOMER') NOT NULL DEFAULT 'CUSTOMER',
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `id_office` char(36) NOT NULL,
    `id_route` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_people_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_people_to_cl_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `CL_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `cl_people_to_cl_route_fk` 
        FOREIGN KEY (`id_route`) REFERENCES `CL_ROUTE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_ORDER`
--
CREATE TABLE `CL_ORDER`( 
    `id` char(36) NOT NULL,
    `id_customer` char(36) NOT NULL,
    `id_route` char(36) NOT NULL,
    `order_status` ENUM('PENDING', 'FINISH', 'REJECTED', 'TO_DELIVERY') NOT NULL DEFAULT 'PENDING',
    `new_customer` ENUM('ENEBLED', 'DISABLED') DEFAULT 'DISABLED',
    `amount_paid` numeric(8,2) NOT NULL,
    `amount_to_pay` numeric(8,2) NOT NULL,
    `comments` varchar(600),
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36),
    `last_updated_on` datetime,
    CONSTRAINT `cl_order_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_order_to_cl_person_as_customer_fk` 
        FOREIGN KEY (`id_customer`) REFERENCES `CL_PEOPLE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `cl_order_to_cl_route_fk` 
        FOREIGN KEY (`id_route`) REFERENCES `CL_ROUTE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_HUMAN_RESOURCE_BY_OFFICE`
--
CREATE TABLE `CL_HUMAN_RESOURCE_BY_OFFICE`( 
    `id` char(36) NOT NULL,
    `id_human_resource` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `application_owner` ENUM('APP_OWNER', 'APP_USER') NOT NULL DEFAULT 'APP_USER',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_human_resource_by_office_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_human_resource_by_office_uk` UNIQUE KEY (`id_human_resource`, `id_office`),
    CONSTRAINT `cl_human_resource_by_office_to_cl_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `CL_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `cl_human_resource_by_office_to_cl_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `CL_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_USER`
--
CREATE TABLE `CL_USER`(
    `id` char(36) NOT NULL,
    `id_human_resource` char(36) NOT NULL,
    `user_name` varchar(100) NOT NULL,
    `pwd` varchar(100)NOT NULL,
    `user_type` ENUM('WEB', 'MOBILE', 'BOTH') NOT NULL,
    `user_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `application_owner` ENUM('APP_OWNER', 'APP_USER') NOT NULL DEFAULT 'APP_USER',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_user_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_user_by_office_to_cl_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `CL_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_ORDER_BY_USER`
--
CREATE TABLE `CL_ORDER_BY_USER`(
    `id_order` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `loan_by_user_status` ENUM('PENDING', 'FINISH','REJECTED', 'TO_DELIVERY') NOT NULL DEFAULT 'PENDING',
    `comments` varchar(150),
    `owner_loan` ENUM('CURRENT_OWNER', 'OLD_OWNER') NOT NULL,
    `order_in_list` smallint DEFAULT 0,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_order_by_user_pk` PRIMARY KEY (`id_order`, `id_user`),
    CONSTRAINT `cl_order_by_user_to_cl_order_fk` 
        FOREIGN KEY (`id_order`) REFERENCES `CL_ORDER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `cl_order_by_user_to_cl_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `CL_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_USER_BY_OFFICE`
--
CREATE TABLE `CL_USER_BY_OFFICE`( 
    `id` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `user_by_office_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `application_owner` ENUM('APP_OWNER', 'APP_USER')NOT NULL  DEFAULT 'APP_USER',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_user_by_office_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_user_by_office_uk` UNIQUE KEY (`id_user`, `id_office`),
    CONSTRAINT `cl_user_by_office_to_cl_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `CL_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `cl_user_by_office_to_cl_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `CL_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_PERMISSION`
--
CREATE TABLE `CL_PERMISSION` (
    `id` char(36) NOT NULL,
    `permission` varchar(200) NOT NULL, -- dashboard.policy.to.expire.name
    `description` varchar(200) NOT NULL, -- dashboard.policy.to.expire.description
    `menu_path` varchar(200) NOT NULL, -- dashboard.policy.to.expire.path
    `left_to_right_order` smallint NOT NULL, -- Orden en la que aparece el menu principal (Dashboard, Administración, Catálogo)
    `top_to_bottom_order` smallint NOT NULL, -- Orden en la que aparece de arriba hacia abajo
    `permission_type` ENUM('PUBLIC', 'PRIVATE', 'EXCLUSIVE') NOT NULL,
    `parent_name` varchar(200),
    `permission_status` ENUM('ENEBLED', 'DISABLED') NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_permission_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_permission_uk` UNIQUE KEY (`permission`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_USER_BY_OFFICE_HAS_PERMISSION`
--
CREATE TABLE `CL_USER_BY_OFFICE_HAS_PERMISSION` (
    `id_user_by_office` char(36) NOT NULL,
    `id_permission` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_user_by_office_has_permission_pk` PRIMARY KEY (`id_user_by_office`,`id_permission`) ,
    CONSTRAINT `cl_user_by_office_has_permission_to_cl_user_by_office_fk` 
        FOREIGN KEY (`id_user_by_office`) REFERENCES `CL_USER_BY_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `cl_user_by_office_has_permission_to_cl_permission_fk` 
        FOREIGN KEY (`id_permission`) REFERENCES `CL_PERMISSION`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `CL_USER_MOBILE_PREFERECE`
--
CREATE TABLE `CL_USER_MOBILE_PREFERECE`( 
    `id` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `preference_name` varchar(25) NOT NULL,
    `preference_value` varchar(25) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `cl_user_mobile_preference_pk` PRIMARY KEY (`id`),
    CONSTRAINT `cl_user_mobile_preference_uk` UNIQUE KEY (`id_user`,`preference_name`),
    CONSTRAINT `cl_user_mobile_preference_to_cl_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `CL_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------

-- -------------------------------------------------------------
-- 
-- Estructura para la vista `CL_SECURITY_AUTHENTICATION` (USER)
--
CREATE OR REPLACE VIEW `CL_SECURITY_AUTHENTICATION`  AS
SELECT CONCAT(`usr`.`user_name`,`ubo`.`id_office`) AS `user_name`, `usr`.`pwd` AS `pwd`
FROM `CL_USER_BY_OFFICE` `ubo`
INNER JOIN `CL_USER` `usr` ON `ubo`.`id_user` = `usr`.`id`
INNER JOIN `CL_HUMAN_RESOURCE` `hr` ON `usr`.`id_human_resource` = `hr`.`id`
WHERE
`usr`.`user_status` = 'ENEBLED' AND
`usr`.`user_type` IN ('WEB', 'BOTH') AND
`ubo`.`user_by_office_status` = 'ENEBLED' AND 
`hr`.`human_resource_status` = 'ENEBLED';
-- ------------------------------------------------------------
-- 
-- Estructura para la vista `CL_SECURITY_AUTHORIZATION` (ROLE)
--
CREATE OR REPLACE VIEW `CL_SECURITY_AUTHORIZATION` AS
SELECT CONCAT(`usr`.`user_name`,`ubo`.`id_office`) AS `user_name`, `perm`.`permission` AS `permission`
FROM `CL_PERMISSION` `perm`
INNER JOIN `CL_USER_BY_OFFICE_HAS_PERMISSION` `ubohp` ON `perm`.`id` = `ubohp`.`id_permission`
INNER JOIN `CL_USER_BY_OFFICE` `ubo` ON `ubohp`.`id_user_by_office` = `ubo`.`id`
INNER JOIN `CL_USER` `usr` ON `ubo`.`id_user` = `usr`.`id`
INNER JOIN `CL_HUMAN_RESOURCE` `hr` ON `usr`.`id_human_resource` = `hr`.`id`
WHERE 
`perm`.`permission_status` = 'ENEBLED' AND
`ubo`.`user_by_office_status` = 'ENEBLED' AND
`usr`.`user_status` = 'ENEBLED' AND 
`usr`.`user_type` IN ('WEB', 'BOTH') AND
`hr`.`human_resource_status` = 'ENEBLED'
ORDER BY `user_name`;
-- -------------------------------------------------------------------
-- 
-- Estructura para la vista `CL_SECURITY_AUTHENTICATION_MOBILE` (USER)
--
CREATE OR REPLACE VIEW `CL_SECURITY_AUTHENTICATION_MOBILE`  AS
SELECT 
`usr`.`id` AS `id`,
`usr`.`user_name` AS `user_name`,
`usr`.`pwd` AS `pwd`,
`hr`.`avatar` AS `avatar`,
`ubo`.`id_office` AS `id_office`,
`hrhr`.`id_route` AS `id_route`
FROM `CL_USER_BY_OFFICE` `ubo`
INNER JOIN `CL_USER` `usr` ON `ubo`.`id_user` = `usr`.`id`
INNER JOIN `CL_HUMAN_RESOURCE` `hr` ON `usr`.`id_human_resource` = `hr`.`id`
INNER JOIN `CL_HUMAN_RESOURCE_HAS_ROUTE` `hrhr` ON `hr`.`id` = `hrhr`.`id_human_resource`
WHERE
`usr`.`user_status` = 'ENEBLED' AND
`usr`.`user_type` IN ('MOBILE', 'BOTH') AND
`ubo`.`user_by_office_status` = 'ENEBLED' AND 
`hr`.`human_resource_status` = 'ENEBLED';

