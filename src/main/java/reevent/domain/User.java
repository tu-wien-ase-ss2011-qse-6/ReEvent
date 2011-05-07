package reevent.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.Date;

@Entity
public class User extends EntityBase {
    @Column(unique=true, nullable=false)
    String username;

    /**
     * Salted MD5 hash of the password.
     */
    String passwordHash;

	String firstName;
	
	String lastName;
	
	Date dayOfBirth;
    

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<UserRole> roles = Collections.singleton(UserRole.USER);

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
    

    public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
