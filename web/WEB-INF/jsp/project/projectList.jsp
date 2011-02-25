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

     //custom calculation
        Ext.ux.grid.GroupSummary.Calculations['customCount'] = function(v, record, field,data){
            var value = data[field+'count'] ? ++data[field+'count'] : (data[field+'count'] = 1);
            return  value  + (value > 1 ? ' Datas' : ' Data') ;
        };




        // create the Data Store
        
        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({
                method:'POST',
                url: '../project/json'
            }),
            autoLoad :false,
            remoteSort :true,
            baseParams : { 
                start:0,
                limit:10
            },
            sortInfo: {
                field: 'projectLastUpdate',
                direction: 'DESC' 
            },
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
                        if(value=='On Budget')
                            return '<span class="normal">' + value + '</span>';
                        else if(value=='Potentially Over Budget')
                            return '<span class="pre-warning">' + value + '</span>';
                        return '<span class="warning">' + value + '</span>';
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
            groupField :'projectresourcename',
            proxy: new Ext.data.HttpProxy({ method:'POST', url: '../projectresource/json' }),
            baseParams : { start:0, limit:10 },
            sortInfo: { field: 'id', direction: 'DESC' },
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
                { header: "Project Resource Name", width: 220, dataIndex: 'projectresourcename', sortable: true},
                { header: "Month", width: 100, dataIndex: 'month', sortable: true, summaryType :'customCount' },
                { header: "Mandays Allocation", width: 100,dataIndex: 'mandaysAllocation' },
                { header: "Mandays Usage", width: 100,dataIndex: 'mandaysUsage',summaryType :'sum', editor :new Ext.form.TextField({vtype:'numeric'}) }
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
                                    project_id : record.data.projectid,
                                    mandaysAllocation : record.data.mandaysAllocation,
                                    mandaysUsage : record.data.mandaysUsage,
                                    month :record.data.month,
                                    projectresourcename : record.data.projectresourceid
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
            sortInfo: { field: 'id', direction: 'DESC' },
            //remoteGroup:true,
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
                { header: "Project Resource Name", width: 220, dataIndex: 'projectresourcename', sortable: true},
                { header: "Month", width: 100, dataIndex: 'month', sortable: true, summaryType :'customCount' },
                { header: "Mandays Allocation", width: 100,dataIndex: 'mandaysAllocation' },
                { header: "Mandays Usage", width: 100,dataIndex: 'mandaysUsage',summaryType :'sum', editor :new Ext.form.TextField({vtype:'numeric'}) }
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
                                    project_id : record.data.projectid,
                                    mandaysAllocation : record.data.mandaysAllocation,
                                    mandaysUsage : record.data.mandaysUsage,
                                    month :record.data.month,
                                    projectresourcename : record.data.projectresourceid
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
            id : 'fpLegal',
            url :'../projectlegal/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                        fpleg.getForm().submit({ waitMsg:'Please wait...' });
                    }
                }],
            items : [ {   xtype:'hidden',fieldLabel: 'Project Legal Id', name: 'id', inputValue :null },
                {   fieldLabel: 'SPK', xtype : 'radiogroup' ,
                    items : [{ boxLabel : 'Done', name: 'spkStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'spkStatus', inputValue : 'Pending' },
                        { xtype : 'textfield', name:'spkNote' }]
                },
                {
                    fieldLabel: 'Kontrak', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'kontrakStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'kontrakStatus', inputValue : 'Pending' },
                        { xtype    : 'textfield', name:'kontrakNote' }]
                },
                {
                    fieldLabel: 'Addendum', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'addendumStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'addendumStatus', inputValue : 'Pending' },
                        { xtype    : 'textfield', name:'addendumNote' }]
                },
                {
                    fieldLabel: 'Other (s)', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'otherStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'otherStatus', inputValue : 'Pending' },
                        { xtype    : 'textfield', name:'otherNote' }]
                }


            ],
            reader : {
                id : 'myFormData',
                root : 'data',
                totalRecords : 'total',
                fields : [{ name: 'id', mapping: 'id'},
                    { name: 'spkStatus', mapping: 'spk_status'},
                    { name: 'spkNote', mapping: 'spk_note'},
                    { name: 'kontrakStatus', mapping: 'kontrak_status'},
                    { name: 'kontrakNote', mapping: 'kontrak_note'},
                    { name: 'addendumStatus', mapping: 'addend_status'},
                    { name: 'addendumNote', mapping: 'addend_note'},
                    { name: 'otherStatus', mapping: 'other_status'},
                    { name: 'otherNote', mapping: 'other_note'}
                ]
            }
        };

        var parameterFormResource ={
            id : 'fpRes',
            labelAlign: 'top',
            url :'../projectresource/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                      fpRes.getForm().submit({
                            params: { project_id : selectionModel.getSelected().data.id },
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
                                       name : 'projectresourcename',
                                       hiddenName:'projectresourcename',
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
                        { width:200, layout: 'form',
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
                         hideGroupedColumn: true,
                         ignoreAdd:true
                         //groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Datas" : "Data"]})'
                     }),
                     plugins: new Ext.ux.grid.GroupSummary(),
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
                            params: { project_id : selectionModel.getSelected().data.id },
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
                                       name : 'projectresourcename',
                                       hiddenName:'projectresourcename',
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
                        { width:200, layout: 'form',
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
                     plugins: new Ext.ux.grid.GroupSummary(),
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
            layout: 'form',
            id : 'fpsch',
            url :'../projectschedule/addProcess',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                        fpSch.getForm().submit({
                            failure:function(){
                                Ext.Msg.show({ title: 'Error', msg :'There must be a problem with your connection <br>or <br>\n\
                                                          Probably you have been inputed wrong values!',
                                    buttons: Ext.MessageBox.OK, icon:'ext-mb-error' });
                            },
                            waitMsg:'Please wait...'
                        });
                    }
                }],
            items : [{ xtype:'hidden', fieldLabel: 'Project Schedule Id', name: 'id', inputValue :null },
                { fieldLabel: 'Kick Off', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleKickOffStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleKickOffStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleKickOffPlanned',format : 'Y-m-d' },
                        { xtype : 'datefield', emptyText:'Actual Activity', name :'projectScheduleKickOffDate' ,format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleKickOffRevised',format : 'Y-m-d' },
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleKickOffRemarks' }
                    ] },
                { fieldLabel: 'Pengadaan Perangkat', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectSchedulePengadaanStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectSchedulePengadaanStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectSchedulePengadaanPlanned',format : 'Y-m-d' },
                        { xtype : 'datefield', emptyText:'Actual Activity', name :'projectSchedulePengadaanDate',format : 'Y-m-d' },
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectSchedulePengadaanRevised',format : 'Y-m-d' },
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectSchedulePengadaanRemarks' }
                    ] },
                { fieldLabel: 'Delivery Perangkat', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleDeliveryStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleDeliveryStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleDeliveryPlanned' ,format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Actual Activity', name :'projectScheduleDeliveryDate' ,format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleDeliveryRevised',format : 'Y-m-d' },
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleDeliveryRemarks' }
                    ] },
                { fieldLabel: 'Stagging / Pra Install', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleStagingStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleStagingStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleStagingPlanned' ,format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Actual Activity', name :'projectScheduleStagingDate' ,format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleStagingRevised' ,format : 'Y-m-d'},
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleStagingRemarks' }
                    ] },
                { fieldLabel: 'Implementasi Activity 1', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleImpl1Status', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleImpl1Status', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleImpl1Planned' ,format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Date Activity', name :'projectScheduleImpl1Date' ,format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleImpl1Revised' ,format : 'Y-m-d'},
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleImpl1Remarks' }
                    ] },
                { fieldLabel: 'Implementasi Activity 2', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleImpl2Status', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleImpl2Status', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleImpl2Planned' ,format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Date Activity', name :'projectScheduleImpl2Date' , format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleImpl2Revised' ,format : 'Y-m-d'},
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleImpl2Remarks' }
                    ] },
                { fieldLabel: 'SIT', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleSitStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleSitStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleSitPlanned',format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Date Activity', name :'projectScheduleSitDate' ,format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleSitRevised' ,format : 'Y-m-d'},
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleSitRemarks' }
                    ] },
                { fieldLabel: 'UAT', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleUatStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleUatStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleUatPlanned' ,format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Date Activity', name :'projectScheduleUatDate' ,format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleUatRevised',format : 'Y-m-d'},
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleUatRemarks' }
                    ] },
                { fieldLabel: 'Berita Acara', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleBaStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleBaStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleBaPlanned' ,format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Date Activity', name :'projectScheduleBaDate' , format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleBaRevised' ,format : 'Y-m-d'},
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleBaRemarks' }
                    ] },
                { fieldLabel: 'Documentation', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Done', name: 'projectScheduleDocStatus', checked : true, inputValue : 'Done' },
                        { boxLabel : 'Pending', name: 'projectScheduleDocStatus', inputValue : 'Pending' },
                        { xtype : 'datefield',emptyText:'Planned Schedule', name :'projectScheduleDocPlanned' , format : 'Y-m-d'},
                        { xtype : 'datefield', emptyText:'Date Activity', name :'projectScheduleDocDate' , format : 'Y-m-d'},
                        { xtype : 'datefield',emptyText:'Revised Schedule', name :'projectScheduleDocRevised' , format : 'Y-m-d'},
                        { xtype : 'textfield',emptyText:'Remarks', name :'projectScheduleDocRemarks' }
                    ] }

            ],
            reader : {
                id : 'myFormData',
                root : 'data',
                totalRecords : 'total',
                fields : [{ name: 'id', mapping: 'id'},
                    { name: 'projectScheduleKickOffDate', mapping: 'kickoff_date'},
                    { name: 'projectScheduleKickOffStatus', mapping: 'kickoff_status'},
                    { name: 'projectScheduleKickOffPlanned', mapping: 'kickoff_planned'},
                    { name: 'projectScheduleKickOffRemarks', mapping: 'kickoff_remarks'},
                    { name: 'projectScheduleKickOffRevised', mapping: 'kickoff_revised'},
                    { name: 'projectSchedulePengadaanDate', mapping: 'pengadaan_date'},
                    { name: 'projectSchedulePengadaanStatus', mapping: 'pengadaan_status'},
                    { name: 'projectSchedulePengadaanPlanned', mapping: 'pengadaan_planned'},
                    { name: 'projectSchedulePengadaanRemarks', mapping: 'pengadaan_remarks'},
                    { name: 'projectSchedulePengadaanRevised', mapping: 'pengadaan_revised'},
                    { name: 'projectScheduleDeliveryDate', mapping: 'delivery_date'},
                    { name: 'projectScheduleDeliveryPlanned', mapping: 'delivery_planned'},
                    { name: 'projectScheduleDeliveryRemarks', mapping: 'delivery_remarks'},
                    { name: 'projectScheduleDeliveryRevised', mapping: 'delivery_revised'},
                    { name: 'projectScheduleDeliveryStatus', mapping: 'delivery_status'},
                    { name: 'projectScheduleStagingDate', mapping: 'stagging_date'},
                    { name: 'projectScheduleStagingPlanned', mapping: 'stagging_planned'},
                    { name: 'projectScheduleStagingRemarks', mapping: 'stagging_remarks'},
                    { name: 'projectScheduleStagingRevised', mapping: 'stagging_revised'},
                    { name: 'projectScheduleStagingStatus', mapping: 'stagging_status'},
                    { name: 'projectScheduleImpl1Date', mapping: 'impl1_date'},
                    { name: 'projectScheduleImpl1Planned', mapping: 'impl1_planned'},
                    { name: 'projectScheduleImpl1Remarks', mapping: 'impl1_remarks'},
                    { name: 'projectScheduleImpl1Revised', mapping: 'impl1_revised'},
                    { name: 'projectScheduleImpl1Status', mapping: 'impl1_status'},
                    { name: 'projectScheduleImpl2Date', mapping: 'impl2_date'},
                    { name: 'projectScheduleImpl2Planned', mapping: 'impl2_planned'},
                    { name: 'projectScheduleImpl2Remarks', mapping: 'impl2_remarks'},
                    { name: 'projectScheduleImpl2Revised', mapping: 'impl2_revised'},
                    { name: 'projectScheduleImpl2Status', mapping: 'impl2_status'},
                    { name: 'projectScheduleSitDate', mapping: 'sit_date'},
                    { name: 'projectScheduleSitPlanned', mapping: 'sit_planned'},
                    { name: 'projectScheduleSitRemarks', mapping: 'sit_remarks'},
                    { name: 'projectScheduleSitRevised', mapping: 'sit_revised'},
                    { name: 'projectScheduleSitStatus', mapping: 'sit_status'},
                    { name: 'projectScheduleUatDate', mapping: 'uat_date'},
                    { name: 'projectScheduleUatPlanned', mapping: 'uat_planned'},
                    { name: 'projectScheduleUatRemarks', mapping: 'uat_remarks'},
                    { name: 'projectScheduleUatRevised', mapping: 'uat_revised'},
                    { name: 'projectScheduleUatStatus', mapping: 'uat_status'},
                    { name: 'projectScheduleUatDate', mapping: 'uat_date'},
                    { name: 'projectScheduleUatPlanned', mapping: 'uat_planned'},
                    { name: 'projectScheduleUatRemarks', mapping: 'uat_remarks'},
                    { name: 'projectScheduleUatRevised', mapping: 'uat_revised'},
                    { name: 'projectScheduleUatStatus', mapping: 'uat_status'},
                    { name: 'projectScheduleBaDate', mapping: 'ba_date'},
                    { name: 'projectScheduleBaPlanned', mapping: 'ba_planned'},
                    { name: 'projectScheduleBaRemarks', mapping: 'ba_remarks'},
                    { name: 'projectScheduleBaRevised', mapping: 'ba_revised'},
                    { name: 'projectScheduleBaStatus', mapping: 'ba_status'},
                    { name: 'projectScheduleDocDate', mapping: 'doc_date'},
                    { name: 'projectScheduleDocPlanned', mapping: 'doc_planned'},
                    { name: 'projectScheduleDocRemarks', mapping: 'doc_remarks'},
                    { name: 'projectScheduleDocRevised', mapping: 'doc_revised'},
                    { name: 'projectScheduleDocStatus', mapping: 'doc_status'}
                ]
            }
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
        var fpleg = formPanel(parameterFormLegal);
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
                                    fpleg.getForm().load({ method:'GET', url: '../projectlegal/add/' + id , waitMsg:'Please wait...'});
                                }},
                            items:[fpleg]
                        },
                        {title : 'Resource',listeners: {activate: function(){
                                    storeResources.load( { params : {  project_id : id } });
                            }},
                            items:[fpRes] },
                        {title : 'Schedule',listeners: {activate: function(){
                                    fpSch.getForm().load({ method:'GET', url: '../projectschedule/add/' + id , waitMsg:'Please wait...'});
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
                width : 850,
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
        Ext.TaskMgr.start({ run: function(){ store.reload(); }, interval: 60000 });
       //

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
