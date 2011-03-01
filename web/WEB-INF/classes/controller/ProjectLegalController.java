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
import model.ProjectLegal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sands.dao.interfaces.ProjectLegalDAO;

/**
 *
 * @author sandy
 */
@Controller
@RequestMapping(value = "/projectlegal")
public class ProjectLegalController {

    private ProjectLegalDAO projectLegalDAO;

    @Autowired
    public void setProjectLegalDAO(ProjectLegalDAO projectLegalDAO) {
        this.projectLegalDAO = projectLegalDAO;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable("id") long project_legal_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        ProjectLegal projectLegal = projectLegalDAO.getById(project_legal_id);

        JSONObject json = new JSONObject();
        json.put("total", 1);
        json.put("success", true);
        JSONObject map = new JSONObject();
        map.put("id", projectLegal.getId());
        map.put("project_id", projectLegal.getProject().getId());
        map.put("addend_note", projectLegal.getAddendumNote());
        map.put("addend_status", projectLegal.getAddendumStatus());
        map.put("kontrak_note", projectLegal.getKontrakNote());
        map.put("kontrak_status", projectLegal.getKontrakStatus());
        map.put("spk_note", projectLegal.getSpkNote());
        map.put("spk_status", projectLegal.getSpkStatus());
        map.put("other_note", projectLegal.getOtherNote());
        map.put("other_status", projectLegal.getOtherStatus());
        json.append("data", map);
        json.write(responseWrapper.getWriter());
    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectLegal") ProjectLegal projectlegal, BindingResult result, HttpServletResponse response) throws Exception {

        projectLegalDAO.save(projectlegal);
        Writer out = response.getWriter();
        out.write("{success:true}");
    }

   
}
