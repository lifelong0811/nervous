package org.nervous.layout;

import java.util.List;

/**
 * @author wangleijie
 */
public interface ModuleLayout {
    /**
     * 编排module信息;
     * 会动解析module class中的方法，加入到编排列表中;
     * TODO: 如果一个module中有两个或者多个方法，而其不再一个流程中如何办？
     * @param moduleClass；
     */
    void layoutModule(List<Class> moduleClass);
}
