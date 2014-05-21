package com.coship.sdp.sce.dp.utils;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 数学运算的扩展功能类
 * 
 * @author kolenxiao
 * 
 */
public class MathUtils {

	// 默认除法运算精度
	private static final int DEFAULT_DIV_SCALE = 2;

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数的和
	 */

	public static double add(double v1, double v2)

	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的加法运算
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数数学加和，以字符串格式返回
	 */

	public static String add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数的差
	 */
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数数学差，以字符串格式返回
	 */
	public static String subtract(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数的积
	 */
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数的数学积，以字符串格式返回
	 */
	public static String multiply(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).toString();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数的商
	 */
	public static double divide(double v1, double v2) {
		return divide(v1, v2, DEFAULT_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double divide(double v1, double v2, int scale) {
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * @return 两个参数的商
	 */
	public static double divide(double v1, double v2, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, round_mode).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * @param v2
	 * @return 两个参数的商，以字符串格式返回
	 */
	public static String divide(String v1, String v2) {
		return divide(v1, v2, DEFAULT_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * @return 两个参数的商，以字符串格式返回
	 */
	public static String divide(String v1, String v2, int scale) {
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * @return 两个参数的商，以字符串格式返回
	 */
	public static String divide(String v1, String v2, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, round_mode).toString();
	}

	/**
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @param round_mode
	 *            指定的舍入模式
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		return b.setScale(scale, round_mode).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果，以字符串格式返回
	 */
	public static String round(String v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @param round_mode
	 *            指定的舍入模式
	 * @return 四舍五入后的结果，以字符串格式返回
	 */
	public static String round(String v, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, round_mode).toString();
	}
	
	/**
	 * 后缀表达式求值
	 * 
	 * @param IFX
	 * @return
	 */
	public static String evaluate(String IFX) {
		String PFX[] = null;
		try {
			PFX = trnsInToSufix(IFX);
		} catch (EmptyStackException e) {
			return "syntax error";
		}
		int i = 0;
		double x1, x2, n;
		String str;
		Stack<String> s = new Stack<String>();
		while (PFX[i] != "=") {
			str = PFX[i];
			switch (str.charAt(0)) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				s.push(str);
				break;
			case '+':
				x1 = Double.parseDouble(s.pop());
				x2 = Double.parseDouble(s.pop());
				n = x1 + x2;
				s.push(String.valueOf(n));
				break;
			case '-':
				x1 = Double.parseDouble(s.pop());
				x2 = Double.parseDouble(s.pop());
				n = x2 - x1;
				s.push(String.valueOf(n));
				break;
			case '*':
				x1 = Double.parseDouble(s.pop());
				x2 = Double.parseDouble(s.pop());
				n = x1 * x2;
				s.push(String.valueOf(n));
				break;
			case '/':
				x1 = Double.parseDouble(s.pop());
				x2 = Double.parseDouble(s.pop());
				n = x2 / x1;
				s.push(String.valueOf(n));
				break;
			case 's':
				x1 = Double.parseDouble(s.pop());
				n = Math.sin(x1 * Math.PI / 180);
				s.push(String.valueOf(n));
				break;
			case 'c':
				x1 = Double.parseDouble(s.pop());
				n = Math.cos(x1 * Math.PI / 180);
				s.push(String.valueOf(n));
				break;
			case 't':
				x1 = Double.parseDouble(s.pop());
				n = Math.tan(x1 * Math.PI / 180);
				s.push(String.valueOf(n));
				break;
			case '√':
				x1 = Double.parseDouble(s.pop());
				n = Math.sqrt(x1);
				s.push(String.valueOf(n));
				break;// 开方
			case '^':
				x1 = Double.parseDouble(s.pop());
				x2 = Double.parseDouble(s.pop());
				n = Math.pow(x2, x1);
				s.push(String.valueOf(n));
				break;
			}
			i++;
		}
		String result = s.pop();
		return result;
	}

	/**
	 * 将中缀表达式分解成后缀
	 * PFX为后缀表达式，IFX为中缀表达式
	 * @param IFX
	 * @return
	 */
	private static String[] trnsInToSufix(String IFX) {
		String PFX[] = new String[IFX.length()];
		StringBuffer numBuffer = new StringBuffer();// 用来保存一个数的
		Stack<String> s = new Stack<String>();// 放操作符
		String a;
		s.push("=");// 第一个为等号
		int i = 0, j = 0;
		char ch;
		for (i = 0; i < IFX.length();) {
			ch = IFX.charAt(i);
			switch (ch) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				while (Character.isDigit(ch) || ch == '.')// 拼数
				{
					numBuffer.append(ch); // 追加字符
					ch = IFX.charAt(++i);
				}
				PFX[j++] = numBuffer.toString();// break;
				numBuffer = new StringBuffer(); // 清空已获取的运算数字
				continue; // 这里要重新循环，因为i已经增加过了
			case '(':
				s.push("(");
				break;
			case ')':
				while (s.peek() != "(")
					PFX[j++] = s.pop();
				break;
			case '+':
			case '-':
				while (s.size() > 1 && s.peek() != "(")
					PFX[j++] = s.pop();
				a = String.valueOf(ch);
				s.push(a);
				break;
			case '*':
			case '/':
				while (s.size() > 1 && (s.peek() == "*") || s.peek() == "/"
						|| s.peek() == "s" || s.peek() == "c"
						|| s.peek() == "t" || s.peek() == "^"
						|| s.peek() == "√")
					// 优先级比较，与栈顶比较，
					PFX[j++] = s.pop();// 当前操作符优先级大于等于栈顶的弹出栈顶
				a = String.valueOf(ch);
				s.push(a);
				break;
			case 's':
			case 'c':
			case 't':// 三角函数
				while (s.size() > 1
						&& (s.peek() == "s" || s.peek() == "c"
								|| s.peek() == "t" || s.peek() == "^" || s
								.peek() == "√"))
					// 优先级比较，与栈顶，大于等于的弹出
					PFX[j++] = s.pop();
				a = String.valueOf(ch);
				s.push(a);
				break;
			case '^':// 幂
			case '√':// 开方
				while (s.size() > 1 && (s.peek() == "^" || s.peek() == "√"))
					PFX[j++] = s.pop();
				a = String.valueOf(ch);
				s.push(a);
				break;
			}
			i++;
		}
		while (s.size() > 1)
			PFX[j++] = s.pop();
		PFX[j] = "=";

		return PFX;
	}
	
	
	public static void main(String args[]) {
		System.out.println(evaluate("(31 + 21) * 51 - (21 + 33) / 2 = "));
	}
	
}
