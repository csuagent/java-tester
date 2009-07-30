/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JdbcProxy.
 * 
 * The Initial Developer of the Original Code is Frans van Gool.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * Frans van Gool. All Rights Reserved.
 * 
 * Contributor(s): Frans van Gool.
 * 
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU Lesser General Public License (the �LGPL License�), in which case the
 * provisions of LGPL License are applicable instead of those above. If you wish
 * to allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under the MPL,
 * indicate your decision by deleting the provisions above and replace them with
 * the notice and other provisions required by the LGPL License. If you do not
 * delete the provisions above, a recipient may use your version of this file
 * under either the MPL or the LGPL License.
 */
package nl.griffelservices.proxy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Generates the proxy classes, and their common super class, for a given set of
 * interfaces.
 * 
 * @author Frans van Gool
 */
public class Generator {
	/** the interfaces for which proxy classes must be generated */
	private Class<?> interfaces[];
	/** the name of the package of the proxy classes */
	private String proxyPackage;
	/** the name of the common super class of the proxy classes */
	private String superClassName;

	/**
	 * Constructs a new Generator object.
	 * 
	 * @param interfaces
	 *            the interfaces for which proxy classes must be generated
	 * @param proxyPackage
	 *            the name of the package of the proxy classes
	 * @param superClassName
	 *            the name of the common super class of the proxy classes
	 */
	public Generator(Class<?> interfaces[], String proxyPackage, String superClassName) {
		this.interfaces = interfaces;
		this.proxyPackage = proxyPackage;
		this.superClassName = superClassName;
	}

	/**
	 * Generates the proxy classes, and their common super class, files in the
	 * specified folder.
	 * 
	 * @param folder
	 * @throws IOException
	 *             if a file cannot be written
	 */
	public void generate(String folder) throws IOException {
		String file = folder + "/" + proxyPackage.replace('.', '/') + "/" + superClassName + ".java";
		PrintWriter pw = new PrintWriter(new FileWriter(file));
		generateSuperClass(pw);
		pw.close();

		for (int i = 0; i < interfaces.length; i++) {
			String className = name(interfaces[i]);
			className = className.substring(className.lastIndexOf('.') + 1);
			file = folder + "/" + proxyPackage.replace('.', '/') + "/" + className + "Proxy.java";
			pw = new PrintWriter(new FileWriter(file));
			generateClass(pw, i);
			pw.close();
		}
	}

	/**
	 * Generates the base class that implements the generic functionality for
	 * all proxt classes.
	 * 
	 * @param pw
	 *            the writer to which the generated code must be written
	 */
	private void generateSuperClass(PrintWriter pw) {
		generateHeader(pw);
		pw.println("package " + proxyPackage + ";");
		pw.println();
		pw.println("import nl.griffelservices.proxy.Handler;");
		pw.println("import nl.griffelservices.proxy.Proxy;");
		pw.println();
		pw.println("/** This class implements the generic functionality for " + proxyPackage + " */");
		pw.println("public abstract class " + superClassName + " extends Proxy");
		pw.println("{");
		pw.println("  /**");
		pw.println("   * Constructs a new " + superClassName + " object.");
		pw.println("   * ");
		pw.println("   * @param handler the proxy handler");
		pw.println("   * @param proxyClass the proxy class");
		pw.println("   * @param proxyObject the proxy data");
		pw.println("   */");
		pw.println("  protected " + superClassName + "(Handler handler, Class proxyClass, Object proxyObject)");
		pw.println("  {");
		pw.println("    super(handler, proxyObject);");
		pw.println("    handler.init(proxyClass, proxyObject);");
		pw.println("  }");
		pw.println();
		pw.println("  public Class narrowReturnType(Class returnType, Object returnValue)");
		pw.println("  {");

		for (int i = 0; i < interfaces.length; i++) {
			String fullClassName = name(interfaces[i]);
			pw.println("    returnType = narrowReturnType(returnType, returnValue, " + fullClassName + ".class);");
		}

		pw.println("    return returnType;");
		pw.println("  }");
		pw.println();
		pw.println("  public Object getReturnValueProxy(Class returnType, Handler handler, Object proxyObject)");
		pw.println("  {");
		pw.println("    if (proxyObject == null) return null;");

		for (int i = 0; i < interfaces.length; i++) {
			String fullClassName = name(interfaces[i]);
			String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
			pw.println("    if (returnType.equals(" + fullClassName + ".class)) return new " + className
					+ "Proxy(handler, proxyObject);");
		}

		pw.println("    return proxyObject;");
		pw.println("  }");
		pw.println("}");
	}

	/**
	 * Generates the proxy class that implements an interface.
	 * 
	 * @param pw
	 *            the writer to which the generated code must be written
	 * @param interfaceIndex
	 *            the index in the {@link #interfaces} array
	 */
	private void generateClass(PrintWriter pw, int interfaceIndex) {
		String fullClassName = name(interfaces[interfaceIndex]);
		String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

		generateHeader(pw);
		pw.println("package " + proxyPackage + ";");
		pw.println();
		pw.println("import java.lang.reflect.Method;");
		pw.println("import nl.griffelservices.proxy.Handler;");
		pw.println();
		pw.println("/** This class is a proxy implementation of " + fullClassName + " */");
		pw.println("public class " + className + "Proxy extends " + superClassName + " implements " + fullClassName);
		pw.println("{");
		pw.println("  /**");
		pw.println("   * Constructs a new " + className + "Proxy object.");
		pw.println("   * ");
		pw.println("   * @param handler the proxy handler");
		pw.println("   * @param proxyObject the proxy data");
		pw.println("   */");
		pw.println("  public " + className + "Proxy(Handler handler, Object proxyObject)");
		pw.println("  {");
		pw.println("    super(handler, " + fullClassName + ".class, proxyObject);");
		pw.println("  }");

		Method methods[] = interfaces[interfaceIndex].getMethods();
		for (int i = 0; i < methods.length; i++) {
			generateMethod(pw, fullClassName, i, methods[i]);
		}

		pw.println("}");
	}

