/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.general.BinderHelper;
import helper.json.JSONException;
import helper.json.JSONObject;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.Project;
import model.ProjectFinancial;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sands.dao.interfaces.ProjectFinancialDAO;

/**
 *
 * @author sandy
 */
@Controller
@RequestMapping(value = "/projectfinancial")
public class ProjectFinancialController extends BinderHelper {

    private ProjectFinancialDAO projectFinancialDAO;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public void setProjectFinancialDAO(ProjectFinancialDAO projectFinancialDAO) {
        this.projectFinancialDAO = projectFinancialDAO;

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

        projectFinancialDAO.setProjectId(Long.parseLong(project_id));
        List countProject = (List) projectFinancialDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //set the limit
        projectFinancialDAO.setMaxResults(limit);

        //set order by
        projectFinancialDAO.orderBy(sort, dir);
        List projectResource = (List) projectFinancialDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = projectResource.iterator();

        while (iterator.hasNext()) {
            ProjectFinancial p = (ProjectFinancial) iterator.next();
            JSONObject map = new JSONObject();
            map.put("id", p.getId());
            map.put("name", p.getProjectFinName());
            map.put("date", p.getProjectFinDate());
            map.put("status", p.getProjectFinStatus());
            map.put("note", p.getProjectFinNote());
            map.put("project_id", p.getProject().getId());
            json.append("data", map);
        }
        Writer w = json.write(responseWrapper.getWriter());
        w.flush();
        w.close();

    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectFinancial") ProjectFinancial projectfinancial,
            @RequestParam("project_id") long project_id,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Project p = new Project();
        p.setId(project_id);
        projectfinancial.setProject(p);
        projectFinancialDAO.save(projectfinancial);

        Writer out = response.getWriter();
        out.write("{success:true}");
        out.flush();
        out.close();
    }
}
