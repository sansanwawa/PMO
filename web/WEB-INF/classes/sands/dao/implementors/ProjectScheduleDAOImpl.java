/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import java.util.ArrayList;
import java.util.List;
import model.ProjectSchedule;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import sands.dao.interfaces.ProjectScheduleDAO;

public class ProjectScheduleDAOImpl extends Crud implements ProjectScheduleDAO {

    private Long project_id = null;

    public void save(ProjectSchedule projectSchedule) {
        this.saveOrUpdate(projectSchedule);
    }

    public void delete(ProjectSchedule projectSchedule) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectSchedule SET active=:active WHERE id=:project_schedule_id").
                setLong("project_schedule_id", projectSchedule.getId()).
                setBoolean("active", false).
                executeUpdate();
    }

    public ProjectSchedule getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectSchedule.class).add(Expression.eq("project.id", id));
        ProjectSchedule p = (ProjectSchedule) criteria.list().get(0);
        return p;
    }

    public void setProjectId(Long project_id) {
        this.project_id = project_id;
    }

    public List getByExpression(SimpleExpression[] ex) {
        return super.getByExpression(ex, ProjectSchedule.class);
    }

    public List list(int offset) {
      return super.list(ProjectSchedule.class, project_id,offset);
    }
}
