/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sands.dao.interfaces;

import model.ProjectLegal;

/**
 *
 * @author sandy
 */
public interface ProjectLegalDAO {

    public void save(ProjectLegal projectLegal);
    public ProjectLegal getById(long id);

}
