package example.com.mvvm.bean;

/**
 * Created by DELL on 2018/2/3.
 */

public class App {
    /**
     * app名字
     */
    private String appName;
    /**
     * app版本
     */
    private String version;

    /**
     * app作者
     */
    private String userName;
    /**
     * app Github地址
     */
    private String gitHub;

    public App(String appName,String version,String userName,String gitHub) {
        this.appName=appName;
        this.version=version;
        this.gitHub=gitHub;
        this.userName=userName;
    }

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    public String getUserName() {
        return userName;
    }

    public String getGitHub() {
        return gitHub;
    }
}
