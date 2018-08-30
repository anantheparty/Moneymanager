package dao.inter;

import java.util.List;

import bean.Category;

public interface CategoryDaoInter {
	public void add(String cName);
	public void delete(int id);
	public void modify(Category category);
	public int getId(String categoryName);
	public String getName(int categoryId);
	public List<Category> findAll();
}
