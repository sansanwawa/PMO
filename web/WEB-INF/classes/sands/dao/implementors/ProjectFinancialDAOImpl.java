package sands.dao.implementors;

import sands.dao.interfaces.ProjectFinancialDAO;
import java.util.List;

import model.ProjectFinancial;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author sandy
 */
public class ProjectFinancialDAOImpl implements ProjectFinancialDAO {

    private HibernateTemplate hibernateTemplate;
    protected final Log logger = LogFactory.getLog(getClass());

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void save(ProjectFinancial projectFinancial) {
        hibernateTemplate.saveOrUpdate(projectFinancial);
    }

    public void delete(ProjectFinancial projectFinancial) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        session.createQuery("UPDATE ProjectFinancial SET active=:active WHERE id=:project_financial_id").
                setLong("project_financial_id", projectFinancial.getPROJECT_FINANCIAL_ID()).
                setBoolean("active", false).
                executeUpdate();
    }

    public List getById(long id) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ProjectFinancial.class).add(Expression.eq("project.id", id));
        return criteria.list();
    }
}
