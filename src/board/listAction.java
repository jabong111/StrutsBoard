package board;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import board.pagingAction;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class listAction extends ActionSupport{
	public static Reader reader;
	public static SqlMapClient sqlMapper;	//디비와 쿼리문을 연결하기위한 객체들 
		//컨트롤러에서 무슨액션을 실행할지 정해서 ->액션실행->디비작업후 포워드,리다이렉트시킨다->해당하는 jsp실행 
		//컨트롤러는 FileDispatcher가 대신한다.
	
	private List<boardVO> list = new ArrayList<boardVO>();	//게시글 여러개를 가져오기 위해서 리스트를 작
	
	private int currentPage = 1;	//현재페이지 초기화
	private int totalCount;	//게시글 총 몇개 있는지 
	private int blockCount=10; // 
	private int blockPage=5;	  //
	private String pagingHtml; //페이징액션에서 만든 이전, 다음 페이지 문정 
	private pagingAction page;	//페이지액션 객
	
	public listAction() throws IOException{	//생성자는 액션이 실행될때 실행된다.
		
		reader = Resources.getResourceAsReader("sqlMapConfig.xml");
		sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		reader.close();
	}

	@Override
	public String execute() throws Exception {
		
		list = sqlMapper.queryForList("selectAll");	//boardSQL.xml에 정의된 쿼리문을 실행하기위해서 게시 전부를 가져온다.
		totalCount = list.size();	//디비에서 가져온 게시글의 갯
		
		page = new pagingAction(currentPage, totalCount, blockCount, blockPage);	//페이징액션객체생
		pagingHtml = page.getPagingHtml().toString();	 //페이징액션에서 처리한 pagingHtml을 스트링버퍼->문자열로 만든다.
		
		int lastCount = totalCount;	
		
		if(page.getEndCount() < totalCount){		
			lastCount = page.getEndCount()+1;	
		}
		
		list = list.subList(page.getStartCount(), lastCount);	
		
		return SUCCESS;
	}





	public static Reader getReader() {
		return reader;
	}

	public static void setReader(Reader reader) {
		listAction.reader = reader;
	}

	public static SqlMapClient getSqlMapper() {
		return sqlMapper;
	}

	public static void setSqlMapper(SqlMapClient sqlMapper) {
		listAction.sqlMapper = sqlMapper;
	}

	public List<boardVO> getList() {
		return list;
	}

	public void setList(List<boardVO> list) {
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public String getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public pagingAction getPage() {
		return page;
	}

	public void setPage(pagingAction page) {
		this.page = page;
	}
	
	

}
















































