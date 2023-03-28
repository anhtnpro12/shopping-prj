/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.User;

/**
 *
 * @author TNA
 */
public class AccountDAO extends DBContext {

    public boolean checkLogin(String email, String pass) {
        try {
            String str = "select * from Accounts\n"
                    + "where Email=? and Password=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, email);
            pstm.setString(2, pass);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Account getAccountByEmail(String email) {
        try {
            String str = "select acc.*, u.[Name], u.Gender, u.BirthDate, u.CreateDate, u.[Address]\n"
                    + "from Accounts acc, Users u\n"
                    + "where acc.Email=? and acc.UserID=u.UserID";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("AccountId"));
                acc.setEmail(rs.getString("Email"));
                acc.setPassword(rs.getString("Password"));
                acc.setPhone(rs.getString("Phone"));
                User user = new User(rs.getInt("UserID"), rs.getString("Name"), rs.getString("Gender"),
                        rs.getDate("BirthDate"), rs.getDate("CreateDate"), rs.getString("Address"), rs.getInt("AccountId"));
                acc.setUser(user);
                acc.setRole(rs.getInt("Role"));
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Account getAccountByID(String id) {
        try {
            String str = "select acc.*, u.[Name], u.Gender, u.BirthDate, u.CreateDate, u.[Address]\n"
                    + "from Accounts acc, Users u\n"
                    + "where acc.[AccountId]=? and acc.UserID=u.UserID";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("AccountId"));
                acc.setEmail(rs.getString("Email"));
                acc.setPassword(rs.getString("Password"));
                acc.setPhone(rs.getString("Phone"));
                User user = new User(rs.getInt("UserID"), rs.getString("Name"), rs.getString("Gender"),
                        rs.getDate("BirthDate"), rs.getDate("CreateDate"), rs.getString("Address"), rs.getInt("AccountId"));
                acc.setUser(user);
                acc.setRole(rs.getInt("Role"));
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean checkEmailExist(String email) {
        try {
            String str = "select * from Accounts\n"
                    + "where Email=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean checkPhoneExist(String phone) {
        try {
            String str = "select * from Accounts\n"
                    + "where Phone=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, phone);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void register(String name, String email, String phone, String pass) {
        try {
            String str = "insert into Accounts([Email], [Password], [Phone], [Role]) values (?, ?, ?, 2);\n"
                    + "insert into Users([Name], [CreateDate], [AccountId]) values (?, ?, \n"
                    + "(select MAX(AccountId) from Accounts));\n"
                    + "update Accounts set UserID=(select top(1)UserID from Users order by UserID desc) \n"
                    + "where AccountId=(select MAX(AccountId) from Accounts);";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, email);
            pstm.setString(2, pass);
            pstm.setString(3, phone);
            pstm.setString(4, name);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pstm.setString(5, sdf.format(date));

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Account> getAccountList() {
        ArrayList<Account> list = new ArrayList<>();

        try {
            String str = "select acc.*, u.[Name], u.Gender, u.BirthDate, u.CreateDate, u.[Address]\n"
                    + "from Accounts acc, Users u\n"
                    + "where acc.UserID=u.UserID";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        new User(rs.getInt(5), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getDate(10),
                                rs.getString(11), rs.getInt(1)), rs.getInt(6));

                list.add(acc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public ArrayList<Account> getListByPage(ArrayList<Account> list, int begin, int end) {
        ArrayList<Account> myList = new ArrayList<>();
        int myEnd = Math.min(end, list.size());
        for (int i = begin; i < myEnd; i++) {
            myList.add(list.get(i));
        }
        return myList;
    }

    public void updateAccount(String name, String email, String pass, String phone,
            String role, String gender, String dob, String address, String aid, String uid) {
        try {
            String str = "update Accounts\n"
                    + "set Email=?, [Password]=?, [Phone]=?, [Role]=?\n"
                    + "where [AccountId]=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, email);
            pstm.setString(2, pass);
            pstm.setString(3, phone);
            pstm.setString(4, role);
            pstm.setString(5, aid);
            pstm.executeUpdate();

            str = "update Users\n"
                    + "set [Name]=?, Gender=?, BirthDate=?, [Address]=?\n"
                    + "where UserID=?";
            pstm = connection.prepareStatement(str);
            pstm.setString(1, name);
            pstm.setString(2, gender);
            pstm.setString(3, dob);
            pstm.setString(4, address);
            pstm.setString(5, uid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update account: " + e.getMessage());
        }
    }

    public void deleteAccountByID(String aid, String uid) {        
        try {
            String str = "delete from Users\n"
                    + "where UserID=?\n"
                    + "delete from Accounts\n"
                    + "where AccountId=?";                    
            PreparedStatement pstm = connection.prepareStatement(str);            
            pstm.setString(1, uid);
            pstm.setString(2, aid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete account: " + e.getMessage());
        }
    }
}
