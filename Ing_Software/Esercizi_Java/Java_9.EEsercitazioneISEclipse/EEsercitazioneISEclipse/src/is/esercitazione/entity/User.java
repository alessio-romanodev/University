/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.entity;

/**
 *
 * @author nonplay
 */
public class User {
    public static enum UserRole {
        SALES_EMPLOYEE, MARKETING_MANAGER, CLERK, STOREMAN, FORWARDING_AGENT
    };

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private UserRole userRole;
    private String username;
    private String password;
}
