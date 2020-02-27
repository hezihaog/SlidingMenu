package com.zh.android.slidingmenu.sample.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zh.android.slidingmenu.sample.R;
import com.zh.android.slidingmenu.sample.model.ListItemModel;

import me.drakeet.multitype.ItemViewBinder;


/**
 * <b>Package:</b> com.zh.android.swipemenulayoutsample.item <br>
 * <b>Create Date:</b> 2020/2/26  5:16 PM <br>
 * <b>@author:</b> zihe <br>
 * <b>Description:</b> 条目类 <br>
 */
public class ListItemViewBinder extends ItemViewBinder<ListItemModel, ListItemViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull ListItemModel item) {
        holder.vContent.setText(item.getContent());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView vContent;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            vContent = itemView.findViewById(R.id.content);
        }
    }
}