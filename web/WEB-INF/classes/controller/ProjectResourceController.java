/**
 *
 * @author sandy
 */
package controller;

import helper.json.JSONException;
import helper.json.JSONObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.Project;
import model.ProjectResource;
import model.ProjectResourceName;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sands.dao.interfaces.ProjectResourceDAO;

@Controller
@RequestMapping(value = "/projectresource")
public class ProjectResourceController {

    private ProjectResourceDAO projectResourceDAO;

    @Autowired
    public void setProjectResourceDAO(ProjectResourceDAO projectResourceDAO) {
        this.projectResourceDAO = projectResourceDAO;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @RequestParam("project_id") String project_id,
            @ModelAttribute("ProjectResourceName") ProjectResourceName projectresourcename,
            BindingResult result, HttpServletResponse response) throws JSONException, IOException {

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        projectResourceDAO.setProjectId(Long.parseLong(project_id));
        List countProject = (List) projectResourceDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //set the limit
        projectResourceDAO.setMaxResults(limit);

        //set order by
        projectResourceDAO.orderBy(sort, dir);
        List projectResource = (List) projectResourceDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = projectResource.iterator();

        while (iterator.hasNext()) {
            ProjectResource p = (ProjectResource) iterator.next();
            JSONObject map = new JSONObject();
            map.put("id", p.getId());
            map.put("projectResourceName", p.getProjectResourceName().getName());
            map.put("projectResourceId", p.getProjectResourceName().getId());
            map.put("projectId", p.getProject().getId());
            map.put("month", p.getMonth());
            map.put("mandaysUsage", p.getMandaysUsage());
            map.put("mandaysAllocation", p.getMandaysAllocation());
            json.append("data", map);
        }
        Writer w = json.write(responseWrapper.getWriter());
        w.flush();
        w.close();

    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectResource") ProjectResource projectResource,
            HttpServletRequest request,
            @RequestParam("project.id") Long project_id,
            @RequestParam("projectResourceName.id") Long project_resource_name_id,
            HttpServletResponse response) throws Exception {
        Writer out = response.getWriter();


        Project project = new Project();
        ProjectResourceName projectResourceName = new ProjectResourceName();
        project.setId(project_id);
        projectResourceName.setId(project_resource_name_id);
        projectResource.setProject(project);
        projectResource.setProjectResourceName(projectResourceName);

       SimpleExpression[] ex = { Expression.eq("project.id", project_id),
                                 Expression.eq("projectResourceName.id", project_resource_name_id),
                                 Expression.eq("active", true)
        };
        List datas = projectResourceDAO.getByExpression(ex);
        if (datas.size() > 0) {
            projectResource.setMandaysAllocation(0);
        }
        projectResourceDAO.save(projectResource);
        out.write("{success:true}");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute("ProjectResource") ProjectResource projectResource,
            BindingResult result,
            HttpServletResponse response, HttpServletRequest request) throws Exception {

        String[] project_resource_id = request.getParameterValues("id");

        for (int i = 0; i < project_resource_id.length; i++) {
            projectResource.setId(Long.parseLong(project_resource_id[i]));
            projectResourceDAO.delete(projectResource);
        }
        Writer out = response.getWriter();
        out.write("{success:true}");
        out.flush();
        out.close();
    }
}
