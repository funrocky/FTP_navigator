package cn.edu.sicnu.tfp_navigator.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import cn.edu.sicnu.tfp_navigator.MainActivity
const val DB_NAME = "mydb.db"
const val TABLE_NAME = "connection"
const val TABLE_FILE_NAME ="file"//文件表

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

    private val createFile="create table File(" +
            "id integer primary key autoincrement," +
            "ip text"+
            "port integer"+
            "name text"+
            "path text"+
            "size Float"+
            "type Int)"
    //建表 :文件表 只保留在上传和下载队列中的文件 在每次进入app时清空

    private val createLog="create table Log(" +
            "id integer primary key autoincrement," +
            "ip text"+
            "port integer"+
            "name text"+
            "path text"+
            "size Float"+
            "type Int"+
            "time text)"
    ////建表 :文件记录表 只保留在上传和下载成功的文件


//    val name: String,
//    val isDone: Boolean,
//    val size: Float,//KB为单位
//    val number:Int,//编号
//    val path: String,//位置
//    val type: Int//0:上传  1:下载

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createConnection)
        Toast.makeText(context,"success",Toast.LENGTH_SHORT).show()

        db.execSQL(createFile)
        db.execSQL(createLog)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}