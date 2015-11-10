# Java Tester简介 #

jester 0.8.2版本发布，jar包下载http://java-tester.googlecode.com/svn/maven2/org/jtester/jtester/0.9.5/jtester-0.9.5.jar<br />
0.9.5 使用jmockit来mock接口，打破了jmock的种种限制。
> 提供了新功能，@AutoBeanInject，不用配置spring文件，自动注入需要测试的bean。
0.8.2使用了dbfit来准备数据和比较数据库，比使用dbfit更简单方便，功能更强大。<br />
jar包下载地址http://code.google.com/p/java-tester/wiki/DownloadJar


1、在unitils的基础，集成了jmock功能。<br />
2、在hamcrest断言的基础上，实现了fluent interface断言。<br />
3、改造了jmock expectation参数断言为fluent interface形式<br />
4、提供了将普通的pojo对象序列化到文件，然后再从文件中反序列化回来的功能，用于在对象复杂的情况下，直接录制接口（远程接口）调用返回的对象，以供下次测试或调试使用。<br />
5、使用wiki代替xml来准备测试数据。<br />
6、实现了更加丰富的断言。<br />
7、提供了hibernate annotation环境下，直接使用内存数据库进行db测试。<br />
8、提供了hibernate annotation环境下，Open Test in Session的实现。<br />


maven使用
声明远程仓库
```
<repositories>
<repository>
	<id>jtester-maven</id>
	<name>JTester</name>
	<url>http://java-tester.googlecode.com/svn/maven2/</url>
</repository>
</repositories>
```
声明依赖
```
<dependency>
	<groupId>org.jtester</groupId>
	<artifactId>jtester</artifactId>
	<version>0.8.2</version>
</dependency>
```

使用指南http://code.google.com/p/java-tester/w/list

未来有可能实现的功能<br />
1、部分mock，当一个接口有多个方法时，可以只mock其中指定的方法，未指定的可以使用实现类的实现。<br />
2、使用wiki准备数据时可以使用变量，这样便于时间之类的数据可以动态设定。