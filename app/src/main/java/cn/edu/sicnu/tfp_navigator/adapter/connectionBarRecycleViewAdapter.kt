package cn.edu.sicnu.tfp_navigator.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.CompoundButton
import cn.edu.sicnu.tfp_navigator.*
import kotlin.concurrent.thread

class connectionBarRecycleViewAdapter (var cursor:Cursor): RecyclerView.Adapter<connectionBarRecycleViewAdapter.ViewHolder>() {

    var conn=Connection("unknown","unkonwn",0,"unknown",0,true,"unknown","unknown")
    var isReadyToJump=false//是否准备跳转


    fun swapCursor(newCursor: Cursor){
        if (cursor == newCursor) return
        cursor.close()
        cursor = newCursor
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nametext: TextView;
        val iptext: TextView;
        val status:Switch

        init {
            nametext = itemView.findViewById(R.id.textView)
            iptext = itemView.findViewById(R.id.textView2)
            status = itemView.findViewById(R.id.switch1)
        }

    }


    //载入layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //载入布局呈现
        val view = LayoutInflater.from(parent.context).inflate(R.layout.connectionbar,parent,false)
        //获取view
        val viewHolder=ViewHolder(view)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cursor.count

    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        cursor.moveToPosition(position)
        val name=cursor.getString(cursor.getColumnIndex("name"))
        val ip=cursor.getString(cursor.getColumnIndex("ip"))
        val port=cursor.getString(cursor.getColumnIndex("port")).toInt()
        val info=cursor.getString(cursor.getColumnIndex("info"))
        val status=cursor.getString(cursor.getColumnIndex("status")).toInt()
        val isAvailable=cursor.getString(cursor.getColumnIndex("isAvailable")).toBoolean()
        val userName=cursor.getString(cursor.getColumnIndex("userName"))
        val password=cursor.getString(cursor.getColumnIndex("password"))
        val con=Connection(name,ip,port,info,status,isAvailable,userName,password)
        holder.nametext.text=name
        holder.iptext.text="IP : "+ip

        if (con.status ==0){
            holder.status.text="Off"
        }else{
            holder.status.text="On"
        }


        holder.itemView.setOnClickListener{

            Log.d("action","点击位置${position}")

            //获取到点击的con对象
            Log.d("conn",con.toString())

            //跳转
            val intent= Intent(it.context,MainActivity_conn_info::class.java)
            intent.putExtra("name",con.name)
            intent.putExtra("info",con.info)
            intent.putExtra("ip",con.ip)
            intent.putExtra("port",con.port)
            intent.putExtra("isAvailable",con.isAvailable)
            intent.putExtra("userName",con.userName)
            intent.putExtra("password",con.password)
            intent.putExtra("status",con.status)
            startActivity(it.context,intent,null)
            Toast.makeText(it.context,"触发了itemview点击",Toast.LENGTH_SHORT).show()

        }
        holder.status.setOnClickListener{
            val action=holder.status.isChecked

            if (action){//打开时
                if (onLinkNum==1){
                    holder.status.isChecked=false
                    Toast.makeText(it.context,"不能同时开启两台FTP连接",Toast.LENGTH_LONG).show()
                }
                else{
                    holder.status.text="On"
                    onLinkNum++
                    onLinkConnection=con//确认当前的连接
                }
            }
            else{//关闭时
                onLinkNum--
                holder.status.text="Off"
            }
        }}

}
//    private var mOnclickListenLiser:((cardIndex:Int)->Unit)? = null
//    fun setOnCardClickListener(l:(cardIndex:Int)->Unit) {
//        mOnclickListenLiser = l
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return game.cards.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val card = game.cardAtIndex(position)
//        holder.cardButton.isEnabled = !card.isMatched
//        if (card.isChosen){
//            holder.cardButton.text = card.toString()
//            holder.cardButton.setBackgroundColor(Color.WHITE)
//        }else{
//            holder.cardButton.text = ""
//            holder.cardButton.setBackgroundResource(R.drawable.background)
//        }
//        holder.cardButton.setOnClickListener {
//            mOnclickListenLiser?.invoke(position)
//        }
//    }

