package com.nc.edu.ta.artemryabtsev.pr3;

import com.nc.edu.ta.artemryabtsev.pr2.Task;

public abstract class AbstractTaskList {

    public int size = 0;

    public abstract void add(Task task);

    public abstract void remove(Task task);

    public int size()
    {
        return size;
    }

    public abstract Task getTask(int index);

    public Task[] incoming(int i, int i1) {
        return new Task[0];
    }
}

