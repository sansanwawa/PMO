/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.implementors;

import model.ProjectSchedule;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;
import sands.dao.interfaces.ProjectScheduleDAO;

/**
 *
 * @author sandy
 */
public class ProjectScheduleDAOImpl implements ProjectScheduleDAO {

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void save(ProjectSchedule projectSchedule) {
        this.hibernateTemplate.saveOrUpdate(projectSchedule);
    }

    public ProjectSchedule getById(long id) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectSchedule.class).add(Expression.eq("project.id", id));
        ProjectSchedule p = (ProjectSchedule) criteria.list().get(0);
        return p;
    }
}
