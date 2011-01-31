/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.interfaces;

import java.util.ArrayList;
import model.ProjectResource;
import model.ProjectResourceName;

/**
 *
 * @author sandy
 */
public interface ProjectResourceDAO {

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
     * Get Information By Id
     * @param id
     * @return ProjectResourceName
     */
    public ProjectResourceName getById(long id);

    /**
     *
     * @param offset
     * @return ArrayList
     */
    public ArrayList list(int offset);

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
