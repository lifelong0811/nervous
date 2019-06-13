package org.nervous.layout.registry;

import com.google.common.collect.Multimap;
import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Module Holder中实际保存的内容;
 */
@Data
public class ModuleNode {

    /**
     * 需要执行的方法;
     */
    private Method method;


    /**
     * 其依赖的module;
     * 以module名称表示;
     */
    private List<String> dependencyOn;

    /**
     * 其依赖的Module;
     * 以moduleNode表示;
     */
    private List<ModuleNode> predecessorModuleList;


    /**
     * 参数类型;
     */
    private List<ParamInfo> paramInfoList;




    /**
     * 返回值类型;
     */
    private Class returnType;


    /**
     * 返回值名字;
     */
    private String returnName;


    /**
     * module的唯一名;
     * module的前路径名;
     */
    private String name;


    /**
     * module的别名;
     */
    private String aliasName;


    /**
     * 构造模块的请求参数的函数;
     */
    private Method requestBuildMethod;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleNode that = (ModuleNode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
