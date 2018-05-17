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
	private String subject;
	private String name;
	private String password;
	private String content;
	private String file_orgname;
	private String fils_savname;
	private int ref;
	private int re_level;
	private int re_step;
	
	
	
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


	public String form() throws Exception{	//폼을 보여준다.
		return SUCCESS;
	}
	

}
