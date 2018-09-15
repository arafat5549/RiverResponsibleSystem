package com.jqm.ssm.dto;

import java.util.List;

/**
 * Created by wangyaoyao on 2018/9/15.
 */
public class EchartData<T> {
    private String title;
    private String subTitle;
    private List<T> data;
    private List<String> legend;
}
