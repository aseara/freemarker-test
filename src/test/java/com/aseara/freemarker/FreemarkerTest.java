package com.aseara.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: aseara
 * Date: 14-3-14
 * Time: 下午3:06
 */

public class FreemarkerTest {

    protected Configuration cfg;
    protected String tempName;
    protected Map<String, Object> root;

    @Before
    public void before() throws Exception {
        // Freemarker的全局配置
        cfg = new Configuration();
        // 设置模板文件的路径
        cfg.setClassForTemplateLoading(FreemarkerTest.class, "\\templates");
        // 设置模板检索数据模型的方式
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        // 准备数据
        root = new HashMap<>();
    }

    @After
    public void after() throws Exception {
        Writer out = new OutputStreamWriter(System.out);

        // 创建、解析模板并缓存
        Template temp = cfg.getTemplate(tempName);
        // 将数据与模板合并
        temp.process(root, out);

        out.flush();
    }

    @Test
    public void testFirst() throws Exception {
        tempName = "test.ftl";

        root.put("user", "Big Jie");
        Map<String, Object> latest = new HashMap<>();
        root.put("latestProduct", latest);
        latest.put("url", "products\\template.html");
        latest.put("name", "green mouse");
    }

    @Test
    public void testMacro1() {
        tempName = "macro.ftl";
        root.put("user", "Big Jie");
    }

    @Test
    public void testNamespace() {
        tempName = "ns2.ftl";
        root.put("user", "Big Jie");
    }

    @Test
    public void upperDirectiveTest() {
        tempName = "upper_directive.ftl";
        root.put("upper", new UpperDirective());
    }

    @Test
    public void repeatDirectiveTest() throws Exception {
        tempName = "repeat.ftl";
        root.put("repeat", new RepeatDirective());
    }

}
