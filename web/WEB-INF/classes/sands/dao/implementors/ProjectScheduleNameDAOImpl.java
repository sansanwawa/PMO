/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import java.util.ArrayList;
import java.util.List;
import model.ProjectScheduleName;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import sands.dao.interfaces.ProjectScheduleNameDAO;

public class ProjectScheduleNameDAOImpl extends Crud implements ProjectScheduleNameDAO {

    public void save(ProjectScheduleName projectScheduleName) {
        projectScheduleName.setCreatedBy(this.getPrincipal().getUsername());
        projectScheduleName.setUpdateBy(this.getPrincipal().getUsername());
        super.save(projectScheduleName);
    }

    public void update(ProjectScheduleName projectScheduleName) {
        projectScheduleName.setUpdateBy(this.getPrincipal().getUsername());
        super.update(projectScheduleName);
    }

    public void delete(ProjectScheduleName projectScheduleName) {
        Session session = Crud.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectScheduleName SET active=:active WHERE id=:project_schedule_name_id").
                setLong("project_schedule_name_id", projectScheduleName.getId()).
                setBoolean("active", false).
                executeUpdate();
    }

    public ProjectScheduleName getById(long id) {
        Session session = Crud.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectScheduleName.class).add(Expression.eq("id", id));
        ProjectScheduleName p = (ProjectScheduleName) criteria.list().get(0);
        return p;
    }

    public List list(int offset) {
        Session session = Crud.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectScheduleName.class).add(Expression.eq("active", true));

        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }

        criteria.setMaxResults(this.maxResult);
        criteria.setFirstResult(offset);

        List results = criteria.list();

        //count rows
        Criteria c = session.createCriteria(ProjectScheduleName.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
        List countRow = c.list();

        float maxPage = countRow.get(0).hashCode() / Integer.valueOf(this.maxResult).floatValue();
        Double maxPageResults = Math.ceil(maxPage);

        ArrayList array = new ArrayList();
        array.add(0, results);
        array.add(1, countRow);
        array.add(2, maxPageResults.intValue());
        Crud.getHibernatetemplate().getSessionFactory().close();
        return array;
    }
}
