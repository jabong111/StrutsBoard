package board;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class deleteCommentAction extends ActionSupport{
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private int no;
	private int currentPage;
	private int cno;
	
	private commentVO paramClass;

	
	public deleteCommentAction() throws Exception{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	
	@Override
	public String execute() throws Exception {
		
		paramClass = new commentVO();
		
		paramClass.setNo(getNo());
		paramClass.setCno(getCno());
		
		sqlMapper.delete("deleteComment",paramClass);
		
		return SUCCESS;
	}
	
	
	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		deleteCommentAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		deleteCommentAction.sqlMapper = sqlMapper;
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

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public commentVO getParamClass() {
		return paramClass;
	}

	public void setParamClass(commentVO paramClass) {
		this.paramClass = paramClass;
	}
	
	

}
