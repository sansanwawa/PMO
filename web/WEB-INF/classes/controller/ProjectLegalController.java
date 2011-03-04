/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.general.BinderHelper;
import helper.json.JSONException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.ProjectLegal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sands.dao.interfaces.ProjectLegalDAO;
import helper.json.JSONObject;
import java.io.IOException;
import model.Project;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sandy
 */
@Controller
@RequestMapping(value = "/projectlegal")
public class ProjectLegalController extends BinderHelper {


    private ProjectLegalDAO projectLegalDAO;

    @Autowired
    public void setProjectLegalDAO(ProjectLegalDAO projectLegalDAO) {
        this.projectLegalDAO = projectLegalDAO;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @RequestParam("project_id") String project_id,
            @ModelAttribute("ProjectLegal") ProjectLegal projectLegal,
            BindingResult result, HttpServletResponse response) throws JSONException, IOException {

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        projectLegalDAO.setProjectId(Long.parseLong(project_id));
        List countProject = (List) projectLegalDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //set the limit
        projectLegalDAO.setMaxResults(limit);

        //set order by
        projectLegalDAO.orderBy(sort, dir);
        List projectResource = (List) projectLegalDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = projectResource.iterator();

        while (iterator.hasNext()) {
            ProjectLegal p = (ProjectLegal) iterator.next();
            JSONObject map = new JSONObject();
            map.put("id", p.getId());
            map.put("project" , p.getProject().getId());
            map.put("projectLegalName",p.getProjectLegalName());
            map.put("projectLegalDate",p.getProjectLegalDate());
            map.put("projectLegalRequired",p.getProjectLegalRequired());
            map.put("projectLegalStatus",p.getProjectLegalStatus());
            json.append("data", map);
        }
        Writer w = json.write(responseWrapper.getWriter());
        w.flush();
        w.close();

    }




    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectLegal") ProjectLegal projectLegal, 
                @RequestParam("project.id") Long project_id,
                BindingResult result,
                HttpServletResponse response) throws Exception {

        Project project = new Project();
        project.setId(project_id);
        projectLegal.setProject(project);
        projectLegalDAO.save(projectLegal);
        Writer out = response.getWriter();
        out.write("{success:true}");
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute("ProjectResource") ProjectLegal projectLegal,
            BindingResult result,
            HttpServletResponse response, HttpServletRequest request) throws Exception {

        String[] project_legal_id = request.getParameterValues("id");

        for (int i = 0; i < project_legal_id.length; i++) {
            projectLegal.setId(Long.parseLong(project_legal_id[i]));
            projectLegalDAO.delete(projectLegal);
        }
        Writer out = response.getWriter();
        out.write("{success:true}");
        out.flush();
        out.close();
    }
}
