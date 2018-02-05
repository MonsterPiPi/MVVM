package example.com.mvvm.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import example.com.mvvm.R;


/**
 * 基础activity
 * Created by DELL on 2017/11/15.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{

    public  SweetAlertDialog pDialog;
    private static int RESULT_LOAD_IMAGE = 1;
    public static final int error=SweetAlertDialog.ERROR_TYPE;
    public static final int success=SweetAlertDialog.SUCCESS_TYPE;
    public static final int warning=SweetAlertDialog.WARNING_TYPE;
    public static final int image=SweetAlertDialog.CUSTOM_IMAGE_TYPE;
    public static final int nomarl=SweetAlertDialog.NORMAL_TYPE;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            //设置状态栏文字颜色及图标为深色
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        initView();
        initData();
        initListener();
    }
    public void wait(int time){
        new Handler().postDelayed(new Runnable() {
            @Override
            public  void run() {
                /**
                 * 延时执行的代码
                 */
                //waitMethod();
            }
        },time); // 延时
    }
    protected abstract void initView();

    protected abstract void initData();

    public abstract void initListener();
    // 判断是否有网络
    public NetworkInfo getNetworkInfo(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }


    public void action(Context context,String activityName) throws ClassNotFoundException {
        Intent intent =new Intent();
        intent.setPackage(getPackageName());
        Class clazz = Class.forName(activityName);
        intent.setClass(context, clazz);
        startActivity(intent);
    }

    /**
     * dialog
     */
    public  void showProgressDialog(String str){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#486B8A"));
        pDialog.setContentText(str);
        pDialog.setTitleText(" ");
        pDialog.setCancelable(false);
        pDialog.show();
    }
    /**
     * 停止等待
     */
    public void dismissProgressDialog(){
        pDialog.dismiss();
    }

    /**
     *showSweetAlertDialog
     */
    public void showSweetAlertDialog(String title, String context, final int type){
        new SweetAlertDialog(this, type)
                .setTitleText(title)
                .setContentText(context)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        waitMethod(type);
                    }
                })
                .show();
    }
    protected abstract void waitMethod(int flag);

    // SnackBar 长时间
    public void showSnackBar(String msg){
        View cv = getWindow().getDecorView();
        Snackbar.make(cv, msg, Snackbar.LENGTH_SHORT).show();
    }
    // Toast 长时间
    public void showShort(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
