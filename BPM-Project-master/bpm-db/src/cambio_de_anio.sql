-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_INFORMATION_LOAN_LAST_WEEK_VIEW`
--
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

CASE WHEN l.new_customer = 'ENEBLED' AND WEEK(DATE(l.created_on),1) = (WEEK(CURDATE(),1) - 1) THEN 'Si' ELSE 'No' END as new_customer,
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
((SELECT COUNT(ld.id) FROM APC_LOAN_DETAIL ld WHERE WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
 YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.id_loan = l.id) > 0 OR 
 ((SELECT COUNT(ld.id) FROM APC_LOAN_DETAIL ld WHERE WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
 YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.id_loan = l.id) = 0 AND l.loan_status = 'APPROVED' AND 
 WEEK(DATE(l.created_on),1) <= (WEEK(CURDATE(),1) - 1)));
-- --------------------------------------------------------
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
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE') AND 
u.certifier = 'DISABLED';
-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_RESUMEN_IN_OUT_LAST_WEEK_BY_USER_VIEW`
--
CREATE OR REPLACE VIEW `APC_RESUMEN_IN_OUT_LAST_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
-- Lunes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'monday') as closing_monday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'monday') as expense_monday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'monday') as money_daily_today_monday,
-- Martes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'tuesday') as closing_tuesday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'tuesday') as expense_tuesday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'tuesday') as money_daily_today_tuesday,
-- Miercoles
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'wednesday') as closing_wednesday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'wednesday') as expense_wednesday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'wednesday') as money_daily_today_wednesday,
-- Jueves
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'thursday') as closing_thursday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'thursday') as expense_thursday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'thursday') as money_daily_today_thursday,
-- Viernes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'friday') as closing_friday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'friday') as expense_friday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'friday') as money_daily_today_friday,
-- Sabado
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'saturday') as closing_saturday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'saturday') as expense_saturday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'saturday') as money_daily_today_saturday
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE','BOTH');
-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_LOAN_DIFERENCES_BY_USER_LAST_WEEK_VIEW`
--
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
-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_SUBTOTAL_LAST_WEEK_BY_USER_VIEW`
--
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
-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_COBRANZA_LAST_WEEK_BY_USER_VIEW`
--
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
-- --------------------------------------------------------
-- 
-- Estructura para la vista `APC_RESUMEN_TOTAL_LAST_WEEK_VIEW`
--
CREATE OR REPLACE VIEW `APC_RESUMEN_TOTAL_LAST_WEEK_VIEW` AS 
SELECT
u.id,
u.office_name,
-- Cortes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND ld.id_office = u.id) as closing__day_total,
-- Inicios
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) AND md.id_office = u.id) as money_daily_today_total,
-- Subtotal
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_total,
-- comision por apertura
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE())) 
    as opening_fee_total,
-- cobranza
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6'  
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) -1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_today,
-- colocacion
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) ) 
    as colocation_total,
-- nominas
(SELECT IF(ISNULL(SUM(ap.total_payment)),0,SUM(ap.total_payment)) FROM APC_PAYROLL ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' 
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as nomina_total,
-- adelantos
(SELECT IF(ISNULL(SUM(ap.amount)),0,SUM(ap.amount)) FROM APC_ADVANCE ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' 
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as adelantos_total,
-- entradas
(SELECT IF(ISNULL(SUM(ap.amount)),0,SUM(ap.amount)) FROM APC_EXPENSE_COMPANY ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' AND 
ap.expense_company_type = 'PAYMENT_IN'
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as entradas_total, 
-- gastos admon
(SELECT IF(ISNULL(SUM(ap.amount)),0,SUM(ap.amount)) FROM APC_EXPENSE_COMPANY ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' AND 
ap.expense_company_type = 'PAYMENT_OUT'
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as gastos_admon_total
FROM APC_OFFICE u
WHERE u.office_status = 'ENEBLED';
-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--
CREATE OR REPLACE VIEW `APC_RESUMEN_TOTAL_LAST_WEEK_VIEW` AS 
SELECT
u.id,
u.office_name,
-- Cortes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND ld.id_office = u.id) as closing__day_total,
-- Inicios
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) AND md.id_office = u.id) as money_daily_today_total,
-- Subtotal
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as subtotal_total,
-- comision por apertura
(SELECT IF(ISNULL(SUM(alt.opening_fee)),0,SUM(alt.opening_fee)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE())) 
    as opening_fee_total,
-- cobranza
(SELECT IF(ISNULL(SUM(ld.payment_amount)),0,SUM(ld.payment_amount)) 
FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l ON l.id = ld.id_loan 
INNER JOIN APC_LOAN_BY_USER lbu ON lbu.id_loan = l.id
WHERE lbu.id_user = 'aad0c673-eb93-11ea-b7e1-02907d0fb4e6'  
AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1) -1) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) 
AND ld.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER')) as cobranza_today,
-- colocacion
(SELECT IF(ISNULL(SUM(alt.payment)),0,SUM(alt.payment)) FROM APC_LOAN al 
    INNER JOIN APC_LOAN_TYPE alt ON al.id_loan_type = alt.id 
    INNER JOIN APC_LOAN_BY_USER albu ON albu.id_loan = al.id
    WHERE loan_status = 'APPROVED' 
    AND WEEK(DATE(al.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(al.created_on)) = YEAR(CURDATE()) ) 
    as colocation_total,
-- nominas
(SELECT IF(ISNULL(SUM(ap.total_payment)),0,SUM(ap.total_payment)) FROM APC_PAYROLL ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' 
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as nomina_total,
-- adelantos
(SELECT IF(ISNULL(SUM(ap.amount)),0,SUM(ap.amount)) FROM APC_ADVANCE ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' 
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as adelantos_total,
-- entradas
(SELECT IF(ISNULL(SUM(ap.amount)),0,SUM(ap.amount)) FROM APC_EXPENSE_COMPANY ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' AND 
ap.expense_company_type = 'PAYMENT_IN'
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as entradas_total, 
-- gastos admon
(SELECT IF(ISNULL(SUM(ap.amount)),0,SUM(ap.amount)) FROM APC_EXPENSE_COMPANY ap 
WHERE ap.id_office = u.id AND ap.active_status = 'ENEBLED' AND 
ap.expense_company_type = 'PAYMENT_OUT'
AND WEEK(DATE(ap.created_on),1) = (WEEK(CURDATE(),1)-1) AND 
    YEAR(Date(ap.created_on)) = YEAR(CURDATE())) as gastos_admon_total
FROM APC_OFFICE u
WHERE u.office_status = 'ENEBLED';
-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--

-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--

-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--

-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--

-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--

-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--

-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--

-- --------------------------------------------------------
-- 
-- Estructura para la vista ``
--