/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author TNA
 */
public class ProductDAO extends DBContext {

    public void insertProduct(Product pro) {
        try {
            String str = "insert into Products([ProductName]\n"
                    + "      ,[CategoryID]\n"
                    + "      ,[QuantityPerUnit]\n"
                    + "      ,[UnitPrice]\n"
                    + "      ,[UnitsInStock]\n"
                    + "      ,[Discontinued]\n"
                    + "      ,[UserID]\n"
                    + "      ,[Description]\n"
                    + "      ,[Type]\n"
                    + "      ,[Images]\n"
                    + "      ,[SellQuantity])\n"
                    + "  values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, pro.getProductName());
            pstm.setInt(2, pro.getCategoryID());
            pstm.setString(3, pro.getQuantityPerUnit());
            pstm.setDouble(4, pro.getUnitPrice());
            pstm.setInt(5, pro.getUnitInStock());
            pstm.setBoolean(6, pro.isDiscontinued());
            pstm.setInt(7, pro.getUserID());
            pstm.setString(8, pro.getDescription());
            pstm.setString(9, String.join(";", pro.getType()));
            pstm.setString(10, String.join(";", pro.getImages()));
            pstm.setInt(11, pro.getSellQuantity());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Product> getListProduct() {
        ArrayList<Product> list = new ArrayList<>();

        try {
            String str = "select * from Products";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductID(rs.getInt("ProductID"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setCategoryID(rs.getInt("CategoryID"));
                pro.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                pro.setUnitPrice(rs.getDouble("UnitPrice"));
                pro.setUnitInStock(rs.getInt("UnitsInStock"));
                pro.setDiscontinued(rs.getBoolean("Discontinued"));
                pro.setUserID(rs.getInt("UserID"));
                pro.setDescription(rs.getString("Description"));
                pro.setType(rs.getString("Type"));
                pro.setImages(rs.getString("Images"));
                pro.setSellQuantity(rs.getInt("SellQuantity"));

                list.add(pro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public ArrayList<Product> getListNewProduct() {
        ArrayList<Product> list = new ArrayList<>();

        try {
            String str = "select * from Products order by ProductID desc";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductID(rs.getInt("ProductID"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setCategoryID(rs.getInt("CategoryID"));
                pro.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                pro.setUnitPrice(rs.getDouble("UnitPrice"));
                pro.setUnitInStock(rs.getInt("UnitsInStock"));
                pro.setDiscontinued(rs.getBoolean("Discontinued"));
                pro.setUserID(rs.getInt("UserID"));
                pro.setDescription(rs.getString("Description"));
                pro.setType(rs.getString("Type"));
                pro.setImages(rs.getString("Images"));
                pro.setSellQuantity(rs.getInt("SellQuantity"));

                list.add(pro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public ArrayList<Product> getListFeaturedProduct() {
        ArrayList<Product> list = new ArrayList<>();

        try {
            String str = "select * from Products order by SellQuantity desc";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductID(rs.getInt("ProductID"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setCategoryID(rs.getInt("CategoryID"));
                pro.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                pro.setUnitPrice(rs.getDouble("UnitPrice"));
                pro.setUnitInStock(rs.getInt("UnitsInStock"));
                pro.setDiscontinued(rs.getBoolean("Discontinued"));
                pro.setUserID(rs.getInt("UserID"));
                pro.setDescription(rs.getString("Description"));
                pro.setType(rs.getString("Type"));
                pro.setImages(rs.getString("Images"));

                list.add(pro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
    
    public ArrayList<Product> getListMostViewProduct() {
        ArrayList<Product> list = new ArrayList<>();

        try {
            String str = "select * from Products order by views desc";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductID(rs.getInt("ProductID"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setCategoryID(rs.getInt("CategoryID"));
                pro.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                pro.setUnitPrice(rs.getDouble("UnitPrice"));
                pro.setUnitInStock(rs.getInt("UnitsInStock"));
                pro.setDiscontinued(rs.getBoolean("Discontinued"));
                pro.setUserID(rs.getInt("UserID"));
                pro.setDescription(rs.getString("Description"));
                pro.setType(rs.getString("Type"));
                pro.setImages(rs.getString("Images"));
                pro.setViews(rs.getInt("views"));

                list.add(pro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public ArrayList<Product> getListByPage(ArrayList<Product> list, int begin, int end) {
        ArrayList<Product> myList = new ArrayList<>();
        int myEnd = Math.min(end, list.size());
        for (int i = begin; i < myEnd; i++) {
            myList.add(list.get(i));
        }
        return myList;
    }

    public Product getProductByID(String id) {

        try {
            String str = "select * from Products\n"
                    + "where ProductID=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductID(rs.getInt("ProductID"));
                pro.setProductName(rs.getString("ProductName"));
                pro.setCategoryID(rs.getInt("CategoryID"));
                pro.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                pro.setUnitPrice(rs.getDouble("UnitPrice"));
                pro.setUnitInStock(rs.getInt("UnitsInStock"));
                pro.setDiscontinued(rs.getBoolean("Discontinued"));
                pro.setUserID(rs.getInt("UserID"));
                pro.setDescription(rs.getString("Description"));
                pro.setType(rs.getString("Type"));
                pro.setImages(rs.getString("Images"));
                pro.setSellQuantity(rs.getInt("SellQuantity"));
                pro.setViews(rs.getInt("views"));

                return pro;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateProduct(Product pro) {
        try {
            String str = "update Products\n"
                    + "set ProductName=?, CategoryID=?, QuantityPerUnit=?\n"
                    + ", UnitPrice=?, UnitsInStock=?, Discontinued=?, UserID=?, [Description]=?\n"
                    + ", [Type]=?, Images=?, SellQuantity=?\n"
                    + "where ProductID=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, pro.getProductName());
            pstm.setInt(2, pro.getCategoryID());
            pstm.setString(3, pro.getQuantityPerUnit());
            pstm.setDouble(4, pro.getUnitPrice());
            pstm.setInt(5, pro.getUnitInStock());
            pstm.setBoolean(6, pro.isDiscontinued());
            pstm.setInt(7, pro.getUserID());
            pstm.setString(8, pro.getDescription());
            pstm.setString(9, String.join(";", pro.getType()));
            pstm.setString(10, String.join(";", pro.getImages()));
            pstm.setInt(11, pro.getSellQuantity());
            pstm.setInt(12, pro.getProductID());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("update product: " + e.getMessage());
        }
    }
    
    public void updateProduct(int id, int views) {
        try {
            String str = "update Products\n"
                    + "set views=?\n"
                    + "where ProductID=?";
            PreparedStatement pstm = connection.prepareStatement(str);            
            pstm.setInt(1, views);
            pstm.setInt(2, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("update product: " + e.getMessage());
        }
    }

    public void deleteProductByID(String id) {
        try {
            String str = "delete from Products\n"
                    + "where ProductID=?";
            PreparedStatement pstm = connection.prepareStatement(str);
            pstm.setString(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete Product: " + e.getMessage());
        }
    }
}
