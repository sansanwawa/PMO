/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.interfaces;

import model.Project;
import java.util.ArrayList;

/**
 *
 * @author sandy
 */
public interface ProjectDAO {

    public void save(Project project);

    public void update(Project project);
    
    public void delete(Project project);

    public ArrayList<Project> list(int offset);

    public void orderBy( String field, String type );
    
    public void orderByDesc(String field);

    public void orderByAsc(String field);

    public void setMaxResults(int i);

    public Project getById(long id);
}
