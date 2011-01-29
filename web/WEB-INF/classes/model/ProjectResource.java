/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author sandy
 */
@Entity
@Table(name = "PROJECT_Resource")
public class ProjectResource {


    @Id
    @GeneratedValue
    @Column(name = "project_resource_id")
    private long id;

    @Column(name = "project_man_first")
    private String projectManFirst;

    @Column(name = "project_man_second")
    private String projectManSecond;

    @Column(name = "project_man_third")
    private String projectManThird;

    @Column(name = "project_man_fourth")
    private String projectManFourth;

    /* use this if there is a checkbox instead of textbox*/
    @Column(name = "project_man_status")
    private String projectManStatus;



    @Column(name = "project_cons_first_1")
    private String projectConsFirst1;

    @Column(name = "project_cons_first_2")
    private String projectConsFirst2;

    @Column(name = "project_cons_first_3")
    private String projectConsFirst3;

    @Column(name = "project_cons_first_4")
    private String projectConsFirst4;

    /* use this if there is a checkbox instead of textbox*/
    @Column(name = "project_cons_first_status")
    private String projectConsFirstStatus;


    @Column(name = "project_cons_second_1")
    private String projectConsSecond1;

    @Column(name = "project_cons_second_2")
    private String projectConsSecond2;

    @Column(name = "project_cons_second_3")
    private String projectConsSecond3;

    @Column(name = "project_cons_second_4")
    private String projectConsSecond4;

    /* use this if there is a checkbox instead of textbox*/
    @Column(name = "project_cons_second_status")
    private String projectConsSecondStatus;

    @Column(name = "project_cons_third_1")
    private String projectConsThird1;

    @Column(name = "project_cons_third_2")
    private String projectConsThird2;

    @Column(name = "project_cons_third_3")
    private String projectConsThird3;

    @Column(name = "project_cons_third_4")
    private String projectConsThird4;

    @Column(name = "active")
    private boolean active = true;

    /* use this if there is a checkbox instead of textbox*/
    @Column(name = "project_cons_third_status")
    private String projectConsThirdStatus;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "project_id",updatable = false)
    private Project project;

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

    public String getProjectConsFirst1() {
        return projectConsFirst1;
    }

    public void setProjectConsFirst1(String projectConsFirst1) {
        this.projectConsFirst1 = projectConsFirst1;
    }

    public String getProjectConsFirst2() {
        return projectConsFirst2;
    }

    public void setProjectConsFirst2(String projectConsFirst2) {
        this.projectConsFirst2 = projectConsFirst2;
    }

    public String getProjectConsFirst3() {
        return projectConsFirst3;
    }

    public void setProjectConsFirst3(String projectConsFirst3) {
        this.projectConsFirst3 = projectConsFirst3;
    }

    public String getProjectConsFirst4() {
        return projectConsFirst4;
    }

    public void setProjectConsFirst4(String projectConsFirst4) {
        this.projectConsFirst4 = projectConsFirst4;
    }

    public String getProjectConsFirstStatus() {
        return projectConsFirstStatus;
    }

    public void setProjectConsFirstStatus(String projectConsFirstStatus) {
        this.projectConsFirstStatus = projectConsFirstStatus;
    }

    public String getProjectConsSecond1() {
        return projectConsSecond1;
    }

    public void setProjectConsSecond1(String projectConsSecond1) {
        this.projectConsSecond1 = projectConsSecond1;
    }

    public String getProjectConsSecond2() {
        return projectConsSecond2;
    }

    public void setProjectConsSecond2(String projectConsSecond2) {
        this.projectConsSecond2 = projectConsSecond2;
    }

    public String getProjectConsSecond3() {
        return projectConsSecond3;
    }

    public void setProjectConsSecond3(String projectConsSecond3) {
        this.projectConsSecond3 = projectConsSecond3;
    }

    public String getProjectConsSecond4() {
        return projectConsSecond4;
    }

    public void setProjectConsSecond4(String projectConsSecond4) {
        this.projectConsSecond4 = projectConsSecond4;
    }

    public String getProjectConsSecondStatus() {
        return projectConsSecondStatus;
    }

    public void setProjectConsSecondStatus(String projectConsSecondStatus) {
        this.projectConsSecondStatus = projectConsSecondStatus;
    }

    public String getProjectConsThird1() {
        return projectConsThird1;
    }

    public void setProjectConsThird1(String projectConsThird1) {
        this.projectConsThird1 = projectConsThird1;
    }

    public String getProjectConsThird2() {
        return projectConsThird2;
    }

    public void setProjectConsThird2(String projectConsThird2) {
        this.projectConsThird2 = projectConsThird2;
    }

    public String getProjectConsThird3() {
        return projectConsThird3;
    }

    public void setProjectConsThird3(String projectConsThird3) {
        this.projectConsThird3 = projectConsThird3;
    }

    public String getProjectConsThird4() {
        return projectConsThird4;
    }

    public void setProjectConsThird4(String projectConsThird4) {
        this.projectConsThird4 = projectConsThird4;
    }

    public String getProjectConsThirdStatus() {
        return projectConsThirdStatus;
    }

    public void setProjectConsThirdStatus(String projectConsThirdStatus) {
        this.projectConsThirdStatus = projectConsThirdStatus;
    }

    public String getProjectManFirst() {
        return projectManFirst;
    }

    public void setProjectManFirst(String projectManFirst) {
        this.projectManFirst = projectManFirst;
    }

    public String getProjectManFourth() {
        return projectManFourth;
    }

    public void setProjectManFourth(String projectManFourth) {
        this.projectManFourth = projectManFourth;
    }

    public String getProjectManSecond() {
        return projectManSecond;
    }

    public void setProjectManSecond(String projectManSecond) {
        this.projectManSecond = projectManSecond;
    }

    public String getProjectManStatus() {
        return projectManStatus;
    }

    public void setProjectManStatus(String projectManStatus) {
        this.projectManStatus = projectManStatus;
    }

    public String getProjectManThird() {
        return projectManThird;
    }

    public void setProjectManThird(String projectManThird) {
        this.projectManThird = projectManThird;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    

}
