package org.springside.examples.showcase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtils {
	
	public static String getElementValueByTagName(Element root, String tagName) {
		NodeList nodes = root.getElementsByTagName(tagName);
		if (nodes != null) {
			Node node = nodes.item(0).getFirstChild();
			if (node != null){
				return node.getNodeValue();
			} else{
				return null;
			}
		} else{
			return null;
		}
	}

	public static Element convertStringToElement(String src)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputStream ins = new ByteArrayInputStream(src.getBytes());
		Document doc = db.parse(ins);
		return doc.getDocumentElement();
	}

	public static org.dom4j.Element getRootElement(String src)
			throws DocumentException {
		org.dom4j.Document document = DocumentHelper.parseText(src);
		org.dom4j.Element rootElement = document.getRootElement();
		return rootElement;
	}

	public static String getElementByPath(org.dom4j.Element rootElement, String pathStr) throws DocumentException {
		org.dom4j.Node selectSingleNode = rootElement.selectSingleNode(pathStr);
		if (selectSingleNode == null){
			return "";
		}
		return selectSingleNode.getText();
	}

	@SuppressWarnings("unchecked")
	public static List<org.dom4j.Element> getElementsByPath(org.dom4j.Element rootElement, String pathStr)
			throws DocumentException {
		List<org.dom4j.Element> selectNodes = rootElement.selectNodes(pathStr);
		return selectNodes;
	}

	public static void updateNodeValueByTagName(Document doc, String node, String value) {
		NodeList nl = doc.getElementsByTagName(node);
		Element e;
		for (int i = 0; i < nl.getLength(); i++) {
			e = (Element) nl.item(i);
			if (e.getFirstChild() != null) {
				e.getFirstChild().setNodeValue(value);
			} else {
				e.appendChild(doc.createTextNode(value));
			}
		}
	}
}
