package com.example.dz_4month.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dz_4month.App
import com.example.dz_4month.R
import com.example.dz_4month.Task
import com.example.dz_4month.TaskFragment
import com.example.dz_4month.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(){pos->
            val alertDialog= AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Удалить?")
            alertDialog.setPositiveButton("Yes"){dialog,_ ->
               setData()
                dialog.dismiss()


            }
            alertDialog.setNegativeButton("No"){dialog,_ ->
                dialog.dismiss()

            }
            alertDialog.create().show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)

        }

        binding.recyclerView.adapter = adapter
       setData()
        rename()

    }

    private fun rename() {
        adapter.onClick={
            val bundle=Bundle()

            bundle.putString("key_tit",it.title)
            bundle.putString("key_des",it.description)
            findNavController().navigate(R.id.taskFragment,bundle)

        }

    }

    private fun setData(){
        val List= App.db.dao().getAllTask()
        adapter.addTasks(List.reversed())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}