import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.Xml;

@SuppressLint("UseValueOf")
public class XMLPullParser {
	
	public static final Map<String, Map<String, List<String>>> seriesMap = new HashMap<String, Map<String, List<String>>>();    
    public static final Map<String, List<String>> CourseMap = new HashMap<String, List<String>>();
    
	// 保存xml
	public static void saveAsXml(String xml){
		
	}
	
	// 解析xml
	public static Courses parseXml(InputStream inStream) {
		XmlPullParser parser = Xml.newPullParser();
		
		try {
			parser.setInput(inStream, "UTF-8");// 设置数据源编码  
			int eventType = parser.getEventType();

			Courses course = null;//new Courses();
			CourseKind ckind = null;
			List<CourseKind> ckList = null;
			CourseActions actions = null;
			List<CourseActions> acList = null;
			CourseAction action = null;
			List<CourseAction> aList = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					ckList = new ArrayList<CourseKind>();
					acList = new ArrayList<CourseActions>();
					aList = new ArrayList<CourseAction>();
					break;

				case XmlPullParser.START_TAG:// 开始元素事件
					String startTag = parser.getName();
					if(startTag.equalsIgnoreCase("course")){
						 course = new Courses();
						 course.setcId((parser.getAttributeValue(null, "id")));
						 course.setcName(parser.getAttributeValue(null, "name"));						 
						 
					}
					if (startTag.equalsIgnoreCase("coursekind") && course != null) {
							ckind = new CourseKind();
							ckind.setId((parser.getAttributeValue(null, "id")));
							ckind.setCkName(parser.getAttributeValue(null, "name"));
					}
					if(startTag.equalsIgnoreCase("actions") && ckind != null){
							actions = new CourseActions() ;
							actions.setId(parser.getAttributeValue(null, "id"));
							actions.setaName(parser.getAttributeValue(null, "name")) ;
					}
					if (startTag.equalsIgnoreCase("action") && actions != null) {
							action = new CourseAction();		
							action.setId(parser.getAttributeValue(null, "id"));
							action.setName(parser.getAttributeValue(null, "name"));
					}
					if(action != null){
						if(startTag.equalsIgnoreCase("video")){
							action.setVideo(parser.nextText());
						}								
						if (startTag.equalsIgnoreCase("desc")){
							action.setDesc(parser.nextText());
						}
					}
					
					break;

				case XmlPullParser.END_TAG:// 结束元素事件
					String endTag = parser.getName();
					
					if (endTag.equalsIgnoreCase("action")
							&& action != null) {
						aList.add(action);
						action = null;
					}
					
					if (endTag.equalsIgnoreCase("actions")
							&& actions != null) {
						actions.setAlist(aList);
						acList.add(actions);
						//aList = null;
						aList = new ArrayList<CourseAction>();
						actions = null;
					}
					
					if (endTag.equalsIgnoreCase("coursekind")
							&& ckind != null) {
						ckind.setAcList(acList);
						ckList.add(ckind);
						//acList = null;
						acList = new ArrayList<CourseActions>();
						ckind = null;
					}
					
					if ((endTag.equalsIgnoreCase("course"))
							&& course != null) {
						course.setCkList(ckList);
						//ckList = null;
						ckList = new ArrayList<CourseKind>();
					}
					break;
				}

				eventType = parser.next();
			}
			
			inStream.close();
			return course;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static Courses_ parseXml_(InputStream inStream) {
		XmlPullParser parser = Xml.newPullParser();
		
		try {
			parser.setInput(inStream, "UTF-8");// 设置数据源编码  
			int eventType = parser.getEventType();

			Courses_ course = null;//new Courses();
			CourseKind_ ckind = null;
			List<CourseKind_> ckList = null;
			Actions_ actions = null;
			List<Actions_> acList = null;
			Action_ action = null;
			List<Action_> aList = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					ckList = new ArrayList<CourseKind_>();
					acList = new ArrayList<Actions_>();
					aList = new ArrayList<Action_>();
					break;

				case XmlPullParser.START_TAG:// 开始元素事件
					String startTag = parser.getName();
					if(startTag.equalsIgnoreCase("course")){
						 course = new Courses_();
						 course.cId = new Integer(parser.getAttributeValue(null, "id"));
						 course.cName = parser.getAttributeValue(null, "name");						 
						 
					}
					if (startTag.equalsIgnoreCase("coursekind") && course != null) {
							ckind = new CourseKind_();
							ckind.ckName = parser.getAttributeValue(null, "name");
					}
					if(startTag.equalsIgnoreCase("actions") && ckind != null){
							actions = new Actions_() ;
							actions.aname = parser.getAttributeValue(null, "name");
					}
					if (startTag.equalsIgnoreCase("action") && actions != null) {
							action = new Action_();		
							action.name = parser.getAttributeValue(null, "name");
					}
					if(action != null){
						if(startTag.equalsIgnoreCase("video")){
							action.video = parser.nextText();
						}								
						if (startTag.equalsIgnoreCase("desc")){
							action.desc = parser.nextText();
						}
					}
					
					break;

				case XmlPullParser.END_TAG:// 结束元素事件
					String endTag = parser.getName();
					
					if (endTag.equalsIgnoreCase("action")
							&& action != null) {
						aList.add(action);
						action = null;
					}
					
					if (endTag.equalsIgnoreCase("actions")
							&& actions != null) {
						actions.aList = aList;
						acList.add(actions);
						aList = null;
						actions = null;
					}
					
					if (endTag.equalsIgnoreCase("coursekind")
							&& ckind != null) {
						ckind.acList = acList;
						ckList.add(ckind);
						acList = null;
						ckind = null;
					}
					
					if ((endTag.equalsIgnoreCase("course"))&&course != null) {
						course.ckList = ckList;
						ckList = null;
						action = null;
					}
					break;
				}

				eventType = parser.next();
			}
			
			return course;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

