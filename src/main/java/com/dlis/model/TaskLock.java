package com.dlis.model;

import java.time.LocalDateTime;

/**
 * Created by DLiszewski on 2017-02-25.
 */
public class TaskLock {
    private String lockOwner;
    private LocalDateTime lockDate;

    public TaskLock(String lockOwner) {
        this.lockOwner = lockOwner;
    }

    public String getLockOwner() {
        return lockOwner;
    }

    public void setLockOwner(String lockOwner) {
        this.lockOwner = lockOwner;
    }

    public LocalDateTime getLockDate() {
        return lockDate;
    }

    public void setLockDate(LocalDateTime lockDate) {
        this.lockDate = lockDate;
    }
}
