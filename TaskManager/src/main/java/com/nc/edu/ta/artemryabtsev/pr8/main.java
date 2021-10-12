package com.nc.edu.ta.artemryabtsev.pr8;

import com.nc.edu.ta.artemryabtsev.pr2.Task;
import com.nc.edu.ta.artemryabtsev.pr4.LinkedTaskList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {

        String xml = "D:\\NetCracker\\Lab\\NetCrackerEdu\\TaskManager\\xml.xml";

        Task[] ts1 = {new Task("a",0), new Task("b",1), new Task("c",2)};
        Task task1 = new Task("a",0);
        Task task2 = new Task("b",1);
        Task task3 = new Task("c",2);

        Task task4 = new Task("c",2);
        Task task5 = new Task("b",1);

        LinkedTaskList ts = new LinkedTaskList();
        ts.add(task1);
        ts.add(task2);
        ts.add(task3);
        LinkedTaskList ts2 = new LinkedTaskList();
        ts2.add(task1);
        ts2.add(task5);
        ts2.add(task4);

        TaskXMLSerializer task = new TaskXMLSerializer();
        task.save(ts2, xml);

        task.load(xml);
        for (int i = 0; i < task.tasks.size; i++){
            System.out.println(task.tasks.getTask(i).toString());
        }
    }
}
