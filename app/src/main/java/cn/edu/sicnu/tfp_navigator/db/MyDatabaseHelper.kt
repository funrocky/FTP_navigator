package cn.edu.sicnu.tfp_navigator.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import cn.edu.sicnu.tfp_navigator.MainActivity
const val DB_NAME = "mydb.db"
const val TABLE_NAME = "connection"

class MyDatabaseHelper(val context: Context, name: String, version:Int): SQLiteOpenHelper(context, name, null, version) {

    private val createConnection="create table Connection(" +
            "id integer primary key autoincrement," +
            "name text," +
            "ip text," +
            "port integer," +
            "info text," +
            "status integer," +
            "isAvailable Boolean," +
            "userName text," +
            "password text )"
    //建表 :连接表

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createConnection)
        Toast.makeText(context,"success",Toast.LENGTH_SHORT).show()

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}