package cn.edu.sicnu.tfp_navigator

//ftp连接
data class Connection (
    val name:String,//设置连接名
    val ip:String,//ip地址
    val port:Int,//端口 默认为21
    val info:String,//备注
    val status:Int,//连接状态 0/1
    val isAvailable:Boolean,//是否可用 默认为0不可用
    val userName:String,//连接用户名
    val password:String//连接密码
){
    override fun toString(): String {
        return "FTP name:${name},ip:${ip},info:${info}"
    }





}