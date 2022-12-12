package com.example.kursova.presenter.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kursova.data.LightDao
import com.example.kursova.data.LightEntity
import com.example.kursova.data.SensorDatabase
import com.example.kursova.databinding.FragmentDataBinding
import kotlinx.coroutines.launch


class DataFragment : Fragment(), ViewItemAdapter.OnItemSelectListener {

    private val adapter = ViewItemAdapter(this)
    private lateinit var binding: FragmentDataBinding
    private lateinit var lightDao: LightDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            groupRecyclerView.adapter = adapter
        }

        val db = Room.databaseBuilder(
            requireActivity().application,
            SensorDatabase::class.java, "database"
        ).build()
        lightDao = db.provideLightDao()

        viewLifecycleOwner.lifecycleScope.launch{
            val light: List<LightEntity> = lightDao.getAll()
            adapter.updateItemsView(light)
        }
    }

    override fun onClick(light: LightEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            lightDao.delete(light.id)
        }
    }
}