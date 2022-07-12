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
-- Drop users:  apoprocomlocalhost & errortracelocalhost
--
DROP USER IF EXISTS 'apoprocomlocalhost'@'localhost';
DROP USER IF EXISTS 'errortracelocalhost'@'localhost';
DROP USER IF EXISTS 'apoprocommobilelocalhost'@'localhost';
DROP USER IF EXISTS 'apcreportdesktop'@'localhost';
-- --------------------------------------------------------

--
-- App user: `apoprocomlocalhost` application user
--

CREATE USER 'apoprocomlocalhost'@'localhost'
	IDENTIFIED BY 'Yj$2Da0z!';
  
GRANT SELECT ON apo_pro_com_april_ten.* TO 'apoprocomlocalhost'@'localhost';
GRANT INSERT, UPDATE, DELETE ON apo_pro_com_april_ten.* TO 'apoprocomlocalhost'@'localhost';
GRANT EXECUTE ON  apo_pro_com_april_ten.* TO 'apoprocomlocalhost'@'localhost';

-- --------------------------------------------------------

--
-- Error trace user: `errortracelocalhost` DB user
--

-- --------------------------------------------------------

CREATE USER 'errortracelocalhost'@'localhost'
    IDENTIFIED BY 'zy61$Jql';
GRANT ALL ON apo_pro_com_april_ten.APC_ERROR_APP_LOG TO 'errortracelocalhost'@'localhost';

-- --------------------------------------------------------

--
-- Error trace user: `apoprocommobilelocalhost` DB user
--

-- --------------------------------------------------------

CREATE USER 'apoprocommobilelocalhost'@'localhost'
    IDENTIFIED BY '0Ps$6%q8';

GRANT SELECT, INSERT ON apo_pro_com_april_ten.APC_LOAN_FEE_NOTIFICATION TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT ON apo_pro_com_april_ten.APC_DELIVERY TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT ON apo_pro_com_april_ten.APC_OTHER_EXPENSE TO 'apoprocommobilelocalhost'@'localhost';

GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN_DETAIL TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN_BY_RENOVATION TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN_BY_USER TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_USER_MOBILE_PREFERECE TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_PEOPLE TO 'apoprocommobilelocalhost'@'localhost'; 
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_LOAN TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_TRANSFER TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT, INSERT, UPDATE ON apo_pro_com_april_ten.APC_GASOLINE TO 'apoprocommobilelocalhost'@'localhost';
-- SELECT
GRANT SELECT ON apo_pro_com_april_ten.APC_OFFICE TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_USER TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_TYPE TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_MONEY_DAILY TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_CLOSING_DAY TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_HUMAN_RESOURCE_HAS_ROUTE TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_ROUTE TO 'apoprocommobilelocalhost'@'localhost';
-- Views
GRANT SELECT ON apo_pro_com_april_ten.APC_SECURITY_AUTHENTICATION_MOBILE TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_BY_USER_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_BY_USER_ORDER_PREFERENCE_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_PERSON_SEARCH_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_PERSON_SEARCH_DETAIL_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_AVAILABLE_CUSTOMERS_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_AVAILABLE_ENDORSEMENTS_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_CASH_REGISTER_CURDATE_BY_USER_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_TO_DELIVERY_BY_CERTIFIER_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_EXCHANGE_ENEBLED_USERS_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_TOTAL_CASH_BY_CURDATE_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_PERSON_SEARCH_HISTORICAL_DETAILS_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_LOAN_APPROVED_DETAIL_VIEW TO 'apoprocommobilelocalhost'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_USER_WEEK_REPORT_VIEW TO 'apoprocommobilelocalhost'@'localhost';
-- -------------------------------------------------------------------------------------
GRANT ALL ON apo_pro_com_april_ten.ARREBOL_TEST TO'apoprocommobilelocalhost'@'localhost';
-- --------------------------------------------------------

--
-- App user: `apcreportdesktop` report desktop user
--

CREATE USER 'apcreportdesktop'@'localhost'
	IDENTIFIED BY 'hY5znQ8j';
  
GRANT SELECT ON apo_pro_com_april_ten.APC_USER TO 'apcreportdesktop'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_HUMAN_RESOURCE TO 'apcreportdesktop'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_TOTAL_CASH_BY_CURDATE_VIEW TO 'apcreportdesktop'@'localhost';
GRANT SELECT ON apo_pro_com_april_ten.APC_CLOSING_DAILY_DETAIL_FROM_USER_BY_CURDATE_VIEW TO 'apcreportdesktop'@'localhost';

-- -------------------------------------------------------------------------------------
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;