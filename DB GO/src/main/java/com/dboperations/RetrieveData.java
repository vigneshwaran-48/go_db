package com.dboperations;

import java.sql.*;

public class RetrieveData {
	
	public String[][] getTableData(Connection c, String dataBaseName, String tableName){
        ResultSet resultSet;
        String arr[][] = new String[0][0];

        try {
        	Statement statement = c.createStatement();
            Statement tempStatement = c.createStatement();
            resultSet = statement.executeQuery("select * from " + dataBaseName + "." +  tableName);
            ResultSetMetaData rmd = resultSet.getMetaData();

            ResultSet temp = tempStatement.executeQuery("select count(*) as rowCount from " + dataBaseName + "." +  tableName);
            int column = rmd.getColumnCount();
            int row = 1;

            while(temp.next()){
                row = temp.getInt("rowCount");
            }
            arr = new String[row+1][column];
            for(int i = 0;i < arr[0].length;i++){
                arr[0][i] = rmd.getColumnName(i+1);
                
            }
            int rowCount = 1;

            while(resultSet.next()){
                int col = 0;
                for(int i = 0;i < arr[rowCount].length;i++){
                    arr[rowCount][col] = String.valueOf(resultSet.getObject(i+1));
                    col ++;
                }
                rowCount ++;
            }
            rowCount = 0;
        }
        catch (Exception e) {
        	System.out.println("Error is here");
            e.printStackTrace();
        }
        return arr;
    }
}
