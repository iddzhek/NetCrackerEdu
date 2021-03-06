package com.nc.edu.ta.artemryabtsev.pr2;

import com.nc.edu.ta.artemryabtsev.pr5.InvalidTaskIndexException;
import com.nc.edu.ta.artemryabtsev.pr5.InvalidTaskParametersException;
import com.nc.edu.ta.artemryabtsev.pr5.InvalidTaskValueException;

public class Task implements Cloneable{
    private String title;
    private boolean active;
    private boolean repeated;
    private int time;
    private int start;
    private int end;
    private int repeat;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;
        setRepeated(false);
        if (time == 0)
            setActive(false);
        if (title == "" || time < 0)
            throw new InvalidTaskParametersException("invalid argument value");
        if (title == null)
            throw new InvalidTaskValueException("invalid argument value");
    }

    public Task(String title, int start, int end, int repeat) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.time = start;
        this.repeat = repeat;
        setRepeated(true);

        if (start > end || start < 0 || repeat < 0 || title == "")
            throw new InvalidTaskParametersException("invalid argument value");
        if (title == null)
            throw new InvalidTaskValueException("invalid argument value");
    }

    public Task() {

    }


    public void setTitle(String title){
        this.title = title;
        if (title == "")
            throw new InvalidTaskIndexException("invalid argument value");
        if (title == null)
            throw new InvalidTaskValueException("invalid argument value");
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRepeated (boolean repeated){
        this.repeated = repeated;
    }

    public void setTime(int time){
        this.time = time;
        this.start = time;
        this.end = time;
        this.repeat = 0;
        this.repeated = false;

        if (time < 0){
            throw new InvalidTaskIndexException("invalid argument value");
        }
    }

    public void setTime(int start, int end, int repeat){
        this.time = start;
        this.start = start;
        this.end = end;
        this.repeat = repeat;
        this.repeated = true;

        if (start < 0 || start > end || end < 0 || repeat < 0){
            throw new InvalidTaskIndexException("invalid argument value");
        }
    }

    public String getTitle(){
        return this.title;
    }

    public boolean isActive(){
        return active;
    }

    public int getTime(){
        return this.time;
    }

    public int getStartTime (){
        return this.start;
    }

    public int getEndTime(){
        return this.end;
    }

    public int getRepeatInterval(){
        return repeat;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public String toString(){
        if (isActive() && isRepeated()) {
            return "Task \"" + getTitle() + "\" from " + getStartTime() +
                    " to " + getEndTime() + " every " + getRepeatInterval() + " seconds";
        }
        if (isActive())
            return "Task \"" + getTitle() + "\" at " + getTime();
        else return "Task \"" + getTitle() + "\" is inactive";
    }

    public int nextTimeAfter(int time){
        if (time < 0){
            throw new RuntimeException("invalid argument value");
        }
        if (!isActive())
            return -1;
        if (!isRepeated() && time >= this.time)
            return -1;
        if (isRepeated() && time >= this.end)
            return -1;
        if ((time >= start && time < (end-repeat)) || time < end)
            for (int i = this.start; i <= end; i += this.repeat){
                if (time < i)
                    return i;
            }
        if (isRepeated() && time <= this.end && time >=(end-repeat))
            return -1;
        if (time < start)
            return start;
        return this.time;
    }
    @Override
    public Task clone(){
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object t){
        if (this.getClass() != t.getClass()){
            return false;
        }else if (this.hashCode() != t.hashCode()){
            return false;
        }else {
            Task task = (Task) t;
            return this.title == task.title
                    && this.active == task.active
                    && this.repeated == task.repeated
                    && this.time == task.time
                    && this.start == task.start
                    && this.end == task.end
                    && this.repeat == task.repeat;
        }
    }

    public int hashCode(){
        return 31 * (title.hashCode() + 31 * (time + 31 * (start + 31 * (end + 31 * (repeat)))));
    }
}
