package controller;

import helper.json.JSONException;
import helper.json.JSONObject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import sands.dao.interfaces.UserDAO;
import model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;

import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponseWrapper;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//configuration is under dispatcher-servlet.xml
//see :
//  <bean name="/user/*.htm" class="com.vaannila.web.UserController" >
//  <property name="userDAO" ref="myUserDAO" />
//  </bean>
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserDAO userDAO;
    protected final Log logger = LogFactory.getLog(getClass());
    private static final String REDIRECT_LIST = "redirect:list";
    public static String EMPLOYEE_NAME_FIELD = "emp_name";
    public static String EMPLOYEE_PHONE_FIELD = "emp_phone";
    public static String TITLE = "Title";

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void json(@RequestParam("limit") int limit,
            @RequestParam("start") int start,
            @RequestParam("sort") String sort,
            @RequestParam("dir") String dir,
            @ModelAttribute("User") User user, BindingResult result, HttpServletResponse response) throws JSONException, IOException {
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);

        List countProject = (List) userDAO.list(0).get(1);
        Integer count = (Integer) countProject.get(0);

        //limit
        userDAO.setMaxResults(limit);
        //order by
        userDAO.orderBy(sort, dir);
        List listProject = (List) userDAO.list(start).get(0);

        JSONObject json = new JSONObject();
        json.put("total", count);
        json.put("success", true);
        Iterator iterator = listProject.iterator();

        while (iterator.hasNext()) {
            User u = (User) iterator.next();
            JSONObject map = new JSONObject();
            map.put("id", u.getId());
            map.put("name", u.getName());
            json.append("data", map);
        }
        json.write(responseWrapper.getWriter());

    }

    @RequestMapping(value = "/panel")
    public ModelAndView panel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("user/userExt");
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {

        logger.info("add Form!");
        String user_id = request.getParameter("id");

        if (user_id != null) {
            logger.info("user_id : " + user_id);
            Long id = Long.parseLong(user_id);
            user = userDAO.getById(id);
        }

        if (user == null) {
            user = new User();
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("user", user);
        return new ModelAndView("user/userForm", modelMap);
    }

    @RequestMapping(value = "/report")
    public String report(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String FileName = "myreport";
        HashMap hashTitle = new HashMap();
        hashTitle.put(TITLE, "Test Report");

        File file = new File("userreport.jrxml");
        String reportFileName = JasperCompileManager.compileReportToFile(request.getRealPath("WEB-INF/reports") + "/" + file);

        userDAO.setMaxResults(0);
        List listUser = (List) userDAO.list(0).get(0);

        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(listUser.toArray());
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

        return "user/userReport";

    }

    /**
     *
     *  DEPRECATED METHOD,REGARDING EXTJS
     * 
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.info("list initialized!");

        String StringOffset = request.getParameter("offset");
        String sort = request.getParameter("sort");
        String field = request.getParameter("field");
        if (StringOffset == null) {
            StringOffset = "0";
        }

        int offset = Integer.parseInt(StringOffset);
        ModelMap modelMap = new ModelMap();
        if (sort == null) {
            modelMap.addAttribute("sort", "asc");
        } else {
            if (sort.equals("desc")) {
                userDAO.orderBy(field, "ASC");
                modelMap.addAttribute("sort", "asc");
            } else {
                userDAO.orderBy(field, "DESC");
                modelMap.addAttribute("sort", "desc");
            }
        }

        List TotalData = (List) userDAO.list(offset).get(1);
        modelMap.addAttribute("userList", userDAO.list(offset).get(0));
        modelMap.addAttribute("totalData", TotalData.get(0));
        modelMap.addAttribute("maxPage", userDAO.list(offset).get(2));

        return new ModelAndView("user/userList", modelMap);
    }

    @RequestMapping(value = "/addProcess")
    public void addProcess(@ModelAttribute("ProjectFinancial") User user,
            BindingResult result,
            @RequestParam("id") Long user_id,
            HttpServletResponse response) throws Exception {

        if (user_id != null) {
            user.setId(user_id);
        }
        userDAO.save(user);
        Writer out = response.getWriter();
        out.write("{success:true}");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Long id = Long.parseLong(request.getParameter("id"));
        User user = new User();
        user.setId(id);
        userDAO.delete(user);
        logger.info("deleted user_id = " + id);
        return new ModelAndView(this.REDIRECT_LIST);
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "user/userLogin";
    }
}
