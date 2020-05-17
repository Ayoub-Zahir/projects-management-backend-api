package com.management.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CollaboraterTaskPK implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "collaborater_id")
    private Integer collaboraterId;

    @Column(name = "task_id")
    private Integer taskId;

    // Constructors, getters, setters, hashcode and equals ------------>
    public CollaboraterTaskPK() {}

    public Integer getCollaboraterId() {
        return collaboraterId;
    }

    public void setCollaboraterId(Integer collaboraterId) {
        this.collaboraterId = collaboraterId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((collaboraterId == null) ? 0 : collaboraterId.hashCode());
        result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CollaboraterTaskPK other = (CollaboraterTaskPK) obj;
        if (collaboraterId == null) {
            if (other.collaboraterId != null)
                return false;
        } else if (!collaboraterId.equals(other.collaboraterId))
            return false;
        if (taskId == null) {
            if (other.taskId != null)
                return false;
        } else if (!taskId.equals(other.taskId))
            return false;
        return true;
    }

}