package com.nc.edu.ta.artemryabtsev.pr5;

import org.junit.Before;
import com.nc.edu.ta.artemryabtsev.pr4.*;

public class LinkedTaskListTest extends TaskListTest {
    @Before 
    public void createTaskList() {
        tasks = new LinkedTaskList();
    }
}

