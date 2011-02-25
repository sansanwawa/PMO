/**
 *
 * @author sandy
 */
 

package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sands.dao.interfaces.ProjectInternalCostDAO;

@Controller
@RequestMapping(value = "/projectinternalcost")
public class ProjectInternalCostController {

    private ProjectInternalCostDAO projectInternalCostDAO;

    @Autowired
    public void setProjectInternalCostDAO(ProjectInternalCostDAO projectInternalCostDAO) {
        this.projectInternalCostDAO = projectInternalCostDAO;
    }

}
