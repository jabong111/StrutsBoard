package board;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class replyAction extends ActionSupport{
	public static Reader reader;
	public static SqlMapClient sqlMapper;
	
	private int no;
	private int currentPage;
	private boolean reply;
	private String subject;
	private String name;
	private String password;
	private String content;
	private String file_orgname;
	private String fils_savname;
	private int ref;
	private int re_level;
	private int re_step;
	
	private boardVO paramClass;
	private boardVO resultClass;
	
	public replyAction() throws Exception{
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}
	/*
	 * 새글이면 ref=0,re_step=0,re_level=0 no=1;
	 * 	답글으면 ref=현재시퀀스값 즉 no와 같아야된다. re_step, re_level
	 * 
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}


	public String form() throws Exception{	//폼을 보여준다. no, currentPage가 넘어와서 저장된다 
		reply = true;
		paramClass = new boardVO();
		resultClass = new boardVO();
		
		paramClass.setNo(getNo());
		
		resultClass = (boardVO)sqlMapper.queryForObject("selectOne",paramClass);	//게시글 하나를 가져와서 답변을 붙이고 ref,re_step,re_level 조
		
		resultClass.setSubject("[답변]"+resultClass.getSubject());
		resultClass.setName("");
		resultClass.setContent("");
		resultClass.setPassword("");
		resultClass.setFile_orgname("");
		
		return SUCCESS;
	}
	public static Reader getReader() {
		return reader;
	}
	public static void setReader(Reader reader) {
		replyAction.reader = reader;
	}
	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}
	public static void setSqlMapper(SqlMapClient sqlMapper) {
		replyAction.sqlMapper = sqlMapper;
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
	public boolean isReply() {
		return reply;
	}
	public void setReply(boolean reply) {
		this.reply = reply;
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
	public String getFile_orgname() {
		return file_orgname;
	}
	public void setFile_orgname(String file_orgname) {
		this.file_orgname = file_orgname;
	}
	public String getFils_savname() {
		return fils_savname;
	}
	public void setFils_savname(String fils_savname) {
		this.fils_savname = fils_savname;
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
	
	
	
	

}

























