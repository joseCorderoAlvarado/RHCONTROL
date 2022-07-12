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
('13c588a6-7d1b-11ea-af3e-28f659da398e','Carlos','Janitzio','Zavala','Lopez',str_to_date('1977-03-02','%Y-%m-%d'),'images/avatar1.png','ENEBLED','b9c76e82-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('cb5e8bec-7db7-11ea-9b1f-500320958bf8','Oscar','Armando','Vargas','Cardenas',str_to_date('1977-03-02','%Y-%m-%d'),'images/avatar2.png','ENEBLED','b9c76e82-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('ebae5148-7db7-11ea-9b1f-500320958bf8','Ruben','Ruben','Campos','Campos',str_to_date('1977-03-02','%Y-%m-%d'),'images/avatar3.png','ENEBLED','b9c76e82-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('c021214a-8bc7-11ea-b45c-c7b846343364','Avatar 1','Avatar 1','Campos','Campos',str_to_date('1977-03-02','%Y-%m-%d'),'https://freisteller24.eu/wp-content/uploads/2017/11/26603674-1200x1200.jpg','ENEBLED','e7fbf750-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('d2869da6-8bc7-11ea-b45c-c7b846343364','Avatar 2','Avatar 2','Campos','Campos',str_to_date('1977-03-02','%Y-%m-%d'),'https://freisteller24.eu/wp-content/uploads/2017/11/97783667-1200x1200.jpg','ENEBLED','3e07c998-a81e-11ea-aa38-77eb3547c70f','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('088d7268-8bc7-11ea-b45c-c7b846343364','Avatar 4','Avatar 4','Campos','Campos',str_to_date('1977-03-02','%Y-%m-%d'),'https://freisteller24.eu/wp-content/uploads/2017/10/573675226-1200x1200.jpg','ENEBLED','e7fbf750-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('c687acca-8bc7-11ea-b45c-c7b846343364','Avatar 3','Avatar 3','Campos','Campos',str_to_date('1977-03-02','%Y-%m-%d'),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg','ENEBLED','ee39d1aa-88c8-11ea-a6b0-82987069bf80','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `APC_OFFICE`
--
INSERT INTO APC_OFFICE(id,office_name,office_status,created_by)
VALUES
-- Tepic
('caef3a64-7d1f-11ea-af3e-28f659da398e','TEPIC','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Compostela 
('e0f1a2fc-7d1f-11ea-af3e-28f659da398e','COMPOSTELA','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('a7fa34c1-f549-49d9-a262-4d61af50d8c4','PROYECTOS FAMILIARES','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
('1223d3a5-092e-49a5-9d94-f05af4720342','JURIDICO','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Add  APC_HUMAN_RESOURCE_BY_OFFICE
--
INSERT INTO APC_HUMAN_RESOURCE_BY_OFFICE(id,id_human_resource,id_office,application_owner,created_by)
VALUES
-- Janitzio GDL
('e540fd40-8246-11ea-9f9f-a9ab5ed40dc4','13c588a6-7d1b-11ea-af3e-28f659da398e','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Oscar Tepic
('14affe8c-8247-11ea-9f9f-a9ab5ed40dc4','cb5e8bec-7db7-11ea-9b1f-500320958bf8','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Ruben Tepic
('f41be05a-8246-11ea-9f9f-a9ab5ed40dc4','ebae5148-7db7-11ea-9b1f-500320958bf8','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Ruben GDL
('0a8cf342-8247-11ea-9f9f-a9ab5ed40dc4','ebae5148-7db7-11ea-9b1f-500320958bf8','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 1 Tepic
('0fc73388-8bc8-11ea-b45c-c7b846343364','c021214a-8bc7-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 2 Tepic
('2412e3d2-8bc8-11ea-b45c-c7b846343364','d2869da6-8bc7-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 4 GDL
('32ea4a62-8bc8-11ea-b45c-c7b846343364','088d7268-8bc7-11ea-b45c-c7b846343364','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 3 Tepic
('49662edc-8bc8-11ea-b45c-c7b846343364','c687acca-8bc7-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8');
-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `APC_USER`
--
INSERT INTO APC_USER(id,id_human_resource,user_name,pwd,user_type,user_status,application_owner,created_by,certifier)
VALUES
-- Janitzio WEB
('5751074e-7d1b-11ea-af3e-28f659da398e','13c588a6-7d1b-11ea-af3e-28f659da398e','direccion','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','WEB','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Oscar WEB
('092a95d8-7db8-11ea-9b1f-500320958bf8','cb5e8bec-7db7-11ea-9b1f-500320958bf8','direccion','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','WEB','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Ruben WEB
('0dc7c246-7db8-11ea-9b1f-500320958bf8','ebae5148-7db7-11ea-9b1f-500320958bf8','ejecutivo','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','WEB','ENEBLED','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Avatar 1 MOBILE
('67b3081e-8bc9-11ea-b45c-c7b846343364','c021214a-8bc7-11ea-b45c-c7b846343364','avatar1','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','MOBILE','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Avatar 2 MOBILE
('52cbc85a-8bc9-11ea-b45c-c7b846343364','d2869da6-8bc7-11ea-b45c-c7b846343364','avatar2','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','MOBILE','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','ENEBLED'),
-- Avatar 4 MOBILE
('3870767c-8bc9-11ea-b45c-c7b846343364','088d7268-8bc7-11ea-b45c-c7b846343364','avatar4','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','MOBILE','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED'),
-- Avatar 3 MOBILE
('22fb81e2-8bc9-11ea-b45c-c7b846343364','c687acca-8bc7-11ea-b45c-c7b846343364','avatar3','8478A4A2819E9C06AB738123C5D04B4FA1AA67548EBA64EAD40B635EA8AA8D5B','MOBILE','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8','DISABLED');
-- --------------------------------------------------------
--
-- Add  APC_USER_BY_OFFICE
--
INSERT INTO APC_USER_BY_OFFICE(id,id_user,id_office,user_by_office_status,application_owner,created_by)
VALUES
-- Janitzio GDL
('a742dfe8-7d20-11ea-af3e-28f659da398e','5751074e-7d1b-11ea-af3e-28f659da398e','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Oscar Tepic
('d855f570-7dbb-11ea-9b1f-500320958bf8','092a95d8-7db8-11ea-9b1f-500320958bf8','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Ruben Tepic
('eca3f824-7dbb-11ea-9b1f-500320958bf8','0dc7c246-7db8-11ea-9b1f-500320958bf8','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Ruben GDL
('e5a44222-7dbb-11ea-9b1f-500320958bf8','0dc7c246-7db8-11ea-9b1f-500320958bf8','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 1 Tepic 
('37835b02-8bca-11ea-b45c-c7b846343364','67b3081e-8bc9-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 2 Tepic 
('5e4c992e-8bca-11ea-b45c-c7b846343364','52cbc85a-8bc9-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 4 GDL 
('efffe026-8bcd-11ea-b45c-c7b846343364','3870767c-8bc9-11ea-b45c-c7b846343364','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8'),
-- Avatar 3 Tepic
('f6dfcbc2-8bcd-11ea-b45c-c7b846343364','22fb81e2-8bc9-11ea-b45c-c7b846343364','caef3a64-7d1f-11ea-af3e-28f659da398e','ENEBLED','APP_USER','0dc7c246-7db8-11ea-9b1f-500320958bf8');
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
-- Chages loans between users
INSERT INTO `APC_PERMISSION` 
(`id`, `permission`, `description`, `menu_path`, `left_to_right_order`, `top_to_bottom_order` ,
 `permission_type`, `permission_status`,`created_by`, `parent_name`) 
VALUES 
('073fba18-2a8e-11eb-9de2-a30e5a9c0028','admin.loan.change.owner', 'admin.loan.change.owner.description', 'admin.loan.change.owner.path', 10, 110,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.loan'),
('50942348-2a8e-11eb-9de2-a30e5a9c0028','admin.loan.change.owner.update', 'admin.loan.change.owner.update.description', 'admin.loan.change.owner.update.path', 10, 111,'PRIVATE', 'ENEBLED', '0dc7c246-7db8-11ea-9b1f-500320958bf8','admin.loan');

INSERT INTO APC_USER_BY_OFFICE_HAS_PERMISSION (id_user_by_office,id_permission,created_by)
SELECT  
    -- Janitzio GDL
    'a742dfe8-7d20-11ea-af3e-28f659da398e',
    id,
    '0dc7c246-7db8-11ea-9b1f-500320958bf8'
FROM  APC_PERMISSION;

INSERT INTO APC_USER_BY_OFFICE_HAS_PERMISSION (id_user_by_office,id_permission,created_by)
SELECT  
    -- Oscar Tepic
    'd855f570-7dbb-11ea-9b1f-500320958bf8',
    id,
    '0dc7c246-7db8-11ea-9b1f-500320958bf8'
FROM  APC_PERMISSION;

INSERT INTO APC_USER_BY_OFFICE_HAS_PERMISSION (id_user_by_office,id_permission,created_by)
SELECT  
    -- Ruben Tepic
    'eca3f824-7dbb-11ea-9b1f-500320958bf8',
    id,
    '0dc7c246-7db8-11ea-9b1f-500320958bf8'
FROM  APC_PERMISSION;


INSERT INTO APC_USER_BY_OFFICE_HAS_PERMISSION (id_user_by_office,id_permission,created_by)
SELECT  
    -- Ruben GDL
    'e5a44222-7dbb-11ea-9b1f-500320958bf8',
    id,
    '0dc7c246-7db8-11ea-9b1f-500320958bf8' 
FROM  APC_PERMISSION;

INSERT INTO APC_USER_MOBILE_PREFERECE(id,id_user,preference_name,preference_value,created_by,created_on)
VALUES
-- Avatar 1
('235e819e-8bbe-11ea-8c0e-beeb61238d59','67b3081e-8bc9-11ea-b45c-c7b846343364','ORDER_LIST','ALPHABETICALLY','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Avatar 2
('e914e614-8bd0-11ea-b45c-c7b846343364','52cbc85a-8bc9-11ea-b45c-c7b846343364','ORDER_LIST','ALPHABETICALLY','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Avatar 4
('ef48f296-8bd0-11ea-b45c-c7b846343364','3870767c-8bc9-11ea-b45c-c7b846343364','ORDER_LIST','ALPHABETICALLY','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Avatar 3
('f49797de-8bd0-11ea-b45c-c7b846343364','22fb81e2-8bc9-11ea-b45c-c7b846343364','ORDER_LIST','ALPHABETICALLY','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()); 
-- -------------------------
-- Logica de negocio de los
-- pagos de APC
-- -------------------------
-- FALTA CREAR RUTA
INSERT INTO APC_ROUTE(id,id_office,route_name,active_status,created_by, created_on)
VALUES
-- Tepic
('51b207a2-8e19-11ea-b65e-4e1376171215','caef3a64-7d1f-11ea-af3e-28f659da398e','Ruta 1 Tepic','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('55baf3ae-8e19-11ea-b65e-4e1376171215','caef3a64-7d1f-11ea-af3e-28f659da398e','Ruta 2 Tepic','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('5a329e3c-8e19-11ea-b65e-4e1376171215','caef3a64-7d1f-11ea-af3e-28f659da398e','Ruta 3 Tepic','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- GDL
('5e9a24e0-8e19-11ea-b65e-4e1376171215','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','Ruta 1 GDL','ENEBLED','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW());
-- FALTA MAPEAR RUTA CON USUARIO

INSERT INTO APC_PEOPLE(id,first_name,second_name,last_name, middle_name, phone_home,address_home, phone_business, address_business, company_name, people_type, active_status,id_office,id_route,created_by,created_on,thumbnail)
VALUES
-- Clientes tepic
('83d2cd30-8e1d-11ea-b65e-4e1376171215','Diego','Segundo 1','Rivera','Materno 1','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('c32a578c-8e1d-11ea-b65e-4e1376171215','David','Segundo 2','Alfaro','Siqueiros','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('ca7fbd56-8e1d-11ea-b65e-4e1376171215','Jose','Segundo 3','Clemente','Orozco','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('d0e60fec-8e1d-11ea-b65e-4e1376171215','Aurora','Segundo 4','Reyes','Materno 4','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('d9763d1c-8e1d-11ea-b65e-4e1376171215','Jorge','Segundo 5','Gonzalez','Camarena','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('e32dd9fa-8e1d-11ea-b65e-4e1376171215','Juan','Segundo 6','O Gorman','Materno 6','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('e8eafa6c-8e1d-11ea-b65e-4e1376171215','Jose','Luis','Cuevas','Materno 7','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('eecbe234-8e1d-11ea-b65e-4e1376171215','Pedro','Segundo 8','Nel','Gomez','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('f45155a4-8e1d-11ea-b65e-4e1376171215','Fernando','Segundo 9','Leal','Materno 9','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
('faa43aa2-8e1d-11ea-b65e-4e1376171215','Mario','Segundo 10','Orozco','Rivera','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
('fffd273e-8e1d-11ea-b65e-4e1376171215','Saturnino','Segundo 11','Herran','Materno 11','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('05d35002-8e1e-11ea-b65e-4e1376171215','Fermin','Segundo 12','Revueltas','Materno 12','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('0b27081e-8e1e-11ea-b65e-4e1376171215','Zabel','Segundo 13','Merida','Materno 13','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('109b9b84-8e1e-11ea-b65e-4e1376171215','Luis','Segundo 14','Nishizawa','Flores','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('15151320-8e1e-11ea-b65e-4e1376171215','Jose','Segundo 15','Chavez','Morado','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
('1f292d38-8e1e-11ea-b65e-4e1376171215','Alfredo','Segundo 16','Ramos','Martinez','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('25bf9ccc-8e1e-11ea-b65e-4e1376171215','Desiderio','Segundo 17','Hernandez','Xochitiotzin','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('2b502d50-8e1e-11ea-b65e-4e1376171215','Rina','Segundo 18','Lazo','Materno 18','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('305aee3e-8e1e-11ea-b65e-4e1376171215','Ramon','Segundo 19','Alva','de la Canal','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('394ffa70-8e1e-11ea-b65e-4e1376171215','Xavier','Segundo 20','Guerrero','Materno 20','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Tepic Nayarit','22-22-22-22','Negocio Calle #Num. col. Colonia, Tepic Nayarit','Nombre Negocio 01','CUSTOMER','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
-- Clientes GDL
('3d8c43e6-8e1e-11ea-b65e-4e1376171215','Juan','Segundo 21','Rulfo','Materno 21','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Guadalajara Jalisco','22-22-22-22','Negocio Calle #Num. col. Colonia, Guadalajara Jalisco','Nombre Negocio 01','CUSTOMER','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('428c5b88-8e1e-11ea-b65e-4e1376171215','Octavio','Segundo 22','Paz','Materno 22','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Guadalajara Jalisco','22-22-22-22','Negocio Calle #Num. col. Colonia, Guadalajara Jalisco','Nombre Negocio 01','CUSTOMER','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('46f6f070-8e1e-11ea-b65e-4e1376171215','Carlos','Segundo 23','Fuentes','Materno 23','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Guadalajara Jalisco','22-22-22-22','Negocio Calle #Num. col. Colonia, Guadalajara Jalisco','Nombre Negocio 01','CUSTOMER','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('4adc2c28-8e1e-11ea-b65e-4e1376171215','Juan','Jose','Arreola','Materno 24','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Guadalajara Jalisco','22-22-22-22','Negocio Calle #Num. col. Colonia, Guadalajara Jalisco','Nombre Negocio 01','CUSTOMER','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
('4ed391e0-8e1e-11ea-b65e-4e1376171215','Rosario','Segundo 25','Castellanos','Materno 25','11-11-11-11-11','Cliente Casa Calle #Num. col. Colonia, Guadalajara Jalisco','22-22-22-22','Negocio Calle #Num. col. Colonia, Guadalajara Jalisco','Nombre Negocio 01','CUSTOMER','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg');
INSERT INTO APC_PEOPLE(id,first_name,second_name,last_name, middle_name, phone_home,address_home, people_type, active_status,id_office,id_route,created_by,created_on,thumbnail)
VALUES
-- Avales tepic
('76a2650c-8e1e-11ea-b65e-4e1376171215','Frida','Segundo 1','Kahlo','Materno 1','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('7ce4fdc6-8e1e-11ea-b65e-4e1376171215','Enrique','Segundo 2','Carbajal','Materno 2','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('818a97aa-8e1e-11ea-b65e-4e1376171215','Juan','Segundo 3','Soriano','Materno 3','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('85d8f694-8e1e-11ea-b65e-4e1376171215','Jose','Segundo 4','Marin','Materno 4','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
('8a4ad7a6-8e1e-11ea-b65e-4e1376171215','Vicente','Segundo 5','Rojo','Almazan','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('91328532-8e1e-11ea-b65e-4e1376171215','Luis','Segundo 6','Ortiz','Monasterio','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('96c9319e-8e1e-11ea-b65e-4e1376171215','Pedro','Segundo 7','Coronel','Materno 7','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('9c481676-8e1e-11ea-b65e-4e1376171215','Miriam','Segundo 8','Medrez','Materno 8','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('aca49c06-8e1e-11ea-b65e-4e1376171215','Maria','Elena','Delgado','Materno 9','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('b0361e44-8e1e-11ea-b65e-4e1376171215','Angela','Segundo 10','Gurria','Materno 10','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('b4c2e9ba-8e1e-11ea-b65e-4e1376171215','Aval 11','Segundo 11','Paterno 11','Materno 11','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('ba2faa96-8e1e-11ea-b65e-4e1376171215','Aval 12','Segundo 12','Paterno 12','Materno 12','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg'),
('be92604c-8e1e-11ea-b65e-4e1376171215','Aval 13','Segundo 13','Paterno 13','Materno 13','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('c2b2b2f8-8e1e-11ea-b65e-4e1376171215','Aval 14','Segundo 14','Paterno 14','Materno 14','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('c6a8b04c-8e1e-11ea-b65e-4e1376171215','Aval 15','Segundo 15','Paterno 15','Materno 15','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('cb0e8d00-8e1e-11ea-b65e-4e1376171215','Aval 16','Segundo 16','Paterno 16','Materno 16','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
('cfafc590-8e1e-11ea-b65e-4e1376171215','Aval 17','Segundo 17','Paterno 17','Materno 17','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('d474b96e-8e1e-11ea-b65e-4e1376171215','Aval 18','Segundo 18','Paterno 18','Materno 18','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('d8f8c804-8e1e-11ea-b65e-4e1376171215','Aval 19','Segundo 19','Paterno 19','Materno 19','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('dd7b34e8-8e1e-11ea-b65e-4e1376171215','Aval 20','Segundo 20','Paterno 20','Materno 20','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Tepic Nayarit','ENDORSEMENT','ENEBLED','caef3a64-7d1f-11ea-af3e-28f659da398e','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
-- Avales GDL
('e13baa9a-8e1e-11ea-b65e-4e1376171215','Aval 21','Segundo 21','Paterno 21','Materno 21','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Guadalajara Jalisco','ENDORSEMENT','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/653323645.jpg'),
('e61db008-8e1e-11ea-b65e-4e1376171215','Aval 22','Segundo 22','Paterno 22','Materno 22','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Guadalajara Jalisco','ENDORSEMENT','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/104313029.jpg'),
('eaa8c70c-8e1e-11ea-b65e-4e1376171215','Aval 23','Segundo 23','Paterno 23','Materno 23','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Guadalajara Jalisco','ENDORSEMENT','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/666164992-1200x1200.jpg'),
('ef10171e-8e1e-11ea-b65e-4e1376171215','Aval 24','Segundo 24','Paterno 24','Materno 24','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Guadalajara Jalisco','ENDORSEMENT','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/10/229554145-1200x1200.jpg'),
('f30d1358-8e1e-11ea-b65e-4e1376171215','Guillermo','Segundo 25','Tellez','Materno 25','11-11-11-11-11','Aval Casa Calle #Num. col. Colonia, Guadalajara Jalisco','ENDORSEMENT','ENEBLED','e0f1a2fc-7d1f-11ea-af3e-28f659da398e','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),'https://freisteller24.eu/wp-content/uploads/2017/11/blogbeitrag-haare-2-1024x713.jpg');
INSERT INTO APC_LOAN_TYPE(id,loan_type_name,total_days, loan_fee, payment,payment_daily,payment_total, id_office,created_by, created_on,opening_fee)
VALUES
-- Para tepic
('db833bf0-8e5e-11ea-8ee4-e54bc704beac','Prestamo $1000', 22,50,1000,60,1320,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),20),
('c59e5bee-8dff-11ea-8745-07889553dd5f','Prestamo $2000', 22,50,2000,120,2640,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),40),
('dc255a16-8dff-11ea-8745-07889553dd5f','Prestamo $3000', 22,50,3000,180,3960,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),60),
('e7cc91c2-8dff-11ea-8745-07889553dd5f','Prestamo $4000', 22,50,4000,240,5280,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),80),
('f0cb05ba-8dff-11ea-8745-07889553dd5f','Prestamo $5000', 22,50,5000,300,6600,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),100),
('fdaa4318-8dff-11ea-8745-07889553dd5f','Prestamo $6000', 22,50,6000,360,7920,'caef3a64-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),120),
-- Para GDL;
('7f0cc30e-8e00-11ea-8745-07889553dd5f','Prestamo $1000', 22,50,1000,60,1320,'e0f1a2fc-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),20),
('8623efbe-8e00-11ea-8745-07889553dd5f','Prestamo $2000', 22,50,2000,120,2640,'e0f1a2fc-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),40),
('8d91bc36-8e00-11ea-8745-07889553dd5f','Prestamo $3000', 22,50,3000,180,3960,'e0f1a2fc-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),60),
('93b13506-8e00-11ea-8745-07889553dd5f','Prestamo $4000', 22,50,4000,240,5280,'e0f1a2fc-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),80),
('99d91a66-8e00-11ea-8745-07889553dd5f','Prestamo $5000', 22,50,5000,300,6600,'e0f1a2fc-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),100),
('a0292d0c-8e00-11ea-8745-07889553dd5f','Prestamo $6000', 22,50,6000,360,7920,'e0f1a2fc-7d1f-11ea-af3e-28f659da398e','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW(),120);
INSERT INTO APC_LOAN(id,id_loan_type,id_customer,id_endorsement,id_route,loan_status,amount_paid,amount_to_pay,last_reference_number,created_by,created_on,last_updated_on)
VALUES
-- Tepic / Avatar 1
('c4ed9e5a-8e1b-11ea-b65e-4e1376171215','db833bf0-8e5e-11ea-8ee4-e54bc704beac','83d2cd30-8e1d-11ea-b65e-4e1376171215','76a2650c-8e1e-11ea-b65e-4e1376171215','51b207a2-8e19-11ea-b65e-4e1376171215','APPROVED',0,1320,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('c09f127a-8e1b-11ea-b65e-4e1376171215','db833bf0-8e5e-11ea-8ee4-e54bc704beac','c32a578c-8e1d-11ea-b65e-4e1376171215','7ce4fdc6-8e1e-11ea-b65e-4e1376171215','51b207a2-8e19-11ea-b65e-4e1376171215','REJECTED',0,1320,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW()),
('bc185d2e-8e1b-11ea-b65e-4e1376171215','db833bf0-8e5e-11ea-8ee4-e54bc704beac','ca7fbd56-8e1d-11ea-b65e-4e1376171215','818a97aa-8e1e-11ea-b65e-4e1376171215','51b207a2-8e19-11ea-b65e-4e1376171215','APPROVED',0,1320,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW()),
('b64c6e94-8e1b-11ea-b65e-4e1376171215','db833bf0-8e5e-11ea-8ee4-e54bc704beac','d0e60fec-8e1d-11ea-b65e-4e1376171215','85d8f694-8e1e-11ea-b65e-4e1376171215','51b207a2-8e19-11ea-b65e-4e1376171215','FINISH',1320,1320,22,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('b0d25168-8e1b-11ea-b65e-4e1376171215','db833bf0-8e5e-11ea-8ee4-e54bc704beac','d9763d1c-8e1d-11ea-b65e-4e1376171215','8a4ad7a6-8e1e-11ea-b65e-4e1376171215','51b207a2-8e19-11ea-b65e-4e1376171215','PENDING',0,1320,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW()),
-- Tepic / Avatar 2
('acccdfac-8e1b-11ea-b65e-4e1376171215','c59e5bee-8dff-11ea-8745-07889553dd5f','e32dd9fa-8e1d-11ea-b65e-4e1376171215','91328532-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','APPROVED',0,2640,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('a811395e-8e1b-11ea-b65e-4e1376171215','c59e5bee-8dff-11ea-8745-07889553dd5f','e8eafa6c-8e1d-11ea-b65e-4e1376171215','96c9319e-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','APPROVED',0,2640,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('a42ceb26-8e1b-11ea-b65e-4e1376171215','c59e5bee-8dff-11ea-8745-07889553dd5f','eecbe234-8e1d-11ea-b65e-4e1376171215','9c481676-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','FINISH',2640,2640,22,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW()),
('9ff730b6-8e1b-11ea-b65e-4e1376171215','c59e5bee-8dff-11ea-8745-07889553dd5f','f45155a4-8e1d-11ea-b65e-4e1376171215','aca49c06-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','APPROVED',0,2640,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW()),
('98c4de60-8e1b-11ea-b65e-4e1376171215','c59e5bee-8dff-11ea-8745-07889553dd5f','faa43aa2-8e1d-11ea-b65e-4e1376171215','b0361e44-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','APPROVED',0,2640,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('94aad8a2-8e1b-11ea-b65e-4e1376171215','e7cc91c2-8dff-11ea-8745-07889553dd5f','fffd273e-8e1d-11ea-b65e-4e1376171215','b4c2e9ba-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','FINISH',5280,5280,22,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('90dadcae-8e1b-11ea-b65e-4e1376171215','e7cc91c2-8dff-11ea-8745-07889553dd5f','05d35002-8e1e-11ea-b65e-4e1376171215','ba2faa96-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','APPROVED',0,5280,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW()),
('8d017a66-8e1b-11ea-b65e-4e1376171215','e7cc91c2-8dff-11ea-8745-07889553dd5f','0b27081e-8e1e-11ea-b65e-4e1376171215','be92604c-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','TO_DELIVERY',0,5280,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 2 DAY),DATE_SUB(NOW(), INTERVAL 2 DAY)),
('86a09490-8e1b-11ea-b65e-4e1376171215','dc255a16-8dff-11ea-8745-07889553dd5f','109b9b84-8e1e-11ea-b65e-4e1376171215','c2b2b2f8-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','APPROVED',0,3960,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('82a781dc-8e1b-11ea-b65e-4e1376171215','dc255a16-8dff-11ea-8745-07889553dd5f','15151320-8e1e-11ea-b65e-4e1376171215','c6a8b04c-8e1e-11ea-b65e-4e1376171215','55baf3ae-8e19-11ea-b65e-4e1376171215','FINISH',3960,3960,22,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
-- Tepic / Avatar 3
('7ec212f8-8e1b-11ea-b65e-4e1376171215','f0cb05ba-8dff-11ea-8745-07889553dd5f','1f292d38-8e1e-11ea-b65e-4e1376171215','cb0e8d00-8e1e-11ea-b65e-4e1376171215','5a329e3c-8e19-11ea-b65e-4e1376171215','APPROVED',0,6600,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW()),
('788f666a-8e1b-11ea-b65e-4e1376171215','f0cb05ba-8dff-11ea-8745-07889553dd5f','25bf9ccc-8e1e-11ea-b65e-4e1376171215','cfafc590-8e1e-11ea-b65e-4e1376171215','5a329e3c-8e19-11ea-b65e-4e1376171215','REJECTED',0,6600,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('74eca1c6-8e1b-11ea-b65e-4e1376171215','f0cb05ba-8dff-11ea-8745-07889553dd5f','2b502d50-8e1e-11ea-b65e-4e1376171215','d474b96e-8e1e-11ea-b65e-4e1376171215','5a329e3c-8e19-11ea-b65e-4e1376171215','APPROVED',0,6600,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('705b9662-8e1b-11ea-b65e-4e1376171215','f0cb05ba-8dff-11ea-8745-07889553dd5f','305aee3e-8e1e-11ea-b65e-4e1376171215','d8f8c804-8e1e-11ea-b65e-4e1376171215','5a329e3c-8e19-11ea-b65e-4e1376171215','APPROVED',0,6600,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('6cd18fec-8e1b-11ea-b65e-4e1376171215','fdaa4318-8dff-11ea-8745-07889553dd5f','394ffa70-8e1e-11ea-b65e-4e1376171215','dd7b34e8-8e1e-11ea-b65e-4e1376171215','5a329e3c-8e19-11ea-b65e-4e1376171215','APPROVED',0,7920,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
-- GDL / Avatar 4
('68b7b92c-8e1b-11ea-b65e-4e1376171215','7f0cc30e-8e00-11ea-8745-07889553dd5f','3d8c43e6-8e1e-11ea-b65e-4e1376171215','e13baa9a-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','FINISH',1320,1320,22,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 4 DAY),DATE_SUB(NOW(), INTERVAL 4 DAY)),
('6613cd16-9a9e-11ea-b304-ce916d70ea46','7f0cc30e-8e00-11ea-8745-07889553dd5f','3d8c43e6-8e1e-11ea-b65e-4e1376171215','e13baa9a-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','REJECTED',0,1320,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 3 DAY),DATE_SUB(NOW(), INTERVAL 3 DAY)),
('6fab97c8-9a9e-11ea-b304-ce916d70ea46','7f0cc30e-8e00-11ea-8745-07889553dd5f','3d8c43e6-8e1e-11ea-b65e-4e1376171215','e13baa9a-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','APPROVED',0,1320,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('606e42f4-8e1b-11ea-b65e-4e1376171215','8623efbe-8e00-11ea-8745-07889553dd5f','428c5b88-8e1e-11ea-b65e-4e1376171215','e61db008-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','APPROVED',0,2640,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('5c925364-8e1b-11ea-b65e-4e1376171215','8d91bc36-8e00-11ea-8745-07889553dd5f','46f6f070-8e1e-11ea-b65e-4e1376171215','eaa8c70c-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','APPROVED',0,3960,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),DATE_SUB(NOW(), INTERVAL 1 DAY)),
('58f3fb72-8e1b-11ea-b65e-4e1376171215','93b13506-8e00-11ea-8745-07889553dd5f','4adc2c28-8e1e-11ea-b65e-4e1376171215','ef10171e-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','FINISH',5280,5280,22,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 3 DAY),DATE_SUB(NOW(), INTERVAL 3 DAY)),
('551308c2-8e1b-11ea-b65e-4e1376171215','99d91a66-8e00-11ea-8745-07889553dd5f','4ed391e0-8e1e-11ea-b65e-4e1376171215','f30d1358-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','REJECTED',0,6600,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 2 DAY),DATE_SUB(NOW(), INTERVAL 2 DAY)),
('7d9d4850-9a9d-11ea-b304-ce916d70ea46','99d91a66-8e00-11ea-8745-07889553dd5f','4ed391e0-8e1e-11ea-b65e-4e1376171215','f30d1358-8e1e-11ea-b65e-4e1376171215','5e9a24e0-8e19-11ea-b65e-4e1376171215','APPROVED',0,6600,0,'0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_SUB(NOW(), INTERVAL 1 DAY),NOW());
INSERT INTO APC_HUMAN_RESOURCE_HAS_ROUTE(id_human_resource,id_route,created_by,created_on)
VALUES
-- Avatar 1
('c021214a-8bc7-11ea-b45c-c7b846343364','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Avatar 2
('d2869da6-8bc7-11ea-b45c-c7b846343364','51b207a2-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('d2869da6-8bc7-11ea-b45c-c7b846343364','55baf3ae-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('d2869da6-8bc7-11ea-b45c-c7b846343364','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Avatar 3
('c687acca-8bc7-11ea-b45c-c7b846343364','5a329e3c-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Avatar 4
('088d7268-8bc7-11ea-b45c-c7b846343364','5e9a24e0-8e19-11ea-b65e-4e1376171215','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW());
INSERT INTO APC_LOAN_BY_USER(id_loan,id_user,loan_by_user_status,owner_loan,created_by,created_on)
VALUES
-- Tepic / Avatar 1
('c4ed9e5a-8e1b-11ea-b65e-4e1376171215','67b3081e-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('c09f127a-8e1b-11ea-b65e-4e1376171215','67b3081e-8bc9-11ea-b45c-c7b846343364','REJECTED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('bc185d2e-8e1b-11ea-b65e-4e1376171215','67b3081e-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('b64c6e94-8e1b-11ea-b65e-4e1376171215','67b3081e-8bc9-11ea-b45c-c7b846343364','FINISH','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('b0d25168-8e1b-11ea-b65e-4e1376171215','67b3081e-8bc9-11ea-b45c-c7b846343364','PENDING','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Tepic / Avatar 2
('acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('a811395e-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('a42ceb26-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','FINISH','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('9ff730b6-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('98c4de60-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('94aad8a2-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','FINISH','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('90dadcae-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('8d017a66-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','TO_DELIVERY','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('86a09490-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('82a781dc-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','FINISH','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- Tepic / Avatar 3
('7ec212f8-8e1b-11ea-b65e-4e1376171215','22fb81e2-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('788f666a-8e1b-11ea-b65e-4e1376171215','22fb81e2-8bc9-11ea-b45c-c7b846343364','REJECTED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('74eca1c6-8e1b-11ea-b65e-4e1376171215','22fb81e2-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('705b9662-8e1b-11ea-b65e-4e1376171215','22fb81e2-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('6cd18fec-8e1b-11ea-b65e-4e1376171215','22fb81e2-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
-- GDL / Avatar 4
('68b7b92c-8e1b-11ea-b65e-4e1376171215','3870767c-8bc9-11ea-b45c-c7b846343364','FINISH','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('6613cd16-9a9e-11ea-b304-ce916d70ea46','3870767c-8bc9-11ea-b45c-c7b846343364','REJECTED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_ADD(NOW(), INTERVAL 22 DAY)),
('6fab97c8-9a9e-11ea-b304-ce916d70ea46','3870767c-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_ADD(NOW(), INTERVAL 44 DAY)),
('606e42f4-8e1b-11ea-b65e-4e1376171215','3870767c-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('5c925364-8e1b-11ea-b65e-4e1376171215','3870767c-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('58f3fb72-8e1b-11ea-b65e-4e1376171215','3870767c-8bc9-11ea-b45c-c7b846343364','FINISH','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('551308c2-8e1b-11ea-b65e-4e1376171215','3870767c-8bc9-11ea-b45c-c7b846343364','REJECTED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',NOW()),
('7d9d4850-9a9d-11ea-b304-ce916d70ea46','3870767c-8bc9-11ea-b45c-c7b846343364','APPROVED','CURRENT_OWNER','0dc7c246-7db8-11ea-9b1f-500320958bf8',DATE_ADD(NOW(), INTERVAL 22 DAY));
INSERT INTO APC_LOAN_DETAIL(id,id_loan,id_user,people_type,payment_amount,reference_number,loan_details_type, created_by, created_on)
VALUES
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,1,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 21 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,2,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 20 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,3,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 19 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,4,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 18 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,5,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 17 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,6,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 16 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,7,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 15 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,8,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 14 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,9,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 13 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,10,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 12 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,11,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 11 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,12,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 10 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,13,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 9 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,14,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 8 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,15,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 7 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,16,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 6 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,17,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 5 DAY)),
-- sumar 100 al monto por pagar
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',50,18,'FEE','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 4 DAY)),
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',50,19,'FEE','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 3 DAY)),
-- sumar +120 al monto pagado
(UUID(),'acccdfac-8e1b-11ea-b65e-4e1376171215','52cbc85a-8bc9-11ea-b45c-c7b846343364','CUSTOMER',120,20,'PAYMENT','52cbc85a-8bc9-11ea-b45c-c7b846343364', DATE_SUB(NOW(), INTERVAL 2 DAY));
UPDATE APC_LOAN
SET amount_paid = 2160, amount_to_pay = 2740 ,last_reference_number = 20, last_updated_by = '52cbc85a-8bc9-11ea-b45c-c7b846343364', last_updated_on = DATE_SUB(NOW(), INTERVAL 1 DAY)
WHERE id = 'acccdfac-8e1b-11ea-b65e-4e1376171215';
COMMIT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
