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
        // create the Data Store

        var store = new Ext.data.Store({
            proxy: new Ext.data.HttpProxy({
                method:'POST',
                url: '../project/json'
            }),
            autoLoad :true,
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
                { xtype: 'datefield', fieldLabel: 'Project Start Date', name: 'projectStartDate', allowBlank:false,editable :false, format : 'Y-m-d' },
                { xtype: 'datefield', fieldLabel: 'Project End Date', name: 'projectEndDate', allowBlank:false,editable :false, format : 'Y-m-d' },
                { fieldLabel: 'Financial', xtype : 'radiogroup',
                    items : [{ boxLabel : 'On Budget', name: 'projectFinancial', checked : true, inputValue : 'On Budget' },
                        { boxLabel : 'Potentially Over Budget', name: 'projectFinancial', inputValue : 'Potentially Over Budget' },
                        { boxLabel : 'Over Budget', name: 'projectFinancial', inputValue : 'Over Budget' }] },
                { fieldLabel: 'Schedule', xtype : 'radiogroup',
                    items : [{ boxLabel : 'On Schedule', name: 'projectSchedule', checked : true, inputValue : 'On Schedule' },
                        { boxLabel : 'Potentially Over Schedule', name: 'projectSchedule', inputValue : 'Potentially Over Schedule' },
                        { boxLabel : 'Over Schedule', name: 'projectSchedule', inputValue : 'Over Schedule' }
                    ] },
                { fieldLabel: 'Technical', xtype : 'radiogroup',
                    items : [{ boxLabel : 'No Problem', name: 'projectTechnical', checked : true, inputValue : 'No Problem' },
                        { boxLabel : 'Potentially Problem', name: 'projectTechnical', inputValue : 'Potentially Problem' },
                        { boxLabel : 'Problem', name: 'projectTechnical', inputValue : 'Problem' }] },
                { fieldLabel: 'Resource', xtype : 'radiogroup',
                    items : [{ boxLabel : 'Available', name: 'projectResource', checked : true, inputValue : 'Available' },
                        { boxLabel : 'Potentially Problem', name: 'projectResource', inputValue : 'Potentially Problem' },
                        { boxLabel : 'Not Available', name: 'projectResource', inputValue : 'Not Available' }] },
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
                    { name: 'projectManager', mapping: 'project_manager' }
                ]
            }
        };


        var parameterFormFinancial = {
            title: 'Financial',
            layout:'table',
            reader : {},
            url :'../projectfinancial/addProcess',
            defaults: {
                bodyStyle:'padding:10px 10px 10px 0px;'
            },
            layoutConfig: {
                columns: 6
            },
            buttons:[{
                    text : 'Save', handler:function(){
                        fpfin.getForm().submit({
                            params: { project_id: selectionModel.getSelected().data.id },
                            success:function(){},
                            waitMsg:'Please wait...'
                        });
                    }
                }],
            items: null //let it blank
        };



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
                } ]

        };



        var gp = gridPanel(parameterGridPanel);
        var fp = formPanel(parameterFormPanel);




        /*Show windows*/

        function showWindow(){

            win.add(fp);
            win.setTitle('Add Project');
            fp.getForm().reset();
            win.setWidth(700);
            win.doLayout(true);
            win.show();
        }

        


        var win = new Ext.Window({
            closeAction:'hide',
            closable:true,
            modal: true,
            width : 600,
            plain:true
        });

    });


</script>

<div id="grid"></div>
