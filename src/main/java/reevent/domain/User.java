package reevent.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.Set;

@Entity
public class User extends EntityBase {
    String username;

    /**
     * Salted MD5 hash of the password.
     */
    String passwordHash;

    String realName;

    @ElementCollection
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
