<%@ include file="/WEB-INF/jsp/main/header.jsp" %>

<script type="text/javascript">
    Ext.onReady(function(){
        //Ext.MessageBox.alert('Status', 'Hello World!!');

        var tabPanel =  new Ext.TabPanel({
            region: 'center',
            id : 'center-id',
            deferredRender: false,
            activeTab: 0,     // first tab initially active
            items: [{
                    contentEl: 'centerEl',
                    title: 'Center Panel',
                    autoScroll: true
                }]
        });


        var treeNode = new Ext.tree.AsyncTreeNode({
            expanded: true,
            allowChildren :true,
            children: [{
                    text: 'Project',

                    url: '../project/list',
                    leaf: true,
                    children: [{
                            text: 'Project',
                            url: '../project/list' ,
                            leaf: true
                        }]
                },{
                    text: 'User',
                    url: '../user/list' ,
                    leaf: true
                }
                ,{
                    text: 'Logout',
                    href: '../j_spring_security_logout' ,
                    leaf: true
                }


            ]
        });

        var treePanel =  new Ext.tree.TreePanel({
            title:'Menu',
            iconCls: 'settings',
            root: treeNode
        });

        treePanel.on('click', function(n){
                    if(n.attributes.url==null) return false;
                    Ext.get("loadCenterContent").load({
                        url: n.attributes.url,
                        scripts: true,
                        text: "Loading Content..."
                    });
        });


        var viewport = new Ext.Viewport({
            layout: 'border',
            items: [
                // create instance immediately
                new Ext.BoxComponent({
                    region: 'north',
                    height: 50, // give north and south regions a height
                    autoEl: {
                        tag: 'div',
                        html:'<p>PT. Asaba Computer Centre - PMO</p>'
                    }
                }), {
                    region: 'west',
                    id: 'west-panel',
                    title: 'West',
                    split: true,
                    width: 200,
                    minSize: 175,
                    maxSize: 400,
                    collapsible: true,
                    margins: '0 0 0 5',
                    layout: {
                        type: 'accordion',
                        animate: true
                    },
                    items: [
                        treePanel,
                        {
                            title: 'Settings',
                            html: '<p>Some settings in here.</p>',
                            border: false,
                            iconCls: 'settings'
                        }]
                }
                ,tabPanel
            ]
        });


    });
</script>




<div id="centerEl" class="x-hide-display">
    <div id="loadCenterContent">
        
    </div>
    
</div>


<%@ include file="/WEB-INF/jsp/main/footer.jsp" %>