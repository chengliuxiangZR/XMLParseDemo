package com.example.asus.xmlparsetest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

public class PullHelper {
    public static ArrayList<Person> getPersons(InputStream xml) throws Exception{
        ArrayList<Person> persons=null;
        Person person=null;
        XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
        XmlPullParser parser=factory.newPullParser();
        parser.setInput(xml,"UTF-8");
        int eventType=parser.getEventType();
        while (eventType!=XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    persons=new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if("person".equals(parser.getName())){
                        person=new Person();
                        int id=Integer.parseInt(parser.getAttributeValue(0));
                        person.setId(id);
                    }else if("name".equals(parser.getName())){
                        String name=parser.nextText();
                        person.setName(name);
                    }else if("age".equals(parser.getName())){
                        short age=Short.parseShort(parser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("person".equals(parser.getName())){
                        persons.add(person);
                        person=null;
                    }
                    break;
            }
            eventType=parser.next();
        }
        return persons;
    }
}
