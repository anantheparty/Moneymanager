package service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import dao.impl.CategoryDaoImpl;

import service.inter.CategoryServiceInter;

import bean.Category;

public class CategoryServiceImpl implements CategoryServiceInter{
	CategoryDaoImpl CDao=new CategoryDaoImpl();
	public void modify(Category category){
		return ;
	}
	public String findAll(){
		List<Category> list = CDao.findAll();
		String result = JSONArray.fromObject(list).toString();   
        return result;
	}
}
