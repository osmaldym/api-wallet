package com.osmy.wallet.entities.embeddables;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class Statuses {
    @JsonIgnore
    @Column(name="created_at", nullable=true)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @JsonIgnore
    @Column(name="updated_at", nullable=true)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt;

    @JsonIgnore
    @Column(name="deleted_at", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    Date deletedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
