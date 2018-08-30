package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import dao.inter.CategoryDaoInter;

import utils.JDBCUtils;
import bean.Category;

public class CategoryDaoImpl implements CategoryDaoInter{
	public void add(String cName){
		Connection con = null;
		PreparedStatement ps= null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "INSERT INTO stonepagecategory (name) VALUES(?)";
    	    ps = con.prepareStatement(sql);
    	    ps.setString(1, cName);
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
	 	    String sql = "DELETE FROM stonepagecategory WHERE id= ?";
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
	public void modify(Category category){
		Connection con = null;
		PreparedStatement ps= null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "UPDATE stonepagecategory SET name=? WHERE id=?";
    	    ps = con.prepareStatement(sql);
    	    
    	    ps.setString(1, category.getName());
    	    ps.setInt(2,category.getId());
    	    ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, ps, con);
		}
		return ;		
	}
	public int getId(String categoryName){
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		int id=0;
		try {
			con = JDBCUtils.getConnection();
 	    	String sql = "SELECT id FROM stonepagecategory where name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, categoryName);
			rs = ps.executeQuery() ;
			if(rs.next()){
				id = rs.getInt(1);
			} else {
				id = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(rs, ps, con);
		}
		return id; 		
	}
	public String getName(int categoryId){
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		String name="";
		try {
			con = JDBCUtils.getConnection();
 	    	String sql = "SELECT name FROM stonepagecategory where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery() ;
			if(rs.next()){
				name = rs.getString(1);
			} else {
				name = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(rs, ps, con);
		}
		return name; 		
	}
	public List<Category> findAll(){
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		List<Category> list = new LinkedList<>();
		try {
			con = JDBCUtils.getConnection();
			String sql = "SELECT * FROM stonepagecategory";
    	    ps = con.prepareStatement(sql);
			rs = ps.executeQuery() ;
			while(rs.next()){
				Category category = new Category();
				category.setName(rs.getString("name"));
				category.setId(rs.getInt("id"));
				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(null, ps, con);
		}
		return list;
	}
}
