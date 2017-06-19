package com.zhongkebochuang.blasthelper;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongkebochuang.blasthelper.uitils.ImageTool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhongkebochuang.blasthelper.uitils.ImageTool.getPath;

public class FaTieActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int IMG_COUNT = 8;
    private static final String IMG_ADD_TAG = "a";
    @Bind(R.id.newThreadSubject)
    EditText newThreadSubject;
    @Bind(R.id.newThreadMessage)
    EditText newThreadMessage;
    private GridView gridView;
    private GVAdapter adapter;
    private ImageButton textView;
    private ImageView img,newThreadBackBtn;
    private List<String> list;
    private TextView text_finish;
    private TextView text_pick_photo;
    private TextView text_take_photo;
    private Dialog dialog;
    String mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";//图片路径


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatie);
        ButterKnife.bind(this);
        initview();
        initData();
    }

    private void initview() {
        newThreadBackBtn = (ImageView) findViewById(R.id.newThreadBackBtn);
        gridView = (GridView) findViewById(R.id.gridview);
        textView = (ImageButton) findViewById(R.id.imageButton1);
        newThreadBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FaTieActivity.this,BaoPoQuanActivity.class);
                startActivity(in);
                FaTieActivity.this.finish();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messagetiele = newThreadSubject.getText().toString();
                String message = newThreadMessage.getText().toString();
                Intent intent = new Intent(FaTieActivity.this,BaoPoQuanActivity.class);
                intent.putExtra("id",1);
                intent.putExtra("messagetiele",messagetiele);
                intent.putExtra("message",message);
                startActivity(intent);
                Toast.makeText(FaTieActivity.this, "发送成功" + Integer.toString(list.size() - 1), Toast.LENGTH_SHORT).show();
