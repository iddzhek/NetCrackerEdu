package com.nc.edu.ta.artemryabtsev.pr3;

import com.nc.edu.ta.artemryabtsev.pr2.Task;
import com.nc.edu.ta.artemryabtsev.pr5.InvalidTaskValueException;

//import java.util.Arrays;

public class ArrayTaskList extends AbstractTaskList {

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
    public void add(Task task) {
        if (task == null)
            throw new InvalidTaskValueException();
        Task[] newTasksOne = new Task[tasks.length + 1];
        if (size == 0) {
            newTasksOne[size] = task;
            size++;
            tasks = newTasksOne;
            return;
        }
        Task[] newTasksTwo = new Task[tasks.length + 1];
        newTasksOne = tasks;
        tasks = newTasksTwo;
        for (int i = 0; i < tasks.length; i++) {
            if (i == tasks.length - 1) {
                tasks[i] = task;
                size++;
                return;
            }
            tasks[i] = newTasksOne[i];
        }
    }


    @Override
    public void remove(Task task) {
        Task searchKey = task;
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

    @Override
    public Task getTask(int index) {
        return (Task) tasks[index];
    }
}
