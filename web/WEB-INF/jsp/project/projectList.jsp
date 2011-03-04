<%-- 
    Document   : projectList
    Created on : Oct 22, 2010, 2:33:38 PM
    Author     : sandy
--%>
<script type="text/javascript">
 

    
    Ext.onReady(function(){
 
        Ext.QuickTips.init();

                
        //selection Model
        var selectionModel = new Ext.grid.CheckboxSelectionModel();
        var selectionResourceModel = new Ext.grid.CheckboxSelectionModel();
        var selectionFinancialModel = new Ext.grid.CheckboxSelectionModel();
        var selectionInternalCostModel = new Ext.grid.CheckboxSelectionModel();
        var selectionScheduleModel = new Ext.grid.CheckboxSelectionModel();
        var selectionLegalModel = new Ext.grid.CheckboxSelectionModel();







        //summary
        var summary = new Ext.ux.grid.GroupSummary();
        var summaryInternalCost = new Ext.ux.grid.GroupSummary();
        var summarySchedule= new Ext.ux.grid.GroupSummary();


// create the Data Store        
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({ method:'POST', url: '../project/json' }),
            autoLoad :false,
            remoteSort :true,
            baseParams : {  start:0, limit:10 },
            sortInfo: { field: 'projectLastUpdate', direction: 'DESC'},
            // the return will be JSON, so lets set up a reader
            reader: new Ext.data.JsonReader({
                root: 'data',
                id: 'mydata',
                totalRecords: 'total',
                fields : [
                    {name: 'id', mapping: 'id'},
                    {name: 'name', mapping: 'name'},
                    {name: 'projectEndDate', mapping: 'project_end_date'},
                    {name: 'projectStartDate', mapping: 'project_start_date'},
                    {name: 'projectFinancial', mapping: 'financial'},
                    {name: 'projectContract', mapping: 'project_contract'},
                    {name: 'projectTechnical', mapping: 'project_technical'},
                    {name: 'projectResource', mapping: 'project_resource'},
                    {name: 'projectCustomer', mapping: 'project_customer'},
                    {name: 'projectSchedule', mapping: 'project_schedule'},
                    {name: 'projectdocument', mapping: 'project_document_id'},
                    {name: 'projectManager', mapping: 'project_manager'},
                    {name: 'accountManager', mapping: 'account_manager'}
                ]
            })
        });


         var columnModel = new Ext.grid.ColumnModel({
            columns : [selectionModel,
                {header: "Id", width: 120, dataIndex: 'id', hidden: true},
                {header: "Project Name", width: 120, dataIndex: 'name', sortable: true},
                {header: "Account Manager", width: 100, dataIndex: 'accountManager'},
                {header: "Project Manager", width: 100,dataIndex: 'projectManager' },
                {header: "Start Date", width: 80, dataIndex: 'projectStartDate', sortable: true, xtype: 'datecolumn', format: 'd-M-Y'},
                {header: "Finish Date", width: 80, dataIndex: 'projectEndDate', sortable: true, xtype: 'datecolumn', format: 'd-M-Y'},
                {header: "Customer", width: 120, dataIndex: 'projectCustomer', sortable: true},
                {header: "Financial", width: 100, dataIndex: 'projectFinancial', sortable: true,
                    renderer:function(value){
                       return value;
                    }
                },
                {header: "Schedule", width: 100, dataIndex: 'projectSchedule', sortable: true,
                    renderer:function(value){
                        if(value=='On Schedule')
                            return '<span class="normal">' + value + '</span>';
                        else if(value=='Potentially Over Schedule')
                            return '<span class="pre-warning">' + value + '</span>';
                        return '<span class="warning">' + value + '</span>';
                    }
                },
                {header: "Contract/Legal", width: 100, dataIndex: 'projectContract', sortable: true,
                    renderer:function(value){
                        if(value=='Done')
                            return '<span class="normal">' + value + '</span>';
                        else if(value=='Warning')
                            return '<span class="pre-warning">' + value + '</span>';
                        return '<span class="warning">' + value + '</span>';
                    }
                },
                {header: "Technical", width: 100, dataIndex: 'projectTechnical', sortable: true,
                    renderer:function(value){
                        if(value=='No Problem')
                            return '<span class="normal">' + value + '</span>';
                        else if(value=='Potentially Problem')
                            return '<span class="pre-warning">' + value + '</span>';
                        return '<span class="warning">' + value + '</span>';
                    }
                },
                {header: "Resource", width: 100, dataIndex: 'projectResource', sortable: true,
                    renderer:function(value){
                        if(value=='Available')
                            return '<span class="normal">' + value + '</span>';
                        else if(value=='Potentially Problem')
                            return '<span class="pre-warning">' + value + '</span>';
                        return '<span class="warning">' + value + '</span>';
                    }
                }
             
            ]
        });

        

 var storeResources = new Ext.data.GroupingStore({
            storeId  : 'storeResource',
            pruneModifiedRecords:true,
            autoSave:false ,
            groupField :'projectresourcename',
            proxy: new Ext.data.HttpProxy({ method:'POST', url: '../projectresource/json' }),
            baseParams : { start:0, limit:10 },
            sortInfo: { field: 'projectresourcename', direction: 'ASC' },
            //remoteGroup:true,
            remoteSort: true,
            reader: new Ext.data.JsonReader({
                            root: 'data',
                            id: 'resourcedatajson',
                            totalRecords: 'total',
                            fields : [
                                {name: 'id', mapping: 'id',type:'int'},
                                {name: 'projectresourcename', mapping: 'projectresourcename', type:'string'},
                                {name: 'mandaysAllocation', mapping: 'mandaysAllocation',type:'int'},
                                {name: 'mandaysUsage', mapping: 'mandaysUsage',type:'int'},
                                {name: 'projectresourceid', mapping: 'projectresourceid',type:'int'},
                                {name: 'projectid', mapping: 'projectid',type:'int'},
                                {name: 'month', mapping: 'month',type:'string'}
                            ]
                })
        }); 




      var columnModelResource = new Ext.grid.ColumnModel({
           columns : [ new Ext.grid.RowNumberer({width: 30}),
                selectionResourceModel,
                { header: "Id", dataIndex: 'id', hidden:true},
                { header: "Project Resource Id", dataIndex: 'projectresourceid', hidden:true},
                { header: "Project Id", dataIndex: 'projectid', hidden:true},
                { header: "Project Resource Name", width: 220, dataIndex: 'projectresourcename', sortable: true,hidden:true},
                { header: "Mandays Allocation", width: 100,dataIndex: 'mandaysAllocation',summaryType :'sum',hidden :true},
                { header: "Month", width: 100, dataIndex: 'month', sortable: true, summaryType :'sum',summaryRenderer:function(v, params, data){
                        return "Mandays Allocation : " +  parseInt(data.data.mandaysAllocation);
                } },
                { header: "Mandays Usage", width: 100,dataIndex: 'mandaysUsage',summaryType :'sum', editor :new Ext.form.TextField({vtype:'numeric'}) },
                { header: "Remaining", width: 100 ,summaryType:'sum',summaryRenderer:function(v, params, data){
                         var total = data.data.mandaysAllocation - data.data.mandaysUsage
                         if (total.toString().match(/^-\d+$/)) total = "<span class='warning-font'>"+ total +"</span>";
                         return total;

                }}

                ]
        });

         storeResources.on('update',function(store,record,operation){
                Ext.Ajax.request({
                    url: '../projectresource/addProcess',
                    success:function(response){
                        var status = Ext.util.JSON.decode(response.responseText).success;
                        if(status==false)
                        Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                        },
                        failure:function(){
                        Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                        },
                        params: {   id : record.data.id,
                                    'project.id' : record.data.projectid,
                                    mandaysUsage : record.data.mandaysUsage,
                                    month :record.data.month,
                                    'projectresourcename.id' : record.data.projectresourceid
                    } } );
        });



       var storeFinancial = new Ext.data.GroupingStore({
            storeId  : 'storeFinancial',
            //groupField :'projectfinancial',
            proxy: new Ext.data.HttpProxy({ method:'POST', url: '../projectfinancial/json' }),
            baseParams : { start:0, limit:10 },
            sortInfo: { field: 'id', direction: 'DESC' },
            //remoteGroup:true,
            remoteSort: true,
            reader: new Ext.data.JsonReader({
                            root: 'data',
                            id: 'financialdatajson',
                            totalRecords: 'total',
                            fields : [
                                {name: 'id', mapping: 'id',type:'int'},
                                {name: 'name', mapping: 'name',type:'string'},
                                {name: 'status', mapping: 'status',type:'string'},
                                {name: 'project_id', mapping: 'project_id',type:'int'},
                                {name: 'date', mapping: 'date',type:'string' },
                                {name: 'value', mapping: 'value',type:'int'},
                                {name: 'note', mapping: 'note',type:'string'}
                            ]
                })
        });

         var columnModelFinancial = new Ext.grid.ColumnModel({
           columns : [ new Ext.grid.RowNumberer({width: 30}),
                       selectionFinancialModel,
                { header: "Id", dataIndex: 'id', hidden:true},
                { header: "Name", dataIndex: 'name'},
                { header: "Status", dataIndex: 'status'},
                { header: "Date", dataIndex: 'date',width: 100},
                { header: "Value", width: 100,dataIndex: 'value', renderer:function(value){return 'Rp.' + value },editor :new Ext.form.TextField({vtype:'numeric'}) }
             ]
        });

        storeFinancial.on('update',function(store,record,operation){
                Ext.Ajax.request({
                    url: '../projectfinancial/addProcess',
                    success:function(response){
                        var status = Ext.util.JSON.decode(response.responseText).success;
                        if(status==false)
                        Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                        },
                        failure:function(){
                        Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                        },
                        params: {   id : record.data.id,
                                    projectFinName : record.data.name,
                                    projectFinDate : record.data.date,
                                    projectFinNote : record.data.note,
                                    project_id : record.data.project_id,
                                    projectFinStatus : record.data.status,
                                    projectFinValue : record.data.value
                                } } );
        });
 




