/* 
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
/**
 * Author:  Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 * Created: 9 mar. 2022
 */
DROP FUNCTION IF EXISTS `isMobileEnabledUser`;
-- --------------------------------------------------------
--
-- Estructura para la función `isMobileEnabledUser`
--
-- Verifica que el usuario este activo y que no sea certificador
--
DELIMITER //
CREATE FUNCTION `isMobileEnabledUser` (
	p_user_id VARCHAR(36)
)
RETURNS BOOL DETERMINISTIC
BEGIN	
    DECLARE v_is_mobile_enabled_user INT;
    
    SELECT 
		COUNT(u.id) INTO v_is_mobile_enabled_user
	FROM APC_USER u
	INNER JOIN APC_HUMAN_RESOURCE hr ON hr.id = u.id_human_resource
	INNER JOIN APC_USER_BY_OFFICE ubo ON ubo.id_user = u.id
	WHERE u.user_status = 'ENEBLED' AND 
	u.user_type IN ('MOBILE') AND 
	u.certifier = 'DISABLED'
    AND u.id = CONVERT(p_user_id USING utf8mb4);
    
    IF v_is_mobile_enabled_user = 1
    THEN 
		RETURN TRUE;
    ELSE 
		RETURN FALSE;
    END IF;
    
END; //
DELIMITER ;
