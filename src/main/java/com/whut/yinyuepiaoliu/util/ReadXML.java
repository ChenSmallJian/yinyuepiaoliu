package com.whut.yinyuepiaoliu.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.util.Iterator;

public class ReadXML {
    public static void readStringXml(String xml) {
        Document doc = null;
        try {
            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML

            Element rootElt = doc.getRootElement(); // 获取根节点

            // iterate through child elements of root
            for (Iterator<Element> it = rootElt.elementIterator(); it.hasNext();) {
                Element element = it.next();
                // do something
                System.out.print(element.getName()+" : ");
                System.out.println(element.getData());
            }
        } catch (DocumentException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void XMLTest(){
    }
}