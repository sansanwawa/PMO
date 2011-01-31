/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author sandy
 * CRUD HELPER!
 */
public class Crud {

    /**
     * Order By Type
     * ASC/DESC
     */
    protected String orderByType;
    /**
     * Order By Field
     * 
     */
    protected String orderByField;
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
