/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.json.JSONException;
import helper.json.JSONObject;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.ProjectSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sands.dao.interfaces.ProjectScheduleDAO;

/**
 *
 * @author sandy
 */
@Controller
@RequestMapping(value = "/projectschedule")
public class ProjectScheduleController {

    private ProjectScheduleDAO projectScheduleDAO;

    @Autowired
    public void setProjectScheduleDAO(ProjectScheduleDAO projectScheduleDAO) {
        this.projectScheduleDAO = projectScheduleDAO;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable("id") long project_schedule_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        ProjectSchedule projectSchedule = projectScheduleDAO.getById(project_schedule_id);

        JSONObject json = new JSONObject();
        json.put("total", 1);
        json.put("success", true);
        JSONObject map = new JSONObject();
        map.put("id", projectSchedule.getId());
        map.put("ba_date", projectSchedule.getProjectScheduleBaDate());
        map.put("ba_planned", projectSchedule.getProjectScheduleBaPlanned());
        map.put("ba_remarks", projectSchedule.getProjectScheduleBaRemarks());
        map.put("ba_revised", projectSchedule.getProjectScheduleBaRevised());
        map.put("ba_status", projectSchedule.getProjectScheduleBaStatus());
        map.put("delivery_date", projectSchedule.getProjectScheduleDeliveryDate());
        map.put("delivery_planned", projectSchedule.getProjectScheduleDeliveryPlanned());
        map.put("delivery_remarks", projectSchedule.getProjectScheduleDeliveryRemarks());
        map.put("delivery_revised", projectSchedule.getProjectScheduleDeliveryRevised());
        map.put("delivery_status", projectSchedule.getProjectScheduleDeliveryStatus());
        map.put("doc_date", projectSchedule.getProjectScheduleDocDate());
        map.put("doc_planned", projectSchedule.getProjectScheduleDocPlanned());
        map.put("doc_remarks", projectSchedule.getProjectScheduleDocRemarks());
        map.put("doc_revised", projectSchedule.getProjectScheduleDocRevised());
        map.put("doc_status", projectSchedule.getProjectScheduleDocStatus());
        map.put("impl1_date", projectSchedule.getProjectScheduleImpl1Date());
        map.put("impl1_planned", projectSchedule.getProjectScheduleImpl1Planned());
        map.put("impl1_remarks", projectSchedule.getProjectScheduleImpl1Remarks());
        map.put("impl1_revised", projectSchedule.getProjectScheduleImpl1Revised());
        map.put("impl1_status", projectSchedule.getProjectScheduleImpl1Status());
        map.put("impl2_date", projectSchedule.getProjectScheduleImpl2Date());
        map.put("impl2_planned", projectSchedule.getProjectScheduleImpl2Planned());
        map.put("impl2_remarks", projectSchedule.getProjectScheduleImpl2Remarks());
        map.put("impl2_revised", projectSchedule.getProjectScheduleImpl2Revised());
        map.put("impl2_status", projectSchedule.getProjectScheduleImpl2Status());
        map.put("kickoff_date", projectSchedule.getProjectScheduleKickOffDate());
        map.put("kickoff_planned", projectSchedule.getProjectScheduleKickOffPlanned());
        map.put("kickoff_remarks", projectSchedule.getProjectScheduleKickOffRemarks());
        map.put("kickoff_revised", projectSchedule.getProjectScheduleKickOffRevised());
        map.put("kickoff_status", projectSchedule.getProjectScheduleKickOffStatus());
        map.put("pengadaan_date", projectSchedule.getProjectSchedulePengadaanDate());
        map.put("pengadaan_planned", projectSchedule.getProjectSchedulePengadaanPlanned());
        map.put("pengadaan_remarks", projectSchedule.getProjectSchedulePengadaanRemarks());
        map.put("pengadaan_revised", projectSchedule.getProjectSchedulePengadaanRevised());
        map.put("pengadaan_status", projectSchedule.getProjectSchedulePengadaanStatus());
        map.put("sit_date", projectSchedule.getProjectScheduleSitDate());
        map.put("sit_planned", projectSchedule.getProjectScheduleSitPlanned());
        map.put("sit_remarks", projectSchedule.getProjectScheduleSitRemarks());
        map.put("sit_revised", projectSchedule.getProjectScheduleSitRevised());
        map.put("sit_status", projectSchedule.getProjectScheduleSitStatus());
        map.put("stagging_date", projectSchedule.getProjectScheduleStagingDate());
        map.put("stagging_planned", projectSchedule.getProjectScheduleStagingPlanned());
        map.put("stagging_remarks", projectSchedule.getProjectScheduleStagingRemarks());
        map.put("stagging_revised", projectSchedule.getProjectScheduleStagingRevised());
        map.put("stagging_status", projectSchedule.getProjectScheduleStagingStatus());
        map.put("uat_date", projectSchedule.getProjectScheduleUatDate());
        map.put("uat_planned", projectSchedule.getProjectScheduleUatPlanned());
        map.put("uat_remarks", projectSchedule.getProjectScheduleUatRemarks());
        map.put("uat_revised", projectSchedule.getProjectScheduleUatRevised());
        map.put("uat_status", projectSchedule.getProjectScheduleUatStatus());
        json.append("data", map);
        json.write(responseWrapper.getWriter());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectSchedule") ProjectSchedule projectschedule, BindingResult result, HttpServletResponse response) throws Exception {
        projectScheduleDAO.save(projectschedule);
        Writer out = response.getWriter();
        out.write("{success:true}");
    }
}
