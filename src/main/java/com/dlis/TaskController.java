package com.dlis;

import com.dlis.model.Task;
import com.dlis.model.TaskStatus;
import com.dlis.model.TaskType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * Created by DLiszewski on 2017-02-25.
 */
@Controller
public class TaskController {

    private static final Log log = LogFactory.getLog(TaskController.class);
    private int taskN = 0;

    @Autowired
    private TaskService taskService;

    @ResponseBody
    @RequestMapping(value = "/putTask", method = RequestMethod.GET)
    public String putTask() {
        taskService.putTask(new Task("test data2", TaskType.SIMPLE_TASK));
        return "test";
    }

    @ResponseBody
    @RequestMapping(value = "/getTask", method = RequestMethod.GET)
    public Task getTask() {
        Task task = taskService.getTaskForProcessing();
        task.setNumberOfTries(task.getNumberOfTries()+1);
        task.setLastTryDate(LocalDateTime.now());
        task.setData("new data after processing task");
        task.setTaskStatus(TaskStatus.COMPLETED);
        task.resetLockInfo();
        taskService.saveTask(task);
        return task;
    }

    @Scheduled(fixedDelay = 5000)
    public void handleTask(){
        Task task = taskService.getTaskForProcessing();
        log.info("loaded task" + task);
        task.setNumberOfTries(task.getNumberOfTries()+1);
        task.setLastTryDate(LocalDateTime.now());
        task.setData("new data after processing task");
        task.setTaskStatus(TaskStatus.COMPLETED);
        task.resetLockInfo();
        log.info("updated task" + task);
        taskService.saveTask(task);

    }

    @Scheduled(fixedDelay = 4000)
    public void addTask(){
        taskN++;
        Task task = new Task("test data2 - test " + taskN, TaskType.SIMPLE_TASK);
        log.info(task);
        taskService.putTask(task);
    }
}
