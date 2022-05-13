package com.jave.homework321002

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		title = "趣味音视频播放器 ←这什么土名字"

		val listView = findViewById<ListView>(R.id.listView)
		listView.adapter = VideosAdapter(this, PredefinedVideos).also { it.filter.filter("") }
		listView.setOnItemClickListener { adapterView, view, i, l ->
			startActivity(Intent(this, PlayerActivity::class.java).apply {
				putExtra("id", PredefinedVideos[i].id)
			})
		}

		findViewById<Button>(R.id.btn_open).setOnClickListener {
			if (listView.adapter.isEmpty) return@setOnClickListener
			startActivity(Intent(this, PlayerActivity::class.java).apply {
				putExtra("id", (listView.adapter as VideosAdapter).currentList[0].id);
			})
		}

		findViewById<EditText>(R.id.input_title).addTextChangedListener(object : TextWatcher {
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				(listView.adapter as VideosAdapter).filter.filter(s.toString())
			}

			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit
			override fun afterTextChanged(s: Editable) = Unit
		})
	}
}
