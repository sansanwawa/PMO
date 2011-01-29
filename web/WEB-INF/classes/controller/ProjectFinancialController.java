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
import org.springframework.web.bind.annotation.PathVariable;
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
public class ProjectFinancialController {

    private ProjectFinancialDAO projectFinancialDAO;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public void setProjectFinancialDAO(ProjectFinancialDAO projectFinancialDAO) {
        this.projectFinancialDAO = projectFinancialDAO;

    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable("id") long project_financial_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        logger.info("project_financial_id : " + project_financial_id);

        List projectFinancial = projectFinancialDAO.getById(project_financial_id);
        Iterator iterator = projectFinancial.iterator();
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("total", projectFinancial.size());

        while (iterator.hasNext()) {
            ProjectFinancial pf = (ProjectFinancial) iterator.next();
            JSONObject map = new JSONObject();
            map.put("id", pf.getPROJECT_FINANCIAL_ID());
            map.put("name", pf.getPROJECT_FIN_NAME());
            map.put("note", pf.getPROJECT_FIN_NOTE());
            map.put("value", pf.getPROJECT_FIN_VALUE());
            map.put("date",pf.getPROJECT_FIN_DATE() );
            json.append("data", map);
        }
        json.write(responseWrapper.getWriter());
    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@RequestParam("totalField") int totalField, @RequestParam("project_id") long project_id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        for (int i = 1; i <= totalField; i++) {
            String paymentName = request.getParameter("paymentName" + i);
            String paymentStatus = request.getParameter("payment" + i);
            String paymentRemark = request.getParameter("paymentNote" + i);
            String paymentDate = request.getParameter("paymentDate" + i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date paymentDateConvert = simpleDateFormat.parse(paymentDate);
            ProjectFinancial pf = new ProjectFinancial();
            Project p = new Project();
            p.setId(project_id);
            pf.setPROJECT_FIN_NAME(paymentName);
            pf.setPROJECT_FIN_VALUE(paymentStatus);
            pf.setPROJECT_FIN_NOTE(paymentRemark);
            pf.setPROJECT_FIN_DATE(paymentDateConvert);
            pf.setProject(p);
            projectFinancialDAO.save(pf);

        }

        Writer out = response.getWriter();

        out.write("{success:true}");


        // projectFinancialDAO.save(projectfinancial);
        //request.getParameter(null);

        // out.write("{success:true}");

    }
}
