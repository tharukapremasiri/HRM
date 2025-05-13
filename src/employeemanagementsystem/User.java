/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

/**
 *
 * @author Ashane
 */
public class User {

    private String userEmpID;
    private String userEmpName;
    private String userType;
    private String username;
    private String password;
    
    public String getUserEmpID() {
        return userEmpID;
    }

    /**
     * @param userEmpID the userEmpID to set
     */
    public void setUserEmpID(String userEmpID) {
        this.userEmpID = userEmpID;
    }

    /**
     * @return the userEmpName
     */
    public String getUserEmpName() {
        return userEmpName;
    }

    /**
     * @param userEmpName the userEmpName to set
     */
    public void setUserEmpName(String userEmpName) {
        this.userEmpName = userEmpName;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
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
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    public void addUser(User user)
    {
        String userString = user.getUserEmpID()+","+ user.getUserEmpName()+","+user.getUserType()+","+
        user.getUsername()+","+ user.getPassword();
    
        DBConnection db = new DBConnection();
        db.writeToDB(userString,"User.txt");
    
    
    }
    
    
}
