package org.nervous.layout.registry;


import com.google.common.collect.*;
import org.apache.logging.log4j.util.Strings;
import org.nervous.annotation.Module;
import org.nervous.annotation.Param;
import org.nervous.controller.NervousFrameExceptionEnum;
import org.nervous.exception.NervousFrameException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.nervous.controller.NervousFrameExceptionEnum.BUILD_METHOD_CHECK_ERROR;
import static org.nervous.controller.NervousFrameExceptionEnum.REQUEST_BUILDER_CANNOT_FOUND;

public class ClassModuleParser {

    public List<ModuleNode> parseBasicModuleNode(Class clzz){
        List<ModuleNode> moduleNodeList = Lists.newArrayList();
        Method[] methods = clzz.getDeclaredMethods();
        validateClassHasMethod(clzz, methods);
        for (Method method : methods) {
            Module annotation = method.getAnnotation(Module.class);
            if (annotation != null) {
                continue;
            }

            ModuleNode moduleNode = new ModuleNode();
            moduleNodeList.add(moduleNode);
            /**
             * 显示依赖;
             */
            Set<String> dependencyOn = Sets.newHashSet();
            if (annotation.dependencyOn() != null) {
                dependencyOn.addAll(Arrays.asList(annotation.dependencyOn()));
            }
            moduleNode.setDependencyOn(Lists.newArrayList(dependencyOn));

            /**
             * moduleName的生成;
             */
            moduleNode.setName(ModuleNameBuilder.buildModuleName(clzz, method));
            moduleNode.setAliasName(annotation.name());

            /**
             * 构造参数信息;
             */
            List<ParamInfo> paramInfos = Lists.newArrayList();
            if (Strings.isBlank(annotation.requestBuilder())) {
                  paramInfos = getParamInfo(clzz, method);
            } else {
                validateBuildMethod(method);
                Method buildMethod = getBuildMethod(clzz, annotation.requestBuilder(), methods);
                paramInfos = getParamInfo(clzz, buildMethod);
                moduleNode.setRequestBuildMethod(buildMethod);
            }
            moduleNode.setParamInfoList(paramInfos);


            if (!method.getReturnType().getSimpleName().equals("void")) {
                if (annotation.returnName() != null) {
                    moduleNode.setReturnName(annotation.returnName());
                }
                moduleNode.setReturnType(method.getReturnType());
            }
        }
        return moduleNodeList;
    }

    private List<ParamInfo> getParamInfo(Class clzz, Method method) {
        List<ParamInfo> paramInfoList = Lists.newArrayList();
        for (Parameter parameter : method.getParameters()) {
            ParamInfo paramInfo = new ParamInfo();
            paramInfo.setParamType(parameter.getType());
            paramInfoList.add(paramInfo);
            Param param = parameter.getAnnotation(Param.class);
            if (param == null) {
                continue;
            }
            paramInfo.setName(param.name());
            paramInfo.setMapperMeber(param.mapperMemberName());
            paramInfo.setMapperName(param.mapperParamName());
        }
        return paramInfoList;
    }

    private void validateBuildMethod(Method method) {
        Parameter[] parameters = method.getParameters();
        if (parameters.length != 1) {
            throw new NervousFrameException(BUILD_METHOD_CHECK_ERROR.getErrorCode(),
                    method.getName() + "设置了request build method，但是其参数个数仍然超过了1");
        }
    }


    private Method getBuildMethod(Class clazz, String methodName, Method[] methods) {
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }

        throw new NervousFrameException(REQUEST_BUILDER_CANNOT_FOUND.getErrorCode(),
                clazz.getName() + "中未找到Request Build方法" + methodName);
    }



    private void validateClassHasMethod(Class clzz, Method[] methods) {
        if (methods == null || methods.length == 0) {
            String message = clzz.getSimpleName() + " 类没有发现@Module注解，请确认是否属于编排流程";
            throw new NervousFrameException(NervousFrameExceptionEnum.CLASS_NO_METHOD_FOUND.getErrorCode(), message);
        }
    }


    public static class ModuleNameBuilder {
        public static String buildModuleName(Class clazz, Method method) {
            StringBuilder name = new StringBuilder();
            name.append(clazz.getName());
            name.append(".");
            name.append(method.getName());
            return name.toString();
        }
    }
}
