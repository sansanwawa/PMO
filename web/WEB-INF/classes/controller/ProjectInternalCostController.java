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
import model.ProjectFinancial;
import model.ProjectInternalCost;
import model.ProjectResourceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sands.dao.interfaces.ProjectInternalCostDAO;

@Controller
@RequestMapping(value = "/projectinternalcost")
public class ProjectInternalCostController {

    private ProjectInternalCostDAO projectInternalCostDAO;

    @Autowired
    public void setProjectInternalCostDAO(ProjectInternalCostDAO projectInternalCostDAO) {
        this.projectInternalCostDAO = projectInternalCostDAO;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @RequestParam("project_id") String project_id,
            @ModelAttribute("ProjectFinancial") ProjectFinancial projectfinancial,
            BindingResult result, HttpServletResponse response) throws JSONException, IOException {

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        projectInternalCostDAO.setProjectId(Long.parseLong(project_id));
        List countProject = (List) projectInternalCostDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //set the limit
        projectInternalCostDAO.setMaxResults(limit);

        //set order by
        projectInternalCostDAO.orderBy(sort, dir);
        List projectResource = (List) projectInternalCostDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = projectResource.iterator();

        while (iterator.hasNext()) {
            ProjectInternalCost p = (ProjectInternalCost) iterator.next();
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
    public void addProcess(@ModelAttribute("ProjectInternalCost") ProjectInternalCost projectinternalcost,
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

        //setter
        projectinternalcost.setMandaysUsage(Integer.parseInt(mandaysUsage));
        projectinternalcost.setMandaysAllocation(Integer.parseInt(mandaysAllocation));
        projectinternalcost.setMonth(month);
        projectinternalcost.setProject(project);
        projectinternalcost.setProjectResourceName(projectresourcename);
        projectInternalCostDAO.save(projectinternalcost);
        out.write("{success:true}");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute("ProjectInternalCost") ProjectInternalCost projectinternalcost,
            BindingResult result,
            HttpServletResponse response, HttpServletRequest request) throws Exception {

        String[] project_resource_id = request.getParameterValues("id");

        for (int i = 0; i < project_resource_id.length; i++) {
            projectinternalcost.setId(Long.parseLong(project_resource_id[i]));
            projectInternalCostDAO.delete(projectinternalcost);
        }
        Writer out = response.getWriter();
        out.write("{success:true}");
        out.flush();
        out.close();
    }
}
