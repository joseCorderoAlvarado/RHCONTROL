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
CREATE TABLE `APC_ERROR_APP_LOG`(
    `id_log` varchar(36) primary key,
    `log_entry_date` timestamp,
    `log_logger` varchar(100),
    `log_level` varchar(100),
    `log_message` varchar(250),
    `log_exception` varchar(4000)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
USE `apo_pro_com_april_ten`;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_OFFICE`
--
CREATE TABLE `APC_OFFICE`(
    `id` char(36) NOT NULL,
    `office_name` varchar(100) NOT NULL,
    `address` varchar(250),
    `office_status` ENUM('ENEBLED', 'DISABLED', 'DELETED') NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `apc_office_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_office_uk` UNIQUE KEY (`office_name`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1; 
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_ROLE`
--
CREATE TABLE `APC_ROLE`(
    `id` char(36) NOT NULL,
    `role_name` varchar(100) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
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
    `loan_status` ENUM('PENDING', 'FINISH','BLACK_LIST', 'APPROVED','REJECTED', 'PENDING_RENOVATION', 'TO_DELIVERY') NOT NULL DEFAULT 'PENDING',
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
    `loan_details_type` ENUM('CREDIT_PAYMENT', 'DEBIT_PAYMENT', 'PAYMENT', 'FEE','RENOVATION_PAYMENT') NOT NULL,
    `loan_comments` varchar(150),
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
    `loan_by_user_status` ENUM('PENDING', 'FINISH','BLACK_LIST', 'APPROVED','REJECTED', 'PENDING_RENOVATION', 'TO_DELIVERY') NOT NULL DEFAULT 'PENDING',
    `comments` varchar(150),
    `owner_loan` ENUM('CURRENT_OWNER', 'OLD_OWNER') NOT NULL,
    `order_in_list` smallint DEFAULT 0,
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
    `total_general_box` numeric(8,2) NOT NULL,
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
    CONSTRAINT `apc_stable_general_box_pk` PRIMARY KEY (`id`),
    CONSTRAINT `apc_stable_general_box_to_apc_office_fk` 
        FOREIGN KEY (`id_office`) REFERENCES `APC_OFFICE`(`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 
-- Estructura para la tabla `APC_STABLE_GENERAL_BOX`
--
-- Totales de la caja de APC.
-- 
CREATE TABLE `APC_TOTAL_EXPECTED_PAYMENT_DAILY_BY_USER`(
    `id` char(36) NOT NULL,
    `total_expected` numeric(8,2) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `id_office` char(36) NOT NULL,
    `id_user` char(36) NOT NULL,
    `created_by` char(36) NOT NULL,
    `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
    `last_updated_by` char(36) DEFAULT NULL,
    `last_updated_on` datetime DEFAULT CURRENT_TIMESTAMP,
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
-- -------------------------------------------------------------
-- 
-- Estructura para la vista `APC_SECURITY_AUTHENTICATION` (USER)
--
CREATE OR REPLACE VIEW `APC_SECURITY_AUTHENTICATION`  AS
SELECT CONCAT(`usr`.`user_name`,`ubo`.`id_office`) AS `user_name`, `usr`.`pwd` AS `pwd`
FROM `APC_USER_BY_OFFICE` `ubo`
INNER JOIN `APC_USER` `usr` ON `ubo`.`id_user` = `usr`.`id`
INNER JOIN `APC_HUMAN_RESOURCE` `hr` ON `usr`.`id_human_resource` = `hr`.`id`
WHERE
`usr`.`user_status` = 'ENEBLED' AND
`usr`.`user_type` IN ('WEB', 'BOTH') AND
`ubo`.`user_by_office_status` = 'ENEBLED' AND 
`hr`.`human_resource_status` = 'ENEBLED';
-- ------------------------------------------------------------
-- 
-- Estructura para la vista `APC_SECURITY_AUTHORIZATION` (ROLE)
--
CREATE OR REPLACE VIEW `APC_SECURITY_AUTHORIZATION` AS
SELECT CONCAT(`usr`.`user_name`,`ubo`.`id_office`) AS `user_name`, `perm`.`permission` AS `permission`
FROM `APC_PERMISSION` `perm`
INNER JOIN `APC_USER_BY_OFFICE_HAS_PERMISSION` `ubohp` ON `perm`.`id` = `ubohp`.`id_permission`
INNER JOIN `APC_USER_BY_OFFICE` `ubo` ON `ubohp`.`id_user_by_office` = `ubo`.`id`
INNER JOIN `APC_USER` `usr` ON `ubo`.`id_user` = `usr`.`id`
INNER JOIN `APC_HUMAN_RESOURCE` `hr` ON `usr`.`id_human_resource` = `hr`.`id`
WHERE 
`perm`.`permission_status` = 'ENEBLED' AND
`ubo`.`user_by_office_status` = 'ENEBLED' AND
`usr`.`user_status` = 'ENEBLED' AND 
`usr`.`user_type` IN ('WEB', 'BOTH') AND
`hr`.`human_resource_status` = 'ENEBLED'
ORDER BY `user_name`;
-- -------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_SECURITY_AUTHENTICATION_MOBILE` (USER)
--
CREATE OR REPLACE VIEW `APC_SECURITY_AUTHENTICATION_MOBILE`  AS
SELECT 
`usr`.`id` AS `id`,
`usr`.`user_name` AS `user_name`,
`usr`.`pwd` AS `pwd`,
`hr`.`avatar` AS `avatar`,
`ubo`.`id_office` AS `id_office`,
`hrhr`.`id_route` AS `id_route`,
`usr`.`certifier` AS `certifier`
FROM `APC_USER_BY_OFFICE` `ubo`
INNER JOIN `APC_USER` `usr` ON `ubo`.`id_user` = `usr`.`id`
INNER JOIN `APC_HUMAN_RESOURCE` `hr` ON `usr`.`id_human_resource` = `hr`.`id`
INNER JOIN `APC_HUMAN_RESOURCE_HAS_ROUTE` `hrhr` ON `hr`.`id` = `hrhr`.`id_human_resource`
WHERE
`usr`.`user_status` = 'ENEBLED' AND
`usr`.`user_type` IN ('MOBILE', 'BOTH') AND
`ubo`.`user_by_office_status` = 'ENEBLED' AND 
`hr`.`human_resource_status` = 'ENEBLED';
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_LOAN_BY_USER_VIEW` (USER)
-- Se utiliza para que un Asesor cargue todos los pretamos que tiene
-- que cobrar en el día actual.
--
CREATE OR REPLACE VIEW `APC_LOAN_BY_USER_VIEW` AS
SELECT
    CONCAT(l.id, u.id) AS id,
    u.id AS user_id,
    CONCAT(
        CASE
            WHEN cstmr.first_name IS NOT NULL AND cstmr.first_name != ''
            THEN CONCAT(SUBSTR(UPPER(cstmr.first_name), 1, 1),SUBSTR(LOWER(cstmr.first_name), 2), ' ')
            ELSE ''
        END,
        CASE
            WHEN cstmr.second_name IS NOT NULL AND cstmr.second_name != ''
            THEN CONCAT(SUBSTR(UPPER(cstmr.second_name), 1, 1),SUBSTR(LOWER(cstmr.second_name), 2), ' ')
            ELSE ''
        END,
        CASE
            WHEN cstmr.last_name IS NOT NULL AND cstmr.last_name != ''
            THEN CONCAT(SUBSTR(UPPER(cstmr.last_name), 1, 1),SUBSTR(LOWER(cstmr.last_name), 2))
            ELSE ''
        END
    ) AS customer_name,
    (CASE 
        WHEN cstmr.address_home IS NULL THEN ''
        ELSE cstmr.address_home
    END) AS customer_address_home,
    (CASE 
        WHEN cstmr.address_business IS NULL THEN ''
        ELSE cstmr.address_business
    END) AS customer_address_business,
    (CASE 
        WHEN cstmr.company_name IS NULL THEN ''
        ELSE cstmr.company_name
    END) AS company_name,
    (CASE 
        WHEN cstmr.thumbnail IS NULL THEN ''
        ELSE cstmr.thumbnail
    END) AS customer_thumbnail,
    CONCAT(
        CASE
            WHEN ndrsmnt.first_name IS NOT NULL AND ndrsmnt.first_name != ''
            THEN CONCAT(SUBSTR(UPPER(ndrsmnt.first_name), 1, 1),SUBSTR(LOWER(ndrsmnt.first_name), 2), ' ')
            ELSE ''
        END,
        CASE
            WHEN ndrsmnt.second_name IS NOT NULL AND ndrsmnt.second_name != ''
            THEN CONCAT(SUBSTR(UPPER(ndrsmnt.second_name), 1, 1),SUBSTR(LOWER(ndrsmnt.second_name), 2), ' ')
            ELSE ''
        END,
        CASE
            WHEN ndrsmnt.last_name IS NOT NULL AND ndrsmnt.last_name != ''
            THEN CONCAT(SUBSTR(UPPER(ndrsmnt.last_name), 1, 1),SUBSTR(LOWER(ndrsmnt.last_name), 2))
            ELSE ''
        END
    )AS endorsement_name,
    (CASE 
        WHEN ndrsmnt.address_home IS NULL THEN ''
        ELSE ndrsmnt.address_home 
    END) AS endorsement_address_home,
    (CASE 
        WHEN ndrsmnt.thumbnail IS NULL THEN ''
        ELSE ndrsmnt.thumbnail 
    END) AS endorsement_thumbnail,
    (CASE 
        WHEN ndrsmnt.phone_home IS NULL THEN ''
        ELSE ndrsmnt.phone_home 
    END) AS endorsement_phone_home,
    IF((l.amount_to_pay - l.amount_paid >= lt.payment_daily), lt.payment_daily, l.amount_to_pay - l.amount_paid)  AS payment_daily,
    lt.loan_fee,
    lbu.order_in_list,
    (SELECT count(notification_number) FROM APC_LOAN_FEE_NOTIFICATION WHERE id_loan = l.id) AS notification_number,
IF(
    (
        l.amount_paid >= (SELECT FLOOR(lt.payment_total * .6364) FROM DUAL)
    ), 
        CASE
            WHEN 
                (SELECT count(notification_number) as total 
                FROM APC_LOAN_FEE_NOTIFICATION
                WHERE id_loan = l.id
                ) < 4   
            THEN 'ENEBLED'
            WHEN 
                (SELECT count(notification_number) as total 
                FROM APC_LOAN_FEE_NOTIFICATION
                WHERE id_loan = l.id
                ) BETWEEN 4 AND 5   
                AND
                lt.payment > 1000
            THEN 'ENEBLED'
            ELSE 'DISABLED'
        END
    ,'DISABLED'
) as renovation,
(SELECT amount_to_pay - amount_paid FROM APC_LOAN WHERE id = l.id) AS max_amount_to_pay
FROM APC_LOAN_BY_USER lbu
INNER JOIN APC_LOAN l ON lbu.id_loan = l.id
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN APC_PEOPLE cstmr ON l.id_customer = cstmr.id
INNER JOIN APC_PEOPLE ndrsmnt ON l.id_endorsement = ndrsmnt.id
INNER JOIN APC_USER u ON lbu.id_user = u.id
WHERE 
    l.loan_status = 'APPROVED' AND
    l.id = (
        CASE
            -- WHEN IS NEW AND update created_on less equals to currentdate
            WHEN ( 
                (SELECT count(id) FROM APC_LOAN_DETAIL WHERE id_loan = l.id ) = 0 AND
                DATE(l.last_updated_on) < curdate()
            ) THEN l.id
            -- WHEN LOAN HAS PAYMENTS
            WHEN (
                (
                SELECT count(id) 
                FROM APC_LOAN_DETAIL 
                WHERE 
                    id_loan = l.id AND 
                    reference_number = l.last_reference_number AND 
                DATE(created_on) < curdate()
                    ) > 0 AND
                DATE(l.last_updated_on) < curdate()
            ) THEN l.id
            ELSE ''
        END
    ) AND
    lbu.owner_loan = 'CURRENT_OWNER';
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_LOAN_BY_USER_ORDER_PREFERENCE_VIEW` (USER)
-- Se utiliza para que un Asesor cargue todos los pretamos que tiene
-- que cobrar en el día actual y los pueda order por order de preferencia
-- para que pueda programar suta a su gusto.
--
CREATE OR REPLACE VIEW `APC_LOAN_BY_USER_ORDER_PREFERENCE_VIEW` AS
SELECT
    CONCAT(l.id, u.id) AS id,
    u.id AS user_id,
    CONCAT(cstmr.first_name,' ',IF(ISNULL(cstmr.second_name) ,'', CONCAT(cstmr.second_name, ' ')),cstmr.last_name,' ', cstmr.middle_name) AS customer_name, 
    cstmr.address_home AS customer_address_home,
    cstmr.address_business AS customer_address_business,
    lbu.order_in_list
FROM APC_LOAN_BY_USER lbu
INNER JOIN APC_LOAN l ON lbu.id_loan = l.id
INNER JOIN APC_PEOPLE cstmr ON l.id_customer = cstmr.id
INNER JOIN APC_USER u ON lbu.id_user = u.id
WHERE l.loan_status = 'APPROVED';
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_PERSON_SEARCH_VIEW`
-- Se utiliza para realizar busqueda por nombre.
--
CREATE OR REPLACE VIEW `APC_PERSON_SEARCH_VIEW` AS
SELECT 
    id,
    CONCAT(
		CASE
			WHEN first_name IS NOT NULL AND first_name != ''
			THEN CONCAT(SUBSTR(UPPER(first_name), 1, 1),SUBSTR(LOWER(first_name), 2))
			ELSE ''
		END,
        CASE
			WHEN second_name IS NOT NULL AND second_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(second_name), 1, 1),SUBSTR(LOWER(second_name), 2))
			ELSE ''
		END,
        CASE
			WHEN last_name IS NOT NULL AND last_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(last_name), 1, 1),SUBSTR(LOWER(last_name), 2))
			ELSE ''
		END,
        CASE
			WHEN middle_name IS NOT NULL AND middle_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(middle_name), 1, 1),SUBSTR(LOWER(middle_name), 2))
			ELSE ''
		END
    ) AS person_search