//                upLoad();
//                shoupopwend();
            }
        });
    }

    private void upLoad() {
        Bitmap bitmap;
        Bitmap bmpCompressed;
        for (int i = 0; i < list.size() - 1; i++) {
            bitmap = BitmapFactory.decodeFile(list.get(i));
            bmpCompressed = Bitmap.createScaledBitmap(bitmap, 640, 480, true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bmpCompressed.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] data = bos.toByteArray();
            Toast.makeText(FaTieActivity.this, "" + data, Toast.LENGTH_SHORT).show();
        }

    }

    //TODO 底部弹出菜单栏
    private void shoupopwend() {
        dialog = new Dialog(this, R.style.DialogStyleBottom);
        View view = LayoutInflater.from(this).inflate(R.layout.paizhao_listview, null);
        text_finish = (TextView) view.findViewById(R.id.text_finish);
        text_pick_photo = (TextView) view.findViewById(R.id.text_pick_photo);
        text_take_photo = (TextView) view.findViewById(R.id.text_take_photo);
        text_finish.setOnClickListener(this);
        text_pick_photo.setOnClickListener(this);
        text_take_photo.setOnClickListener(this);
        dialog.setContentView(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        view.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        dialog.show();
    }


    private void initData() {
        if ( list == null ) {
            list = new ArrayList<>();
            list.add(IMG_ADD_TAG);
        }
        adapter = new GVAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ( list.get(position).equals(IMG_ADD_TAG) ) {
                    if ( list.size() < IMG_COUNT ) {
                        shoupopwend();
                    } else
                        Toast.makeText(FaTieActivity.this, "最多只能选择7张照片！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        refreshAdapter();
    }

    private void refreshAdapter() {
        if ( list == null ) {
            list = new ArrayList<>();
        }
        if ( adapter == null ) {
            adapter = new GVAdapter();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_finish:
                dialog.dismiss();
                break;
            case R.id.text_pick_photo:
                if ( PackageManager.PERMISSION_GRANTED == ContextCompat.
                        checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {
                    choosePhoto();
                } else {
                    //提示用户开户权限
                    String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE"};
                    ActivityCompat.requestPermissions(this, perms, 0);
                }
                break;
            case R.id.text_take_photo:
                /**android 6.0 权限申请**/
                if ( ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED ) {
                    //请求权限
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA, "android.permission.WRITE_EXTERNAL_STORAGE"}, 8);
                    //判断是否需要 向用户解释，为什么要申请该权限
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_CONTACTS);
                } else {
                    takePhoto();
                }
                break;
        }
    }

    /**
     * 拍照
     */
    void takePhoto() {
        dialog.dismiss();
        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //下面这句指定调用相机拍照后的照片存储的路径
        File temp = new File(mTempPhotoPath);
        Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(takeIntent, 11);
        Log.e("拍照之后路径“1", "" + Uri.fromFile(new File(mTempPhotoPath)));
    }

    /**
     * 从相册选取图片
     */
    void choosePhoto() {
        dialog.dismiss();
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 0);
    }

    private class GVAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if ( convertView == null ) {
                convertView = LayoutInflater.from(getApplication()).inflate(R.layout.activity_add_photo_gv_items, parent, false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.main_gridView_item_photo);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.main_gridView_item_cb);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String s = list.get(position);
            if ( !s.equals(IMG_ADD_TAG) ) {
                holder.checkBox.setVisibility(View.VISIBLE);
                holder.imageView.setImageBitmap(ImageTool.createImageThumbnail(s));
            } else {
                holder.checkBox.setVisibility(View.GONE);
                holder.imageView.setImageResource(R.mipmap.ic_launcher);
            }
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    refreshAdapter();
                }
            });
            return convertView;
        }

        private class ViewHolder {
            ImageView imageView;
            CheckBox checkBox;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                try {
                    final Uri uri = data.getData();
                    String path = getPath(this, uri);
                    System.out.println(path);
                    if ( list.size() == IMG_COUNT ) {
                        removeItem();
                        refreshAdapter();
                        return;
                    }
                    removeItem();
                    list.add(path);
                    list.add(IMG_ADD_TAG);
                    refreshAdapter();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("tag", e.getMessage());
                }

                break;
            case 11:
                if ( resultCode == Activity.RESULT_OK ) {
                    if ( data == null ) {
                        try {
                            Uri uri1 = Uri.parse((String) mTempPhotoPath);
//                        String path1 = getImageAbsolutePath(this, uri1);
                            String path1 = uri1.toString();
                            if ( list.size() == IMG_COUNT ) {
                                removeItem();
                                refreshAdapter();
                                return;
                            }
                            removeItem();
                            list.add(path1);
                            list.add(IMG_ADD_TAG);
                            refreshAdapter();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("tag", e.getMessage());
                        }
                    }

                }
                break;
        }

    }

    private void removeItem() {
        if ( list.size() != IMG_COUNT ) {
            if ( list.size() != 0 ) {
                list.remove(list.size() - 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //权限申请结果
        if ( requestCode == 8 ) {
            for (int index = 0; index < permissions.length; index++) {
                switch (permissions[index]) {
                    case Manifest.permission.CAMERA:
                        if ( grantResults[index] == PackageManager.PERMISSION_GRANTED ) {
                            /**用户已经受权*/
                            takePhoto();
                        } else if ( grantResults[index] == PackageManager.PERMISSION_DENIED ) {
                            /**用户拒绝了权限*/
                            Toast.makeText(this, "应用没有拍照权限，请授权！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "应用没有拍照权限，请授权！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        } else if ( requestCode == 0 ) {
            for (int index = 0; index < permissions.length; index++) {
                switch (permissions[index]) {
                    case Manifest.permission.CAMERA:
                        if ( grantResults[index] == PackageManager.PERMISSION_GRANTED ) {
                            /**用户已经受权*/
                            choosePhoto();
                        } else if ( grantResults[index] == PackageManager.PERMISSION_DENIED ) {
                            /**用户拒绝了权限*/
                            Toast.makeText(this, "应用没有拍照权限，请授权！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "应用没有拍照权限，请授权！", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        }
    }


}
