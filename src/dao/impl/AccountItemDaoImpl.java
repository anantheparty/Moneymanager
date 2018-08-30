package dao.impl;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import dao.inter.AccountItemDaoInter;
import utils.JDBCUtils;
import bean.AccountItem;

public class AccountItemDaoImpl implements AccountItemDaoInter{
	
	public double getOrderId(double preOrderId){
		
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		double curOrderId = 250.25 ,nxtOrderId;
		
 	    
		try {
			con = JDBCUtils.getConnection();
 	    	String sql = "SELECT orderid FROM stonepage where orderid > ? LIMMIT 1";
			ps = con.prepareStatement(sql);
			ps.setDouble(1, preOrderId);
			rs = ps.executeQuery() ;
			if(rs.next()){
				nxtOrderId = rs.getDouble(1);
				curOrderId = (preOrderId+nxtOrderId)/2.0;
			} else {
				curOrderId = preOrderId+1024.0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(rs, ps, con);
		}
		return curOrderId; 
	}
	public void add(AccountItem item){
		Connection con = null;
		PreparedStatement ps= null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "INSERT INTO stonepage (date,name,details,categoryid,orderid,money) VALUES(?,?,?,?,?,?)";
    	    ps = con.prepareStatement(sql);
    	    
    	    ps.setString(1, item.getDate());
    	    ps.setString(2, item.getName());
    	    ps.setString(3, item.getDetails());
    	    ps.setInt(4, item.getCategoryid());
    	    ps.setDouble(5, item.getOrderid());
    	    ps.setDouble(6, item.getMoney());
    	    ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, ps, con);
		}
		return ;
	}
	public void delete(int id){
		Connection con = null;
		PreparedStatement ps= null;
		try {
			con = JDBCUtils.getConnection();
	 	    String sql = "DELETE FROM stonepage WHERE id= ?";
	 	    ps = con.prepareStatement(sql);
	 	    ps.setInt(1, id);
	 	    ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, ps, con);
		}
		return ;
	}
	public void modify(AccountItem item){
		Connection con = null;
		PreparedStatement ps= null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "UPDATE stonepage SET date=?,name=?,details=?,categoryid=?,orderid=?,money=? WHERE id=?";
    	    ps = con.prepareStatement(sql);
    	    
    	    ps.setString(1, item.getDate());
    	    ps.setString(2, item.getName());
    	    ps.setString(3, item.getDetails());
    	    ps.setInt(4, item.getCategoryid());
    	    ps.setDouble(5, item.getOrderid());
    	    ps.setDouble(6, item.getMoney());
    	    ps.setInt(7,item.getId());
    	    ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, ps, con);
		}
		return ;
	}
	public List<AccountItem> find(List<Integer> categoryId, String frDate, String toDate){
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		List<AccountItem> list = new LinkedList<>();
		try {
			con = JDBCUtils.getConnection();
			String sql = "SELECT * FROM stonepage where 1=1 ";
			int a=0,b=0;
			if(categoryId != null && categoryId.size() > 0) {
				sql+=" AND (1=2 ";
				for(int i = 0;i < categoryId.size();i++){
					sql+=" OR categoryid=? ";
				}
				sql+=")";
				a=categoryId.size();
			}
			if(frDate!=null) {
				sql+=" AND date >= ? ";
				b=1;
			}
			if(toDate!=null) {
				sql+=" AND date <= ? ";
			}
			sql+=" ORDER BY date ";
    	    ps = con.prepareStatement(sql);
    	    if(categoryId != null && categoryId.size() > 0){ 
    	    	for(int i = 0;i < categoryId.size();i++){
					ps.setInt(1+i , categoryId.get(i)); 
				}
    	    	
    	    }
			if(frDate!=null){  ps.setString(a+1, frDate);  }
			if(toDate!=null){  ps.setString(a+b+1, toDate);}
			rs = ps.executeQuery() ;
			while(rs.next()){
				AccountItem item = new AccountItem();
				item.setDate(rs.getString("date"));
				item.setName(rs.getString("name"));
				item.setDetails(rs.getString("details"));
				item.setCategoryid(rs.getInt("categoryid"));
				item.setOrderid(rs.getDouble("orderid"));
				item.setMoney(rs.getDouble("money"));
				item.setId(rs.getInt("id"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, ps, con);
		}
		return list;
	}
}
