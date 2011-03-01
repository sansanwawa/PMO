/**
 *
 * @author sandy
 * CRUD HELPER!
 */
package helper.database;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    protected void save(final Object objectname){
        this.hibernateTemplate.save(objectname);
      
    }

    /**
     * update database objects
     * override this methods for another purposes
     * @param Object objectname
     */
    protected void update(Object objectname){
        this.hibernateTemplate.update(objectname);
    }

    /**
     * Save Or Update database objects
     * override this methods for another purposes
     * @param Object objectname
     */
    protected void saveOrUpdate(Object objectname){
        this.hibernateTemplate.saveOrUpdate(objectname);
    }

    /**
     * Delete Database Objects
     * @param Object objectname
     */
    protected void delete(Object objectname){
        this.hibernateTemplate.delete(objectname);
    }



    /**
     * 
     * @return HibernateTemplate
     */
    protected HibernateTemplate getHibernatetemplate(){
        return this.hibernateTemplate;
    }


    /**
     *
     * @param SimpleExpression[] ex
     * @param Class classname
     * @return List
     */


    protected List getByExpression(SimpleExpression[] ex ,Class classname) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(classname);
        for (int i = 0; i < ex.length; i++)  criteria.add(ex[i]);
        this.getHibernatetemplate().getSessionFactory().close();
        return criteria.list();
    }
   


}
