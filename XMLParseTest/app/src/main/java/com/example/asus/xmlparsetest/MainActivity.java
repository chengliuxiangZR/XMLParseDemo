package com.example.asus.xmlparsetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private TextView textView;
    private ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        textView = (TextView) findViewById(R.id.txt);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
    }

    private ArrayList<Person> readxmlForSAX() throws Exception {
        InputStream is = getAssets().open("person1.xml");
        SaxHelper ss = new SaxHelper();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(is, ss);
        is.close();
        return ss.getPersons();
    }

    @Override
    public void onClick(View view) {
        StringBuilder stringBuilder=new StringBuilder();
        switch (view.getId()) {
            case R.id.btn_1:
                try {
                    persons = readxmlForSAX();
                    for (Person person : persons) {
                        stringBuilder.append(person.toString());
                    }
                    textView.setText(stringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_2:
                Toast.makeText(MainActivity.this,"hehe",Toast.LENGTH_SHORT).show();
                DomHelper domHelper = new DomHelper();
                persons = domHelper.queryXML(MainActivity.this);
                for (Person person : persons) {
                    stringBuilder.append(person.toString());
                }
                textView.setText(stringBuilder.toString());
                break;
            case R.id.btn_3:
                try {
                    PullHelper pullHelper = new PullHelper();
                    InputStream is = getAssets().open("person3.xml");
                    persons = pullHelper.getPersons(is);
                    for (Person person : persons) {
                        stringBuilder.append(person.toString());
                    }
                    textView.setText(stringBuilder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