FROM APC_PEOPLE 
WHERE active_status = 'ENEBLED'
ORDER BY person_search;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_PERSON_SEARCH_DETAIL_VIEW`
-- Busca los detalles de una persona en el sistema.
CREATE OR REPLACE VIEW `APC_PERSON_SEARCH_DETAIL_VIEW` AS
SELECT 
    p.id AS id,
    CONCAT(
		CASE
			WHEN p.first_name IS NOT NULL AND p.first_name != ''
			THEN CONCAT(SUBSTR(UPPER(p.first_name), 1, 1),SUBSTR(LOWER(p.first_name), 2), ' ')
			ELSE ''
		END,
        CASE
			WHEN p.second_name IS NOT NULL AND p.second_name != ''
			THEN CONCAT(SUBSTR(UPPER(p.second_name), 1, 1),SUBSTR(LOWER(p.second_name), 2), ' ')
			ELSE ''
		END,
        CASE
			WHEN p.last_name IS NOT NULL AND p.last_name != ''
			THEN CONCAT(SUBSTR(UPPER(p.last_name), 1, 1),SUBSTR(LOWER(p.last_name), 2), ' ')
			ELSE ''
		END,
        CASE
			WHEN p.middle_name IS NOT NULL AND p.middle_name != ''
			THEN CONCAT(SUBSTR(UPPER(p.middle_name), 1, 1),SUBSTR(LOWER(p.middle_name), 2))
			ELSE ''
		END
    ) AS person_search,
    p.thumbnail,
    CASE
        WHEN 
            0 < (
            SELECT COUNT(ID)
            FROM APC_LOAN
            WHERE 
                id_customer = p.id AND
                loan_status = 'APPROVED')
        THEN 
            'ENEBLED'
        WHEN 
            0 < (
            SELECT COUNT(ID)
            FROM APC_LOAN
            WHERE 
                id_endorsement = p.id AND
                loan_status = 'APPROVED')
        THEN 
            'ENEBLED'
        ELSE
            'DISABLED'
    END  AS loanStatus
FROM APC_PEOPLE p;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_PERSON_SEARCH_HISTORICAL_DETAILS_VIEW`
-- Busca los detalles historicos de una persona en el sistema móvil.
CREATE OR REPLACE VIEW `APC_PERSON_SEARCH_HISTORICAL_DETAILS_VIEW` AS
SELECT
    l.id,
    p.id AS id_person_search,
    DATE_FORMAT(l.created_on,'%d-%m-%Y') AS format_date,
    CASE
		WHEN 0 < (SELECT COUNT(ID) FROM APC_LOAN WHERE id_customer = p.id)
        THEN 'Cliente'
        WHEN 0 < (SELECT COUNT(ID) FROM APC_LOAN WHERE id_endorsement = p.id)
        THEN 'Aval'
        ELSE
        'Indeterminado'
    END AS person_type,
    CASE
		WHEN 0 < (SELECT COUNT(ID) FROM APC_LOAN WHERE id_customer = p.id)
        THEN 
			(SELECT CONCAT(in_p.first_name,' ', in_p.last_name)
            FROM APC_LOAN in_l
            INNER JOIN APC_PEOPLE in_p ON in_l.id_endorsement = in_p.id
            WHERE in_l.id = l.id)
        WHEN 0 < (SELECT COUNT(ID) FROM APC_LOAN WHERE id_endorsement = p.id)
        THEN (SELECT CONCAT(in_p.first_name,' ', in_p.last_name)
            FROM APC_LOAN in_l
            INNER JOIN APC_PEOPLE in_p ON in_l.id_customer = in_p.id
            WHERE in_l.id = l.id)
        ELSE
        'Indeterminado'
    END AS relationship,
    lt.payment AS loan,
    CASE 
		WHEN l.loan_status = 'APPROVED'
        THEN CONCAT(l.last_reference_number, ' de 22')
        ELSE ''
    END AS payment_number,
    CASE 
		WHEN l.loan_status = 'APPROVED'
        THEN (l.amount_to_pay - l.amount_paid)
        ELSE 0
    END AS amount_to_pay,
    (SELECT COUNT(id) FROM APC_LOAN_FEE_NOTIFICATION WHERE id_loan = l.id) AS total_fees,
    CASE 
        WHEN l.loan_status = 'PENDING'
        THEN 'Pendiente'
        WHEN l.loan_status = 'FINISH'
        THEN 'Terminado'
        WHEN l.loan_status = 'BLACK_LIST'
        THEN 'Lista negra'
        WHEN l.loan_status = 'APPROVED'
        THEN 'Activo'
        WHEN l.loan_status = 'REJECTED'
        THEN 'Rechazado'
        WHEN l.loan_status = 'PENDING_RENOVATION'
        THEN 'Pendiente renovación'
        WHEN l.loan_status = 'TO_DELIVERY'
        THEN 'Por recibir'
    END AS loan_status
FROM APC_LOAN l
INNER JOIN APC_PEOPLE p ON (l.id_customer = p.id or l.id_endorsement = p.id)
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
ORDER BY l.created_on DESC;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_AVAILABLE_CUSTOMERS_VIEW`
--
-- Sirve para cargar todos los posibles clientes de una oficina 
-- que puedan solicitar un prestamo nuevo.
-- 
CREATE OR REPLACE VIEW `APC_AVAILABLE_CUSTOMERS_VIEW` AS
SELECT 
    p.id,
    CONCAT(p.first_name,' ', IF(ISNULL(p.second_name) ,'', CONCAT(p.second_name,' ')) ,p.last_name, ' ', p.middle_name) AS available_person,
    CASE
        WHEN p.people_type = 'BOTH'
        THEN 
            CASE
                WHEN 0 < (SELECT COUNT(id) FROM APC_LOAN WHERE id_customer = p.id)
                THEN (SELECT id_endorsement FROM APC_LOAN WHERE id_customer = p.id ORDER BY created_on DESC LIMIT 1 )
                ELSE  ''
            END
        ELSE ''
    END as cross_signature
FROM APC_PEOPLE p
WHERE 
    p.people_type IN ('CUSTOMER', 'BOTH') AND
    p.active_status = 'ENEBLED' AND
    CASE
	WHEN 0 = (
                SELECT COUNT(id) 
                FROM APC_LOAN 
                WHERE 
                    id_customer = p.id AND 
                    loan_status IN ('PENDING', 'BLACK_LIST', 'APPROVED', 'PENDING_RENOVATION', 'TO_DELIVERY')
            )
        THEN TRUE
        ELSE FALSE
    END
ORDER BY available_person;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_AVAILABLE_ENDORSEMENT_VIEW`
--
-- Sirve para cargar todos los posibles avales de una oficina 
-- que puedan solicitar un prestamo nuevo.
-- 
CREATE OR REPLACE VIEW `APC_AVAILABLE_ENDORSEMENTS_VIEW` AS
SELECT 
    p.id,
    CONCAT(p.first_name,' ', IF(ISNULL(p.second_name) ,'', CONCAT(p.second_name,' ')) ,p.last_name, ' ', p.middle_name) AS available_person,
    CASE
        WHEN p.people_type = 'BOTH'
        THEN 
            CASE
                WHEN 0 < (SELECT COUNT(id) FROM APC_LOAN WHERE  id_endorsement = p.id)
                THEN (SELECT id_customer FROM APC_LOAN WHERE id_endorsement = p.id ORDER BY created_on DESC LIMIT 1 )
                ELSE  ''
            END
        ELSE ''
    END as cross_signature
FROM APC_PEOPLE p
WHERE 
    p.people_type IN ('ENDORSEMENT', 'BOTH') AND
    p.active_status = 'ENEBLED' AND
    CASE
	WHEN 0 = (
            SELECT COUNT(id) 
            FROM APC_LOAN 
            WHERE 
                id_endorsement = p.id AND 
                loan_status IN ('PENDING', 'BLACK_LIST', 'APPROVED', 'PENDING_RENOVATION', 'TO_DELIVERY')
            )
        THEN TRUE
        ELSE FALSE
    END
