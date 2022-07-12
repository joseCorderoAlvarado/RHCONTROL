-- --------------------------------------------------------
-- Arrebol Consuntancy
-- version 1.0.1
-- http://www.arrebolconsultancy.com
-- --------------------------------------------------------

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
-- SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE IF EXISTS `apo_pro_com_april_ten`;

CREATE DATABASE IF NOT EXISTS `apo_pro_com_april_ten` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `apo_pro_com_april_ten`;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_ERROR_APP_LOG`
--
CREATE TABLE `APC_ERROR_APP_LOG` (
  `id_log` varchar(36) NOT NULL,
  `log_entry_date` timestamp NULL DEFAULT NULL,
  `log_logger` varchar(100) DEFAULT NULL,
  `log_level` varchar(100) DEFAULT NULL,
  `log_message` varchar(250) DEFAULT NULL,
  `log_exception` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id_log`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_OFFICE`
--
CREATE TABLE `APC_OFFICE` (
  `id` char(36) NOT NULL,
  `office_name` varchar(100) NOT NULL,
  `address` varchar(250) DEFAULT NULL,
  `office_status` enum('ENEBLED','DISABLED','DELETED') NOT NULL,
  `created_by` char(36) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_office_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_office_uk` UNIQUE KEY (`office_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_ROLE`
--
CREATE TABLE `APC_ROLE` (
  `id` char(36) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `active_status` enum('ENEBLED','DISABLED') NOT NULL DEFAULT 'ENEBLED',
  `created_by` char(36) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_role_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_role_uk` UNIQUE KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_ROUTE`
--
CREATE TABLE `APC_ROUTE`(
    `id` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `route_name` varchar(25) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_route_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_route_uk` UNIQUE KEY (`id_office`,`route_name`),
    CONSTRAINT `apc_route_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_BONUS`
--
-- Dar de alta los bonos activos.
-- 
CREATE TABLE `APC_BONUS`(
    `id` char(36) NOT NULL,
    `name` varchar(100) NOT NULL,
    `loan_bonus` numeric(8,2) NOT NULL,
    `mora_bonus` numeric(8,2) NOT NULL,
    `new_customer_bonus` numeric(8,2) NOT NULL,
    `administrative` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `id_office` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_bono_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_bono_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_HUMAN_RESOURCES`
--
CREATE TABLE `APC_HUMAN_RESOURCE` (
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
  `id_bonus` char(36) DEFAULT NULL,
  `payment` numeric(8,2) DEFAULT NULL,
  `imss` numeric(8,2) DEFAULT NULL,
  `balance` decimal(8,2) NOT NULL,
  `employee_saving` DECIMAL(8,2) DEFAULT 0.00,
  `created_by` char(36) DEFAULT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `person_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_human_resource_to_apc_role_fk` 
        FOREIGN KEY (`id_role`) REFERENCES `APC_ROLE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_human_resource_to_apc_bonus_fk` 
        FOREIGN KEY (`id_bonus`) REFERENCES `APC_BONUS`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_HUMAN_RESOURCE_HAS_ROUTE`
--
CREATE TABLE `APC_HUMAN_RESOURCE_HAS_ROUTE`( 
    `id_human_resource` char(36) NOT NULL,
    `id_route` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_human_resource_has_route_pk` PRIMARY KEY (`id_human_resource`, `id_route`),
    CONSTRAINT `apc_human_resource_has_route_to_apc_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_human_resource_has_route_to_apc_office_fk` 
        FOREIGN KEY (`id_route`) REFERENCES `APC_ROUTE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_PEOPLE`
--
CREATE TABLE `APC_PEOPLE`( 
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
    `people_type` ENUM('CUSTOMER', 'ENDORSEMENT', 'BOTH') NOT NULL DEFAULT 'CUSTOMER',
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `id_office` char(36) NOT NULL,
    `id_route` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_people_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_people_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_people_to_apc_route_fk` 
        FOREIGN KEY (`id_route`) REFERENCES `APC_ROUTE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_LOAN_TYPE`
--
CREATE TABLE `APC_LOAN_TYPE`( 
    `id` char(36) NOT NULL,
    `loan_type_name` varchar(50) NOT NULL,
    `total_days` smallint NOT NULL DEFAULT 22,
    `loan_fee` numeric(8,2) NOT NULL,-- Multa
    `opening_fee` int NOT NULL, -- Comisión 
    `payment` numeric(8,2) NOT NULL,-- Monte del prestamo
    `payment_daily` numeric(8,2) NOT NULL,-- 60 x c/1000
    `payment_total` numeric(8,2) NOT NULL, -- Prestamo más intereses
    `payment_monday` ENUM('MONDAY'),
    `payment_tuesday` ENUM('TUESDAY'),
    `payment_wednesday` ENUM('WEDNESDAY'),
    `payment_thursday` ENUM('THURSDAY'),
    `payment_friday` ENUM('FRIDAY'),
    `payment_saturday` ENUM('SATURDAY'),
    `payment_sunday` ENUM('SUNDAY'),
    `convenio` ENUM('ENEBLED','DISABLED') DEFAULT 'DISABLED'
    `id_office` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_loan_type_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_loan_type_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_LOAN`
--
CREATE TABLE `APC_LOAN`( 
    `id` char(36) NOT NULL,
    `id_loan_type` char(36) NOT NULL,
    `id_customer` char(36) NOT NULL,
    `id_endorsement` char(36) NOT NULL,
    `id_route` char(36) NOT NULL,
    `loan_status` ENUM('PENDING','FINISH','BLACK_LIST','APPROVED','REJECTED','PENDING_RENOVATION','TO_DELIVERY','DELETED') NOT NULL DEFAULT 'PENDING',
    `new_customer` ENUM('ENEBLED', 'DISABLED') DEFAULT 'DISABLED',
    `amount_paid` numeric(8,2) NOT NULL,
    `amount_to_pay` numeric(8,2) NOT NULL,
    `last_reference_number` smallint NOT NULL,
    `comments` varchar(200),
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36),
    `last_updated_on` datetime,
    CONSTRAINT `apc_loan_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_loan_to_apc_loan_type_fk` 
        FOREIGN KEY (`id_loan_type`) REFERENCES `APC_LOAN_TYPE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_loan_to_apc_person_as_customer_fk` 
        FOREIGN KEY (`id_customer`) REFERENCES `APC_PEOPLE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_loan_to_apc_person_as_endorsement_fk` 
        FOREIGN KEY (`id_endorsement`) REFERENCES `APC_PEOPLE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_loan_to_apc_route_fk` 
        FOREIGN KEY (`id_route`) REFERENCES `APC_ROUTE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_HUMAN_RESOURCE_BY_OFFICE`
--
CREATE TABLE `APC_HUMAN_RESOURCE_BY_OFFICE`( 
    `id` char(36) NOT NULL,
    `id_human_resource` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `application_owner` ENUM('APP_OWNER', 'APP_USER') NOT NULL DEFAULT 'APP_USER',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_human_resource_by_office_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_human_resource_by_office_uk` UNIQUE KEY (`id_human_resource`, `id_office`),
    CONSTRAINT `apc_human_resource_by_office_to_apc_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_human_resource_by_office_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_USER`
--
CREATE TABLE `APC_USER`(
    `id` char(36) NOT NULL,
    `id_human_resource` char(36) NOT NULL,
    `user_name` varchar(100) NOT NULL,
    `pwd` varchar(100)NOT NULL,
    `user_type` ENUM('WEB', 'MOBILE', 'BOTH') NOT NULL,
    `user_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `application_owner` ENUM('APP_OWNER', 'APP_USER') NOT NULL DEFAULT 'APP_USER',
    `certifier` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'DISABLED',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_user_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_user_by_office_to_apc_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_LOAN_DETAIL`
--
CREATE TABLE `APC_LOAN_DETAIL`( 
    `id` char(36) NOT NULL,
    `id_loan` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `people_type` ENUM('CUSTOMER', 'ENDORSEMENT') NOT NULL DEFAULT 'CUSTOMER',
    `payment_amount` numeric(8,2) NOT NULL,
    `reference_number` smallint NOT NULL,
    `loan_details_type` ENUM('CREDIT_PAYMENT','DEBIT_PAYMENT','PAYMENT','FEE','RENOVATION_PAYMENT','TRANSFER') NOT NULL,
    `loan_comments` varchar(150),
    `fee_status` enum('TO_PAY','PAID') DEFAULT 'TO_PAY',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_loan_details_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_loan_details_uk` UNIQUE KEY (`id`,`reference_number`),
    CONSTRAINT `apc_loan_details_to_apc_loan_fk` 
        FOREIGN KEY (`id_loan`) REFERENCES `APC_LOAN`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_loan_details_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_LOAN_FEE_NOTIFICATION`
--
CREATE TABLE `APC_LOAN_FEE_NOTIFICATION`( 
    `id` char(36) NOT NULL,
    `id_loan` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `notification_number` smallint NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_loan_notification_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_loan_notification_to_apc_loan_fk` 
        FOREIGN KEY (`id_loan`) REFERENCES `APC_LOAN`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_loan_notification_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_LOAN_BY_USER`
--
CREATE TABLE `APC_LOAN_BY_USER`(
    `id_loan` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `loan_by_user_status` enum('PENDING','FINISH','BLACK_LIST','APPROVED','REJECTED','PENDING_RENOVATION','TO_DELIVERY','DELETED') NOT NULL DEFAULT 'PENDING',
    `comments` varchar(150) DEFAULT NULL,
    `owner_loan` enum('CURRENT_OWNER','OLD_OWNER') NOT NULL,
    `order_in_list` smallint(6) DEFAULT '0',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_loan_by_user_pk` PRIMARY KEY (`id_loan`, `id_user`),
    CONSTRAINT `apc_loan_by_user_to_apc_loan_fk` 
        FOREIGN KEY (`id_loan`) REFERENCES `APC_LOAN`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_loan_by_user_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_LOAN_BY_RENOVATION`
--
CREATE TABLE `APC_LOAN_BY_RENOVATION`(
    `id_loan_old` char(36) NOT NULL,
    `id_loan_new` char(36) NOT NULL,
    `loan_by_renovation_status` ENUM('PENDING', 'APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
    `comments` varchar(150),
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_loan_by_renovation_pk` PRIMARY KEY (`id_loan_old`, `id_loan_new`),
    CONSTRAINT `apc_loan_by_renovation_old_to_apc_loan_fk` 
        FOREIGN KEY (`id_loan_old`) REFERENCES `APC_LOAN`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_loan_by_renovation_new_to_apc_user_fk` 
        FOREIGN KEY (`id_loan_new`) REFERENCES `APC_LOAN`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_USER_BY_OFFICE`
--
CREATE TABLE `APC_USER_BY_OFFICE`( 
    `id` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `user_by_office_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `application_owner` ENUM('APP_OWNER', 'APP_USER')NOT NULL  DEFAULT 'APP_USER',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_user_by_office_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_user_by_office_uk` UNIQUE KEY (`id_user`, `id_office`),
    CONSTRAINT `apc_user_by_office_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_user_by_office_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_PERMISSION`
--
CREATE TABLE `APC_PERMISSION` (
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
    CONSTRAINT `apc_permission_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_permission_uk` UNIQUE KEY (`permission`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_USER_BY_OFFICE_HAS_PERMISSION`
--
CREATE TABLE `APC_USER_BY_OFFICE_HAS_PERMISSION` (
    `id_user_by_office` char(36) NOT NULL,
    `id_permission` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_user_by_office_has_permission_pk` PRIMARY KEY (`id_user_by_office`,`id_permission`) ,
    CONSTRAINT `apc_user_by_office_has_permission_to_apc_user_by_office_fk` 
        FOREIGN KEY (`id_user_by_office`) REFERENCES `APC_USER_BY_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_user_by_office_has_permission_to_apc_permission_fk` 
        FOREIGN KEY (`id_permission`) REFERENCES `APC_PERMISSION`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_USER_MOBILE_PREFERECE`
--
CREATE TABLE `APC_USER_MOBILE_PREFERECE`( 
    `id` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `preference_name` varchar(25) NOT NULL,
    `preference_value` varchar(25) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_user_mobile_preference_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_user_mobile_preference_uk` UNIQUE KEY (`id_user`,`preference_name`),
    CONSTRAINT `apc_user_mobile_preference_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_TRANSFER`
--
CREATE TABLE `APC_TRANSFER`(
    `id` char(36) NOT NULL,
    `id_user_transmitter` char(36) NOT NULL,
    `id_user_receiver` char(36) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `action_status` ENUM('PENDING', 'APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
    `amount_to_transfer` numeric(8,2) NOT NULL,
    `id_office` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_transfer_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_transfer_user_transmitter_to_apc_user_fk` 
        FOREIGN KEY (`id_user_transmitter`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_transfer_user_receiver_to_apc_user_fk` 
        FOREIGN KEY (`id_user_receiver`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_transfer_by_office_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_MONEY_DAILY`
--
CREATE TABLE `APC_MONEY_DAILY`(
    `id` char(36) NOT NULL,
    `money_daily_date` datetime DEFAULT CURRENT_TIMESTAMP,
    `amount` numeric(8,2) NOT NULL,
    `id_user` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_money_daily_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_money_daily_by_id_user_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_money_daily_by_id_office_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de la tabla `APC_DELIVERY`
--
CREATE TABLE `APC_DELIVERY`(
    `id` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `id_loan` char(36) NOT NULL,
    `amount` numeric(8,2) NOT NULL,
    `comission` comission enum('INCLUDED','EXCLUDED') DEFAULT 'INCLUDED',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_delivery_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_delivery_by_id_user_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_delivery_to_apc_loan_fk` 
        FOREIGN KEY (`id_loan`) REFERENCES `APC_LOAN`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- -------------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_CLOSING_DAY`
--
CREATE TABLE `APC_CLOSING_DAY`(
    `id` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `amount_paid` numeric(8,2) NOT NULL,
    `amount_expected` numeric(8,2) NOT NULL,
    `comments` varchar(250),
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_closing_day_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_closing_day_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_closing_day_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- -------------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_OTHER_EXPENSE`
--
CREATE TABLE `APC_OTHER_EXPENSE`(
    `id` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `expense` numeric(8,2) NOT NULL,
    `description` varchar(200) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_other_expense_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_other_expense_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_other_expense_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_GOAL`
--
-- Dar de alta las metas de los trabajadores.
-- 
CREATE TABLE `APC_GOAL`(
    `id` char(36) NOT NULL,
    `start_date` date NOT NULL,
    `end_date` date NOT NULL,
    `amount` numeric(8,2) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `id_office` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_goal_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_goal_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_ADVANCE`
--
-- Adelanto de nomina.
-- 
CREATE TABLE `APC_ADVANCE`(
    `id` char(36) NOT NULL,
    `id_human_resource` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `amount` numeric(8,2) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_advance_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_advance_to_apc_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_advance_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_EXPENSE_COMPANY`
--
-- Gastos de la compañia.
-- 
CREATE TABLE `APC_EXPENSE_COMPANY`(
    `id` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `amount` numeric(8,2) NOT NULL,
    `description` varchar(200),
    `expense_company_type` ENUM('PAYMENT_IN', 'PAYMENT_OUT') NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_expense_company_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_expense_company_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_STABLE_GENERAL_BOX`
--
-- Totales de la caja de APC.
-- 
CREATE TABLE `APC_STABLE_GENERAL_BOX`(
  `id` char(36) NOT NULL,
  `total_general_box` decimal(8,2) NOT NULL,
  `total_envelope` decimal(8,2) NOT NULL,
  `total_bank_note` decimal(8,2) NOT NULL,
  `total_coin` decimal(8,2) NOT NULL,
  `total_stable` decimal(8,2) NOT NULL,
  `active_status` enum('ENEBLED','DISABLED') NOT NULL DEFAULT 'ENEBLED',
  `description` varchar(200) DEFAULT NULL,
  `id_office` char(36) NOT NULL,
  `created_by` char(36) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_stable_general_box_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_stable_general_box_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_STABLE_GENERAL_BOX`
--
-- Totales de la caja de APC.
-- 
CREATE TABLE `APC_TOTAL_EXPECTED_PAYMENT_DAILY_BY_USER`(
  `id` char(36) NOT NULL,
  `total_expected` decimal(8,2) NOT NULL,
  `active_status` enum('ENEBLED','DISABLED') NOT NULL DEFAULT 'ENEBLED',
  `id_office` char(36) NOT NULL,
  `id_user` char(36) NOT NULL,
  `created_by` char(36) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `total_expected_payment` decimal(8,2) DEFAULT NULL,
    CONSTRAINT `apc_total_expected_payment_daily_by_user_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_total_expected_payment_daily_by_user_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_total_expected_payment_daily_by_user_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `ARREBOL_TEST`
--
-- Solo para pruebas exclusivas.
-- 
CREATE TABLE `ARREBOL_TEST`(
    `id` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_PAYROLL`
-- 
CREATE TABLE `APC_PAYROLL`(
  `id` char(36) NOT NULL,
  `active_status` enum('ENEBLED','DISABLED') NOT NULL DEFAULT 'ENEBLED',
  `id_office` char(36) NOT NULL,
  `id_human_resource` char(36) NOT NULL,
  `salary` decimal(8,2) NOT NULL,
  `imss` decimal(8,2) NOT NULL,
  `advance` decimal(8,2) NOT NULL,
  `total_bonus_new_customer` decimal(8,2) NOT NULL,
  `total_bonus_colocation` decimal(8,2) NOT NULL,
  `total_bonus_mora` decimal(8,2) NOT NULL,
  `discounts` decimal(8,2) NOT NULL,
  `increases` decimal(8,2) NOT NULL,
  `total_payment` decimal(8,2) NOT NULL,
  `total_days_salary` smallint(6) NOT NULL DEFAULT '5',
  `total_days_bonus` smallint(6) NOT NULL DEFAULT '5',
  `comments_discounts` varchar(200) DEFAULT NULL,
  `comments_increases` varchar(200) DEFAULT NULL,
  `observation` varchar(200) DEFAULT NULL,
  `boxed` decimal(8,2) NOT NULL,
  `saving` decimal(8,2) NOT NULL,
  `payment_to_debt` decimal(8,2) NOT NULL,
  `created_by` char(36) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_payroll_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_payroll_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `apc_payroll_to_apc_human_resource_fk` 
        FOREIGN KEY (`id_human_resource`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_CLOSING_DAY_DETAIL`
-- 
CREATE TABLE `APC_CLOSING_DAY_DETAIL`(
  `id` char(36) NOT NULL,
  `id_closing_day` char(36) NOT NULL,
  `comments` varchar(200) NOT NULL,
  `amount` decimal(8,2) NOT NULL,
  `type` varchar(200) NOT NULL,
  `dateDetail` datetime DEFAULT NULL,
  `created_by` char(36) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_closing_day_detail_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_closing_day_detail_to_apc_closing_day_fk` 
        FOREIGN KEY (`id_closing_day`) REFERENCES `APC_CLOSING_DAY`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_STABLE_SMALL_BOX`
--
-- Caja chica.
-- 
CREATE TABLE `APC_STABLE_SMALL_BOX`(
    `id` char(36) NOT NULL,
    `total_small_box` numeric(8,2) NOT NULL,
    `total_envelope` numeric(8,2) NOT NULL,
    `total_bank_note` numeric(8,2) NOT NULL,
    `total_coin` numeric(8,2) NOT NULL,
    `total_stable` numeric(8,2) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `description` varchar(200),
    `id_office` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_stable_small_box_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_stable_small_box_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_BITACORA`
--
-- Bitacora de APC.
-- 
CREATE TABLE `APC_BITACORA`(
    `id` char(36) NOT NULL,
    `id_office` char(36) NOT NULL,
    `comments_user` varchar(300) NOT NULL,
    `action` varchar(50) NOT NULL,
    `description` varchar(300) NOT NULL,
    `name_user` varchar(200) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_bitacora_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_bitacora_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_LOAN_EMPLOYEE`
-- 
-- Crear tabla para préstamos de empleados
--
CREATE TABLE `APC_LOAN_EMPLOYEE` (
	`id` CHAR(36) NOT NULL,
	`id_employee` CHAR(36) NOT NULL, 
    `amount_loan` DECIMAL(8,2),
    `amount_to_pay` DECIMAL(8,2),
    `balance` DECIMAL(8,2),
    `loan_employee_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `total_amount_to_pay` DECIMAL(8,2),
    `created_by` CHAR(36) NOT NULL,
    `created_on` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` CHAR(36) DEFAULT NULL,
    `last_updated_on` DATETIME DEFAULT NULL,
    
     CONSTRAINT `apc_loan_employee_pk` PRIMARY KEY (id),
     CONSTRAINT `apc_loan_to_apc_person_as_employee_fk` 
		FOREIGN KEY (`id_employee`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
             ON UPDATE CASCADE
             ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_LOAN_EMPLOYEE_DETAIL`
-- 
-- Crear tabla para detalles (abonos)
--
CREATE TABLE `APC_LOAN_EMPLOYEE_DETAIL` (
	`id` char(36) NOT NULL,
	`id_loan` char(36) NOT NULL,
	`id_user` char(36) NOT NULL,
	`payment_amount` DECIMAL(8,2),
    `reference_number` SMALLINT NOT NULL,
    `loan_employee_detail_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
	`created_by` CHAR(36) NOT NULL,
	`created_on` DATETIME DEFAULT CURRENT_TIMESTAMP,
	
    CONSTRAINT `apc_loan_employee_detail_pk` PRIMARY KEY (id),
    CONSTRAINT `apc_loan_employee_details_uk` UNIQUE KEY (`id`,`reference_number`),
	CONSTRAINT `apc_loan_details_to_apc_loan_employee_fk` 
		FOREIGN KEY (`id_loan`) REFERENCES `APC_LOAN_EMPLOYEE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
	CONSTRAINT `apc_loan_employee_details_to_apc_user_fk`
		FOREIGN KEY (`id_user`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_GASOLINE`
--
CREATE TABLE `APC_GASOLINE` (
  `id` char(36) NOT NULL,
  `id_user` char(36) NOT NULL,
  `id_office` char(36) NOT NULL,
  `id_route` char(36) NOT NULL,
  `quantity` DOUBLE DEFAULT 0.0,
  `km_old` DOUBLE DEFAULT 0.0,
  `km_new` DOUBLE DEFAULT 0.0,
  `total` DOUBLE DEFAULT 0.0,
  `status` ENUM('ENABLED', 'DISABLED', 'DELETED') NOT NULL,
  `description` VARCHAR(500),
  `created_by` char(36) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_updated_by` char(36) DEFAULT NULL,
  `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `apc_gasoline_to_apc_user_fk` 
        FOREIGN KEY (`id_user`) REFERENCES `APC_USER`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
  CONSTRAINT `apc_gasoline_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
  CONSTRAINT `apc_gasoline_to_apc_route_fk` 
        FOREIGN KEY (`id_route`) REFERENCES `APC_ROUTE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `APC_EMPLOYEE_SAVING` (
	`id` char(36) NOT NULL,
	-- `id_payroll` char(36) NOT NULL,
	`id_user` char(36) NOT NULL,
	`employee_saving` DECIMAL(8,2) default 0,
	`created_by` CHAR(36) NOT NULL,
	`created_on` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` CHAR(36) DEFAULT NULL,
	`last_updated_on` DATETIME DEFAULT NULL,
	
    CONSTRAINT `apc_employee_saving_pk` PRIMARY KEY (id),
	-- CONSTRAINT `apc_employee_saving_to_apc_payroll_fk` 
		-- FOREIGN KEY (`id_payroll`) REFERENCES `APC_PAYROLL`(`id`)
           --  ON UPDATE CASCADE
            -- ON DELETE RESTRICT,
	CONSTRAINT `apc_employee_saving_to_apc_user_fk`
		FOREIGN KEY (`id_user`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_VEHICLES`
--
CREATE TABLE `APC_VEHICLES` (
    `id` CHAR(36) NOT NULL,
    `id_driver` CHAR(36) NOT NULL,
    `license_plate` VARCHAR(50),
    `economic_number` VARCHAR(50),
    `serial_number` VARCHAR(50),
    `engine_number` VARCHAR(50),
    `mileage` DECIMAL(8,2),
    `year` INTEGER,
    `insurance_name` VARCHAR(150),
    `insurance_number` VARCHAR(50),
    `coverage_type` VARCHAR(50),
    `model` VARCHAR(50),
    `colour` VARCHAR(50),
    `gps` BIT,
    `gps_number` VARCHAR(50),
    `comments` varchar(200),
    `vehicle_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `created_by` CHAR(36) NOT NULL,
    `created_on` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` CHAR(36) DEFAULT NULL,
    `last_updated_on` DATETIME DEFAULT NULL,
    
     CONSTRAINT `apc_vehicles_pk` PRIMARY KEY (id),
     CONSTRAINT `apc_vehicles_to_apc_person_as_employee_fk` 
		FOREIGN KEY (`id_driver`) REFERENCES `APC_HUMAN_RESOURCE`(`id`)
             ON UPDATE CASCADE
             ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- --------------------------------------------------------------------
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;