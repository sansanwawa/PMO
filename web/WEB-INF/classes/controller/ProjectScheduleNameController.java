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
import model.ProjectResourceName;
import model.ProjectScheduleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sands.dao.interfaces.ProjectScheduleNameDAO;

@Controller
@RequestMapping(value = "/projectschedulename")
public class ProjectScheduleNameController {

    private ProjectScheduleNameDAO projectScheduleNameDAO;

    @Autowired
    public void setProjectScheduleNameDAO(ProjectScheduleNameDAO projectScheduleNameDAO) {
        this.projectScheduleNameDAO = projectScheduleNameDAO;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("project/projectScheduleList");
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @ModelAttribute("ProjectScheduleName") ProjectResourceName projectresourcename,
            BindingResult result, HttpServletResponse response) throws JSONException, IOException {

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);


        List countProject = (List) projectScheduleNameDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //set the limit
        projectScheduleNameDAO.setMaxResults(limit);

        //set order by
        projectScheduleNameDAO.orderBy(sort, dir);
        List listProject = (List) projectScheduleNameDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = listProject.iterator();

        while (iterator.hasNext()) {
            ProjectScheduleName p = (ProjectScheduleName) iterator.next();
            JSONObject map = new JSONObject();
            map.put("id", p.getId());
            map.put("name", p.getName());
            json.append("data", map);
        }
        Writer w = json.write(responseWrapper.getWriter());
        w.flush();
        w.close();

    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable("id") long project_schedule_name_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        ProjectScheduleName projectResource = projectScheduleNameDAO.getById(project_schedule_name_id);
        JSONObject json = new JSONObject();
        json.put("total", 1);
        json.put("success", true);
        JSONObject map = new JSONObject();
        map.put("id", projectResource.getId());
        map.put("name", projectResource.getName());
        json.append("data", map);
        Writer writer = json.write(responseWrapper.getWriter());
        writer.flush();
        writer.close();
    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectResourceName") ProjectScheduleName projectScheduleName,
            BindingResult result,
            @RequestParam("id") String project_schedule_name_id,
            HttpServletResponse response) throws Exception {

        Writer out = response.getWriter();

        //if there is an Id then update it..otherwise just save it!
        if (project_schedule_name_id != null && project_schedule_name_id.isEmpty() == false) {
            Long id = Long.parseLong(project_schedule_name_id);
            projectScheduleName.setId(id);
            projectScheduleNameDAO.update(projectScheduleName);
        } else {
            projectScheduleNameDAO.save(projectScheduleName);
        }

        out.write("{success:true}");
    }
}
