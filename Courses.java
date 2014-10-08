import java.util.List;

public class Courses {
	
	private String cId;
	private String cName;
	private List<CourseKind> ckList;
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public List<CourseKind> getCkList() {
		return ckList;
	}
	public void setCkList(List<CourseKind> ckList) {
		this.ckList = ckList;
	}

}
