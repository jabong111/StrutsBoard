<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ���</title>
<link rel="stylesheet" href="/board/common/css/css.css" type="test/css">
<script type="text/javascript">
	function open_win_noresizable(url,name){
		var oWin = window.open(url,name,"scrollbars=no,status=no,resizable=no,width=300,height=150");
	}
	
</script>
</head>
			
<body>
	<table width="600" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td align="center"><h2>��Ʈ����2 �Խ���</h2></td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
	</table>
	
	<table width="600" border="0" cellpadding="0" cellspacing="0">
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4">��ȣ</td>
			<td bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.no"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td width="100" bgcolor="#F4F4F4">����</td>
			<td width="500" bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.subject"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		<tr>
			<td bgcolor="#F4F4F4">�۾���</td>
			<td bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.name"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4">����</td>
			<td bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.content"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4">��ȸ��</td>
			<td bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.readhit"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4">��ϳ�¥</td>
			<td bgcolor="#FFFFFF">
				&nbsp;&nbsp;<s:property value="resultClass.regdate"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4">÷������</td>
			<td bgcolor="#FFFFFF">
			&nbsp;&nbsp;
			<s:url id="download" action="fileDownloadAction">
				<s:param name="no">
					<s:property value="no"/>
				</s:param>
			</s:url>
			<s:a href="%{download}"><s:property value="resultClass.file_orgname"/></s:a>
			<!-- download = https://localhost:8080/StrutsBoard/fileDownloadActon.action?no=no  url����-->
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
		
		<tr>
			<td align="right" colspan="2">
				<s:url id="modifyURL" action="modifyForm">
					<s:param name="no">
						<s:property value="no"/>
					</s:param>
				</s:url>
				
				<s:url id="deleteURL" action="deleteAction">
					<s:param name="no">
						<s:property value="no"/>
					</s:param>
				</s:url>
				
				<input name="list" type="button" value="���" class="inputb" onclick="javascript:location.href='replyForm.action?no=<s:property value="no" />&currentPage=<s:property value="currentPage" />'">
				
				<input name="list" type="button" value="����" class="inputb" onclick="javascript:open_win_noresizable('checkForm.action?no=<s:property value="resultClass.no"/>&currentPage=<s:property value="currentPage"/>','modify')">
				<input name="list" type="button" value="����" class="inputb" onclick="javascript:open_win_noresizable('checkForm.action?no=<s:property value="resultClass.no"/>&currentPage=<s:property value="currentPage"/>','delete')">
				
				<input name="list" type="button" value="���" class="inputb" onclick="javascript:location.href='listAction.action?currentPage=<s:property value="currentPage"/>'">
			</td>
		</tr>
	</table>
	
	<form action="commentAction.action" method="post">
		<table width="600" border="1" cellpadding="2" cellspacing="0">
			<tr>
				<td colspan="4">�ۼ���:<input type="text" name="cname">
				��й�ȣ:<input type="password" name="cpassword"></td>
				<input type="hidden" name="no" value="<s:property value="resultClass.no"/>">
				<input type="hidden" name="currentPage" value="<s:property value="currentPage"/>">
			</tr>
			<tr>
				<td colspan="3"><textarea cols="50" rows="10" name="ccontent"></textarea></td>
				<td><input type="submit" value="����"></td>	
			</tr>
		</table>
	</form>
	<s:if test="commentList.size() == 0">
		<table>
			<tr>
				<td align="center">����� �����ϴ�.</td>
			</tr>
		</table>
	</s:if>
	<s:else>
		<s:iterator value="commentList" status="stat">
		<table border="1">
			<tr>
				<td colspan="5">��ȣ:<s:property value="cno"/>&nbsp;&nbsp;�ۼ���:<s:property value="cname"/>&nbsp;&nbsp;&nbsp;&nbsp;��¥:<s:property value="cdate"/><td>
			</tr>
			<tr>
				<td colspan="4"><s:property value="ccontent"/></td>
				<td><input type="button" value="����" onclick="javascript:open_win_noresizable('checkCPasswordForm.action?no=<s:property value="resultClass.no"/>&currentPage=<s:property value="currentPage"/>&cno=<s:property value="cno"/>','deleteComment')"></td>
		</table>
		</s:iterator>
	</s:else>
	
</body>
</html>
































