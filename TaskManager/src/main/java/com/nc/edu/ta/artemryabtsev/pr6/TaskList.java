package com.nc.edu.ta.artemryabtsev.pr6;

import java.util.ArrayList;

public class TaskList extends ArrayList {

    Task[] tasks = new Task[0];

    public Task[] incoming(int from, int to) {

        int count = 0;
        int j = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (((tasks[i].getTime() > from && tasks[i].getTime() <= to)
                    || (tasks[i].nextTimeAfter(from) > from && tasks[i].nextTimeAfter(from) <= to))
                    && tasks[i].isActive())
                count++;

        }
        Task[] incoming = new Task[count];
        for (int i = 0; i < tasks.length; i++) {
            if (((tasks[i].getTime() > from && tasks[i].getTime() <= to)
                    || (tasks[i].nextTimeAfter(from) > from && tasks[i].nextTimeAfter(from) <= to))
                    && tasks[i].isActive()){
                incoming[j] = tasks[i];
                j++;
            }
        }
        return incoming;
    }
//
//    public int size = 0;
//    TaskList tasks = new TaskList();
//
//    Node head;
//    Node tail;
//
//    class Node{
//        Task data;
//        Node next;
//    }
//
//    public TaskList() {
//        this.head = null;
//    }
//
//    @Override
//    public void add(Object task) {
//        TaskList.Node tasks = new TaskList.Node();
//        tasks.data = (Task) task;
//        if (task == null)
//            throw new InvalidTaskValueException();
//        if (head == null) {
//            head = tasks;
//            head.next = tail;
//            tail = tasks;
//        } else {
//            tail.next = tasks;
//            tail = tasks;
//        }
//        size++;
//    }
//
//    @Override
//    public void remove(Object task) throws InvalidTaskValueException{
//        if (task == null)
//            throw new InvalidTaskValueException();
//
//        TaskList.Node current = head;
//
//        if (head.data == task) {
//            head = head.next;
//            size--;
//        }
//
//        while (current.data != null) {
//
//            if (current.next == null)
//                return;
//
//            if(current.next.data == task) {
//                if (tail == current.next) {
//                    tail = current;
//                }
//                current.next = current.next.next;
//                size--;
//                return;
//            }
//            current = current.next;
//        }
//    }
//
//
//    public Task getTask(int index) throws InvalidTaskIndexException {
//        if (index < 0 )
//            throw new InvalidTaskIndexException("invalid element index entered");
//        if (index >= this.size)
//            throw new InvalidTaskIndexException("invalid element index entered");
//
//        TaskList.Node current = head;
//        TaskList.Node temp = head;
//        int counter = 0;
//
//        while (counter != index) {
//            if(current.next == null)
//                break;
//            current = current.next;
//            temp = current;
//            counter++;
//        }
//        return temp.data;
//    }
//
//    public int size()
//    {
//        return size;
//    }
//
//    public Task[] incoming(int from, int to) {
//        TaskList.Node current = head;
//        TaskList filteredTasks = new TaskList();
//
//        for (int i = 0; i < this.size; i++) {
//            if (shouldBeInvokedInPeriod(current.data, from, to)){
//                filteredTasks.add(current.data);
//            }
//            current = current.next;
//        }
//
//        Task[] task = new Task[filteredTasks.size];
//        for(int i = 0; i < filteredTasks.size; i++) {
//            task[i] = filteredTasks.getTask(i);
//        }
//        return task;
//    }
//
//
//
//    private boolean shouldBeInvokedInPeriod(Task element, int from, int to){
//        return (((element.getTime() > from && element.getTime() <= to)
//                || (element.nextTimeAfter(from) > from && element.nextTimeAfter(from) <= to))
//                && element.isActive());
//    }
//
//
//    @Override
//    public TaskList clone() {
//        System.out.println("List");
//        TaskList cloneList = new TaskList();
//        for (int i = 0; i < this.size; i++){
//            cloneList.add(this.getTask(i));
//        }
//        return cloneList;
//    }
//
//    @Override
//    public String toString() {
//        return "LinkedTaskList " +
//                "head " + head +
//                ", tail = " + tail;
//    }
//
//
//    public boolean equals(Object t){
//        if (t == null)
//            return false;
//        if (this.getClass() != t.getClass()){
//            return false;
//        }
//        if (((TaskList) t).size == this.size){
//            for (int i = 0; i < this.size; i++){
//                if (this.getTask(i).hashCode() != ((TaskList) t).getTask(i).hashCode())
//                    return false;
//            }
//        }else {
//            for (int i = 0; i < this.size; i++){
//                return this.getTask(i).getTitle() == getTask(i).getTitle()
//                        && this.getTask(i).isActive() == getTask(i).isActive()
//                        && this.getTask(i).isRepeated() == getTask(i).isRepeated()
//                        && this.getTask(i).getTime() == getTask(i).getTime()
//                        && this.getTask(i).getStartTime() == getTask(i).getStartTime()
//                        && this.getTask(i).getEndTime() == getTask(i).getEndTime()
//                        && this.getTask(i).getRepeatInterval() == getTask(i).getRepeatInterval();
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public Iterator getIterator() {
//        return new TaskIterator();
//    }
//
//    private class TaskIterator implements Iterator{
//
//        @Override
//        public boolean hasNext() {
//            if (size < tasks.size){
//                return true;
//            }
//            return false;
//        }
//
//        @Override
//        public Object next() {
//            if (!hasNext()) {
//                throw new RuntimeException();
//            }
//            return tasks.getTask(size++);
//        }
//    }
}