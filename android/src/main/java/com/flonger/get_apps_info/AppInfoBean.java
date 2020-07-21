package com.flonger.get_apps_info;

public class AppInfoBean {
    private String userId;
    public String appName; // 应用名
    public String packageName; // 包名
    public String versionName; // 版本名
    public int versionCode = 0; // 版本号
    public String iexpress;//app类型 0:第三方应用,1.系统内置内应用
    public String lastUpdateTime;
    public String firstInstallTime;
    public byte[] appicon;

    private String systemType;

    public String getSystemType() {
        return systemType == null ? "" : systemType;
    }

    public void setIexpress(String iexpress) {
        this.iexpress = iexpress;
    }

    public String getIexpress() {
        return iexpress == null ? "" : iexpress;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getUserId() {
        return userId == null ? "" : userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFirstInstallTime(String firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFirstInstallTime() {
        return firstInstallTime == null ? "" : firstInstallTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime == null ? "" : lastUpdateTime;
    }



    public String getAppName() {
        return appName == null ? "" : appName;
    }

    public String getPackageName() {
        return packageName == null ? "" : packageName;
    }

    public String getVersionName() {
        return versionName == null ? "" : versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public byte[] getAppicon() {
        return appicon;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
    public void setAppicon(byte[] appicon) {
        this.appicon = appicon;
    }
}
