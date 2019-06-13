package org.nervous.layout.registry;

import java.util.List;

public interface ModuleRegistery {
    /**
     * 注册一个moudle进入某个引擎流程中;
     * throw exception标示失败的注册;
     */
    void registerModuleList(List<ModuleNode> moduleNodeList);


    /**
     * 注册一个module进入到引擎中;
     * 注意尽量不要使用这个函数进行注册，因此每次注册均会refresh层次结构;
     * @param moduleNode
     */
    void registerModule(ModuleNode moduleNode);

    /**
     * 获取到有依赖层次关系的Module信息;
     */
    List<List<ModuleNode>> getAllModuleWithDependencyLevel();

}
