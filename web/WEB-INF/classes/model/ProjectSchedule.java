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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author sandy
 */
@Entity
@Table(name = "PROJECT_SCHEDULE")
public class ProjectSchedule {

    @Id
    @GeneratedValue
    @Column(name = "project_schedule_id")
    private long id;

    /* 
    Kick Off
     */
    @Column(name = "project_schedule_kickoff_status")
    private String projectScheduleKickOffStatus;
    @Column(name = "project_schedule_kickoff_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleKickOffDate;

    @Column(name = "project_schedule_kickoff_planned")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleKickOffPlanned;

    @Column(name = "project_schedule_kickoff_revised")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleKickOffRevised;
    
    @Column(name = "project_schedule_kickoff_remarks")
    private String projectScheduleKickOffRemarks;
    /* 
    Pengadaan
     */
    @Column(name = "project_schedule_pengadaan_status")
    private String projectSchedulePengadaanStatus;
    @Column(name = "project_schedule_pengadaan_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectSchedulePengadaanDate;
    @Column(name = "project_schedule_pengadaan_planned")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectSchedulePengadaanPlanned;
    @Column(name = "project_schedule_pengadaan_revised")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectSchedulePengadaanRevised;
    @Column(name = "project_schedule_pengadaan_remarks")
    private String projectSchedulePengadaanRemarks;
    /* 
    Delivery
     */
    @Column(name = "project_schedule_delivery_status")
    private String projectScheduleDeliveryStatus;

    @Column(name = "project_schedule_delivery_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleDeliveryDate;

    @Column(name = "project_schedule_delivery_planned")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleDeliveryPlanned;

    @Column(name = "project_schedule_delivery_revised")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleDeliveryRevised;

    @Column(name = "project_schedule_delivery_remarks")
    private String projectScheduleDeliveryRemarks;
    /* 
    Staging
     */
    @Column(name = "project_schedule_staging_status")
    private String projectScheduleStagingStatus;
    @Column(name = "project_schedule_staging_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleStagingDate;

    @Column(name = "project_schedule_staging_planned")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleStagingPlanned;

    @Column(name = "project_schedule_staging_revised")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleStagingRevised;

    @Column(name = "project_schedule_staging_remarks")
    private String projectScheduleStagingRemarks;
    /* 
    Implementasi 1
     */
    @Column(name = "project_schedule_impl1_status")
    private String projectScheduleImpl1Status;
    @Column(name = "project_schedule_impl1_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleImpl1Date;
    @Column(name = "project_schedule_impl1_planned")
    private String projectScheduleImpl1Planned;
    @Column(name = "project_schedule_impl1_revised")
    private String projectScheduleImpl1Revised;
    @Column(name = "project_schedule_impl1_remarks")
    private String projectScheduleImpl1Remarks;
    /* 
    Implementasi 2
     */
    @Column(name = "project_schedule_impl2_status")
    private String projectScheduleImpl2Status;
    @Column(name = "project_schedule_impl2_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleImpl2Date;
    @Column(name = "project_schedule_impl2_planned")
    private String projectScheduleImpl2Planned;
    @Column(name = "project_schedule_impl2_revised")
    private String projectScheduleImpl2Revised;
    @Column(name = "project_schedule_impl2_remarks")
    private String projectScheduleImpl2Remarks;
    /*
    SIT
     */
    @Column(name = "project_schedule_sit_status")
    private String projectScheduleSitStatus;
    @Column(name = "project_schedule_sit_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleSitDate;
    @Column(name = "project_schedule_sit_planned")
    private String projectScheduleSitPlanned;
    @Column(name = "project_schedule_sit_revised")
    private String projectScheduleSitRevised;
    @Column(name = "project_schedule_sit_remarks")
    private String projectScheduleSitRemarks;
    /* 
    UAT
     */
    @Column(name = "project_schedule_uat_status")
    private String projectScheduleUatStatus;
    @Column(name = "project_schedule_uat_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleUatDate;
    @Column(name = "project_schedule_uat_planned")
    private String projectScheduleUatPlanned;
    @Column(name = "project_schedule_uat_revised")
    private String projectScheduleUatRevised;
    @Column(name = "project_schedule_uat_remarks")
    private String projectScheduleUatRemarks;
    /* 
    berita Acara
     */
    @Column(name = "project_schedule_ba_status")
    private String projectScheduleBaStatus;
    @Column(name = "project_schedule_ba_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleBaDate;
    @Column(name = "project_schedule_ba_planned")
    private String projectScheduleBaPlanned;
    @Column(name = "project_schedule_ba_revised")
    private String projectScheduleBaRevised;
    @Column(name = "project_schedule_ba_remarks")
    private String projectScheduleBaRemarks;

    /* 
    Documentation
     */
    @Column(name = "project_schedule_doc_status")
    private String projectScheduleDocStatus;
    @Column(name = "project_schedule_doc_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectScheduleDocDate;
    @Column(name = "project_schedule_doc_planned")
    private String projectScheduleDocPlanned;
    @Column(name = "project_schedule_doc_revised")
    private String projectScheduleDocRevised;
    @Column(name = "project_schedule_doc_remarks")
    private String projectScheduleDocRemarks;
    /*
    FK
     */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "project_id", updatable = false)
    private Project project;

    public Date getProjectScheduleDeliveryPlanned() {
        return projectScheduleDeliveryPlanned;
    }

    public void setProjectScheduleDeliveryPlanned(Date projectScheduleDeliveryPlanned) {
        this.projectScheduleDeliveryPlanned = projectScheduleDeliveryPlanned;
    }

    public Date getProjectScheduleDeliveryRevised() {
        return projectScheduleDeliveryRevised;
    }

    public void setProjectScheduleDeliveryRevised(Date projectScheduleDeliveryRevised) {
        this.projectScheduleDeliveryRevised = projectScheduleDeliveryRevised;
    }

    public Date getProjectScheduleKickOffPlanned() {
        return projectScheduleKickOffPlanned;
    }

    public void setProjectScheduleKickOffPlanned(Date projectScheduleKickOffPlanned) {
        this.projectScheduleKickOffPlanned = projectScheduleKickOffPlanned;
    }

    public Date getProjectScheduleKickOffRevised() {
        return projectScheduleKickOffRevised;
    }

    public void setProjectScheduleKickOffRevised(Date projectScheduleKickOffRevised) {
        this.projectScheduleKickOffRevised = projectScheduleKickOffRevised;
    }

    public Date getProjectSchedulePengadaanPlanned() {
        return projectSchedulePengadaanPlanned;
    }

    public void setProjectSchedulePengadaanPlanned(Date projectSchedulePengadaanPlanned) {
        this.projectSchedulePengadaanPlanned = projectSchedulePengadaanPlanned;
    }

    public Date getProjectSchedulePengadaanRevised() {
        return projectSchedulePengadaanRevised;
    }

    public void setProjectSchedulePengadaanRevised(Date projectSchedulePengadaanRevised) {
        this.projectSchedulePengadaanRevised = projectSchedulePengadaanRevised;
    }

    public Date getProjectScheduleStagingPlanned() {
        return projectScheduleStagingPlanned;
    }

    public void setProjectScheduleStagingPlanned(Date projectScheduleStagingPlanned) {
        this.projectScheduleStagingPlanned = projectScheduleStagingPlanned;
    }

    public Date getProjectScheduleStagingRevised() {
        return projectScheduleStagingRevised;
    }

    public void setProjectScheduleStagingRevised(Date projectScheduleStagingRevised) {
        this.projectScheduleStagingRevised = projectScheduleStagingRevised;
    }

    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getProjectScheduleBaDate() {
        return projectScheduleBaDate;
    }

    public void setProjectScheduleBaDate(Date projectScheduleBaDate) {
        this.projectScheduleBaDate = projectScheduleBaDate;
    }

    public String getProjectScheduleBaPlanned() {
        return projectScheduleBaPlanned;
    }

    public void setProjectScheduleBaPlanned(String projectScheduleBaPlanned) {
        this.projectScheduleBaPlanned = projectScheduleBaPlanned;
    }

    public String getProjectScheduleBaRemarks() {
        return projectScheduleBaRemarks;
    }

    public void setProjectScheduleBaRemarks(String projectScheduleBaRemarks) {
        this.projectScheduleBaRemarks = projectScheduleBaRemarks;
    }

    public String getProjectScheduleBaRevised() {
        return projectScheduleBaRevised;
    }

    public void setProjectScheduleBaRevised(String projectScheduleBaRevised) {
        this.projectScheduleBaRevised = projectScheduleBaRevised;
    }

    public String getProjectScheduleBaStatus() {
        return projectScheduleBaStatus;
    }

    public void setProjectScheduleBaStatus(String projectScheduleBaStatus) {
        this.projectScheduleBaStatus = projectScheduleBaStatus;
    }

    public Date getProjectScheduleDeliveryDate() {
        return projectScheduleDeliveryDate;
    }

    public void setProjectScheduleDeliveryDate(Date projectScheduleDeliveryDate) {
        this.projectScheduleDeliveryDate = projectScheduleDeliveryDate;
    }



    public String getProjectScheduleDeliveryRemarks() {
        return projectScheduleDeliveryRemarks;
    }

    public void setProjectScheduleDeliveryRemarks(String projectScheduleDeliveryRemarks) {
        this.projectScheduleDeliveryRemarks = projectScheduleDeliveryRemarks;
    }



    public String getProjectScheduleDeliveryStatus() {
        return projectScheduleDeliveryStatus;
    }

    public void setProjectScheduleDeliveryStatus(String projectScheduleDeliveryStatus) {
        this.projectScheduleDeliveryStatus = projectScheduleDeliveryStatus;
    }

    public Date getProjectScheduleDocDate() {
        return projectScheduleDocDate;
    }

    public void setProjectScheduleDocDate(Date projectScheduleDocDate) {
        this.projectScheduleDocDate = projectScheduleDocDate;
    }

    public String getProjectScheduleDocPlanned() {
        return projectScheduleDocPlanned;
    }

    public void setProjectScheduleDocPlanned(String projectScheduleDocPlanned) {
        this.projectScheduleDocPlanned = projectScheduleDocPlanned;
    }

    public String getProjectScheduleDocRemarks() {
        return projectScheduleDocRemarks;
    }

    public void setProjectScheduleDocRemarks(String projectScheduleDocRemarks) {
        this.projectScheduleDocRemarks = projectScheduleDocRemarks;
    }

    public String getProjectScheduleDocRevised() {
        return projectScheduleDocRevised;
    }

    public void setProjectScheduleDocRevised(String projectScheduleDocRevised) {
        this.projectScheduleDocRevised = projectScheduleDocRevised;
    }

    public String getProjectScheduleDocStatus() {
        return projectScheduleDocStatus;
    }

    public void setProjectScheduleDocStatus(String projectScheduleDocStatus) {
        this.projectScheduleDocStatus = projectScheduleDocStatus;
    }

    

    public Date getProjectScheduleImpl1Date() {
        return projectScheduleImpl1Date;
    }

    public void setProjectScheduleImpl1Date(Date projectScheduleImpl1Date) {
        this.projectScheduleImpl1Date = projectScheduleImpl1Date;
    }

    public String getProjectScheduleImpl1Planned() {
        return projectScheduleImpl1Planned;
    }

    public void setProjectScheduleImpl1Planned(String projectScheduleImpl1Planned) {
        this.projectScheduleImpl1Planned = projectScheduleImpl1Planned;
    }

    public String getProjectScheduleImpl1Remarks() {
        return projectScheduleImpl1Remarks;
    }

    public void setProjectScheduleImpl1Remarks(String projectScheduleImpl1Remarks) {
        this.projectScheduleImpl1Remarks = projectScheduleImpl1Remarks;
    }

    public String getProjectScheduleImpl1Revised() {
        return projectScheduleImpl1Revised;
    }

    public void setProjectScheduleImpl1Revised(String projectScheduleImpl1Revised) {
        this.projectScheduleImpl1Revised = projectScheduleImpl1Revised;
    }

    public String getProjectScheduleImpl1Status() {
        return projectScheduleImpl1Status;
    }

    public void setProjectScheduleImpl1Status(String projectScheduleImpl1Status) {
        this.projectScheduleImpl1Status = projectScheduleImpl1Status;
    }

    public Date getProjectScheduleImpl2Date() {
        return projectScheduleImpl2Date;
    }

    public void setProjectScheduleImpl2Date(Date projectScheduleImpl2Date) {
        this.projectScheduleImpl2Date = projectScheduleImpl2Date;
    }

    public String getProjectScheduleImpl2Planned() {
        return projectScheduleImpl2Planned;
    }

    public void setProjectScheduleImpl2Planned(String projectScheduleImpl2Planned) {
        this.projectScheduleImpl2Planned = projectScheduleImpl2Planned;
    }

    public String getProjectScheduleImpl2Remarks() {
        return projectScheduleImpl2Remarks;
    }

    public void setProjectScheduleImpl2Remarks(String projectScheduleImpl2Remarks) {
        this.projectScheduleImpl2Remarks = projectScheduleImpl2Remarks;
    }

    public String getProjectScheduleImpl2Revised() {
        return projectScheduleImpl2Revised;
    }

    public void setProjectScheduleImpl2Revised(String projectScheduleImpl2Revised) {
        this.projectScheduleImpl2Revised = projectScheduleImpl2Revised;
    }

    public String getProjectScheduleImpl2Status() {
        return projectScheduleImpl2Status;
    }

    public void setProjectScheduleImpl2Status(String projectScheduleImpl2Status) {
        this.projectScheduleImpl2Status = projectScheduleImpl2Status;
    }

    public Date getProjectScheduleKickOffDate() {
        return projectScheduleKickOffDate;
    }

    public void setProjectScheduleKickOffDate(Date projectScheduleKickOffDate) {
        this.projectScheduleKickOffDate = projectScheduleKickOffDate;
    }


    public String getProjectScheduleKickOffRemarks() {
        return projectScheduleKickOffRemarks;
    }

    public void setProjectScheduleKickOffRemarks(String projectScheduleKickOffRemarks) {
        this.projectScheduleKickOffRemarks = projectScheduleKickOffRemarks;
    }



    public String getProjectScheduleKickOffStatus() {
        return projectScheduleKickOffStatus;
    }

    public void setProjectScheduleKickOffStatus(String projectScheduleKickOffStatus) {
        this.projectScheduleKickOffStatus = projectScheduleKickOffStatus;
    }

    public Date getProjectSchedulePengadaanDate() {
        return projectSchedulePengadaanDate;
    }

    public void setProjectSchedulePengadaanDate(Date projectSchedulePengadaanDate) {
        this.projectSchedulePengadaanDate = projectSchedulePengadaanDate;
    }

    public String getProjectSchedulePengadaanRemarks() {
        return projectSchedulePengadaanRemarks;
    }

    public void setProjectSchedulePengadaanRemarks(String projectSchedulePengadaanRemarks) {
        this.projectSchedulePengadaanRemarks = projectSchedulePengadaanRemarks;
    }



    public String getProjectSchedulePengadaanStatus() {
        return projectSchedulePengadaanStatus;
    }

    public void setProjectSchedulePengadaanStatus(String projectSchedulePengadaanStatus) {
        this.projectSchedulePengadaanStatus = projectSchedulePengadaanStatus;
    }

    public Date getProjectScheduleSitDate() {
        return projectScheduleSitDate;
    }

    public void setProjectScheduleSitDate(Date projectScheduleSitDate) {
        this.projectScheduleSitDate = projectScheduleSitDate;
    }

    public String getProjectScheduleSitPlanned() {
        return projectScheduleSitPlanned;
    }

    public void setProjectScheduleSitPlanned(String projectScheduleSitPlanned) {
        this.projectScheduleSitPlanned = projectScheduleSitPlanned;
    }

    public String getProjectScheduleSitRemarks() {
        return projectScheduleSitRemarks;
    }

    public void setProjectScheduleSitRemarks(String projectScheduleSitRemarks) {
        this.projectScheduleSitRemarks = projectScheduleSitRemarks;
    }

    public String getProjectScheduleSitRevised() {
        return projectScheduleSitRevised;
    }

    public void setProjectScheduleSitRevised(String projectScheduleSitRevised) {
        this.projectScheduleSitRevised = projectScheduleSitRevised;
    }

    public String getProjectScheduleSitStatus() {
        return projectScheduleSitStatus;
    }

    public void setProjectScheduleSitStatus(String projectScheduleSitStatus) {
        this.projectScheduleSitStatus = projectScheduleSitStatus;
    }

    public Date getProjectScheduleStagingDate() {
        return projectScheduleStagingDate;
    }

    public void setProjectScheduleStagingDate(Date projectScheduleStagingDate) {
        this.projectScheduleStagingDate = projectScheduleStagingDate;
    }

    public String getProjectScheduleStagingRemarks() {
        return projectScheduleStagingRemarks;
    }

    public void setProjectScheduleStagingRemarks(String projectScheduleStagingRemarks) {
        this.projectScheduleStagingRemarks = projectScheduleStagingRemarks;
    }



    public String getProjectScheduleStagingStatus() {
        return projectScheduleStagingStatus;
    }

    public void setProjectScheduleStagingStatus(String projectScheduleStagingStatus) {
        this.projectScheduleStagingStatus = projectScheduleStagingStatus;
    }

    public Date getProjectScheduleUatDate() {
        return projectScheduleUatDate;
    }

    public void setProjectScheduleUatDate(Date projectScheduleUatDate) {
        this.projectScheduleUatDate = projectScheduleUatDate;
    }

    public String getProjectScheduleUatPlanned() {
        return projectScheduleUatPlanned;
    }

    public void setProjectScheduleUatPlanned(String projectScheduleUatPlanned) {
        this.projectScheduleUatPlanned = projectScheduleUatPlanned;
    }

    public String getProjectScheduleUatRemarks() {
        return projectScheduleUatRemarks;
    }

    public void setProjectScheduleUatRemarks(String projectScheduleUatRemarks) {
        this.projectScheduleUatRemarks = projectScheduleUatRemarks;
    }

    public String getProjectScheduleUatRevised() {
        return projectScheduleUatRevised;
    }

    public void setProjectScheduleUatRevised(String projectScheduleUatRevised) {
        this.projectScheduleUatRevised = projectScheduleUatRevised;
    }

    public String getProjectScheduleUatStatus() {
        return projectScheduleUatStatus;
    }

    public void setProjectScheduleUatStatus(String projectScheduleUatStatus) {
        this.projectScheduleUatStatus = projectScheduleUatStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
   

}
