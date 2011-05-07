/**
 *
 * @author sandy
 */
 

package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;


public class ProjectRole {

    /*
    @OneToMany(mappedBy = "projectResourceName")
    private List<ProjectResource> projectResources;
    */
    @Id
    @GeneratedValue
    @Column(name = "project_role_id")
    private long id;


    @Column(name = "project_role_name")
    private String name;

    @Column(name = "project_role_lastupdate")
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date projectLastUpdate = new Date();

    @Column(name = "project_role_created", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date projectCreatedDate = new Date();

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "active")
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProjectCreatedDate() {
        return projectCreatedDate;
    }

    public void setProjectCreatedDate(Date projectCreatedDate) {
        this.projectCreatedDate = projectCreatedDate;
    }

    public Date getProjectLastUpdate() {
        return projectLastUpdate;
    }

    public void setProjectLastUpdate(Date projectLastUpdate) {
        this.projectLastUpdate = projectLastUpdate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    

}
