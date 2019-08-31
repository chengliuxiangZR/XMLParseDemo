package com.example.asus.xmlparsetest;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomHelper {
    public static ArrayList<Person> queryXML(Context context){
        ArrayList<Person> Persons=new ArrayList<Person>();
        try {
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(context.getAssets().open("person2.xml"));
            System.out.println("处理该文档的DomImplemention对象="+document.getImplementation());
            NodeList nodeList=document.getElementsByTagName("person");
            for(int i=0;i<nodeList.getLength();i++){
                Element personElement=(Element) nodeList.item(i);
                Person person=new Person();
                person.setId(Integer.valueOf(personElement.getAttribute("id")));
                NodeList childNoList = personElement.getChildNodes();
                for(int j = 0;j < childNoList.getLength();j++)
                {
                    Node childNode = childNoList.item(j);
                    //判断子note类型是否为元素Note
                    if(childNode.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element childElement = (Element) childNode;
                        if("name".equals(childElement.getNodeName()))
                            person.setName(childElement.getFirstChild().getNodeValue());
                        else if("age".equals(childElement.getNodeName()))
                            person.setAge(Short.valueOf(childElement.getFirstChild().getNodeValue()));
                    }
                }
                Persons.add(person);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Persons;
    }
}
