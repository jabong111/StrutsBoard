package board;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class deleteAction extends ActionSupport{
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private boardVO paramClass;
	private boardVO resultClass;
	
	//폼에서 받아온 데이터들 
	private int currentPage;
	private String fileUploadPath = "C:\\java\\image\\";
	private int no;
	
	public deleteAction() throws IOException{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}

	@Override
	public String execute() throws Exception {
		
		paramClass = new boardVO();
		resultClass = new boardVO();
		
		resultClass = (boardVO)sqlMapper.queryForObject("selectOne",getNo());		//디비에서 받아온다. 
		
		File deleteFile = new File(fileUploadPath+resultClass.getFile_savname());	//파일을 지운다.
		deleteFile.delete();
		
		paramClass.setNo(getNo());	//deleteBoard에서 자바빈을 인자로 받기때문에 paramClass자바빈을 만들어준다.  
		
		sqlMapper.update("deleteBoard",paramClass);
		return SUCCESS;
	}

	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		deleteAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		deleteAction.sqlMapper = sqlMapper;
	}

	public boardVO getParamClass() {
		return paramClass;
	}

	public void setParamClass(boardVO paramClass) {
		this.paramClass = paramClass;
	}

	public boardVO getResultClass() {
		return resultClass;
	}

	public void setResultClass(boardVO resultClass) {
		this.resultClass = resultClass;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	
	

}
