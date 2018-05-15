package board;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URLEncoder;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class viewAction extends ActionSupport{		//게시글 상세보기 
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private boardVO paramClass = new boardVO();		
	private boardVO resultClass = new boardVO();
	
	private int currentPage;
	
	private int no;
	private String password;
	
	private String fileUploadPath = "C:\\java\\image\\";
	
	//다운로드시 필요한 변수와 객체
	private InputStream inputStream; //struts.xml 에서 <param name="inputName"> 에 들어가야되고 
	private String contentDisposition;	//<param name="contentDisposition">"attachment; filename="+URLEncoder.encode()+</param>
	private long contenLength;			//<param name="contentLength">${}</param> 
										//<param name="bufferSize">
										//<param name="contentType>binary/octet-stream</param>
	public viewAction() throws IOException{	//create constructor
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}

	@Override
	public String execute() throws Exception { 	//읽으러 들어오면 
		
		paramClass.setNo(getNo());
		sqlMapper.update("updateReadHit",paramClass);	//조회수 올려준다. 자동으로 
		
		resultClass = (boardVO)sqlMapper.queryForObject("selectOne",getNo()); //상세보기 페이지에서 보여줄 내용들을 디비에서 가져와 담고있는 자바빈 jsp에서 사용한다.
		
		return SUCCESS;
	}
	
	public String download() throws Exception{
		
		resultClass = (boardVO)sqlMapper.queryForObject("selectOne",getNo());
		File fileInfo = new File(fileUploadPath+resultClass.getFile_savname());
		
		setContenLength(fileInfo.length());
		setContentDisposition("attachment; filename="+URLEncoder.encode(resultClass.getFile_orgname(),"UTF-8"));
		setInputStream(new FileInputStream(fileUploadPath+resultClass.getFile_savname()));
		
		return SUCCESS;
	}
	
	public String checkForm() throws Exception{
		return SUCCESS;
	}
	
	public String checkAction() throws Exception{
		
		paramClass.setNo(getNo());
		paramClass.setPassword(getPassword());
		
		resultClass = (boardVO)sqlMapper.queryForObject("selectPassword",paramClass);
		if(resultClass == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		viewAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		viewAction.sqlMapper = sqlMapper;
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

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public long getContenLength() {
		return contenLength;
	}

	public void setContenLength(long contenLength) {
		this.contenLength = contenLength;
	}
	

}

































