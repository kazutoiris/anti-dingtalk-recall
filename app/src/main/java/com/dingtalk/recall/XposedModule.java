package com.dingtalk.recall;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedBridge;


public class XposedModule implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws ClassNotFoundException {
        ClassLoader classLoader = lpparam.classLoader;
        XposedBridge.log(classLoader.toString());
        try {
            XposedHelpers.findAndHookMethod("com.alibaba.wukong.im.message.MessageImpl", classLoader, "recallStatus", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(0);
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
        } catch (Exception ex) {
            XposedBridge.log(ex.getMessage());
        }
        try {
            XposedHelpers.findAndHookMethod("com.alibaba.wukong.im.message.MessageImpl", classLoader, "canRecall", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
        } catch (Exception ex) {
            XposedBridge.log(ex.getMessage());
        }
    }
}