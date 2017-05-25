package com.aseara.freemarker;

import freemarker.template.Template;
import org.junit.After;

import static org.springframework.ui.freemarker.FreeMarkerTemplateUtils.processTemplateIntoString;

/**
 * Created by qiujingde on 2017/3/9. <br />
 * spring context support中提供的工具类的测试。
 * @author qiujingde
 */
public class SpringUtilTest extends FreemarkerTest {

    @After
    public void after() throws Exception {
        Template temp = cfg.getTemplate(tempName);
        System.out.println(processTemplateIntoString(temp, root));
    }

}
