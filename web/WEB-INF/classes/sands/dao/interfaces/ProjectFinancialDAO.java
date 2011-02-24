/**
 *
 * @author sandy
 */
package sands.dao.interfaces;

import java.util.List;
import model.ProjectFinancial;


public interface ProjectFinancialDAO {

    /**
     * set Project Id for another purposes
     * @param project_id
     */
    public void setProjectId(Long project_id);

    /**
     * Save ProjectFinancial
     * @param ProjectFinancial projectFinancial
     */
    public void save(ProjectFinancial projectFinancial);

    /**
     * Delete ProjectFinancial
     * @param ProjectFinancial projectFinancial
     */
    public void delete(ProjectFinancial projectFinancial);

   
    /**
     *
     * @param int max result
     */
    public void setMaxResults(int maxresult);

    /**
     * Order by
     * @param String field
     * @param String type
     */
    public void orderBy(String field, String type);

    /**
     *
     * @param String field
     */
    public void orderByDesc(String field);

    /**
     *
     * @param String field
     */
    public void orderByAsc(String field);


   /**
     *
     * @param int offset
     * @param long project_id
     * @return List
     */
    public List list(int offset);

}
