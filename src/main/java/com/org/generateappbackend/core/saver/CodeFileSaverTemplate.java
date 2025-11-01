package com.org.generateappbackend.core.saver;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.org.generateappbackend.exception.BusinessException;
import com.org.generateappbackend.exception.ErrorCode;
import com.org.generateappbackend.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 抽象的代码文件保存器 - 模板方法模式
 *
 * @param <T>
 */
public abstract class CodeFileSaverTemplate<T> {

    // 文件保存的根目录
    private static final String FILE_SAVE_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";

    /**
     * 保存代码的标准流程
     *
     * @param result 代码结果对象
     * @return 保存后的文件对象
     */
    public final File saveCode(T result){
        // 1.验证输入
        validateInput(result);
        // 2.构建唯一目录
        String baseDirPath = buildUniqueDir();
        // 3.保存文件
        saveFiles(result, baseDirPath);
        // 4.返回保存的文件对象
        return new File(baseDirPath);
    }

    /**
     * 验证输入参数
     *
     * @param result 代码结果对象
     */
    protected void validateInput(T result){
        if (result == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"代码对象不能为空");
        }
    }

    /**
     * 构建文件的唯一目录路径
     *
     * @return 目录路径
     */
    protected final String buildUniqueDir(){
        String codeType = getCodeType( ).getValue( );
        String uniqueDirName = StrUtil.format("{}_{}", codeType, IdUtil.getSnowflakeNextIdStr( ));
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }

    /**
     * 写入单个文件的工具方法
     *
     * @param dirPath   目录路径
     * @param fileName  文件名
     * @param content   文件内容
     */
    protected final void writeToFile(String dirPath, String fileName, String content){
        if (StrUtil.isNotBlank(content)){
            String filePath = dirPath + File.separator + fileName;
            FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);
        }
    }


    /**
     * 获取代码类型(由子类实现)
     *
     * @return 代码生成枚举类型
     */
    protected abstract CodeGenTypeEnum getCodeType();

    /**
     * 保存文件的具体实现(由子类实现)
     *
     * @param result        代码结果对象
     * @param baseDirPath   基础目录路径
     */
    protected abstract void saveFiles(T result, String baseDirPath);

}
