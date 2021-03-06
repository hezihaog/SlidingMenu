package com.zh.android.slidingmenu.sample.model;

import java.io.Serializable;

/**
 * <b>Package:</b> com.zh.android.swipemenulayoutsample.model <br>
 * <b>Create Date:</b> 2020/2/27  10:53 AM <br>
 * <b>@author:</b> zihe <br>
 * <b>Description:</b> 列表条目模型 <br>
 */
public class ListItemModel implements Serializable {
    /**
     * 内容
     */
    private String content;

    public ListItemModel(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public ListItemModel setContent(String content) {
        this.content = content;
        return this;
    }
}