var storeInternalCost = new Ext.data.GroupingStore({
            storeId  : 'storeInternalCost',
            groupField :'projectresourcename',
            proxy: new Ext.data.HttpProxy({ method:'POST', url: '../projectinternalcost/json' }),
            baseParams : { start:0, limit:10 },
            sortInfo: { field: 'projectresourcename', direction: 'ASC' },
            remoteSort: true,
            reader: new Ext.data.JsonReader({
                            root: 'data',
                            id: 'internalcostdatajson',
                            totalRecords: 'total',
                            fields : [
                                {name: 'id', mapping: 'id',type:'int'},
                                {name: 'projectresourcename', mapping: 'projectresourcename', type:'string'},
                                {name: 'mandaysAllocation', mapping: 'mandaysAllocation',type:'int'},
                                {name: 'mandaysUsage', mapping: 'mandaysUsage',type:'int'},
                                {name: 'projectresourceid', mapping: 'projectresourceid',type:'int'},
                                {name: 'projectid', mapping: 'projectid',type:'int'},
                                {name: 'month', mapping: 'month',type:'string'}
                            ]
                })
        });
     
      var columnModelInternalCost = new Ext.grid.ColumnModel({
           columns : [ new Ext.grid.RowNumberer({width: 30}),
                selectionInternalCostModel,
                { header: "Id", dataIndex: 'id', hidden:true},
                { header: "Project Resource Id", dataIndex: 'projectresourceid', hidden:true},
                { header: "Project Id", dataIndex: 'projectid', hidden:true},
                { header: "Cost Allocation", width: 100,dataIndex: 'mandaysAllocation',summaryType :'sum',hidden :true},
                { header: "Project Resource Name", width: 220, dataIndex: 'projectresourcename', sortable: true},
                { header: "Month", width: 100, dataIndex: 'month', sortable: true, summaryType :'sum',summaryRenderer:function(v, params, data){
                        return "Cost Allocation : " +  parseInt(data.data.mandaysAllocation);
                } },
                { header: "Cost Usage", width: 100,dataIndex: 'mandaysUsage',summaryType :'sum', editor :new Ext.form.TextField({vtype:'numeric'}) },
                { header: "Remaining", width: 100 ,summaryType:'sum',summaryRenderer:function(v, params, data){
                         var total = data.data.mandaysAllocation - data.data.mandaysUsage
                         if (total.toString().match(/^-\d+$/)) total = "<span class='warning-font'>"+ total +"</span>";
                         return total;
                }}

             ]
        });



        storeInternalCost.on('update',function(store,record,operation){
                Ext.Ajax.request({
                    url: '../projectinternalcost/addProcess',
                    success:function(response){
                        var status = Ext.util.JSON.decode(response.responseText).success;
                        if(status==false)
                        Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                        },
                        failure:function(){
                        Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                        },
                        params: {   id : record.data.id,
                                    'project.id' : record.data.projectid,
                                    mandaysAllocation : record.data.mandaysAllocation,
                                    mandaysUsage : record.data.mandaysUsage,
                                    month :record.data.month,
                                    'projectresourcename.id' : record.data.projectresourceid
                    } } );
        });





