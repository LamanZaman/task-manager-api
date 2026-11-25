package com.example.taskmanagment.specification;

import com.example.taskmanagment.entity.TaskEntity;
import com.example.taskmanagment.entity.enums.TaskPriority;
import com.example.taskmanagment.entity.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    private TaskSpecification() {
    }


    public static Specification<TaskEntity> isActive() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isTrue(root.get("status"));
        };
    }


    public static Specification<TaskEntity> hasTaskStatus(TaskStatus taskStatus) {
        return (root, query, criteriaBuilder) -> {
            if(taskStatus == null) return null;

            return criteriaBuilder.equal(root.get("taskStatus"), taskStatus);
        };
    }

    public static Specification<TaskEntity> hasTaskPriority(TaskPriority taskPriority) {
        return (root, query, criteriaBuilder) -> {
            if(taskPriority == null) return null;

            return criteriaBuilder.equal(root.get("taskPriority"), taskPriority);
        };
    }
}
