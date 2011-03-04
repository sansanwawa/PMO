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
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.Project;
import model.ProjectSchedule;
import model.ProjectScheduleName;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sands.dao.interfaces.ProjectScheduleDAO;

/**
 *
 * @author sandy
 */
@Controller
@RequestMapping(value = "/projectschedule")
public class ProjectScheduleController extends BinderHelper {

    private ProjectScheduleDAO projectScheduleDAO;

    @Autowired
    public void setProjectScheduleDAO(ProjectScheduleDAO projectScheduleDAO) {
        this.projectScheduleDAO = projectScheduleDAO;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @RequestParam("project_id") String project_id,
            HttpServletResponse response) throws JSONException, IOException {

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        projectScheduleDAO.setProjectId(Long.parseLong(project_id));
        List countProject = (List) projectScheduleDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //set the limit
        projectScheduleDAO.setMaxResults(limit);

        //set order by
        projectScheduleDAO.orderBy(sort, dir);
        List projectResource = (List) projectScheduleDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = projectResource.iterator();

        while (iterator.hasNext()) {
            ProjectSchedule p = (ProjectSchedule) iterator.next();
            JSONObject map = new JSONObject();
            map.put("id", p.getId());
            map.put("projectScheduleName", p.getProjectScheduleName().getName());
            map.put("projectId", p.getProject().getId());
            map.put("projectScheduleId", p.getProjectScheduleName().getId());
            map.put("projectScheduleStatus", p.getProjectScheduleStatus());
            map.put("projectPlannedDate", p.getProjectPlannedDate());
            map.put("projectActualDate", p.getProjectActualDate());
            map.put("projectRevisedDate", p.getProjectRevisedDate());
            map.put("projectRemark", p.getProjectRemarks());
            json.append("data", map);
        }
        Writer w = json.write(responseWrapper.getWriter());
        w.flush();
        w.close();

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
        map.put("projectScheduleName", projectSchedule.getProjectScheduleName().getName());
        map.put("projectId", projectSchedule.getProject().getId());
        map.put("projectScheduleId", projectSchedule.getProjectScheduleName().getId());
        map.put("projectPlannedDate", projectSchedule.getProjectPlannedDate());
        map.put("projectRevisedDate", projectSchedule.getProjectRevisedDate());
        map.put("projectRemark", projectSchedule.getProjectRemarks());
        json.append("data", map);
        json.write(responseWrapper.getWriter());
    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectResource") ProjectSchedule projectSchedule,
            HttpServletRequest request,
            @RequestParam("project.id") Long project_id,
            @RequestParam("projectScheduleName.id") Long project_resource_name_id,
            HttpServletResponse response) throws Exception {
        Writer out = response.getWriter();


        Project project = new Project();
        ProjectScheduleName projectScheduleName = new ProjectScheduleName();
        project.setId(project_id);
        projectScheduleName.setId(project_resource_name_id);
        projectSchedule.setProject(project);
        projectSchedule.setProjectScheduleName(projectScheduleName);

        SimpleExpression[] ex = {Expression.eq("project.id", project_id),
            Expression.eq("projectScheduleName.id", project_resource_name_id),
            Expression.eq("active", true)
        };
        List datas = projectScheduleDAO.getByExpression(ex);
        if (datas.size() > 0) {
            projectSchedule.setProjectPlannedDate(null);
        }

        projectScheduleDAO.save(projectSchedule);
        out.write("{success:true}");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute("ProjectSchedule") ProjectSchedule projectSchedule,
            BindingResult result,
            HttpServletResponse response, HttpServletRequest request) throws Exception {

        String[] project_schedule_id = request.getParameterValues("id");

        for (int i = 0; i < project_schedule_id.length; i++) {
            projectSchedule.setId(Long.parseLong(project_schedule_id[i]));
            projectScheduleDAO.delete(projectSchedule);
        }
        Writer out = response.getWriter();
        out.write("{success:true}");
        out.flush();
        out.close();
    }
}
