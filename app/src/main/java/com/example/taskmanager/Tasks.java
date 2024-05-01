package com.example.taskmanager;

public class Tasks {
    private String title, desc, dueDate;
    private Integer priority;

    public Tasks(String title, String desc, String dueDate, Integer priority) {
        this.title = title;
        this.desc = desc;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public Tasks() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                '}';
    }
}
