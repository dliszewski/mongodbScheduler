package com.dlis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by DLiszewski on 2017-02-25.
 */
@Document
public class Task {

    @Id
    private String id;
    private String data;
    private LocalDateTime creationDate;
    private LocalDateTime lastTryDate;
    private int numberOfTries;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private TaskLock taskLock;
    @Version
    private Long version;

    public Task() {
    }

    public Task(String data, TaskType taskType) {
        this.data = data;
        this.taskType = taskType;
        creationDate = LocalDateTime.now();
        numberOfTries = 0;
        taskStatus = TaskStatus.IDLE;
    }

    public Task(String data, TaskType taskType, String ownerLock) {
        this(data, taskType);
        this.taskLock = new TaskLock(ownerLock);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastTryDate() {
        return lastTryDate;
    }

    public void setLastTryDate(LocalDateTime lastTryDate) {
        this.lastTryDate = lastTryDate;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskLock getTaskLock() {
        return taskLock;
    }

    public void setTaskLock(TaskLock taskLock) {
        this.taskLock = taskLock;
    }

    public void resetLockInfo() {
        taskLock.setLockDate(null);
        taskLock.setLockOwner(null);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", numberOfTries=" + numberOfTries +
                ", taskType=" + taskType +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
