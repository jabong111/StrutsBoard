<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function view(){
		location.href='viewAction.action?no=<s:property value="no"/>&currentPage=<s:property value="currentPage"/>'
	}


</script>
</head>
<body>
	<script type="text/javascript">
		view();
	</script>
</body>
</html>