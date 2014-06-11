package org.springside.examples.showcase;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {
	
	public static void main(String[] args) throws Exception {
		test3();

	}
	
	
	
	public static void test1() throws Exception{
		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("student");
		doc.setRootElement(root);
		
		root.addAttribute("name", "张三");
		
		Element s1 = root.addElement("AA");
		s1.addAttribute("age", "18");
		s1.addComment("哈哈哈");
		s1.addText("213123");
		
		XMLWriter xw = new XMLWriter();
		xw.write(doc);
	}
	
	public static void test2() throws Exception{
		SAXReader sx = new SAXReader();
		Document doc = sx.read(new File("d:/student.xml"));
		Element root = doc.getRootElement();
		
		XMLWriter xw = new XMLWriter();
		xw.write(root);
		
		List list = doc.selectNodes("//world");
		System.out.println(list);

	}
	
	public static void test3() throws Exception {
		SAXReader sx = new SAXReader();
		Document doc = sx.read(new File("d:/student.xml"));
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gb2312");
		XMLWriter writer = new XMLWriter(System.out, format);
		writer.write(doc);
		writer.flush();

		return;
	}
	
	


	
	

}
