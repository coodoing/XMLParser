import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

/*
 * SAX解析
 * 优点：XML文件在加载的过程中，加载到不同节点会相应触发不同方法来处理
 * 缺点：解析过程中无法中断，只能读取XML文件而不能修改，编码上也相对复杂与难于理解
 * 
 * example: 以action区块为例
 * */
public class XMLSaxParser {
	private SAXParser parser;

	public XMLSaxParser() {
		// 实例化解析工厂
		SAXParserFactory f = SAXParserFactory.newInstance();
		try {
			parser = f.newSAXParser();
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("Sax", e.getMessage());
		}
	}

	public List<Action_> doParse(InputStream in) {
		XmlHandler h = new XmlHandler();
		try {
			parser.parse(in, h);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h.getActionList();
	}

	/*
	 * 处理器
	 */
	class XmlHandler extends DefaultHandler {
		List<Action_> aList = null;
		private Action_ action;
		private Object currentEleName;

		/**
		 * 开始解析文档的时候实例化集合
		 */
		@Override
		public void startDocument() throws SAXException {
			aList = new ArrayList<Action_>();
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.endDocument();
		}

		/**
		 * 元素开始
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if ("action".equals(localName)) {
				action = new Action_();// 节点开始的时候实例化person
			} else if ("video".equals(localName)) {
				this.currentEleName = "video";
			} else if ("desc".equals(localName)) {
				this.currentEleName = "desc";
			}
		}

		/**
		 * 元素结束事件
		 */
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// 解析到文档末尾
			if ("action".equals(localName)) {
				// 将解析完成的对象添加到集合
				aList.add(action);
			} else if ("video".equals(localName) || "desc".equals(localName)) {
				this.currentEleName = "";
			}
		}

		/**
		 * ) 用户处理字符节点
		 */
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			String str = new String(ch, start, length);
			if ("video".equals(currentEleName)) {
				action.video = str;
			} else if ("desc".equals(currentEleName)) {
				action.desc = str;
			}
		}

		public List<Action_> getActionList() {
			return aList;
		}
	}
}
