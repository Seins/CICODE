package nd.com.db.model;

public class ElementOperational {
	int elementId;
	int operationalId;
	int resultId;
	int targetPageId;

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public int getOperationalId() {
		return operationalId;
	}

	public void setOperationalId(int operationalId) {
		this.operationalId = operationalId;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getTargetPageId() {
		return targetPageId;
	}

	public void setTargetPageId(int targetPageId) {
		this.targetPageId = targetPageId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "elementId:" + elementId + "operationalId:"
				+ operationalId + "resultId:" + resultId + "targetPageId:"
				+ targetPageId;
		return str;
	}
}
