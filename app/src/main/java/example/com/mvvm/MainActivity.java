package example.com.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import example.com.mvvm.bean.User;
import example.com.mvvm.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //用 DatabindingUtil.setContentView() 来替换掉 setContentView()
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("testFirst", "testLast");
        binding.setUser(user);

        testJsoup();


    }
    public void testJsoup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
        try {
            //从一个URL加载一个Document对象。
            doc = Jsoup.connect("http://home.meishichina.com/show-top-type-recipe.html").get();
            //选择“美食天下”所在节点
            Elements elements = doc.select("div.top-bar");

            //“椒麻鸡”和它对应的图片都在<div class="pic">中
            Elements titleAndPic = doc.select("div.pic");
            //使用Element.select(String selector)查找元素，使用Node.attr(String key)方法取得一个属性的值
            Log.i("mytag", "title:" + titleAndPic.get(1).select("a").attr("title") + "pic:" + titleAndPic.get(1).select("a").select("img").attr("data-src"));
            //打印 <a>标签里面的title
            Log.i("mytag",elements.select("a").attr("title"));
        }catch(Exception e) {
            Log.i("mytag", e.toString());
        }
    }

}).start();
    }
}
