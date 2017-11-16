package com.grass.grass.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.grass.grass.R;
import com.grass.grass.base.BaseMVPFragment;
import com.grass.grass.contract.home.Home;
import com.grass.grass.presenter.home.HomePersenter;
import com.grass.grass.ui.adapter.base.BaseQuickAdapter;
import com.grass.grass.ui.adapter.base.BaseViewHolder;
import com.grass.grass.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseMVPFragment<HomePersenter> implements Home.HomeView {

    @BindView(R.id.home_list)
    RecyclerView mList;
    BaseQuickAdapter mAdapter;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public String getThisPageTitle() {
        return "首页";
    }

    @Override
    public boolean isShowTitle() {
        return true;
    }

    @Override
    public boolean isCanBack() {
        return false;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onFragmentStart() {
        //设置menu
        getToolbar().inflateMenu(R.menu.menu_add_msg);

        initView();

    }

    private void initView() {
        mList.setLayoutManager(new LinearLayoutManager(mContext()));
        mAdapter = new BaseQuickAdapter<String>(mContext(),R.layout.item_home_list) {
            @Override
            public void convert(BaseViewHolder viewHolder, String data, int position) {
                TextView test = viewHolder.getView(R.id.test);
                test.setText(data);
            }
        };
        mList.setAdapter(mAdapter);
        mAdapter.setOnRecylyViewItemClickListener(position -> AppUtils.toast(mContext(),position+""));
        mAdapter.addAll(getDatas());
    }


    private List<String> getDatas() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("条目" + i);
        }
        return data;
    }

    @Override
    public void onItemMenuClick(MenuItem item) {
        super.onItemMenuClick(item);
        AppUtils.jump(mContext(), AddMsgActivity.class);
    }


}
