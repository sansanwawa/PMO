<%-- 
    Document   : userLogin
    Created on : Oct 22, 2010, 9:36:53 AM
    Author     : sandy
--%>

<%--@
include file="/WEB-INF/jsp/main/header.jsp"
--%>
<script type="text/javascript">
    /*
    Ext.onReady(function(){
        
        Ext.QuickTips.init();
        var parameterFormPanel = {
            id : 'fpLogin',
            title:'Login',
            width : 300,
            url :'../j_spring_security_check',
            buttons: [{
                    text: 'Save',
                    handler : function(a){
                          document.getElementsByTagName("form")[0].action = "../j_spring_security_check";
                          document.getElementsByTagName("form")[0].submit();
                        }
                }],
            items : [
                { xtype:'textfield',fieldLabel: 'Username', name: 'j_username', allowBlank:false },
                { xtype:'textfield',fieldLabel: 'Password', name: 'j_password', allowBlank:false }
                ],
            reader :  {}
        };
        var fp = formPanel(parameterFormPanel);
        fp.render('loginForm');
    });
     */
</script>

<style>
    /* Login Form */
    th,tr{
        font-size: 13px;
    }
    h1{
        font-size: 15px;
    }
    *{
        font-family: sans-serif;
    }
    #formLogin {

    }
    input[type=submit],input[type=reset]{
        background: #C2D6F0;
        background: -webkit-gradient(linear, left top, left bottom, from(rgba(255,255,255,0.4)), to(rgba(107,143,191,0.4)));
        background: -moz-linear-gradient(top, rgba(255,255,255,0.4), rgba(107,143,191,0.4));
        float: left;
        padding: 4px;
        text-decoration: none;
        outline: 0;
        border-width: 1px;
        border-style: solid;
        width: auto;
        overflow: visible;
        /*
        border-color: transparent transparent #666 transparent;
        border-color: transparent transparent rgba(202,202,202,0.27) transparent;      
        */
        -webkit-background-clip: padding-box;
        margin-right: 2px;
    }
</style>


<!--<div id="loginForm"></div>-->

<h1>Login</h1>
<form id="formLogin" action='../j_spring_security_check' method='POST'>
    <table>
        <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
        <tr><td colspan='2'><input name="submit" type="submit" value="Login"/>
                <input name="reset" type="reset"/></td></tr>
    </table>
</form>
<%--@ include file="/WEB-INF/jsp/main/footer.jsp"--%>