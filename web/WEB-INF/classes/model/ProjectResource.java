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

@Entity()
//@org.hibernate.annotations.Entity( dynamicUpdate = true)
@Table(name = "project_resource")
public class ProjectResource implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "project_resource_id")
    private long id;

    @Column(name = "project_resource_month")
    private String month;

    @Column(name = "project_resource_mandays_allocation")
    private int mandaysAllocation;

    @Column(name = "project_resource_mandays_usage")
    private int mandaysUsage;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @ManyToOne
    @JoinColumn(name = "project_name_id")
    private ProjectResourceName projectResourceName;

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
        return projectResourceName;
    }

    public void setProjectResourceName(ProjectResourceName pr) {
        this.projectResourceName = pr;
    }

    public int getMandaysAllocation() {
        return mandaysAllocation;
    }

    public void setMandaysAllocation(int mandaysAllocation) {
        this.mandaysAllocation = mandaysAllocation;
    }

    public int getMandaysUsage() {
        return mandaysUsage;
    }

    public void setMandaysUsage(int mandaysUsage) {
        this.mandaysUsage = mandaysUsage;
    }


    
}
