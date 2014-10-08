import java.io.InputStream;

// parser入口类
public class XMLParser {
	public static Object parse(InputStream inputStream) {
		if("pull".equalsIgnoreCase(Constants.XML_PARSER)){
			return XMLPullParser.parseXml(inputStream);
		}
		if("sax".equalsIgnoreCase(Constants.XML_PARSER)){
			// TODO 
			return "";
		}
		if("dom".equalsIgnoreCase(Constants.XML_PARSER)){
			// TODO
			return "";
		}
		return null;
	}		
}
