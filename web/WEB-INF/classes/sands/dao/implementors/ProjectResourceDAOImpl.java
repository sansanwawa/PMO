/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sands.dao.implementors;

import model.ProjectResource;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;
import sands.dao.interfaces.ProjectResourceDAO;

/**
 *
 * @author sandy
 */
public class ProjectResourceDAOImpl implements ProjectResourceDAO{

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void save(ProjectResource projectResource) {
        this.hibernateTemplate.saveOrUpdate(projectResource);
    }

    public ProjectResource getById(long id) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectResource.class).add(Expression.eq("project.id", id));
        ProjectResource p = (ProjectResource) criteria.list().get(0);
        return p;
    }

}
