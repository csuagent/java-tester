如何使用JMock

只需用简单的在需要mock的对象前面加@Mock，JTester会自动帮助你创建一个mock对象。
```
@Mock
private CalledService calledService;
```
在测试方法中定义一个Je （org.jtester.jmock.JTesterExpectations）的匿名类，模拟被调用的方法，以及返回值等
```
@Test
public void register_SinglThread() {
	checking(new Je() {
	{
		will.call.one(calledService).called(the.string().contains("test").wanted());
		will.returns.value("dddd");
		will.call.ignoring(calledService).called(the.string().any().wanted());
		will.returns.value("dddd");
	}
	});
	callingService.call("i am a test message!");
}
```
更详细的用法请参考[AssertSyntax](AssertSyntax.md) 以及 <br />
jmock官方文档 http://www.jmock.org/cookbook.html <br />
jmock中文翻译 http://thinking.5ming.org.cn/2008/11/23/jmock-cookbook/<br />
unitils mock文档 http://www.unitils.org/cookbook.html