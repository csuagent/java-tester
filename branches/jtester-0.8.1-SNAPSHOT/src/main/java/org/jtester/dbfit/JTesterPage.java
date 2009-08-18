package org.jtester.dbfit;

import java.io.Serializable;
import java.util.Date;

import fitnesse.wiki.PageData;
import fitnesse.wiki.WikiPage;
import fitnesse.wiki.WikiPageProperties;
import fitnesse.wikitext.WidgetBuilder;
import fitnesse.wikitext.widgets.AliasLinkWidget;
import fitnesse.wikitext.widgets.AnchorDeclarationWidget;
import fitnesse.wikitext.widgets.AnchorMarkerWidget;
import fitnesse.wikitext.widgets.BoldWidget;
import fitnesse.wikitext.widgets.CenterWidget;
import fitnesse.wikitext.widgets.CommentWidget;
import fitnesse.wikitext.widgets.EmailWidget;
import fitnesse.wikitext.widgets.EvaluatorWidget;
import fitnesse.wikitext.widgets.HeaderWidget;
import fitnesse.wikitext.widgets.HruleWidget;
import fitnesse.wikitext.widgets.ImageWidget;
import fitnesse.wikitext.widgets.ItalicWidget;
import fitnesse.wikitext.widgets.LinkWidget;
import fitnesse.wikitext.widgets.ListWidget;
import fitnesse.wikitext.widgets.LiteralWidget;
import fitnesse.wikitext.widgets.NoteWidget;
import fitnesse.wikitext.widgets.ParentWidget;
import fitnesse.wikitext.widgets.PreformattedWidget;
import fitnesse.wikitext.widgets.StrikeWidget;
import fitnesse.wikitext.widgets.StyleWidget;
import fitnesse.wikitext.widgets.TableWidget;
import fitnesse.wikitext.widgets.VariableDefinitionWidget;
import fitnesse.wikitext.widgets.VariableWidget;
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

	public String getHtml() throws Exception {
		ParentWidget root = new WidgetRoot(content, (WikiPage) null, myHtmlWidgetBuilder);
		return root.render();
	}

	public boolean isEmpty() throws Exception {
		return content == null || content.length() == 0;
	}

	public static WidgetBuilder myHtmlWidgetBuilder = new WidgetBuilder() {
		{

			this.addWidgetClass(CommentWidget.class);
			this.addWidgetClass(LiteralWidget.class);
			// this.addWidgetClass(WikiWordWidget.class);
			this.addWidgetClass(BoldWidget.class);
			this.addWidgetClass(ItalicWidget.class);
			this.addWidgetClass(PreformattedWidget.class);
			this.addWidgetClass(HruleWidget.class);
			this.addWidgetClass(HeaderWidget.class);
			this.addWidgetClass(CenterWidget.class);
			this.addWidgetClass(NoteWidget.class);
			this.addWidgetClass(StyleWidget.ParenFormat.class);
			this.addWidgetClass(StyleWidget.BraceFormat.class);
			this.addWidgetClass(StyleWidget.BracketFormat.class);
			this.addWidgetClass(TableWidget.class);
			this.addWidgetClass(ListWidget.class);
			// this.addWidgetClass(ClasspathWidget.class);
			this.addWidgetClass(ImageWidget.class);
			this.addWidgetClass(LinkWidget.class);
			// this.addWidgetClass(TOCWidget.class);
			this.addWidgetClass(AliasLinkWidget.class);
			// this.addWidgetClass(VirtualWikiWidget.class);
			this.addWidgetClass(StrikeWidget.class);
			// this.addWidgetClass(LastModifiedWidget.class);
			// this.addWidgetClass(TodayWidget.class);
			// this.addWidgetClass(XRefWidget.class);
			// this.addWidgetClass(MetaWidget.class);
			this.addWidgetClass(EmailWidget.class);
			this.addWidgetClass(AnchorDeclarationWidget.class);
			this.addWidgetClass(AnchorMarkerWidget.class);
			// this.addWidgetClass(CollapsableWidget.class);
			// this.addWidgetClass(IncludeWidget.class);
			this.addWidgetClass(VariableDefinitionWidget.class);
			this.addWidgetClass(EvaluatorWidget.class);
			this.addWidgetClass(VariableWidget.class);
		}
	};
}
