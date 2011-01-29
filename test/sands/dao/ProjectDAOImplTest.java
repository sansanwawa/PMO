/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao;

import sands.dao.implementors.ProjectDAOImpl;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.Project;
import model.ProjectDocument;
import model.ProjectFinancial;
import model.SuperTest;

/**
 *
 * @author sandy
 */
public class ProjectDAOImplTest extends SuperTest {

    protected ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    protected void setUp() throws Exception {
        System.out.println("Set UP");
        super.SuperTest();
        projectDAO.setSessionFactory(sessionFactory);
    }

    public void testHibrenateExpressionIn() {
        String s = "12,21";
        String c[] = s.split(",");

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }

        /*
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Project.class).add(Expression.in("id", c));
        List list = criteria.list();
        Iterator iter = list.iterator();
        while(iter.hasNext()){
        Project p = (Project) iter.next();
        System.out.println(p.getName());
        }
         */
    }

    public void testCountData() {
        List countProject = (List) projectDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);
        //System.out.println(count);
    }

    public void testList() {
        projectDAO.setMaxResults(10);
        List List = (List) projectDAO.list(10).get(0);
        Iterator iterator = List.iterator();
        while (iterator.hasNext()) {
            Project p = (Project) iterator.next();
            System.out.println(p.getId());
        }
    }


    public void testSaveData(){
        Project project = new Project();
        ProjectDocument projectDocument = new ProjectDocument();

        Set<ProjectFinancial> projectFinancial = new HashSet<ProjectFinancial>();
        projectFinancial.add(new ProjectFinancial());
        projectFinancial.add(new ProjectFinancial());       

        project.setName("test");
        project.setProjectdocument(projectDocument);
        project.setProjectFinancialObj(projectFinancial);
        projectDAO.save(project);

    }


}
