/**
 *
 * @author sandy
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "project_schedule")
public class ProjectSchedule implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "project_schedule_id")
    private long id;

    /*
    FK
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /*
     * FK
     */
    @ManyToOne
    @JoinColumn(name = "project_schedule_name_id")
    private ProjectScheduleName projectScheduleName;

    
    @JoinColumn(name = "project_schedule_status")
    private String projectScheduleStatus;

    @JoinColumn(name = "project_schedule_planned")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectPlannedDate;

    @JoinColumn(name = "project_schedule_actual")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectRevisedDate;

    @JoinColumn(name = "project_schedule_remarks")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectRemarks;

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

    public Date getProjectPlannedDate() {
        return projectPlannedDate;
    }

    public void setProjectPlannedDate(Date projectPlannedDate) {
        this.projectPlannedDate = projectPlannedDate;
    }

    public Date getProjectRevisedDate() {
        return projectRevisedDate;
    }

    public void setProjectRevisedDate(Date projectRevisedDate) {
        this.projectRevisedDate = projectRevisedDate;
    }

    public Date getProjectRemarks() {
        return projectRemarks;
    }

    public void setProjectRemarks(Date projectRemarks) {
        this.projectRemarks = projectRemarks;
    }
    

    public ProjectScheduleName getProjectScheduleName() {
        return projectScheduleName;
    }

    public void setProjectScheduleName(ProjectScheduleName projectScheduleName) {
        this.projectScheduleName = projectScheduleName;
    }

    public String getProjectScheduleStatus() {
        return projectScheduleStatus;
    }

    public void setProjectScheduleStatus(String projectScheduleStatus) {
        this.projectScheduleStatus = projectScheduleStatus;
    }



    




    
}
