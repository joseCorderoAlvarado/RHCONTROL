/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository.core;

import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.constance.HumanResourceCfg;
import com.arrebol.apc.repository.AbstractRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class HumanResourceRepository extends AbstractRepository implements Serializable {

    /**
     *
     * @param hr
     * @param updateAvatar
     * @return
     */
    public boolean updateByHumanResourceId(HumanResource hr, boolean updateAvatar) {
        logger.debug("updateByHumanResourceId");

        boolean success = false;
        try {
            openConnection();

            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            CriteriaUpdate<HumanResource> update = builder.createCriteriaUpdate(HumanResource.class);
            Root<HumanResource> root = update.from(HumanResource.class);

            if (updateAvatar) {
                update.set(root.get(HumanResourceCfg.FIELD_AVATAR), hr.getAvatar());
            } else {
                update.set(root.get(HumanResourceCfg.FIELD_FIRST_NAME), hr.getFirstName());
                update.set(root.get(HumanResourceCfg.FIELD_SECOND_NAME), hr.getSecondName());
                update.set(root.get(HumanResourceCfg.FIELD_LAST_NAME), hr.getLastName());
                update.set(root.get(HumanResourceCfg.FIELD_MIDDLE_NAME), hr.getMiddleName());
                update.set(root.get(HumanResourceCfg.FIELD_BIRTHDATE), hr.getBirthdate());
                update.set(root.get(HumanResourceCfg.FIELD_ADMISSION_DATE), hr.getAdmissionDate());
                update.set(root.get(HumanResourceCfg.FIELD_PAYMENT), hr.getPayment());
                update.set(root.get(HumanResourceCfg.FIELD_IMSS), hr.getImss());
                update.set(root.get(HumanResourceCfg.FIELD_ROLE), hr.getRoleCtlg());
                
                if(hr.getEmployeeSaving() != null){
                    update.set(root.get(HumanResourceCfg.FIELD_EMPLOYEE_SAVING),  hr.getEmployeeSaving());
                }else{
                    update.set(root.get(HumanResourceCfg.FIELD_EMPLOYEE_SAVING),  BigDecimal.ZERO);
                }
                
                //update.set(root.get(HumanResourceCfg.FIELD_HR_TYPE), hr.getHumanResourceType());
            }

            update.set(root.get(HumanResourceCfg.FIELD_LAST_UPDATED_BY), hr.getLastUpdatedBy());
            update.set(root.get(HumanResourceCfg.FIELD_LAST_UPDATED_ON), hr.getLastUpdatedOn());

            update.where(builder.equal(root.get(HumanResourceCfg.FIELD_ID), hr.getId()));

            int total = getSession().createQuery(update).executeUpdate();

            if (1 == total) {
                closeConnection();

                success = true;

                logger.debug("Human resource updated");
            } else {
                logger.error("Update HR from create user", new Exception("Was avatar update " + updateAvatar));
                rollback();
            }
        } catch (HibernateException e) {
            logger.error("updateByHumanResourceId hibernate", e);
            rollback();
        } catch (Exception e) {
            logger.error("Method updateByHumanResourceId() ", e);
            rollback();
        }
        return success;
    }

    private static final long serialVersionUID = -5624272695296306941L;
    final Logger logger = LogManager.getLogger(HumanResourceRepository.class);
}
