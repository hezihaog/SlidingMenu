package com.zh.android.slidingmenu.sample;

import android.animation.FloatEvaluator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zh.android.slidingmenu.sample.item.ListItemViewBinder;
import com.zh.android.slidingmenu.sample.model.ListItemModel;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author wally
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView vMenuList;
    private RecyclerView vContentList;
    private View vContentBg;
    private SlidingMenu vSlidingMenu;

    /**
     * 透明度估值器
     */
    private FloatEvaluator mAlphaEvaluator;

    /**
     * 菜单
     */
    private Items mMenuListItems = new Items();
    private MultiTypeAdapter mMenuListAdapter;
    /**
     * 内容
     */
    private Items mContentListItems = new Items();
    private MultiTypeAdapter mContentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        bindView();
    }

    private void findView() {
        vSlidingMenu = findViewById(R.id.sliding_menu);
        vMenuList = findViewById(R.id.menu_list);
        vContentList = findViewById(R.id.content_list);
        vContentBg = findViewById(R.id.content_bg);
    }

    private void bindView() {
        setupMenuList();
        setupContentList();
        setupSlidingMenu();
    }

    private void setupMenuList() {
        mMenuListAdapter = new MultiTypeAdapter(mMenuListItems);
        mMenuListAdapter.register(ListItemModel.class, new ListItemViewBinder());
        vMenuList.setLayoutManager(new LinearLayoutManager(this));
        vMenuList.setAdapter(mMenuListAdapter);
        vMenuList.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        for (int i = 0; i < 25; i++) {
            mMenuListItems.add(new ListItemModel("菜单" + i));
        }
        mMenuListAdapter.notifyDataSetChanged();
    }

    private void setupContentList() {
        mContentListAdapter = new MultiTypeAdapter(mContentListItems);
        mContentListAdapter.register(ListItemModel.class, new ListItemViewBinder());
        vContentList.setLayoutManager(new LinearLayoutManager(this));
        vContentList.setAdapter(mContentListAdapter);
        vContentList.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        for (int i = 0; i < 25; i++) {
            mContentListItems.add(new ListItemModel("内容" + i));
        }
        mContentListAdapter.notifyDataSetChanged();
    }

    private void setupSlidingMenu() {
        mAlphaEvaluator = new FloatEvaluator();
        vSlidingMenu.setOnMenuStateChangeListener(new SlidingMenu.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen() {
                Log.d(TAG, "菜单打开");
                //禁用触摸
                vContentBg.setClickable(true);
            }

            @Override
            public void onSliding(float fraction) {
                Log.d(TAG, "菜单拽托中，百分比：" + fraction);
                float startValue = 0;
                float endValue = 0.55f;
                Float value = mAlphaEvaluator.evaluate(fraction, startValue, endValue);
                vContentBg.setAlpha(value);
            }

            @Override
            public void onMenuClose() {
                Log.d(TAG, "菜单关闭");
                //恢复触摸
                vContentBg.setClickable(false);
            }
        });
    }
}