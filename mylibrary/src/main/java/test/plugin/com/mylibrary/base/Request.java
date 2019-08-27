package test.plugin.com.mylibrary.base;

import test.plugin.com.mylibrary.listener.PermissListener;

/**
 * Created by 98179 on 2019/8/21.
 */

public interface Request {

    Request listener(PermissListener<String> granted);

    Request permission(String... permissions);

    /**
     * 开始请求
     */
    void start();

}
