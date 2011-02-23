 


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


//extend method
Ext.apply(Ext.form.VTypes,{
    phoneText: "Not a valid phone number.  Must be in the format 123-4567 or 123-456-7890.",
    phoneMask: /[\d-]/,
    phoneRe: /^(\d{3}[-]?){1,2}(\d{4})$/,
    phone : function (v) {
        return this.phoneRe.test(v);
    },
    numericText: "Only numbers are allowed.",
    numericMask: /[0-9]/,
    numericRe: /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/,
    numeric :function (v) {
        return this.numericRe.test(v);
    },
    decNumText: "Only decimal numbers are allowed.",
    decNumMask: /(\d|\.)/,
    decNumRe: /\d+\.\d+|\d+/,
    decNum : function (v) {
        return this.decNumRe.test(v);
    }
});  

//Item deleter
//plase refer to : http://www.sencha.com/forum/showthread.php?29961-1.0-Grid-RowActions-Plugin






