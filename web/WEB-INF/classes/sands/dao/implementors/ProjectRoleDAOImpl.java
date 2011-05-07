/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import java.util.List;
import model.ProjectRole;
import org.hibernate.Session;
import org.hibernate.criterion.SimpleExpression;
import sands.dao.interfaces.ProjectRoleDAO;

public class ProjectRoleDAOImpl extends Crud implements ProjectRoleDAO {

    private Long project_id = null;

    public void save(ProjectRole projectRole) {
        projectRole.setCreatedBy(this.getPrincipal().getUsername());
        projectRole.setUpdateBy(this.getPrincipal().getUsername());
        super.save(projectRole);
    }

    public void delete(ProjectRole projectRole) {
        Session session = Crud.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectRole SET active=:active WHERE id=:id").
                setLong("id", projectRole.getId()).
                setBoolean("active", false).
                executeUpdate();
    }

    public List getByExpression(SimpleExpression[] ex) {
        return super.getByExpression(ex, ProjectRole.class);
    }

    public void setProjectId(Long project_id) {
        this.project_id = project_id;
    }

    public ProjectRole getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        ProjectRole project = new ProjectRole();
        project.setId(id);
        ProjectRole p = (ProjectRole) session.load(ProjectRole.class, project.getId());
        return p;
    }

    public void update(ProjectRole projectRole) {
        projectRole.setUpdateBy(this.getPrincipal().getUsername());
        super.update(projectRole);
    }

    public List list(int offset) {
        return super.list(ProjectRole.class, project_id, offset);
    }
   
}
