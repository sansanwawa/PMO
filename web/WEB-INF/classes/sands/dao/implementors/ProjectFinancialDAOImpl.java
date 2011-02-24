package sands.dao.implementors;

import helper.database.Crud;
import java.util.ArrayList;
import sands.dao.interfaces.ProjectFinancialDAO;
import java.util.List;
import model.ProjectFinancial;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

/**
 *
 * @author sandy
 */
public class ProjectFinancialDAOImpl extends Crud implements ProjectFinancialDAO {

    private Long project_id;

    public void save(ProjectFinancial projectFinancial) {
        this.saveOrUpdate(projectFinancial);
    }

    public void delete(ProjectFinancial projectFinancial) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectFinancial SET active=:active WHERE id=:project_financial_id").
                setLong("project_financial_id", projectFinancial.getId()).
                setBoolean("active", false).
                executeUpdate();
    }

    public List getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectFinancial.class).add(Expression.eq("project.id", id));
        return criteria.list();
    }

    public void setProjectId(Long project_id) {
        this.project_id = project_id;
    }

    public List list(int offset) {
         Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectFinancial.class).add(Expression.eq("active", true)).add(Expression.eq("project.id", project_id));


        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }

        criteria.setMaxResults(this.maxResult);
        criteria.setFirstResult(offset);

        List results = criteria.list();

        //count rows
        Criteria c = session.createCriteria(ProjectFinancial.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
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
