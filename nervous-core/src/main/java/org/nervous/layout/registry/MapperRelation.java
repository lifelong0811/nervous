package org.nervous.layout.registry;

import lombok.Data;

@Data
public class MapperRelation {


    /**
     * 需要映射饿到param的全路径;
     */
    private String mapperName;

    /**
     * 需要映射到的module的名字;
     */
    private String mapperNodeName;

    /**
     * 本module的名字;
     */
    private String myselfNodeName;

    /**
     * 1 request;
     * 2 response;
     */
    private int type;
}
