package board;

import com.opensymphony.xwork2.ActionSupport;

public class pagingAction{
	private int currentPage;		//현재페이
	private int totalCount;		//전체 게시물 수 
	private int totalPage;		//전체 페이지 수 
	private int blockCount;		//한 페이지의 게시물의 수 
	private int blockPage;		//한 화면에 보여줄 페이지 수 
	private int startCount;		//한 페이지에서 보여줄 게시글의 시작 번호 ->서브쿼리를 사용하지 않기때문에 페이지에서 원하는 만큼만 가져오기위해사용한다.  
	private int endCount;		//한 페이지에서 보여줄 게시글의 끝 번호 
	private int startPage;		//시작 페이지 
	private int endPage;			//마지막 페이지 
	
	private StringBuffer pagingHtml;	//페이징을 하기위한 jsp문을 만들기 위해 
	
	public pagingAction(int currentPage, int totalCount, int blockCount, int blockPage) {	//생성자를 이용해 페이징에 필요한 변수들을 초기화한다. 
		
		this.currentPage = currentPage;	//2페이지  5
		this.totalCount = totalCount;	//17개 
		this.blockCount = blockCount;	//10개 
		this.blockPage = blockPage;		//5개 
		
		
		totalPage = (int)Math.ceil((double) totalCount/blockCount);		// 17/10 = 1.7 -->반올림해서 2페이지 
		if(totalPage == 0) {			
			totalPage = 1;
		}
		if(currentPage > totalPage) {	
			currentPage = totalPage;
		}
		
		startCount = (currentPage - 1)* blockCount; //(2 - 1)*10 = 10부터 
		endCount = startCount + blockCount -1;	//10+10-1 19까지      0부터 시작이라면 2페이의 내용은 10~19까지이다. 
		
		startPage = (int)((currentPage-1)/blockPage)*blockPage+1;	// ((2-1)/5)*5+1 = 1페이지 
		endPage = startPage+blockPage-1;							//1+5-1 = 5페이지 	
		
		if(endPage>totalPage) { 	
			endPage = totalPage;
		}
		
		pagingHtml = new StringBuffer();  
		if(currentPage > blockPage) {	// 6페이지면 startPage=6-1/5 * 5+1   1*5+1
			pagingHtml.append("<a href=listAction.action?currentPage="+(startPage-1)+">"); 	//현재페이지 5로되고 이부분 사라지고 스타트페이지1 엔드페이지5
			pagingHtml.append("[이전]");	//생김 
			pagingHtml.append("</a>");
		}
		pagingHtml.append("&nbsp;|&nbsp;");	// [이전]|1|2|3|4|5|[다음]
		
		for(int i=startPage;i<=endPage;i++) {	//1~5
			if(i>totalPage) {
				break;
			}
			if(i==currentPage) {	//현재페이지면 빨간색으로 보이게 하기 
				pagingHtml.append("&nbsp;<b> <font color='red'>");
				pagingHtml.append(i);
				pagingHtml.append("</font></b>");
			}else {		//현재페이지 아니면 눌렀을때 해당 페이지를 리스트액션으로 보내고 페이지를 jsp에 띄운다. 
				pagingHtml.append("&nbsp;<a href='listAction.action?currentPage=");
				pagingHtml.append(i);
				pagingHtml.append("'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}
			pagingHtml.append("&nbsp;");
		}
		pagingHtml.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
		
		if(totalPage - startPage >= blockPage) {	//  5페이지만 있으면 다음을 보여주면 안되고 6페이지까지 있을경우 다음을 눌렀을떄 6페이지부터 시작하니까 6-1 >= 5 인경우에 다음을 넣는다.
			pagingHtml.append("<a href=listAction.action?currentPage="+(endPage+1)+">");
			pagingHtml.append("[다음]");
			pagingHtml.append("</a>");
		}
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}
	
	
	
	
	
	

}
