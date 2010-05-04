package org.jtester.tutorial.debugit;

import java.util.List;

import org.jtester.tutorial.beans.PhoneGroup;
import org.jtester.tutorial.beans.PhoneItem;
import org.jtester.tutorial.daos.PhoneGroupDao;
import org.jtester.tutorial.daos.PhoneItemDao;
import org.jtester.tutorial.services.PhoneBookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DebugIt {
	public static void main(String[] args) {
		// 初始化环境
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"spring/data-source.xml", "spring/beans.xml" });
		// 准备数据
		PhoneGroupDao phoneGroupDao = (PhoneGroupDao) context.getBean("phoneGroupDao");
		long groupId = phoneGroupDao.insertPhoneGroup(new PhoneGroup("classmate"));
		PhoneItemDao phoneItemDao = (PhoneItemDao) context.getBean("phoneItemDao");
		long phoneId1 = phoneItemDao.insertPhoneItem(new PhoneItem("darui.wu", "15900001111"));
		phoneGroupDao.addPhoneItemToGroup(phoneId1, groupId);

		long phoneId2 = phoneItemDao.insertPhoneItem(new PhoneItem("jobs.he", "13900001111"));
		phoneGroupDao.addPhoneItemToGroup(phoneId2, groupId);

		// 开始测试
		PhoneBookService phoneBookService = (PhoneBookService) context.getBean("phoneBookService");
		List<PhoneItem> items = phoneBookService.findPhoneItemsByGroupName("classmate");

		// 将消息打印出来肉眼验证
		System.out.println(items.size());
		for (PhoneItem item : items) {
			System.out.println("user name:" + item.getUsername() + ", mobile:" + item.getMobile());
		}
	}
}
