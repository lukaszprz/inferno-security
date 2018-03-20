/**
 * 
 */
package pl.inferno.security.form;

import java.util.List;

/**
 * Class SuccessfulAction
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class SuccessfullAction {

	private boolean success = false;

	private String objectName;

	private List<Change> changes;

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName
	 *            the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * @return the changes
	 */
	public List<Change> getChanges() {
		return changes;
	}

	/**
	 * @param changes
	 *            the changes to set
	 */
	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	public class Change {

		private String fieldName;

		private Object oldValue;

		private Object newValue;

		private String messageCode;

		/**
		 * @return the fieldName
		 */
		public String getFieldName() {
			return fieldName;
		}

		/**
		 * @param fieldName
		 *            the fieldName to set
		 */
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		/**
		 * @return the oldValue
		 */
		public Object getOldValue() {
			return oldValue;
		}

		/**
		 * @param oldValue
		 *            the oldValue to set
		 */
		public void setOldValue(Object oldValue) {
			this.oldValue = oldValue;
		}

		/**
		 * @return the newValue
		 */
		public Object getNewValue() {
			return newValue;
		}

		/**
		 * @param newValue
		 *            the newValue to set
		 */
		public void setNewValue(Object newValue) {
			this.newValue = newValue;
		}

		/**
		 * @return the messageCode
		 */
		public String getMessageCode() {
			return messageCode;
		}

		/**
		 * @param messageCode
		 *            the messageCode to set
		 */
		public void setMessageCode(String messageCode) {
			this.messageCode = messageCode;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Change [");
			if (fieldName != null) {
				builder.append("fieldName=").append(fieldName).append(", ");
			}
			if (oldValue != null) {
				builder.append("oldValue=").append(oldValue).append(", ");
			}
			if (newValue != null) {
				builder.append("newValue=").append(newValue).append(", ");
			}
			if (messageCode != null) {
				builder.append("messageCode=").append(messageCode);
			}
			builder.append("]");
			return builder.toString();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
			result = prime * result + ((messageCode == null) ? 0 : messageCode.hashCode());
			result = prime * result + ((newValue == null) ? 0 : newValue.hashCode());
			result = prime * result + ((oldValue == null) ? 0 : oldValue.hashCode());
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
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Change)) {
				return false;
			}
			Change other = (Change) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (fieldName == null) {
				if (other.fieldName != null) {
					return false;
				}
			} else if (!fieldName.equals(other.fieldName)) {
				return false;
			}
			if (messageCode == null) {
				if (other.messageCode != null) {
					return false;
				}
			} else if (!messageCode.equals(other.messageCode)) {
				return false;
			}
			if (newValue == null) {
				if (other.newValue != null) {
					return false;
				}
			} else if (!newValue.equals(other.newValue)) {
				return false;
			}
			if (oldValue == null) {
				if (other.oldValue != null) {
					return false;
				}
			} else if (!oldValue.equals(other.oldValue)) {
				return false;
			}
			return true;
		}

		private SuccessfullAction getOuterType() {
			return SuccessfullAction.this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + ((changes == null) ? 0 : changes.hashCode());
		result = prime * result + ((objectName == null) ? 0 : objectName.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SuccessfulAction [success=").append(success).append(", ");
		if (objectName != null) {
			builder.append("objectName=").append(objectName).append(", ");
		}
		if (changes != null) {
			builder.append("changes=").append(changes);
		}
		builder.append("]");
		return builder.toString();
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SuccessfullAction)) {
			return false;
		}
		SuccessfullAction other = (SuccessfullAction) obj;
		if (changes == null) {
			if (other.changes != null) {
				return false;
			}
		} else if (!changes.equals(other.changes)) {
			return false;
		}
		if (objectName == null) {
			if (other.objectName != null) {
				return false;
			}
		} else if (!objectName.equals(other.objectName)) {
			return false;
		}
		if (success != other.success) {
			return false;
		}
		return true;
	}
}
