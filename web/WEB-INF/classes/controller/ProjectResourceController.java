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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
            map.put("projectresourcename", p.getProjectResourceName().getName());
            map.put("projectresourceid", p.getProjectResourceName().getId());
            map.put("projectid", p.getProject().getId());
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
    public void addProcess(@ModelAttribute("ProjectResource") ProjectResource projectresource,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Writer out = response.getWriter();
        String project_id = request.getParameter("project_id");
        String mandaysUsage = request.getParameter("mandaysUsage");
        String mandaysAllocation = request.getParameter("mandaysAllocation");
        String month = request.getParameter("month");
        String projectResourceName = request.getParameter("projectresourcename");

        //declare Project
        Project project = new Project();
        project.setId(Long.parseLong(project_id));

        //declare ProjectResourceName
        ProjectResourceName projectresourcename = new ProjectResourceName();
        projectresourcename.setId(Long.parseLong(projectResourceName));


        projectresource.setMandaysUsage(Integer.parseInt(mandaysUsage));
        projectresource.setMandaysAllocation(Integer.parseInt(mandaysAllocation));
        projectresource.setMonth(month);
        projectresource.setProject(project);
        projectresource.setProjectResourceName(projectresourcename);
        projectResourceDAO.save(projectresource);
        out.write("{success:true}");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() throws Exception {
        return new ModelAndView("project/projectResourceList");
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute("ProjectResource") ProjectResource projectresource,
            BindingResult result,
            HttpServletResponse response, HttpServletRequest request) throws Exception {

        String[] project_resource_id = request.getParameterValues("id");

        for (int i = 0; i < project_resource_id.length; i++) {
            projectresource.setId(Long.parseLong(project_resource_id[i]));
            projectResourceDAO.delete(projectresource);
        }
        Writer out = response.getWriter();
        out.write("{success:true}");
    }
}
