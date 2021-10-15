package com.nc.edu.ta.artemryabtsev.pr8;

import com.nc.edu.ta.artemryabtsev.pr2.Task;
import com.nc.edu.ta.artemryabtsev.pr4.LinkedTaskList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {

        File TaskXML = new File("./src/main/resources/TaskXML.xml");
        String xml = "./src/main/resources/TaskXML.xml";

        Task task1 = new Task("a",0);
        Task task2 = new Task("b",1, 50, 5);
        Task task3 = new Task("c",2);

        task2.setActive(true);

        LinkedTaskList ts = new LinkedTaskList();
        ts.add(task1);
        ts.add(task2);
        ts.add(task3);

        TaskXMLSerializer taskXMLSerializer = new TaskXMLSerializer();
        taskXMLSerializer.save(ts, xml);

        taskXMLSerializer.load(xml);
        for (int i = 0; i < taskXMLSerializer.tasks.size; i++){
            System.out.println(taskXMLSerializer.tasks.getTask(i).toString());
        }
    }
}
