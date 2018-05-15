package board;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.FileUtils;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class modifyAction extends ActionSupport{
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private boardVO paramClass;
	private boardVO resultClass;
	
	private int currentPage;
	
	//수정폼에서 입렫되는값들 
	private int no;
	private String subject;
	private String name;
	private String password;
	private String content;
	private String old_file;
	
	//파일업로드할떄 필요한 것들 
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String fileUploadPath = "C:\\java\\image\\";
	
	public modifyAction() throws IOException{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}

	@Override
	public String execute() throws Exception {	
		paramClass = new boardVO();
		resultClass = new boardVO();
		//수정폼에서 받아온 값들을 디비에 넣을 쿼리문을 실행하기위해 paramClass에 세팅한다.
		paramClass.setNo(getNo());
		paramClass.setSubject(getSubject());
		paramClass.setName(getName());
		paramClass.setPassword(getPassword());
		paramClass.setContent(getContent());
		
		sqlMapper.update("updateBoard",paramClass); //paramClass를 보내고 디비에 넣는다.
		
		if(getUpload() != null) {		//새로넣을 파일이 있으면 기존파일을 지우고 새로운 파일을 넣는다.
			String file_name = "file_"+getNo();
			String file_ext = getUploadFileName().substring(getUploadFileName().lastIndexOf('.')+1,getUploadFileName().length());
			
			File deleteFile = new File(fileUploadPath+getOld_file());	//기존경로에 저장되어있는 파일을 불러와서 
			deleteFile.delete();//지운다. 
			
			File destFile = new File(getFileUploadPath()+file_name+"."+file_ext);	//새로들어온 파일을 넣는다. 
			FileUtils.copyFile(getUpload(), destFile);
			
			paramClass.setFile_orgname(getUploadFileName());
			paramClass.setFile_savname(file_name+"."+file_ext);
			
			sqlMapper.update("updateFile",paramClass);		//수정시에는 기존 게시글번호가 저장되어 있으므로 안넣어줘도 된다. 
		}
		
		resultClass = (boardVO)sqlMapper.queryForObject("selectOne",getNo());		//jsp에서 가져다 쓰기위한 클래스 
		
		
		return SUCCESS;
	}

	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		modifyAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		modifyAction.sqlMapper = sqlMapper;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOld_file() {
		return old_file;
	}

	public void setOld_file(String old_file) {
		this.old_file = old_file;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}
	
	

}










































