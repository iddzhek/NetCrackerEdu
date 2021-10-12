package com.nc.edu.ta.artemryabtsev.pr8;

import com.nc.edu.ta.artemryabtsev.pr2.Task;
import com.nc.edu.ta.artemryabtsev.pr3.AbstractTaskList;
import com.nc.edu.ta.artemryabtsev.pr3.ArrayTaskList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
            Element task = doc.createElement("task");
            tasks.appendChild(task);

            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(object.getTask(i).getTitle()));
            task.appendChild(title);

            Element active = doc.createElement("active");
            active.appendChild(doc.createTextNode(String.valueOf(object.getTask(i).isActive())));
            task.appendChild(active);

            Element time = doc.createElement("time");
            time.appendChild(doc.createTextNode(String.valueOf(object.getTask(i).getTime())));
            task.appendChild(time);

            Element start = doc.createElement("start");
            start.appendChild(doc.createTextNode(String.valueOf(object.getTask(i).getStartTime())));
            task.appendChild(start);

            Element end = doc.createElement("end");
            end.appendChild(doc.createTextNode(String.valueOf(object.getTask(i).getEndTime())));
            task.appendChild(end);

            Element repeat = doc.createElement("repeat");
            repeat.appendChild(doc.createTextNode(String.valueOf(object.getTask(i).getRepeatInterval())));
            task.appendChild(repeat);

            Element repeated = doc.createElement("repeated");
            repeated.appendChild(doc.createTextNode(String.valueOf(object.getTask(i).isRepeated())));
            task.appendChild(repeated);
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

        Node taskNode = document.getFirstChild();
        NodeList tasksChilds = taskNode.getChildNodes();

        NodeList list = document.getElementsByTagName("task");

        for (int i = 0; i < list.getLength(); i++){

            Task task = new Task();

            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){

                Element element = (Element) node;

                task.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                task.setActive(Boolean.parseBoolean((element.getElementsByTagName("active").item(0).getTextContent())));
                task.setTime(Integer.parseInt(element.getElementsByTagName("time").item(0).getTextContent()));
                task.setTime((Integer.parseInt(element.getElementsByTagName("start").item(0).getTextContent())),
                        (Integer.parseInt(element.getElementsByTagName("end").item(0).getTextContent())),
                        (Integer.parseInt(element.getElementsByTagName("repeat").item(0).getTextContent())));
                task.setRepeated(Boolean.parseBoolean((element.getElementsByTagName("repeated").item(0).getTextContent())));

                tasks.add(task);
            }
        }
    }
}
