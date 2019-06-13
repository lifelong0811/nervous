package org.nervous.layout.registry;

import lombok.Data;

@Data
public class ParamInfo {
    /**
     * param的名字;
     */
    private String name;

    /**
     * param的具体类型信息;
     */
    private Class paramType;

    /**
     * 其所映射到的param名字;
     */
    private String mapperName;

    /**
     * 其所映射到具体字段信息;
     * 如果映射整个类，可为空;
     */
    private String mapperMeber;

}
