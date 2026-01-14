package com.example.Spring_Project.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@FieldNameConstants
@MappedSuperclass
public abstract class AuditField {
    @CreationTimestamp
    @Column(name = "created_time", updatable = false)
    protected Date createdTime;

    @UpdateTimestamp
    @Column(name = "updated_time", insertable = false)
    protected Date updatedTime;
}