var storeSchedule = new Ext.data.GroupingStore({
            storeId  : 'storeSchedule',
            groupField :'projectScheduleName',
            proxy: new Ext.data.HttpProxy({ method:'POST', url: '../projectschedule/json' }),
            baseParams : { start:0, limit:10 },
            sortInfo: { field: 'projectScheduleName', direction: 'ASC' },
            remoteSort: true,
            reader: new Ext.data.JsonReader({
                            root: 'data',
                            totalRecords: 'total',
                            fields : [
                                {name: 'id', mapping: 'id',type:'int'},
                                {name: 'projectId', mapping: 'projectId',type:'int'},
                                {name: 'projectScheduleId', mapping: 'projectScheduleId'},
                                {name: 'projectScheduleName', mapping: 'projectScheduleName', type:'string'},
                                {name: 'projectScheduleStatus', mapping: 'projectScheduleStatus', type:'string'},
                                {name: 'projectPlannedDate', mapping: 'projectPlannedDate'},
                                {name: 'projectActualDate', mapping: 'projectActualDate'},
                                {name: 'projectRevisedDate', mapping: 'projectRevisedDate'},
                                {name: 'projectRemark', mapping: 'projectRemark'}
                            ]
                })
        });



        Ext.ux.grid.GroupSummary.Calculations['dateSummary'] = function(v, record, field,data){
            return record;
        };


      var columnModelSchedule= new Ext.grid.ColumnModel({
           columns : [ new Ext.grid.RowNumberer({width: 30}),
                selectionInternalCostModel,
                { header: "Id", dataIndex: 'id', hidden:true},
                { header: "Project Schedule Id", dataIndex: 'projectScheduleId', hidden:true},
                { header: "Project Id", dataIndex: 'projectId', hidden:true},
                { header: "Project Schedule Id", dataIndex: 'projectScheduleId', hidden:true},
                { header: "Project Schedule Name", dataIndex: 'projectScheduleName' },
                { header: "Status", dataIndex: 'projectScheduleStatus', summaryType :'dateSummary',
                    summaryRenderer:function(v, params, data){
                        //console.log(v);
                        return "Planned Date : " +  v.data.projectPlannedDate;
                } },
                { header: "Project Planned Date", dataIndex: 'projectPlannedDate',hidden:true },
                { header: "Project Actual Date", dataIndex: 'projectActualDate' },
                { header: "Project Revised Date", dataIndex: 'projectRevisedDate' },
                { header: "Project Remark", dataIndex: 'projectRemark' }

             ]
        });



        storeSchedule.on('update',function(store,record,operation){
                Ext.Ajax.request({
                    url: '../projectschedule/addProcess',
                    success:function(response){
                        var status = Ext.util.JSON.decode(response.responseText).success;
                        if(status==false)
                        Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                        },
                        failure:function(){
                        Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                        },
                        params: {   id : record.data.id,
                                    'project.id' : record.data.projectId,
                                    'projectScheduleId.id' : record.data.projectScheduleId
                    } } );
        });



