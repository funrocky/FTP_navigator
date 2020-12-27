package cn.edu.sicnu.tfp_navigator

data class FileInQueue (
        val name: String,
        val isDone: Boolean,
        val size: Float,//KB为单位
        val number:Int,//编号
        val path: String,//位置
        val type: Int//0:上传  1:下载
        //对应数据库
) {
    override fun toString(): String {
        return "name:"+name+"number:"+number.toString()+"path:"+path+"type:"+type.toString()
    }
}