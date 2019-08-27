package test.plugin.com.mylibrary.listener;

import java.util.List;

/**
 * Created by 98179 on 2019/8/21.
 */

public interface PermissListener<T>{

    void onGranted(List<T> granted);

    void onDenied(List<T> granted);
}
