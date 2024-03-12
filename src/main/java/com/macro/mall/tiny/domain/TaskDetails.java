package com.macro.mall.tiny.domain;

import com.macro.mall.tiny.modules.task.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    public void transfer(State nextState){
        if (task.getState() == nextState.getValue()) {
            return;
        }
        task.setState(nextState.getValue());
    }

}
