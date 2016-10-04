<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<%@include file="common/head.jsp"%>
<%@include file="common/easyui.jsp"%>
</head>
<body>
	<div class="easyui-panel" title="Ajax Form" style="width:300px;padding:10px;">
    <form id="ff" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input name="userName" class="f1 easyui-textbox"></input></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'" /></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input name="telphone" class="f1 easyui-textbox"></input></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Submit"></input></td>
            </tr>
        </table>
    </form>
</div>
<style scoped>
		.f1{
			width:200px;
		}
	</style>

<script type="text/javascript">
	$('#ff').form({
		url: '/user/addUser',
	    success:function(data){
	        $.messager.alert('Info', data, 'info');
	    }
	});
	
	//$('#ff').submit();
    </script>
</body>
</html>