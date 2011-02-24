/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author sandy
 */
@Entity
@Table(name = "PROJECT_FINANCIAL")
public class ProjectFinancial implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "project_financial_id")
    private long id;
    @Column(name = "project_fin_name")
    private String projectFinName;
    @Column(name = "project_fin_value")
    private String projectFinValue;
    @Column(name = "project_fin_note")
    private String projectFinNote;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "project_fin_date")
    private Date projectFinDate;
    @Column(name = "project_fin_status")
    private String projectFinStatus;
    @Column(name = "active")
    private boolean active = true;
    @JoinColumn(name = "project_id")
    @ManyToOne
    private Project project;

    public ProjectFinancial() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public Date getProjectFinDate() {
        return projectFinDate;
    }

    public void setProjectFinDate(Date projectFinDate) {
        this.projectFinDate = projectFinDate;
    }

    public String getProjectFinName() {
        return projectFinName;
    }

    public void setProjectFinName(String projectFinName) {
        this.projectFinName = projectFinName;
    }

    public String getProjectFinNote() {
        return projectFinNote;
    }

    public void setProjectFinNote(String projectFinNote) {
        this.projectFinNote = projectFinNote;
    }

    public String getProjectFinValue() {
        return projectFinValue;
    }

    public void setProjectFinValue(String projectFinValue) {
        this.projectFinValue = projectFinValue;
    }

    public String getProjectFinStatus() {
        return projectFinStatus;
    }

    public void setProjectFinStatus(String projectFinStatus) {
        this.projectFinStatus = projectFinStatus;
    }
    
}
