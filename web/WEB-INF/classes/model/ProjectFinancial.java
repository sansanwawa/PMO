/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class ProjectFinancial {

    @Id
    @GeneratedValue
    @Column(name = "project_financial_id")
    private long PROJECT_FINANCIAL_ID;
    @Column(name = "project_fin_name")
    private String PROJECT_FIN_NAME;
    @Column(name = "project_fin_value")
    private String PROJECT_FIN_VALUE;
    @Column(name = "project_fin_note")
    private String PROJECT_FIN_NOTE;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "project_fin_date")
    private Date PROJECT_FIN_DATE;
    @Column(name = "active")
    private boolean active = true;
    @JoinColumn(name = "project_id")
    @ManyToOne
    private Project project;

    public ProjectFinancial() {
    }

    public ProjectFinancial(String name) {
        this.PROJECT_FIN_NAME = name;
    }

    public Date getPROJECT_FIN_DATE() {
        return PROJECT_FIN_DATE;
    }

    public void setPROJECT_FIN_DATE(Date PROJECT_FIN_DATE) {
        this.PROJECT_FIN_DATE = PROJECT_FIN_DATE;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getPROJECT_FINANCIAL_ID() {
        return PROJECT_FINANCIAL_ID;
    }

    public void setPROJECT_FINANCIAL_ID(long PROJECT_FINANCIAL_ID) {
        this.PROJECT_FINANCIAL_ID = PROJECT_FINANCIAL_ID;
    }

    public String getPROJECT_FIN_NAME() {
        return PROJECT_FIN_NAME;
    }

    public void setPROJECT_FIN_NAME(String PROJECT_FIN_NAME) {
        this.PROJECT_FIN_NAME = PROJECT_FIN_NAME;
    }

    public String getPROJECT_FIN_NOTE() {
        return PROJECT_FIN_NOTE;
    }

    public void setPROJECT_FIN_NOTE(String PROJECT_FIN_NOTE) {
        this.PROJECT_FIN_NOTE = PROJECT_FIN_NOTE;
    }

    public String getPROJECT_FIN_VALUE() {
        return PROJECT_FIN_VALUE;
    }

    public void setPROJECT_FIN_VALUE(String PROJECT_FIN_VALUE) {
        this.PROJECT_FIN_VALUE = PROJECT_FIN_VALUE;
    }
}
