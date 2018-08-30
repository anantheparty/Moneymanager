package dao.inter;

import java.util.List;

import bean.AccountItem;

public interface AccountItemDaoInter {
	double getOrderId(double preOrderId);
	public void add(AccountItem item);
	public void delete(int id);
	public void modify(AccountItem item);
	public List<AccountItem> find(List<Integer> categoryId, String frDate, String toDate);
}
