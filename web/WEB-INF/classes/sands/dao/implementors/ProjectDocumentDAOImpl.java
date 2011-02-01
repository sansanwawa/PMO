/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import sands.dao.interfaces.ProjectDocumentDAO;
import model.ProjectDocument;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;


public class ProjectDocumentDAOImpl extends Crud implements ProjectDocumentDAO {



    public void save(ProjectDocument projectdocument) {
        this.saveOrUpdate(projectdocument);
    }

    public void delete(ProjectDocument projectdocument) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectDocument SET active=:active WHERE id=:project_management_id").
                setLong("project_management_id", projectdocument.getPROJECT_MANAGEMENT_ID()).
                setBoolean("active", false).
                executeUpdate();
    }

    public ArrayList<ProjectDocument> list(int offset) {
        
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectDocument.class).add(Expression.eq("active", true));

        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }

        int firstResults = 0;
        if (this.maxResult != 0) {
            criteria.setMaxResults(this.maxResult);
            firstResults = offset * this.maxResult;
            firstResults = firstResults - this.maxResult;
        }


        criteria.setFirstResult(firstResults);
        List results = criteria.list();

        Criteria c = session.createCriteria(ProjectDocument.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
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


    public ProjectDocument getById(long id) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        ProjectDocument project = new ProjectDocument();
        project.setPROJECT_MANAGEMENT_ID(id);
        ProjectDocument p = (ProjectDocument) session.load(ProjectDocument.class, project.getPROJECT_MANAGEMENT_ID());
        return p;

    }
}
