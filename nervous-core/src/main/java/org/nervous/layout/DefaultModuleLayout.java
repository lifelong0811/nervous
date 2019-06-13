package org.nervous.layout;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;
import com.sun.tools.javac.util.GraphUtils;
import org.apache.logging.log4j.util.Strings;
import org.nervous.annotation.Module;
import org.nervous.controller.NervousFrameExceptionEnum;
import org.nervous.exception.NervousFrameException;
import org.nervous.layout.registry.ClassModuleParser;
import org.nervous.layout.registry.ModuleNode;
import org.nervous.layout.registry.ParamInfo;
import org.nervous.reflect.ReflectUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 默认编排引擎实现；
 * @author wangleijie
 */
public class DefaultModuleLayout implements ModuleLayout{

    private ClassModuleParser parser;

    @Override
    public void layoutModule(List<Class> moduleClassList) {
        List<ModuleNode> moduleNodeList = Lists.newArrayList();
        for (Class aClass : moduleClassList) {
            moduleNodeList = parser.parseBasicModuleNode(aClass);
        }


        Map<String, ModuleNode> moduleName2ModuleNode = Maps.newHashMap();
        Map<String, ModuleNode> moduleAliasName2ModuleNode = Maps.newHashMap();
        Map<String, String> paramAliasName2ModuleName = Maps.newHashMap();
        Map<String, String> paramName2ModuleName = Maps.newHashMap();

        for (ModuleNode moduleNode : moduleNodeList) {
            moduleName2ModuleNode.put(moduleNode.getName(), moduleNode);
            moduleAliasName2ModuleNode.put(moduleNode.getAliasName(), moduleNode);
            for (ParamInfo paramInfo : moduleNode.getParamInfoList()) {
                if (paramInfo.getName() != null) {
                    paramAliasName2ModuleName.put(paramInfo.getName(), moduleNode.getName());
                }
                paramName2ModuleName.put(buildParamName(paramInfo, moduleNode), moduleNode.getName());
            }
        }




    }

    private String buildParamName(ParamInfo paramInfo, ModuleNode moduleNode) {
        if (ReflectUtil.isBasicType(paramInfo.getParamType())) {
            //TODO: 类型为basic类型的情况;
        }
        return paramInfo.getParamType().getName();
    }
}
