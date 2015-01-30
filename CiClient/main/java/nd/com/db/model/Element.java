package nd.com.db.model;

import java.io.Serializable;

public class Element implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int elementId;
	String text;
	String classType;
	int elementIndex;
	int operationalId;
	int resultId;
	String sourceId;

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
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

	public int getElementIndex() {
		return elementIndex;
	}

	public void setElementIndex(int elementIndex) {
		this.elementIndex = elementIndex;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof Element)) {
			return false;
		}
		Element elem = (Element) obj;
		return elem.classType.equals(classType) && elem.elementId == elementId
				&& elem.elementIndex == elementIndex && elem.operationalId == operationalId
				&& elem.resultId == resultId && elem.text.equals(text);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = elementId + " | " + text + " | " + classType + " | "
				+ elementIndex + " | " + operationalId + " | " + resultId;
		return str;
	}
}
