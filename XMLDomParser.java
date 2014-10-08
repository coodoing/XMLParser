import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* 
 * DOM解析 ： 属于两次加载，首先把文档载入内存，第二次把文档解析形成一棵树。
 * 优点：解析的过程中修改文件树，可以随便存储文件树的任意部分
 * 缺点：内存占用过大
 * 
 * example: 以action区块为例
 */
public class XMLDomParser {
	/**
	 * 文档构建器
	 */	
	private CourseAction action;	
	private DocumentBuilder builder;	// 这里可以单例化
	
	// 	
	public XMLDomParser() {
		// 获取构建器
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		try {
			builder = f.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	// parse
	public List<CourseAction> parseXml(InputStream in) {
		List<CourseAction> aList = new ArrayList<CourseAction>();
		try {
			Document doc = builder.parse(in);
			NodeList list = doc.getElementsByTagName("person");
			for (int i = 0; i < list.getLength(); i++) {
				// 提取Person元素
				Element el = (Element) list.item(i);
				action = new CourseAction();
				// 获取ID节点				
				action.setVideo(getSubelementTextContentByName(el, "video"));
				action.setDesc(getSubelementTextContentByName(el, "desc"));

				aList.add(action);
			}
			return aList;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Document saveAsXML(String xmlString) {
		if (xmlString == null)
			return null;

		InputSource inputSource = new InputSource(new StringReader(xmlString));
		try {
			return builder.parse(inputSource);
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 得到指定元素的子元素文本 节点(
	 * 
	 * @param el
	 *            父元素
	 * @param name
	 *            子元素名称
	 * @return
	 */
	private String getSubelementTextContentByName(Element el, String name) {
		NodeList list = el.getElementsByTagName(name);
		Element e = (Element) list.item(0);
		return e.getTextContent();
	}

}