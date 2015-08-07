package br.com.ibtechnology.cpsweb.model.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ibtechnology.cpsweb.model.enums.TransactionType;

@Entity
@Table(name = "log_trace")
@AttributeOverride(name = "id", column = @Column(name = "log_trace_id") )
public class LogTrace extends BaseEntities<Long> {

	private static final long serialVersionUID = 686153858473946L;

	@Column(name = "transaction_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@Column(name = "entity_name", nullable = false)
	private String entityName;

	@Column(name = "registry_id", nullable = false)
	private Long registryId;

	@Column(name = "operation_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date operationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "executed_by", nullable = false)
	private UserEntity executedBy;

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getRegistryId() {
		return registryId;
	}

	public void setRegistryId(Long registryId) {
		this.registryId = registryId;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public UserEntity getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(UserEntity executedBy) {
		this.executedBy = executedBy;
	}

	
}
