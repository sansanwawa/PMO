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
@Table(name = "PROJECT_DOCUMENT")
public class ProjectDocument {

    @Id
    @GeneratedValue
    @Column(name = "project_management_id")
    private long PROJECT_MANAGEMENT_ID;

    /*
    RFP
     */
    @Column(name = "project_rfp")
    private String PROJECT_RFP;
    @Column(name = "project_rfp_note")
    private String PROJECT_RFP_NOTE;
    /*
    RFP Adendum
     */
    @Column(name = "project_rfp_adendum")
    private String PROJECT_RFP_ADENDUM;
    @Column(name = "project_rfp_adendum_note")
    private String PROJECT_RFP_ADENDUM_NOTE;
    /*
    MOM
     */
    @Column(name = "project_mom")
    private String PROJECT_MOM;
    @Column(name = "project_mom_note")
    private String PROJECT_MOM_NOTE;

    /*
    Technical Assessment From
     */
    @Column(name = "project_taf")
    private String PROJECT_TAF;
    @Column(name = "project_taf_note")
    private String PROJECT_TAF_NOTE;
    /*
    Technical Proposal
     */
    @Column(name = "project_tp")
    private String PROJECT_TP;
    @Column(name = "project_tp_note")
    private String PROJECT_TP_NOTE;
    /*
    POC
     */
    @Column(name = "project_poc")
    private String PROJECT_POC;
    @Column(name = "project_poc_note")
    private String PROJECT_POC_NOTE;
    /*
    Mandays Estimation
     */
    @Column(name = "project_me")
    private String PROJECT_ME;
    @Column(name = "project_me_note")
    private String PROJECT_ME_NOTE;
    /*
    Risk Mitigation Letter
     */
    @Column(name = "project_rml")
    private String PROJECT_RML;
    @Column(name = "project_rml_note")
    private String PROJECT_RML_NOTE;
    /*
    SPK
     */
    @Column(name = "project_spk")
    private String PROJECT_SPK;
    @Column(name = "project_spk_note")
    private String PROJECT_SPK_NOTE;

    /*
    Contract
     */
    @Column(name = "project_contract")
    private String PROJECT_CONTRACT;
    @Column(name = "project_contract_note")
    private String PROJECT_CONTRACT_NOTE;
    /*
    Contract Addendum
     */
    @Column(name = "project_contract_addendum")
    private String PROJECT_CONTRACT_ADDENDUM;
    @Column(name = "project_contract_addendum_note")
    private String PROJECT_CONTRACT_ADDENDUM_NOTE;
    /*
    Project Manager Authorization Letter
     */
    @Column(name = "project_mal")
    private String PROJECT_MAL;
    @Column(name = "project_mal_note")
    private String PROJECT_MAL_NOTE;
    /*
    Join Planning Session
     */
    @Column(name = "project_jps")
    private String PROJECT_JPS;
    @Column(name = "project_jps_note")
    private String PROJECT_JPS_NOTE;

    /*
    Kick Off Document
     */
    @Column(name = "project_kod")
    private String PROJECT_KOD;
    @Column(name = "project_kod_note")
    private String PROJECT_KOD_NOTE;

    /*
    Project Progress Summary Report
     */
    @Column(name = "project_psr")
    private String PROJECT_PSR;
    @Column(name = "project_psr_note")
    private String PROJECT_PSR_NOTE;
    /*
    Delivery Order
     */
    @Column(name = "project_do")
    private String PROJECT_DO;
    @Column(name = "project_do_note")
    private String PROJECT_DO_NOTE;
    /*
    Implementation MOM
     */
    @Column(name = "project_implementation_mom")
    private String PROJECT_IMPLEMENTATION_MOM;
    @Column(name = "project_implementation_mom_note")
    private String PROJECT_IMPLEMENTATION_MOM_NOTE;

    /*
    Change Request
     */
    @Column(name = "project_change_request")
    private String PROJECT_CHANGE_REQUEST;
    @Column(name = "project_change_request_note")
    private String PROJECT_CHANGE_REQUEST_NOTE;
    /*
    UAT
     */
    @Column(name = "project_uat")
    private String PROJECT_UAT;
    @Column(name = "project_uat_note")
    private String PROJECT_UAT_NOTE;

