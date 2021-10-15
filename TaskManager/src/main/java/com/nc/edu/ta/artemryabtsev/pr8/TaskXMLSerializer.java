package com.nc.edu.ta.artemryabtsev.pr8;

import com.nc.edu.ta.artemryabtsev.pr2.Task;
import com.nc.edu.ta.artemryabtsev.pr3.AbstractTaskList;
import com.nc.edu.ta.artemryabtsev.pr3.ArrayTaskList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class TaskXMLSerializer {

    AbstractTaskList tasks = new ArrayTaskList();

    public void save (AbstractTaskList object, String file) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();

        Element tasks = doc.createElement("tasks");
        doc.appendChild(tasks);

        for (int i = 0; i < object.size; i++){

            String getTitleTask = object.getTask(i).getTitle();
            String getActiveTask = String.valueOf(object.getTask(i).isActive());
            String getRepeatedTask = String.valueOf(object.getTask(i).isRepeated());
            String getTimeTask = String.valueOf(object.getTask(i).getTime());
            String getStartTask = String.valueOf(object.getTask(i).getStartTime());
            String getEndTask = String.valueOf(object.getTask(i).getEndTime());
            String getRepeatTask = String.valueOf(object.getTask(i).getRepeatInterval());

            Element task = doc.createElement("task");
            task.appendChild(doc.createTextNode(String.valueOf(getTitleTask)));

            Attr attrActive = doc.createAttribute("active");
            attrActive.setValue(getActiveTask);
            task.setAttributeNode(attrActive);

            Attr attrTime = doc.createAttribute("time");
            attrTime.setValue(getTimeTask);
            task.setAttributeNode(attrTime);

            Attr attrStart = doc.createAttribute("start");
            attrStart.setValue(getStartTask);
            task.setAttributeNode(attrStart);

            Attr attrEnd = doc.createAttribute("end");
            attrEnd.setValue(getEndTask);
            task.setAttributeNode(attrEnd);

            Attr attrRepeat = doc.createAttribute("repeat");
            attrRepeat.setValue(getRepeatTask);
            task.setAttributeNode(attrRepeat);

            Attr attrRepeated = doc.createAttribute("repeated");
            attrRepeated.setValue(getRepeatedTask);
            task.setAttributeNode(attrRepeated);

            tasks.appendChild(task);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(file));
        transformer.transform(domSource, streamResult);
    }

    public void load(String file) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =  builder.parse(new File(file));
        document.getDocumentElement().normalize();

        NodeList list = document.getElementsByTagName("task");

        for (int i = 0; i < list.getLength(); i++){

            Task task = new Task();

            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){

                Element element = (Element) node;

                Boolean getActive = Boolean.parseBoolean(element.getAttribute("active"));
                int getTime = Integer.parseInt(element.getAttribute("time"));
                int getStart = Integer.parseInt(element.getAttribute("start"));
                int getEnd = Integer.parseInt(element.getAttribute("end"));
                int getRepeat = Integer.parseInt(element.getAttribute("repeat"));
                Boolean getRepeated = Boolean.parseBoolean(element.getAttribute("repeated"));
                String getTitle = String.valueOf(element.getTextContent());

                task.setTitle(getTitle);
                task.setActive(getActive);
                task.setTime(getTime);
                task.setTime(getStart, getEnd, getRepeat);
                task.setRepeated(getRepeated);

                tasks.add(task);
            }
        }
    }
}
