package com.org.generateappbackend.core.saver;

import cn.hutool.core.util.StrUtil;
import com.org.generateappbackend.ai.model.HtmlCodeResult;
import com.org.generateappbackend.exception.BusinessException;
import com.org.generateappbackend.exception.ErrorCode;
import com.org.generateappbackend.model.enums.CodeGenTypeEnum;

/**
 * HTML代码文件保存器
 *
 * @author konna
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
        }
    }
}
