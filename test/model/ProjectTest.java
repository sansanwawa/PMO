/**
 *
 * @author sandy
 */
package model;

import helper.json.JSONException;
import java.util.List;
import helper.json.JSONObject;
import java.util.Iterator;
import sands.dao.implementors.ProjectDAOImpl;
import sands.dao.implementors.ProjectFinancialDAOImpl;

public class ProjectTest extends SuperTest {

    protected ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    protected void setUp() throws Exception {
        System.out.println("Set UP");
        super.SuperTest();
        projectDAO.setSessionFactory(sessionFactory);
    }

    protected void tearDown() {
        //destructor
        //System.out.println("Tearing down ");
    }

    public void testSave() {
        Project project = new Project();
        project.setName("Test JUnit");
        //this.projectDAO.save(project);
    }

    public void testCountRow() {
        projectDAO.setMaxResults(0);
        List listUser = (List) projectDAO.list(0).get(0);
        //this.assertEquals(0, listUser.size());
    }

    public void testListJson() throws JSONException {
        List listUser = (List) projectDAO.list(0).get(0);
        JSONObject json = new JSONObject();
        json.put("total", listUser.size());
        json.put("success", true);

        Iterator iterator = listUser.iterator();

        while (iterator.hasNext()) {
            Project p = (Project) iterator.next();

            //ProjectFinancialDAOImpl projectFinImpl = new ProjectFinancialDAOImpl();
             //projectFinImpl.setProjectId(p.getId());
            //List sum = projectFinImpl.getSumByProjectId();
            JSONObject map = new JSONObject();


 

            String financialSum = (String) ProjectFinancialDAOImpl.getSumByProjectId(p.getId()).get(0);
            Double financialValue = Math.ceil((Double.parseDouble(financialSum) / p.getProjectValue() ) * 100);

            map.put("id", p.getId());
           // map.put("name", p.getName());
            //map.put("financial", p.getProjectFinancialObj().size());
            map.put("financialSum", financialValue);


            json.append("data", map);
        }
        System.out.println(json.toString());



    }
}
