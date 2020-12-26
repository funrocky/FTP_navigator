package cn.edu.sicnu.tfp_navigator.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.tfp_navigator.Connection
import cn.edu.sicnu.tfp_navigator.MainActivity_conn_info
import cn.edu.sicnu.tfp_navigator.R
import android.util.Log
import android.widget.CompoundButton
import kotlin.concurrent.thread

class connectionBarRecycleViewAdapter (val connList: List<Connection>): RecyclerView.Adapter<connectionBarRecycleViewAdapter.ViewHolder>() {

    var conn=Connection("unknown","unkonwn",0,"unknown",0,true,"unknown","unknown")
    var isReadyToJump=false//是否准备跳转

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
        return connList.size

    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        notifyDataSetChanged()

        val connect=connList[position]
        holder.iptext.text="IP: "+connect.ip
        holder.nametext.text=connect.name
        val statustext:String
        if (connect.status ==0){
            holder.status.text="Off"
        }else{
            holder.status.text="On"
        }

        holder.itemView.setOnClickListener{

            Log.d("action","点击位置${position}")

            conn=connList[position]//获取到点击的conn对象
            Log.d("conn",conn.toString())

            //跳转
            val intent= Intent(it.context,MainActivity_conn_info::class.java)
            intent.putExtra("name",conn.name)
            intent.putExtra("info",conn.info)
            intent.putExtra("ip",conn.ip)
            intent.putExtra("port",conn.port)
            intent.putExtra("isAvailable",conn.isAvailable)
            intent.putExtra("userName",conn.userName)
            intent.putExtra("password",conn.password)
            intent.putExtra("status",conn.status)
            startActivity(it.context,intent,null)
            Toast.makeText(it.context,"触发了itemview点击",Toast.LENGTH_SHORT).show()

        }
        holder.status.setOnClickListener{
            if (holder.status.text=="On"){
                holder.status.text="Off";
            }else {
                holder.status.text="On";
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

