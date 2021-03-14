package per.maingame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDb {
	
	private int id;
	private String tableName;
	
	private int[] num;
	private int[] enviorment;
	
	static final String DB_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/human_society";
	static final String USER = "root";
	static final String PASS = "";
	
	/**
	 * 创建表，记录一次数据
	 */
	public void creatTable() {
		
		Connection conn=null;
		Statement stmt=null;
		
		try {
			Class.forName(DB_DRIVER);
			
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			//更新总表
			String sql1="insert into main values("+id+",'"+tableName+"')";

			stmt.executeUpdate(sql1);
			
			//创建数据表			
			String sql2 = "CREATE TABLE "+tableName +"(N int,peopleNum int,enviormentNum int)";
			stmt.executeUpdate(sql2);
			
			// 完成后关闭
            stmt.close();
            conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 更新数据
	 * @return
	 */
	public void upData() {
		
		Connection conn=null;
		Statement stmt=null;
		
		try {
			Class.forName(DB_DRIVER);
			
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			for(int i=0;i<num.length;i++) {
				int a=i+1;
				String sql = "insert into "+tableName+" values("+a+","+num[i]+","+enviorment[i]+")";
				stmt.executeUpdate(sql);
			}
			
			// 完成后关闭
            stmt.close();
            conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 导出数据，并绘制折线图
	 */
	public void export() {
		
		String tablename = null;
		ChartTest chartt=new ChartTest();
		Connection conn=null;
		Statement stmt=null;
		ArrayList<String> list=new ArrayList<String>();////////////////////////
		ArrayList<String> list2=new ArrayList<String>();
		ArrayList<String> list3=new ArrayList<String>();
		ArrayList<String> list4=new ArrayList<String>();
		
		try {
			Class.forName(DB_DRIVER);
				
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			String sql="select * from t1224000857";
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				list.add(rs.getString(2));
				list2.add(rs.getString(3));
			}
			if(list!=null && list.size()>0) {
				
				int[] peopleN=new int[list.size()];
				int[] enviormentN=new int[list2.size()];
				
				for(int i=0;i<list.size();i++) {
					peopleN[i]=Integer.parseInt(list.get(i));
					enviormentN[i]=Integer.parseInt(list2.get(i));
				}
				
				chartt.setPeopleNum(peopleN);
				chartt.setEnviorment(enviormentN);
			}
			
			//传入比对数据
			String sql2="select * from main where id=(select MAX(id) from main )";
			ResultSet rs2 = stmt.executeQuery(sql2);
			while(rs2.next()) {
				
				tablename = rs2.getString(2);
			}
			
			String sql3 = "select * from "+tablename;
			ResultSet rs3 = stmt.executeQuery(sql3);
			
			while(rs3.next()) {
				list3.add(rs3.getString(2));
				list4.add(rs3.getString(3));
			}
			if(list3!=null && list3.size()>0) {
				
				int[] peopleN2=new int[list3.size()];
				int[] enviormentN2=new int[list4.size()];
				
				for(int i=0;i<list3.size();i++) {
					peopleN2[i]=Integer.parseInt(list3.get(i));
					enviormentN2[i]=Integer.parseInt(list4.get(i));
				}
				
				chartt.setPeopleNum2(peopleN2);
				chartt.setEnviorment2(enviormentN2);
			}
			
			//开始绘制折线图！！
			chartt.paint();
			// 完成后关闭
            stmt.close();
            conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int[] getNum() {
		return num;
	}

	public void setNum(int[] num) {
		this.num = num;
	}

	public int[] getEnviorment() {
		return enviorment;
	}

	public void setEnviorment(int[] enviorment) {
		this.enviorment = enviorment;
	}

}