ORDER BY available_person;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_CASH_REGISTER_CURDATE_BY_USER_VIEW`
--
-- Sirve para obtener todos los pagos que recabo un asesor en el día.
-- 
CREATE OR REPLACE VIEW `APC_CASH_REGISTER_CURDATE_BY_USER_VIEW` AS
SELECT 
    UUID() AS id,
    lt.payment_amount AS payment, 
    CONCAT(p.first_name, ' ', p.last_name) AS customer_name,
    lt.id_user AS id_user
FROM APC_LOAN_DETAIL lt
INNER JOIN APC_LOAN l ON lt.id_loan = l.id
INNER JOIN APC_PEOPLE p ON l.id_customer = p.id 
WHERE 
    lt.loan_details_type = 'PAYMENT' AND
    DATE(lt.created_on) = CURDATE()
ORDER BY lt.created_on;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_LOAN_TO_DELIVERY_BY_CERTIFIER_VIEW`
--
-- Sirve para obtener todos los prestamos que un certificador tiene que entregar.
-- 
CREATE OR REPLACE VIEW `APC_LOAN_TO_DELIVERY_BY_CERTIFIER_VIEW` AS
SELECT 
    l.id AS id_loan,
    u.id AS id_user,
    -- u.user_name, 
    -- hrhr.id_route,
    -- l.loan_status,
    CONCAT(
		CASE
			WHEN p.first_name IS NOT NULL
			THEN CONCAT(SUBSTR(UPPER(p.first_name), 1, 1),SUBSTR(LOWER(p.first_name), 2))
			ELSE ''
		END,
        CASE
			WHEN p.second_name IS NOT NULL
			THEN CONCAT(' ',SUBSTR(UPPER(p.second_name), 1, 1),SUBSTR(LOWER(p.second_name), 2))
			ELSE ''
		END,
        CASE
			WHEN p.last_name IS NOT NULL
			THEN CONCAT(' ',SUBSTR(UPPER(p.last_name), 1, 1),SUBSTR(LOWER(p.last_name), 2))
			ELSE ''
		END
    ) AS customer_name,
    CASE
	WHEN p.address_business IS NOT NULL
        THEN CONCAT(SUBSTR(UPPER(p.address_business), 1, 1),SUBSTR(LOWER(p.address_business), 2))
        ELSE ''
    END AS customer_address,
    p.thumbnail AS thumbnail,
    CASE
        WHEN (SELECT COUNT(id_loan_new) FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id) > 0
        THEN 'star'
        ELSE 'new'
    END AS icon,
    CASE
        WHEN (SELECT COUNT(id_loan_new) FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id) > 0
        THEN 
            CASE
                WHEN -- (SELECT amount_to_pay - amount_paid FROM APC_LOAN  WHERE id = l.id) > (lt.payment_daily * 2)
                    (SELECT innerL.amount_to_pay - innerL.amount_paid FROM APC_LOAN innerL 
                    INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                    WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id)) 
                    >
                    (SELECT innerLT.payment_daily FROM APC_LOAN innerL 
                    INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                    WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id))
                THEN 
                    CASE
                        WHEN 
                            (SELECT (innerL.amount_to_pay - innerL.amount_paid) - (innerLT.payment_daily * 2) FROM APC_LOAN innerL 
                            INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                            WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id))  > 0
                        THEN	
                            lt.payment - 
                            (lt.opening_fee + 
                            -- ((l.amount_to_pay - l.amount_paid) - (lt.payment_daily * 2))
                            (SELECT (innerL.amount_to_pay - innerL.amount_paid) - (innerLT.payment_daily * 2) FROM APC_LOAN innerL 
                            INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                            WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id)))
                    ELSE  
                       lt.payment - 
                       (lt.opening_fee + 
                       -- ((l.amount_to_pay - l.amount_paid) - (lt.payment_daily * 2))
                       (SELECT (innerLT.payment_daily * 2) - (innerL.amount_to_pay - innerL.amount_paid) FROM APC_LOAN innerL 
                       INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                       WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id)))
                    END
                ELSE lt.payment - lt.opening_fee
            END
        ELSE lt.payment - lt.opening_fee
    END AS amount_to_delivery,
    CASE
        WHEN (SELECT COUNT(id_loan_new) FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id) > 0
        THEN 
            CASE
                WHEN -- (SELECT amount_to_pay - amount_paid FROM APC_LOAN  WHERE id = l.id) > (lt.payment_daily * 2)
                    (SELECT innerL.amount_to_pay - innerL.amount_paid FROM APC_LOAN innerL 
                    INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                    WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id)) 
                    >
                    (SELECT innerLT.payment_daily FROM APC_LOAN innerL 
                    INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                    WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id))
                THEN -- ((l.amount_to_pay - l.amount_paid) - (lt.payment_daily * 2))
                    CASE
                        WHEN 
                            (SELECT (innerL.amount_to_pay - innerL.amount_paid) - (innerLT.payment_daily * 2) FROM APC_LOAN innerL 
                            INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                            WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id)) 
                            > 0
                        THEN  
                            (SELECT (innerL.amount_to_pay - innerL.amount_paid) - (innerLT.payment_daily * 2) FROM APC_LOAN innerL 
                            INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                            WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id)) 
                        ELSE
                            (SELECT (innerLT.payment_daily * 2) -(innerL.amount_to_pay - innerL.amount_paid)  FROM APC_LOAN innerL 
                            INNER JOIN APC_LOAN_TYPE innerLT ON innerL.id_loan_type = innerLT.id 
                            WHERE innerL.id = (SELECT id_loan_old FROM APC_LOAN_BY_RENOVATION WHERE id_loan_new = l.id)) 
                    END
                ELSE 0
            END
        ELSE 0
    END AS discount,
    lt.opening_fee AS opening,
    lt.payment AS payment
FROM 
    APC_USER u
    INNER JOIN APC_HUMAN_RESOURCE_HAS_ROUTE hrhr ON u.id_human_resource = hrhr.id_human_resource
    INNER JOIN APC_HUMAN_RESOURCE hr ON hrhr.id_human_resource = hr.id
    INNER JOIN APC_ROLE r ON hr.id_role = r.id
    INNER JOIN APC_LOAN l ON hrhr.id_route = l.id_route 
    INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
    INNER JOIN APC_PEOPLE p ON l.id_customer = p.id
WHERE 
    u.certifier = 'ENEBLED' AND 
    l.loan_status = 'TO_DELIVERY' AND
    DATE(l.created_on) <= CURDATE()
ORDER BY customer_name DESC;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_EXCHANGE_ENEBLED_USERS_VIEW`
--
-- Sirve para obtener todos los usuarios disponibles para realizar traspasos.
-- 
CREATE OR REPLACE VIEW `APC_EXCHANGE_ENEBLED_USERS_VIEW` AS
SELECT 
u.id AS id_user,
CONCAT(hr.first_name, ' ', IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name,' ')), hr.second_name, ' ', hr.last_name) AS user_name,
hrbo.id_office AS id_office
FROM APC_USER u 
INNER JOIN APC_HUMAN_RESOURCE hr ON u.id_human_resource = hr.id
INNER JOIN APC_HUMAN_RESOURCE_BY_OFFICE hrbo ON hr.id = hrbo.id_human_resource
WHERE u.user_status = 'ENEBLED' and
u.user_type IN ('MOBILE','BOTH');
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_TOTAL_CASH_BY_CURDATE_VIEW`
--
-- Sirve para obtener lo que tiene que entregar el asesor al final del día.
-- 
CREATE OR REPLACE VIEW `APC_TOTAL_CASH_BY_CURDATE_VIEW` AS
SELECT 
    u.id,
    ubo.id_office,
    IF(
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE())  = 0
        ,(SELECT IF(ISNULL(SUM(ld.payment_amount)),0, SUM(ld.payment_amount))
        FROM APC_LOAN_DETAIL ld
        WHERE 
            ld.id_user = u.id AND
            ld.loan_details_type = 'PAYMENT' AND 
            DATE(ld.created_on) = CURDATE()
        ) 
    ,0.0
    )AS total_amount_payment,
    IF(
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE())  = 0
        ,(SELECT IF(ISNULL(SUM(ld.payment_amount)),0, SUM(ld.payment_amount))
        FROM APC_LOAN_DETAIL ld
        WHERE 
            ld.id_user = u.id AND
            ld.loan_details_type = 'TRANSFER' AND 
            DATE(ld.created_on) = CURDATE()
        ) 
    ,0.0
    )AS total_amount_deposit,
    IF(
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE()) = 0
        ,(SELECT IF(ISNULL(SUM(amount_to_transfer)),0, SUM(amount_to_transfer))
         FROM APC_TRANSFER 
         WHERE 
            id_user_transmitter = u.id AND
            action_status = 'APPROVED' AND
            active_status = 'ENEBLED' AND 
            DATE(last_updated_on) = CURDATE()
        )
        ,0.0
    ) AS transfer_sender,
    IF(
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE()) = 0
        ,(SELECT IF(ISNULL(SUM(amount_to_transfer)),0, SUM(amount_to_transfer)) 
        FROM APC_TRANSFER 
        WHERE 
            id_user_receiver = u.id AND
            action_status = 'APPROVED' AND
            active_status = 'ENEBLED'  AND 
            DATE(last_updated_on) = CURDATE()
        )
        ,0.0
    ) AS transfer_receiver,
    IF(
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE()) = 0
        ,(SELECT IF(ISNULL(SUM(amount)),0, SUM(amount)) 
        FROM APC_MONEY_DAILY 
        WHERE 
            id_user = u.id AND 
            DATE(money_daily_date) = CURDATE()
        )
        ,0.0
    ) AS money_daily,
    IF(
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE()) = 0
        ,(SELECT IF(ISNULL(SUM(expense)),0, SUM(expense)) 
        FROM APC_OTHER_EXPENSE 
        WHERE 
            id_user = u.id AND 
            DATE(created_on) = CURDATE()
        ) 
        ,0.0
    )AS other_expense,
    IF( 
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE()) = 0
        ,(SELECT IF(ISNULL(SUM(amount)),0, SUM(amount)) 
        FROM APC_DELIVERY 
        WHERE 
            id_user = u.id AND 
            DATE(created_on) = CURDATE()
        )
        , 0.0
    ) as delivery,
    IF(
        (SELECT COUNT(id) FROM APC_CLOSING_DAY WHERE id_user = u.id AND id_office = ubo.id_office AND active_status = 'ENEBLED' AND DATE(created_on) = CURDATE()) = 0
        ,(SELECT IF(ISNULL(SUM(amount_to_transfer)),0, SUM(amount_to_transfer)) 
        FROM APC_TRANSFER 
        WHERE 
            id_user_receiver = u.id AND
            action_status = 'PENDING' AND
            active_status = 'ENEBLED'  AND 
            DATE(created_on) = CURDATE()
        )
        ,0.0
    ) AS transfer_pending
