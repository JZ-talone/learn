package com.hym.spring.learn.drools.service.impl;

import com.google.common.collect.Maps;
import com.hym.spring.learn.drools.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by yuhao.wang on 2017/6/19.
 */
@Slf4j
@Service
public class RuleServiceImpl implements RuleService {


    static Map<String, String> ruleMap = Maps.newHashMap();

    static {
        ruleMap.put("score", "package com.xiaolyuh.drools.address\n" +
                "\n" +
                "import com.xiaolyuh.domain.model.Address;\n" +
                "import com.xiaolyuh.domain.fact.AddressCheckResult;\n" +
                "\n" +
                "rule \"address first 6\"\n" +
                "    no-loop true\n" +
                "    lock-on-active true\n" +
                "    salience 9999999\n" +
                "    when\n" +
                "\t\teval(true);\n" +
                "    then\n" +
                "        System.out.println(\"执行了规则Address6文件\"); \n" +
                "end\n" +
                "\n" +
                "rule \"Postcode 6 numbers\"\n" +
                "\n" +
                "    when\n" +
                "        address : Address(postcode != null, postcode matches \"([0-9]{6})\")\n" +
                "        checkResult : AddressCheckResult();\n" +
                "    then\n" +
                "        checkResult.setPostCodeResult(true);\n" +
                "\t\tSystem.out.println(\"规则6中打印日志：校验通过!\");\n" +
                "end");
        ruleMap.put("score", "package com.xiaolyuh.drools.address\n" +
                "\n" +
                "import com.xiaolyuh.domain.model.Address;\n" +
                "import com.xiaolyuh.domain.fact.AddressCheckResult;\n" +
                "\n" +
                "rule \"address first 7\"\n" +
                "    no-loop true\n" +
                "    lock-on-active true\n" +
                "    salience 9999999\n" +
                "    when\n" +
                "\t\teval(true);\n" +
                "    then\n" +
                "        System.out.println(\"执行了规则Address7文件\"); \n" +
                "end\n" +
                "\n" +
                "rule \"Postcode 7 numbers\"\n" +
                "\n" +
                "    when\n" +
                "        address : Address(postcode != null, postcode matches \"([0-9]{6})\")\n" +
                "        checkResult : AddressCheckResult();\n" +
                "    then\n" +
                "        checkResult.setPostCodeResult(true);\n" +
                "\t\tSystem.out.println(\"规则7中打印日志：校验通过!\");\n" +
                "end");
        ruleMap.put("hello world", "package com.xiaolyuh.drools\n" +
                "import com.xiaolyuh.domain.model.Message;\n" +
                "\n" +
                "rule \"message first\"\n" +
                "    no-loop true\n" +
                "    lock-on-active true\n" +
                "    salience 9999999\n" +
                "    when\n" +
                "\t\teval(true);\n" +
                "    then\n" +
                "        System.out.println(\"执行了规则Message文件\"); \n" +
                "end\n" +
                "\n" +
                "rule \"rule1\"\n" +
                "\twhen\n" +
                "\t\tMessage( status == 1, myMessage : msg )\n" +
                "\tthen\n" +
                "\t\tSystem.out.println( 1+\":\"+myMessage );\n" +
                "end\n");
    }


    @Override
    public KieSession getKieSessionByName(String ruleKey) {
        StatefulKnowledgeSession kSession = null;
        long startTime = System.currentTimeMillis();

        String rule = ruleMap.get(ruleKey);

        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // 装入规则，可以装入多个
        kb.add(ResourceFactory.newByteArrayResource(rule.getBytes(StandardCharsets.UTF_8)), ResourceType.DRL);

        // 检查错误
        KnowledgeBuilderErrors errors = kb.getErrors();
        for (KnowledgeBuilderError error : errors) {
            System.out.println(error);
        }

        KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addKnowledgePackages(kb.getKnowledgePackages());

        // 创建kSession
        kSession = kBase.newStatefulKnowledgeSession();
        long endTime = System.currentTimeMillis();
        log.info("获取kSession耗时：{}", endTime - startTime);
        return kSession;
    }

    // 不使用kieContainer这种方式，因为创建kSession的时候只能用kieContainer.newKieSession()，
    // 不能指定要创建那个kSession，在执行规则的时候会把所有的规则都执行一次
    @Override
    public void reloadRule() {
        KieContainer kieContainer = loadContainerFromString(ruleMap);
    }

    private KieContainer loadContainerFromString(Map<String, String> rules) {
        long startTime = System.currentTimeMillis();
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        for (String rulename : rules.keySet()) {
            String drl = rules.get(rulename);
            kfs.write("src/main/resources/" + drl.hashCode() + ".drl", drl);
        }

        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to build rules : " + (endTime - startTime) + " ms");
        startTime = System.currentTimeMillis();
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        endTime = System.currentTimeMillis();
        System.out.println("Time to load container: " + (endTime - startTime) + " ms");
        return kContainer;
    }
}
