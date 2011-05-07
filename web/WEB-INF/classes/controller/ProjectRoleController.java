/**
 *
 * @author sandy
 */
package controller;

import helper.json.JSONException;
import helper.json.JSONObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.ProjectRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import sands.dao.interfaces.ProjectRoleDAO;


@Controller
@RequestMapping(value = "/projectrole")
public class ProjectRoleController {

    private ProjectRoleDAO projectRoleDAO;

    @Autowired
    public void setProjectDAO(ProjectRoleDAO projectRoleDAO) {
        this.projectRoleDAO = projectRoleDAO;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @ModelAttribute("ProjectRole") ProjectRole projectRole, BindingResult result, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        ArrayList countProject = (ArrayList) (List) projectRoleDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //limit
        projectRoleDAO.setMaxResults(limit);

        //order by
        projectRoleDAO.orderBy(sort, dir);

        //list
        ArrayList listProject = (ArrayList) (List) projectRoleDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = listProject.iterator();

        while (iterator.hasNext()) {
            ProjectRole p = (ProjectRole) iterator.next();
            JSONObject map = new JSONObject();

            map.put("id", p.getId());
            map.put("name", p.getName());
            json.append("data", map);

        }

        Writer w = json.write(responseWrapper.getWriter());
        json = null;
        w.flush();
        w.close();
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("project/projectRoleList");
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public void add(@PathVariable("id") long project_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        ProjectRole project = projectRoleDAO.getById(project_id);

        JSONObject json = new JSONObject();
        json.put("total", 1);
        json.put("success", true);
        JSONObject map = new JSONObject();
        map.put("id", project.getId());
        map.put("name", project.getName());
        json.append("data", map);
        Writer w = json.write(responseWrapper.getWriter());
        w.flush();
        w.close();

    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("Project") ProjectRole projectRole,
            BindingResult result,
            @RequestParam("id") String project_id,
            HttpServletResponse response) throws Exception {

        if (project_id != null && project_id.isEmpty() == false) {
            Long id = Long.parseLong(project_id);
            projectRole.setId(id);
            projectRoleDAO.update(projectRole);

        } else {
            projectRoleDAO.save(projectRole);
        }
        Writer out = response.getWriter();
        out.write("{success:true}");

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") String project_id, HttpServletResponse response)
            throws Exception {

        Writer out = response.getWriter();
        if (project_id.isEmpty()) {
            out.write("{success:false}");
            return;
        }

        String[] project_ids = project_id.split(",");
        for (int i = 0; i < project_ids.length; i++) {
            ProjectRole p = new ProjectRole();
            p.setId(Long.parseLong(project_ids[i]));
            this.projectRoleDAO.delete(p);
        }
        out.write("{success:true}");
    }
}
