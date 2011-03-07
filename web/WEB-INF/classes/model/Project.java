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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author sandy
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private long id;
    @Column(name = "project_name")
    private String name;
    @Column(name = "active")
    private boolean active = true;
    @Column(name = "project_number", nullable = true)
    private Long projectNumber;
    @Column(name = "project_customer")
    private String projectCustomer;
    @Column(name = "project_lastupdate")
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    private Date projectLastUpdate = new Date();
    @Column(name = "project_created", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date projectCreatedDate = new Date();
    @Column(name = "project_start_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectStartDate;
    @Column(name = "project_end_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectEndDate;
    @Column(name = "project_financial")
    private String projectFinancial;
    @Column(name = "project_schedule")
    private String projectSchedule;
    @Column(name = "project_technical")
    private String projectTechnical;
    @Column(name = "project_resource")
    private String projectResource;
    @Column(name = "project_contract")
    private String projectContract;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @Column(name = "project_account_manager")
    private String accountManager;
    @Column(name = "project_project_manager")
    private String projectManager;
    @Column(name = "project_value")
    private double projectValue;
    /*FK*/
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "project")
    private ProjectDocument projectdocument;


    
    /*One to many*/
   // @OneToMany(cascade = CascadeType.ALL)
   // @JoinColumn(name = "project_id")
   // private Set<ProjectFinancial> projectFinancialObj = new HashSet<ProjectFinancial>(0);

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public ProjectDocument getProjectdocument() {
        return projectdocument;
    }

    public void setProjectdocument(ProjectDocument projectdocument) {
        this.projectdocument = projectdocument;
    }

    /*
    public Set<ProjectFinancial> getProjectFinancialObj() {
        return projectFinancialObj;
    }

    public void setProjectFinancialObj(Set<ProjectFinancial> projectFinancialObj) {
        this.projectFinancialObj = projectFinancialObj;
    }
    
     */

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProjectCustomer() {
        return projectCustomer;
    }

    public void setProjectCustomer(String projectCustomer) {
        this.projectCustomer = projectCustomer;
    }

    public Date getProjectLastUpdate() {
        return projectLastUpdate;
    }

    @PreUpdate
    public void setProjectLastUpdate(Date projectLastUpdate) {
        this.projectLastUpdate = projectLastUpdate;
    }

    @PrePersist
    public void setProjectCreatedDate(Date projectCreatedDate) {
        this.projectCreatedDate = new Date();
    }

    public Long getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Long projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectContract() {
        return projectContract;
    }

    public void setProjectContract(String projectContract) {
        this.projectContract = projectContract;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectFinancial() {
        return projectFinancial;
    }

    public void setProjectFinancial(String projectFinancial) {
        this.projectFinancial = projectFinancial;
    }

    public String getProjectResource() {
        return projectResource;
    }

    public void setProjectResource(String projectResource) {
        this.projectResource = projectResource;
    }

    public String getProjectSchedule() {
        return projectSchedule;
    }

    public void setProjectSchedule(String projectSchedule) {
        this.projectSchedule = projectSchedule;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectTechnical() {
        return projectTechnical;
    }

    public void setProjectTechnical(String projectTechnical) {
        this.projectTechnical = projectTechnical;
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

    public double getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(double projectValue) {
        this.projectValue = projectValue;
    }
}
