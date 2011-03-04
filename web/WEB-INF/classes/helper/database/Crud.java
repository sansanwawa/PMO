/**
 *
 * @author sandy
 * CRUD HELPER!
 */
package helper.database;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class Crud {

    /**
     * Order By Type
     * ASC/DESC
     */
    protected String orderByType = "ASC";
    /**
     * Order By Field
     * 
     */
    protected String orderByField = "id";
    /**
     * Max Result / Limit
     */
    protected int maxResult = 10;
    /**
     * Logger
     */
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * Hibernate Template
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * Array of Expression
     */
    private SimpleExpression[] expression;


    /**
     * Inject Session
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    /**
     * Order By Field DESC
     * @param String field
     */
    public void orderByDesc(String field) {
        this.orderByType = "DESC";
        if (field != null) {
            this.orderByField = field;
        }
    }

    /**
     * Order By Field ASC
     * @param String field
     */
    public void orderByAsc(String field) {
        this.orderByType = "ASC";
        if (field != null) {
            this.orderByField = field;
        }
    }

    /**
     * Order By Field DESC
     * @param String field
     * @param String type
     */
    public void orderBy(String field, String type) {
        this.orderByType = type;
        this.orderByField = field;
    }

    /**
     * set max result / db limit
     * @param int maxResults
     */
    public void setMaxResults(int maxResults) {
        this.maxResult = maxResults;
    }

    /**
     * get principal login
     * @return User
     */
    protected static User getPrincipal() {
        return (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }

    /**
     * save database objects
     * override this methods for another purposes
     * @param Object objectname
     */
    protected void save(final Object objectname) {
        this.hibernateTemplate.save(objectname);

    }

    /**
     * update database objects
     * override this methods for another purposes
     * @param Object objectname
     */
    protected void update(Object objectname) {
        this.hibernateTemplate.update(objectname);
    }

    /**
     * Save Or Update database objects
     * override this methods for another purposes
     * @param Object objectname
     */
    protected void saveOrUpdate(Object objectname) {
        this.hibernateTemplate.saveOrUpdate(objectname);
    }

    /**
     * Delete Database Objects
     * @param Object objectname
     */
    protected void delete(Object objectname) {
        this.hibernateTemplate.delete(objectname);
    }

    /**
     * 
     * @return HibernateTemplate
     */
    protected HibernateTemplate getHibernatetemplate() {
        return this.hibernateTemplate;
    }

    /**
     *
     * @param SimpleExpression[] ex
     * @param Class classname
     * @return List
     */
    protected List getByExpression(SimpleExpression[] ex, Class classname) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(classname);
        for (int i = 0; i < ex.length; i++) {
            criteria.add(ex[i]);
        }
        this.getHibernatetemplate().getSessionFactory().close();
        return criteria.list();
    }



    /**
     * BETA!
     * @param Expression ex
     */
    protected void setExpression(SimpleExpression[] ex){
            this.expression = ex;
    }

    /**
     * BETA!
     * @return Expression[]
     */
    protected SimpleExpression[] getExpression(){
            return this.expression;
    }






    /**
     * List all Datas
     * @param Class className
     * @param Long id
     * @param int offset
     * @return List
     */

    protected List list(Class className,  Long id , int offset) {
        
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(className).add(Expression.eq("active", true));
     
        //will consider with this conditional
        if(id != null ) criteria.add(Expression.eq("project.id", id));

        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }

        criteria.setMaxResults(this.maxResult);
        criteria.setFirstResult(offset);

        List results = criteria.list();

        //count rows
        Criteria c = session.createCriteria(className).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
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
