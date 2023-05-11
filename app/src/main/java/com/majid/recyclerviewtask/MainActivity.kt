package com.majid.recyclerviewtask

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.majid.recyclerviewtask.databinding.ActivityMainBinding
import com.majid.recyclerviewtask.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity(), ClickInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var itemsAdapter: ItemsAdapter
    var list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemsAdapter = ItemsAdapter(list, this)
        System.out.println("UpdateValue $list")

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = itemsAdapter
        binding.fab.setOnClickListener {
            val dialog = Dialog(this)
            var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.save.setOnClickListener {
                if (dialogBinding.etName.text.toString().isEmpty()) {
                    dialogBinding.etName.error = "Enter Name"
                } else {
                    list.add(dialogBinding.etName.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }

    override fun newClick(position: Int) {
        val dialog = Dialog(this)
        var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.etName.setText(list[position])
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogBinding.save.setOnClickListener {
            if (dialogBinding.etName.text.toString().isEmpty()) {
                dialogBinding.etName.error = "Enter Name"
            } else {
                list.set(position,dialogBinding.etName.text.toString())
                itemsAdapter.notifyDataSetChanged()
                System.out.println("UpdateValue $list")
                dialog.dismiss()
            }
        }
        dialogBinding.btnDelete.setOnClickListener {
            list.removeAt(position)
            System.out.println("Delete $list")
            itemsAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }
}