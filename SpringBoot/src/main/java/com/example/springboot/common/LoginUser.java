package com.example.springboot.common;

/**
 * ClassName:LoginUser
 * Package: com.example.springboot
 * Description:
 * LoginUser 是一个访问计数工具类，用于记录系统被访问的总次数。
 * @Author WXK
 * @Create 2025/4/9 15:38
 * @Version 1.0
 */

public class LoginUser {

    /**
     * 静态访问计数器。
     * 由于是 static 类型，整个程序运行期间共享一份数据，不随对象实例化而改变。
     */
    private static int visitCount = 0;

    /**
     * 将访问次数加一。
     * 每当用户访问系统时（如打开首页或登录成功），可调用此方法统计一次访问。
     */
    public static void addVisitCount() {
        LoginUser.visitCount++;  // 增加一次访问记录
    }

    /**
     * 获取当前累计的访问次数。
     *
     * @return 系统当前的总访问次数
     */
    public static int getVisitCount() {
        return LoginUser.visitCount;  // 返回总访问次数
    }
}