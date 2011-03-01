/**
 *
 * @author sandy
 */
 

package sands.dao.interfaces;

import java.util.List;
import model.ProjectInternalCost;
import org.hibernate.criterion.SimpleExpression;


public interface ProjectInternalCostDAO {

     /**
     * set Project Id for another purposes
     * @param project_id
     */
    public void setProjectId(Long project_id);

    /*
     * Save ProjectResource
     */
    public void save(ProjectInternalCost projectInternalCost);



    /**
     *
     * Delete ProjectResource
     */
    public void delete(ProjectInternalCost projectInternalCost);


    /**
     *
     * @param int offset
     * @param long project_id
     * @return List
     */
    public List list(int offset);


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
     * get By Expression
     * Array of Expression
     * @return List
     */
    public List getByExpression(SimpleExpression[] simpleExpression);


}
