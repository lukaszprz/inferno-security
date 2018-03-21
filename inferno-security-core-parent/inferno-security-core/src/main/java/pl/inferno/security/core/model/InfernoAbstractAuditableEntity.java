/**
 *
 */
package pl.inferno.security.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author lukasz-adm
 *
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class InfernoAbstractAuditableEntity implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 2370262178492809909L;

    // @Column(name = "created_by")
    // @CreatedBy
    // @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "created_by")
    private String createdBy;

    @JsonIgnore
    @Column(name = "created_date", nullable = false)
    // @CreatedDate
    // @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    // @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdDate = LocalDateTime.now();

    @JsonIgnore
    // @Column(name = "modified_by")
    // @LastModifiedBy
    // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_modified_by")
    private String lastModifiedBy;

    @JsonIgnore
    @Column(name = "last_modified_date")
    // @LastModifiedDate
    // @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    // @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
	return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public LocalDateTime getCreatedDate() {
	return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(LocalDateTime createdDate) {
	this.createdDate = createdDate;
    }

    /**
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
	return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy
     *            the lastModifiedBy to set
     */
    public void setLastModifiedBy(String lastModifiedBy) {
	this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return the lastModifiedDate
     */
    public LocalDateTime getLastModifiedDate() {
	return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate
     *            the lastModifiedDate to set
     */
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
	this.lastModifiedDate = lastModifiedDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = (prime * result) + ((createdBy == null) ? 0 : createdBy.hashCode());
	result = (prime * result) + ((createdDate == null) ? 0 : createdDate.hashCode());
	result = (prime * result) + ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
	result = (prime * result) + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!super.equals(obj)) {
	    return false;
	}
	if (!(obj instanceof InfernoAbstractAuditableEntity)) {
	    return false;
	}
	final InfernoAbstractAuditableEntity other = (InfernoAbstractAuditableEntity) obj;
	if (createdBy == null) {
	    if (other.createdBy != null) {
		return false;
	    }
	} else if (!createdBy.equals(other.createdBy)) {
	    return false;
	}
	if (createdDate == null) {
	    if (other.createdDate != null) {
		return false;
	    }
	} else if (!createdDate.equals(other.createdDate)) {
	    return false;
	}
	if (lastModifiedBy == null) {
	    if (other.lastModifiedBy != null) {
		return false;
	    }
	} else if (!lastModifiedBy.equals(other.lastModifiedBy)) {
	    return false;
	}
	if (lastModifiedDate == null) {
	    if (other.lastModifiedDate != null) {
		return false;
	    }
	} else if (!lastModifiedDate.equals(other.lastModifiedDate)) {
	    return false;
	}
	return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	final StringBuilder builder = new StringBuilder();
	builder.append("InfernoAbstractAuditableEntity [");
	if (createdBy != null) {
	    builder.append("createdBy=").append(createdBy).append(", ");
	}
	if (createdDate != null) {
	    builder.append("createdDate=").append(createdDate).append(", ");
	}
	if (lastModifiedBy != null) {
	    builder.append("lastModifiedBy=").append(lastModifiedBy).append(", ");
	}
	if (lastModifiedDate != null) {
	    builder.append("lastModifiedDate=").append(lastModifiedDate);
	}
	builder.append("]");
	return builder.toString();
    }

}
