/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Oscar
 * Created: 11/01/2022
 */

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
WHERE ldFaltante.id_loan = l.id AND LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_all,
(SELECT IF(COUNT(DISTINCT(DATE(ldFaltante.created_on))) > 5, 5, COUNT(DISTINCT(DATE(ldFaltante.created_on)))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = (WEEK(CURDATE(),1)) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_week,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND ldLunes.loan_details_type IN ('FEE')) as fee_todos
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
----------------------------------------------------------------
----------------------------------------------------------------
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
WHERE ldFaltante.id_loan = l.id AND DATE(ldFaltante.created_on) <= DATE('2022-01-23') and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_all,
(SELECT IF(COUNT(DISTINCT(DATE(ldFaltante.created_on))) > 5, 5, COUNT(DISTINCT(DATE(ldFaltante.created_on)))) FROM APC_LOAN_DETAIL ldFaltante 
WHERE ldFaltante.id_loan = l.id AND WEEK(DATE(ldFaltante.created_on),1) = (WEEK(CURDATE(),1) - 1) AND 
YEAR(DATE(ldFaltante.created_on)) = YEAR(CURDATE()) and LOWER(DAYNAME(DATE(ldFaltante.created_on))) NOT IN('saturday','sunday') 
AND ldFaltante.loan_details_type IN ('PAYMENT','RENOVATION_PAYMENT','TRANSFER', 'FEE')) as num_pagos_week,
(SELECT IF(ISNULL(SUM(ldLunes.payment_amount)),0,SUM(ldLunes.payment_amount)) 
FROM APC_LOAN_DETAIL ldLunes 
WHERE ldLunes.id_loan = l.id AND WEEK(DATE(ldLunes.created_on),1) <= DATE('2022-01-23')
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