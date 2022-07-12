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
-- Base de datos: `apo_pro_com_april_ten`
--
USE `apo_pro_com_april_ten`;

-- --------------------------------------------------------

--
-- Drop users:  apoprocom & errortrace
--
DROP USER IF EXISTS 'apoprocom'@'%';
DROP USER IF EXISTS 'errortrace'@'%';
DROP USER IF EXISTS 'apoprocommobile'@'%';
DROP USER IF EXISTS 'apcreportdesktop'@'%';
-- --------------------------------------------------------

--
-- App user: `apoprocom` application user
--

CREATE USER 'apoprocom'@'%'
	IDENTIFIED BY 'Yj$2Da0z!';
  
GRANT SELECT ON apo_pro_com_april_ten.* TO 'apoprocom'@'%';
GRANT INSERT, UPDATE, DELETE ON apo_pro_com_april_ten.* TO 'apoprocom'@'%';
GRANT EXECUTE ON  apo_pro_com_april_ten.* TO 'apoprocom'@'%';

-- --------------------------------------------------------

--
-- Error trace user: `errortrace` DB user
--

-- --------------------------------------------------------

CREATE USER 'errortrace'@'%'
    IDENTIFIED BY 'zy61$Jql';
GRANT ALL ON apo_pro_com_april_ten.APC_ERROR_APP_LOG TO 'errortrace'@'%';

-- --------------------------------------------------------

--
-- Error trace user: `apoprocommobile` DB user
--

-- --------------------------------------------------------

CREATE USER 'apoprocommobile'@'%'
    IDENTIFIED BY '0Ps$6%q8';

GRANT SELECT, INSERT ON apo_pro_com_april_ten.APC_LOAN_DETAIL TO 'apoprocommobile'@'%';
GRANT SELECT, INSERT ON apo_pro_com_april_ten.APC_LOAN_FEE_NOTIFICATION TO 'apoprocommobile'@'%';
GRANT SELECT, INSERT ON apo_pro_com_april_ten.APC_DELIVERY TO 'apoprocommobile'@'%';
GRANT SELECT, INSERT ON apo_pro_com_april_ten.APC_OTHER_EXPENSE TO 'apoprocommobile'@'%';

GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN_BY_RENOVATION TO 'apoprocommobile'@'%';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN_BY_USER TO 'apoprocommobile'@'%';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_USER_MOBILE_PREFERECE TO 'apoprocommobile'@'%';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_PEOPLE TO 'apoprocommobile'@'%'; 
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN TO 'apoprocommobile'@'%';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_TRANSFER TO 'apoprocommobile'@'%';
-- SELECT
GRANT SELECT ON apo_pro_com_april_ten.APC_OFFICE TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_USER TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_TYPE TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_MONEY_DAILY TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_CLOSING_DAY TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_HUMAN_RESOURCE_HAS_ROUTE TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_ROUTE TO 'apoprocommobile'@'%';
-- Views
GRANT SELECT ON apo_pro_com_april_ten.APC_SECURITY_AUTHENTICATION_MOBILE TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_BY_USER_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_BY_USER_ORDER_PREFERENCE_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_PERSON_SEARCH_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_PERSON_SEARCH_DETAIL_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_AVAILABLE_CUSTOMERS_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_AVAILABLE_ENDORSEMENTS_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_CASH_REGISTER_CURDATE_BY_USER_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_TO_DELIVERY_BY_CERTIFIER_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_EXCHANGE_ENEBLED_USERS_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_TOTAL_CASH_BY_CURDATE_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_PERSON_SEARCH_HISTORICAL_DETAILS_VIEW TO 'apoprocommobile'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_APPROVED_DETAIL_VIEW TO 'apoprocommobile'@'%';
-- ----------------------------------------------------------------------
GRANT ALL ON apo_pro_com_april_ten.ARREBOL_TEST TO 'apoprocommobile'@'%';
-- --------------------------------------------------------

--
-- App user: `apcreportdesktop` report desktop user
--

CREATE USER 'apcreportdesktop'@'%'
	IDENTIFIED BY 'hY5znQ8j';
  
GRANT SELECT ON apo_pro_com_april_ten.APC_USER TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_HUMAN_RESOURCE TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_TOTAL_CASH_BY_CURDATE_VIEW TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_CLOSING_DAILY_DETAIL_FROM_USER_BY_CURDATE_VIEW TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_CLOSING_DAILY_DETAIL_FROM_USER_BY_CURDATE_VIEW_REPORT TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_STABLE_SMALL_BOX TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_CLOSING_DAY TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_MONEY_DAILY TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_PAYROLL TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_ADVANCE TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_EXPENSE_COMPANY TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_STABLE_GENERAL_BOX TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_TYPE TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_DETAIL TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_BY_USER TO 'apcreportdesktop'@'%';
GRANT SELECT ON apo_pro_com_april_ten.APC_OFFICE TO 'apcreportdesktop'@'%';
-- ----------------------------------------------------------------------
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;