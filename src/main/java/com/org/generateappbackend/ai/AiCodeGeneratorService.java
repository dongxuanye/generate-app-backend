package com.org.generateappbackend.ai;

import com.org.generateappbackend.ai.model.HtmlCodeResult;
import com.org.generateappbackend.ai.model.MultiFileCodeResult;
import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

public interface AiCodeGeneratorService {

    /**
     * 生成HTML代码
     *
     * @param userPrompt 用户提示词
     * @return 代码生成结果
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode(String userPrompt);

    /**
     * 生成多文件代码
     *
     * @param userPrompt 用户提示词
     * @return 代码生成结果
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userPrompt);

    /**
     * 生成HTML代码(流式)
     *
     * @param userPrompt 用户提示词
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    Flux<String> generateHtmlCodeStream(String userPrompt);

    /**
     * 生成多文件代码(流式)
     *
     * @param userPrompt 用户提示词
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    Flux<String> generateMultiFileCodeStream(String userPrompt);
}
