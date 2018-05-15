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
	
	private int no;
	private String subject;
	private String name;
	private String password;
	private String content;
	private String old_file;
	
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
		
		paramClass.setNo(getNo());
		paramClass.setSubject(getSubject());
		paramClass.setName(getName());
		paramClass.setPassword(getPassword());
		paramClass.setContent(getContent());
		
		sqlMapper.update("updateBoard",paramClass);
		
		if(getUpload() != null) {
			String file_name = "file_"+getNo();
			String file_ext = getUploadFileName().substring(getUploadFileName().lastIndexOf('.')+1,getUploadFileName().length());
			
			File deleteFile = new File(fileUploadPath+getOld_file());
			deleteFile.delete();
			
			File destFile = new File(getFileUploadPath()+file_name+"."+file_ext);
			FileUtils.copyFile(getUpload(), destFile);
			
			paramClass.setFile_orgname(getUploadFileName());
			paramClass.setFile_savname(file_name+"."+file_ext);
			
			sqlMapper.update("updateFile",paramClass);
		}
		
		resultClass = (boardVO)sqlMapper.queryForObject("selectOne",getNo());
		
		
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










































