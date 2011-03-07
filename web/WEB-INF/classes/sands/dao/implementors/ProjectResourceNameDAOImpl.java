/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import java.util.ArrayList;
import java.util.List;
import model.ProjectResourceName;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import sands.dao.interfaces.ProjectResourceNameDAO;

public class ProjectResourceNameDAOImpl extends Crud implements ProjectResourceNameDAO {

    public void save(ProjectResourceName projectResourceName) {
        projectResourceName.setCreatedBy(this.getPrincipal().getUsername());
        projectResourceName.setUpdateBy(this.getPrincipal().getUsername());
        super.save(projectResourceName);
    }

    public void delete(ProjectResourceName projectresourcename) {
        Session session = Crud.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectResourceName SET active=:active WHERE id=:id").
                setLong("id", projectresourcename.getId()).
                setBoolean("active", false).
                executeUpdate();
    }

    public ProjectResourceName getById(long id) {
        Session session = Crud.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectResourceName.class).add(Expression.eq("id", id));
        ProjectResourceName p = (ProjectResourceName) criteria.list().get(0);
        return p;
    }

    public void update(ProjectResourceName projectresourcename) {
        projectresourcename.setUpdateBy(this.getPrincipal().getUsername());
        super.update(projectresourcename);
    }

    public List list(int offset) {
        Session session = Crud.getHibernatetemplate().getSessionFactory().openSession();
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
