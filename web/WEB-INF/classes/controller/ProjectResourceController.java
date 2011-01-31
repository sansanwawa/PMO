/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.json.JSONException;
import helper.json.JSONObject;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.ProjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sands.dao.interfaces.ProjectResourceDAO;

/**
 *
 * @author sandy
 */
@Controller
@RequestMapping(value = "/projectresource")
public class ProjectResourceController {

    private ProjectResourceDAO projectResourceDAO;

    @Autowired
    public void setProjectResourceDAO(ProjectResourceDAO projectResourceDAO) {
        this.projectResourceDAO = projectResourceDAO;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable("id") long project_resource_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        System.out.println("project_resource_id : " + project_resource_id);
        ProjectResource projectResource = projectResourceDAO.getById(project_resource_id);

        JSONObject json = new JSONObject();
        json.put("total", 1);
        json.put("success", true);
        JSONObject map = new JSONObject();
        map.put("id", projectResource.getId());
        map.put("man_first", projectResource.getProjectManFirst());
        map.put("man_second", projectResource.getProjectManSecond());
        map.put("man_third", projectResource.getProjectManThird());
        map.put("man_fourth", projectResource.getProjectManFourth());
        map.put("cons_first_1", projectResource.getProjectConsFirst1());
        map.put("cons_first_2", projectResource.getProjectConsFirst2());
        map.put("cons_first_3", projectResource.getProjectConsFirst3());
        map.put("cons_first_4", projectResource.getProjectConsFirst4());
        map.put("cons_second_1", projectResource.getProjectConsSecond1());
        map.put("cons_second_2", projectResource.getProjectConsSecond2());
        map.put("cons_second_3", projectResource.getProjectConsSecond3());
        map.put("cons_second_4", projectResource.getProjectConsSecond4());
        map.put("cons_third_1", projectResource.getProjectConsThird1());
        map.put("cons_third_2", projectResource.getProjectConsThird2());
        map.put("cons_third_3", projectResource.getProjectConsThird3());
        map.put("cons_third_4", projectResource.getProjectConsThird4());
        json.append("data", map);
        json.write(responseWrapper.getWriter());
    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectResource") ProjectResource projectresource, BindingResult result, HttpServletResponse response) throws Exception {

        Writer out = response.getWriter();
        projectResourceDAO.save(projectresource);
        out.write("{success:true}");
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() throws Exception {
        return new ModelAndView("project/projectResourceList");
    }



}