FROM APC_USER u
JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE 
    u.user_status = 'ENEBLED';
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_TOTAL_LOANS_BY_OFFICE`
--
-- 
CREATE OR REPLACE VIEW `APC_TOTAL_LOANS_BY_OFFICE` AS
SELECT
    l.id,
    l.loan_status,
    r.id_office,
    r.route_name,
    l.id_route,
    l.amount_to_pay,
    l.amount_paid
    FROM
        APC_LOAN l
    JOIN 
	APC_ROUTE r ON r.id = l.id_route
	WHERE
            l.loan_status in ('PENDING', 'FINISH', 'APPROVED','REJECTED','TO_DELIVERY')
            AND r.active_status = 'ENEBLED';
-- --------------------------------------------------------------------
-- Estructura para la vista `APC_TOTAL_CLOSING_DAY_BY_CURDATE_VIEW`
--
-- Sirve para obtener lo que tiene que entregar el asesor al final del día.
-- 
CREATE OR REPLACE VIEW `APC_TOTAL_CLOSING_DAY_BY_CURDATE_VIEW` AS
SELECT 
    u.id,
    u.id_office,
    u.amount_paid
FROM APC_CLOSING_DAY u
WHERE 
    u.active_status = 'ENEBLED' AND
    DATE(u.created_on) = CURDATE();
-- --------------------------------------------------------------------
-- Estructura para la vista `APC_CLOSING_DAILY_DETAIL_FROM_USER_BY_CURDATE_VIEW`
--
-- Sirve para obtener los detalles del total de los cobros realizados por los asesores
-- estos pueden ser tanto pagos como multas.
-- 
CREATE OR REPLACE VIEW `APC_CLOSING_DAILY_DETAIL_FROM_USER_BY_CURDATE_VIEW` AS
SELECT
	ld.id,
    CONCAT(p.first_name,' ',IF(ISNULL(p.second_name) ,'', CONCAT(p.second_name, ' ')),p.last_name,' ', p.middle_name) AS comments,
    ld.payment_amount as amount, 
    CASE 
	WHEN ld.loan_details_type = 'PAYMENT' THEN 'Abono' 
        WHEN ld.loan_details_type = 'FEE' THEN 'Multa' 
        WHEN ld.loan_details_type = 'TRANSFER' THEN 'Depósito'
        ELSE '' END as type,
    ld.id_user,
    ld.created_on,
    l.created_on as fechaFiltro,
    'xxxx' as route,
    (l.amount_to_pay - amount_paid) as saldo
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON ld.id_loan = l.id
INNER JOIN APC_PEOPLE p ON l.id_customer = p.id
WHERE DATE(ld.created_on) = CURDATE() 
AND ld.loan_details_type in ('PAYMENT', 'FEE', 'TRANSFER' )
UNION
SELECT 
	md.id,
	DATE(md.money_daily_date) AS comments,
    md.amount as amount,
    'Inicio' as type,
    md.id_user,
    md.created_on,
    CURDATE() as fechaFiltro,
    'xxxx' as route,
    0 as saldo
FROM
	APC_MONEY_DAILY md
WHERE DATE(md.money_daily_date) = CURDATE()
UNION
SELECT 
	oe.id,
	oe.description,
    oe.expense,
    'Gasto',
    oe.id_user,
    oe.created_on,
    CURDATE() as fechaFiltro,
    'xxxx' as route,
    0 as saldo
FROM
	APC_OTHER_EXPENSE oe
WHERE DATE(oe.created_on) = CURDATE()
UNION
SELECT
	te.id,
	CONCAT(hr.first_name,' ',IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name, ' ')),hr.last_name,' ', hr.middle_name),
    te.amount_to_transfer,
    'Transferencia enviada',
    te.id_user_transmitter,
    te.created_on,
    CURDATE() as fechaFiltro,
    'xxxx' as route,
    0 as saldo
FROM
	APC_TRANSFER te
JOIN
	APC_USER u on u.id = te.id_user_receiver
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = u.id_human_resource
WHERE DATE(te.created_on) = CURDATE()
UNION
SELECT
	tr.id,
	CONCAT(hr.first_name,' ',IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name, ' ')),hr.last_name,' ', hr.middle_name),
    tr.amount_to_transfer,
    'Transferencia recibida',
    tr.id_user_receiver,
    tr.created_on,
    CURDATE() as fechaFiltro,
    'xxxx' as route,
    0 as saldo
FROM
	APC_TRANSFER tr
JOIN
	APC_USER u on u.id = tr.id_user_transmitter
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = u.id_human_resource
WHERE DATE(tr.created_on) = CURDATE()
UNION 
SELECT
	d.id,
    CONCAT(p.first_name,' ',IF(ISNULL(p.second_name) ,'', CONCAT(p.second_name, ' ')),p.last_name,' ', p.middle_name, ' - ', 
    r.route_name , ', Abono préstamo anterior : ', Case WHEN (SELECT CASE WHEN ld.payment_amount is null or 0 THEN 0 ELSE ld.payment_amount END FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l3 ON ld.id_loan = l3.id
WHERE ld.loan_details_type = 'RENOVATION_PAYMENT' 
AND l3.id = (SELECT albr.id_loan_old FROM APC_LOAN_BY_RENOVATION albr WHERE albr.id_loan_new = l.id)) is null then 0 else (SELECT CASE WHEN ld.payment_amount is null or 0 THEN 0 ELSE ld.payment_amount END FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l3 ON ld.id_loan = l3.id
WHERE ld.loan_details_type = 'RENOVATION_PAYMENT' 
AND l3.id = (SELECT albr.id_loan_old FROM APC_LOAN_BY_RENOVATION albr WHERE albr.id_loan_new = l.id)) end , ', Comisión por apertura: ',  lt.opening_fee),
    d.amount, 
    'Entrega de préstamo',
    d.id_user,
    d.created_on,
    CURDATE() as fechaFiltro,
    r.route_name as route,
    CASE WHEN (SELECT (l2.amount_to_pay - l2.amount_paid) FROM APC_LOAN_BY_RENOVATION lr 
    INNER JOIN APC_LOAN l2 ON l2.id = lr.id_loan_old 
    WHERE lr.id_loan_new = l.id) is null THEN 0 ELSE 
    (SELECT (l2.amount_to_pay - l2.amount_paid) FROM APC_LOAN_BY_RENOVATION lr 
    INNER JOIN APC_LOAN l2 ON l2.id = lr.id_loan_old 
    WHERE lr.id_loan_new = l.id) END as saldo
FROM APC_DELIVERY d
INNER JOIN APC_LOAN l ON d.id_loan = l.id
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN APC_PEOPLE p ON l.id_customer = p.id
INNER JOIN APC_ROUTE r ON r.id = l.id_route
WHERE DATE(d.created_on) = CURDATE();
-- --------------------------------------------------------------------
-- Estructura para la vista `APC_LOAN_APPROVED_DETAIL_VIEW`
--
-- Sirve para obtener los detalles del total de los prestamos que estan
-- en estatus de APROBADO.
-- 
CREATE OR REPLACE VIEW `APC_LOAN_APPROVED_DETAIL_VIEW` AS
SELECT
    l.id,
    l.amount_to_pay - l.amount_paid AS total_to_pay,
    l.amount_to_pay - (l.amount_paid + (SELECT IF(ISNULL(SUM(payment_amount)),0,SUM(payment_amount)) FROM APC_LOAN_DETAIL WHERE id_loan = l.id AND loan_details_type = 'FEE')) AS loan_amount_to_pay,
    ( 
        SELECT 
            IF(ISNULL(SUM(payment_amount)),0,SUM(payment_amount)) 
        FROM APC_LOAN_DETAIL 
        WHERE 
            id_loan = l.id AND 
            loan_details_type = 'FEE'
    )AS total_fee
FROM APC_LOAN l
WHERE l.loan_status = 'APPROVED';
-- --------------------------------------------------------------------
-- Estructura para la vista `APC_GENERAL_BOX_VIEW`
-- 
CREATE OR REPLACE VIEW `APC_GENERAL_BOX_VIEW` AS
SELECT
	md.id,
    CONCAT(hr.first_name,' ',IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name, ' ')),hr.last_name,' ', hr.middle_name) AS comments,
    Date(md.money_daily_date) as fecha,
    md.amount as amount, 
    md.id_office as office,
    'Inicio' as type
FROM APC_MONEY_DAILY md
JOIN
	APC_USER u on u.id = md.id_user
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = u.id_human_resource
UNION
SELECT
	cd.id,
    CONCAT(hr.first_name,' ',IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name, ' ')),hr.last_name,' ', hr.middle_name) AS comments,
    Date(cd.created_on),
    cd.amount_paid,
    cd.id_office,
    'Corte del día'
FROM APC_CLOSING_DAY cd
JOIN
	APC_USER u on u.id = cd.id_user
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = u.id_human_resource
WHERE cd.active_status = 'ENEBLED'
UNION
SELECT
	ecin.id,
    ecin.description,
    date(ecin.created_on),
    ecin.amount,
    ecin.id_office,
    'Entrada'
FROM APC_EXPENSE_COMPANY as ecin
WHERE ecin.expense_company_type = 'PAYMENT_IN' 
AND ecin.active_status = 'ENEBLED'
UNION
SELECT
	ecin.id,
    ecin.description,
    date(ecin.created_on),
    ecin.amount,
    ecin.id_office,
    'Salida'
FROM APC_EXPENSE_COMPANY as ecin
WHERE ecin.expense_company_type = 'PAYMENT_OUT' 
AND ecin.active_status = 'ENEBLED' 
UNION
SELECT
	a.id,
    CONCAT(hr.first_name,' ',IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name, ' ')),hr.last_name,' ', hr.middle_name) AS comments,
    date(a.created_on),
    a.amount,
    a.id_office,
    'Adelanto'
FROM 
	APC_ADVANCE a 
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = a.id_human_resource
WHERE 
	a.active_status = 'ENEBLED' 
UNION
SELECT
	pay.id,
    CONCAT(hr.first_name,' ',IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name, ' ')),hr.last_name,' ', hr.middle_name) AS comments,
    date(pay.created_on),
    pay.total_payment,
    pay.id_office,
    'Nomina'
FROM 
	APC_PAYROLL pay 
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = pay.id_human_resource
WHERE 
	pay.active_status = 'ENEBLED';
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `APC_LOAN`
--
-- MODUFY COLUMN loan_status
--
ALTER TABLE APC_LOAN
MODIFY COLUMN `loan_status` ENUM('PENDING', 'FINISH','BLACK_LIST', 'APPROVED','REJECTED', 'PENDING_RENOVATION', 'TO_DELIVERY', 'DELETED') NOT NULL DEFAULT 'PENDING';

ALTER TABLE APC_LOAN_BY_USER
MODIFY COLUMN `loan_by_user_status` ENUM('PENDING', 'FINISH','BLACK_LIST', 'APPROVED','REJECTED', 'PENDING_RENOVATION', 'TO_DELIVERY', 'DELETED') NOT NULL DEFAULT 'PENDING';

-- 
-- Estructura para la vista `APC_LOAN_BY_USER_PAYMENT_ZERO_VIEW`
--
-- Total de abonos en ceros por dia.
-- 
CREATE OR REPLACE VIEW `APC_LOAN_BY_USER_PAYMENT_ZERO_VIEW` AS 
SELECT 
    ald.id,
    ald.id_user,
    al.id_customer,
    ald.loan_comments
FROM APC_LOAN_DETAIL ald
INNER JOIN APC_LOAN al ON ald.id_loan = al.id
WHERE
	DATE(ald.created_on) = CURDATE() 
    AND ald.payment_amount = 0
    AND al.loan_status != 'DELETED'
    AND ald.loan_details_type = 'PAYMENT';
-- 
-- Estructura para la tabla `APC_PAYROLL`
--
-- Totales de la caja de APC.
-- 
CREATE TABLE `APC_PAYROLL`(
    `id` char(36) NOT NULL,
    `active_status` ENUM('ENEBLED', 'DISABLED') NOT NULL DEFAULT 'ENEBLED',
    `id_office` char(36) NOT NULL,
    `id_human_resource` char(36) NOT NULL,
    `salary` numeric(8,2) NOT NULL,
    `imss` numeric(8,2) NOT NULL,
    `advance` numeric(8,2) NOT NULL,
    `total_bonus_new_customer` numeric(8,2) NOT NULL,
    `total_bonus_colocation` numeric(8,2) NOT NULL,
    `total_bonus_mora` numeric(8,2) NOT NULL,
    `discounts` numeric(8,2) NOT NULL,
    `increases` numeric(8,2) NOT NULL,
    `total_payment` numeric(8,2) NOT NULL,
    `total_days_salary` smallint NOT NULL DEFAULT 5,
    `total_days_bonus` smallint NOT NULL DEFAULT 5,
    `comments_discounts` varchar(200),
    `comments_increases` varchar(200),
    `observation` varchar(200),
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

CREATE OR REPLACE VIEW `APC_ADVANCE_USER_DAILY_VIEW` AS 
SELECT 
	ate.id,
	CONCAT(ahr.first_name, ' ' , ahr.last_name) as user_name,
    ate.total_expected,
    (ate.total_expected - (SELECT COUNT(id) FROM APC_LOAN_BY_USER_VIEW where user_id = ate.id_user)) as total_now,
    CASE WHEN (ate.total_expected - (SELECT COUNT(id) FROM APC_LOAN_BY_USER_VIEW where user_id = ate.id_user)) = 0 
    THEN 0 
    ELSE 
    ((ate.total_expected - (SELECT COUNT(id) FROM APC_LOAN_BY_USER_VIEW where user_id = ate.id_user)) * 100) / ate.total_expected
    END 
    as porcentaje,
    ate.id_office,
    ate.id_user,
    (SELECT IF(ISNULL(SUM(ate2.total_expected_payment)),0,SUM(ate2.total_expected_payment)) FROM APC_TOTAL_EXPECTED_PAYMENT_DAILY_BY_USER ate2 
    where WEEK(DATE(ate2.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(DATE(ate2.created_on)) = (SELECT YEAR(CURDATE())) AND ate2.id_user = ate.id_user) 
    as total_expected_week,
    (SELECT IF(ISNULL(SUM(ald.payment_amount)),0,SUM(ald.payment_amount)) FROM APC_LOAN_DETAIL ald 
    where WEEK(Date(ald.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(Date(ald.created_on)) = (SELECT YEAR(CURDATE())) AND ald.id_user = ate.id_user 
    AND ald.loan_details_type IN ('PAYMENT', 'RENOVATION_PAYMENT', 'TRANSFER')) 
    as total_reported_week,
    (SELECT IF(ISNULL(SUM(ailwv.faltante)),0,SUM(ailwv.faltante)) FROM APC_INFORMATION_LOAN_WEEK_VIEW ailwv
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = ailwv.id 
    WHERE albu.id_user = ate.id_user) as faltante,
    (SELECT IF(ISNULL(SUM(ald.payment_amount)),0,SUM(ald.payment_amount)) 
    FROM APC_LOAN_DETAIL ald 
    INNER JOIN APC_LOAN al ON ald.id_loan = al.id 
    INNER JOIN APC_LOAN_BY_USER albu ON al.id = albu.id_loan
    where WEEK(Date(ald.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(Date(ald.created_on)) = (SELECT YEAR(CURDATE())) 
    AND ald.loan_details_type IN ('RENOVATION_PAYMENT') AND albu.id_user = ate.id_user) as total_reported_renovation_week, 
    (SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) 
    FROM APC_LOAN al 
    INNER JOIN APC_LOAN_BY_USER albu ON al.id = albu.id_loan
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id
    WHERE albu.id_user = ate.id_user AND al.loan_status = 'APPROVED' AND 
    WEEK(Date(al.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(Date(al.created_on)) = (SELECT YEAR(CURDATE()))
    ) as total_comision_fee,
    (SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = ate.id_user 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = (SELECT YEAR(CURDATE()))) 
    as colocation_approved,
    (SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'TO_DELIVERY' AND albu.id_user = ate.id_user 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = (SELECT YEAR(CURDATE()))) 
    as colocation_to_delivery
FROM 
	APC_TOTAL_EXPECTED_PAYMENT_DAILY_BY_USER ate
INNER JOIN APC_USER au ON au.id = ate.id_user
INNER JOIN APC_HUMAN_RESOURCE ahr ON ahr.id = au.id_human_resource
WHERE
	DATE(ate.created_on) = CURDATE() 
    AND ate.active_status = 'ENEBLED';

CREATE OR REPLACE VIEW `APC_CUSTOMERS_WITHOUT_RENOVATION_VIEW` AS
SELECT 
    p.id,
    CONCAT(p.first_name,' ', IF(ISNULL(p.second_name) ,'', CONCAT(p.second_name,' ')) ,p.last_name, ' ', p.middle_name) AS available_person,
    ar.route_name,
    (SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id) as last_loan,
    p.id_office
FROM APC_PEOPLE p
INNER JOIN APC_ROUTE ar ON ar.id = p.id_route
WHERE 
    p.people_type IN ('CUSTOMER', 'BOTH') AND
    p.active_status = 'ENEBLED' AND 
    (SELECT COUNT(l.id) FROM APC_LOAN l WHERE l.id_customer = p.id) > 0 AND
    CASE
	WHEN 0 = (
                SELECT COUNT(id) 
                FROM APC_LOAN 
                WHERE 
                    id_customer = p.id AND 
                    loan_status IN ('PENDING', 'BLACK_LIST', 'APPROVED', 'PENDING_RENOVATION', 'TO_DELIVERY')
            )
        THEN TRUE
        ELSE FALSE
    END
ORDER BY available_person;

CREATE OR REPLACE VIEW `APC_ADVANCE_USER_DAILY_DETAIL_VIEW` AS 
SELECT 
ald.id,
ald.id_user,
CONCAT(ap.first_name,
                ' ',
                ap.last_name) AS customer_name,
ald.payment_amount,
ald.created_on,
ap.address_business,
CASE 
	WHEN ald.loan_details_type = 'PAYMENT' THEN 'Abono' 
        WHEN ald.loan_details_type = 'FEE' THEN 'Multa' 
        WHEN ald.loan_details_type = 'TRANSFER' THEN 'Depósito' 
        ELSE '' END as type_ayment
FROM APC_LOAN_DETAIL ald
INNER JOIN APC_LOAN al ON al.id = ald.id_loan
INNER JOIN APC_PEOPLE ap ON ap.id = al.id_customer
WHERE DATE(ald.created_on) = CURDATE() AND 
al.loan_status != 'DELETED' AND 
ald.loan_details_type IN ('PAYMENT','FEE','TRANSFER')
UNION
SELECT
id,
user_id,
customer_name,
'Sin visita',
CURDATE(),
customer_address_business,
'Sin visita'
FROM APC_LOAN_BY_USER_VIEW;

CREATE TABLE `APC_CLOSING_DAY_DETAIL`(
    `id` char(36) NOT NULL,
    `id_closing_day` char(36) NOT NULL,
    `comments` varchar(200) NOT NULL,
    `amount` numeric(8,2) NOT NULL,
    `type` varchar(200) NOT NULL,
    `dateDetail` datetime,
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

CREATE OR REPLACE VIEW `APC_TOTAL_LOANS_APPROVED_BY_OFFICE` AS
SELECT
    l.id,
    l.loan_status,
    r.id_office,
    r.payment_daily
    FROM
        APC_LOAN l
    JOIN 
	APC_LOAN_TYPE r ON r.id = l.id_loan_type
	WHERE
            l.loan_status in ('APPROVED');

CREATE OR REPLACE VIEW `APC_TOTAL_CASH_BY_CURDATE_DASHBOARD_VIEW` AS
SELECT 
    u.id,
    ubo.id_office,
    (SELECT IF(ISNULL(SUM(ld.payment_amount)),0, SUM(ld.payment_amount))
        FROM APC_LOAN_DETAIL ld
        WHERE 
            ld.id_user = u.id AND
            ld.loan_details_type = 'PAYMENT' AND 
            DATE(ld.created_on) = CURDATE()
        
    )AS total_amount_payment,
    (SELECT IF(ISNULL(SUM(ld.payment_amount)),0, SUM(ld.payment_amount))
        FROM APC_LOAN_DETAIL ld
        WHERE 
            ld.id_user = u.id AND
            ld.loan_details_type = 'TRANSFER' AND 
            DATE(ld.created_on) = CURDATE()
        
    )AS total_amount_deposit,
    (SELECT IF(ISNULL(SUM(amount_to_transfer)),0, SUM(amount_to_transfer))
         FROM APC_TRANSFER 
         WHERE 
            id_user_transmitter = u.id AND
            action_status = 'APPROVED' AND
            active_status = 'ENEBLED' AND 
            DATE(last_updated_on) = CURDATE()
        )AS transfer_sender,
    (SELECT IF(ISNULL(SUM(amount_to_transfer)),0, SUM(amount_to_transfer)) 
        FROM APC_TRANSFER 
        WHERE 
            id_user_receiver = u.id AND
            action_status = 'APPROVED' AND
            active_status = 'ENEBLED'  AND 
            DATE(last_updated_on) = CURDATE()
        ) AS transfer_receiver,
    (SELECT IF(ISNULL(SUM(amount)),0, SUM(amount)) 
        FROM APC_MONEY_DAILY 
        WHERE 
            id_user = u.id AND 
            DATE(money_daily_date) = CURDATE()
        ) AS money_daily,
    (SELECT IF(ISNULL(SUM(expense)),0, SUM(expense)) 
        FROM APC_OTHER_EXPENSE 
        WHERE 
            id_user = u.id AND 
            DATE(created_on) = CURDATE()
        ) AS other_expense,
    (SELECT IF(ISNULL(SUM(amount)),0, SUM(amount)) 
        FROM APC_DELIVERY 
        WHERE 
            id_user = u.id AND 
            DATE(created_on) = CURDATE()
        ) as delivery,
    (SELECT IF(ISNULL(SUM(amount_to_transfer)),0, SUM(amount_to_transfer)) 
        FROM APC_TRANSFER 
        WHERE 
            id_user_receiver = u.id AND
            action_status = 'PENDING' AND
            active_status = 'ENEBLED'  AND 
            DATE(created_on) = CURDATE()
        ) AS transfer_pending
FROM APC_USER u
JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE 
    u.user_status = 'ENEBLED';

CREATE OR REPLACE VIEW `APC_MONEY_DAILY_BY_USER_CERTIFIER_VIEW` AS
SELECT 
	u.id,
	u.user_name,
    uo.id_office,
    CONCAT(hr.first_name, ' ' , hr.last_name) as employee,
    (SELECT SUM(ldc.amount_to_delivery) FROM APC_LOAN_TO_DELIVERY_BY_CERTIFIER_VIEW ldc 
    WHERE ldc.id_user = u.id) as amount
FROM 
	APC_USER u
INNER JOIN 
	APC_USER_BY_OFFICE uo ON u.id = uo.id_user
INNER JOIN 
	APC_HUMAN_RESOURCE hr ON u.id_human_resource = hr.id
WHERE
	u.user_status = 'ENEBLED'
AND 
	u.certifier = 'ENEBLED';
-- --------------------------------------------------------
--
-- Alter table to  `APC_LOAN_DETAIL`
--
-- Add new loan details status to save transfer payments.
--
ALTER TABLE APC_LOAN_DETAIL
MODIFY COLUMN `loan_details_type` ENUM('CREDIT_PAYMENT', 'DEBIT_PAYMENT', 'PAYMENT', 'FEE','RENOVATION_PAYMENT', 'TRANSFER') NOT NULL;

ALTER TABLE APC_TOTAL_EXPECTED_PAYMENT_DAILY_BY_USER
ADD COLUMN `total_expected_payment` numeric(8,2) default null;

CREATE OR REPLACE VIEW `APC_INFORMATION_LOAN_WEEK_VIEW` AS
SELECT 
l.id,
u.id as id_user,
r.id_office,
l.created_on as fecha,
lt.payment as apoyos,
lt.payment_total as apoyos_total,
lt.opening_fee as comision_apertura,
CONCAT(endor.first_name,' ', IF(ISNULL(endor.second_name) ,'', CONCAT(endor.second_name,' ')) ,endor.last_name, ' ', endor.middle_name) AS aval,
CONCAT(cus.first_name,' ', IF(ISNULL(cus.second_name) ,'', CONCAT(cus.second_name,' ')) ,cus.last_name, ' ', cus.middle_name) AS customer,
l.amount_to_pay as documento_por,
lt.payment_daily as abono_diario,
l.amount_paid,
(l.amount_to_pay - l.amount_paid) saldo_insoluto,
r.route_name,
CONCAT(hr.first_name,' ', IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name,' ')) ,hr.last_name, ' ', hr.middle_name) AS asesor,
(SELECT COUNT(lfn.id) FROM APC_LOAN_FEE_NOTIFICATION lfn WHERE lfn.id_loan = l.id) as num_fee,
-- Lunes
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'monday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_monday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'monday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_monday,
-- Martes
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'tuesday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_tuesday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE())  
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'tuesday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_tuesday,
-- Miercoles
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) 
AND YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'wednesday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_wednesday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'wednesday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_wednesday,
-- Jueves
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'thursday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_thursday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'thursday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_thursday,
-- Viernes
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'friday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_friday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'friday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_friday,
-- Sabado
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'saturday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_saturday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'saturday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_saturday,

((lt.payment_daily * (SELECT IF(COUNT(DISTINCT(DATE(ldFaltante.created_on))) > 5, 5 , COUNT(DISTINCT(DATE(ldFaltante.created_on)))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE'))) 
- (SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER'))) as faltante,

CASE WHEN l.new_customer = 'ENEBLED' THEN 'Si' ELSE 'No' END as new_customer,
if((SELECT COUNT(id_loan_old) FROM APC_LOAN_BY_RENOVATION lbr 
INNER JOIN APC_LOAN lRenovation ON lbr.id_loan_new = lRenovation.id
WHERE id_loan_old = l.id
 AND loan_by_renovation_status = 'APPROVED' and WEEK(DATE(lRenovation.created_on),1) <=  WEEK(CURDATE(),1) AND 
 YEAR(DATE(lRenovation.created_on)) = YEAR(CURDATE())) = 0 , 'No' , 'Si') as renovation, 
l.loan_status as estatus_prestamo,
(SELECT COUNT(DISTINCT(DATE(ldFaltante.created_on))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) <= (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_all,
(SELECT IF(COUNT(DISTINCT(DATE(ldFaltante.created_on))) > 5, 5, COUNT(DISTINCT(DATE(ldFaltante.created_on)))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_week,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) <= (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND ldLunes.loan_details_type IN ('FEE')) as fee_todos
FROM
APC_LOAN l
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN APC_PEOPLE cus ON cus.id = l.id_customer
INNER JOIN APC_PEOPLE endor ON endor.id = l.id_endorsement
INNER JOIN APC_ROUTE r ON r.id = l.id_route
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
INNER JOIN APC_USER u ON u.id = lbu.id_user
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
WHERE
l.loan_status not in ('DELETED','REJECTED') AND 
(SELECT COUNT(ld.id) FROM APC_LOAN_DETAIL ld WHERE WEEK(DATE(ld.created_on),1) = WEEK(CURDATE(),1) AND 
 YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.id_loan = l.id) > 0;
-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_USER_BY_ROUTE_VIEW`
--
-- Se utiliza para identificar a los usuarios por rutas. 
--
CREATE OR REPLACE VIEW `APC_USER_BY_ROUTE_VIEW` AS
SELECT
CONCAT(u.id,hrhr.id_route) AS id,
u.id AS id_user,
hrhr.id_route AS id_route,
 CONCAT(
		CASE
			WHEN hr.first_name IS NOT NULL AND hr.first_name != ''
			THEN CONCAT(SUBSTR(UPPER(hr.first_name), 1, 1),SUBSTR(LOWER(hr.first_name), 2))
			ELSE ''
		END,
        CASE
			WHEN hr.second_name IS NOT NULL AND hr.second_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(hr.second_name), 1, 1),SUBSTR(LOWER(hr.second_name), 2))
			ELSE ''
		END,
        CASE
			WHEN hr.last_name IS NOT NULL AND hr.last_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(hr.last_name), 1, 1),SUBSTR(LOWER(hr.last_name), 2))
			ELSE ''
		END,
        CASE
			WHEN hr.middle_name IS NOT NULL AND hr.middle_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(hr.middle_name), 1, 1),SUBSTR(LOWER(hr.middle_name), 2))
			ELSE ''
		END
    ) AS employee_name
