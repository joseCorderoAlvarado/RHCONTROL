/* 
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
/**
 * Author:  Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 * Created: 28/10/2020
 */

CREATE OR REPLACE VIEW `APC_CLOSING_DAILY_DETAIL_FROM_USER_BY_CURDATE_VIEW_REPORT` AS
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
    (l.amount_to_pay - amount_paid) as saldo,
    0 as comisionApertura,
    0 as prestamoAnterior
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
    0 as saldo,
    0 as comisionApertura,
    0 as prestamoAnterior
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
    0 as saldo,
    0 as comisionApertura,
    0 as prestamoAnterior
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
    0 as saldo,
    0 as comisionApertura,
    0 as prestamoAnterior
FROM
	APC_TRANSFER te
JOIN
	APC_USER u on u.id = te.id_user_receiver
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = u.id_human_resource
WHERE DATE(te.created_on) = CURDATE() and te.action_status = 'APPROVED'
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
    0 as saldo,
    0 as comisionApertura,
    0 as prestamoAnterior
FROM
	APC_TRANSFER tr
JOIN
	APC_USER u on u.id = tr.id_user_transmitter
JOIN
	APC_HUMAN_RESOURCE hr on hr.id = u.id_human_resource
WHERE DATE(tr.created_on) = CURDATE() and tr.action_status = 'APPROVED'
UNION 
SELECT
	d.id,
    CONCAT(p.first_name,' ',IF(ISNULL(p.second_name) ,'', CONCAT(p.second_name, ' ')),p.last_name,' ', p.middle_name),
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
    WHERE lr.id_loan_new = l.id) END as saldo,
    lt.opening_fee as comisionApertura,
    (SELECT CASE WHEN ld.payment_amount is null or 0 THEN 0 ELSE ld.payment_amount END FROM APC_LOAN_DETAIL ld
INNER JOIN APC_LOAN l3 ON ld.id_loan = l3.id
WHERE ld.loan_details_type = 'RENOVATION_PAYMENT' 
AND l3.id = (SELECT albr.id_loan_old FROM APC_LOAN_BY_RENOVATION albr WHERE albr.id_loan_new = l.id)) as prestamoAnterior
FROM APC_DELIVERY d
INNER JOIN APC_LOAN l ON d.id_loan = l.id
INNER JOIN APC_LOAN_TYPE lt ON l.id_loan_type = lt.id
INNER JOIN APC_PEOPLE p ON l.id_customer = p.id
INNER JOIN APC_ROUTE r ON r.id = l.id_route
WHERE DATE(d.created_on) = CURDATE();


CREATE OR REPLACE VIEW `APC_RESUMEN_IN_OUT_WEEK_BY_USER_VIEW` AS 
SELECT
u.id,
CONCAT(hr.first_name, ' ' , hr.last_name) as username,
ubo.id_office,
-- Lunes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'monday') as closing_monday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'monday') as expense_monday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'monday') as money_daily_today_monday,
-- Martes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'tuesday') as closing_tuesday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'tuesday') as expense_tuesday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'tuesday') as money_daily_today_tuesday,
-- Miercoles
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'wednesday') as closing_wednesday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'wednesday') as expense_wednesday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'wednesday') as money_daily_today_wednesday,
-- Jueves
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'thursday') as closing_thursday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'thursday') as expense_thursday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'thursday') as money_daily_today_thursday,
-- Viernes
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'friday') as closing_friday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'friday') as expense_friday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'friday') as money_daily_today_friday,
-- Sabado
(SELECT IF(ISNULL(SUM(ld.amount_paid)),0,SUM(ld.amount_paid)) 
FROM APC_CLOSING_DAY ld
WHERE ld.id_user = u.id AND WEEK(DATE(ld.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.active_status = 'ENEBLED' 
AND LOWER(DAYNAME(DATE(ld.created_on))) = 'saturday') as closing_saturday,
(SELECT IF(ISNULL(SUM(oe.expense)),0,SUM(oe.expense)) 
FROM APC_OTHER_EXPENSE oe
WHERE oe.id_user = u.id AND WEEK(DATE(oe.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(oe.created_on)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(oe.created_on))) = 'saturday') as expense_saturday,
(SELECT IF(ISNULL(SUM(md.amount)),0,SUM(md.amount)) 
FROM APC_MONEY_DAILY md
WHERE md.id_user = u.id AND WEEK(DATE(md.money_daily_date),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(md.money_daily_date)) = YEAR(CURDATE()) 
AND LOWER(DAYNAME(DATE(md.money_daily_date))) = 'saturday') as money_daily_today_saturday
FROM APC_USER u
INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
WHERE u.user_status = 'ENEBLED' AND 
u.user_type IN ('MOBILE','BOTH');

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

CASE WHEN l.new_customer = 'ENEBLED' AND WEEK(DATE(l.created_on),1) = WEEK(CURDATE(),1) THEN 'Si' ELSE 'No' END as new_customer,
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
((SELECT COUNT(ld.id) FROM APC_LOAN_DETAIL ld WHERE WEEK(DATE(ld.created_on),1) = WEEK(CURDATE(),1) AND 
 YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.id_loan = l.id) > 0 OR 
 ((SELECT COUNT(ld.id) FROM APC_LOAN_DETAIL ld WHERE WEEK(DATE(ld.created_on),1) = WEEK(CURDATE(),1) AND 
 YEAR(DATE(ld.created_on)) = YEAR(CURDATE()) AND ld.id_loan = l.id) = 0 AND l.loan_status = 'APPROVED' AND 
 WEEK(DATE(l.created_on),1) <= (WEEK(CURDATE(),1))));