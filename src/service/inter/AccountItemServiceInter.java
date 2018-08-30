package service.inter;

import java.util.List;

import bean.AccountItem;

public interface AccountItemServiceInter {
	public void add(AccountItem item,String cName);
	public void delete(int id,String cName);
	public void modify(AccountItem item,String cName);
	public String getAccountList(List<String> category, String frDate, String toDate);
	public String getAccountListInfo(List<String> cName, String frDate, String toDate);
}
