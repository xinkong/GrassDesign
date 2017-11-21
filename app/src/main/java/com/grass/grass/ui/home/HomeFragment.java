package com.grass.grass.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.grass.grass.R;
import com.grass.grass.base.BaseMVPFragment;
import com.grass.grass.contract.home.Home;
import com.grass.grass.entity.MsgInfoEntity;
import com.grass.grass.presenter.home.HomePersenter;
import com.grass.grass.ui.adapter.base.BaseQuickAdapter;
import com.grass.grass.ui.adapter.base.BaseViewHolder;
import com.grass.grass.utils.AppUtils;
import com.grass.grass.view.MultiImageView;

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
        showLoadingView("正在加载中...");
        mPresenter.loadData(0,10);

    }

    private void initView() {
        mList.setLayoutManager(new LinearLayoutManager(mContext()));
        mAdapter = new BaseQuickAdapter<MsgInfoEntity>(mContext(),R.layout.item_home_list) {
            @Override
            public void convert(BaseViewHolder viewHolder, MsgInfoEntity data, int position) {
                TextView userName = viewHolder.getView(R.id.userName);
                TextView content = viewHolder.getView(R.id.content);
                MultiImageView images = viewHolder.getView(R.id.images);

                userName.setText(data.userInfo.userName);
                content.setText(data.msgContent);
                ArrayList<String> thumbnaiUrl = new ArrayList<>();
                //有后台保证data.imagesInfo不为空
                for (MsgInfoEntity.ImagesInfo infos : data.imagesInfo){
                    thumbnaiUrl.add(infos.imagemThumbnailUrl);
                }
                images.setList(thumbnaiUrl);

            }
        };
        mList.setAdapter(mAdapter);
//        mAdapter.setOnRecylyViewItemClickListener(position -> AppUtils.toast(mContext(),position+""));
    }


    @Override
    public void onItemMenuClick(MenuItem item) {
        super.onItemMenuClick(item);
        AppUtils.jump(mContext(), AddMsgActivity.class);
    }


    @Override
    public void loadDataOk(List<MsgInfoEntity> msgInfoEntity) {
        mAdapter.addAll(msgInfoEntity);
    }

    @Override
    public void onRetry() {
        super.onRetry();
        mPresenter.loadData(0,10);
    }
}
