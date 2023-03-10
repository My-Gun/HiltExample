package com.gun.hiltexample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.gun.hiltexample.R
import com.gun.hiltexample.constant.Constants.TAG
import com.gun.hiltexample.data.dto.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener {

    @Inject
    lateinit var adapter: UserRecyclerAdapter

    private val viewModel: UserViewModel by viewModels()

    private val etName: EditText by lazy { findViewById(R.id.et_name) }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_user)

        findViewById<Button>(R.id.btn_save).setOnClickListener(this)
        findViewById<Button>(R.id.btn_query).setOnClickListener(this)

        recyclerView.adapter = adapter

        viewModel.userLiveData.observe(this) {
            Log.d(TAG, "MainActivity - userLiveData.observe() - list : $it")
            adapter.submitList(it.toMutableList())
        }

        viewModel.insertResultLiveData.observe(this) {
            Log.d(TAG, "MainActivity - insertResultLiveData.observe() - result : $it")

            val message = if (it <= 0) "사용자 추가 성공" else "사용자 추가 실패 (result:$it)"

            viewModel.getUserList()

            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_save -> {
                val name = etName.text.toString()

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "이름을 입력 해주세요.", Toast.LENGTH_LONG).show()
                    return
                }

                val user = User(name = name)
                viewModel.insertUser(user)
            }
            R.id.btn_query -> {
                viewModel.getUserList()
            }
        }
    }
}