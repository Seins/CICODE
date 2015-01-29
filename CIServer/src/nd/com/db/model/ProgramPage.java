package nd.com.db.model;

public class ProgramPage {
	int pageId;
	int endPage;
	int programId;
	int depth;

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "pageId:" + pageId + " endPage:" + endPage + " programId:"
				+ programId + " depth:" + depth;
		return str;
	}

}
