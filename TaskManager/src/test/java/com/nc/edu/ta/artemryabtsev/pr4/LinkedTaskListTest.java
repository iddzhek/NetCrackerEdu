package com.nc.edu.ta.artemryabtsev.pr4;

import org.junit.Before;

public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
