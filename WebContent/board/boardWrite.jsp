<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판</title>
<link rel="stylesheet" href="/board/common/css/css.css" type="text/css">
<script type="text/javascript">
	function validation(){ 
		var frm = document.forms(0);
		
		if(frm.subject.value==""){
			alert("제목을 입력해주세요");
			return false;
		}
		
		else if(frm.name.value == ""){
			alert("이름을 입력해주세요");
			return false;
		}
		else if(frm.password.value ==""){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		else if(frm.content.value ==""){
			alert("내용을 입력해주세여");
			return false;
		}
		return true;
	}				
</script>
</head>
<body>
	<table width="600" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td align="center"><h2>스트럿츠 게시판</h2></td>
		</tr>
	</table>
	<s:if test="reply">	<!-- reply action에서 트루로 바꿔준다. -->
		<form action="replyAction.action" method="post">
	</s:if>
	
	<s:elseif test="resultClass == NULL">	
		<form action="writeAction.action" method="post" enctype="multipart/form-data" onsubmit="return validation();">
	</s:elseif>
	
	<s:else>		<!-- 수정폼을 누르면 디비에서 가져오는 값이 있으므로 resultClass는 널이 아니게 된다. -->
		<form action="modifyAction.action" method="post" enctype="multipart/form-data">
		<s:hidden name="no" value="%{resultClass.no}"/>
		<s:hidden name="currentPage" value="%{currentPage}"/>
		<s:hidden name="old_file" value="%{resultClass.file_savname}"/>		<!-- 저장된 파일이름을 새로운파일로 업르드하려면 지워야되기 때문에 old_file로 보낸다. -->
	</s:else>
	
	<!-- 수정폼을 공유하기 때문에 value를 사용해 기존데이터들을 띄운다. -->
	<table width="600" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right" colspan="2"><font color="#FF0000">*</font>는 필수 입력사항입니다.</td>
		</tr>
		
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td width="100" bgcolor="#F4F4F4"><font color="#FF0000">*</font>제목</td>
			<td width="500" bgcolor="#FFFFFF">
				<s:textfield name="subject" theme="simple" value="%{resultClass.subject}" cssStyle="width:370px" maxlength="50"/>
			</td>
		</tr>
		
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4"><font color="#FF0000">*</font>이름</td>
			<td bgcolor="#FFFFFF">
				<s:textfield name="name" theme="simple" value="%{resultClass.name}" cssStyle="width:100px" maxlength="20"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4"><font color="#FF0000">*</font>비밀번호</td>
			<td bgcolor="#FFFFFF">
            <s:textfield name="password" theme="simple" value="%{resultClass.password}" cssStyle="width:100px" maxlength="20"/>
          	</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4"><font color="#FF0000">*</font>내용</td>
			<td bgcolor="#FFFFFF">
				<s:textarea name="content" theme="simple" value="%{resultClass.content}" cols="50" rows="10"/>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
			<td bgcolor="#F4F4F4">첨부파일</td>
			<td bgcolor="#FFFFFF">
				<s:file name="upload" theme="simple"/>
				<s:if test="resultClass.file_orgname != NULL">
				&nbsp;*<s:property value="resultClass.file_orgname"/>파일이 등록되어 있습니다. 다시 업로드하면 기존의 파일은 삭제됩니다.
				</s:if>
				</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="2"></td>
		</tr>
		
		<tr>
		 	<td headers="10" colspan="2"></td>
		</tr>
		
		<tr>
			<td align="right" colspan="2">
				<input name="submit" type="submit" value="작성완료" class="inputb">	<!-- 완료하면 writeAction 으로 가서 처리하고  처리 다하고 listAction으로  리다이렉트-->
				<input name="list" type="button" value="목록" class="inputb" onclick="javascript:location.href='listAction.action?currentPage=<s:property value="currentPage"/>'">
			</td>
		</tr>
	
	</table>
	</form>
</body>
</html>












































