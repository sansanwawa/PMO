/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.interfaces;

import java.util.List;
import model.ProjectFinancial;

/**
 *
 * @author sandy
 */
public interface ProjectFinancialDAO {

    public void save(ProjectFinancial projectFinancial);

    public void delete(ProjectFinancial projectFinancial);

    public List getById(long id);
}
