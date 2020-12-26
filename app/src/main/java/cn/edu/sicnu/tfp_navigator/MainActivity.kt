package cn.edu.sicnu.tfp_navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cn.edu.sicnu.tfp_navigator.db.MyDatabaseHelper
import cn.edu.sicnu.tfp_navigator.db.TABLE_NAME

val connList= mutableListOf<Connection>()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //初始化数据库：建立连接表
        val dbhelper= MyDatabaseHelper(this, TABLE_NAME,1)
        dbhelper.writableDatabase
        Log.d("call","MainActivityOnCreate")



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("call","MainActivityResult")
        when(requestCode){
            1->if (requestCode== Activity.RESULT_OK){
                //第一个板块connction中点进详情后要删除此连接

            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("call","MainActivityOnRestart")
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val dbhelper= MyDatabaseHelper(this, TABLE_NAME,1)
        dbhelper.writableDatabase

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}