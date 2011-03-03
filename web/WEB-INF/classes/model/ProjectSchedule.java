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

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "project_schedule_status")
    private String projectScheduleStatus;

    @Column(name = "project_schedule_planned")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date projectPlannedDate;

    @Column(name = "project_schedule_actual")
    private String projectActualDate;

    @Column(name = "project_schedule_revised")
    private String projectRevisedDate;

    @Column(name = "project_schedule_remarks")
    private String projectRemarks;
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

    public String getProjectActualDate() {
        return projectActualDate;
    }

    public void setProjectActualDate(String projectActualDate) {
        this.projectActualDate = projectActualDate;
    }

    public Date getProjectPlannedDate() {
        return projectPlannedDate;
    }

    public void setProjectPlannedDate(Date projectPlannedDate) {
        this.projectPlannedDate = projectPlannedDate;
    }

    public String getProjectRemarks() {
        return projectRemarks;
    }

    public void setProjectRemarks(String projectRemarks) {
        this.projectRemarks = projectRemarks;
    }

    public String getProjectRevisedDate() {
        return projectRevisedDate;
    }

    public void setProjectRevisedDate(String projectRevisedDate) {
        this.projectRevisedDate = projectRevisedDate;
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
