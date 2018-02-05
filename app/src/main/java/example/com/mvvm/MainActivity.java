package example.com.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import example.com.mvvm.adapter.MyBaseAdapter;
import example.com.mvvm.bean.Food;
import example.com.mvvm.bean.User;
import example.com.mvvm.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    private List<Food> foods;
    private ListView lv;
    private MainActivity mActivity;
    private MyBaseAdapter<Food> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //用 DatabindingUtil.setContentView() 来替换掉 setContentView()
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        mActivity = this;

        User user = new User("testFirst", "testLast");
        binding.setUser(user);
        binding.setFood(new Food("11", "张三","http://img2.cache.netease.com/auto/2016/7/28/201607282215432cd8a.jpg","22","11"));
        testJsoup();
        initView();
        initData();

    }
    private void initView() {
        lv = (ListView) findViewById(R.id.lv);

    }
    private void initData() {
        if (foods == null) {
            foods = new ArrayList<>();
        }
        for (int i = 0; i < 30; i++) {
            foods.add(new Food("小明", "一花一世界", "http://pic.sc.chinaz.com/files/pic/pic9/201412/apic8065.jpg","222","11"));
        }
        if ( adapter== null) {
            adapter = new MyBaseAdapter<>(mActivity, R.layout.food_item, BR.food, foods);
            lv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }


    }

    public void testJsoup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                String pageNumber="66";
        try {
            //从一个URL加载一个Document对象。
            doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe-page-"+pageNumber+".html").get();
            //选择“美食天下”所在节点
            //Elements elements = doc.select("div.top-bar");

            //“椒麻鸡”和它对应的图片都在<div class="pic">中
            Elements elementsPic = doc.select("div.pic");
            Elements elementsDetail=doc.select("div.detail");
            for (Element element : elementsPic) {
                String title = element.select("a").attr("title");
                String picture = element.select("a").select("img").attr("data-src");

                Log.i("mytag", "title:" + title + "pic:" + picture);

            }
            for (Element element:elementsDetail){
                String title=element.select("h2").select("a").html();
                String author=element.select("p").select("a").html();
                String content =element.select("p.subcontent").html();
                Log.i("mytag", "title"+title+"author:" + author + "  content:"+content);
            }
            //使用Element.select(String selector)查找元素，使用Node.attr(String key)方法取得一个属性的值
            //Log.i("mytag", "title:" + titleAndPics.get(1).select("a").attr("title") + "pic:" + titleAndPics.get(1).select("a").select("img").attr("data-src"));
            //打印 <a>标签里面的title
            //Log.i("mytag",elements.select("a").attr("title"));
        }catch(Exception e) {
            Log.i("mytag", e.toString());
        }
    }

}).start();
    }
}
