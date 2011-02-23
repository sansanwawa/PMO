/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import java.util.ArrayList;
import java.util.List;
import model.ProjectResource;
import model.ProjectResourceName;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import sands.dao.interfaces.ProjectResourceDAO;

public class ProjectResourceDAOImpl extends Crud implements ProjectResourceDAO {

    private Long project_id = null;

    public void setProjectId(Long project_id) {
        this.project_id = project_id;
    }

    public void save(ProjectResource projectResource) {
        projectResource.setCreatedBy(this.getPrincipal().getUsername());
        projectResource.setUpdateBy(this.getPrincipal().getUsername());
        super.saveOrUpdate(projectResource);
    }

    public void saveName(ProjectResourceName projectresourcename) {
        projectresourcename.setCreatedBy(this.getPrincipal().getUsername());
        projectresourcename.setUpdateBy(this.getPrincipal().getUsername());
        this.save(projectresourcename);
    }

    public void updateName(ProjectResourceName projectresourcename) {
        projectresourcename.setUpdateBy(this.getPrincipal().getUsername());
        super.update(projectresourcename);
    }

    public void deleteName(ProjectResourceName projectresourcename) {

        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectResourceName SET active=:active WHERE id=:id").
                setLong("id", projectresourcename.getId()).
                setBoolean("active", false).
                executeUpdate();
    }

    //under development
    public void delete(ProjectResource projectresource) {
        super.delete(projectresource);
    }

    public ProjectResourceName getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectResourceName.class).add(Expression.eq("id", id));
        ProjectResourceName p = (ProjectResourceName) criteria.list().get(0);
        return p;
    }

    public List list(int offset) {
        if (this.project_id == null) {
            return this.listResourceName(offset);
        } else {
            return this.listResource(offset, this.project_id);
        }
    }

    private List listResource(int offset, long project_id) {

        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectResource.class)
                                    .add(Expression.eq("active", true))
                                    .add(Expression.eq("project.id", project_id));

       
        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }

        criteria.setMaxResults(this.maxResult);
        criteria.setFirstResult(offset);

        List results = criteria.list();

        //count rows
        Criteria c = session.createCriteria(ProjectResource.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
        List countRow = c.list();

        float maxPage = countRow.get(0).hashCode() / Integer.valueOf(this.maxResult).floatValue();
        Double maxPageResults = Math.ceil(maxPage);

        ArrayList array = new ArrayList();
        array.add(0, results);
        array.add(1, countRow);
        array.add(2, maxPageResults.intValue());
        this.getHibernatetemplate().getSessionFactory().close();
        return array;
    }

    private List listResourceName(int offset) {

        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectResourceName.class).add(Expression.eq("active", true));

        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }

        criteria.setMaxResults(this.maxResult);
        criteria.setFirstResult(offset);

        List results = criteria.list();

        //count rows
        Criteria c = session.createCriteria(ProjectResourceName.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
        List countRow = c.list();

        float maxPage = countRow.get(0).hashCode() / Integer.valueOf(this.maxResult).floatValue();
        Double maxPageResults = Math.ceil(maxPage);

        ArrayList array = new ArrayList();
        array.add(0, results);
        array.add(1, countRow);
        array.add(2, maxPageResults.intValue());
        this.getHibernatetemplate().getSessionFactory().close();
        return array;
    }
}