FROM APC_HUMAN_RESOURCE_HAS_ROUTE hrhr
INNER JOIN APC_HUMAN_RESOURCE hr ON hrhr.id_human_resource = hr.id
INNER JOIN APC_USER u ON hr.id = u.id_human_resource
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE','BOTH')
ORDER BY employee_name;

CREATE OR REPLACE VIEW `APC_LOAN_DIFERENCES_BY_USER_LAST_WEEK_VIEW` AS
SELECT
l.id,
lbu.id_user,
((lt.payment_daily * 5) - (SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld 
WHERE ld.id_loan = l.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) 
AND YEAR(DATE(ld.created_on)) = YEAR(CURDATE())
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')))
 as faltante
FROM
	APC_LOAN l
INNER JOIN 
	APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN 
	APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE
l.loan_status not in ('DELETED','REJECTED') AND 
(SELECT COUNT(ld2.id) FROM APC_LOAN_DETAIL ld2 WHERE WEEK(DATE(ld2.created_on),1) = (WEEK(CURDATE(),1)-1) 
AND YEAR(DATE(ld2.created_on)) = YEAR(CURDATE()) AND ld2.id_loan = l.id) > 4;

CREATE OR REPLACE VIEW `APC_INFORMATION_LOAN_LAST_WEEK_VIEW` AS
SELECT 
l.id,
u.id as id_user,
r.id_office,
l.created_on as fecha,
lt.payment as apoyos,
lt.payment_total as apoyos_total,
lt.opening_fee as comision_apertura,
CONCAT(endor.first_name,' ', IF(ISNULL(endor.second_name) ,'', CONCAT(endor.second_name,' ')) ,endor.last_name, ' ', endor.middle_name) AS aval,
CONCAT(cus.first_name,' ', IF(ISNULL(cus.second_name) ,'', CONCAT(cus.second_name,' ')) ,cus.last_name, ' ', cus.middle_name) AS customer,
l.amount_to_pay as documento_por,
lt.payment_daily as abono_diario,
l.amount_paid,
(l.amount_to_pay - l.amount_paid) saldo_insoluto,
r.route_name,
CONCAT(hr.first_name,' ', IF(ISNULL(hr.second_name) ,'', CONCAT(hr.second_name,' ')) ,hr.last_name, ' ', hr.middle_name) AS asesor,
(SELECT COUNT(lfn.id) FROM APC_LOAN_FEE_NOTIFICATION lfn WHERE lfn.id_loan = l.id) as num_fee,
-- Lunes
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'monday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_monday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'monday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_monday,
-- Martes
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'tuesday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_tuesday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'tuesday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_tuesday,
-- Miercoles
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'wednesday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_wednesday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'wednesday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_wednesday,
-- Jueves
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'thursday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_thursday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'thursday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_thursday,
-- Viernes
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'friday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_friday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE())  
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'friday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_friday,
-- Sabado
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'saturday' 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as payment_saturday,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ldLunes.created_on))) = 'saturday' 
AND ldLunes.loan_details_type IN ('FEE')) as fee_saturday,

