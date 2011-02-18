/**
 *
 * @author sandy
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_RESOURCE")
public class ProjectResource implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "project_resource_id")
    private long id;

    @Column(name = "project_resource_month")
    private String month;

    @Column(name = "project_resource_mandays")
    private int mandays;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @ManyToOne
    @JoinColumn(name = "project_name_id")
    private ProjectResourceName projectresourcename;

    @Column(name = "update_by")
    private String updateBy;
    
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "active")
    private boolean active = true;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getMandays() {
        return mandays;
    }

    public void setMandays(int mandays) {
        this.mandays = mandays;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ProjectResourceName getProjectResourceName() {
        return projectresourcename;
    }

    public void setProjectResourceName(ProjectResourceName projectresourcename) {
        this.projectresourcename = projectresourcename;
    }

    
}
