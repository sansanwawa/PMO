<%-- 
    Document   : projectForm
    Created on : Oct 22, 2010, 10:58:47 AM
    Author     : sandy
--%>

<%@ include file="/WEB-INF/jsp/main/header.jsp" %>
<h1>Project Document</h1>
<a href="report?id=<% out.print(request.getParameter("id"));%>">Report</a>


        <form:form action="addProcess" commandName="projectdocument">
            <form:hidden path="PROJECT_MANAGEMENT_ID" />
            <table>
                <tr>
                    <td width="500px">RFP :</td>
                    <td width="100px"><form:radiobutton path="PROJECT_RFP" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_RFP" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_RFP_NOTE"/></td>

                </tr>

                <tr>
                    <td>Addendum RFP :</td>
                    <td><form:radiobutton path="PROJECT_RFP_ADENDUM" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_RFP_ADENDUM" value="no" label="No" /></td>
                     <td><form:input path="PROJECT_RFP_ADENDUM_NOTE"/></td>
                </tr>

                <tr>
                    <td>MOM :</td>
                    <td><form:radiobutton path="PROJECT_MOM" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_MOM" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_MOM_NOTE"/></td>
                </tr>

                <tr>
                    <td>Technical Assessment Form :</td>
                    <td><form:radiobutton path="PROJECT_TAF" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_TAF" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_TAF_NOTE"/></td>
                </tr>

                <tr>
                    <td>Technical Proposal :</td>
                    <td><form:radiobutton path="PROJECT_TP" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_TP" value="no" label="No" /></td>
                <td><form:input path="PROJECT_TP_NOTE"/></td>
                </tr>

                <tr>
                    <td>Technical Presentation / POC :</td>
                    <td><form:radiobutton path="PROJECT_POC" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_POC" value="no" label="No" /></td>
                <td><form:input path="PROJECT_POC_NOTE"/></td>

                </tr>

                <tr>
                    <td>PMO Mandays Estimation :</td>
                    <td><form:radiobutton path="PROJECT_ME" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_ME" value="no" label="No" /></td>
                     <td><form:input path="PROJECT_ME_NOTE"/></td>

                </tr>

                <tr>
                    <td>PMO Risk Mitigation Letter :</td>
                    <td><form:radiobutton path="PROJECT_RML" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_RML" value="no" label="No" /></td>
                                    <td><form:input path="PROJECT_RML_NOTE"/></td>

                </tr>

                 </table>
                <hr>
                 <table>
                <tr>
                    <td  width="500px">SPK / PO :</td>
                    <td width="100px"><form:radiobutton path="PROJECT_SPK" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_SPK" value="no" label="No" /></td>
                                    <td><form:input path="PROJECT_SPK_NOTE"/></td>

                </tr>

                <tr>
                    <td>Contract / Agreement :</td>
                    <td><form:radiobutton path="PROJECT_CONTRACT" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_CONTRACT" value="no" label="No" /></td>
                                    <td><form:input path="PROJECT_CONTRACT_NOTE"/></td>

                </tr>

                <tr>
                    <td>Addendum Contract :</td>
                    <td><form:radiobutton path="PROJECT_CONTRACT_ADDENDUM" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_CONTRACT_ADDENDUM" value="no" label="No" /></td>
                                    <td><form:input path="PROJECT_CONTRACT_ADDENDUM_NOTE"/></td>

                </tr>

                <tr>
                    <td>Project Manager Authorization Letter :</td>
                    <td><form:radiobutton path="PROJECT_MAL" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_MAL" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_MAL_NOTE"/></td>
                </tr>

                <tr>
                    <td>Join Planning Session / DRM / Project Charter :</td>
                    <td><form:radiobutton path="PROJECT_JPS" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_JPS" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_JPS_NOTE"/></td>
                </tr>

                <tr>
                    <td>Kick Off Document / Sign Off :</td>
                    <td><form:radiobutton path="PROJECT_KOD" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_KOD" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_KOD_NOTE"/></td>
                </tr>

                <tr>
                    <td>Project Progress Summary Report (Weekly) :</td>
                    <td><form:radiobutton path="PROJECT_PSR" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_PSR" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_PSR_NOTE"/></td>
                </tr>

                  <tr>
                    <td>Delivery Order (Jika ada pengiriman barang) :</td>
                    <td><form:radiobutton path="PROJECT_DO" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_DO" value="no" label="No" /></td>
                     <td><form:input path="PROJECT_DO_NOTE"/></td>
                </tr>

                 <tr>
                    <td>MOM :</td>
                    <td><form:radiobutton path="PROJECT_IMPLEMENTATION_MOM" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_IMPLEMENTATION_MOM" value="no" label="No" /></td>
                     <td><form:input path="PROJECT_IMPLEMENTATION_MOM_NOTE"/></td>
                </tr>


                 <tr>
                    <td>Change Request :</td>
                    <td><form:radiobutton path="PROJECT_CHANGE_REQUEST" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_CHANGE_REQUEST" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_CHANGE_REQUEST_NOTE"/></td>
                </tr>

                <tr>
                    <td>UAT :</td>
                    <td><form:radiobutton path="PROJECT_UAT" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_UAT" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_UAT_NOTE"/></td>
                </tr>

                <tr>
                    <td>Berita Acara / BAST :</td>
                    <td><form:radiobutton path="PROJECT_BAST" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_BAST" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_BAST_NOTE"/></td>
                </tr>

                 </table>
                <hr>

                <table>
                  <tr>
                    <td  width="500px">Project Closing Report :</td>
                    <td width="100px"><form:radiobutton path="PROJECT_CR" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_CR" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_CR_NOTE"/></td>

                </tr>

                 <tr>
                    <td>Training :</td>
                    <td><form:radiobutton path="PROJECT_TRAINING" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_TRAINING" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_TRAINING_NOTE"/></td>
                </tr>

                <tr>
                    <td>Handover to Maintenance Document :</td>
                    <td><form:radiobutton path="PROJECT_HTD" value="yes" label="Yes" />
                        <form:radiobutton path="PROJECT_HTD" value="no" label="No" /></td>
                    <td><form:input path="PROJECT_HTD_NOTE"/></td>
                </tr>
               <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>
            </table>
        </form:form>

<%@ include file="/WEB-INF/jsp/main/footer.jsp" %>