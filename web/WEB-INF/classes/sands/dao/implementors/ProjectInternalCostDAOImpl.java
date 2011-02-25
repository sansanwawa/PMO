/**
 *
 * @author sandy
 */
package sands.dao.implementors;

import helper.database.Crud;
import java.util.List;
import model.ProjectInternalCost;
import sands.dao.interfaces.ProjectInternalCostDAO;

public class ProjectInternalCostDAOImpl extends Crud implements ProjectInternalCostDAO {

    private Long project_id = null;

    public void setProjectId(Long project_id) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void save(ProjectInternalCost projectInternalCost) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(ProjectInternalCost projectInternalCost) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List list(int offset) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
