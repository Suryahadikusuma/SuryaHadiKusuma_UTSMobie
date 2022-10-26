package com.example.SuryaHadiKusuma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.SuryaHadiKusuma.databinding.FragmentWordListBinding

//deklarasi class untuk WordListFragment
class WordListFragment : Fragment() {
    private lateinit var letterId: String
    //menambahkan variabel binding jenis FragmentWordListBinding
    private var _binding: FragmentWordListBinding? = null
    //membuat properti baru binding dan tetapkan sama dengan _binding
    private val binding get() = _binding!!
    companion object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    // Implementasikan onCreateView()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //untuk meng-inflate tampilan dan menyetel nilai _binding
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        // untuk menampilkan tampilan root.
        return binding.root
    }

    //mengimplementasikan onViewCreated()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //mengonfigurasi recyclerView di onCreateView() di DetailActivity
        //dan mereferensikannya dengan activity.intent
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    //menyetel ulang variabel _binding di onDestroyView()
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }
}