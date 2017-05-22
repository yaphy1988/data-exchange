package com.ai.bdex.dataexchange.thymeleaf;

import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class ToolExpressionDialect implements IExpressionObjectDialect {

	public static final String NAME = "ToolStandard";

	private final IExpressionObjectFactory TOOL_EXPRESSION_OBJECT_FACTORY = new ToolExpressionObjectFactory();
	
	@Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return TOOL_EXPRESSION_OBJECT_FACTORY;
    }

	@Override
	public String getName() {
		return NAME;
	}
}
