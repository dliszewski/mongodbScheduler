package com.dlis;

import com.dlis.model.Task;
import com.dlis.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by DLiszewski on 2017-02-25.
 */
@Service
public class TaskService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void putTask(Task task){
        mongoTemplate.save(task);
    }

    public void saveTask(Task task){
        mongoTemplate.save(task);
    }

    public Task getTaskForProcessing(){
        Query query = Query.query(where("taskStatus").is(TaskStatus.IDLE));
        Update update = new Update()
                .set("taskStatus", TaskStatus.IN_PROCESSING)
                .set("taskLock.lockOwner","mongoApp")
                .set("taskLock.lockDate", LocalDateTime.now());
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Task.class);
    }

}
