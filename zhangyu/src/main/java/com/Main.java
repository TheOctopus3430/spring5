package com;

import com.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(UserDao.class);
		context.refresh();
		System.out.println("---------源码测试-----");
		UserDao bean = context.getBean(UserDao.class);
		bean.setName("Tom");
		System.out.println(bean.getName());

	}
}
