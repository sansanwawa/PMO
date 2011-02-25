/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
@Table(name = "project_legal")
public class ProjectLegal implements Serializable {

    
    @Id
    @GeneratedValue
    @Column(name = "project_legal_id")
    private long id;

    @Column(name = "project_legal_spk_status")
    private String spkStatus;

    @Column(name = "project_legal_spk_note")
    private String spkNote;

    @Column(name = "project_legal_kontrak_status")
    private String kontrakStatus;

    @Column(name = "project_legal_kontrak_note")
    private String kontrakNote;

    @Column(name = "project_legal_addendum_status")
    private String addendumStatus;

    @Column(name = "project_legal_addendum_note")
    private String addendumNote;

    @Column(name = "project_legal_other_status")
    private String otherStatus;

    @Column(name = "project_legal_other_note")
    private String otherNote;

    @Column(name = "active")
    private boolean active = true;

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

    public String getAddendumNote() {
        return addendumNote;
    }

    public void setAddendumNote(String addendumNote) {
        this.addendumNote = addendumNote;
    }

    public String getAddendumStatus() {
        return addendumStatus;
    }

    public void setAddendumStatus(String addendumStatus) {
        this.addendumStatus = addendumStatus;
    }

   
    public String getKontrakNote() {
        return kontrakNote;
    }

    public void setKontrakNote(String kontrakNote) {
        this.kontrakNote = kontrakNote;
    }

    public String getKontrakStatus() {
        return kontrakStatus;
    }

    public void setKontrakStatus(String kontrakStatus) {
        this.kontrakStatus = kontrakStatus;
    }

    public String getOtherNote() {
        return otherNote;
    }

    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote;
    }

    public String getOtherStatus() {
        return otherStatus;
    }

    public void setOtherStatus(String otherStatus) {
        this.otherStatus = otherStatus;
    }

    public String getSpkNote() {
        return spkNote;
    }

    public void setSpkNote(String spkNote) {
        this.spkNote = spkNote;
    }

    public String getSpkStatus() {
        return spkStatus;
    }

    public void setSpkStatus(String spkStatus) {
        this.spkStatus = spkStatus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }




    
}
