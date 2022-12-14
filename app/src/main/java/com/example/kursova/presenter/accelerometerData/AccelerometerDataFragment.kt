package com.example.kursova.presenter.accelerometerData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kursova.data.*
import com.example.kursova.databinding.FragmentAccelerometerDataBinding
import kotlinx.coroutines.launch

class AccelerometerDataFragment : Fragment(), AxisViewItemAdapter.OnItemSelectListener {

    private val adapter = AxisViewItemAdapter(this)
    private lateinit var binding: FragmentAccelerometerDataBinding
    private lateinit var axisDao: AxisDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccelerometerDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            axisRecyclerView.adapter = adapter
        }

        val db = Room.databaseBuilder(
            requireActivity().application,
            SensorDatabase::class.java, "database"
        ).build()
        axisDao = db.provideAxisDao()

        viewLifecycleOwner.lifecycleScope.launch{
            val axis: List<AxisEntity> = axisDao.getAll()
            adapter.updateItemsView(axis)
        }
    }

    override fun onClick(axis: AxisEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            axisDao.delete(axis.id)
            adapter.updateItemsView(axisDao.getAll())
        }
    }

}