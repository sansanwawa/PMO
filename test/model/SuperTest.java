/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author sandy
 */
public class SuperTest extends TestCase  {

    protected SessionFactory sessionFactory;

    protected void SuperTest() throws Exception {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(ProjectDocument.class);
        configuration.addAnnotatedClass(ProjectFinancial.class);
        configuration.addAnnotatedClass(ProjectLegal.class);
        configuration.addAnnotatedClass(ProjectResource.class);
        configuration.addAnnotatedClass(ProjectSchedule.class);
        configuration.addAnnotatedClass(ProjectResourceName.class);
        configuration.addAnnotatedClass(ProjectScheduleName.class);
        configuration.addAnnotatedClass(ProjectInternalCost.class);
        AnnotationConfiguration conf = configuration.configure("hibernateTest.cfg.xml");
        sessionFactory = conf.buildSessionFactory();
    }
}
