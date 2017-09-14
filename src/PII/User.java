package PII;
/**
 * An User.
 * @author group0724
 */
public abstract class User {
	/** This User's login name. */ 
    private String loginName;
    
    /** This User's password. */ 
    private String password;
    
    /** This User's name. */ 
    private String name;
    
    /** This User's ID. */ 
    private String ID;
    
    /**
     * Constructs a new User with login name from loginName, and password from
     * password.
     * @param loginName This User's login name.
     * @param password This User's password.
     */
    public User(String loginName, String password, String ID) {
        this.loginName = loginName;
        this.password = password;
        this.ID = ID;
    }
    
    /**
     * Returns this User's login name.
     * @return This User's login name.
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets the login name of this User to loginName.
     * @param loginName The new login name of this User.
     */
    public void setLoginName(String login) {
        loginName = login;
    }

    /**
     * Returns this User's password.
     * @return This User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this User to password.
     * @param password The new password of this User.
     */
    public void setPassword(String pass) {
        password = pass;
    }

    /**
     * Returns this User's name.
     * @return This User's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this User to name.
     * @param name The new name of this User.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Returns this User's ID.
     * @return This User's ID.
     */
    public String getID() {
        return ID;
    }

    /**
     * Sets the ID of this User to id.
     * @param id The new ID of this User.
     */
    public void setID(String id) {
        ID = id;
    }   

    /**
     * Changes the password of this User to password.
     * @param password The new password of this User.
     */
    public void changePassword(String password) {
        this.setPassword(password);
    }
    
}
