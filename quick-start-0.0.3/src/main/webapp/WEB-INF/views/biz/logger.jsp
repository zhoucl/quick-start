<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../common/btcss.jsp" %>
	
	<title>资源白名单配置管理系统</title>
</head>
<body>
	<ol class="breadcrumb">
        <li><a href="#">首页</a></li>
        <li><a href="#">IPCC设置管理</a></li>
        <li><a href="javascript:void(0);" style="text-decoration: none;font-color:gray;">日志等级设置</a></li>
    </ol>
	
	<form id="logform" action="#">
		<div class="container-fluid">
		  	<table class="table table-bordered">
		  		<tr>
		  			<td width="140px;">
						LoggerName：
	        		</td>
	        		<td>
	        			<select name="loggerName">
	        				<c:forEach items="${packages}" var="pk">
								<option value="${pk}">${pk}</option>
							</c:forEach>
	        			</select>
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>
						LoggerLevel：
	        		</td>
	        		<td>
	        			<select name="level">  
						    <option value="TRACE">TRACE</option>  
						    <option value="DEBUG">DEBUG</option>  
						    <option value="INFO">INFO</option>  
						    <option value="WARN">WARN</option>  
						    <option value="ERROR">ERROR</option>  
						    <option value="FATAL">FATAL</option>  
						    <option value="ALL">ALL</option>  
						    <option value="OFF">OFF</option>
						</select> 
	        		</td>
	        	</tr>
		  	</table>
		</div>
	    <div class="container">
	    	<button id="cancel" type="button" class="btn btn-warning">取消</button>
	    	<button id="submit" type="button" class="btn btn-success">提交</button>
	    </div>
	    
	    <input type="hidden" id="time" name="time"  value=""/>
    </form>
	
	<%@ include file="../common/btjs.jsp" %>
	<script type="text/javascript">
		$(function(){
			$('#submit').click(function(){submit();})
		});
	
		function submit() {
			$.ajax({
	            cache: true,
	            type: "POST",
	            url: "${ctx}/log/rest/chgp",
	            data:$('#logform').serialize(),// 你的formid
	            async: false,
	            error: function(request) {
	                alert("Connection error");
	            },
	            success: function(data) {
	                alert(data);
	            }
	        });
		}
	</script>
</body>
</html>