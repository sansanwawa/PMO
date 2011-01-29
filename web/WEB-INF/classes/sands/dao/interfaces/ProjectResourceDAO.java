/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.interfaces;

import model.ProjectResource;

/**
 *
 * @author sandy
 */
public interface ProjectResourceDAO {

    public void save(ProjectResource projectResource);

    public ProjectResource getById(long id);
}
