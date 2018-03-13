package com.bow.spring.document;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.xml.BeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/** 
 * @author ViVi
 * @date 2015年8月23日 下午12:02:06  
 */

public class XmlDocumentTest {

    public void testXmlParse() {
        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
        InputStream is = null;
        try {
            DocumentBuilder db = domfac.newDocumentBuilder();
            is = this.getClass().getResourceAsStream("/spring/document/parse.xml");
            // is = new FileInputStream(new File("/spring/document/parse.xml"));
            Document doc = db.parse(is);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                String name = list.item(i).getNodeName();
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testDocumentReader() {
        BeanDefinitionDocumentReader documentReader = new DefaultBeanDefinitionDocumentReader();
    }

    public void testGetPath() {
        URL path1 = this.getClass().getResource("");
        URL path2 = this.getClass().getResource("/");
        System.out.println(path1);
        System.out.println(path2);
    }
}
