package com.xiu.uuc.common.jexl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.xiu.uuc.common.util.StringUtils;

/**
 * @ClassName: JexlManager 
 * @Description: Jexl表达式解析 如：c=a1*100+b1*200 a1=1,b1=1 c=300
 * @author menglei
 * @date Aug 2, 2011 9:57:00 AM 
 */
public class JexlManager {


	@SuppressWarnings("unchecked")
	public static Long getExpressValue(Map map, String expressionStr) throws ParseException {
		Long expValue = 0L;
		map = instanlExpressMap(map);
		if (!StringUtils.isNullObject(expressionStr)) {
			JexlEngine jexl = JexlManager.getJexlEngine();
			Expression expression = jexl.createExpression(expressionStr);
			JexlContext jexlContext = new MapContext(map);
			Object obj = (Object)expression.evaluate(jexlContext);
			if (null != obj) {
				BigDecimal b = new BigDecimal(obj.toString());
				b = b.setScale(0, BigDecimal.ROUND_HALF_UP);
				expValue = b.longValue();
			}
		}
		return expValue;
	}

	@SuppressWarnings("unchecked")
	private static Map instanlExpressMap(Map map) {
		if (map != null) {
			Object p1 = map.get("p1");
			Object p2 = map.get("p2");
			if (p1 == null) {
				map.put("p1", 0);
			}
			if (p2 == null) {
				map.put("p2", 0);
			}
		} else {
			map = new HashMap();
			map.put("p1", 0);
			map.put("p2", 0);
		}
		return map;
	}
	
	public static JexlEngine getJexlEngine() {
		return jexlEngine;
	}
	
	private static final JexlEngine jexlEngine = new JexlEngine();
	
	static {
		jexlEngine.setCache(1);
		jexlEngine.setLenient(false);
		jexlEngine.setSilent(false);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		
//		Map map = new HashMap();
//		map.put("p1", 4.5);
//		map.put("p2", 0);
//		String express = "p1*1+p2*1";
	
//		Map map = new HashMap();
//		map.put("p1", 555555555);
//		map.put("p2", 1);
//		String express = "p1*0.01+p2*1";
		

		Map map = new HashMap();
		map.put("p1", 555555555);
		map.put("p2", 0.55555555);
		String express = "p1*100000+p2*1";

		Long integer111 = getExpressValue(null,express);
		System.out.println("integer="+integer111);
    }
}