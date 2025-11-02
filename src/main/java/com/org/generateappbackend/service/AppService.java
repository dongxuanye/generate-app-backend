package com.org.generateappbackend.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.org.generateappbackend.model.dto.app.AppQueryRequest;
import com.org.generateappbackend.model.entity.App;
import com.org.generateappbackend.model.entity.User;
import com.org.generateappbackend.model.vo.app.AppVO;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 *  服务层。
 *
 * @author konna
 */
public interface AppService extends IService<App> {

    /**
     * 校验应用
     *
     * @param app 应用对象
     * @param add 是否为创建操作
     */
    void validApp(App app, boolean add);

    /**
     * 获取查询条件
     *
     * @param appQueryRequest 查询请求
     * @return 查询条件
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 获取应用封装
     *
     * @param app 应用对象
     * @return 应用视图对象
     */
    AppVO getAppVO(App app);

    /**
     * 获取应用封装列表
     *
     * @param appList 应用列表
     * @return 应用视图对象列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 聊天生成代码
     *
     * @param appId 应用id
     * @param message 聊天内容
     * @param loginUser 登录用户
     * @return 生成的代码流
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);
}
