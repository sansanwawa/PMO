 


function formPanel(parameter){

    
        return new Ext.form.FormPanel({
            layout: parameter.layout || 'form',
            labelAlign: parameter.labelAlign || 'left',
            id: parameter.id,
            frame: parameter.frame || true,
            width : parameter.width || '',
            title: parameter.title || null,
            defaults : parameter.defaults || {},
            layoutConfig:parameter.layoutConfig || {},
            //defaultType: 'textfield',
            reader: new Ext.data.JsonReader({
                root: parameter.reader.root,
                id: parameter.reader.id,
                totalRecords: parameter.reader.totalRecords,
                fields : parameter.reader.fields
            }) || {},
            url: parameter.url,
            items: parameter.items,
            buttons:parameter.buttons
        });
    }

function gridPanel(parameterGridPanel){

   return new Ext.grid.GridPanel({
            loadMask:true,
            store: parameterGridPanel.store,
            title :parameterGridPanel.title,
            renderTo:parameterGridPanel.renderTo,
            height : parameterGridPanel.height,
            //autoHeight : true,
            iconCls: parameterGridPanel.iconCls,
            sm: parameterGridPanel.sm,
            colModel : parameterGridPanel.colModel,
            tbar:  parameterGridPanel.tbar,
            bbar: new Ext.PagingToolbar({
                pageSize: parameterGridPanel.bbar.pageSize,
                store: parameterGridPanel.bbar.store,
                displayInfo: parameterGridPanel.bbar.displayInfo,
                displayMsg:  parameterGridPanel.bbar.displayMsg,
                emptyMsg: parameterGridPanel.bbar.emptyMsg
            })

        });
}



  