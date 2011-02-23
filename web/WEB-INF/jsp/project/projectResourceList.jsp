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
                url: '../projectresourcename/json'
            }),
            autoLoad :true,
            remoteSort :true,
            baseParams : {
                start:0,
                limit:10
            },
            sortInfo: {
                field: 'id',
                direction: 'DESC'
            },
            // the return will be JSON, so lets set up a reader
            reader: new Ext.data.JsonReader({
                root: 'data',
                id: 'mydata',
                totalRecords: 'total',
                fields : [
                    {name: 'id', mapping: 'id'},
                    {name: 'name', mapping: 'name'}

                ]
            })
        });



         var columnModel = new Ext.grid.ColumnModel({
            columns : [selectionModel,
                {header: "Id", width: 120, dataIndex: 'id', hidden: true},
                {header: "Resource Name", width: 120, dataIndex: 'name', sortable: true}

            ]
        });





        var parameterFormPanel = {
            id : 'fp',
            url :'../projectresourcename/addProcess',
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
            items : [{ xtype:'hidden', fieldLabel: 'Id', name: 'id', inputValue :null },
                    { xtype:'textfield',fieldLabel: 'Name', name: 'name', allowBlank:false }

            ],
            reader : {
                id : 'myFormData',
                root : 'data',
                totalRecords : 'total',
                fields : [{ name: 'id', mapping: 'id'},
                          { name: 'name', mapping: 'name'}  ]
            }
        };


 



        var parameterGridPanel = {
            store : store,
            title : 'Project Resource',
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
                                method : 'GET',
                                url: '../projectresourcename/add/' + id,
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
                        //var ids = "";
                        var ids = [];
                        for(var i = 0;i<selection.length;i++)
                              ids[i] = selection[i].data.id;

                    Ext.Ajax.request({
                            url: '../projectresourcename/delete',
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
                            params: { id :  ids  } });
                    }
                }]

        };



        var gp = gridPanel(parameterGridPanel);
        var fp = formPanel(parameterFormPanel);




        /*Show windows*/

        function showWindow(){

            win.add(fp);
            win.setTitle('Add Project Resource Name');
            fp.getForm().reset();
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
