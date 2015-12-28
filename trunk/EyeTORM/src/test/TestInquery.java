package test;

import java.util.Iterator;
import java.util.List;

import com.eyet.framework.orm.Inquery;

public class TestInquery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Inquery query = new Inquery();
		query.setString("id", "123");
		query.setString("name", "abc");
		query.setOR();
		query.setOR();
		query.setString("class", "obj");
		query.setInteger("uid", 123342);
		query.setDouble("price", 11.11);
		
		System.out.println(query.getPrepareWhere());
		List<Object> list = query.getPrepareValues();
		Iterator<Object> it = list.iterator();
		while(it.hasNext()){
			Object obj = it.next();
			if(obj instanceof Integer){
				System.out.println("is int:"+obj);
			}else if(obj instanceof Double){
				System.out.println("is double:"+obj);
			}
		}
		
		

	}

}
