package com.example.codeexp.util;

import android.app.Activity;
import android.os.Process;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AppManagerUtils {
    public static final long MILLISECONDS = 1000L;
    private static Stack<Activity> mActivityStack;
    private static Stack<Fragment> mFragmentStack;
    private static List<ExitCallback> exitCallbacks;

    private AppManagerUtils() {
        exitCallbacks = new ArrayList();
    }

    public static AppManagerUtils getAppManager() {
        return AppManagerUtils.Instance.INSTANCE;
    }

    public static Stack<Activity> getActivityStack() {
        return mActivityStack;
    }

    public static Stack<Fragment> getFragmentStack() {
        return mFragmentStack;
    }

    public static Activity getActivity(Class<?> cls) {
        if (mActivityStack != null) {
            Iterator var1 = mActivityStack.iterator();

            while(var1.hasNext()) {
                Activity activity = (Activity)var1.next();
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }

        return null;
    }

    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack();
        }

        mActivityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null && mActivityStack != null) {
            mActivityStack.remove(activity);
        }

    }

    public void addExitCallback(AppManagerUtils.ExitCallback exitCallback) {
        if (exitCallbacks != null) {
            exitCallbacks.add(exitCallback);
        }

    }

    public void removeExitCallback(AppManagerUtils.ExitCallback exitCallback) {
        if (exitCallbacks != null) {
            exitCallbacks.remove(exitCallback);
        }

    }

    public void cleanAllExitCallback() {
        if (exitCallbacks != null) {
            exitCallbacks.clear();
        }

    }

    public boolean hasActivity() {
        return mActivityStack != null ? mActivityStack.isEmpty() : false;
    }

    public Activity currentActivity() {
        if (mActivityStack != null) {
            Activity activity = (Activity)mActivityStack.lastElement();
            return activity;
        } else {
            return null;
        }
    }

    public void finishActivity() {
        if (mActivityStack != null) {
            Activity activity = (Activity)mActivityStack.lastElement();
            this.finishActivity(activity);
        }

    }

    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }

    }

    public void finishActivity(Class<?> cls) {
        if (mActivityStack != null) {
            Iterator var2 = mActivityStack.iterator();

            while(var2.hasNext()) {
                Activity activity = (Activity)var2.next();
                if (activity.getClass().equals(cls)) {
                    this.finishActivity(activity);
                    break;
                }
            }
        }

    }

    public void finishAllActivity() {
        if (exitCallbacks != null && mActivityStack != null) {
            int i = 0;

            for(int size = mActivityStack.size(); i < size; ++i) {
                if (null != mActivityStack.get(i)) {
                    this.finishActivity((Activity)mActivityStack.get(i));
                }
            }

            mActivityStack.clear();
        }

    }

    public void addFragment(Fragment fragment) {
        if (mFragmentStack == null) {
            mFragmentStack = new Stack();
        }

        mFragmentStack.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            mFragmentStack.remove(fragment);
        }

    }

    public boolean isFragment() {
        return mFragmentStack != null ? mFragmentStack.isEmpty() : false;
    }

    public Fragment currentFragment() {
        if (mFragmentStack != null) {
            Fragment fragment = (Fragment)mFragmentStack.lastElement();
            return fragment;
        } else {
            return null;
        }
    }

    public void appExit() {
        try {
            this.finishAllActivity();
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Exception var2) {
            if (mActivityStack != null) {
                mActivityStack.clear();
            }

            var2.printStackTrace();
        }

    }

    public void callbackExitCallbacks() {
        if (exitCallbacks != null && exitCallbacks.size() > 0) {
            for(int i = 0; i < exitCallbacks.size(); ++i) {
                AppManagerUtils.ExitCallback exitCallback = (AppManagerUtils.ExitCallback)exitCallbacks.get(i);
                if (exitCallback != null) {
                    exitCallback.callback();
                }
            }
        }

    }

    public Activity popActivity() {
        return mActivityStack != null && !mActivityStack.empty() ? (Activity)mActivityStack.pop() : null;
    }

    private static class Instance {
        public static AppManagerUtils INSTANCE = new AppManagerUtils();

        private Instance() {
        }
    }

    public interface ExitCallback {
        void callback();
    }
}
