package com.jqm.ssm.enums;

/**
 * Created by wangyaoyao on 2018/9/1.
 */
public enum CategoryEnum
{
    CATEGORY_WATERSCAN(1,"检测设备"),
    CATEGORY_MONITOR(7,"视频");

    private int state;
    private String msg;

    CategoryEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int getState() {
        return state;
    }
    public String getMsg() {
        return msg;
    }

    public static CategoryEnum stateOf(int index) {
        for (CategoryEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
