#jtester 使用指南.

JMock的使用

断言的使用
```
   先继承接口org.jtester.core.JTesterAssert
   例子：want.string("this is a example").contains("is").contains("example");
   断言都是以want语法开头,后面可以对具体的变量实例进行断言，目前支持的类型包括6个java基本类型,collection,数组，map，以及object对象。
```
```
List<String> list = new ArrayList<String>();
list.add("test.hello.one");
list.add("test.hello.two");
list.add("test.hello.three");
want.collection(list).sizeIs(3).allItemMatch("test.*", ".*hello.*").hasItemMatch(".*three");

want.array(new String[] { "aaaa", "bbbb" }).hasItems("aaaa", "bbbb");
want.array(new int[] { 1, 2 }).hasItems(1);
want.array(new double[] { 1, 2.0d }).hasItems(1d);

want.array(new int[] { 1, 2, 3 }).hasItems(Arrays.asList(1, 3));
```

`对对象属性的断言`
```
// 断言user的id属性的值是1
want.object(user).propertyEq("id", 1).propertyEq("address.street", "First street");
want.collection(users).propertyEq("id", Arrays.asList(1, 2, 3)).propertyEq("address.street",
				Arrays.asList("First street", "Second street", "Third street"));
```