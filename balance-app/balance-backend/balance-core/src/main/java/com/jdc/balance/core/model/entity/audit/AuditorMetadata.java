package com.jdc.balance.core.model.entity.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditorMetadata extends AuditTimeMetadata {
	
	private static final long serialVersionUID = 1L;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String modifiedBy;

}
