/**
 *
 * @author sandy
 */
package helper.database;

import java.sql.SQLException;
import java.util.List;
import model.ProjectFinancial;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;

public class Calculation extends Crud {

    private Class className;
    private Criteria criteria;
    private Session session;



    public Calculation(Class nameClass) {
         session        = this.getHibernatetemplate().getSessionFactory().getCurrentSession();
         //criteria       = session.createCriteria(ProjectFinancial.class);
    }

    
    protected void setCalculationClassName(Class nameClass) {
        this.criteria = session.createCriteria(nameClass);
    }

     


    public List sum(String fieldToSum) throws SQLException {

        SimpleExpression[] ex = this.getExpression();

        if(ex != null) {
            for (int i = 0; i <=ex.length; i++) {
                    criteria.add(ex[i]);
            }
        }

        this.criteria.add(Expression.eq("active", true)).setProjection(Projections.sum(fieldToSum));
        this.session.clear();
        this.session.close();
        //this.session.disconnect().close();
        return criteria.list();
    }

    protected List count(String fieldToCount) throws SQLException {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria c = session.createCriteria(ProjectFinancial.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.count(fieldToCount));
        List i = c.list();
        session.clear();
        session.close();
        return i;
    }
}
