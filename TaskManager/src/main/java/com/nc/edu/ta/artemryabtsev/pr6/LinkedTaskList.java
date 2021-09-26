package com.nc.edu.ta.artemryabtsev.pr6;


public class LinkedTaskList implements AbstractTaskList {

    private int size = 0;

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
    public void add(Object task) {
        Node tasks = new Node();
        tasks.data = (Task) task;
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
    public void remove(Object task) throws InvalidTaskValueException{
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


    @Override
    public Task[] clone() {
        System.out.println("List");
        LinkedTaskList cloneList = new LinkedTaskList();
        for (int i = 0; i < this.size; i++){
            cloneList.add(this.getTask(i));
        }
        Task[] task = new Task[cloneList.size];
        for(int i = 0; i < cloneList.size; i++) {
            task[i] = cloneList.getTask(i);
        }
        return task;
    }

    @Override
    public String toString() {
        return "LinkedTaskList " +
                "head " + head +
                ", tail = " + tail;
    }

    public boolean equals(Object t){
        if (t == null)
            return false;
        if (this.getClass() != t.getClass()){
            return false;
        }
        if (((LinkedTaskList) t).size == this.size){
            for (int i = 0; i < this.size; i++){
                if (this.getTask(i).hashCode() != ((LinkedTaskList) t).getTask(i).hashCode())
                    return false;
            }
        }else {
            for (int i = 0; i < this.size; i++){
                return this.getTask(i).getTitle() == getTask(i).getTitle()
                        && this.getTask(i).isActive() == getTask(i).isActive()
                        && this.getTask(i).isRepeated() == getTask(i).isRepeated()
                        && this.getTask(i).getTime() == getTask(i).getTime()
                        && this.getTask(i).getStartTime() == getTask(i).getStartTime()
                        && this.getTask(i).getEndTime() == getTask(i).getEndTime()
                        && this.getTask(i).getRepeatInterval() == getTask(i).getRepeatInterval();
            }
        }
        return false;
    }
}
