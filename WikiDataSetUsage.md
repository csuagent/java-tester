#使用wiki代替xml来准备测试数据

# Introduction #
0.7版本提供了wiki准备数据方式

dbUnit提供了xml方式来准备数据，Unitils对dbunit进行了包装，使用Annotation的方式来加载准备数据。但是xml文件看起来不直观，编辑起来不方便，JTester对此进行改造，使用wiki文件来准备数据。

简单示例<br />
wiki源文件
```
|table|jtester user|
|id|is deleted|
|1|false|

|table|jtester address|
|id|user_id|city|is deleted|
|1|1|city|false|
```
视图预览

http://java-tester.googlecode.com/svn/wiki/image/wiki-dataset.JPG

其效果相当于下列的xml文件
```
<?xml version='1.0' encoding='UTF-8'?>
<dataset>
    <jtester_user id="1" is_deleted="false"/>
    <jtester_address id="1" user_id="1" city="city" is_deleted="false"/>
</dataset>
```

wiki语法
```
|table|表名称|
|字段名称1|字段名称2|字段名称3|
|字段值|字段值|字段值|
|字段值|字段值|字段值|
|字段值|字段值|字段值|
```

java语法
```
@Test
@WikiDataSet( { "lazy address.wiki" })
public void getUser_LazyAddress() {
	User user = userService.getUser(1);
	want.object(user).notNull();

	want.object(user.getAddresses()).notNull();
	Collection<Address> addresses = user.getAddresses();
	want.object(addresses.size()).isEqualTo(1);
	for (Address address : addresses) {
		want.string(address.getCity()).contains("city");
	}
}
```

wiki插件安装 http://eclipsewiki.sourceforge.net/ ，也可以使用mylyn带的wikitext插件