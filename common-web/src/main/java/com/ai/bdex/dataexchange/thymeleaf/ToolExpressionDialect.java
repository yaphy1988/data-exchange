package com.ai.bdex.dataexchange.thymeleaf;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.standard.StandardDialect;
import java.util.Map;

public class ToolExpressionDialect implements IExpressionObjectDialect {

	public static final String NAME = "ToolStandard";
	public static final String PREFIX = "th";
	public static final int PROCESSOR_PRECEDENCE = 1000;

	private final IExpressionObjectFactory TOOL_EXPRESSION_OBJECT_FACTORY = new ToolExpressionObjectFactory();
	
//	public ToolExpressionDialect(){
//		super(NAME,PREFIX,PROCESSOR_PRECEDENCE);
//	}
	
	@Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return TOOL_EXPRESSION_OBJECT_FACTORY;
    }

	@Override
	public String getName() {
		return NAME;
	}
}
