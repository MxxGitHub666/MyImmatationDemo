package test.plugin.com.cameralibrary.global;

import java.io.File;

import test.plugin.com.cameralibrary.utils.FileUtils;

/**
 * Created by 98179 on 2019/8/26.
 */

public class Constant {
//
public static final String APP_NAME = "MyImmatationDemo";//app名称
    public static final String BASE_DIR = APP_NAME + File.separator;//WildmaIDCardCamera/
    public static final String DIR_ROOT = FileUtils.getRootPath() + File.separator + Constant.BASE_DIR;//文件夹根目录 /storage/emulated/0/WildmaIDCardCamera/

}
