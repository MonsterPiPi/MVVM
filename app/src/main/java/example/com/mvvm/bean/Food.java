package example.com.mvvm.bean;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by DELL on 2018/1/31.
 */

public class Food extends BaseObservable {
    /**
     * 美食名称
     */
    private  String name;
    /**
     * 美食材料
     */
    private  String material;
    /**
     * 美食图片
     */
    private  String foodPhoto;


    public Food(String name,String material,String foodPhoto){
        this.name=name;
        this.material=material;
        this.foodPhoto=foodPhoto;
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
                .into(iv);
    }

    /**
     * ListView点击事件
     * @param view
     */
    public void onItemClick(View view) {
        Toast.makeText(view.getContext(), getName(), Toast.LENGTH_SHORT).show();
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


}