((lt.payment_daily * (SELECT IF(COUNT(DISTINCT(DATE(ldFaltante.created_on))) > 5, 5, COUNT(DISTINCT(DATE(ldFaltante.created_on)))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE'))) 
- (SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER'))) as faltante,

CASE WHEN l.new_customer = 'ENEBLED' THEN 'Si' ELSE 'No' END as new_customer,
(SELECT IF(COUNT(id_loan_old) = 0 , 'No' , 'Si') FROM APC_LOAN_BY_RENOVATION lbr 
INNER JOIN APC_LOAN lRenovation ON lbr.id_loan_new = lRenovation.id
WHERE id_loan_old = l.id
 AND loan_by_renovation_status = 'APPROVED' and WEEK(DATE(lRenovation.created_on),1) <= (WEEK(CURDATE(),1) - 1) AND 
 YEAR(DATE(lRenovation.created_on)) = YEAR(CURDATE())) as renovation, 
l.loan_status as estatus_prestamo,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as abono_semana_actual,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND ldLunes.loan_details_type IN ('FEE')) as fee_semana_actual,
(SELECT COUNT(DISTINCT(DATE(ldFaltante.created_on))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) <= (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_all,
(SELECT IF(COUNT(DISTINCT(DATE(ldFaltante.created_on))) > 5, 5, COUNT(DISTINCT(DATE(ldFaltante.created_on)))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_week,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) <= (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
AND ldLunes.loan_details_type IN ('FEE')) as fee_todos
FROM
APC_LOAN l
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN APC_PEOPLE cus ON cus.id = l.id_customer
INNER JOIN APC_PEOPLE endor ON endor.id = l.id_endorsement
INNER JOIN APC_ROUTE r ON r.id = l.id_route
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
INNER JOIN APC_USER u ON u.id = lbu.id_user
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
WHERE
l.loan_status not in ('DELETED','REJECTED') AND 
(SELECT COUNT(ld.id) FROM APC_LOAN_DETAIL ld WHERE WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) 
AND YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.id_loan = l.id) > 0;

CREATE OR REPLACE VIEW `APC_COLOCATION_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'monday') 
    as colocation_monday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'tuesday') 
    as colocation_tuesday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'wednesday') 
    as colocation_wednesday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'thursday') 
    as colocation_thursday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'friday') 
    as colocation_friday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'saturday') 
    as colocation_saturday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE())) 
    as colocation_total
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';

CREATE OR REPLACE VIEW `APC_COLOCATION_LAST_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'monday') 
    as colocation_monday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'tuesday') 
    as colocation_tuesday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'wednesday') 
    as colocation_wednesday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'thursday') 
    as colocation_thursday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'friday') 
    as colocation_friday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'saturday') 
    as colocation_saturday,
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE())) 
    as colocation_total
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';


