package www.bawei.com.mymvp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wmm on 2017/9/14 0014.
 */

public class SQliteDb extends SQLiteOpenHelper  {
    public SQliteDb(Context context) {
        super(context, "ku.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(_id Integer primary key autoincrement,name varchar(100),pwd varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
