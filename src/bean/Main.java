package bean;

import service.impl.AccountItemServiceImpl;
import service.impl.CategoryServiceImpl;

@SuppressWarnings("unused")
public class Main {
	private static CategoryServiceImpl service =new CategoryServiceImpl();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String result = service.findAll();
		System.out.print(result);
	}

}
