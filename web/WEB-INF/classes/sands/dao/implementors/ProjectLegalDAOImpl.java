/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao.implementors;


import model.ProjectLegal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;
import sands.dao.interfaces.ProjectLegalDAO;

/**
 *
 * @author sandy
 */
public class ProjectLegalDAOImpl implements ProjectLegalDAO {

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void save(ProjectLegal projectLegal) {
        hibernateTemplate.saveOrUpdate(projectLegal);
    }

    public ProjectLegal getById(long id) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();       
        Criteria criteria = session.createCriteria(ProjectLegal.class).add(Expression.eq("project.id", id));        
        ProjectLegal p = (ProjectLegal) criteria.list().get(0);
        return p;
    }
}
