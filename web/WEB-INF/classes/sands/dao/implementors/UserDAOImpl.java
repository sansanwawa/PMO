/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import sands.dao.interfaces.UserDAO;
import java.util.List;
import model.User;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import org.hibernate.criterion.Projections;

public class UserDAOImpl extends Crud implements UserDAO {

    @Override
    public void save(User user) {
        this.saveOrUpdate(user);
    }

    public void delete(User user) {
        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        User userObj = (User) session.get(User.class, user.getId());
        userObj.setActive(Boolean.FALSE);
        this.getHibernatetemplate().saveOrUpdate(userObj);
    }




    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<User> list(int offset) {

        Session session = this.getHibernatetemplate().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class).add(Expression.eq("active", true));

        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }

        //offset = 2
        //totalRecord = 20
        //limit = 3
        //2 * 3 = (6 - 3) = 3
        //total / limit
        //20 / 10 = 2

        int firstResults = 0;


        if (this.maxResult != 0) {
            criteria.setMaxResults(this.maxResult);
            firstResults = offset * this.maxResult;
            firstResults = firstResults - this.maxResult;
        }

        criteria.setFirstResult(firstResults);
        List results = criteria.list();

        Criteria c = session.createCriteria(User.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
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

    public User getById(long id) {
        Session sf = this.getHibernatetemplate().getSessionFactory().openSession();
        User user = new User();
        user.setId(id);
        User resultUserObj = (User) sf.load(User.class, user.getId());
        return resultUserObj;
    }
}
