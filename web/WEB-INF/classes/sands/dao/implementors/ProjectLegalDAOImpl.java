/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import java.util.List;
import model.ProjectLegal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.SimpleExpression;
import sands.dao.interfaces.ProjectLegalDAO;

public class ProjectLegalDAOImpl extends Crud implements ProjectLegalDAO {

    private Long project_id = null;

    public void save(ProjectLegal projectLegal) {
        projectLegal.setCreatedBy(this.getPrincipal().getUsername());
        projectLegal.setUpdateBy(this.getPrincipal().getUsername());
        super.saveOrUpdate(projectLegal);
    }

    public ProjectLegal getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectLegal.class).add(Expression.eq("project.id", id));
        ProjectLegal p = (ProjectLegal) criteria.list().get(0);
        return p;
    }

    public void setProjectId(Long project_id) {
        this.project_id = project_id;
    }

    public void delete(ProjectLegal projectLegal) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectLegal SET active=:active WHERE id=:project_legal_id").
                setLong("project_legal_id", projectLegal.getId()).
                setBoolean("active", false).
                executeUpdate();
    }

    public List list(int offset) {
        return super.list(ProjectLegal.class, project_id, offset);
    }

    public List getByExpression(SimpleExpression[] ex) {
        return super.getByExpression(ex, ProjectLegal.class);
    }
}
