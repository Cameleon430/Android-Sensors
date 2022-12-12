package com.example.kursova.presenter.accelerometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kursova.R
import com.example.kursova.data.AxisEntity
import com.example.kursova.data.SensorDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class AccelerometerFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var xAxisTextView: TextView
    private lateinit var yAxisTextView: TextView
    private lateinit var level: View
    private lateinit var saveFAB: FloatingActionButton
    private var xAxis = 0f
    private var yAxis = 0f
    private var mAccelerator: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accelerometer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        xAxisTextView = view.findViewById(R.id.xAxisTextView)
        yAxisTextView = view.findViewById(R.id.yAxisTextView)
        saveFAB = view.findViewById(R.id.saveFAB)
        level = view.findViewById(R.id.levelLinearLayout)

        val db = Room.databaseBuilder(
            requireActivity().application,
            SensorDatabase::class.java, "database"
        ).build()
        val axisDao = db.provideAxisDao()

        saveFAB.setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch{
                val axis = AxisEntity(
                    id = 0,
                    name = "Accelerator",
                    xValue = xAxis,
                    yValue = yAxis,
                    sensor = "Accelerator"
                )
                axisDao.insert(axis)
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        xAxis = event.values[0]
        yAxis = event.values[1]

        level.apply{
            rotationX = yAxis * 3f
            rotationY = xAxis * 3f
            rotation = -xAxis
            translationX = xAxis * -10
            translationY = yAxis * 10
        }

        xAxisTextView.text = xAxis.toInt().toString()
        yAxisTextView.text = yAxis.toInt().toString()

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //Nothing to do here
    }

    override fun onResume() {
        super.onResume()
        mAccelerator?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}