CREATE OR REPLACE VIEW `APC_SUBTOTAL_LAST_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'monday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_monday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'monday') 
    as opening_fee_monday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'tuesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_tuesday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'tuesday') 
    as opening_fee_tuesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'wednesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_wednesday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'wednesday') 
    as opening_fee_wednesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'thursday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_thursday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'thursday') 
    as opening_fee_thursday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'friday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_friday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'friday') 
    as opening_fee_friday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'saturday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_saturday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'saturday') 
    as opening_fee_saturday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_total,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) ) 
    as opening_fee_total
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';

CREATE OR REPLACE VIEW `APC_SUBTOTAL_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'monday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_monday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'monday') 
    as opening_fee_monday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'tuesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_tuesday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'tuesday') 
    as opening_fee_tuesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'wednesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_wednesday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'wednesday') 
    as opening_fee_wednesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'thursday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_thursday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'thursday') 
    as opening_fee_thursday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'friday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_friday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'friday') 
    as opening_fee_friday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'saturday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_saturday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'saturday') 
    as opening_fee_saturday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_total,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) ) 
    as opening_fee_total
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_CURRENT_CUSTOMER_BY_LOAN_VIEW`
-- SIRVE PARA TRAER LOS PRESTAMOS CON SUS CLIENTES, AVALES Y ASESORES.
-- 
-- --------------------------------------------------------------------
CREATE OR REPLACE VIEW `APC_CURRENT_CUSTOMER_BY_LOAN_VIEW` AS
SELECT 
loan.id AS id_loan,
usr.id AS id_user,
usr_by_office.id_office AS id_office,
loan_type.payment AS payment,
CONCAT(
		CASE
			WHEN customer.first_name IS NOT NULL AND customer.first_name != ''
			THEN CONCAT(SUBSTR(UPPER(customer.first_name), 1, 1),SUBSTR(LOWER(customer.first_name), 2))
			ELSE ''
		END,
        CASE
			WHEN customer.second_name IS NOT NULL AND customer.second_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(customer.second_name), 1, 1),SUBSTR(LOWER(customer.second_name), 2))
			ELSE ''
		END,
        CASE
			WHEN customer.last_name IS NOT NULL AND customer.last_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(customer.last_name), 1, 1),SUBSTR(LOWER(customer.last_name), 2))
			ELSE ''
		END,
        CASE
			WHEN customer.middle_name IS NOT NULL AND customer.middle_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(customer.middle_name), 1, 1),SUBSTR(LOWER(customer.middle_name), 2))
			ELSE ''
		END
    ) AS customer_name,
    CONCAT(
		CASE
			WHEN endorsement.first_name IS NOT NULL AND endorsement.first_name != ''
			THEN CONCAT(SUBSTR(UPPER(endorsement.first_name), 1, 1),SUBSTR(LOWER(endorsement.first_name), 2))
			ELSE ''
		END,
        CASE
			WHEN endorsement.second_name IS NOT NULL AND endorsement.second_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(endorsement.second_name), 1, 1),SUBSTR(LOWER(endorsement.second_name), 2))
			ELSE ''
		END,
        CASE
			WHEN endorsement.last_name IS NOT NULL AND endorsement.last_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(endorsement.last_name), 1, 1),SUBSTR(LOWER(endorsement.last_name), 2))
			ELSE ''
		END,
        CASE
			WHEN endorsement.middle_name IS NOT NULL AND endorsement.middle_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(endorsement.middle_name), 1, 1),SUBSTR(LOWER(endorsement.middle_name), 2))
			ELSE ''
		END
    ) AS endorsement_name,
    loan.loan_status AS loan_status,
    loan.created_on AS created_on,
    route.route_name AS route_name
FROM APC_LOAN loan
INNER JOIN APC_PEOPLE customer ON loan.id_customer = customer.id
INNER JOIN APC_PEOPLE endorsement ON loan.id_endorsement = endorsement.id
INNER JOIN APC_LOAN_TYPE loan_type ON loan.id_loan_type = loan_type.id
INNER JOIN APC_LOAN_BY_USER loan_by_user ON loan.id = loan_by_user.id_loan
INNER JOIN APC_USER usr ON loan_by_user.id_user = usr.id
INNER JOIN APC_USER_BY_OFFICE usr_by_office ON usr.id = usr_by_office.id_user
INNER JOIN APC_ROUTE route ON loan.id_route = route.id
WHERE loan.loan_status IN('APPROVED','PENDING','PENDING_RENOVATION','TO_DELIVERY')
ORDER BY usr.id, loan.created_on;
-- --------------------------------------------------------------------
-- 
-- Estructura para la vista `APC_AVAILABLES_OWNERS_VIEW`
-- REGRESA LOS USUARIOS ACTIVOS DE TIPO MOBILE AND BOTH.
-- 
-- --------------------------------------------------------------------
CREATE OR REPLACE VIEW `APC_AVAILABLES_OWNERS_VIEW` AS
SELECT 
usr.id AS id_user,
usr_by_office.id_office AS id_office,
usr.user_name AS user_name,
CONCAT(
		CASE
			WHEN human_resource.first_name IS NOT NULL AND human_resource.first_name != ''
			THEN CONCAT(SUBSTR(UPPER(human_resource.first_name), 1, 1),SUBSTR(LOWER(human_resource.first_name), 2))
			ELSE ''
		END,
        CASE
			WHEN human_resource.second_name IS NOT NULL AND human_resource.second_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(human_resource.second_name), 1, 1),SUBSTR(LOWER(human_resource.second_name), 2))
			ELSE ''
		END,
        CASE
			WHEN human_resource.last_name IS NOT NULL AND human_resource.last_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(human_resource.last_name), 1, 1),SUBSTR(LOWER(human_resource.last_name), 2))
			ELSE ''
		END,
        CASE
			WHEN human_resource.middle_name IS NOT NULL AND human_resource.middle_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(human_resource.middle_name), 1, 1),SUBSTR(LOWER(human_resource.middle_name), 2))
			ELSE ''
		END
    ) AS full_name
FROM APC_USER usr
INNER JOIN APC_HUMAN_RESOURCE human_resource ON usr.id_human_resource = human_resource.id
INNER JOIN APC_USER_BY_OFFICE usr_by_office ON usr.id = usr_by_office.id_user
WHERE usr.user_status = 'ENEBLED' AND
usr.user_type IN ('MOBILE','BOTH') AND
usr.certifier = 'DISABLED';

CREATE OR REPLACE VIEW `APC_COBRANZA_LAST_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' AND ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'monday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_monday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'tuesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_tuesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'wednesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_wednesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'thursday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_thursday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'friday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_friday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'saturday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_saturday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_total
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';

CREATE OR REPLACE VIEW `APC_COBRANZA_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' AND ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'monday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_monday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'tuesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_tuesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'wednesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_wednesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'thursday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_thursday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'friday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_friday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'saturday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_saturday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6' and ld.id_user = u.id 
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_total
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';

CREATE OR REPLACE VIEW `APC_ADVANCE_USER_DAILY_DASHBOARD_VIEW` AS 
SELECT 
	ate.id,
	CONCAT(ahr.first_name, ' ' , ahr.last_name) as user_name,
    ate.total_expected,
    (ate.total_expected - (SELECT COUNT(id) FROM APC_LOAN_BY_USER_VIEW where user_id = ate.id_user)) as total_now,
    CASE WHEN (ate.total_expected - (SELECT COUNT(id) FROM APC_LOAN_BY_USER_VIEW where user_id = ate.id_user)) = 0 
    THEN 0 
    ELSE 
    ((ate.total_expected - (SELECT COUNT(id) FROM APC_LOAN_BY_USER_VIEW where user_id = ate.id_user)) * 100) / ate.total_expected
    END 
    as porcentaje,
    ate.id_office,
    ate.id_user,
    (SELECT IF(ISNULL(SUM(ate2.total_expected_payment)),0,SUM(ate2.total_expected_payment)) FROM APC_TOTAL_EXPECTED_PAYMENT_DAILY_BY_USER ate2 
    where WEEK(DATE(ate2.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(DATE(ate2.created_on)) = (SELECT YEAR(CURDATE())) AND ate2.id_user = ate.id_user) 
    as total_expected_week,
    (SELECT IF(ISNULL(SUM(ald.payment_amount)),0,SUM(ald.payment_amount)) FROM APC_LOAN_DETAIL ald 
    where WEEK(Date(ald.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(Date(ald.created_on)) = (SELECT YEAR(CURDATE())) AND ald.id_user = ate.id_user 
    AND ald.loan_details_type IN ('PAYMENT', 'RENOVATION_PAYMENT', 'TRANSFER')) 
    as total_reported_week,
(
		select 
        IF(ISNULL(
				SUM(
				CASE WHEN l.loan_status IN ('PENDING_RENOVATION', 'FINISH') then 0 ELSE 
			((lt.payment_daily * (SELECT COUNT(ldFaltante.id) FROM APC_LOAN_DETAIL ldFaltante 
			WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = WEEK(CURDATE(),1) AND 
			YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) 
			AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE'))) 
			- (SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
			FROM APC_LOAN_DETAIL ldLunes 
			WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
			YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
			AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER'))) END
			)
            ),0,
            SUM(
				CASE WHEN l.loan_status IN ('PENDING_RENOVATION', 'FINISH') then 0 ELSE 
			((lt.payment_daily * (SELECT COUNT(ldFaltante.id) FROM APC_LOAN_DETAIL ldFaltante 
			WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = WEEK(CURDATE(),1) AND 
			YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) 
			AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE'))) 
			- (SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
			FROM APC_LOAN_DETAIL ldLunes 
			WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = WEEK(CURDATE(),1) AND 
			YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
			AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER'))) END
			)
            )
            as faltante
            
		FROM
		APC_LOAN l
		INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
		INNER JOIN APC_LOAN_BY_USER albu ON l.id = albu.id_loan
		WHERE albu.id_user = ate.id_user
		AND albu.owner_loan = 'CURRENT_OWNER'
		
)
as faltante,
    (SELECT IF(ISNULL(SUM(ald.payment_amount)),0,SUM(ald.payment_amount)) 
    FROM APC_LOAN_DETAIL ald 
    INNER JOIN APC_LOAN al ON ald.id_loan = al.id 
    INNER JOIN APC_LOAN_BY_USER albu ON al.id = albu.id_loan
    where WEEK(Date(ald.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(Date(ald.created_on)) = (SELECT YEAR(CURDATE())) 
    AND ald.loan_details_type IN ('RENOVATION_PAYMENT') AND albu.id_user = ate.id_user) as total_reported_renovation_week, 
    (SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) 
    FROM APC_LOAN al 
    INNER JOIN APC_LOAN_BY_USER albu ON al.id = albu.id_loan
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id
    WHERE albu.id_user = ate.id_user AND al.loan_status = 'APPROVED' AND 
    WEEK(Date(al.created_on),1) = (Select WEEK(CURDATE(),1)) and YEAR(Date(al.created_on)) = (SELECT YEAR(CURDATE()))
    ) as total_comision_fee,
    (SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND al.created_by = ate.id_user 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = (SELECT YEAR(CURDATE()))) 
    as colocation_approved,
    (SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'TO_DELIVERY' AND al.created_by = ate.id_user 
    AND WEEK(DATE(al.created_on),1) = WEEK(CURDATE(),1) AND 
    YEAR(Date(al.created_on)) = (SELECT YEAR(CURDATE()))) 
    as colocation_to_delivery
FROM 
	APC_TOTAL_EXPECTED_PAYMENT_DAILY_BY_USER ate
INNER JOIN APC_USER au ON au.id = ate.id_user
INNER JOIN APC_HUMAN_RESOURCE ahr ON ahr.id = au.id_human_resource
WHERE
	DATE(ate.created_on) = CURDATE() 
    AND ate.active_status = 'ENEBLED';

CREATE OR REPLACE VIEW `APC_SUBTOTAL_LAST_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'monday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_monday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'monday') 
    as opening_fee_monday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'tuesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_tuesday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'tuesday') 
    as opening_fee_tuesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'wednesday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_wednesday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'wednesday') 
    as opening_fee_wednesday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'thursday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_thursday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'thursday') 
    as opening_fee_thursday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'friday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_friday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'friday') 
    as opening_fee_friday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'saturday' 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_saturday,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) 
    AND LOWER(DAYNAME(DATE(al.created_on))) = 'saturday') 
    as opening_fee_saturday,
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_total,
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' AND albu.id_user = u.id 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) ) 
    as opening_fee_total,