	/**
	 * Generates the comment that indicates it is a generated class. This
	 * comment is used both in teh proxy classes and in the common super class.
	 * 
	 * @param pw
	 *            the writer to which the generated code must be written
	 */
	private static void generateHeader(PrintWriter pw) {
		pw.println("/*");
		pw.println(" * This file is generated by " + Generator.class.getName() + ".");
		pw.println(" * Please do not modify this file manually.");
		pw.println(" * All your changes will be deleted when this file is regenerated.");
		pw.println(" */");
	}

	/**
	 * Generates the implementation of a single method in a proxy class.
	 * 
	 * @param pw
	 *            the writer to which the generated code must be written
	 * @param fullClassName
	 *            the name of the interface whose method implementation is
	 *            generated
	 * @param count
	 *            the method index (used to name method specific static
	 *            variables)
	 * @param method
	 *            the method whose implementation must be generated
	 */
	private static void generateMethod(PrintWriter pw, String fullClassName, int count, Method method) {
		String argClasses = "";
		String argDeclaration = "";
		String argValues = "";
		Class<?> arg[] = method.getParameterTypes();
		for (int i = 0; i < arg.length; i++) {
			if (i > 0)
				argClasses += ", ";
			argClasses += name(arg[i]) + ".class";

			if (i > 0)
				argDeclaration += ", ";
			argDeclaration += name(arg[i]) + " p" + i;

			if (i > 0)
				argValues += ", ";
			if (arg[i].equals(boolean.class))
				argValues += "new Boolean(p" + i + ")";
			else if (arg[i].equals(byte.class))
				argValues += "new Byte(p" + i + ")";
			else if (arg[i].equals(char.class))
				argValues += "new Character(p" + i + ")";
			else if (arg[i].equals(double.class))
				argValues += "new Double(p" + i + ")";
			else if (arg[i].equals(float.class))
				argValues += "new Float(p" + i + ")";
			else if (arg[i].equals(int.class))
				argValues += "new Integer(p" + i + ")";
			else if (arg[i].equals(long.class))
				argValues += "new Long(p" + i + ")";
			else if (arg[i].equals(short.class))
				argValues += "new Short(p" + i + ")";
			else
				argValues += "p" + i;
		}

		String excThrows = "";
		Class<?> exc[] = method.getExceptionTypes();
		if (exc.length > 0) {
			excThrows += " throws ";
		}
		for (int i = 0; i < exc.length; i++) {
			if (i > 0)
				excThrows += ", ";
			excThrows += name(exc[i]);
		}

		Class<?> ret = method.getReturnType();
		pw.println();
		pw.println("  /** " + method + " */");
		pw.println("  private static final Method m" + count + " = getMethod(" + fullClassName + ".class, \""
				+ method.getName() + "\", new Class[] {" + argClasses + "});");
		pw.println("  public " + name(ret) + " " + method.getName() + "(" + argDeclaration + ")" + excThrows);
		pw.println("  {");
		String invoke = "invoke(m" + count + ", new Object[] {" + argValues + "})";
		if (ret.equals(boolean.class))
			invoke = "return ((Boolean)" + invoke + ").booleanValue()";
		else if (ret.equals(byte.class))
			invoke = "return ((Byte)" + invoke + ").byteValue()";
		else if (ret.equals(char.class))
			invoke = "return ((Character)" + invoke + ").charValue()";
		else if (ret.equals(double.class))
			invoke = "return ((Double)" + invoke + ").doubleValue()";
		else if (ret.equals(float.class))
			invoke = "return ((Float)" + invoke + ").floatValue()";
		else if (ret.equals(int.class))
			invoke = "return ((Integer)" + invoke + ").intValue()";
		else if (ret.equals(long.class))
			invoke = "return ((Long)" + invoke + ").longValue()";
		else if (ret.equals(short.class))
			invoke = "return ((Short)" + invoke + ").shortValue()";
		else if (ret.equals(void.class))
			invoke = invoke + "";
		else
			invoke = "return (" + name(ret) + ")" + invoke;
		pw.println("    " + invoke + ";");
		pw.println("  }");
	}

	/**
	 * Returns the declaration of a class as used in Java source code.
	 * 
	 * @param c
	 *            the class for which teh name is needed
	 * @return the declaration of a class as used in Java source code
	 */
	private static String name(Class<?> c) {
		String name = c.getName();
		if (name.charAt(0) == '[') {
			String arrayName = "";
			while (name.charAt(0) == '[') {
				arrayName += "[]";
				name = name.substring(1);
			}
			switch (name.charAt(0)) {
			case 'Z':
				return "boolean" + arrayName;
			case 'B':
				return "byte" + arrayName;
			case 'C':
				return "char" + arrayName;
			case 'D':
				return "double" + arrayName;
			case 'F':
				return "float" + arrayName;
			case 'I':
				return "int" + arrayName;
			case 'J':
				return "long" + arrayName;
			case 'S':
				return "short" + arrayName;
			case 'L':
				return name.substring(1, name.length() - 1) + arrayName;
			default:
				throw new IllegalArgumentException(name);
			}
		}
		return name;
	}
}
