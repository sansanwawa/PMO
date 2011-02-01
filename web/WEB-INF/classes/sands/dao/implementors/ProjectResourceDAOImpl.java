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

    public void save(ProjectResource projectResource) {
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
        this.delete(projectresource);
    }

    public ProjectResourceName getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectResourceName.class).add(Expression.eq("id", id));
        ProjectResourceName p = (ProjectResourceName) criteria.list().get(0);
        return p;
    }

    public ArrayList<ProjectResourceName> list(int offset) {

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
