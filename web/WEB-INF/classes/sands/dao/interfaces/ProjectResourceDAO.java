/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.interfaces;

import java.util.List;
import model.ProjectResource;
import model.ProjectResourceName;

/**
 *
 * @author sandy
 */
public interface ProjectResourceDAO {

    /**
     * set Project Id for another purposes
     * @param project_id
     */
    public void setProjectId(Long project_id);

    /*
     * Save ProjectResource
     */
    public void save(ProjectResource projectResource);

    /*
     *  Save ProjectResourceName
     */
    public void saveName(ProjectResourceName projectresourcename);

    /*
     *  Update ProjectResourceName
     */
    public void updateName(ProjectResourceName projectresourcename);

    /**
     *
     * Delete ProjectResource
     */
    public void delete(ProjectResource projectresource);

    /**
     *
     * Delete ProjectresourceName
     * 
     */
    public void deleteName(ProjectResourceName projectresourcename);

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
}
