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
    @BindView(R.id.text2)
    ImageView mText2;
    @BindView(R.id.text3)
    ImageView mText3;

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

        String imageUrll = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510651501373&di=cae5405106d7399b12fc855d2840fe1c&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fd52a2834349b033bda2800f71ece36d3d539bd7f.jpg";
        String imageUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510661437072&di=0366ed110d72c7c1174db906f2b6ae1d&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F58ee3d6d55fbb2fb61e660c0484a20a44723dc16.jpg";
        String imageUrl3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510721220540&di=784a07a26329f1fbe63e8a36bb0b5a05&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D1904166057%2C1739242185%26fm%3D214%26gp%3D0.jpg";

        ImageLoadUtils.getInstance().loadImage(imageUrll, mText);
        ImageLoadUtils.getInstance().loadImage(imageUrl2, mText2);
        ImageLoadUtils.getInstance().loadImage(imageUrl3, mText3);


    }

    @Override
    public void onItemMenuClick(MenuItem item) {
        super.onItemMenuClick(item);
        AppUtils.jump(mContext(), AddMsgActivity.class);
    }
}
