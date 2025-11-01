package com.org.generateappbackend.core.parser;

import com.org.generateappbackend.exception.BusinessException;
import com.org.generateappbackend.exception.ErrorCode;
import com.org.generateappbackend.model.enums.CodeGenTypeEnum;

/**
 * 代码解析执行器
 * 根据代码生成类型执行响应的解析逻辑
 *
 * @author konna
 */
public class CodeParserExecutor {

    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();

    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    /**
     * 执行解析逻辑
     *
     * @param codeContent 代码内容
     * @param codeGenType 生成类型
     * @return 解析结果 (HtmlCodeResult或者MultiFileCodeResult)
     */
    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenType){
        return switch (codeGenType){
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileCodeParser.parseCode(codeContent);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR,"不支持的生成类型: "+codeGenType);
        };
    }
}
