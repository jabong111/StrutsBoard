<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>비밀번호 확인</title>
<link rel="stylesheet" href="/board/common/css/css.css" type="text/css">
<script type="text/javascript">
	function locationURL(){
	if ( window.name == 'modify' ){ 
		window.opener.parent.location.href='modifyForm.action?no=<s:property value="no" />&currentPage=<s:property value="currentPage" />';
	}else if(window.name == 'delete'){
		alert("삭제되었씁니다.");
		window.opener.parent.location.href='deleteAction.action?no=<s:property value="no"/>&currentPage=<s:property value="currentPage"/>';
	}else if(window.name == 'deleteComment'){
		alert("삭제되었습습니다.");
		window.opener.parent.location.href='deleteCommentAction.action?no=<s:property value="no"/>&currentPage=<s:property value="currentPage"/>&cno=<s:property value="cno"/>';
	}
	
	window.close;
}

</script>
</head>
<body>
	<script type="text/javascript">
	locationURL();
	</script>

</body>
</html>