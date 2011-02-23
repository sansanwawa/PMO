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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sands.dao.interfaces.ProjectResourceNameDAO;

@Controller
@RequestMapping(value = "/projectresourcename")
public class ProjectResourceNameController {

    private ProjectResourceNameDAO projectResourceNameDAO;

    @Autowired
    public void setProjectResourceDAO(ProjectResourceNameDAO projectResourceNameDAO) {
        this.projectResourceNameDAO = projectResourceNameDAO;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @ModelAttribute("ProjectResourceName") ProjectResourceName projectresourcename,
            BindingResult result, HttpServletResponse response) throws JSONException, IOException {

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);


        List countProject = (List) projectResourceNameDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //set the limit
        projectResourceNameDAO.setMaxResults(limit);

        //set order by
        projectResourceNameDAO.orderBy(sort, dir);
        List listProject = (List) projectResourceNameDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = listProject.iterator();

        while (iterator.hasNext()) {
            ProjectResourceName p = (ProjectResourceName) iterator.next();
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
    public void add(@PathVariable("id") long project_resource_name_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        ProjectResourceName projectResource = projectResourceNameDAO.getById(project_resource_name_id);
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
    public void addProcess(@ModelAttribute("ProjectResourceName") ProjectResourceName projectresourcename,
            BindingResult result,
            @RequestParam("id") String project_resource_id,
            HttpServletResponse response) throws Exception {

        Writer out = response.getWriter();

        //if there is an Id then update it..otherwise just save it!
        if (project_resource_id != null && project_resource_id.isEmpty() == false) {
            Long id = Long.parseLong(project_resource_id);
            projectresourcename.setId(id);
            projectResourceNameDAO.update(projectresourcename);
        } else {
            projectResourceNameDAO.save(projectresourcename);
        }

        out.write("{success:true}");
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute("ProjectResourceName") ProjectResourceName projectresourcename,
            BindingResult result,
            HttpServletResponse response, HttpServletRequest request) throws Exception {


        String[] project_resource_id = request.getParameterValues("id");

        for (int i = 0; i < project_resource_id.length; i++) {
            projectresourcename.setId(Long.parseLong(project_resource_id[i]));
            projectResourceNameDAO.delete(projectresourcename);
        }
        Writer out = response.getWriter();
        out.write("{success:true,data:" + project_resource_id.length + "}");
    }
}
