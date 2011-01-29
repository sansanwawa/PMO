/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sands.dao.interfaces;

import model.ProjectSchedule;

/**
 *
 * @author sandy
 */
public interface ProjectScheduleDAO {

    public void save(ProjectSchedule projectSchedule);

    public ProjectSchedule getById(long id);

}
