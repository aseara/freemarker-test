package com.aseara.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: aseara
 * Date: 14-3-14
 * Time: 下午4:19
 */
@SuppressWarnings("unchecked")
public class RepeatDirective implements TemplateDirectiveModel {

    private static final String PARAM_NAME_COUNT = "count";
    private static final String PARAM_NAME_HR = "hr";

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body)
            throws TemplateException, IOException {

        int countParam = 0;
        boolean countParamSet = false;
        boolean hrParam = false;

        Iterator paramIter = params.entrySet().iterator();

        for (Map.Entry<String, TemplateModel> ent : ((Map<String, TemplateModel>)params).entrySet()) {
            String paramName = ent.getKey();
            TemplateModel paramValue = ent.getValue();

            if (PARAM_NAME_COUNT.equals(paramName)) {
                if (!(paramValue instanceof TemplateNumberModel)) {
                    throw new TemplateModelException("The \"" +
                            PARAM_NAME_HR + "\" parameter " +
                            "must be a number.");
                }

                countParam = ((TemplateNumberModel)paramValue).getAsNumber().intValue();
                countParamSet = true;

                if (countParam < 0) {
                    throw new TemplateModelException("The \"" +
                            PARAM_NAME_HR + "\" parameter " +
                            "can't be negative.");
                }

            } else if (PARAM_NAME_HR.equals(paramName)) {
                if (!(paramValue instanceof TemplateBooleanModel)) {
                    throw new TemplateModelException("The \"" +
                            PARAM_NAME_HR + "\" parameter " +
                            "must be a boolean.");
                }

                hrParam = ((TemplateBooleanModel)paramValue).getAsBoolean();
            } else {
                throw new TemplateModelException(
                        "Unsupported parameter: " + paramName);
            }
        }

        if (!countParamSet) {
            throw new TemplateModelException("The required \""
                    + PARAM_NAME_COUNT + "\" paramter" +
                    "is missing.");
        }

        if (loopVars.length > 1) {
            throw new TemplateModelException(
                    "At most one loop variable is allowed.");
        }

        Writer out = env.getOut();

        if (body != null) {

            for (int i = 0; i < countParam; i++) {

                if (hrParam && i > 0) {
                    out.write("<hr>");
                }

                if (loopVars.length > 0) {
                    loopVars[0] = new SimpleNumber(i + 1);
                }

                body.render(out);
            }
        }
    }
}
