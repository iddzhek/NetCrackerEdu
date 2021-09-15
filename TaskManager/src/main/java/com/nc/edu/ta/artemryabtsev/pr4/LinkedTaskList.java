package com.nc.edu.ta.artemryabtsev.pr4;

import com.nc.edu.ta.artemryabtsev.pr2.Task;
import com.nc.edu.ta.artemryabtsev.pr3.AbstractTaskList;
import com.nc.edu.ta.artemryabtsev.pr5.InvalidTaskIndexException;
import com.nc.edu.ta.artemryabtsev.pr5.InvalidTaskValueException;


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
        if (task == null)
            throw new InvalidTaskValueException();
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
    public void remove(Task task) throws InvalidTaskValueException{
        if (task == null)
            throw new InvalidTaskValueException();

        Node current = head;

        if (head.data == task) {
            head = head.next;
            size--;
        }

        while (current.data != null) {

            if (current.next == null)
                return;

            if(current.next.data == task) {
                if (tail == current.next) {
                    tail = current;
                }
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    @Override
    public Task getTask(int index) throws InvalidTaskIndexException {
        if (index < 0 )
            throw new InvalidTaskIndexException("invalid element index entered");
        if (index >= this.size)
            throw new InvalidTaskIndexException("invalid element index entered");

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
        LinkedTaskList filteredTasks = new LinkedTaskList();

         for (int i = 0; i < this.size; i++) {
             if (shouldBeInvokedInPeriod(current.data, from, to)){
                 filteredTasks.add(current.data);
              }
             current = current.next;
        }

         Task[] task = new Task[filteredTasks.size];
         for(int i = 0; i < filteredTasks.size; i++) {
             task[i] = filteredTasks.getTask(i);
         }
        return task;
    }

    private boolean shouldBeInvokedInPeriod(Task element, int from, int to){
        return (((element.getTime() > from && element.getTime() <= to)
                || (element.nextTimeAfter(from) > from && element.nextTimeAfter(from) <= to))
                && element.isActive());
    }
}
