package com.weibin.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * 万能的ViewHolder
 * Created by wei.bin on 2017/8/26.
 */
public class CommonViewHolder {

    /**
     * @param view 所有缓存View的根View
     * @param id   缓存View的唯一标识
     * @return
     */
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        //如果根view没有用来缓存View的集合
        if (viewHolder == null){
            viewHolder = new SparseArray<>();
            //创建集合和根View关联
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        //获取根View储存在集合中的孩纸
        //如果没有该孩纸
        if (childView == null){
            //找到该孩纸
            childView = view.findViewById(id);
            //保存到集合
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
