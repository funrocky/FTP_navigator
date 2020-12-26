package cn.edu.sicnu.tfp_navigator.ui.downloadAndUpload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.edu.sicnu.tfp_navigator.R

class DownloadAndUploadFragment : Fragment() {
    //延迟初始化，伴随类  lateinit var只能用来修饰类属性
    private lateinit var downloadAndUploadViewModel: DownloadAndUploadViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        downloadAndUploadViewModel =
                ViewModelProvider(this).get(DownloadAndUploadViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_downloadandupdate, container, false)
        //加载上layout布局文件


        val textView: TextView = root.findViewById(R.id.text_dashboard)
        downloadAndUploadViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            //设置textview的显示 具体数据从vm获取
        })

        return root
    }
}