(
		select 
        IF(ISNULL(
				SUM(
				CASE WHEN l.loan_status IN ('PENDING_RENOVATION', 'FINISH') then 0 ELSE 
			((lt.payment_daily * (SELECT COUNT(ldFaltante.id) FROM APC_LOAN_DETAIL ldFaltante 
			WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
			YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) 
			AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE'))) 
			- (SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
			FROM APC_LOAN_DETAIL ldLunes 
			WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
			YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
			AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER'))) END
			)
            ),0,
            SUM(
				CASE WHEN l.loan_status IN ('PENDING_RENOVATION', 'FINISH') then 0 ELSE 
			((lt.payment_daily * (SELECT COUNT(ldFaltante.id) FROM APC_LOAN_DETAIL ldFaltante 
			WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
			YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) 
			AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE'))) 
			- (SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
			FROM APC_LOAN_DETAIL ldLunes 
			WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
			YEAR(DATE(ldLunes.created_on)) = YEAR(CURDATE()) 
			AND ldLunes.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER'))) END
			)
            )
            as faltante
            
		FROM
		APC_LOAN l
		INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
		INNER JOIN APC_LOAN_BY_USER albu ON l.id = albu.id_loan
		WHERE albu.id_user = u.id
		AND albu.owner_loan = 'CURRENT_OWNER'
		
)
as faltante
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';

-- --------------------------------------------------------------------
-- 
-- Estructura para la tabla `APC_STABLE_GENERAL_BOX`
--
-- Totales de la caja de APC.
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

ALTER TABLE APC_LOAN_TYPE
ADD COLUMN `convenio` ENUM('ENEBLED','DISABLED') DEFAULT 'DISABLED'
AFTER `payment_sunday`;

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

CREATE OR REPLACE VIEW APC_HISTORY_LOAN_VIEW AS
SELECT 
l.id,
CONCAT(pc.first_name, ' ', pc.last_name) customerName,
CONCAT(pa.first_name, ' ', pa.last_name) endorsementName,
r.route_name routeName,
o.office_name officeName,
lt.payment montoPrestado,
l.amount_to_pay montoAPagar,
l.amount_paid montoPagado,
(l.amount_to_pay - l.amount_paid) saldoInsoluto,
(SELECT count(lfn.id) FROM APC_LOAN_FEE_NOTIFICATION lfn WHERE lfn.id_loan = l.id) numMultas,
CASE WHEN l.loan_status = 'PENDING' THEN 'Pendiente'
WHEN l.loan_status = 'FINISH' THEN 'Terminado' 
WHEN l.loan_status = 'APPROVED' THEN 'Aprobado'
WHEN l.loan_status = 'REJECTED' THEN 'Rechazado'
WHEN l.loan_status = 'PENDING_RENOVATION' THEN 'Pendiente de renovación'
WHEN l.loan_status = 'TO_DELIVERY' THEN 'Por liberar'
END as estatusPrestamo,
DATE(l.created_on) as fecha,
u.user_name nombreUsuario
FROM 
APC_LOAN l
INNER JOIN APC_PEOPLE pc ON l.id_customer = pc.id
INNER JOIN APC_PEOPLE pa ON l.id_endorsement = pa.id
INNER JOIN APC_ROUTE r ON l.id_route = r.id
INNER JOIN APC_OFFICE o ON r.id_office = o.id
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
INNER JOIN APC_USER u ON u.id = lbu.id_user
WHERE l.loan_status NOT IN ('DELETED') 
ORDER BY l.created_on DESC
-- -------------------------------------------------------------
-- 
-- Estructura para la vista `APC_STATS_FEES_VIEW`
--
CREATE OR REPLACE VIEW APC_FEES_VIEW  AS
SELECT
LD.ID, 
LD.id_user,
CONCAT(HR.first_name, ' ', HR.last_name) AS name,
LD.payment_amount AS total_fees,
LD.created_on
FROM apc_loan_detail LD
INNER JOIN apc_loan L ON LD.id_loan = L.id AND L.loan_status <> 'DELETED'
INNER JOIN apc_user U ON LD.id_user = U.id
INNER JOIN apc_human_resource HR ON U.id_human_resource = HR.id
WHERE loan_details_type = 'FEE'
-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_ADMINISTRATION_PERSON_SEARCH_VIEW`
--
CREATE OR REPLACE VIEW `APC_ADMINISTRATION_PERSON_SEARCH_VIEW` AS
SELECT
	people.id AS id, people.phone_home AS phone_home, people.address_home AS address_home, people.people_type AS people_type,
    people.id_route AS id_route, route.route_name  AS route_name, people.id_office AS id_office, office.office_name AS office_name,
    CONCAT(
		CASE
			WHEN people.first_name IS NOT NULL AND people.first_name != ''
			THEN CONCAT(SUBSTR(UPPER(people.first_name), 1, 1),SUBSTR(LOWER(people.first_name), 2))
			ELSE ''
		END,
        CASE
			WHEN people.second_name IS NOT NULL AND people.second_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(people.second_name), 1, 1),SUBSTR(LOWER(people.second_name), 2))
			ELSE ''
		END,
        CASE
			WHEN people.last_name IS NOT NULL AND people.last_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(people.last_name), 1, 1),SUBSTR(LOWER(people.last_name), 2))
			ELSE ''
		END,
        CASE
			WHEN people.middle_name IS NOT NULL AND people.middle_name != ''
			THEN CONCAT(' ',SUBSTR(UPPER(people.middle_name), 1, 1),SUBSTR(LOWER(people.middle_name), 2))
			ELSE ''
		END
    ) AS person_search
FROM APC_PEOPLE people
INNER JOIN APC_OFFICE office ON people.id_office = office.id
INNER JOIN APC_ROUTE route ON people.id_route = route.id
WHERE 
	people.active_status = 'ENEBLED'
ORDER BY office_name, route_name, person_search;
-- --------------------------------------------------------------------
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- Formula es:
-- monto_pagado >= a FLOOR(payment_total  * .7728)
-- y
-- monto_pagado  < a FLOOR(payment_total  * .8182)
-- El pago puede renovarce segun las reglas y si se acpeta en este caso 
--    se deberán condonar pago 21 y 22.
-- El pago 19 y 20 se cobrará cuando se entregue la renovación.
/*
SELECT 
	FLOOR(payment_total  * .7728) as inicio,
    
	FLOOR(payment_total  * .8182) as fin
FROM APC_LOAN_TYPE WHERE id_office = 'e0f1a2fc-7d1f-11ea-af3e-28f659da398e' ORDER BY payment;

 -- SEGUNDO CASO PAGO 20 se condona 21 y se deberá pagar el 22

SELECT 
	FLOOR(payment_total  * 0.8637) as inicio,
    
	FLOOR(payment_total  * 0.9091) as fin
FROM APC_LOAN_TYPE WHERE id_office = 'e0f1a2fc-7d1f-11ea-af3e-28f659da398e' ORDER BY payment;
*/


-- VISTA RESPALDADA
-- CREATE ALGORITHM=UNDEFINED DEFINER=`AlphaCJZL`@`%` SQL SECURITY DEFINER VIEW `APC_LOAN_BY_USER_VIEW` AS select concat(`l`.`id`,`u`.`id`) AS `id`,`u`.`id` AS `user_id`,concat((case when ((`cstmr`.`first_name` is not null) and (`cstmr`.`first_name` <> '')) then concat(substr(upper(`cstmr`.`first_name`),1,1),substr(lower(`cstmr`.`first_name`),2),' ') else '' end),(case when ((`cstmr`.`second_name` is not null) and (`cstmr`.`second_name` <> '')) then concat(substr(upper(`cstmr`.`second_name`),1,1),substr(lower(`cstmr`.`second_name`),2),' ') else '' end),(case when ((`cstmr`.`last_name` is not null) and (`cstmr`.`last_name` <> '')) then concat(substr(upper(`cstmr`.`last_name`),1,1),substr(lower(`cstmr`.`last_name`),2)) else '' end)) AS `customer_name`,`cstmr`.`address_home` AS `customer_address_home`,`cstmr`.`address_business` AS `customer_address_business`,`cstmr`.`company_name` AS `company_name`,`cstmr`.`thumbnail` AS `customer_thumbnail`,concat((case when ((`ndrsmnt`.`first_name` is not null) and (`ndrsmnt`.`first_name` <> '')) then concat(substr(upper(`ndrsmnt`.`first_name`),1,1),substr(lower(`ndrsmnt`.`first_name`),2),' ') else '' end),(case when ((`ndrsmnt`.`second_name` is not null) and (`ndrsmnt`.`second_name` <> '')) then concat(substr(upper(`ndrsmnt`.`second_name`),1,1),substr(lower(`ndrsmnt`.`second_name`),2),' ') else '' end),(case when ((`ndrsmnt`.`last_name` is not null) and (`ndrsmnt`.`last_name` <> '')) then concat(substr(upper(`ndrsmnt`.`last_name`),1,1),substr(lower(`ndrsmnt`.`last_name`),2)) else '' end)) AS `endorsement_name`,`ndrsmnt`.`address_home` AS `endorsement_address_home`,`ndrsmnt`.`thumbnail` AS `endorsement_thumbnail`,`ndrsmnt`.`phone_home` AS `endorsement_phone_home`,if(((`l`.`amount_to_pay` - `l`.`amount_paid`) >= `lt`.`payment_daily`),`lt`.`payment_daily`,(`l`.`amount_to_pay` - `l`.`amount_paid`)) AS `payment_daily`,`lt`.`loan_fee` AS `loan_fee`,`lbu`.`order_in_list` AS `order_in_list`,(select count(`APC_LOAN_FEE_NOTIFICATION`.`notification_number`) from `APC_LOAN_FEE_NOTIFICATION` where (`APC_LOAN_FEE_NOTIFICATION`.`id_loan` = `l`.`id`)) AS `notification_number`,if((`l`.`amount_paid` >= (select floor((`lt`.`payment_total` * 0.6364)))),(case when ((select count(`APC_LOAN_FEE_NOTIFICATION`.`notification_number`) AS `total` from `APC_LOAN_FEE_NOTIFICATION` where (`APC_LOAN_FEE_NOTIFICATION`.`id_loan` = `l`.`id`)) < 4) then 'ENEBLED' when (((select count(`APC_LOAN_FEE_NOTIFICATION`.`notification_number`) AS `total` from `APC_LOAN_FEE_NOTIFICATION` where (`APC_LOAN_FEE_NOTIFICATION`.`id_loan` = `l`.`id`)) between 4 and 5) and (`lt`.`payment` > 1000)) then 'ENEBLED' else 'DISABLED' end),'DISABLED') AS `renovation`,(select (`APC_LOAN`.`amount_to_pay` - `APC_LOAN`.`amount_paid`) from `APC_LOAN` where (`APC_LOAN`.`id` = `l`.`id`)) AS `max_amount_to_pay` from (((((`APC_LOAN_BY_USER` `lbu` join `APC_LOAN` `l` on((`lbu`.`id_loan` = `l`.`id`))) join `APC_LOAN_TYPE` `lt` on((`l`.`id_loan_type` = `lt`.`id`))) join `APC_PEOPLE` `cstmr` on((`l`.`id_customer` = `cstmr`.`id`))) join `APC_PEOPLE` `ndrsmnt` on((`l`.`id_endorsement` = `ndrsmnt`.`id`))) join `APC_USER` `u` on((`lbu`.`id_user` = `u`.`id`))) where ((`l`.`loan_status` in ('APPROVED','PENDING','TO_DELIVERY')) and (`l`.`id` = (case when (((select count(`APC_LOAN_DETAIL`.`id`) from `APC_LOAN_DETAIL` where (`APC_LOAN_DETAIL`.`id_loan` = `l`.`id`)) = 0) and (cast(`l`.`last_updated_on` as date) < curdate())) then `l`.`id` when (((select count(`APC_LOAN_DETAIL`.`id`) from `APC_LOAN_DETAIL` where ((`APC_LOAN_DETAIL`.`id_loan` = `l`.`id`) and (`APC_LOAN_DETAIL`.`reference_number` = `l`.`last_reference_number`) and (cast(`APC_LOAN_DETAIL`.`created_on` as date) < curdate()))) > 0) and (cast(`l`.`last_updated_on` as date) < curdate())) then `l`.`id` else '' end)) and (`lbu`.`owner_loan` = 'CURRENT_OWNER') and (0 <> (case when (lower(dayname(curdate())) = 'monday') then ('monday' = `lt`.`payment_monday`) when (lower(dayname(curdate())) = 'tuesday') then ('tuesday' = lower(`lt`.`payment_tuesday`)) when (lower(dayname(curdate())) = 'wednesday') then ('wednesday' = lower(`lt`.`payment_wednesday`)) when (lower(dayname(curdate())) = 'thursday') then ('thursday' = lower(`lt`.`payment_thursday`)) when (lower(dayname(curdate())) = 'friday') then ('friday' = lower(`lt`.`payment_friday`)) when (lower(dayname(curdate())) = 'saturday') then ('saturday' = lower(`lt`.`payment_saturday`)) when (lower(dayname(curdate())) = 'sunday') then ('sunday' = lower(`lt`.`payment_sunday`)) else false end)));
-- NO BORRAR vista APC_LOAN_BY_USER_VIEW
