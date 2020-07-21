package com.flonger.get_apps_info;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SystemUtils {
    /**
     * 跳转到应用详情界面
     */
    public static void toSetting(Context context) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            //startActivityForResult(localIntent, 10001);
           context.startActivity(localIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取手机里面所有应用的名称
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public synchronized static List<AppInfoBean> getApplicationName(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<AppInfoBean> appList = new ArrayList<>(); // 用来存储获取的应用信息数据
            try {
                PackageManager pm = context.getPackageManager();
                List<PackageInfo> list2 = pm.getInstalledPackages(0);
                for (PackageInfo packageInfo : list2) {
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        //第三方应用，否则系统应用
                        Log.e("222",packageInfo.applicationInfo.loadIcon(context.getPackageManager()).toString());
                        AppInfoBean appInfoBean = new AppInfoBean();
                        appInfoBean.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                        appInfoBean.setPackageName(packageInfo.packageName);
                        appInfoBean.setVersionName(packageInfo.versionName);
                        appInfoBean.setVersionCode(packageInfo.versionCode);
                        appInfoBean.setLastUpdateTime(packageInfo.lastUpdateTime + "");
                        appInfoBean.setFirstInstallTime(packageInfo.firstInstallTime + "");
                        appInfoBean.setIexpress("0");

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        DrawableToBitmap(packageInfo.applicationInfo.loadIcon(context.getPackageManager())).compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        appInfoBean.setAppicon(baos.toByteArray());

                        appList.add(appInfoBean);
                    }
                }
                return appList;
            } catch (Exception e) {
                e.printStackTrace();
                AppInfoBean appInfoBean = new AppInfoBean();
                appInfoBean.setAppName("Data Exception");
                appList.add(appInfoBean);
                return appList;
            }
        } else {
            ArrayList<AppInfoBean> appList = new ArrayList<>(); // 用来存储获取的应用信息数据
            try {
                PackageManager pm = context.getPackageManager();
                @SuppressLint("WrongConstant") List<PackageInfo> list2 = pm.getInstalledPackages(0);
                for (PackageInfo packageInfo : list2) {
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        //第三方应用，否则系统应用
                        AppInfoBean appInfoBean = new AppInfoBean();
                        appInfoBean.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                        appInfoBean.setPackageName(packageInfo.packageName);
                        appInfoBean.setVersionName(packageInfo.versionName);
                        appInfoBean.setVersionCode(packageInfo.versionCode);
                        appInfoBean.setLastUpdateTime(packageInfo.lastUpdateTime + "");
                        appInfoBean.setFirstInstallTime(packageInfo.firstInstallTime + "");
                        appInfoBean.setIexpress("0");
                        appList.add(appInfoBean);
                    }
                }
                return appList;
            } catch (Exception e) {
                e.printStackTrace();
                AppInfoBean appInfoBean = new AppInfoBean();
                appInfoBean.setAppName("Data Exception");
                appList.add(appInfoBean);
                return appList;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public synchronized static List<Map> getNewApplicationName(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<Map> appList = new ArrayList<>(); // 用来存储获取的应用信息数据
            try {
                PackageManager pm = context.getPackageManager();
                List<PackageInfo> list2 = pm.getInstalledPackages(0);
                for (PackageInfo packageInfo : list2) {
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        //第三方应用，否则系统应用
                        Log.e("222",packageInfo.applicationInfo.loadIcon(context.getPackageManager()).toString());

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        DrawableToBitmap(packageInfo.applicationInfo.loadIcon(context.getPackageManager())).compress(Bitmap.CompressFormat.JPEG, 100, baos);

                        Map appInfo = new HashMap();
                        appInfo.put("AppName",packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                        appInfo.put("PackageName",packageInfo.packageName);
                        appInfo.put("VersionName",packageInfo.versionName);
                        appInfo.put("VersionCode",packageInfo.versionCode);
                        appInfo.put("LastUpdateTime",packageInfo.lastUpdateTime+"");
                        appInfo.put("FirstInstallTime",packageInfo.firstInstallTime+"");
                        appInfo.put("Iexpress","0");
                        appInfo.put("Appicon",baos.toByteArray());

                        appList.add(appInfo);
                    }
                }
                return appList;
            } catch (Exception e) {
                e.printStackTrace();
                Map appInfo = new HashMap();
                appInfo.put("AppName","Data Exception");
                appList.add(appInfo);
                return appList;
            }
        } else {
            ArrayList<Map> appList = new ArrayList<>(); // 用来存储获取的应用信息数据
            try {
                PackageManager pm = context.getPackageManager();
                @SuppressLint("WrongConstant") List<PackageInfo> list2 = pm.getInstalledPackages(0);
                for (PackageInfo packageInfo : list2) {
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        //第三方应用，否则系统应用
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        DrawableToBitmap(packageInfo.applicationInfo.loadIcon(context.getPackageManager())).compress(Bitmap.CompressFormat.JPEG, 100, baos);

                        Map appInfo = new HashMap();
                        appInfo.put("AppName",packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                        appInfo.put("PackageName",packageInfo.packageName);
                        appInfo.put("VersionName",packageInfo.versionName);
                        appInfo.put("VersionCode",packageInfo.versionCode);
                        appInfo.put("LastUpdateTime",packageInfo.lastUpdateTime+"");
                        appInfo.put("FirstInstallTime",packageInfo.firstInstallTime+"");
                        appInfo.put("Iexpress","0");
                        appInfo.put("Appicon",baos.toByteArray());
                        appList.add(appInfo);
                    }
                }
                return appList;
            } catch (Exception e) {
                e.printStackTrace();
                Map appInfo = new HashMap();
                appInfo.put("AppName","Data Exception");
                appList.add(appInfo);
                return appList;
            }
        }
    }


    // Drawable----> Bitmap
    public static Bitmap DrawableToBitmap(Drawable drawable) {

        // 获取 drawable 长宽
        int width = drawable.getIntrinsicWidth();
        int heigh = drawable.getIntrinsicHeight();

        drawable.setBounds(0, 0, width, heigh);

        // 获取drawable的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 创建bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, heigh, config);
        // 创建bitmap画布
        Canvas canvas = new Canvas(bitmap);
        // 将drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

}