var storeLegal = new Ext.data.Store({
            storeId  : 'storeLegal',
            proxy: new Ext.data.HttpProxy({ method:'POST', url: '../projectlegal/json' }),
            baseParams : { start:0, limit:10 },
            sortInfo: { field: 'projectresourcename', direction: 'ASC' },
            remoteSort: true,
            reader: new Ext.data.JsonReader({
                            root: 'data',
                            id: 'internalcostdatajson',
                            totalRecords: 'total',
                            fields : [
                                {name: 'id', mapping: 'id',type:'int'},
                                {name: 'projectresourcename', mapping: 'projectresourcename', type:'string'}
                               
                            ]
                })
        });

      var columnModelLegal = new Ext.grid.ColumnModel({
           columns : [ new Ext.grid.RowNumberer({width: 30}),
                selectionLegalModel,
                { header: "Id", dataIndex: 'id', hidden:true},
                { header: "Project Resource Id", dataIndex: 'projectresourceid', hidden:true}

             ]
        });



        storeLegal.on('update',function(store,record,operation){
                Ext.Ajax.request({
                    url: '../projectinternalcost/addProcess',
                    success:function(response){
                        var status = Ext.util.JSON.decode(response.responseText).success;
                        if(status==false)
                        Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                        },
                        failure:function(){
                        Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                        },
                        params: {   id : record.data.id,
                                    'project.id' : record.data.projectid,
                                    mandaysAllocation : record.data.mandaysAllocation,
                                    mandaysUsage : record.data.mandaysUsage,
                                    month :record.data.month,
                                    'projectresourcename.id' : record.data.projectresourceid
                    } } );
        });



















        var parameterFormPanel = {
            id : 'fp',
            url :'../project/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                        fp.getForm().submit({
                            success:function(){

                                store.reload();
                                win.hide();
                                fp.getForm().reset();
                            },
                            waitMsg:'Please wait...'
                        });

                    }
                }],
            items : [{ xtype:'hidden', fieldLabel: 'Project Id', name: 'id', inputValue :null },
                { xtype:'textfield',fieldLabel: 'Project Name', name: 'name', allowBlank:false },
                { xtype:'textfield',fieldLabel: 'Customer', name: 'projectCustomer', allowBlank:false },
                { xtype:'textfield',fieldLabel: 'Account Manager', name: 'accountManager'  },
                { xtype:'textfield',fieldLabel: 'Project Manager', name: 'projectManager'  },
                { xtype:'datefield',fieldLabel: 'Project Start Date', name: 'projectStartDate', allowBlank:false,editable :false, format : 'Y-m-d' },
                { xtype:'datefield',fieldLabel: 'Project End Date', name: 'projectEndDate', allowBlank:false,editable :false, format : 'Y-m-d' },
                { xtype:'textfield',fieldLabel: 'Project Value', name: 'projectValue' ,vtype:'numeric'},
                
                { fieldLabel: 'Technical', xtype : 'radiogroup',
                    items : [{ boxLabel : 'No Problem', name: 'projectTechnical', checked : true, inputValue : 'No Problem' },
                        { boxLabel : 'Potentially Problem', name: 'projectTechnical', inputValue : 'Potentially Problem' },
                        { boxLabel : 'Problem', name: 'projectTechnical', inputValue : 'Problem' }] },

                { fieldLabel: 'Contract/Legal', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectContract', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Warning', name: 'projectContract', inputValue : 'Warning' },
                        { boxLabel : 'Issue', name: 'projectContract', inputValue : 'Issue' }] }
            ],
            reader : {
                id : 'myFormData',
                root : 'data',
                totalRecords : 'total',
                fields : [{ name: 'id', mapping: 'id'},
                    { name: 'name', mapping: 'name'},
                    { name: 'projectEndDate', mapping: 'project_end_date'},
                    { name: 'projectStartDate',mapping: 'project_start_date'},
                    { name: 'projectFinancial',mapping: 'financial' },
                    { name: 'projectContract', mapping: 'project_contract' },
                    { name: 'projectTechnical', mapping: 'project_technical' },
                    { name: 'projectResource', mapping: 'project_resource' },
                    { name: 'projectCustomer', mapping: 'project_customer' },
                    { name: 'projectSchedule', mapping: 'project_schedule' },
                    { name: 'accountManager', mapping: 'account_manager' },
                    { name: 'projectManager', mapping: 'project_manager' },
                    { name: 'projectValue', mapping: 'project_value' }

                ]
            }
        };
    

        var parameterFormFinancial = {
            id : 'fpFin',
            labelAlign: 'top',
            url :'../projectfinancial/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                      fpfin.getForm().submit({
                            params: { project_id : selectionModel.getSelected().data.id },
                            success:function(){
                                storeFinancial.reload();
                                fpfin.getForm().reset();
                            },
                            waitMsg:'Please wait...'
                        });
                    }
                }],
            reader : {},
            items: [{
                    layout:'column',
                    items:[
                        
                        { width:200, layout: 'form',
                            items: [{ xtype:'textfield', fieldLabel: 'Name', name: 'projectFinName',anchor:'95%', allowBlank:false }]
                        },
                        { width:150, layout: 'form',
                           items: [{ xtype:'radiogroup', fieldLabel: 'Payment Status', anchor:'90%',
                                            items:[{ boxLabel :'Paid', checked : true ,name: 'projectFinStatus', inputValue :'Paid' },
                                                   { boxLabel  :'Pending' , name: 'projectFinStatus',inputValue :'Pending' }]
                                        }]
                        },
                        { width:100, layout: 'form',
                            items: [{ xtype:'datefield', fieldLabel: 'Date', name: 'projectFinDate',anchor:'95%', allowBlank:false,format : 'Y-m-d' }]
                        },
                        { width:100, layout: 'form',
                            items: [{ xtype:'textfield', fieldLabel: 'Value', name: 'projectFinValue',anchor:'95%', allowBlank:false,vtype : 'numeric' }]
                        },
                        { width:200, layout: 'form',
                            items: [{ xtype:'textfield', fieldLabel: 'Note', name: 'projectFinNote',anchor:'95%', allowBlank:false }]
                        }
                        ]
                },{
                     xtype:'editorgrid',
                     id :'financialGrid',
                     store : storeFinancial,
                     sm: selectionFinancialModel,
                     clicksToEdit : 1,
                     title : 'Resources',
                     height : 580,
                     iconCls: 'icon-list',
                     colModel : columnModelFinancial,
                     tbar : [ {    iconCls: 'icon-delete-button', text : "Delete",
                                    handler  : function(){
                                        var selection = selectionFinancialModel.getSelections();
                                        var ids = [];
                                        for(var i = 0;i<selection.length;i++)  ids.push(selection[i].data.id);
                                         Ext.Ajax.request({
                                            url: '../projectfinancial/delete',
                                            success:function(response){
                                                var status = Ext.util.JSON.decode(response.responseText).success;
                                                if(status==false){
                                                    Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                                                }
                                                 
                                                storeFinancial.reload();
                                            },
                                            failure:function(){
                                                Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                                            },
                                            params: { id : ids } });


                    }
                }]
               // , bbar:  { pageSize: 10, store: store, displayInfo: true, displayMsg: 'Displaying Records {0} - {1} of {2}', emptyMsg: "No Records to display" }
                }
            ]
        }
 


        var parameterFormLegal = {
            id : 'fpLeg',
            labelAlign: 'top',
            url :'../projectlogal/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                      fpLeg.getForm().submit({
                            params: { 'project.id' : selectionModel.getSelected().data.id },
                            success:function(){
                                storeLegal.reload();
                                fpLeg.getForm().reset();
                            },
                            waitMsg:'Please wait...'
                        });
                    }
                }],
            reader : {},
            items: [{
                    layout:'column',
                    items:[
                        { width:200, layout: 'form',
                            items: [
                                     {  xtype:'combo',
                                        name : 'projectLegalName',
                                        hiddenName:'projectLegalName',
                                        typeAhead: true,
                                        triggerAction: 'all',
                                        lazyRender:true,
                                        mode: 'local',
                                        fieldLabel: 'Legal',
                                        store: new Ext.data.ArrayStore({
                                            fields: [ 'value', 'text'],
                                            data: [['SPK', 'SPK'], ['Contract', 'Contract'] , ['Addendum', 'Addendum'] , ['Other(s)', 'Other(s)'] ]
                                        }),
                                        valueField: 'value',
                                        displayField: 'text'
                                          }]
                        },
                        { width:100, layout: 'form',
                            items: [{ xtype:'datefield', fieldLabel: 'Date', name: 'projectLegalDate',anchor:'95%',allowBlank:false }]
                        },
                        { width:200, layout: 'form',
                            items: [{  xtype:'combo',
                                        name : 'projectLegalRequired',
                                        hiddenName:'projectLegalRequired',
                                        typeAhead: true,
                                        triggerAction: 'all',
                                        lazyRender:true,
                                        mode: 'local',
                                        fieldLabel: 'Legal',
                                        store: new Ext.data.ArrayStore({
                                            fields: [ 'value', 'text'],
                                            data: [['Required', 'Required'], ['Not Required', 'Not Required']]
                                        }),
                                        valueField: 'value',
                                        displayField: 'text' }]
                        },
                         { width:150, layout: 'form',
                            items: [{ xtype:'radiogroup', labelSeparator:'',fieldLabel:'Status', anchor:'95%',
                                    items : [{ boxLabel : 'Done', name: 'projectLegalStatus', checked : true, inputValue : 'Done' },
                                             { boxLabel : 'Pending', name: 'projectLegalStatus', inputValue : 'Pending'  }]
                                    }]
                        }
                        ]
                },{
                     xtype:'editorgrid',
                     id :'legalGrid',
                     store : storeLegal,
                     sm: selectionLegalModel,
                     clicksToEdit : 1,
                     title : 'Legal',
                     height : 580 ,
                     iconCls: 'icon-list',
                     colModel : columnModelLegal,
                      tbar : [ {    iconCls: 'icon-delete-button', text : "Delete",
                                    handler  : function(){
                                        var selection = selectionLegalModel.getSelections();
                                        var ids = [];
                                        for(var i = 0;i<selection.length;i++)  ids.push(selection[i].data.id);
 
                                        storeResources.reload();


                                        /*
                                        Ext.Ajax.request({
                                            url: '../projectresource/delete',
                                            success:function(response){
                                                var status = Ext.util.JSON.decode(response.responseText).success;
                                                if(status==false){
                                                    Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                                                }
                                                storeResources.reload();
                                            },
                                            failure:function(){
                                                Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                                            },
                                            params: { id : ids } });
                                            */

                    }
                }]

                }
            ]
        };



        var parameterFormResource ={
            id : 'fpRes',
            labelAlign: 'top',
            url :'../projectresource/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                      fpRes.getForm().submit({
                            params: { 'project.id' : selectionModel.getSelected().data.id },
                            success:function(){
                                storeResources.reload();
                                fpRes.getForm().reset();
                            },
                            waitMsg:'Please wait...'
                        });
                    }
                }],
            reader : {},
            items: [{
                    layout:'column',
                    items:[
                        { width:200, layout: 'form',
                            items: [ 
                                     { xtype:'combo',
                                       allowBlank:false,
                                       name : 'projectresourcename.id',
                                       hiddenName:'projectresourcename.id',
                                       displayField:'name',
                                       valueField:'id',
                                       lazyRender:true,
                                       typeAhead: true,
                                       queryParam : '',
                                       triggerAction: 'all',
                                       mode: 'remote',
                                       fieldLabel: 'Resource',
                                       anchor:'95%',
                                       editable : false,
                                       emptyText : '-----Select Resource Name-----',
                                       store: new Ext.data.Store({
                                                                proxy: new Ext.data.HttpProxy({
                                                                    method:'POST',
                                                                    url: '../projectresourcename/json'
                                                                }),
                                                                autoLoad :false,
                                                                remoteSort :true,
                                                                baseParams : { start:0, limit:10 },
                                                                sortInfo: { field: 'id', direction: 'DESC' },
                                                                reader: new Ext.data.JsonReader({
                                                                    root: 'data',
                                                                    id: 'sidiasd',
                                                                    totalRecords: 'total',
                                                                    fields : [
                                                                        {name: 'id', mapping: 'id'},
                                                                        {name: 'name', mapping: 'name'}
                                                                    ]
                                                                } )
                                                            })
                                          } ]
                        },
                        { width:100, layout: 'form',
                            items: [{ xtype:'datefield', fieldLabel: 'Date', name: 'month',anchor:'95%',allowBlank:false }]
                        },
                        { width:200, layout: 'form',
                            items: [{ xtype:'textfield', fieldLabel: 'Mandays Alocation', name: 'mandaysAllocation',anchor:'95%',vtype : 'numeric', allowBlank:false }]
                        },
                        { width:200, layout: 'form',
                            items: [{ xtype:'textfield', fieldLabel: 'Mandays Usage', name: 'mandaysUsage',anchor:'95%',vtype : 'numeric', allowBlank:false }]
                        }
                        ]
                },{
                     xtype:'editorgrid',
                     id :'resourceGrid',
                     store : storeResources,
                     sm: selectionResourceModel,
                     clicksToEdit : 1,
                     title : 'Resources',
                     height : 580,
                     view: new Ext.grid.GroupingView({
                         forceFit:true,
                         showGroupName: false,
                         enableNoGroups: true,
			 enableGroupingMenu: true,
                         hideGroupedColumn: true
                        // ignoreAdd:true
                         //groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Datas" : "Data"]})'
                     }),
                     plugins: [summary],
                     iconCls: 'icon-list',
                     colModel : columnModelResource,
                      tbar : [ {    iconCls: 'icon-delete-button', text : "Delete",
                                    handler  : function(){
                                        var selection = selectionResourceModel.getSelections();
                                        var ids = [];
                                        for(var i = 0;i<selection.length;i++)  ids.push(selection[i].data.id);

                                        /*
                                        var record = new Ext.data.Record(
                                        {name: 'id', mapping: 'id' ,id:'nya'},
                                        {name: 'projectresourcename', mapping: 'projectresourcename'},
                                        {name: 'mandays', mapping: 'mandays'},
                                        {name: 'month', mapping: 'month'});
                                        storeResources.remove(record);
                                        */
                                        
                                        storeResources.reload();


                                        /*
                                        Ext.Ajax.request({
                                            url: '../projectresource/delete',
                                            success:function(response){
                                                var status = Ext.util.JSON.decode(response.responseText).success;
                                                if(status==false){
                                                    Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                                                }
                                                storeResources.reload();
                                            },
                                            failure:function(){
                                                Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                                            },
                                            params: { id : ids } });
                                            */

                    }
                }]
               // , bbar:  { pageSize: 10, store: store, displayInfo: true, displayMsg: 'Displaying Records {0} - {1} of {2}', emptyMsg: "No Records to display" }
                }
            ]
        }
 



   var parameterFormInternalCost = {
      id : 'fpRes',
            labelAlign: 'top',
            url :'../projectinternalcost/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                      fpInt.getForm().submit({
                            params: { 'project.id' : selectionModel.getSelected().data.id },
                            success:function(){
                                storeInternalCost.reload();
                                fpInt.getForm().reset();
                            },
                            waitMsg:'Please wait...'
                        });
                    }
                }],
            reader : {},
            items: [{
                    layout:'column',
                    items:[
                        { width:200, layout: 'form',
                            items: [
                                     { xtype:'combo',
                                       allowBlank:false,
                                       name : 'projectresourcename.id',
                                       hiddenName:'projectresourcename.id',
                                       displayField:'name',
                                       valueField:'id',
                                       lazyRender:true,
                                       typeAhead: true,
                                       queryParam : '',
                                       triggerAction: 'all',
                                       mode: 'remote',
                                       fieldLabel: 'Resource',
                                       anchor:'95%',
                                       editable : false,
                                       emptyText : '-----Select Resource Name-----',
                                       store: new Ext.data.Store({
                                                                proxy: new Ext.data.HttpProxy({
                                                                    method:'POST',
                                                                    url: '../projectresourcename/json'
                                                                }),
                                                                autoLoad :false,
                                                                remoteSort :true,
                                                                baseParams : { start:0, limit:10 },
                                                                sortInfo: { field: 'id', direction: 'DESC' },
                                                                reader: new Ext.data.JsonReader({
                                                                    root: 'data',
                                                                    id: 'sidiasd',
                                                                    totalRecords: 'total',
                                                                    fields : [
                                                                        {name: 'id', mapping: 'id'},
                                                                        {name: 'name', mapping: 'name'}
                                                                    ]
                                                                } )
                                                            })
                                          } ]
                        },
                        { width:100, layout: 'form',
                            items: [{ xtype:'datefield', fieldLabel: 'Date', name: 'month',anchor:'95%',allowBlank:false }]
                        },
                        { width:200, layout: 'form',
                            items: [{ xtype:'textfield', fieldLabel: 'Mandays Alocation', name: 'mandaysAllocation',anchor:'95%',vtype : 'numeric', allowBlank:false }]
                        },
                        { width:200, layout: 'form',
                            items: [{ xtype:'textfield', fieldLabel: 'Mandays Usage', name: 'mandaysUsage',anchor:'95%',vtype : 'numeric', allowBlank:false }]
                        }
                        ]
                },{
                     xtype:'editorgrid',
                     id :'resourceGrid',
                     store : storeInternalCost,
                     sm: selectionInternalCostModel,
                     clicksToEdit : 1,
                     title : 'Resources',
                     height : 580,
                     view: new Ext.grid.GroupingView({
                         forceFit:true,
                         showGroupName: false,
                         enableNoGroups: true,
			 enableGroupingMenu: true,
                         hideGroupedColumn: true,
                         ignoreAdd:true
                         //groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Datas" : "Data"]})'
                     }),
                     plugins: summaryInternalCost,
                     iconCls: 'icon-list',
                     colModel : columnModelInternalCost,
                      tbar : [ {    iconCls: 'icon-delete-button', text : "Delete",
                                    handler  : function(){
                                        var selection = selectionResourceModel.getSelections();
                                        var ids = [];
                                        for(var i = 0;i<selection.length;i++)  ids.push(selection[i].data.id);

                                         Ext.Ajax.request({
                                            url: '../projectinternalcost/delete',
                                            success:function(response){
                                                var status = Ext.util.JSON.decode(response.responseText).success;
                                                if(status==false){
                                                    Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                                                }
                                                storeInternalCost.reload();
                                            },
                                            failure:function(){
                                                Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                                            },
                                            params: { id : ids } });

                    }
                }]
          
                }
            ]


   }



        var parameterFormSchedule = {
             id : 'fpSch',
            labelAlign: 'top',
            url :'../projectschedule/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                      fpSch.getForm().submit({
                            params: { 'project.id' : selectionModel.getSelected().data.id },
                            success:function(){
                                storeSchedule.reload();
                                fpSch.getForm().reset();
                            },
                            waitMsg:'Please wait...'
                        });
                    }
                }],
            reader : {},
            items: [{
                    layout:'column',
                    items:[
                        { width:200, layout: 'form',
                            items: [
                                     { xtype:'combo',
                                       allowBlank:false,
                                       name : 'projectScheduleName.id',
                                       hiddenName:'projectScheduleName.id',
                                       displayField:'name',
                                       valueField:'id',
                                       lazyRender:true,
                                       typeAhead: true,
                                       queryParam : '',
                                       triggerAction: 'all',
                                       mode: 'remote',
                                       fieldLabel: 'Resource',
                                       labelSeparator:'',
                                       anchor:'95%',
                                       editable : false,
                                       emptyText : '-----Select Schedule-----',
                                       store: new Ext.data.Store({
                                                                proxy: new Ext.data.HttpProxy({
                                                                    method:'POST',
                                                                    url: '../projectschedulename/json'
                                                                }),
                                                                autoLoad :false,
                                                                remoteSort :true,
                                                                baseParams : { start:0, limit:10 },
                                                                sortInfo: { field: 'name', direction: 'ASC' },
                                                                reader: new Ext.data.JsonReader({
                                                                    root: 'data',
                                                                    totalRecords: 'total',
                                                                    fields : [
                                                                        {name: 'id', mapping: 'id'},
                                                                        {name: 'name', mapping: 'name'}
                                                                    ]
                                                                } )
                                                            })
                                          } ]
                        },
                        { width:150, layout: 'form',
                            items: [{ xtype:'radiogroup', labelSeparator:'',fieldLabel:'Status', anchor:'95%',
                                    items : [{ boxLabel : 'Done', name: 'projectScheduleStatus', checked : true, inputValue : 'Done' },
                                             { boxLabel : 'Pending', name: 'projectScheduleStatus', inputValue : 'Pending'  }]
                                    }]
                        },
                        { width:110, layout: 'form',
                            items: [{ xtype:'datefield',labelSeparator:'', fieldLabel:'Planned Schedule', name: 'projectPlannedDate',anchor:'95%',format : 'Y-m-d',allowBlank:false }]
                        },
                        { width:110, layout: 'form',
                            items: [{ xtype:'datefield',labelSeparator:'', fieldLabel:'Actual Activity', name: 'projectActualDate',format : 'Y-m-d',anchor:'95%'}]
                        },
                        { width:110, layout: 'form',
                            items: [{ xtype:'datefield', labelSeparator:'',fieldLabel: 'Revised Schedule', name: 'projectRevisedDate',format : 'Y-m-d',anchor:'95%'}]
                        },
                        { width:200, layout: 'form',
                            items: [{ xtype:'textfield', labelSeparator:'',fieldLabel: 'Remark', name: 'projectRemark',anchor:'95%', allowBlank:true }]
                        }
                        ]
                },{
                     xtype:'editorgrid',
                     store : storeSchedule,
                     sm: selectionScheduleModel,
                     clicksToEdit : 1,
                     title : 'Resources',
                     height : 580,
                     view: new Ext.grid.GroupingView({
                         forceFit:true,
                         showGroupName: false,
                         enableNoGroups: true,
			 enableGroupingMenu: true,
                         hideGroupedColumn: true,
                         ignoreAdd:true
                     }),
                     plugins: summarySchedule,
                     iconCls: 'icon-list',
                     colModel : columnModelSchedule,
                      tbar : [ {    iconCls: 'icon-delete-button', text : "Delete",
                                    handler  : function(){
                                        var selection = selectionScheduleModel.getSelections();
                                        var ids = [];
                                        for(var i = 0;i<selection.length;i++)  ids.push(selection[i].data.id);

                                         Ext.Ajax.request({
                                            url: '../projectschedule/delete',
                                            success:function(response){
                                                var status = Ext.util.JSON.decode(response.responseText).success;
                                                if(status==false){
                                                    Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                                                }
                                                storeSchedule.reload();
                                            },
                                            failure:function(){
                                                Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection', buttons: Ext.MessageBox.OK, icon:'ext-mb-error'});
                                            },
                                            params: { id : ids } });

                    }
                }]

                }
            ]
        };

 
        var parameterFormPanelDocument = {
            layout: 'form',
            id : 'fpdoc',
            url :'../projectdocument/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(){
                        fpdoc.getForm().submit({ waitMsg:'Please wait...' });
                    }
                },{
                    text : 'Report',
                    handler : function(){
                        var project_document_id = fpdoc.getForm().findField('PROJECT_MANAGEMENT_ID').getValue();
                        document.location.href='../projectdocument/report?id=' + project_document_id;
                    }
                }],
            items : [{ xtype:'hidden', fieldLabel: 'Project Document Id', name: 'PROJECT_MANAGEMENT_ID', inputValue :null },
                { fieldLabel: 'RFP', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_RFP', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_RFP', inputValue : 'No' },
                        { xtype : 'textfield', name :'PROJECT_RFP_NOTE' }] },
                { fieldLabel: 'Addendum RFP', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_RFP_ADENDUM', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_RFP_ADENDUM', inputValue : 'No' },
                        { xtype : 'textfield', name :'PROJECT_RFP_ADENDUM_NOTE' }] },
                { fieldLabel: 'MOM', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_MOM', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_MOM', inputValue : 'No' },
                        { xtype : 'textfield', name :'PROJECT_MOM_NOTE' }] },
                { fieldLabel: 'Technical Assessment Form', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_TAF', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_TAF', inputValue : 'No'},
                        { xtype : 'textfield', name :'PROJECT_TAF_NOTE'}] },
                { fieldLabel: 'Technical Proposal', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_TP', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_TP', inputValue : 'No' },
                        { xtype : 'textfield', name :'PROJECT_TP_NOTE'}] },
                { fieldLabel: 'Technical Presentation / POC', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_POC', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_POC', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_POC_NOTE' }] },
                { fieldLabel: 'PMO Mandays Estimation', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_ME', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_ME', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_ME_NOTE' }] },
                { fieldLabel: 'PMO Risk Mitigation Letter', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_RML', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_RML', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_RML_NOTE' }] },
                { fieldLabel: 'SPK / PO', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_SPK', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_SPK', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_SPK_NOTE' }] },
                { fieldLabel: 'Contract / Agreement', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_CONTRACT', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_CONTRACT', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_CONTRACT_NOTE' }] },

                { fieldLabel: 'Addendum Contract', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_CONTRACT_ADDENDUM', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_CONTRACT_ADDENDUM', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_CONTRACT_ADDENDUM_NOTE' }] },

                { fieldLabel: 'Project Manager Authorization Letter', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_MAL', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_MAL', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_MAL_NOTE' }] },

                { fieldLabel: 'Join Planning Session / DRM / Project Charter', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_JPS', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_JPS', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_JPS_NOTE' }] },

                { fieldLabel: 'Kick Off Document / Sign Off', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_KOD', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_KOD', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_KOD_NOTE' }] },

                { fieldLabel: 'Project Progress Summary Report (Weekly)', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_PSR', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_PSR', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_PSR_NOTE' }] },

                { fieldLabel: 'Delivery Order', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_DO', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_DO', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_DO_NOTE' }] },

                { fieldLabel: 'MOM', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_IMPLEMENTATION_MOM', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_IMPLEMENTATION_MOM', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_IMPLEMENTATION_MOM_NOTE' }] },
                                 
                { fieldLabel: 'Change Request', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_CHANGE_REQUEST', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_CHANGE_REQUEST', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_CHANGE_REQUEST_NOTE' }] },

                { fieldLabel: 'UAT', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_UAT', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_UAT', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_UAT_NOTE' }] },

                { fieldLabel: 'Berita Acara / BAST', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_BAST', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_BAST', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_BAST_NOTE' }] },

                { fieldLabel: 'Project Closing Report', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_CR', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_CR', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_CR_NOTE' }] },

                { fieldLabel: 'Training', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_TRAINING', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_TRAINING', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_TRAINING_NOTE' }] },

                { fieldLabel: 'Handover to Maintenance Document', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Yes', name: 'PROJECT_HTD', checked : true, inputValue : 'Yes' },
                        { boxLabel : 'No', name: 'PROJECT_HTD', inputValue : 'No' },
                        { xtype : 'textfield', name: 'PROJECT_HTD_NOTE' }] }
            ],
            reader : {
                id : 'myFormData',
                root : 'data',
                totalRecords : 'total',
                fields : [{ name: 'PROJECT_MANAGEMENT_ID', mapping: 'id'},
                    { name: 'PROJECT_RFP', mapping: 'rfp'},
                    { name: 'PROJECT_RFP_ADENDUM', mapping: 'rfp_addendum'},
                    { name: 'PROJECT_MOM', mapping: 'mom'},
                    { name: 'PROJECT_TAF', mapping: 'taf'},
                    { name: 'PROJECT_TP', mapping: 'tp'},
                    { name: 'PROJECT_POC', mapping: 'poc'},
                    { name: 'PROJECT_ME', mapping: 'me'},
                    { name: 'PROJECT_RML', mapping: 'rml'},
                    { name: 'PROJECT_SPK', mapping: 'spk'},
                    { name: 'PROJECT_CONTRACT', mapping: 'contract'},
                    { name: 'PROJECT_CONTRACT_ADDENDUM', mapping: 'contract_addendum'},
                    { name: 'PROJECT_MAL', mapping: 'mal'},
                    { name: 'PROJECT_JPS', mapping: 'jps'},
                    { name: 'PROJECT_KOD', mapping: 'kod'},
                    { name: 'PROJECT_PSR', mapping: 'psr'},
                    { name: 'PROJECT_DO', mapping: 'do'},
                    { name: 'PROJECT_IMPLEMENTATION_MOM', mapping: 'implementation_mom'},
                    { name: 'PROJECT_CHANGE_REQUEST', mapping: 'change_request'},
                    { name: 'PROJECT_UAT', mapping: 'uat'},
                    { name: 'PROJECT_BAST', mapping: 'bast'},
                    { name: 'PROJECT_CR', mapping: 'cr'},
                    { name: 'PROJECT_TRAINING', mapping: 'training'},
                    { name: 'PROJECT_HTD', mapping: 'htd'},
                    { name: 'PROJECT_RFP_NOTE', mapping: 'rfp_note'},
                    { name: 'PROJECT_RFP_ADENDUM_NOTE', mapping: 'rfp_addendum_note'},
                    { name: 'PROJECT_MOM_NOTE', mapping: 'mom_note'},
                    { name: 'PROJECT_TAF_NOTE', mapping: 'taf_note'},
                    { name: 'PROJECT_TP_NOTE', mapping: 'tp_note'},
                    { name: 'PROJECT_POC_NOTE', mapping: 'poc_note'},
                    { name: 'PROJECT_ME_NOTE', mapping: 'me_note'},
                    { name: 'PROJECT_RML_NOTE', mapping: 'rml_note'},
                    { name: 'PROJECT_SPK_NOTE', mapping: 'spk_note'},
                    { name: 'PROJECT_CONTRACT_NOTE', mapping: 'contract_note'},
                    { name: 'PROJECT_CONTRACT_ADDENDUM_NOTE', mapping: 'contract_addendum_note'},
                    { name: 'PROJECT_MAL_NOTE', mapping: 'mal_note'},
                    { name: 'PROJECT_JPS_NOTE', mapping: 'jps_note'},
                    { name: 'PROJECT_KOD_NOTE', mapping: 'kod_note'},
                    { name: 'PROJECT_PSR_NOTE', mapping: 'psr_note'},
                    { name: 'PROJECT_DO_NOTE', mapping: 'do_note'},
                    { name: 'PROJECT_IMPLEMENTATION_MOM_NOTE', mapping: 'implementation_mom_note'},
                    { name: 'PROJECT_CHANGE_REQUEST_NOTE', mapping: 'change_request_note'},
                    { name: 'PROJECT_UAT_NOTE', mapping: 'uat_note'},
                    { name: 'PROJECT_BAST_NOTE', mapping: 'bast_note'},
                    { name: 'PROJECT_CR_NOTE', mapping: 'cr_note'},
                    { name: 'PROJECT_TRAINING_NOTE', mapping: 'training_note'},
                    { name: 'PROJECT_HTD_NOTE', mapping: 'htd_note'}
                ]
            }
        };
 
 
    
    
        var parameterGridPanel = {
            store : store,
            title : 'Project',
            renderTo:'grid',
            height : 469,
            iconCls: 'icon-list',
            sm: selectionModel,
            colModel : columnModel,
            bbar:  { pageSize: 10, store: store, displayInfo: true, displayMsg: 'Displaying Records {0} - {1} of {2}', emptyMsg: "No Records to display" },
            tbar : [{ id:'addButton',iconCls: 'icon-add-button', text : "Add", handler  : showWindow  },
                { iconCls: 'icon-edit-button',text : "Edit",
                    handler  : function(){
                        var selectionLength = selectionModel.getSelections().length;
                        if(selectionLength == 0 ){
                            Ext.Msg.show({
                                title: 'Warning',
                                msg :'You have not chosen any data yet!',
                                buttons: Ext.MessageBox.OK,
                                icon:'ext-mb-info'
                            });
                        }
                        else if(selectionLength == 1){
                            var selection = selectionModel.getSelected();
                            var id = selection.data.id;
                            showWindow();
                            fp.getForm().load({
                                url: '../project/add/' + id,
                                waitMsg:'Please wait...'
                            });
                        }
                        else if(selectionLength > 1){
                            Ext.Msg.show({
                                title: 'Warning',
                                msg :'You cannot choose more than 1 data to edit!!',
                                buttons: Ext.MessageBox.OK,
                                icon:'ext-mb-info'
                            });
                        }
                    }
                },{ iconCls: 'icon-delete-button', text : "Delete",
                    handler  : function(){
                        var selection = selectionModel.getSelections();
                        var ids = "";
                        for(var i = 0;i<selection.length;i++){
                            ids +=  selection[i].data.id + ",";
                        }
                        ids = ids.substr(0, ids.length -1);
                        Ext.Ajax.request({
                            url: '../project/delete',
                            success:function(response){
                                var status = Ext.util.JSON.decode(response.responseText).success;
                                if(status==false){
                                    Ext.Msg.show({
                                        title: 'Warning',
                                        msg :'You have not chosen any data yet!',
                                        buttons: Ext.MessageBox.OK,
                                        icon:'ext-mb-info'
                                    });
                                }
                                store.reload();
                            },
                            failure:function(){
                                Ext.Msg.show({
                                    title: 'Error',
                                    msg :'There must be a problem with your connection',
                                    buttons: Ext.MessageBox.OK,
                                    icon:'ext-mb-error'
                                });
                            },
                            params: { id : ids } });
                    }
                },{ iconCls: 'icon-report-button', text : "Report",
                    handler  : function(){
                        document.location.href='../project/report';
                    }
                },{ iconCls: 'icon-detail', text : "Detail",
                    handler  : function(){
                        showWindowAll();
                    }
                }]

        };


    
        var gp = gridPanel(parameterGridPanel);
        var fp = formPanel(parameterFormPanel);
        var fpdoc = formPanel(parameterFormPanelDocument);
        var fpfin = formPanel(parameterFormFinancial);
        var fpLeg = formPanel(parameterFormLegal);
        var fpRes = formPanel(parameterFormResource);
        var fpSch = formPanel(parameterFormSchedule);
        var fpInt = formPanel(parameterFormInternalCost);






        /*Show windows*/
        function showWindow(){

            win.add(fp);
            win.setTitle('Add Project');
            fp.getForm().reset();
            win.setWidth(700);
            win.doLayout(true);
            win.show();
        }

        function showWindowAll(){
            var selection = selectionModel.getSelected();
            var selectionLength = selectionModel.getSelections().length;
            if(selectionLength == 0 ){
                Ext.Msg.show({ title: 'Warning', msg :'You have not chosen any data yet!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                return false;
            }else if(selectionLength > 1){
                Ext.Msg.show({ title: 'Warning', msg :'You cannot choose more than 1 data to edit!', buttons: Ext.MessageBox.OK, icon:'ext-mb-info' });
                return false;
            }


            var id = selection.data.id;
            fpdoc.getForm().load({ method:'GET',url: '../projectdocument/add/' + id , waitMsg:'Please wait...'});            
            var tabpannel = new Ext.TabPanel({
                activeTab: 0,
                defaults:{autoScroll: true},
                height: 300,             
                items :[{title : 'Document', items:[fpdoc]},
                        {title : 'Financial',listeners: { activate: function(){
                                  storeFinancial.load( { params : {  project_id : id } });
                                }}, items:[fpfin]},
                        {title : 'Legal/Contract',listeners: { activate: function(){
                                    //fpLeg.getForm().load({ method:'GET', url: '../projectlegal/add/' + id , waitMsg:'Please wait...'});
                                }},
                            items:[fpLeg]
                        },
                        {title : 'Resource',listeners: {activate: function(){
                                    storeResources.load( { params : {  project_id : id } });
                            }},
                            items:[fpRes] },
                        {title : 'Schedule',listeners: {activate: function(){
                                    storeSchedule.load( { params : {  project_id : id } });
                                }},
                            items:[fpSch]},
                        {title : 'Internal Cost',listeners: {activate: function(){
                          storeInternalCost.load( { params : {  project_id : id } });
                                }},
                            items:[fpInt]}
                ]
            });

            var win7 = new Ext.Window({
                closeAction:'hide',
                closable:true,
                modal: true,
                width : 950,
                plain:true,
                maximizable:true,
                items :tabpannel
            });
            win7.setTitle("Project Detail");
            win7.show();

            win7.on('maximize',function(){
            //set height to current window
            tabpannel.setHeight(document.body.clientHeight-30);
            });
            win7.on('restore',function(){
            //set height to current setting
            tabpannel.setHeight(300);
            });


        }

        var win = new Ext.Window({
            closeAction:'hide',
            closable:true,
            modal: true,
            width : 600,
            plain:true
        });




        //autorefresh 60 seconds
       // Ext.TaskMgr.start({ run: function(){ store.reload(); }, interval: 60000 });
        store.load();


      /*
         Ext.TaskMgr.start({ run:
                function(){
                //alert(selectionModel.getSelected());
                store.reload();
            }, interval: 10000 });
        *
        */
     });

    
</script>

<div id="grid"></div>
