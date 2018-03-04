/**
 *
 */
package pl.inferno.security.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author lukasz-adm
 *
 */
@Entity
@Table(schema = "inferno_authorization_schema", name = "inferno_address")
public class Address extends InfernoAbstractAuditableEntity {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1670157285186767972L;

	@Id
	@SequenceGenerator(name = "address_seq_gen", sequenceName = "inferno_address_seq", allocationSize = 1)
	@GeneratedValue(generator = "address_seq_gen")
	@Column(name = "address_id")
	private Long id;

	@Column
	private String street;

	@Column(name = "building_nr")
	private String buildingNumber;

	@Column
	private String appartment;

	@Column(name = "post_code")
	private String postCode;

	@Column
	private String city;

	@Column
	private String district;

	@Enumerated(EnumType.STRING)
	private AddressType type;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the buildingNumber
	 */
	public String getBuildingNumber() {
		return buildingNumber;
	}

	/**
	 * @param buildingNumber
	 *            the buildingNumber to set
	 */
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	/**
	 * @return the appartment
	 */
	public String getAppartment() {
		return appartment;
	}

	/**
	 * @param appartment
	 *            the appartment to set
	 */
	public void setAppartment(String appartment) {
		this.appartment = appartment;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the type
	 */
	public AddressType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(AddressType type) {
		this.type = type;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
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
		result = (prime * result) + ((appartment == null) ? 0 : appartment.hashCode());
		result = (prime * result) + ((buildingNumber == null) ? 0 : buildingNumber.hashCode());
		result = (prime * result) + ((city == null) ? 0 : city.hashCode());
		result = (prime * result) + ((district == null) ? 0 : district.hashCode());
		result = (prime * result) + ((id == null) ? 0 : id.hashCode());
		// result = (prime * result) + ((person == null) ? 0 : person.hashCode());
		result = (prime * result) + ((postCode == null) ? 0 : postCode.hashCode());
		result = (prime * result) + ((street == null) ? 0 : street.hashCode());
		result = (prime * result) + ((type == null) ? 0 : type.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Address other = (Address) obj;
		if (appartment == null) {
			if (other.appartment != null) {
				return false;
			}
		} else if (!appartment.equals(other.appartment)) {
			return false;
		}
		if (buildingNumber == null) {
			if (other.buildingNumber != null) {
				return false;
			}
		} else if (!buildingNumber.equals(other.buildingNumber)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (district == null) {
			if (other.district != null) {
				return false;
			}
		} else if (!district.equals(other.district)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		// if (person == null) {
		// if (other.person != null) {
		// return false;
		// }
		// } else if (!person.equals(other.person)) {
		// return false;
		// }
		if (postCode == null) {
			if (other.postCode != null) {
				return false;
			}
		} else if (!postCode.equals(other.postCode)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (type != other.type) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("Address [");
		if (id != null) {
			builder.append("id=").append(id).append(", ");
		}
		if (street != null) {
			builder.append("street=").append(street).append(", ");
		}
		if (buildingNumber != null) {
			builder.append("buildingNumber=").append(buildingNumber).append(", ");
		}
		if (appartment != null) {
			builder.append("appartment=").append(appartment).append(", ");
		}
		if (postCode != null) {
			builder.append("postCode=").append(postCode).append(", ");
		}
		if (city != null) {
			builder.append("city=").append(city).append(", ");
		}
		if (district != null) {
			builder.append("district=").append(district).append(", ");
		}
		if (type != null) {
			builder.append("type=").append(type).append(", ");
		}
		// if (person != null) {
		// builder.append("person=").append(person.getFirstName() + " " +
		// person.getLastName());
		// }
		builder.append("]");
		return builder.toString();
	}

}
