/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao;

import sands.dao.implementors.ProjectFinancialDAOImpl;
import helper.json.JSONException;
import helper.json.JSONObject;
import java.util.Iterator;
import java.util.List;
import model.ProjectFinancial;
import model.SuperTest;

/**
 *
 * @author sandy
 */
public class ProjectFinancialDAOImplTest extends SuperTest {

    protected ProjectFinancialDAOImpl projectFinancialDAOImpl = new ProjectFinancialDAOImpl();

    protected void setUp() throws Exception {
        System.out.println("Set UP");
        super.SuperTest();
        projectFinancialDAOImpl.setSessionFactory(sessionFactory);
    }

    public void testGetById() throws JSONException {
        long id = 1;
        List list = projectFinancialDAOImpl.getById(id);

        System.out.println("size : " + list.size());
        
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("total", list.size());
        Iterator iterator = list.iterator();
        System.out.println("has next : " + iterator.hasNext());
        while (iterator.hasNext()) {
            ProjectFinancial pf = (ProjectFinancial) iterator.next();
            JSONObject map = new JSONObject();
            
            json.append("data", map);

        }

            System.out.println(json);


    }
}
