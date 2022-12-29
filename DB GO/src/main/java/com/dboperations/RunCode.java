package com.dboperations;

import java.sql.*;
import java.util.ArrayList;

public class RunCode {

    public ArrayList runCode(String code, Connection c, String extraCode){
        ResultSet rs = null;
        ArrayList temp = new ArrayList<>();
        try {
            Statement st = c.createStatement();
            if(!extraCode.isEmpty()) {
            	//In this is condition use statement will be run.
            	st.execute(extraCode);
            }
            boolean isQuery = st.execute(code);
            
            if(isQuery){
                rs = st.getResultSet();
                
                temp.add(rs);
                temp.add("empty");
                return temp;
            }
            int rowsAffected = st.getUpdateCount();
            temp.add(rowsAffected);
        }
        catch(Exception e){
        	temp.add(e.getMessage());
        	//These two lines are just for increasing the length of the ArrayList used in CodeAreaServlet.
        	temp.add("empty-1");
        	temp.add("empty-2");
        	
        }
        return temp;
    }

    public ArrayList convertResultSet(ResultSet rs){
    	
    	ArrayList wholeWrapper = new ArrayList<>();
        ArrayList<ArrayList<String>> resultSetData = new ArrayList<ArrayList<String>>();
        int rowCount = 0;
        try {
            ResultSetMetaData rmd = rs.getMetaData();
            int columnCount = rmd.getColumnCount();
            int count = 0;
            
            ArrayList<String> tempArray = new ArrayList<String>();
            for(int i = 1;i <= columnCount;i++){
                tempArray.add(rmd.getColumnName(i));
            }
            resultSetData.add(tempArray);
            while(rs.next()){
            	rowCount ++;
                ArrayList<String> temp = new ArrayList<String>();
                for(int i = 1;i <= columnCount;i++){
                    temp.add(String.valueOf(rs.getObject(i)));
                }
                resultSetData.add(temp);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        wholeWrapper.add(resultSetData);
        wholeWrapper.add(rowCount);
        
        return wholeWrapper;
    }
}