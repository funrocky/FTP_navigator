package cn.edu.sicnu.tfp_navigator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.util.Log
import cn.edu.sicnu.tfp_navigator.db.MyDatabaseHelper
import cn.edu.sicnu.tfp_navigator.db.TABLE_NAME

//在第一板块里点击之后显示的连接的详情
class MainActivity_conn_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_conn_info)

        var isDelete=0

        val name=findViewById<TextView>(R.id.conninfo_name)
        val ip=findViewById<TextView>(R.id.conninfo_ip)
        val isvail=findViewById<TextView>(R.id.conninfo_isvail)
        val port=findViewById<TextView>(R.id.conninfo_port)
        val user=findViewById<TextView>(R.id.conninfo_user)
        val password=findViewById<TextView>(R.id.conninfo_password)
        val info=findViewById<TextView>(R.id.conninfo_info)
        val status=findViewById<TextView>(R.id.conninfo_status)
        val button=findViewById<FloatingActionButton>(R.id.floatingActionButton2)

        //接收intent信息：
        if(intent==null){
            Log.d("有无收到intent","无")
        }else{
            name.text=intent.getStringExtra("name")
            ip.text=intent.getStringExtra("ip")
            isvail.text=intent.getBooleanExtra("isAvailable",true).toString()
            port.text=intent.getIntExtra("port",21).toString()
            user.text=intent.getStringExtra("userName")
            password.text=intent.getStringExtra("password")
            info.text=intent.getStringExtra("info")
            status.text=intent.getIntExtra("status",0).toString()
        }


        button.setOnClickListener{
            isDelete=1
            val intent=Intent()
            intent.putExtra("isDelete",isDelete)//表示要删除此连接

            val dbHelper=MyDatabaseHelper(this, TABLE_NAME,1)
            val db=dbHelper.writableDatabase
            db.delete(TABLE_NAME,"ip = ?", arrayOf(ip.text.toString()))

            setResult(Activity.RESULT_OK,intent)
            finish()
        }


    }





}