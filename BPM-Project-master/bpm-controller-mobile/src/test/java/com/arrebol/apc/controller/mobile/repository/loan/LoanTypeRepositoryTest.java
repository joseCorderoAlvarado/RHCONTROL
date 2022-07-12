/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.loan;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.constance.LoanTypeCfg;
import com.arrebol.apc.model.loan.Loan;
import com.arrebol.apc.model.loan.LoanType;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanTypeRepositoryTest {

    public static final String OFFICE_ID = "caef3a64-7d1f-11ea-af3e-28f659da398e";
    public static final String LOAND_ID = "acccdfac-8e1b-11ea-b65e-4e1376171215";

    private LoanTypeRepository repository;

    @Before
    public void setUp() {
        repository = new LoanTypeRepository();
    }

    @Test
    public void findLoanTypes() {
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(
                            LoanTypeCfg.FIELD_OFFICE,
                            new Office(OFFICE_ID)
                    )
            );
            
            parameters.add(
                    new ModelParameter(
                            LoanTypeCfg.PARAM_LOAN,
                            new Loan(LOAND_ID)
                    )
            );
            
            parameters.add(
                    new ModelParameter(
                            LoanTypeCfg.PARAM_LOAN_ID,
                            LOAND_ID
                    )
            );

            List<LoanType> results = repository.findLoanTypes(
                    LoanType.class,
                    LoanTypeCfg.QUERY_FIND_NEW_CREDIT_LINE_BY_LOAN_ID,
                    parameters);

            results.forEach(System.out::println);
            assertFalse(results.isEmpty());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

}
