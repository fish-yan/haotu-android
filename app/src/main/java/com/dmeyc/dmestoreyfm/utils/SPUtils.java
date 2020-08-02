package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.base.BaseApp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jockie on 2016/3/25.
 */
public class SPUtils{

    private static final String SP_NAME = "SP_UTILS";
    private static SharedPreferences sp;

    public static Context getContext() {
        return BaseApp.getContext();
    }

    /**
     * sava sp value
     * @param key
     * @param value
     */
    public static void setValue(String key,Object value){
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        if(value instanceof Integer){
            sp.edit().putInt(key, (int)value).commit();
        }else if(value instanceof String){
            sp.edit().putString(key, (String)value).commit();
        }else if(value instanceof Boolean){
            sp.edit().putBoolean(key,(boolean)value).commit();
        }
    }

    public static <T extends Object> T getValue(String key, T defaultValue){
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        if(defaultValue instanceof Boolean){

        }
        return null;
    }

    /**
     * 保存int值到sp
     * @param key
     * @param position
     */
    public static void savaIntData(String key, int position) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, position).commit();
    }

    public static int getIntData(String key){
        return getIntData(key,0);
    }

    public static int getIntData(String key, int def) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getInt(key, def);
    }

    /**
     * 保存String类型数据
     *
     * @param key
     * @param value
     */
    public static void savaStringData(String key, String value) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getStringData(String key, String def) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getString(key, def);
    }

    public static String getStringData(String key) {
        return getStringData(key,"");
    }

    public static void savaBooleanData(String key, boolean value) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static Boolean getBooleanData(String key, boolean def) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, def);
    }

    public static Boolean getBooleanData(String key){
        return getBooleanData(key,false);
    }

    public static void savaLongData(String key, long value) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        sp.edit().putLong(key, value).commit();
    }

    public static Long getLongData(String key, long def) {
        if (sp == null) {
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getLong(key, def);
    }


    public static void deleteSP(){
        if(sp == null)
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    public static void savaAppendString(String key,String value){
        if(sp == null)
            sp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        List<String> array = getAppendString(key);
        if(array.contains(value))
            array.remove(value);
        array.add(0,value);
        String printValue = "";
        for (int i = 0; i < array.size(); i++) {
            printValue = printValue + array.get(i) + ((i == array.size() - 1) ? "" :"~,~");
        }
        savaStringData(key,printValue);
    }

    public static List<String> getAppendString(String key){
        List<String> array = new ArrayList<>();
        String string = getStringData(key, "");
        if(TextUtils.isEmpty(string)) return array;
        String[] split = string.split("~,~");
        for (int i = 0; i < split.length; i++) {
            array.add(split[i]);
        }
        return array;
    }

    public static boolean isContains(String key,String compareValue){
        List<String> appendString = getAppendString(key);
        return appendString.contains(compareValue);
    }

    public static void removeAppendString(String key, String value) {
        List<String> array = getAppendString(key);
        array.remove(value);
        String printValue = "";
        for (int i = 0; i < array.size(); i++) {
            printValue = printValue + array.get(i) + ((i == array.size() - 1) ? "" :"~,~");
        }
        savaStringData(key,printValue);
    }



    public static void put(Context context, String key, Object object)
    {

        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String)
        {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer)
        {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float)
        {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long)
        {
            editor.putLong(key, (Long) object);
        } else
        {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }


    public static Object get(Context context, String key, Object defaultObject)
    {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String)
        {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }



    public static void remove(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    public static void clear(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }



    public static boolean contains(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public static Map<String, ?> getAll(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }



    private static class SharedPreferencesCompat
    {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod()
        {
            try
            {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e)
            {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor)
        {
            try
            {
                if (sApplyMethod != null)
                {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e)
            {
            } catch (IllegalAccessException e)
            {
            } catch (InvocationTargetException e)
            {
            }
            editor.commit();
        }
    }


}
