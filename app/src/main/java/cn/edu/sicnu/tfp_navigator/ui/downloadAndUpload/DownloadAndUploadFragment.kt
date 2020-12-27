package cn.edu.sicnu.tfp_navigator.ui.downloadAndUpload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.edu.sicnu.tfp_navigator.FileInQueue
import cn.edu.sicnu.tfp_navigator.R

class DownloadAndUploadFragment : Fragment() {
    //延迟初始化，伴随类  lateinit var只能用来修饰类属性
    private lateinit var downloadAndUploadViewModel: DownloadAndUploadViewModel
    private lateinit var filesQueue: MutableList<FileInQueue>
    private lateinit var filesUploadQueue: MutableList<FileInQueue>//上传队列
    private lateinit var filesDownloadQueue: MutableList<FileInQueue>//下载队列

    var page=0//默认界面显示上传队列

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        downloadAndUploadViewModel =
                ViewModelProvider(this).get(DownloadAndUploadViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_downloadandupdate, container, false)
        //加载上layout布局文件

        val uploadText=root.findViewById<TextView>(R.id.updload_textView)
        val downloadText=root.findViewById<TextView>(R.id.downloadtextView)
        var files:MutableList<FileInQueue>

        init()//初始数据

        uploadText.setOnClickListener{
            files=filesUploadQueue
            page=0
        }

        downloadText.setOnClickListener{
            files=filesDownloadQueue
            page=1
        }


        return root
    }

    private fun init() {
        var file1=FileInQueue("wwwe",false, 30.78F,1,"hiuygiuh/hgyfg/k",0)
        var file2=FileInQueue("ufc",false, 300.11F,1,"hiuygiuh/hgyfg/k",0)
        var file3=FileInQueue("nxt",false, 30.78F,1,"hiuygiuh/hgyfg/k",1)
        var file4=FileInQueue("wlf",false, 300.11F,1,"hiuygiuh/hgyfg/k",1)
        filesQueue.add(file1)
        filesQueue.add(file2)
        filesQueue.add(file3)
        filesQueue.add(file4)



    }
}