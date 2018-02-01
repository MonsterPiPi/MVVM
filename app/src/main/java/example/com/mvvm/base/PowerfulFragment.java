package example.com.mvvm.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 基础Fragment
 * Created by DELL on 2017/11/15.
 */

public abstract class PowerfulFragment extends Fragment implements View.OnClickListener{

    protected Activity mActivity;
    public  ProgressDialog progressDialog;
    private static int RESULT_LOAD_IMAGE = 1;
    public static final int error=SweetAlertDialog.ERROR_TYPE;
    public static final int success=SweetAlertDialog.SUCCESS_TYPE;
    public static final int warning=SweetAlertDialog.WARNING_TYPE;
    public static final int image=SweetAlertDialog.CUSTOM_IMAGE_TYPE;
    public static final int nomarl=SweetAlertDialog.NORMAL_TYPE;
    /**
     * 获得全局的，防止使用getActivity()为空
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {

        View view = LayoutInflater.from(mActivity)
                .inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    /**
     * 该抽象方法就是 onCreateView中需要的layoutID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 该抽象方法就是 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 该抽象方法是初始化监听
     */
    protected abstract void initListener();
    /**
     * 执行数据的加载
     */
    protected abstract void initData();

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



    // Snakebar
    public void showSnakebar(String msg){
        Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();

    }
    // Toast
    public void showToast(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    // 判断是否有网络
    public NetworkInfo getNetworkInfo(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }
    //fragment跳转
    public void action(int layoutId,Fragment fragment){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(layoutId, fragment, null)
                .addToBackStack(null)
                //.remove(new MyBookFragment())
                .commit();
    }
    //activity跳转
    public void action(Class clazz){
        Intent intent=new Intent();
        intent.setClass(getContext(),clazz);
        startActivity(intent);
    }
    public void setBuddle(String name,String text,Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString(name, text);
        //bundle.putString("cityname",cityName);
        fragment.setArguments(bundle);
    }

    /**
     * 请求链接
     */
    public void GetVolley(String url){

        //Log.i("json",result);

    }
    /**
     * dialog
     */
    public void showProgressDialog(String str){
        progressDialog= new ProgressDialog(getContext());
        progressDialog.setMessage(str);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
    /**
     * 停止等待
     */
    public void dismissProgressDialog(){
        progressDialog.dismiss();
    }

    /**
     *showSweetAlertDialog
     */
    public void showSweetAlertDialog(String title, String context, final int type){
        new SweetAlertDialog(getContext(), type)
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

    /**
     * gson将字符串转换为JavaBean的方法
     * @param text
     * @param clazz
     * @param <T>
     * @return

    public static final <T> T gsonObject(String text, Class<T> clazz){
        T t = new Gson().fromJson(text,clazz);
        return t;
    }
     */
    public void backFragment(){
        ////返回到上一个Fragment（同一个Activity中）
        getActivity().getSupportFragmentManager().popBackStack();
    }

}


