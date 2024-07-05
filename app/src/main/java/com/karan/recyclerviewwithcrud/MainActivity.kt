package com.karan.recyclerviewwithcrud

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karan.recyclerviewwithcrud.databinding.ActivityMainBinding
import com.karan.recyclerviewwithcrud.databinding.CustomDialogboxBinding

class MainActivity : AppCompatActivity(),RecyclerInterface {
    lateinit var binding: ActivityMainBinding
    var item = arrayListOf<Title>()
    lateinit var description: Array<String>
    lateinit var title: Array<String>
    var recyclerAdapter = RecyclerAdapter(item,this)
    lateinit var newrecyclerview:RecyclerView
    lateinit var newarraylist:ArrayList<Title>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        title = arrayOf(
            "Laptop", "Mobile Phones", "Tablets"
        )
        description = arrayOf(
            "A laptop is a personal computer that can be easily moved and used in a variety of locations.",
            "Mobile is a portable device.",
            "A tablet is a wireless, portable personal computer with a touchscreen interface."
        )
        newrecyclerview=findViewById(R.id.recycler_view)
        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = recyclerAdapter
        binding.recyclerView.setHasFixedSize(true)

        newarraylist= arrayListOf<Title>()
        getuserdata()




        binding.btnfab.setOnClickListener {

            val dialogBinding = CustomDialogboxBinding.inflate(layoutInflater)
            val update_dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                show()
            }
            val oldName: String = item.toString()
            dialogBinding.etname.setText(oldName)
            val update = "update"
            dialogBinding.btnadd.text = update
            dialogBinding.btnadd.setOnClickListener {
                if (dialogBinding.etname.text.toString().isEmpty()) {
                    dialogBinding.etname.error = "Enter Name"
                } else {


                    recyclerAdapter.notifyDataSetChanged()
                    update_dialog.dismiss()
                }
            }
        }


    }

    private fun getuserdata() {
        for (i in title.indices) {
            val title = Title(title[i], description[i])
            newarraylist.add(title)
        }
        newrecyclerview.adapter=RecyclerAdapter(newarraylist,this)
    }

    override fun Update_data(position: Int) {
        TODO("Not yet implemented")
    }

    override fun Delete_data(position: Int) {
            AlertDialog.Builder(this).apply {
                setTitle("Are you sure")
                setPositiveButton("yes")
                { _, _ ->
                    item.removeAt(position)
                    recyclerAdapter.notifyDataSetChanged()
                }
                setNegativeButton("No")
                { _, _ ->

                }
                setCancelable(false)
            }
                .show()


        }
    }

