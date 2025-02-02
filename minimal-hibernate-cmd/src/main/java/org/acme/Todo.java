package org.acme;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id public long id;
    public String title;
    public String priority;

    public Todo() {}

    public Todo(long id, String title, String priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
