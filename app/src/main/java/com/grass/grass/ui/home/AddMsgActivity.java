package com.grass.grass.ui.home;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;

import com.grass.grass.R;
import com.grass.grass.app.Constants;
import com.grass.grass.base.BaseMvpActivity;
import com.grass.grass.base.RxPresenter;
import com.grass.grass.contract.login.AddMsg;
import com.grass.grass.presenter.home.AddMsgPersenter;
import com.grass.grass.ui.adapter.AddMsgSelPicAdapter;
import com.grass.grass.utils.AppUtils;
import com.grass.grass.utils.GlideImageLoader;
import com.grass.grass.utils.RxBus;
import com.grass.imagepicker.ImagePicker;
import com.grass.imagepicker.bean.ImageItem;
import com.grass.imagepicker.ui.ImageGridActivity;
import com.grass.imagepicker.ui.ImagePreviewDelActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.grass.grass.app.Constants.RequestCode.REQUEST_CODE_PREVIEW;
import static com.grass.grass.app.Constants.RequestCode.REQUEST_CODE_SELECT;

public class AddMsgActivity extends BaseMvpActivity<AddMsgPersenter> implements AddMsg.AddMsgView {



    @BindView(R.id.sendContent)
    EditText mSendContent;

    @BindView(R.id.selPicList)
    RecyclerView mSelPicList;
    AddMsgSelPicAdapter mAdapter;

    List<String> mImagePaths;
    ArrayList<ImageItem> images = new ArrayList<>();
    int mMaxSelPic = 9;


    @Override
    public String getThisPageTitle() {
        return "发布消息";
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_add_msg;
    }

    @Override
    public void onActivityStart() {
        mImagePaths = new ArrayList<>();
        getToolbar().inflateMenu(R.menu.menu_send);

        initImagePick();
        initView();

    }

    private void initView() {
        mSelPicList.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new AddMsgSelPicAdapter(this, R.layout.item_sel_image, mMaxSelPic);
        mAdapter.setSelImages(mImagePaths);
        mSelPicList.setAdapter(mAdapter);
        mAdapter.setOnRecylyViewItemClickListener(position -> {
            String path = mAdapter.getData(position);
            if (AddMsgSelPicAdapter.defaultPic.equals(path)) {
                AppUtils.hideSoftInput(this);
                //权限判断
                new RxPermissions(this)
                        .request(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted ->{
                            if(granted){//所有权限都同意了
                                ImagePicker.getInstance().setSelectLimit(mMaxSelPic-mImagePaths.size());
                                Intent intent = new Intent(this, ImageGridActivity.class);
//                intent.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                            }else {
                                AppUtils.toast(this,"请打开所需要的权限");
                            }
                        });

            }else {
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, images);
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
            }
        });
    }

    private void initImagePick() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(true);                        //设置多选
//        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSelectLimit(mMaxSelPic);              //选中数量限制
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    @Override
    public void onItemMenuClick(MenuItem item) {
        super.onItemMenuClick(item);
        String content = mSendContent.getText().toString();
        if (TextUtils.isEmpty(content) && mImagePaths.size() == 0) {
            AppUtils.toast(mContext(), "不能发行空内容");
            return;
        }
        mPresenter.sendMsg(content, mImagePaths);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE_SELECT){
            images.addAll((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
            imageItemToPath();
        }else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images.clear();
                images.addAll((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS));
                imageItemToPath();
            }
        }
    }

    private void imageItemToPath() {

        mImagePaths.clear();
        for(ImageItem imageItem : images){
            mImagePaths.add(imageItem.path);
        }
        mAdapter.setSelImages(mImagePaths);
    }

    @Override
    public void sendSuccess() {
        AppUtils.toast(mContext(), "发送成功");
        RxBus.get().post(Constants.RxBusKeyMap.SendMsgOk);
        finish();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }
}
