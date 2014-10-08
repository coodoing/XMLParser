import java.util.List;

/*
 * 基础类
 * */
class Courses_ {
	public int cId;
	public String cName;
	public List<CourseKind_> ckList;
}

// 等价map类型
class CourseKind_ {
	public String ckName;
	public List<Actions_> acList;
}

class Actions_ {
	public String aname;
	public List<Action_> aList;
}

class Action_ {
	public String name;
	public String video;
	public String desc;
}

public class XMLBase {

}
