/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Question;

/**
 *
 * @author TNA
 */
public class QuestionDAO extends DBContext {
    public ArrayList<Question> getListQuestion() {
        ArrayList<Question> list = new ArrayList<>();
        
        try {
            String str = "select * from Question";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Question qu = new Question(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                        , rs.getString(5), rs.getString(6), rs.getString(7));
                
                list.add(qu);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
    
    public ArrayList<Question> getListByPage(ArrayList<Question> list, int begin, int end) {
        ArrayList<Question> myList = new ArrayList<>();
        int myEnd = Math.min(end, list.size());
        for (int i = begin; i < myEnd; i++) {
            myList.add(list.get(i));
        }
        return myList;
    }
    
    public int getScore(String ans) {
        String dbAns = "";
        int score = 0;
                
        try {
            String str = "select * from Question";
            PreparedStatement pstm = connection.prepareStatement(str);
            ResultSet rs = pstm.executeQuery();
            int i = 0;
            while (rs.next()) {                
                if ((ans.charAt(i)+"").equalsIgnoreCase(rs.getString(7))) {
                    score++;
                    i++;
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }                
        
        return score;
    }
}
