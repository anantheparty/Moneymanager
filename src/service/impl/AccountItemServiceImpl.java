package service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import bean.AccountItem;
import bean.ChartData;
import dao.impl.*;
import service.inter.AccountItemServiceInter;


public class AccountItemServiceImpl implements AccountItemServiceInter{
	
	private AccountItemDaoImpl AIDao = new AccountItemDaoImpl();
	private CategoryDaoImpl CDao = new CategoryDaoImpl();
	
	public void add(AccountItem item,String cName){
		int categoryid = CDao.getId(cName);
		if( categoryid == 0){
			CDao.add(cName);
			categoryid=CDao.getId(cName);
		}
		item.setCategoryid(categoryid);
		AIDao.add(item);
		return ;
	}
	public void delete(int id,String cName){
		AIDao.delete(id);
		int categoryid = CDao.getId(cName);
		List<Integer> tmp = new LinkedList<Integer>() ;
		tmp.add(categoryid);
		List<AccountItem> tmp1= AIDao.find(tmp,null,null);
		if(tmp1 == null || tmp1.isEmpty()) 
			CDao.delete(categoryid);
	}
	public void modify(AccountItem item,String cName){
		int categoryid = CDao.getId(cName);
		if( categoryid == 0){
			CDao.add(cName);
			categoryid=CDao.getId(cName);
		}
		item.setCategoryid(categoryid);
		AIDao.modify(item);
		List<Integer> tmp = new LinkedList<Integer>() ;
		tmp.add(categoryid);
		List<AccountItem> tmp1= AIDao.find(tmp,null,null);
		if(tmp1 == null || tmp1.isEmpty()) 
			CDao.delete(categoryid);
	}
	private List<AccountItem> getAList(List<String> cName, String frDate, String toDate){
		List<Integer> cId = new LinkedList<Integer>();
		if(cName != null ){
			for( int i=0; i < cName.size(); i++){
				cId.add( CDao.getId(cName.get(i)));
			}
		}
		List<AccountItem> list = AIDao.find(cId, frDate, toDate);
		for( int i=0; i < list.size(); i++){
			AccountItem item = list.get(i);
			item.setCategoryName( CDao.getName(item.getCategoryid()));
			list.set(i, item);
		}
		return list;
	}
	public String getAccountList(List<String> cName, String frDate, String toDate){
		List<AccountItem> list = getAList(cName, frDate, toDate);
		long total = list.size();
		Map<String, Object> jsonMap = new HashMap<String, Object>();  
        jsonMap.put("recordsTotal", total);
        jsonMap.put("data", list); 
        String result = JSONObject.fromObject(jsonMap).toString();
		return result;
	}
	public String getAccountListInfo(List<String> cName, String frDate, String toDate){
		List<AccountItem> aList = getAList(cName, frDate, toDate);
		Map <String , Double> sum = new HashMap<String, Double>();
		for( int i=0; i < aList.size(); i++){
			String name = aList.get(i).getCategoryName();
			Double money = aList.get(i).getMoney();
			if( sum.containsKey(name) ) {
				sum.put(name, sum.get(name)+money);
			} else {
				sum.put(name, money);
			}
		}
		List<ChartData> cList = new LinkedList<ChartData>();
		Iterator<Map.Entry<String, Double>> iterator = sum.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Double> entry = iterator.next();
			ChartData c = new ChartData();
			c.setName(entry.getKey());
			c.setY(entry.getValue());
			cList.add(c);
		}
		String result = JSONArray.fromObject(cList).toString();   
		return result;
	}
	
}
