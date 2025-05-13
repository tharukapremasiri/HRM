/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

/**
 *
 * @author Ashane
 */
public class Department {

    private String deptID;
    private String deptName;
    private String phoneNo;
    private String email;
    private String des;
    
    public String getDeptID() {
        return deptID;
    }

    /**
     * @param deptID the deptID to set
     */
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    /**
     * @return the deptName
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName the deptName to set
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the des
     */
    public String getDes() {
        return des;
    }

    /**
     * @param des the des to set
     */
    public void setDes(String des) {
        this.des = des;
    }
    
    public void addDepartment(Department dept)
    {
        String deptString = dept.getDeptID()+","+ dept.deptName+","+dept.getPhoneNo()+","+
        dept.getEmail();
    
        DBConnection db = new DBConnection();
        db.writeToDB(deptString,"Department.txt");
    
    
    }
    
    public void addDesignation(Department des)
    {
        String desString = des.deptName+","+des.getDes();
    
        DBConnection db = new DBConnection();
        db.writeToDB(desString,"Designation.txt");
    
    
    }
    
    
}
