package board;

import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

//비밀번호 체크
//에러면 에러메세지	->checkError.jsp띄움
//성공이면 checkSucess.jsp -> deleteCommentAction으로 보내짐
public class checkCpasswordAction extends ActionSupport{	//디비에서 받아와서 
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private commentVO paramClass;
	private commentVO resultClass;
	
	private int no;
	private int currentPage;
	private int cno;
	private String cpassword;
	
	public checkCpasswordAction() throws Exception{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	
	@Override
	public String execute() throws Exception {	//디비에서 받아와서 cpassword와 값이 같은지 본다.
		
		paramClass = new commentVO();
		resultClass = new commentVO();
		
		paramClass.setNo(getNo());
		paramClass.setCpassword(getCpassword());
		paramClass.setCno(getCno());
		
		resultClass = (commentVO)sqlMapper.queryForObject("selectCpassword",paramClass);
		
		if(resultClass == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	public String form() throws Exception{
		return SUCCESS;
	}



	public static Reader getReader() {
		return reader;
	}



	public static void setReader(Reader reader) {
		checkCpasswordAction.reader = reader;
	}



	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}



	public static void setSqlMapper(SqlMapClient sqlMapper) {
		checkCpasswordAction.sqlMapper = sqlMapper;
	}



	public commentVO getParamClass() {
		return paramClass;
	}



	public void setParamClass(commentVO paramClass) {
		this.paramClass = paramClass;
	}



	public commentVO getResultClass() {
		return resultClass;
	}



	public void setResultClass(commentVO resultClass) {
		this.resultClass = resultClass;
	}



	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public int getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public String getCpassword() {
		return cpassword;
	}



	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}
	
	

}
