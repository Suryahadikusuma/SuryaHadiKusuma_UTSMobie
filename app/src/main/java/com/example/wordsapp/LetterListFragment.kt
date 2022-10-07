package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

//deklarasi class untuk LetterListFragment
class LetterListFragment : Fragment() {
    //menambahkan variabel binding jenis FragmentLetterListBinding
    private var _binding: FragmentLetterListBinding? = null
    //membuat properti baru binding dan tetapkan sama dengan _binding
    private val binding get() = _binding!!
    //properti untuk tampilan recycler
    private lateinit var recyclerView: RecyclerView
    //deklarasi properti recyclerView
    private var isLinearLayoutManager = true
    //mengimplementasikan onCreate() dengan memanggil setHasOptionsMenu()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // Implementasikan onCreateView()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //untuk meng-inflate tampilan dan menyetel nilai _binding
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        // untuk menampilkan tampilan root.
        val view = binding.root
        return view
    }

    //metetapkan nilai properti recyclerView di onViewCreated() dan memanggil chooseLayout()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    //setel ulang properti _binding ke null karena tampilan sudah tidak ada
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }
    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = LetterAdapter()
            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
                recyclerView.adapter = LetterAdapter()
            }
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}