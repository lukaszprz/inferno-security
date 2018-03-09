/**
 * Project: inferno-security-interfaces-rest
 * File: LoginTemplate.java
 * Package: pl.inferno.security.interfaces.rest.utils
 * Location:
 * 8 mar 2018 16:53:38 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.proxy.dto;

/**
 * Class LoginTemplate
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class LoginTemplate {

    private String username;

    private String password;

    /**
     * @param username
     * @param password
     */
    public LoginTemplate(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     */
    public LoginTemplate() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((password == null) ? 0 : password.hashCode());
        result = (prime * result) + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
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
        if (!(obj instanceof LoginTemplate)) {
            return false;
        }
        LoginTemplate other = (LoginTemplate) obj;
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginTemplate [");
        if (username != null) {
            builder.append("username=").append(username).append(", ");
        }
        if (password != null) {
            builder.append("password=").append(password);
        }
        builder.append("]");
        return builder.toString();
    }

}
