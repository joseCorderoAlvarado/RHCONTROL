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

-- --------------------------------------------------------

--
-- Base de datos: Select `apo_pro_com_april_ten` DB
--
USE `apo_pro_com_april_ten`;
-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `APC_ROLE`
--
INSERT INTO APC_ROLE(id,role_name,active_status,created_by)
VALUES
('56dd386a-88c8-11ea-a6b0-82987069bf80','DIRECTOR GENERAL','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('b9c76e82-88c8-11ea-a6b0-82987069bf80','GERENCIA','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('e7fbf750-88c8-11ea-a6b0-82987069bf80','SUPERVISOR REGIONAL','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('ee39d1aa-88c8-11ea-a6b0-82987069bf80','CERTIFICADOR Y FACILITADOR DE CREDITO','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('3e07c998-a81e-11ea-aa38-77eb3547c70f','JEFE DE ASESORES','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('b5b506f9-3ca4-4ceb-b938-dc58da3f039b','ASESORES DE CREDITO Y COBRANZA','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('e67360db-5953-46ba-9fe1-d71e30ae9b59','JURIDICO','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('b691aea2-3ba5-4e4d-a256-37f749a310bd','JEFE DE PERSONAL PRO FAM','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('44cb12f9-19e6-439a-9cd9-abdf4af38651','EJECUTIVO DE RUTA PRO FAM','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('23259ba5-7a0e-4503-a470-b8bd900635e1','AUXILIAR DE RUTA PRO FAM.','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `APC_HUMAN_RESOURCE`
--
INSERT INTO APC_HUMAN_RESOURCE(id,first_name,second_name,last_name,middle_name,birthdate,avatar,human_resource_status, id_role ,created_by)
VALUES
('13c588a6-7d1b-11ea-af3e-28f659da398e','Ruben','','Campos','Bueno',str_to_date('1977-03-02','%Y-%m-%d'),'images/avatar1.png','ENEBLED','b9c76e82-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('c021214a-8bc7-11ea-b45c-c7b846343364','Hector','Alonso','Ubaldo','Nolasco',str_to_date('1977-03-02','%Y-%m-%d'),'https://freisteller24.eu/wp-content/uploads/2017/11/26603674-1200x1200.jpg','ENEBLED','b5b506f9-3ca4-4ceb-b938-dc58da3f039b','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('d2869da6-8bc7-11ea-b45c-c7b846343364','Juan','Carlos','Campos','Campos',str_to_date('1977-03-02','%Y-%m-%d'),'https://freisteller24.eu/wp-content/uploads/2017/11/97783667-1200x1200.jpg','ENEBLED','b5b506f9-3ca4-4ceb-b938-dc58da3f039b','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('088d7268-8bc7-11ea-b45c-c7b846343364','Avatar 4','Avatar 4','Campos','Campos',str_to_date('1977-03-02','%Y-%m-%d'),'https://freisteller24.eu/wp-content/uploads/2017/10/573675226-1200x1200.jpg','ENEBLED','ee39d1aa-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `APC_OFFICE`
--
INSERT INTO APC_OFFICE(id,office_name,office_status,created_by)
VALUES
-- Tepic
('caef3a64-7d1f-11ea-af3e-28f659da398e','TEPIC','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Add  APC_HUMAN_RESOURCE_BY_OFFICE
--
INSERT INTO APC_HUMAN_RESOURCE_BY_OFFICE(id,id_human_resource,id_office,application_owner,created_by)
VALUES
-- Ruben
('e540fd40-8246-11ea-9f9f-a9ab5ed40dc4','13c588a6-7d1b-11ea-af3e-28f659da398e','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Hector Tepic
('14affe8c-8247-11ea-9f9f-a9ab5ed40dc4','c021214a-8bc7-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Juan Tepic
('f41be05a-8246-11ea-9f9f-a9ab5ed40dc4','d2869da6-8bc7-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Certificador Tepic
('0a8cf342-8247-11ea-9f9f-a9ab5ed40dc4','088d7268-8bc7-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `APC_USER`
--
INSERT INTO APC_USER(id,id_human_resource,user_name,pwd,user_type,user_status,application_owner,created_by,certifier)
VALUES
-- Ruben WEB
('5751074e-7d1b-11ea-af3e-28f659da398e','13c588a6-7d1b-11ea-af3e-28f659da398e','ejecutivo','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','WEB','ENEBLED','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Hector MOBILE
('092a95d8-7db8-11ea-9b1f-500320958bf8','c021214a-8bc7-11ea-b45c-c7b846343364','ruta3','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','MOBILE','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Juan MOBILE
('0dc7c246-7db8-11ea-9b1f-500320958bf8','d2869da6-8bc7-11ea-b45c-c7b846343364','ruta7','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','MOBILE','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Certificador MOBILE
('67b3081e-8bc9-11ea-b45c-c7b846343364','088d7268-8bc7-11ea-b45c-c7b846343364','certificador','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','MOBILE','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','ENEBLED');
-- --------------------------------------------------------
--
-- Add  APC_USER_BY_OFFICE
--
INSERT INTO APC_USER_BY_OFFICE(id,id_user,id_office,user_by_office_status,application_owner,created_by)
VALUES
-- Ruben Tepic
('a742dfe8-7d20-11ea-af3e-28f659da398e','5751074e-7d1b-11ea-af3e-28f659da398e','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Hector Tepic
('d855f570-7dbb-11ea-9b1f-500320958bf8','092a95d8-7db8-11ea-9b1f-500320958bf8','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Juan Tepic
('eca3f824-7dbb-11ea-9b1f-500320958bf8','0dc7c246-7db8-11ea-9b1f-500320958bf8','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Certificador Tepic
('e5a44222-7dbb-11ea-9b1f-500320958bf8','67b3081e-8bc9-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Add  APC_PERMISSION
--
INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`) 
VALUES
('7a6cbba2-7dba-11ea-9b1f-500320958bf8','public.access', 'public.access.description', 'public.access.path', 0, 10,'PUBLIC', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('001055b6-804f-11ea-bd33-54754d23c678','system.employee', 'system.employee.description', 'system.employee.path', 10, 10,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('7f116c66-7dba-11ea-9b1f-500320958bf8','system.user.create', 'system.user.create.description', 'system.user.create.path', 10, 20,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('87d3d32a-7dba-11ea-9b1f-500320958bf8','system.user.admin', 'system.user.admin.description', 'system.user.admin.path', 10, 30,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('8c54a1f4-7dba-11ea-9b1f-500320958bf8','system.user.access', 'system.user.access.description', 'system.user.access.path', 10, 40,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('3697f4e3-6a7d-46df-9618-60c632e3e472','catalog.role', 'catalog.role.description', 'catalog.role.path', 10, 50,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('572bccc2-4848-4dad-b63c-ddf6f29c14f7','catalog.typeLoan', 'catalog.typeLoan.description', 'catalog.typeLoan.path', 10, 60,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('7f139dd9-a1fb-42a4-866c-e70b4d84587a','catalog.route', 'catalog.route.description', 'catalog.route.path', 10, 70,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('58ef9e12-13b3-4a7d-a5ad-fd0db3557d3c','admin.customer', 'admin.customer.description', 'admin.customer.path', 10, 80,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('45f2cd84-98df-4991-be77-3f22dfa7d4b2','admin.endorsement', 'admin.endorsement.description', 'admin.endorsement.path', 10, 90,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('e492bbf5-b25e-4ff2-b126-dc92a733e921','admin.loan', 'admin.loan.description', 'admin.loan.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('fee9d1d9-3961-4dfa-a8e2-56eccae1e347','admin.transfer', 'admin.transfer.description', 'admin.transfer.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('7a8d71b0-1d2b-43ae-b7f7-fd048929ae8e','admin.moneyDaily', 'admin.moneyDaily.description', 'admin.moneyDaily.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('55a79bce-9466-44f8-8a13-7b69f176b80b','admin.closingDay', 'admin.closingDay.description', 'admin.closingDay.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('04d9cf0f-b8e9-46fb-9b77-b301dec9c533','admin.otherExpense', 'admin.otherExpense.description', 'admin.otherExpense.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('f01aeaac-8b18-4eb5-95ae-d02ae5a0716d','admin.goal', 'admin.goal.description', 'admin.goal.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('6c2ec1ce-9163-42b4-bbf1-8448e3894d55','admin.bonus', 'admin.bonus.description', 'admin.bonus.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('9067f64e-b2eb-4361-9c9b-c6dccc29e67f','admin.advance', 'admin.advance.description', 'admin.advance.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('de5d48ac-2242-4937-95e2-1140b987b8c2','system.office', 'system.office.description', 'system.office.path', 10, 110,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- Permission by role
INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`, `parent_name`) 
VALUES
-- employee
('dcfafbee-82a1-11ea-a6b6-200fe86028a8','system.employee.add', 'system.employee.add.description', 'system.employee.add.path', 10, 11,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.employee'),
('e2ea92a8-82a1-11ea-a6b6-200fe86028a8','system.employee.enebled', 'system.employee.enebled.description', 'system.employee.enebled.path', 10, 12,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.employee'),
('eb530786-82a1-11ea-a6b6-200fe86028a8','system.employee.disabled', 'system.employee.disabled.description', 'system.employee.disabled.path', 10, 13,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.employee'),
('f2245ed4-82a1-11ea-a6b6-200fe86028a8','system.employee.deleted', 'system.employee.deleted.description', 'system.employee.deleted.path', 10, 14,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.employee'),
('fec3b7a2-82a1-11ea-a6b6-200fe86028a8','system.employee.updated', 'system.employee.updated.description', 'system.employee.updated.path', 10, 15,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.employee'),
-- user create
('065b2432-82a2-11ea-a6b6-200fe86028a8','system.user.create.permission', 'system.user.create.permission.description', 'system.user.create.permission.path', 10, 21,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.create'),
('10d740d0-82a2-11ea-a6b6-200fe86028a8','system.user.admin.enebled', 'system.user.admin.enebled.description', 'system.user.admin.enebled.path', 10, 31,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
('36b619fe-82fa-11ea-a6b6-200fe86028a8','system.user.admin.disabled', 'system.user.admin.disabled.description', 'system.user.admin.disabled.path', 10, 32,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
('2fcf2806-82fa-11ea-a6b6-200fe86028a8','system.user.admin.deleted', 'system.user.admin.deleted.description', 'system.user.admin.deleted.path', 10, 33,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
('282079e8-82fa-11ea-a6b6-200fe86028a8','system.user.admin.updated', 'system.user.admin.updated.description', 'system.user.admin.updated.path', 10, 34,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
('176d3744-82fa-11ea-a6b6-200fe86028a8','system.user.admin.pwd', 'system.user.admin.pwd.description', 'system.user.admin.pwd.path', 10, 35,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
('1fecd260-82a2-11ea-a6b6-200fe86028a8','system.user.admin.avatar', 'system.user.admin.avatar.description', 'system.user.admin.avatar.path', 10, 36,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
-- office
('c5ef3b18-6cc2-4d62-88ba-41304c6ae9c8','system.office.add', 'system.office.add.description', 'system.office.add.path', 10, 111,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
('037ea752-c33f-4bfe-ae84-da867de0d7cd','system.office.updated', 'system.office.updated.description', 'system.office.updated.path', 10, 112,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin'),
('65399669-44da-4e17-9217-c6b89c1f0a61','system.office.deleted', 'system.office.deleted.description', 'system.office.deleted.path', 10, 113,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','system.user.admin');

INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`, `parent_name`) 
VALUES
-- role
('96f78612-1e06-47cf-a282-f4f1a14f8e0d','catalog.role.add', 'catalog.role.add.description', 'catalog.role.add.path', 10, 51,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.role'),
('1c8175f5-dcde-4611-b2d7-9b79a68b3f0c','catalog.role.updated', 'catalog.role.updated.description', 'catalog.role.updated.path', 10, 52,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.role'),
('4c11f842-6c29-4d18-a58c-595df41afaa0','catalog.role.deleted', 'catalog.role.deleted.description', 'catalog.role.deleted.path', 10, 53,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.role'),
-- type loan
('29445731-3e36-427b-a14a-ee53b9699582','catalog.typeLoan.add', 'catalog.typeLoan.add.description', 'catalog.typeLoan.add.path', 10, 61,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.typeLoan'),
('39482063-a243-490b-bb78-0c0599bce30e','catalog.typeLoan.updated', 'catalog.typeLoan.updated.description', 'catalog.typeLoan.updated.path', 10, 62,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.typeLoan'),
('c573acd1-cb14-4464-8bbc-b962dbb0bc62','catalog.typeLoan.deleted', 'catalog.typeLoan.deleted.description', 'catalog.typeLoan.deleted.path', 10, 63,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.typeLoan'),
-- route
('ab812966-4df0-4462-a9e2-a2c364cf97f1','catalog.route.add', 'catalog.route.add.description', 'catalog.route.add.path', 10, 71,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.route'),
('886ab08d-9494-4ae4-8635-4a6b4363cdf7','catalog.route.updated', 'catalog.route.updated.description', 'catalog.route.updated.path', 10, 72,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.route'),
('629c0be7-b3a9-49f5-9c20-3f6681a4b6d3','catalog.route.deleted', 'catalog.route.deleted.description', 'catalog.route.deleted.path', 10, 73,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','catalog.route');

INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`, `parent_name`) 
VALUES
-- customer
('65928cb3-f463-4266-9d29-bc29c560c891','admin.customer.add', 'admin.customer.add.description', 'admin.customer.add.path', 10, 81,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.customer'),
('b209327b-f568-4783-8da1-7d45b88fd65b','admin.customer.updated', 'admin.customer.updated.description', 'admin.customer.updated.path', 10, 82,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.customer'),
('6aeb69ff-bb96-4ab9-9d07-8a3b237cfc9e','admin.customer.deleted', 'admin.customer.deleted.description', 'admin.customer.deleted.path', 10, 83,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.customer'),
-- endorsement
('8bee00dc-199f-43c5-8e83-5d670502552b','admin.endorsement.add', 'admin.endorsement.add.description', 'admin.endorsement.add.path', 10, 91,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.endorsement'),
('d16b9b0b-7c4d-456a-a257-ed67586226b0','admin.endorsement.updated', 'admin.endorsement.updated.description', 'admin.endorsement.updated.path', 10, 92,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.endorsement'),
('b57da38c-ba69-4d1e-944f-0dd1f11d816f','admin.endorsement.deleted', 'admin.endorsement.deleted.description', 'admin.endorsement.deleted.path', 10, 93,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.endorsement'),
-- loan
('cca7b7e8-2d41-471b-bb81-c4ca518294dc','admin.loan.add', 'admin.loan.add.description', 'admin.loan.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.loan'),
('55fd99c6-8110-4604-901b-0ed95ca31132','admin.loan.updated', 'admin.loan.updated.description', 'admin.loan.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.loan'),
('1e98037f-1950-417e-be11-39b51203c409','admin.loan.deleted', 'admin.loan.deleted.description', 'admin.loan.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.loan'),
-- transfer
('0d582b8a-2820-4a58-95ca-37484daa8304','admin.transfer.add', 'admin.transfer.add.description', 'admin.transfer.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.transfer'),
('f8bb6db8-acd1-4c5e-845a-ef62f16c7b2d','admin.transfer.updated', 'admin.transfer.updated.description', 'admin.transfer.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.transfer'),
('ae782c52-1e20-40a3-88cf-28f2fb7d7583','admin.transfer.deleted', 'admin.transfer.deleted.description', 'admin.transfer.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.transfer'),
-- money daily
('d14f5077-ecd9-467e-b274-8674e1955667','admin.moneyDaily.add', 'admin.moneyDaily.add.description', 'admin.moneyDaily.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.moneyDaily'),
('a8f492bf-0a8b-4a4e-9d61-0de2b7153334','admin.moneyDaily.updated', 'admin.moneyDaily.updated.description', 'admin.moneyDaily.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.moneyDaily'),
('285860be-92fd-4c7a-bac2-eec1f530d6d9','admin.moneyDaily.deleted', 'admin.moneyDaily.deleted.description', 'admin.moneyDaily.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.moneyDaily'),
-- closing day
('8ab4bea2-3e00-4474-bf94-e01f1ed6b52c','admin.closingDay.add', 'admin.closingDay.add.description', 'admin.closingDay.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.closingDay'),
('89d55a97-8bc4-4430-8e8d-ce8d15851695','admin.closingDay.updated', 'admin.closingDay.updated.description', 'admin.closingDay.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.closingDay'),
('4e8ab1c5-1889-45b3-8550-101f300b2e70','admin.closingDay.deleted', 'admin.closingDay.deleted.description', 'admin.closingDay.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.closingDay'),
-- other expense
('f8b6306b-166d-48b8-8626-0e8a92970c17','admin.otherExpense.add', 'admin.otherExpense.add.description', 'admin.otherExpense.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.otherExpense'),
('2dbc0faa-2cd0-4490-a5af-e6a8eb4eb132','admin.otherExpense.updated', 'admin.otherExpense.updated.description', 'admin.otherExpense.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.otherExpense'),
('aa7c8eca-2117-45a0-b7ce-6d78599b0a66','admin.otherExpense.deleted', 'admin.otherExpense.deleted.description', 'admin.otherExpense.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.otherExpense'),
-- goal
('2e3e6bc1-6baf-4fde-9cc2-c60cd4c1d2f3','admin.goal.add', 'admin.goal.add.description', 'admin.goal.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.goal'),
('6add466f-4a1d-464a-a96c-2aecc67087ab','admin.goal.updated', 'admin.goal.updated.description', 'admin.goal.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.goal'),
('cd3306f9-b713-4995-a8bf-7585e42c2ca0','admin.goal.deleted', 'admin.goal.deleted.description', 'admin.goal.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.goal'),
-- bonus
('71e44e75-91e8-4a55-8d36-d24e2a645f10','admin.bonus.add', 'admin.bonus.add.description', 'admin.bonus.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.bonus'),
('14b6bf11-cc3b-41d9-b589-7c2aebc5dbeb','admin.bonus.updated', 'admin.bonus.updated.description', 'admin.bonus.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.bonus'),
('9f5d7c8d-2115-4114-82a6-5b7329c3efc7','admin.bonus.deleted', 'admin.bonus.deleted.description', 'admin.bonus.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.bonus'),
-- advance
('961193c0-754e-4444-b281-7c62aacb3987','admin.advance.add', 'admin.advance.add.description', 'admin.advance.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.advance'),
('0fa64abd-3e2a-4a42-96cf-1672bdd51086','admin.advance.updated', 'admin.advance.updated.description', 'admin.advance.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.advance'),
('6ee26a46-919b-4400-8837-31762892fa97','admin.advance.deleted', 'admin.advance.deleted.description', 'admin.advance.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.advance');

-- Entradas de caja general
INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`) 
VALUES
('1bd221ee-bfd2-49e1-899a-8456cc05e559','admin.expenseCompanyIn', 'admin.expenseCompanyIn.description', 'admin.expenseCompanyIn.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8');

INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`,`parent_name`) 
VALUES
('b855fbbc-e25a-4051-8789-9ec9af62ce3a','admin.expenseCompanyIn.add', 'admin.expenseCompanyIn.add.description', 'admin.expenseCompanyIn.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.expenseCompanyIn'),
('8165c4e7-eb55-4eb7-bb07-98e14234ad8b','admin.expenseCompanyIn.updated', 'admin.expenseCompanyIn.updated.description', 'admin.expenseCompanyIn.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.expenseCompanyIn'),
('292e5385-1368-4b8d-895d-638181e05e0d','admin.expenseCompanyIn.deleted', 'admin.expenseCompanyIn.deleted.description', 'admin.expenseCompanyIn.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.expenseCompanyIn');

-- Salidas de caja general
INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`) 
VALUES
('a5301073-05f2-4d80-bed9-e39e0739ee95','admin.expenseCompanyOut', 'admin.expenseCompanyOut.description', 'admin.expenseCompanyOut.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8');

INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`,`parent_name`) 
VALUES
('8d4985e8-c3e1-4534-9ebc-06d2f708398c','admin.expenseCompanyOut.add', 'admin.expenseCompanyOut.add.description', 'admin.expenseCompanyOut.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.expenseCompanyOut'),
('3d812774-98ec-4157-ab62-5a60a69f6ddd','admin.expenseCompanyOut.updated', 'admin.expenseCompanyOut.updated.description', 'admin.expenseCompanyOut.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.expenseCompanyOut'),
('c59fd63d-7658-40e9-82be-c2a75221e200','admin.expenseCompanyOut.deleted', 'admin.expenseCompanyOut.deleted.description', 'admin.expenseCompanyOut.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.expenseCompanyOut');

-- Cuadre de caja general
INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`) 
VALUES
('a18072cb-ff92-4763-977c-c604828ff4c7','admin.stableGeneralBox', 'admin.stableGeneralBox.description', 'admin.stableGeneralBox.path', 10, 100,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8');

INSERT INTO `APC_PERMISSION` (`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` , `permission_type`, `permission_status`,`created_by`,`parent_name`) 
VALUES
('4273bd52-7435-483b-a185-d6686c8fb3e7','admin.stableGeneralBox.add', 'admin.stableGeneralBox.add.description', 'admin.stableGeneralBox.add.path', 10, 101,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.stableGeneralBox'),
('2cceb95d-6d3c-4c67-8c9f-cedc75d4e293','admin.stableGeneralBox.updated', 'admin.stableGeneralBox.updated.description', 'admin.stableGeneralBox.updated.path', 10, 102,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.stableGeneralBox'),
('c46ebab5-904c-4cc1-8c77-49a9fded0fba','admin.stableGeneralBox.deleted', 'admin.stableGeneralBox.deleted.description', 'admin.stableGeneralBox.deleted.path', 10, 103,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.stableGeneralBox');

INSERT INTO APC_USER_BY_OFFICE_HAS_PERMISSION (id_user_by_office,id_permission,created_by)
SELECT  
-- Ruben Tepic
'a742dfe8-7d20-11ea-af3e-28f659da398e',
id,
'0dc7c246-7db8-11ea-9b1f-500320958bf8'
FROM  APC_PERMISSION;


INSERT INTO APC_USER_MOBILE_PREFERECE(id,id_user,preference_name,preference_value,created_by,created_on)
VALUES
-- Hector
('235e819e-8bbe-11ea-8c0e-beeb61238d59','092a95d8-7db8-11ea-9b1f-500320958bf8','ORDER_LIST','ALPHABETICALLY','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Juan
('e914e614-8bd0-11ea-b45c-c7b846343364','0dc7c246-7db8-11ea-9b1f-500320958bf8','ORDER_LIST','ALPHABETICALLY','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Certificador 
('ef48f296-8bd0-11ea-b45c-c7b846343364','67b3081e-8bc9-11ea-b45c-c7b846343364','ORDER_LIST','ALPHABETICALLY','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()); 
-- -------------------------
-- Logica de negocio de los
-- pagos de APC
-- -------------------------
-- FALTA CREAR RUTA
INSERT INTO APC_ROUTE(id,id_office,route_name,active_status,created_by, created_on)
VALUES
-- Tepic
('51b207a2-8e19-11ea-b65e-4e1376171215','caef3a64-7d1f-11ea-af3e-28f659da398e','Ruta 3 Tepic','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('55baf3ae-8e19-11ea-b65e-4e1376171215','caef3a64-7d1f-11ea-af3e-28f659da398e','Ruta 7 Tepic','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW());

INSERT INTO APC_LOAN_TYPE(id,loan_type_name,total_days, loan_fee, payment,payment_daily,payment_total, id_office,created_by, created_on,opening_fee)
VALUES
-- Para tepic
('db833bf0-8e5e-11ea-8ee4-e54bc704beac','Prestamo $1000', 22,50,1000,60,1320,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),20),
('c59e5bee-8dff-11ea-8745-07889553dd5f','Prestamo $2000', 22,50,2000,120,2640,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),40),
('dc255a16-8dff-11ea-8745-07889553dd5f','Prestamo $3000', 22,50,3000,180,3960,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),60),
('e7cc91c2-8dff-11ea-8745-07889553dd5f','Prestamo $4000', 22,50,4000,240,5280,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),80),
('f0cb05ba-8dff-11ea-8745-07889553dd5f','Prestamo $5000', 22,50,5000,300,6600,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),100),
('fdaa4318-8dff-11ea-8745-07889553dd5f','Prestamo $6000', 22,50,6000,360,7920,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),120),

('32af80cf-5961-4ed9-9bbb-c8c3d17151c0','Prestamo $7000', 22,50,7000,420,9240,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),140),
('a436bf31-8229-4817-b014-3a1c76bffe9d','Prestamo $8000', 22,50,8000,480,10560,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),160),
('f0f2fe2a-a7ec-45e6-80ea-f46a1e689584','Prestamo $9000', 22,50,9000,540,11880,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),180),
('4015d73b-6d05-4f69-a5f3-d446e3870f40','Prestamo $10000', 22,50,10000,600,13200,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),200);

INSERT INTO APC_HUMAN_RESOURCE_HAS_ROUTE(id_human_resource,id_route,created_by,created_on)
VALUES
-- Hector 
('c021214a-8bc7-11ea-b45c-c7b846343364','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Juan 
('d2869da6-8bc7-11ea-b45c-c7b846343364','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Certificador
('088d7268-8bc7-11ea-b45c-c7b846343364','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('088d7268-8bc7-11ea-b45c-c7b846343364','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW());

COMMIT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
