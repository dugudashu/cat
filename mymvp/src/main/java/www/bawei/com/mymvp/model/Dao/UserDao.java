package www.bawei.com.mymvp.model.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import www.bawei.com.mymvp.model.bean.UserBean;
import www.bawei.com.mymvp.model.db.SQliteDb;

/**
 * Created by wmm on 2017/9/14 0014.
 */

public class UserDao {

    private static final String TABLE_NAME = "user";
    private final SQLiteDatabase db;

    public UserDao(Context context) {

        SQliteDb sQliteDb = new SQliteDb(context);
        db = sQliteDb.getWritableDatabase();
    }

    public void add(String name, String pwd) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("pwd", pwd);
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<UserBean> select(String id) {

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id=?", new String[]{id});

        ArrayList<UserBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndex("name"));
            String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
            int hehe = cursor.getInt(cursor.getColumnIndex("_id"));
            UserBean userbean = new UserBean();
            userbean.setName(name);
            userbean.setPwd(pwd);
            userbean.setId(hehe);
            list.add(userbean);
        }
        return list;


    }


}
