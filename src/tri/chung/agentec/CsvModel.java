package tri.chung.agentec;

import java.util.List;

public class CsvModel {

	private int id, parentId;
	private String name;
	private List<CsvModel> childList;

	public CsvModel(String id, String name, String parentId) {
		this.id = Integer.parseInt(id);
		this.parentId = Integer.parseInt(parentId);
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CsvModel> getChildList() {
		return childList;
	}
	public void setChildList(List<CsvModel> childList) {
		this.childList = childList;
	}
}
