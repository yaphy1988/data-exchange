package com.ai.bdex.dataexchange.thymeleaf;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ai.bdex.dataexchange.util.ThymeleafToolsUtil;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class ToolExpressionObjectFactory implements IExpressionObjectFactory {

	public static final String TOOLS_EXPRESSION_OBJECT_NAME = "tools";

	protected static final Set<String> ALL_EXPRESSION_OBJECT_NAMES =
			Collections.unmodifiableSet(
					new LinkedHashSet<String>(java.util.Arrays.asList(new String[]{TOOLS_EXPRESSION_OBJECT_NAME}))
			);

	public ToolExpressionObjectFactory(){ super(); }

	@Override
	public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }
	
	@Override
	public Object buildObject(final IExpressionContext context, final String expressionObjectName) {
		
		if (TOOLS_EXPRESSION_OBJECT_NAME.equals(expressionObjectName)) {
			return new ThymeleafToolsUtil();
	    }

		return null;
	}

	@Override
	public boolean isCacheable(String expressionObjectName) {
		return true;
	}
}
