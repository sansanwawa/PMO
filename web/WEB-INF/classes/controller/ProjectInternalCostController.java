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
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.SimpleExpression;
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
    public void addProcess(@ModelAttribute("ProjectInternalCost") ProjectInternalCost projectInternalCost,
            @RequestParam("project.id") Long project_id,
            @RequestParam("projectResourceName.id") Long project_resource_name_id,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Writer out = response.getWriter();

        //declare Project
        Project project = new Project();
        project.setId(project_id);

        //declare ProjectResourceName
        ProjectResourceName projectresourcename = new ProjectResourceName();
        projectresourcename.setId(project_resource_name_id);

        //setter
        projectInternalCost.setProject(project);
        projectInternalCost.setProjectResourceName(projectresourcename);

        SimpleExpression[] ex = {Expression.eq("project.id", project_id),
                                 Expression.eq("projectResourceName.id", project_resource_name_id),
                                 Expression.eq("active", true)
        };
        List datas = projectInternalCostDAO.getByExpression(ex);
        if (datas.size() > 0) {
            projectInternalCost.setMandaysAllocation(0);
        }
        projectInternalCostDAO.save(projectInternalCost);
        out.write("{success:true}");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute("ProjectInternalCost") ProjectInternalCost projectInternalCost,
            BindingResult result,
            HttpServletResponse response, HttpServletRequest request) throws Exception {

        String[] project_resource_id = request.getParameterValues("id");

        for (int i = 0; i < project_resource_id.length; i++) {
            projectInternalCost.setId(Long.parseLong(project_resource_id[i]));
            projectInternalCostDAO.delete(projectInternalCost);
        }
        Writer out = response.getWriter();
        out.write("{success:true}");
        out.flush();
        out.close();
    }
}
