package com.clovi.app.base.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{

  @CreatedBy
  @Column(updatable = false)
  protected Long createBy;

  @LastModifiedBy
  protected Long lastModifiedBy;

  public boolean isNotCreatedBy(Long userId) {
      return userId.equals(createBy) == false;
    }
  public boolean isNotDeleted(){
    return this.getDeleted().equals(false);
  }
}
