/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author TNA
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Category> getCategoryList() {
        ArrayList<Category> list = new ArrayList<>();

        try {
            String str = "select * from Categories";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Category cate = new Category();
                cate.setCategoryID(rs.getInt("CategoryID"));
                cate.setCategoryName(rs.getString("CategoryName"));
                cate.setDescription(rs.getString("Description"));
                list.add(cate);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public Category getCategoryByID(ArrayList<Category> list, int id) {
        for (Category cate : list) {
            if (cate.getCategoryID() == id) {
                return cate;
            }
        }
        return null;
    }

    public ArrayList<Category> getListByPage(ArrayList<Category> list, int begin, int end) {
        ArrayList<Category> myList = new ArrayList<>();
        int myEnd = Math.min(end, list.size());
        for (int i = begin; i < myEnd; i++) {
            myList.add(list.get(i));
        }
        return myList;
    }

    public void insertCategory(String name, String desc) {
        try {
            String str = "insert into Categories([CategoryName], [Description])\n"
                    + "values (?, ?)";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, name);
            pstm.setString(2, desc);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Insert Category: " + e.getMessage());
        }
    }

    public void updateCategory(String id, String name, String desc) {
        try {
            String str = "update Categories\n"
                    + "set CategoryName=?, [Description]=?\n"
                    + "where CategoryID=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, name);
            pstm.setString(2, desc);
            pstm.setString(3, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update Category: " + e.getMessage());
        }
    }

    public void deleteCategory(String id) {
        try {
            String str = "delete from Categories\n"
                    + "where CategoryID=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, id);            
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete Category: " + e.getMessage());
        }
    }
}
