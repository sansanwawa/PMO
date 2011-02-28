/**
 *
 * @author sandy
 */
 

package sands.dao;

import java.util.Iterator;
import java.util.List;
import model.ProjectResource;
import model.SuperTest;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.SimpleExpression;
import sands.dao.implementors.ProjectResourceDAOImpl;


public class ProjectResourceDAOImplTest extends SuperTest {
protected ProjectResourceDAOImpl projectResourceDAO = new ProjectResourceDAOImpl();

    protected void setUp() throws Exception {
        System.out.println("Set UP");
        super.SuperTest();
        projectResourceDAO.setSessionFactory(sessionFactory);
    }

    public void testGetByExpression(){
        SimpleExpression[] ex = { Expression.eq("project.id", Long.parseLong("1"))};
        List datas = projectResourceDAO.getByExpression(ex);
        System.out.println(datas.size());

        /*
        Iterator i = datas.listIterator();
        
         while(i.hasNext()){
            ProjectResource p = (ProjectResource) i.next();
            System.out.println(p.getId());

        }
         *
         */


    }



}
