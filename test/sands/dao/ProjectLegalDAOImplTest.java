/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao;

import helper.json.JSONException;
import helper.json.JSONObject;
import java.util.Iterator;
import java.util.List;
import model.ProjectFinancial;
import model.ProjectLegal;
import model.SuperTest;
import sands.dao.implementors.ProjectLegalDAOImpl;

/**
 *
 * @author sandy
 */
public class ProjectLegalDAOImplTest extends SuperTest {

    protected ProjectLegalDAOImpl projectLegalDAOImpl = new ProjectLegalDAOImpl();

    protected void setUp() throws Exception {
        System.out.println("Set UP");
        super.SuperTest();
        projectLegalDAOImpl.setSessionFactory(sessionFactory);
    }

    public void testGetById() throws JSONException {
        Long id = new Long(1);
        ProjectLegal projectLegal = projectLegalDAOImpl.getById(id);
        //System.out.println("add note : " + projectLegal.getAddendumNote());
    }

    public void testSave(){
        ProjectLegal p = new ProjectLegal();
        p.setId(new Long(1));
       
        p.setAddendumNote("zzzz");
     

        projectLegalDAOImpl.save(p);
    }
}
