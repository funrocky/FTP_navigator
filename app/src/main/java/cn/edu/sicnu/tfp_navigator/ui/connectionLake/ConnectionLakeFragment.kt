package cn.edu.sicnu.tfp_navigator.ui.connectionLake

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.tfp_navigator.adapter.connectionBarRecycleViewAdapter
import cn.edu.sicnu.tfp_navigator.db.MyDatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cn.edu.sicnu.tfp_navigator.*
import cn.edu.sicnu.tfp_navigator.db.TABLE_NAME
import kotlin.concurrent.thread

class ConnectionLakeFragment : Fragment() {


    private lateinit var connectionLakeViewModel: ConnectionLakeViewModel
    private lateinit var db:SQLiteDatabase
    private lateinit var cursor:Cursor
    private lateinit var inflater:LayoutInflater
    private lateinit var container:ViewGroup

    override fun onCreateView(
            inflater_raw: LayoutInflater,
            container_raw: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        connectionLakeViewModel =
                ViewModelProvider(this).get(ConnectionLakeViewModel::class.java)
        inflater=inflater_raw
        if (container_raw != null) {
            container=container_raw
        }
        val root = inflater.inflate(R.layout.fragment_connectionlake, container, false)

        //获取组件
        val recycleView=root.findViewById<RecyclerView>(R.id.recyclerView_home)
        val floatingButton=root.findViewById<FloatingActionButton>(R.id.floatingActionButton_home)
        //context//获取上下文
        //LinearLayoutManager使用时和reclycerView出现点击时index溢出异常，现继承并重写LinearLayoutManager.onLayoutChildren()方法，写在myutils里面//在onViewBinder里面直接用position
        // 于上无关，2020.12.24错误仍未解除
        // 2020.12.25错误解除，用onViewBinder
        val layoutManager= LinearLayoutManager(context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL

//        initconn()

        //获取cursor
        val dbhelper= this.context?.let { MyDatabaseHelper(it, TABLE_NAME,1) } //context只使用this不得行
        if(dbhelper!=null){
            db=dbhelper.writableDatabase
        }
        cursor=db.query(TABLE_NAME,null,null,null,null,null,null)
        recycleView.layoutManager=layoutManager
        val adapter=connectionBarRecycleViewAdapter(cursor)
        recycleView.adapter=adapter

        //fragment跳转到activity —————— 添加连接 requestcode=1
        floatingButton.setOnClickListener{
            if (activity==null){
                Toast.makeText(context,"跳不过去",Toast.LENGTH_SHORT).show()
            }else{
                val intent=Intent(this.activity, MainActivity_conn_add::class.java)
                startActivityForResult(intent,1)
            }
        }

        //如果connlist数据发生变化
        connectionLakeViewModel.connlistModel.observe(viewLifecycleOwner, Observer {
            Log.d("call","connlist changed observe")
            val root = inflater.inflate(R.layout.fragment_connectionlake, container, false)

            //获取组件
            val recycleView=root.findViewById<RecyclerView>(R.id.recyclerView_home)
            val floatingButton=root.findViewById<FloatingActionButton>(R.id.floatingActionButton_home)
            //context//获取上下文
            //LinearLayoutManager使用时和reclycerView出现点击时index溢出异常，现继承并重写LinearLayoutManager.onLayoutChildren()方法，写在myutils里面//在onViewBinder里面直接用position
            // 于上无关，2020.12.24错误仍未解除
            // 2020.12.25错误解除，用onViewBinder
            val layoutManager= LinearLayoutManager(context)
            layoutManager.orientation=LinearLayoutManager.VERTICAL

            //获取cursor
            val dbhelper= this.context?.let { MyDatabaseHelper(it, TABLE_NAME,1) } //context只使用this不得行
            if(dbhelper!=null){
                db=dbhelper.writableDatabase
            }
            cursor=db.query(TABLE_NAME,null,null,null,null,null,null)


            recycleView.layoutManager=layoutManager
            val adapter=connectionBarRecycleViewAdapter(cursor)
            recycleView.adapter=adapter
        })

        Log.d("call","conntionLake frgment oncreateView")

        return root
    }





//        val textView: TextView = root.findViewById(R.id.text_home)
//        val textView2: TextView = root.findViewById(R.id.text_home2)
//        val button: Button = root.findViewById(R.id.button_home)
//

//
//        connectionLakeViewModel.text2.observe(viewLifecycleOwner, Observer {
//            textView2.text = it.toString()
//        })
//
//        //可在frgment里写处理逻辑
//        button.setOnClickListener{
//            textView2.text = "按钮已经点击"
//        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null){
            Log.d("call","onActivityResult form conn_add activity")
        }
        Log.d("call","onActivityResult")

    }

    override fun onResume() {
        super.onResume()
        Log.d("call","conntionLake frgment onResume")

        val root = inflater.inflate(R.layout.fragment_connectionlake, container, false)

        //获取组件
        val recycleView=root.findViewById<RecyclerView>(R.id.recyclerView_home)
        //context//获取上下文
        //LinearLayoutManager使用时和reclycerView出现点击时index溢出异常，现继承并重写LinearLayoutManager.onLayoutChildren()方法，写在myutils里面//在onViewBinder里面直接用position
        // 于上无关，2020.12.24错误仍未解除
        // 2020.12.25错误解除，用onViewBinder
        val layoutManager= LinearLayoutManager(context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL

        //获取最新cursor
        val dbhelper= this.context?.let { MyDatabaseHelper(it, TABLE_NAME,1) } //context只使用this不得行
        if(dbhelper!=null){
            db=dbhelper.writableDatabase
        }
        cursor=db.query(TABLE_NAME,null,null,null,null,null,null)


        recycleView.layoutManager=layoutManager
        val adapter=connectionBarRecycleViewAdapter(cursor)
        recycleView.adapter=adapter





        initconn()




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("call","conntionLake frgment onViewCreated")


    }


    private fun initconn() {
        var localconnlist=mutableListOf<Connection>()
        if(connList.size>0){
            //先清空再查询SQL添加，不会重复
            Log.d("action","清空connlist")
            connList.clear()
        }
        val dbhelper= this.context?.let { MyDatabaseHelper(it, TABLE_NAME,1) } //context只使用this不得行
        if(dbhelper!=null){
            db=dbhelper.writableDatabase
        }
        cursor=db.query(TABLE_NAME,null,null,null,null,null,null)
        var time=0
        if(cursor.moveToFirst()){
            do{
                val name=cursor.getString(cursor.getColumnIndex("name"))
                val ip=cursor.getString(cursor.getColumnIndex("ip"))
                val port=cursor.getString(cursor.getColumnIndex("port")).toInt()
                val info=cursor.getString(cursor.getColumnIndex("info"))
                val status=cursor.getString(cursor.getColumnIndex("status")).toInt()
                val isAvailable=cursor.getString(cursor.getColumnIndex("isAvailable")).toBoolean()
                val userName=cursor.getString(cursor.getColumnIndex("userName"))
                val password=cursor.getString(cursor.getColumnIndex("password"))
                val con=Connection(name,ip,port,info,status,isAvailable,userName,password)
                localconnlist.add(con)
                time++
            }while (cursor.moveToNext())
            Log.d("action","添加connlist${time}次")
            Log.d("action","connlist长度为${localconnlist.size}")

            connList.addAll(localconnlist)
            cursor.close()
            db.close()
        }
        else{
            Log.d("1234","56789")
        }


//        "id integer primary key autoincrement," +
//                "name text," +
//                "ip text," +
//                "port integer," +
//                "info text," +
//                "status integer," +
//                "isAvailable Boolean," +
//                "userName text," +
//                "password text )"

    }
}