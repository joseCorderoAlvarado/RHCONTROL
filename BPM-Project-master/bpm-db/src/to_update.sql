/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Oscar
 * Created: 26/10/2021
 */
ALTER TABLE APC_LOAN_DETAIL
ADD COLUMN fee_status enum('TO_PAY','PAID') DEFAULT 'TO_PAY' AFTER loan_comments;

GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN_DETAIL TO 'apoprocommobile'@'%';
-- GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN_DETAIL TO 'apoprocommobilelocalhost'@'localhost';

ALTER TABLE APC_GASOLINE
MODIFY COLUMN quantity  DOUBLE DEFAULT 0.0;




-- ----------------------------------------------------------------
-- 
-- Estructura para la vista `APC_CUSTOMERS_WITHOUT_RENOVATION_VIEW`
--
CREATE OR REPLACE VIEW `APC_CUSTOMERS_WITHOUT_RENOVATION_VIEW` AS
SELECT 
    p.id AS id,
    CONCAT(p.first_name,' ', IF(ISNULL(p.second_name) ,'', CONCAT(p.second_name,' ')) ,p.last_name, ' ', p.middle_name) AS available_person,
    ar.route_name AS route_name,
    (SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id) as last_loan,
    p.id_office AS id_office,
(
CASE
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '01'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Enero - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '02'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Febrero - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '03'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Marzo - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '04'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Abril - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '05'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Mayo - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '06'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Junio - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '07'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Julio - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '08'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Agosto - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '09'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Septiembre - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '10'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Octubre - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '11'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Noviembre - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
    WHEN date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%m') = '12'
    THEN concat(date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%d'),' - Diciembre - ',date_format((SELECT MAX(l.created_on) FROM APC_LOAN l WHERE l.id_customer = p.id),'%Y'))
END
) AS str_payment_date
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

-- Agregar campo saldo en tabla de empleados
ALTER TABLE APC_HUMAN_RESOURCE
ADD COLUMN employee_saving DECIMAL(8,2) DEFAULT 0;

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
    cstmr.address_home AS customer_address_home,
    cstmr.address_business AS customer_address_business,
    cstmr.company_name,
    cstmr.thumbnail AS customer_thumbnail,
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
    ndrsmnt.address_home AS endorsement_address_home,
    ndrsmnt.thumbnail AS endorsement_thumbnail,
    ndrsmnt.phone_home AS endorsement_phone_home,
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
(SELECT amount_to_pay - amount_paid FROM APC_LOAN WHERE id = l.id) AS max_amount_to_pay,
cstmr.phone_home AS customer_phone_home
FROM APC_LOAN_BY_USER lbu
INNER JOIN APC_LOAN l ON lbu.id_loan = l.id
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN APC_PEOPLE cstmr ON l.id_customer = cstmr.id
INNER JOIN APC_PEOPLE ndrsmnt ON l.id_endorsement = ndrsmnt.id
INNER JOIN APC_USER u ON lbu.id_user = u.id
WHERE 
    l.loan_status IN ('APPROVED', 'PENDING', 'TO_DELIVERY') AND
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
    lbu.owner_loan = 'CURRENT_OWNER' AND
    CASE
        WHEN LOWER(DAYNAME(CURDATE())) = 'monday'
            THEN ('monday' = lt.payment_monday)
        WHEN LOWER(DAYNAME(CURDATE())) = 'tuesday'
            THEN ('tuesday' = LOWER(lt.payment_tuesday))
        WHEN LOWER(DAYNAME(CURDATE())) = 'wednesday'
            THEN ('wednesday' = LOWER(lt.payment_wednesday))
        WHEN LOWER(DAYNAME(CURDATE())) = 'thursday'
            THEN ('thursday' =LOWER(lt.payment_thursday))
        WHEN LOWER(DAYNAME(CURDATE())) = 'friday'
            THEN ('friday' = LOWER(lt.payment_friday))
        WHEN LOWER(DAYNAME(CURDATE())) = 'saturday'
            THEN ('saturday' = LOWER(lt.payment_saturday))
        WHEN LOWER(DAYNAME(CURDATE())) = 'sunday'
            THEN ('sunday' = LOWER(lt.payment_sunday))
        ELSE FALSE
    END;

-- -------------------------------------------------------------
-- 
-- Estructura para la vista `APC_STATS_EMPLOYEE_SAVING_VIEW`
--
CREATE OR REPLACE VIEW `APC_STATS_EMPLOYEE_SAVING_VIEW`  AS
SELECT
    ES.id, 
    ES.id_user,
    CONCAT(HR.first_name, ' ', HR.last_name) AS name,
    R.route_name AS route_name,
    ES.employee_saving AS employee_saving,
    ES.created_on
FROM APC_EMPLOYEE_SAVING ES
INNER JOIN APC_HUMAN_RESOURCE HR ON ES.id_user = HR.id
INNER JOIN APC_HUMAN_RESOURCE_HAS_ROUTE HRR ON HR.id = HRR.id_human_resource
INNER JOIN APC_ROUTE R ON HRR.id_route = R.id;

CREATE OR REPLACE VIEW `APC_LOAN_EMPLOYEE_VIEW`  AS
SELECT
    L.id, 
    CONCAT(HR.first_name, ' ', HR.last_name) AS name,
    HR.id AS id_user,
    L.amount_loan,
    L.amount_to_pay,
    L.balance,
    L.total_amount_to_pay,
    L.created_on,
    CONCAT(HRU.first_name, ' ', HRU.last_name) AS created_by_name,
    (SELECT IFNULL(MAX(LD.reference_number), 0) FROM APC_LOAN_EMPLOYEE_DETAIL LD WHERE LD.id_loan = L.id) AS reference_number
FROM APC_LOAN_EMPLOYEE L
INNER JOIN APC_HUMAN_RESOURCE HR ON L.id_employee = HR.id
INNER JOIN APC_USER US ON L.created_by = US.id
INNER JOIN APC_HUMAN_RESOURCE HRU ON US.id_human_resource = HRU.id
WHERE L.loan_employee_status = 'ENEBLED' AND L.balance > 0
ORDER BY name;

-- Agrega columna de monto total a pagar
ALTER TABLE APC_LOAN_EMPLOYEE ADD COLUMN `total_amount_to_pay` DECIMAL(8,2) DEFAULT 0;
-- Actualiza el campo created_by ya que guardaba un valor mal
UPDATE APC_STABLE_SMALL_BOX SET created_by = '5751074e-7d1b-11ea-af3e-28f659da398e';

CREATE OR REPLACE VIEW APC_FEES_VIEW  AS
SELECT
    LD.ID, 
    LD.id_user,
    R.id AS id_route,
    R.route_name AS route_name,
    CONCAT(HR.first_name, ' ', HR.last_name) AS name,
    LD.payment_amount AS total_fees,
    LD.created_on
FROM APC_LOAN_DETAIL LD
INNER JOIN APC_LOAN L ON LD.id_loan = L.id AND L.loan_status <> 'DELETED'
INNER JOIN APC_USER U ON LD.id_user = U.id
INNER JOIN APC_ROUTE R ON L.id_route = R.id
INNER JOIN APC_HUMAN_RESOURCE HR ON U.id_human_resource = HR.id
WHERE loan_details_type = 'FEE'

ALTER TABLE APC_CLOSING_DAY ADD COLUMN id_route varchar(36) DEFAULT '' AFTER id_user;

-- ----------------------------------------------------------------
-- 
-- Estructura para la vista `APC_COLOCATION_WEEK_BY_USER_VIEW`
--
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
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource AND hr.human_resource_status = 'ENEBLED'
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE');

-- ----------------------------------------------------------------
-- 
-- Estructura para la vista `APC_COLOCATION_LAST_WEEK_BY_USER_VIEW`
--
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
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource AND hr.human_resource_status = 'ENEBLED'
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE');

-- Alter para agregar la columna de clasificación
ALTER TABLE APC_PEOPLE ADD COLUMN `classification` ENUM('WHITE', 'RED', 'YELLOW') NOT NULL DEFAULT 'WHITE';

-- Estructura para la vista `APC_CUSTOMER_VIEW`
--
CREATE OR REPLACE VIEW `APC_CUSTOMER_VIEW` AS
SELECT  
ap.id AS id,
CONCAT(
    CASE
        WHEN ap.first_name IS NOT NULL AND ap.first_name != ''
        THEN CONCAT(SUBSTR(UPPER(ap.first_name), 1, 1),SUBSTR(LOWER(ap.first_name), 2), ' ')
        ELSE ''
    END,
    CASE
        WHEN ap.second_name IS NOT NULL AND ap.second_name != ''
        THEN CONCAT(SUBSTR(UPPER(ap.second_name), 1, 1),SUBSTR(LOWER(ap.second_name), 2), ' ')
        ELSE ''
    END,
    CASE
        WHEN ap.last_name IS NOT NULL AND ap.last_name != ''
        THEN CONCAT(SUBSTR(UPPER(ap.last_name), 1, 1),SUBSTR(LOWER(ap.last_name), 2), ' ')
        ELSE ''
    END,
    CASE
        WHEN ap.middle_name IS NOT NULL AND ap.middle_name != ''
        THEN CONCAT(SUBSTR(UPPER(ap.middle_name), 1, 1),SUBSTR(LOWER(ap.middle_name), 2))
        ELSE ''
    END
) AS full_name,
ap.company_name AS company_name, 
ap.address_home AS address_home,
ap.address_business AS address_business,
ar.route_name,
ap.people_type AS people_type,
(CASE
	WHEN ap.people_type = 'CUSTOMER' THEN 'Cliente'
    WHEN ap.people_type = 'BOTH' THEN 'Cliente y aval'
END) AS str_people_type,
ao.office_name AS office_name,
(SELECT COUNT(id) FROM APC_LOAN WHERE id_customer = ap.id) AS total_of_loan,
ap.classification
FROM APC_PEOPLE ap
INNER JOIN APC_ROUTE ar ON ap.id_route = ar.id
INNER JOIN APC_OFFICE ao ON ap.id_office = ao.id
WHERE ap.active_status = 'ENEBLED' AND ap.people_type IN ('CUSTOMER','BOTH');

-- -------------------------------------------------------------
-- 
-- Estructura para la vista `APC_LOAN_EMPLOYEE_DETAIL_ALL_DATA_VIEW`
--
CREATE OR REPLACE VIEW `APC_LOAN_EMPLOYEE_DETAIL_ALL_DATA_VIEW`  AS
SELECT
	id,
	id_loan,
	id_user,
	payment_amount,
    reference_number,
    loan_employee_detail_status,
    created_on
FROM APC_LOAN_EMPLOYEE_DETAIL
ORDER BY created_on ASC;

-- -------------------------------------------------------------
-- 
-- Estructura para la vista `APC_LOAN_EMPLOYEE_VIEW`
--
CREATE OR REPLACE VIEW `APC_LOAN_EMPLOYEE_VIEW`  AS
SELECT
    L.id, 
    CONCAT(HR.first_name, ' ', HR.last_name) AS name,
    HR.id AS id_user,
    L.amount_loan,
    L.amount_to_pay,
    L.balance,
    L.total_amount_to_pay,
    L.created_on,
    L.loan_employee_status,
    CONCAT(HRU.first_name, ' ', HRU.last_name) AS created_by_name,
    (SELECT IFNULL(MAX(LD.reference_number), 0) FROM APC_LOAN_EMPLOYEE_DETAIL LD WHERE LD.id_loan = L.id) AS reference_number
FROM APC_LOAN_EMPLOYEE L
INNER JOIN APC_HUMAN_RESOURCE HR ON L.id_employee = HR.id
INNER JOIN APC_USER US ON L.created_by = US.id
INNER JOIN APC_HUMAN_RESOURCE HRU ON US.id_human_resource = HRU.id
WHERE L.balance > 0
ORDER BY name, created_on DESC;

-- -------------------------------------------------------------
-- 
-- Estructura para la vista `APC_STATS_GASOLINE_VIEW`
--
CREATE OR REPLACE VIEW `APC_STATS_GASOLINE_VIEW`  AS
SELECT
    G.ID, 
    R.id AS id_route,
    R.route_name AS route_name,
    G.id_user,
    CONCAT(HR.first_name, ' ', HR.last_name) AS user_name,
    (SELECT GAS.km_new FROM APC_GASOLINE GAS WHERE GAS.created_on < G.created_on AND GAS.id_user = G.id_user ORDER BY GAS.created_on DESC LIMIT 1) AS km_old,
    G.km_new,
    G.total AS total_gasoline,
    G.Description AS description,
    G.created_on,
    G.quantity
FROM APC_GASOLINE G
INNER JOIN APC_USER U ON G.id_user = U.id
INNER JOIN APC_HUMAN_RESOURCE HR ON U.id_human_resource = HR.id
INNER JOIN APC_ROUTE R ON G.id_route = R.id
WHERE G.status = 'ENABLED';

-- CAMBIOS 28/06/2022 DRH
ALTER TABLE APC_VEHICLES ADD COLUMN type varchar(15) DEFAULT '' AFTER vehicle_status;
ALTER TABLE APC_VEHICLES ADD COLUMN brand varchar(50) DEFAULT '' AFTER type;

-- -------------------------------------------------------------
-- 
-- Estructura para la vista `APC_LOAN_EMPLOYEE_VIEW`
--
CREATE OR REPLACE VIEW `APC_LOAN_EMPLOYEE_VIEW`  AS
SELECT
    L.id, 
    CONCAT(HR.first_name, ' ', HR.last_name) AS name,
    HR.id AS id_user,
    L.amount_loan,
    L.amount_to_pay,
    L.balance,
    L.total_amount_to_pay,
    L.created_on,
    L.loan_employee_status,
    CONCAT(HRU.first_name, ' ', HRU.last_name) AS created_by_name,
    (SELECT IFNULL(MAX(LD.reference_number), 0) FROM APC_LOAN_EMPLOYEE_DETAIL LD WHERE LD.id_loan = L.id) AS reference_number
FROM APC_LOAN_EMPLOYEE L
INNER JOIN APC_HUMAN_RESOURCE HR ON L.id_employee = HR.id
INNER JOIN APC_USER US ON L.created_by = US.id
INNER JOIN APC_HUMAN_RESOURCE HRU ON US.id_human_resource = HRU.id
ORDER BY name, created_on DESC;