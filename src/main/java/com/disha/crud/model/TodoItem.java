package com.disha.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "TodoItem")
public class TodoItem {

    @Transient
    public static final String SEQUENCE_NAME = "todo_sequence";

    @Id
    private long id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String description;

    private boolean status;
    private Date itemDate;
    private Time itemTime;

    public TodoItem() {
    }

    public TodoItem(long id, String description, boolean status) {
        this.id = id;
        this.description = description;
        status = status;
        itemDate = Date.valueOf(LocalDate.now());
        itemTime = Time.valueOf(LocalTime.now());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getItemDate() {
        return itemDate;
    } //push

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public Time getItemTime() {
        return itemTime;
    }

    public void setItemTime(Time itemTime) {
        this.itemTime = itemTime;
    }

    @Override
    public String toString(){
        return "Todo [id = "+ id + ", Title = "+ description +
                ", Date = "+ itemDate + ", Time = "+ itemTime + "]";
    }
}
