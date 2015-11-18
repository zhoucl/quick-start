<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../common/btcss.jsp" %>
	
	<link href="${ctx}/static/lib/layout/1.4.0/css/layout-default-latest.css" rel="stylesheet">
	<title>基础模板</title>
</head>
<body>
	<div class="ui-layout-north">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><span style="font-weight:bolder;">基本模板</span>
                    </a>
                </div>
                <div class="collapse navbar-collapse" style="margin-right: 40px;">
                    <p class="navbar-text navbar-right" style="font-size:14px; ">
                    	欢迎您
                    </p>
                </div>
            </div>
        </nav>
    </div>
    <div class="ui-layout-west">
        <span>这是菜单</span>
    </div>

    <div class="ui-layout-center" style="top:60px;">
        <iframe name="main" frameborder="no" border="0"
                style="height: 100%; width: 100%; border: 0px solid"
                src="${ctx }/log/list"></iframe>
    </div>
	
	
	<%@ include file="../common/btjs.jsp" %>
	<script src="${ctx}/static/lib/layout/1.4.0/js/jquery.layout-latest.js"></script>
	
	<script>
		$(document).ready(function() {
			$('body').layout({
				applyDemoStyles : false,
				north__size: 54,
				north__closable:false,//可以被关闭  
			    north__resizable:false//可以改变大小
			});
		});
	</script>
</body>
</html>