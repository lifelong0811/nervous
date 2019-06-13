package org.nervous.layout.registry;

import com.google.common.graph.*;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


@Data
public class ModuleHolder {

    /**
     * 记录以来关系的依赖关系图;
     */
    private MutableGraph<ModuleNode> graph;

    /**
     * 记录所有的Module信息;
     * TODO: 是否能够实现分层级记录;
     */
    private Set<ModuleNode> allModuleNode;

}
