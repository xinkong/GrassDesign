package com.grass.grass.ui.home;

import android.view.MenuItem;
import android.widget.ImageView;

import com.grass.grass.R;
import com.grass.grass.base.BaseMVPFragment;
import com.grass.grass.contract.home.Home;
import com.grass.grass.presenter.home.HomePersenter;
import com.grass.grass.utils.AppUtils;
import com.grass.grass.utils.ImageLoadUtils;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.OkHttpClient;


public class HomeFragment extends BaseMVPFragment<HomePersenter> implements Home.HomeView {

    @BindView(R.id.text)
    ImageView mText;

    @Inject
    public OkHttpClient mOkHttpClient;

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
        Logger.i(mOkHttpClient.toString());
        //设置menu
        getToolbar().inflateMenu(R.menu.menu_add_msg);

//        String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510651501373&di=cae5405106d7399b12fc855d2840fe1c&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fd52a2834349b033bda2800f71ece36d3d539bd7f.jpg";
        String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510661437072&di=0366ed110d72c7c1174db906f2b6ae1d&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F58ee3d6d55fbb2fb61e660c0484a20a44723dc16.jpg";

        ImageLoadUtils.getInstance().loadImage(imageUrl, mText);


    }

    @Override
    public void onItemMenuClick(MenuItem item) {
        super.onItemMenuClick(item);
        AppUtils.jump(mContext(), AddMsgActivity.class);
    }
}
