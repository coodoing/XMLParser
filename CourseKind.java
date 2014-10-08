import java.util.List;

public class CourseKind {
	
	private String id;
	private String ckName;
	private List<CourseActions> acList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCkName() {
		return ckName;
	}
	public void setCkName(String ckName) {
		this.ckName = ckName;
	}
	public List<CourseActions> getAcList() {
		return acList;
	}
	public void setAcList(List<CourseActions> acList) {
		this.acList = acList;
	}

}
