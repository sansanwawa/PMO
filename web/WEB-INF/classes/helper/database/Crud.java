/**
 *
 * @author sandy
 * CRUD HELPER!
 */
package helper.database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


public class Crud {

    /**
     * Order By Type
     * ASC/DESC
     */
 
    protected String orderByType = "ASC";
    /**
     * Order By Field
     * 
     */
    protected String orderByField = "id";
    /**
     * Max Result / Limit
     */
    protected int maxResult = 10;
    /**
     * Logger
     */
    protected final Log logger = LogFactory.getLog(getClass());



    
    public void orderByDesc(String field) {
        this.orderByType = "DESC";
        if (field != null) {
            this.orderByField = field;
        }
    }

    public void orderByAsc(String field) {
        this.orderByType = "ASC";
        if (field != null) {
            this.orderByField = field;
        }
    }

    public void orderBy(String field, String type) {
        this.orderByType = type;
        this.orderByField = field;
    }

    public void setMaxResults(int maxResults) {
        this.maxResult = maxResults;
    }

    protected static User getPrincipal() {
        return (User) ((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}
