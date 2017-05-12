package com.ecloud.rcsd.bean;

/**
 * Rcsd
 * Create   2017/5/12 18:22;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class AreaBean  {
    String type ;
    String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AreaBean{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
