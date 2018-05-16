package board;

import java.io.Reader;
import java.util.Calendar;
import java.util.Date;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class commentAction extends ActionSupport{
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	//폼에서 받아오는거 
	private int currentPage;
	private int no;
	private int cno;
	private String cname;
	private String cpassword;
	private String ccontent;
	Calendar today = Calendar.getInstance();
	
	//디비에 넣고 뺄때 사용 
	private commentVO paramClass;
	private commentVO resultClass;
	
	public commentAction() throws Exception{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	//체인으로 commentViewAction 을 실행해서 리설트를 가져오고 이는 다시 체인으로 viewAction을 실행 
	//디비에 넣어준다. 
	@Override
	public String execute() throws Exception {
		paramClass = new commentVO();
		resultClass = new commentVO();
		
		paramClass.setNo(getNo());//게시글 번호를 저장 
		//paramClass.setCno(getCno());//코멘트 번호 
		paramClass.setCname(getCname());
		paramClass.setCpassword(getCpassword());
		paramClass.setCcontent(getCcontent());
		paramClass.setCdate(today.getTime());
			
		sqlMapper.insert("insertComment",paramClass);
		
		return SUCCESS;
	}

	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		commentAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		commentAction.sqlMapper = sqlMapper;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
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
	
	
	

}
