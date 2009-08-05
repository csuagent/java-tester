package org.jtester.dbfit;

import java.io.Serializable;
import java.util.Date;

import fitnesse.wiki.PageData;
import fitnesse.wiki.WikiPage;
import fitnesse.wiki.WikiPageProperties;
import fitnesse.wikitext.WidgetBuilder;
import fitnesse.wikitext.widgets.ParentWidget;
import fitnesse.wikitext.widgets.WidgetRoot;

public class JTesterPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;
	private WikiPageProperties properties;

	public JTesterPage(String content) throws Exception {
		properties = new WikiPageProperties();
		this.initializeAttributes();
		this.content = content;
	}

	public JTesterPage(PageData data) throws Exception {
		this.content = data.getContent();
		properties = new WikiPageProperties(data.getProperties());
	}

	public String getStringOfAllAttributes() {
		return properties.toString();
	}

	public void initializeAttributes() {
		properties.setLastModificationTime(new Date());
		properties.set("Test");
	}

	public WikiPageProperties getProperties() throws Exception {
		return properties;
	}

	public String getAttribute(String key) throws Exception {
		return properties.get(key);
	}

	public void removeAttribute(String key) throws Exception {
		properties.remove(key);
	}

	public void setAttribute(String key, String value) throws Exception {
		properties.set(key, value);
	}

	public void setAttribute(String key) throws Exception {
		properties.set(key);
	}

	public boolean hasAttribute(String attribute) {
		return properties.has(attribute);
	}

	public void setProperties(WikiPageProperties properties) {
		this.properties = properties;
	}

	public String getContent() throws Exception {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHtml() throws Exception {
		return processHTMLWidgets(getContent(), null);
	}

	public String getHtml(WikiPage context) throws Exception {
		return processHTMLWidgets(getContent(), context);
	}

	private String processHTMLWidgets(String content, WikiPage context) throws Exception {
		ParentWidget root = new WidgetRoot(content, context, WidgetBuilder.htmlWidgetBuilder);
		return root.render();
	}

	public boolean isEmpty() throws Exception {
		return getContent() == null || getContent().length() == 0;
	}
}
