package sands.dao.implementors;

import sands.dao.interfaces.ProjectDAO;
import model.Project;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.ProjectDocument;
import model.ProjectFinancial;
import model.ProjectLegal;
import model.ProjectResource;
import model.ProjectSchedule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author sandy
 */
public class ProjectDAOImpl implements ProjectDAO {

    private HibernateTemplate hibernateTemplate;
    private String orderByType = "ASC";
    private String orderByField = "id";
    private int maxResult = 10;
    protected final Log logger = LogFactory.getLog(getClass());

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public static User getPrincipal() {
        return (User) ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }

    public void save(Project project) {

        ProjectDocument projectdocument = new ProjectDocument();
        ProjectLegal projectfinancial = new ProjectLegal();
        ProjectResource projectresource = new ProjectResource();
        ProjectSchedule projectschedule = new ProjectSchedule();
        Set<ProjectFinancial> projectFinancial = new HashSet<ProjectFinancial>();
        projectFinancial.add(new ProjectFinancial(""));
        project.setProjectdocument(projectdocument);
        project.setProjectFinancialObj(projectFinancial);
        project.setProjectlegal(projectfinancial);
        project.setProjectresourceObj(projectresource);
        project.setProjectscheduleObj(projectschedule);
        project.setCreatedBy(this.getPrincipal().getUsername());
        projectfinancial.setProject(project);
        projectdocument.setProject(project);
        projectresource.setProject(project);
        projectschedule.setProject(project);
        hibernateTemplate.save(project);

    }

    public void update(Project project) {
        project.setUpdateBy(this.getPrincipal().getUsername());
        hibernateTemplate.update(project);
    }

    public void delete(Project project) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Project p = (Project) session.load(Project.class, project.getId());
        session.createQuery("UPDATE Project SET active=:active WHERE id=:project_id").
                setLong("project_id", project.getId()).
                setBoolean("active", false).
                executeUpdate();
        session.createQuery("UPDATE ProjectDocument SET active=:active WHERE project_management_id=:project_document_id").
                setLong("project_document_id", p.getProjectdocument().getPROJECT_MANAGEMENT_ID()).
                setBoolean("active", false).
                executeUpdate();
    }

    public ArrayList<Project> list(int offset) {

        Session session = hibernateTemplate.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Project.class).add(Expression.eq("active", true));

        if (this.orderByType.equals("ASC")) {
            criteria.addOrder(Order.asc(this.orderByField));
        } else {
            criteria.addOrder(Order.desc(this.orderByField));
        }


        criteria.setMaxResults(this.maxResult);
        criteria.setFirstResult(offset);

        List results = criteria.list();

        //count rows
        Criteria c = session.createCriteria(Project.class).setFirstResult(0).add(Expression.eq("active", true)).setProjection(Projections.rowCount());
        List countRow = c.list();

        float maxPage = countRow.get(0).hashCode() / Integer.valueOf(this.maxResult).floatValue();
        Double maxPageResults = Math.ceil(maxPage);

        ArrayList array = new ArrayList();
        array.add(0, results);
        array.add(1, countRow);
        array.add(2, maxPageResults.intValue());

        hibernateTemplate.getSessionFactory().close();
        return array;

    }

    public void orderByDesc(String field) {
        this.orderByType = "DESC";
        if (field != null) {
            this.orderByField = field;
        }
    }

    public void orderByAsc(String field) {
        this.orderByType = "ASC";
        if (field != null) {
            this.orderByField = field;
        }
    }

    public void orderBy(String field, String type) {
        this.orderByType = type;
        this.orderByField = field;
    }

    public void setMaxResults(int maxResults) {
        this.maxResult = maxResults;
    }

    public Project getById(long id) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Project project = new Project();
        project.setId(id);
        Project p = (Project) session.load(Project.class, project.getId());
        return p;

    }
}
