/**
 *
 * @author sandy
 */
 

package sands.dao.interfaces;

import java.util.List;
import model.ProjectScheduleName;


public interface ProjectScheduleNameDAO {


    /*
     * Save ProjectResourceName
     */
    public void save(ProjectScheduleName projectScheduleName);



    /**
     *
     * Delete ProjectResource
     */
    public void delete(ProjectScheduleName projectScheduleName);

    /**
     * Get Information By Id
     * @param id
     * @return ProjectResourceName
     */
    public ProjectScheduleName getById(long id);

    /**
     *
     * @param int offset
     * @param long project_id
     * @return List
     */
    public List list(int offset);

    /**
     *
     * @param int offset
     * @return List
     */
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
     * @param ProjectResourceName Object
     */
     public void update(ProjectScheduleName projectschedulename);

}