    /*
    BAST
     */
    @Column(name = "project_bast")
    private String PROJECT_BAST;
    @Column(name = "project_bast_note")
    private String PROJECT_BAST_NOTE;
    /*
    Closing Report
     */
    @Column(name = "project_cr")
    private String PROJECT_CR;
    @Column(name = "project_cr_note")
    private String PROJECT_CR_NOTE;
    /*
    Training
     */
    @Column(name = "project_training")
    private String PROJECT_TRAINING;
    @Column(name = "project_training_note")
    private String PROJECT_TRAINING_NOTE;
    /*
    Handover To Maintenance Document
     */
    @Column(name = "project_htd")
    private String PROJECT_HTD;
    @Column(name = "project_htd_note")
    private String PROJECT_HTD_NOTE;

    /*
    FK
    */
   
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "project_id",updatable = true)
    private Project project;

    @Column(name = "active")
    private boolean active = true;

    public String getPROJECT_BAST() {
        return PROJECT_BAST;
    }

    public void setPROJECT_BAST(String PROJECT_BAST) {
        this.PROJECT_BAST = PROJECT_BAST;
    }

    public String getPROJECT_CHANGE_REQUEST() {
        return PROJECT_CHANGE_REQUEST;
    }

    public void setPROJECT_CHANGE_REQUEST(String PROJECT_CHANGE_REQUEST) {
        this.PROJECT_CHANGE_REQUEST = PROJECT_CHANGE_REQUEST;
    }

    public String getPROJECT_CONTRACT() {
        return PROJECT_CONTRACT;
    }

    public void setPROJECT_CONTRACT(String PROJECT_CONTRACT) {
        this.PROJECT_CONTRACT = PROJECT_CONTRACT;
    }

    public String getPROJECT_CONTRACT_ADDENDUM() {
        return PROJECT_CONTRACT_ADDENDUM;
    }

    public void setPROJECT_CONTRACT_ADDENDUM(String PROJECT_CONTRACT_ADDENDUM) {
        this.PROJECT_CONTRACT_ADDENDUM = PROJECT_CONTRACT_ADDENDUM;
    }

    public String getPROJECT_CR() {
        return PROJECT_CR;
    }

    public void setPROJECT_CR(String PROJECT_CR) {
        this.PROJECT_CR = PROJECT_CR;
    }

    public String getPROJECT_DO() {
        return PROJECT_DO;
    }

    public void setPROJECT_DO(String PROJECT_DO) {
        this.PROJECT_DO = PROJECT_DO;
    }

    public String getPROJECT_HTD() {
        return PROJECT_HTD;
    }

    public void setPROJECT_HTD(String PROJECT_HTD) {
        this.PROJECT_HTD = PROJECT_HTD;
    }

    public String getPROJECT_IMPLEMENTATION_MOM() {
        return PROJECT_IMPLEMENTATION_MOM;
    }

    public void setPROJECT_IMPLEMENTATION_MOM(String PROJECT_IMPLEMENTATION_MOM) {
        this.PROJECT_IMPLEMENTATION_MOM = PROJECT_IMPLEMENTATION_MOM;
    }

    public String getPROJECT_JPS() {
        return PROJECT_JPS;
    }

    public void setPROJECT_JPS(String PROJECT_JPS) {
        this.PROJECT_JPS = PROJECT_JPS;
    }

    public String getPROJECT_KOD() {
        return PROJECT_KOD;
    }

    public void setPROJECT_KOD(String PROJECT_KOD) {
        this.PROJECT_KOD = PROJECT_KOD;
    }

    public String getPROJECT_MAL() {
        return PROJECT_MAL;
    }

    public void setPROJECT_MAL(String PROJECT_MAL) {
        this.PROJECT_MAL = PROJECT_MAL;
    }

    public long getPROJECT_MANAGEMENT_ID() {
        return PROJECT_MANAGEMENT_ID;
    }

    public void setPROJECT_MANAGEMENT_ID(long PROJECT_MANAGEMENT_ID) {
        this.PROJECT_MANAGEMENT_ID = PROJECT_MANAGEMENT_ID;
    }

    public String getPROJECT_ME() {
        return PROJECT_ME;
    }

    public void setPROJECT_ME(String PROJECT_ME) {
        this.PROJECT_ME = PROJECT_ME;
    }

    public String getPROJECT_MOM() {
        return PROJECT_MOM;
    }

    public void setPROJECT_MOM(String PROJECT_MOM) {
        this.PROJECT_MOM = PROJECT_MOM;
    }

    public String getPROJECT_POC() {
        return PROJECT_POC;
    }

    public void setPROJECT_POC(String PROJECT_POC) {
        this.PROJECT_POC = PROJECT_POC;
    }

    public String getPROJECT_PSR() {
        return PROJECT_PSR;
    }

    public void setPROJECT_PSR(String PROJECT_PSR) {
        this.PROJECT_PSR = PROJECT_PSR;
    }

    public String getPROJECT_RFP() {
        return PROJECT_RFP;
    }

    public void setPROJECT_RFP(String PROJECT_RFP) {
        this.PROJECT_RFP = PROJECT_RFP;
    }

    public String getPROJECT_RFP_ADENDUM() {
        return PROJECT_RFP_ADENDUM;
    }

    public void setPROJECT_RFP_ADENDUM(String PROJECT_RFP_ADENDUM) {
        this.PROJECT_RFP_ADENDUM = PROJECT_RFP_ADENDUM;
    }

    public String getPROJECT_RML() {
        return PROJECT_RML;
    }

    public void setPROJECT_RML(String PROJECT_RML) {
        this.PROJECT_RML = PROJECT_RML;
    }

    public String getPROJECT_SPK() {
        return PROJECT_SPK;
    }

    public void setPROJECT_SPK(String PROJECT_SPK) {
        this.PROJECT_SPK = PROJECT_SPK;
    }

    public String getPROJECT_TAF() {
        return PROJECT_TAF;
    }

    public void setPROJECT_TAF(String PROJECT_TAF) {
        this.PROJECT_TAF = PROJECT_TAF;
    }

    public String getPROJECT_TP() {
        return PROJECT_TP;
    }

    public void setPROJECT_TP(String PROJECT_TP) {
        this.PROJECT_TP = PROJECT_TP;
    }

    public String getPROJECT_TRAINING() {
        return PROJECT_TRAINING;
    }

    public void setPROJECT_TRAINING(String PROJECT_TRAINING) {
        this.PROJECT_TRAINING = PROJECT_TRAINING;
    }

    public String getPROJECT_UAT() {
        return PROJECT_UAT;
    }

    public void setPROJECT_UAT(String PROJECT_UAT) {
        this.PROJECT_UAT = PROJECT_UAT;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPROJECT_BAST_NOTE() {
        return PROJECT_BAST_NOTE;
    }

    public void setPROJECT_BAST_NOTE(String PROJECT_BAST_NOTE) {
        this.PROJECT_BAST_NOTE = PROJECT_BAST_NOTE;
    }

    public String getPROJECT_CHANGE_REQUEST_NOTE() {
        return PROJECT_CHANGE_REQUEST_NOTE;
    }

    public void setPROJECT_CHANGE_REQUEST_NOTE(String PROJECT_CHANGE_REQUEST_NOTE) {
        this.PROJECT_CHANGE_REQUEST_NOTE = PROJECT_CHANGE_REQUEST_NOTE;
    }

    public String getPROJECT_CONTRACT_ADDENDUM_NOTE() {
        return PROJECT_CONTRACT_ADDENDUM_NOTE;
    }

    public void setPROJECT_CONTRACT_ADDENDUM_NOTE(String PROJECT_CONTRACT_ADDENDUM_NOTE) {
        this.PROJECT_CONTRACT_ADDENDUM_NOTE = PROJECT_CONTRACT_ADDENDUM_NOTE;
    }

    public String getPROJECT_CONTRACT_NOTE() {
        return PROJECT_CONTRACT_NOTE;
    }

    public void setPROJECT_CONTRACT_NOTE(String PROJECT_CONTRACT_NOTE) {
        this.PROJECT_CONTRACT_NOTE = PROJECT_CONTRACT_NOTE;
    }

    public String getPROJECT_CR_NOTE() {
        return PROJECT_CR_NOTE;
    }

    public void setPROJECT_CR_NOTE(String PROJECT_CR_NOTE) {
        this.PROJECT_CR_NOTE = PROJECT_CR_NOTE;
    }

    public String getPROJECT_DO_NOTE() {
        return PROJECT_DO_NOTE;
    }

    public void setPROJECT_DO_NOTE(String PROJECT_DO_NOTE) {
        this.PROJECT_DO_NOTE = PROJECT_DO_NOTE;
    }

    public String getPROJECT_HTD_NOTE() {
        return PROJECT_HTD_NOTE;
    }

    public void setPROJECT_HTD_NOTE(String PROJECT_HTD_NOTE) {
        this.PROJECT_HTD_NOTE = PROJECT_HTD_NOTE;
    }

    public String getPROJECT_IMPLEMENTATION_MOM_NOTE() {
        return PROJECT_IMPLEMENTATION_MOM_NOTE;
    }

    public void setPROJECT_IMPLEMENTATION_MOM_NOTE(String PROJECT_IMPLEMENTATION_MOM_NOTE) {
        this.PROJECT_IMPLEMENTATION_MOM_NOTE = PROJECT_IMPLEMENTATION_MOM_NOTE;
    }

    public String getPROJECT_JPS_NOTE() {
        return PROJECT_JPS_NOTE;
    }

    public void setPROJECT_JPS_NOTE(String PROJECT_JPS_NOTE) {
        this.PROJECT_JPS_NOTE = PROJECT_JPS_NOTE;
    }

    public String getPROJECT_KOD_NOTE() {
        return PROJECT_KOD_NOTE;
    }

    public void setPROJECT_KOD_NOTE(String PROJECT_KOD_NOTE) {
        this.PROJECT_KOD_NOTE = PROJECT_KOD_NOTE;
    }

    public String getPROJECT_MAL_NOTE() {
        return PROJECT_MAL_NOTE;
    }

    public void setPROJECT_MAL_NOTE(String PROJECT_MAL_NOTE) {
        this.PROJECT_MAL_NOTE = PROJECT_MAL_NOTE;
    }

    public String getPROJECT_ME_NOTE() {
        return PROJECT_ME_NOTE;
    }

    public void setPROJECT_ME_NOTE(String PROJECT_ME_NOTE) {
        this.PROJECT_ME_NOTE = PROJECT_ME_NOTE;
    }

    public String getPROJECT_MOM_NOTE() {
        return PROJECT_MOM_NOTE;
    }

    public void setPROJECT_MOM_NOTE(String PROJECT_MOM_NOTE) {
        this.PROJECT_MOM_NOTE = PROJECT_MOM_NOTE;
    }

    public String getPROJECT_POC_NOTE() {
        return PROJECT_POC_NOTE;
    }

    public void setPROJECT_POC_NOTE(String PROJECT_POC_NOTE) {
        this.PROJECT_POC_NOTE = PROJECT_POC_NOTE;
    }

    public String getPROJECT_PSR_NOTE() {
        return PROJECT_PSR_NOTE;
    }

    public void setPROJECT_PSR_NOTE(String PROJECT_PSR_NOTE) {
        this.PROJECT_PSR_NOTE = PROJECT_PSR_NOTE;
    }

    public String getPROJECT_RFP_ADENDUM_NOTE() {
        return PROJECT_RFP_ADENDUM_NOTE;
    }

    public void setPROJECT_RFP_ADENDUM_NOTE(String PROJECT_RFP_ADENDUM_NOTE) {
        this.PROJECT_RFP_ADENDUM_NOTE = PROJECT_RFP_ADENDUM_NOTE;
    }

    public String getPROJECT_RFP_NOTE() {
        return PROJECT_RFP_NOTE;
    }

    public void setPROJECT_RFP_NOTE(String PROJECT_RFP_NOTE) {
        this.PROJECT_RFP_NOTE = PROJECT_RFP_NOTE;
    }

    public String getPROJECT_RML_NOTE() {
        return PROJECT_RML_NOTE;
    }

    public void setPROJECT_RML_NOTE(String PROJECT_RML_NOTE) {
        this.PROJECT_RML_NOTE = PROJECT_RML_NOTE;
    }

    public String getPROJECT_SPK_NOTE() {
        return PROJECT_SPK_NOTE;
    }

    public void setPROJECT_SPK_NOTE(String PROJECT_SPK_NOTE) {
        this.PROJECT_SPK_NOTE = PROJECT_SPK_NOTE;
    }

    public String getPROJECT_TAF_NOTE() {
        return PROJECT_TAF_NOTE;
    }

    public void setPROJECT_TAF_NOTE(String PROJECT_TAF_NOTE) {
        this.PROJECT_TAF_NOTE = PROJECT_TAF_NOTE;
    }

    public String getPROJECT_TP_NOTE() {
        return PROJECT_TP_NOTE;
    }

    public void setPROJECT_TP_NOTE(String PROJECT_TP_NOTE) {
        this.PROJECT_TP_NOTE = PROJECT_TP_NOTE;
    }

    public String getPROJECT_TRAINING_NOTE() {
        return PROJECT_TRAINING_NOTE;
    }

    public void setPROJECT_TRAINING_NOTE(String PROJECT_TRAINING_NOTE) {
        this.PROJECT_TRAINING_NOTE = PROJECT_TRAINING_NOTE;
    }

    public String getPROJECT_UAT_NOTE() {
        return PROJECT_UAT_NOTE;
    }

    public void setPROJECT_UAT_NOTE(String PROJECT_UAT_NOTE) {
        this.PROJECT_UAT_NOTE = PROJECT_UAT_NOTE;
    }
}
