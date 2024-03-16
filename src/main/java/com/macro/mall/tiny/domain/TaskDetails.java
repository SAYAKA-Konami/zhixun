package com.macro.mall.tiny.domain;

import cn.hutool.core.date.DateTime;
import com.macro.mall.tiny.modules.task.model.Task;
import com.macro.mall.tiny.modules.task.model.TaskCustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class TaskDetails {
    /**
     * 定义任务状态类
     */
    @AllArgsConstructor
    @Getter
    public enum State {
        NOT_STARTED(0), PAUSE(1), IN_PROGRESS(2), COMPLETED(-1), FORCED_END(-2);
        private final int value;
    }

    private final Task task;

    public TaskDetails(Task task) {
        this.task = task;
    }

    /**
     * 初始化一个默认的task
     */
    public static Task initialTask(long userId){
        Task task = new Task();
        task.setState(State.NOT_STARTED.getValue())
                .setParentId(-1L)
                .setSuccess(0)
                .setFailed(0)
                .setMiss(0)
                .setCreateBy(userId)
                .setName(DateTime.now().toDateStr());
        return task;
    }

    public List<TaskCustomer> buildRelation(List<Long> customerIds){
        return customerIds.stream()
                .map(customerId -> {
                    TaskCustomer taskCustomer = new TaskCustomer();
                    taskCustomer.setCustomerId(customerId);
                    taskCustomer.setTaskId(task.getId());
                    return taskCustomer;
                }).toList();
    }

    public void transfer(State nextState){
        if (task.getState() == nextState.getValue()) {
            return;
        }
        task.setState(nextState.getValue());
    }

}
