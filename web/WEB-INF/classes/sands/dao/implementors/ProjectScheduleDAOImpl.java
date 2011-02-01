/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import model.ProjectSchedule;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import sands.dao.interfaces.ProjectScheduleDAO;

public class ProjectScheduleDAOImpl extends Crud implements ProjectScheduleDAO {

    public void save(ProjectSchedule projectSchedule) {
        this.saveOrUpdate(projectSchedule);
    }

    public ProjectSchedule getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectSchedule.class).add(Expression.eq("project.id", id));
        ProjectSchedule p = (ProjectSchedule) criteria.list().get(0);
        return p;
    }
}
