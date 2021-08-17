package com.idsbg.foxconn.myapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DictProvider extends ContentProvider {
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;
    private MyDatabaseHelper dbOpenHelper;

    static {
        //为UriMatcher注册两个Uri
        matcher.addURI(Words.AUTHORITY, "words", WORDS);
        matcher.addURI(Words.AUTHORITY, "word/#", WORD);
    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new MyDatabaseHelper(this.getContext(), "myDict.db3", 1);
        return true;
    }

    //执行查询方法，该方法返回查询得到的cursor的值
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            //如果URI参数代表操作全部数据项
            case WORDS:
                //执行查询
                return db.query("dict", projection, selection, selectionArgs, null, null, sortOrder);
            //如果uri参数代表操作指定数据项
            case WORD:
                //解析出想查询的记录ID
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                //如果原来的where子句存在，拼接where子句
                if (selection != null && !"".equals(selection)) {
                    whereClause = whereClause + "and" + selection;
                }
                return db.query("dict", projection, whereClause, selectionArgs, null, null, sortOrder);
            default:
                try {
                    throw new IllegalAccessException("未知uri：" + uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Nullable
    //该方法的返回值代表了该contentProvider所提供数据的MIME类型
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            //如果操作的数据为多项记录
            case WORDS:
                return "vnd:android.cursor.dir/org.crazyit.dict";
            //如果操作的数据为单项记录
            case WORD:
                return "vnd:android.cursor.item/org.crazyit.dict";
            default:
                try {
                    throw new IllegalAccessException("未知URI:" + uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    //实现插入的方法，该方法返回新插入的记录的uri
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //获得数据库实例
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri)) {
            case WORDS:
                long rowId = db.insert("dict", Words.Word._ID, values);
                //如果成功插入返回uri
                if (rowId > 0) {
                    //在已有的uri后面追加id
                    Uri wordUri = ContentUris.withAppendedId(uri, rowId);
                    //通知数据已经改变
                    getContext().getContentResolver().notifyChange(wordUri, null);
                    return wordUri;
                }
                break;
            default:
                try {
                    throw new IllegalAccessException("未知URI:" + uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    //实现删除方法，该方法返回被删除的记录条数
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        //记录删除的数据行数
        int num = 0;
        switch (matcher.match(uri)) {
            case WORDS:
                num = db.delete("dict", selection, selectionArgs);
                break;
            case WORD:
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                //如果原来的where子句存在，拼接where子句
                if (selection != null && !"".equals(selection)) {
                    whereClause = whereClause + "and" + selection;
                }
                num = db.delete("dict", whereClause, selectionArgs);
                break;
            default:
                try {
                    throw new IllegalAccessException("未知URI:" + uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    //实现更新方法，该方法应该返回被更新的记录条数
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        //记录修改的数据数
        int num = 0;
        switch (matcher.match(uri)) {
            case WORDS:
                num = db.update("dict", values, selection, selectionArgs);
                break;
            case WORD:
                long id = ContentUris.parseId(uri);
                String whereClause = Words.Word._ID + "=" + id;
                //如果原来的where子句存在，拼接where子句
                if (selection != null && !"".equals(selection)) {
                    whereClause = whereClause + "and" + selection;
                }
                num = db.update("dict", values, whereClause, selectionArgs);
                break;
            default:
                try {
                    throw new IllegalAccessException("未知URI:" + uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        //通知数据已经改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }
}
