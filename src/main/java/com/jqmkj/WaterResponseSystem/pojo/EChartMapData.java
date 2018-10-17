package com.jqmkj.WaterResponseSystem.pojo;

/**
 * Created by wangyaoyao on 2018/9/5.
 * echart返回的地图数据对象
  {name: '监测站1',coord: [117.51812,26.771538],symbol:'image://images/sdz.png'},
 {name: '监测站2',coord: [117.56049,26.298622],symbol:'image://images/monitor.png'}
 */
public class EChartMapData {
    private String name;
    private Object value;
    private Float coord[];
    private String symbol;
    private Integer symbolSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Float[] getCoord() {
        return coord;
    }

    public void setCoord(Float[] coord) {
        this.coord = coord;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Integer symbolSize) {
        this.symbolSize = symbolSize;
    }
}
