package com.org.generateappbackend.ai;

import com.org.generateappbackend.ai.model.HtmlCodeResult;
import com.org.generateappbackend.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeGeneratorServiceTest {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult res = aiCodeGeneratorService.generateHtmlCode("做个程序员konna的工作记录小工具，不超过20行");
        Assertions.assertNotNull(res);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult res = aiCodeGeneratorService.generateMultiFileCode("做个程序员konna的留言板，不超过20行");
        Assertions.assertNotNull(res);
    }
}