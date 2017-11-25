package com.grass.grass.ui.my;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.grass.grass.R;
import com.grass.grass.app.BaseApplication;
import com.grass.grass.app.Constants;
import com.grass.grass.base.BaseMVPFragment;
import com.grass.grass.contract.my.MyInfo;
import com.grass.grass.entity.UserEntity;
import com.grass.grass.presenter.my.MyInfoPersenter;
import com.grass.grass.ui.login.LoginActivity;
import com.grass.grass.utils.AppUtils;
import com.grass.grass.utils.GlideImageLoader;
import com.grass.grass.utils.ImageLoadUtils;
import com.grass.grass.utils.SharePrefsUtils;
import com.grass.grass.view.MyPressView;
import com.grass.grass.view.SelectDialog;
import com.grass.imagepicker.ImagePicker;
import com.grass.imagepicker.bean.ImageItem;
import com.grass.imagepicker.ui.ImageGridActivity;
import com.grass.imagepicker.view.CropImageView;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.grass.grass.ui.home.AddMsgActivity.REQUEST_CODE_SELECT;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfoFragment extends BaseMVPFragment<MyInfoPersenter> implements MyInfo.MyInfoView {

    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.exitLogin)
    MyPressView mExitLogin;
    @BindView(R.id.headPic)
    ImageView mHeadPic;

    public static MyInfoFragment getInstance() {
        return new MyInfoFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public String getThisPageTitle() {
        return "我的";
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
        return R.layout.fragment_my_info;
    }

    @Override
    public void onFragmentStart() {
        
        initImagePick();
        
        mUserName.setText(SharePrefsUtils.getInstance().getString(Constants.UserName,""));
        mExitLogin.setOnClickListener(view -> exit());
        ImageLoadUtils.getInstance().loadCircleImage(SharePrefsUtils.getInstance().getString(Constants.UserHeadUrl,""),mHeadPic);
        mHeadPic.setOnClickListener(view ->changeHeadPic());
    }

    private void initImagePick() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());     //设置图片加载器
        imagePicker.setShowCamera(true);                        //显示拍照按钮
        imagePicker.setMultiMode(false);                        //设置单选模式
        imagePicker.setCrop(true);                              //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                     //是否按矩形区域保存
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);    //裁剪框的形状
        imagePicker.setFocusWidth(BaseApplication.SCREEN_WIDTH);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight((int) (BaseApplication.SCREEN_WIDTH/1.8));                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素

        imagePicker.clear();
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog((Activity) mContext(), R.style.transparentFrameWindowStyle, listener, names);
        if (!((Activity)mContext()).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    private void changeHeadPic() {

        new RxPermissions((Activity) mContext())
                .request(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted ->{
                    if(granted){//所有权限都同意了
                        List<String> names = new ArrayList<>();
                        names.add("拍照");
                        names.add("相册");
                        showDialog((parent, view, position, id) -> dialogItemClickLis(position), names);
                    }else {
                        AppUtils.toast(mContext(),"请打开所需要的权限");
                    }
                });



    }

    private void dialogItemClickLis(int position) {
        switch (position) {
            case 0: // 直接调起相机
                Intent intent = new Intent(mContext(), ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            case 1:

                Intent intent1 = new Intent(mContext(), ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                break;
            default:
                break;
        }
    }

    private void exit() {
        SharePrefsUtils.getInstance().remove(Constants.UserId);
        AppUtils.jump(mContext(), LoginActivity.class);
        ((Activity) mContext()).finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                ArrayList<ImageItem> items = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                ImageLoadUtils.getInstance().loadLocaltionPic(items.get(0).path,mHeadPic);
                mPresenter.updatUserHeadPic(items.get(0).path);
            }
        }
    }

    @Override
    public void updateUserHeadOk() {
        ImageLoadUtils.getInstance().loadCircleImage(SharePrefsUtils.getInstance().getString(Constants.UserHeadUrl,""),mHeadPic);
    }
}
