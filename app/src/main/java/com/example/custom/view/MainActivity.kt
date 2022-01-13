package com.example.custom.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    private var recyclerView: RecyclerView? = null
    private var dataList = mutableListOf<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = this.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        buildDataList()
        recyclerView?.adapter = DemoAdapter()
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun buildDataList() {
        dataList.add(Item("==> View基础属性"))
    }


    inner class DemoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_list_layout, parent, false)
            return object : RecyclerView.ViewHolder(itemView) {

            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = dataList.get(position)
            var textView = holder.itemView as TextView
            textView.setText(item.title)
            holder.itemView.setOnClickListener { view ->
                if (!item.linkType.isNullOrBlank()) openActivity(item.linkType)
            }
        }


        override fun getItemCount(): Int {
            return dataList.size
        }

    }

    private fun openActivity(linkType: String) {
        try {
            Log.i(TAG, "linkType: ${linkType}")
            var intent = Intent(this, Class.forName(linkType))
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "openActivity error", e)
        }
    }

    data class Item(var title: String = "", var linkType: String = "")

}