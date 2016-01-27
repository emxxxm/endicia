package jexlexample;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

import atfImplementation.CalculationNotPossibleException;


public class JEXLModule {
	private static final JexlEngine jexl = new JexlBuilder().strict(true).silent(false).cache(512).create();
	static JexlQuery query = new JexlQuery();
	
	public static void main(String[] args) throws NumberFormatException, CalculationNotPossibleException {
		System.out.println("Result of HFPU query: " + evalHFPUQuery());;
	}
	
	public static String evalMailQuery() throws NumberFormatException, CalculationNotPossibleException {
		String calcType = JexlQuery.TYPE_MAIL_CALC;
		return evalQuery(query.getSampleQueryResponseString(calcType));
	}
	
	public static String evalHFPUQuery() throws NumberFormatException, CalculationNotPossibleException {
		String calcType = JexlQuery.TYPE_HFPU_LOC;
		return evalQuery(query.getSampleQueryResponseString(calcType));
	}
	
	public static String evalQuery(String queryType) throws NumberFormatException, CalculationNotPossibleException {
		System.out.println("Evaluating Expression: " + queryType);
		JexlExpression e = jexl.createExpression(queryType);

		JexlContext context = getContext();
		
		Object result = e.evaluate(context);
		
		return result.toString();
	}
	
	public static JexlContext getContext() throws NumberFormatException, CalculationNotPossibleException {
		JexlContext context = new MapContext();
		
		context.set(JexlQuery.HFPU_ID, JexlLogic.evalHFPU(query.getMailClass()));
		context.set(JexlQuery.MAIL_CLASS_ID, query.getMailClass());
		context.set(JexlQuery.TIME_ID, JexlLogic.evalTime(query.getOriginZip(), query.getDestZip()));
		
		context.set(JexlQuery.LOC_ID, JexlLogic.getLocationWithinRadius(query.getLocation(), query.getRadius()));
		
		return context;
	}

}
