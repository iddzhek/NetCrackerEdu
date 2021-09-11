package com.nc.edu.ta.artemryabtsev.pr4;

import com.nc.edu.ta.artemryabtsev.pr2.Task;
import com.nc.edu.ta.artemryabtsev.pr3.AbstractTaskList;


public class LinkedTaskList extends AbstractTaskList {

    Node head;
    Node tail;

    class Node{
        Task data;
        Node next;
    }

    public LinkedTaskList() {
        this.head = null;
    }

    @Override
    public void add(Task task) {
        Node tasks = new Node();
        tasks.data = task;
        if (head == null) {
            head = tasks;
            head.next = tail;
            tail = tasks;
        } else {
            tail.next = tasks;
            tail = tasks;
        }
        size++;
    }

    @Override
    public void remove(Task task) {
        Node current = head;
        Node preview = head;

        while (current.data != task){
            if(current.next == null)
                return;
            if (head == null) {
                return;
            } else {
                preview = current;
                current = current.next;
            }
            if (current == head)
                head = head.next;
            else
                preview.next = current.next;
            size--;
        }
    }

    @Override
    public Task getTask(int index) {
        Node current = head;
        Node temp = head;
        int counter = 0;

        while (counter != index) {
            if(current.next == null)
                break;
            current = current.next;
            temp = current;
            counter++;
        }
        return temp.data;
    }

    public Task[] incoming(int from, int to) {
        Node current = head;
        Node incoming = new Node();
        LinkedTaskList tasks = new LinkedTaskList();

         for (int i = 0; i < this.size; i++) {
             if (isChek(current.data, from, to)){
                 if (incoming.data == null)
                    incoming = current;
                 tasks.add(current.data);
                 if (current.next == null)
                     break;
              }
             current = current.next;
             if(current.next == null){
                 if (incoming.data == null)
                     return new Task[0];
             }
        }

         Task[] task = new Task[tasks.size];
         for(int i = 0; i < tasks.size; i++) {
             task[i] = tasks.getTask(i);
         }
        return task;
    }

    private boolean isChek(Task element, int from, int to){
        return (((element.getTime() > from && element.getTime() <= to)
                || (element.nextTimeAfter(from) > from && element.nextTimeAfter(from) <= to))
                && element.isActive());
    }
}
