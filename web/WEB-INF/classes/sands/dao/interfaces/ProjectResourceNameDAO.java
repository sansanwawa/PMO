/**
 *
 * @author sandy
 */
package sands.dao.interfaces;

import java.util.List;
import model.ProjectResourceName;

public interface ProjectResourceNameDAO {



    /*
     * Save ProjectResourceName
     */
    public void save(ProjectResourceName projectResourceName);



    /**
     *
     * Delete ProjectResource
     */
    public void delete(ProjectResourceName projectresourcename);
 
    /**
     * Get Information By Id
     * @param id
     * @return ProjectResourceName
     */
    public ProjectResourceName getById(long id);

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
     public void update(ProjectResourceName projectresourcename);
}
