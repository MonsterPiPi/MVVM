package example.com.mvvm.bean;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;


/**
 * Created by DELL on 2018/1/31.
 */

public class Food extends BaseObservable implements Serializable {
    /**
     * 美食名称
     */
    private  String name;
    /**
     * 美食材料
     */
    private  String material;
    /**
     * 美食概述
     */
    private String materialOther;
    /**
     * 美食厨具
     */
    private String kitchenware;
    /**
     * 美食图片
     */
    private  String foodPhoto;
    /**
     * 美食详情界面的url
     */
    private String url;

    /**
     * 美食作者
     */
    private String author;
    public Food(String name,String material,String foodPhoto,String url,String author){
        this.name=name;
        this.material=material;
        this.foodPhoto=foodPhoto;
        this.url=url;
        this.author=author;
    }

    /**
     * 绑定网络图片
     * @param iv
     * @param foodPhoto
     */
    @BindingAdapter("foodPhoto")
    public static void getInternetImage(ImageView iv, String foodPhoto) {
        Picasso.with(iv.getContext())
                .load(foodPhoto)
                .resize(1200,1200)
                .into(iv);
    }

    /**
     * ListView点击事件
     * @param view
     */
    public void onItemClick(View view) {
        Toast.makeText(view.getContext(), getUrl(), Toast.LENGTH_SHORT).show();

    }

    public String getName() {
        return name;
    }


    public String getMaterial() {
        return material;
    }


    public String getFoodPhoto() {
        return foodPhoto;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public String getMaterialOther() {
        return materialOther;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setMaterialOther(String materialOther) {
        this.materialOther = materialOther;
    }

    public void setFoodPhoto(String foodPhoto) {
        this.foodPhoto = foodPhoto;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKitchenware() {
        return kitchenware;
    }

    public void setKitchenware(String kitchenware) {
        this.kitchenware = kitchenware;
    }
}
