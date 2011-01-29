/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.json.JSONException;
import helper.json.JSONObject;
import org.springframework.validation.BindingResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import sands.dao.interfaces.ProjectDocumentDAO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.ProjectDocument;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sandy
 */
@Controller
@RequestMapping(value = "/projectdocument")
public class ProjectDocumentController {

    private ProjectDocumentDAO projectDocumentDAO;
    protected final Log logger = LogFactory.getLog(getClass());
    public static String TITLE = "Title";

    @Autowired
    public void setProjectDocumentDAO(ProjectDocumentDAO projectDocumentDAO) {
        this.projectDocumentDAO = projectDocumentDAO;

    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable("id") long project_document_id, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        ProjectDocument projectDocument = projectDocumentDAO.getById(project_document_id);

        JSONObject json = new JSONObject();
        json.put("total", 1);
        json.put("success", true);
        JSONObject map = new JSONObject();
        map.put("id", projectDocument.getPROJECT_MANAGEMENT_ID());
        map.put("bast", projectDocument.getPROJECT_BAST());
        map.put("bast_note", projectDocument.getPROJECT_BAST_NOTE());
        map.put("change_request", projectDocument.getPROJECT_CHANGE_REQUEST());
        map.put("change_request_note", projectDocument.getPROJECT_CHANGE_REQUEST_NOTE());
        map.put("contract", projectDocument.getPROJECT_CONTRACT());
        map.put("contract_note", projectDocument.getPROJECT_CONTRACT_NOTE());
        map.put("contract_addendum", projectDocument.getPROJECT_CONTRACT_ADDENDUM());
        map.put("contract_addendum_note", projectDocument.getPROJECT_CONTRACT_ADDENDUM_NOTE());
        map.put("cr", projectDocument.getPROJECT_CR());
        map.put("cr_note", projectDocument.getPROJECT_CR_NOTE());
        map.put("do", projectDocument.getPROJECT_DO());
        map.put("do_note", projectDocument.getPROJECT_DO_NOTE());
        map.put("htd", projectDocument.getPROJECT_HTD());
        map.put("htd_note", projectDocument.getPROJECT_HTD_NOTE());
        map.put("implementation_mom", projectDocument.getPROJECT_IMPLEMENTATION_MOM());
        map.put("implementation_mom_note", projectDocument.getPROJECT_IMPLEMENTATION_MOM_NOTE());
        map.put("jps", projectDocument.getPROJECT_JPS());
        map.put("jps_note", projectDocument.getPROJECT_JPS_NOTE());
        map.put("kod", projectDocument.getPROJECT_KOD());
        map.put("kod_note", projectDocument.getPROJECT_KOD_NOTE());
        map.put("mal", projectDocument.getPROJECT_MAL());
        map.put("mal_note", projectDocument.getPROJECT_MAL_NOTE());
        map.put("me", projectDocument.getPROJECT_ME());
        map.put("me_note", projectDocument.getPROJECT_ME_NOTE());
        map.put("mom", projectDocument.getPROJECT_MOM());
        map.put("mom_note", projectDocument.getPROJECT_MOM_NOTE());
        map.put("poc", projectDocument.getPROJECT_POC());
        map.put("poc_note", projectDocument.getPROJECT_POC_NOTE());
        map.put("psr", projectDocument.getPROJECT_PSR());
        map.put("psr_note", projectDocument.getPROJECT_PSR_NOTE());
        map.put("rfp", projectDocument.getPROJECT_RFP());
        map.put("rfp_note", projectDocument.getPROJECT_RFP_NOTE());
        map.put("rfp_addendum", projectDocument.getPROJECT_RFP_ADENDUM());
        map.put("rfp_addendum_note", projectDocument.getPROJECT_RFP_ADENDUM_NOTE());
        map.put("rml", projectDocument.getPROJECT_RML());
        map.put("rml_note", projectDocument.getPROJECT_RML_NOTE());
        map.put("spk", projectDocument.getPROJECT_SPK());
        map.put("spk_note", projectDocument.getPROJECT_SPK_NOTE());
        map.put("taf", projectDocument.getPROJECT_TAF());
        map.put("taf_note", projectDocument.getPROJECT_TAF_NOTE());
        map.put("tp", projectDocument.getPROJECT_TP());
        map.put("tp_note", projectDocument.getPROJECT_TP_NOTE());
        map.put("training", projectDocument.getPROJECT_TRAINING());
        map.put("training_note", projectDocument.getPROJECT_TRAINING_NOTE());
        map.put("uat", projectDocument.getPROJECT_UAT());
        map.put("uat_note", projectDocument.getPROJECT_UAT_NOTE());
        json.append("data", map);
        json.write(responseWrapper.getWriter());
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String FileName = "project_document";
        HashMap hashTitle = new HashMap();
        hashTitle.put(TITLE, "Test Report");

        Long id = Long.parseLong(request.getParameter("id"));

        File file = new File("projectdocumentreport.jrxml");
        String reportFileName = JasperCompileManager.compileReportToFile(request.getRealPath("WEB-INF/reports") + "/" + file);

        ProjectDocument[] projectdocument = {projectDocumentDAO.getById(id)};
        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(projectdocument);


        JasperPrint report = JasperFillManager.fillReport(reportFileName, hashTitle, dataSource);

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameters(new HashMap());
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, report);
        JasperExportManager.exportReportToPdfFile(report, request.getRealPath("WEB-INF/reports") + "/" + FileName + ".pdf");

        FileInputStream fileInputStream = new FileInputStream(new File(request.getRealPath("WEB-INF/reports") + "/" + FileName + ".pdf"));
        ServletOutputStream outputStream = response.getOutputStream();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + FileName + ".pdf");

        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, n);
        }
        outputStream.flush();
        fileInputStream.close();
        outputStream.close();

        return "projectdocument/projectDocumentReport";

    }

    @RequestMapping(value = "/addProcess", method = RequestMethod.POST)
    public void addProcess(@ModelAttribute("ProjectDocument") ProjectDocument projectdocument, BindingResult result, HttpServletResponse response) throws Exception {
        projectDocumentDAO.save(projectdocument);
        Writer out = response.getWriter();
        out.write("{success:true}");
    }
   
}
