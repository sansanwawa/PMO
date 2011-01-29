/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sands.dao;

import model.ProjectDocument;
import model.SuperTest;
import sands.dao.implementors.ProjectDocumentDAOImpl;

/**
 *
 * @author sandy
 */
public class ProjectDocumentDAOImplTest extends SuperTest {

    ProjectDocumentDAOImpl projectDocumentDAOImpl = new ProjectDocumentDAOImpl();
    protected void setUp() throws Exception {
        System.out.println("Set UP");
        super.SuperTest();
        projectDocumentDAOImpl.setSessionFactory(sessionFactory);
    }
     public void testSave(){
        ProjectDocument  p = new ProjectDocument();
        
        projectDocumentDAOImpl.save(p);
    }
}
