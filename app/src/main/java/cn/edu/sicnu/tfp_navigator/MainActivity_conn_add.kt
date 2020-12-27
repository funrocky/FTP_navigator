package cn.edu.sicnu.tfp_navigator

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import cn.edu.sicnu.tfp_navigator.db.MyDatabaseHelper
import cn.edu.sicnu.tfp_navigator.db.TABLE_NAME
import android.util.Log

var onLinkNum=0//当前默认连接数为0 同一时间只能连接一台。
lateinit var onLinkConnection:Connection //当前的ftp连接

class MainActivity_conn_add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_conn_add)

        val inputname=findViewById<EditText>(R.id.editText_connname)
        val inputip=findViewById<EditText>(R.id.editText_conn_ip)
        val inputport=findViewById<EditText>(R.id.editText_conn_port)
        val inputusername=findViewById<EditText>(R.id.editText_conn_username)
        val inputpassword=findViewById<EditText>(R.id.editText_conn_password)
        val inputinfo=findViewById<EditText>(R.id.editText_conn_info)
        val commitButton=findViewById<Button>(R.id.btn_conn_add_commin)

        commitButton.setOnClickListener{
            val dbHelper=MyDatabaseHelper(this, TABLE_NAME,1)
            val db=dbHelper.writableDatabase

            if(inputname.text.toString()!="" && inputip.text.toString()!="" && inputport.text.toString()!="" && inputpassword.text.toString()!="" && inputusername.text.toString()!=""){
                val values=ContentValues().apply {
                    put("name",inputname.text.toString())
                    put("ip",inputip.text.toString())
                    put("port",inputport.text.toString().toInt())
                    put("info",inputinfo.text.toString())
                    put("status",0)
                    put("isAvailable",true)
                    put("username",inputusername.text.toString())
                    put("password",inputpassword.text.toString())

                }
                db.insert(TABLE_NAME,null,values)//插入新连接
                db.close()

                Log.d("username",inputusername.text.toString())
                Toast.makeText(this,"insert OK",Toast.LENGTH_SHORT).show()

                var intent= Intent()
                intent.putExtra("msg","yes")
                //添加到connlist 引发变化
                connList.add(Connection(inputname.text.toString(),inputip.text.toString(),inputport.text.toString().toInt(),inputinfo.text.toString(),0,true,inputusername.text.toString(),inputpassword.text.toString()))
                setResult(Activity.RESULT_OK,intent)


                finish()
            }else{
                Toast.makeText(this,"连接名，IP地址，端口，用户名，密码不能为空",Toast.LENGTH_SHORT).show()
            }


        }

        "id integer primary key autoincrement," +
                "name text," +
                "ip text," +
                "port integer," +
                "info text," +
                "status integer," +
                "isAvailable Boolean," +
                "userName text," +
                "password text )"
    }
}