package com.nc.edu.ta.artemryabtsev.pr6;

public interface AbstractTaskList{

    int size = 0;

    void add(Task task);

    void remove(Task task);

    Object clone();

//public abstract class AbstractTaskList implements Cloneable {
//
//    public int size = 0;
//
//    public abstract void add(Task task);
//
//    public abstract void remove(Task task);
//
//    public int size()
//    {
//        return size;
//    }
//
//    public abstract Task getTask(int index);
//
//    public Task[] incoming(int i, int i1) {
//        return new Task[0];
//    }
//
//    public abstract Object[] clone();
}

