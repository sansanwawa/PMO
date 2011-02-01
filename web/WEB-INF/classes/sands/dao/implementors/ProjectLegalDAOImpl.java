/**
 *
 * @author sandy
 */
package sands.dao.implementors;


import helper.database.Crud;
import model.ProjectLegal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import sands.dao.interfaces.ProjectLegalDAO;


public class ProjectLegalDAOImpl extends Crud implements ProjectLegalDAO {

    public void save(ProjectLegal projectLegal) {
        this.saveOrUpdate(projectLegal);
    }

    public ProjectLegal getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectLegal.class).add(Expression.eq("project.id", id));        
        ProjectLegal p = (ProjectLegal) criteria.list().get(0);
        return p;
    }
}
