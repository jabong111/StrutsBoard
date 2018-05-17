package board;

import java.util.*;
import java.io.Reader;
import java.io.IOException;

import java.io.File;
import org.apache.commons.io.FileUtils;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class writeAction extends ActionSupport{	//폼에서 얻은 데이터를 디비에 넣는 액션클래스 
	
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private boardVO paramClass;	//디비로 보내기위한 폼에서 얻은 데이터를 자바빈에 넣고 디비로 보낸다.
	private boardVO resultClass;	//디비에서 얻어온 자바빈 
	
	private int currentPage;	// 현재페이지 
	
	//폼에서 적은걸 받는 변수 이걸 paramClass 자바빈에 넣는다.
	private int no;		//게시글 번호 
	private String subject;	//제목 
	private String name;		//이름 
	private String password;	//비밀번호 
	private String content;	//내용 
	private String file_orgName;	//오리지날 파일 이름 
	private String file_savName;	//처리를해서 저장하는 이름 
	
	private int ref;	//글의 시퀀스값이랑 같에 글과 답변을 묶어준다. 그룹화
	private int re_level;	// 답글인지 답글의 답글인지 
	private int re_step;	// 보여주는 차례 답글 쓸때마다 1씩 증가시켜서 큰 값이 위로 올라오게 한다.
	
	Calendar today = Calendar.getInstance();	//데이트 저장 
	
	//파일 업로드 하기위한 파일객체랑 컨텐트타입, 컴퓨터에있는파일이름 
	private File upload;
	private String uploadContentType;
	private String uploadFileName;	//업로드할때 실제 이름
	private String fileUploadPath = "C:\\java\\image\\";
	
	public writeAction() throws IOException{		//boardSQL.xml내용을 사용하기위해 넣는다. 
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");	//디비에 연결
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	
	public String form() throws Exception{	//폼보여주기위한 메소드 바로 success를 리턴 
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		
		paramClass = new boardVO();		//폼에서 얻은 데이터를 자바빈에 넣고 이를 디비에 넣기위해서 파라미터값으로 보내주기위한 자바빈 
		resultClass = new boardVO();		//수정폼에서 데이터를 보여주기위한 디비에서 가져온값을 저장해둔 자바빈 
		
		paramClass.setSubject(getSubject());  	
		paramClass.setName(getName());
		paramClass.setPassword(getPassword());
		paramClass.setContent(getContent());
		paramClass.setRegdate(today.getTime());	
		paramClass.setRef(getRef());
		paramClass.setRe_level(getRe_level());
		paramClass.setRe_step(getRe_step());
		
		sqlMapper.insert("insertBoard",paramClass);	//게시글 에 필요한 데이터먼저 디비에 넣고 파일처리를 한다. 
		
		if(getUpload() != null) {	//업로드 파일이 존재하면 
			resultClass = (boardVO)sqlMapper.queryForObject("selectLastNo");  //최근에 넣은 게시글을 가져와서 
			
			String file_name = "file_"+resultClass.getNo();		//file_9 이런식으로 이름을 정한다. 숫자는 게시글 번호다 
			String file_ext = getUploadFileName().substring(getUploadFileName().lastIndexOf('.')+1, 
					getUploadFileName().length());//업로드 할때 사용했던 이름을 가져와서  확장자명이 시작하는 . 다음부터 끝까지를 잘라서 저장한다. 즉 확장자명을 가져온다. 
			
			File destFile = new File(fileUploadPath+file_name+"."+file_ext);	// C:\java\image\file_9.jpg 이런 파일을 만들고 
			FileUtils.copyFile(getUpload(), destFile);		//업로드한 파일을 설정한 경로에 카피한다. 
			
			paramClass.setNo(resultClass.getNo());			
			paramClass.setFile_orgname(getUploadFileName());	//실제 이름
			paramClass.setFile_savname(file_name+"."+file_ext);		//updateFile 쿼리문에는 게시글번호까지 들어가야된다.
			
			sqlMapper.update("updateFile",paramClass);
		}
		
		return SUCCESS;
	}

	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		writeAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		writeAction.sqlMapper = sqlMapper;
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

	public String getFile_orgName() {
		return file_orgName;
	}

	public void setFile_orgName(String file_orgName) {
		this.file_orgName = file_orgName;
	}

	public String getFile_savName() {
		return file_savName;
	}

	public void setFile_savName(String file_savName) {
		this.file_savName = file_savName;
	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
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

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	
	
	
	
	

}






































