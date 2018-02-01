package example.com.mvvm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import example.com.mvvm.adapter.MyBaseAdapter;
import example.com.mvvm.base.PowerfulFragment;
import example.com.mvvm.bean.Food;

public class FoodFragment extends PowerfulFragment {

    private List<Food> foods;
    private ListView lv;
    private MyBaseAdapter<Food> adapter;
    private String name;
    private String material;
    private String foodPhoto;
    private Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    adapter.notifyDataSetChanged(); //发送消息通知ListView更新
                    lv.setAdapter(adapter); // 重新设置ListView的数据适配器
                    break;
                default:
                    //do something
                    break;
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_food;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        lv = (ListView) view.findViewById(R.id.lv);
        testJsoup();


    }

    private void setFoodData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
    public void testJsoup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (foods == null) {
                    foods = new ArrayList<>();
                }

                Document doc = null;
                String pageNumber="66";
                try {
                    //从一个URL加载一个Document对象。
                    doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe-page-"+pageNumber+".html").get();
                    //选择“美食天下”所在节点
                    Elements elements = doc.select("div.ui_newlist_1").select("ul").select("li");

                    for (Element element : elements) {

                        name = element.select("div.pic").select("a").attr("title");
                        material="\u3000\u3000"+element.select("div.detail").select("p.subcontent").html();
                        foodPhoto = element.select("div.pic").select("img").attr("data-src");
                        foods.add(new Food(name,material,foodPhoto));
                        Log.i("mytag", "name:" + name + "material:"+material+"pic:" + foodPhoto);

                    }
                    if (adapter== null) {
                        adapter = new MyBaseAdapter<>(mActivity, R.layout.food_item, BR.food, foods);
                    }
                    hander.sendEmptyMessage(0); // 下载完成后发送处理消息
                }catch(Exception e) {
                    Log.i("mytag", e.toString());
                }
            }

        }).start();
    }
    @Override
    protected void waitMethod(int flag) {

    }

    @Override
    public void onClick(View v) {

    }
}
