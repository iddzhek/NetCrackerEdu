package com.nc.edu.ta.artemryabtsev.pr6;

import java.util.Arrays;

public class ArrayTaskList implements AbstractTaskList {

    private int size = 0;

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

    @Override
    public void add(Object task) {
        if (task == null)
            throw new InvalidTaskValueException();
        Task[] newTasksOne = new Task[tasks.length + 1];
        if (size == 0) {
            newTasksOne[size] = (Task) task;
            size++;
            tasks = newTasksOne;
            return;
        }
        Task[] newTasksTwo = new Task[tasks.length + 1];
        newTasksOne = tasks;
        tasks = newTasksTwo;
        for (int i = 0; i < tasks.length; i++) {
            if (i == tasks.length - 1) {
                tasks[i] = (Task) task;
                size++;
                return;
            }
            tasks[i] = newTasksOne[i];
        }
    }

    @Override
    public void remove(Object task) {
        Task searchKey = (Task) task;
        Task[] newTasks = new Task[tasks.length - 1];
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == searchKey) {
                for (int j = 0; j < i; j++) {
                    newTasks[j] = tasks[j];
                }
                for (int k = i; k < tasks.length - 1; k++) {
                    newTasks[k] = tasks[k + 1];
                }
            }
        }
        tasks = newTasks;
        size--;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    @Override
    public Task[] clone()  {
        Task[] cloneArray = new Task[0];
        for (int i = 0; i < tasks.length; i++){
            cloneArray[i] = tasks[i];
        }
        return cloneArray;
    }

    public boolean equals(Object t){
        if (t == null)
            return false;
        if (this.getClass() != t.getClass()){
            return false;
        }
        if (((ArrayTaskList) t).size == this.size){
            for (int i = 0; i < this.size; i++){
                if (this.getTask(i).hashCode() != ((ArrayTaskList) t).getTask(i).hashCode())
                    return false;
            }
        }else {
            Task[] task = (Task[]) t;
            for (int i = 0; i < this.size; i++){
                return this.getTask(i).getTitle() == task[i].getTitle()
                        && this.getTask(i).isActive() == task[i].isActive()
                        && this.getTask(i).isRepeated() == task[i].isRepeated()
                        && this.getTask(i).getTime() == task[i].getTime()
                        && this.getTask(i).getStartTime() == task[i].getStartTime()
                        && this.getTask(i).getEndTime() == task[i].getEndTime()
                        && this.getTask(i).getRepeatInterval() == task[i].getRepeatInterval();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ArrayTaskList " +
                "size= " + size +
                ", tasks= " + Arrays.toString(tasks);
    }
}
