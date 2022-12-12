package com.example.kursova.presenter.light

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kursova.R
import com.example.kursova.data.LightEntity
import com.example.kursova.data.SensorDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class LightSensorFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var luxCounterTextView: TextView
    private lateinit var luxIndicatorImageView: ImageView
    private lateinit var saveFAB: FloatingActionButton
    private var mLight: Sensor? = null
    private var lux = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_light_sensor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        luxCounterTextView = view.findViewById(R.id.luxCounterTextView)
        luxIndicatorImageView = view.findViewById(R.id.luxIndicatorImageView)
        saveFAB = view.findViewById(R.id.saveFAB)

        val db = Room.databaseBuilder(
            requireActivity().application,
            SensorDatabase::class.java, "database"
        ).build()
        val lightDao = db.provideLightDao()

        saveFAB.setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch{
                val light = LightEntity(
                    id = 0,
                    name = "Light Sensor",
                    value = lux,
                    sensor = "Light"
                )
                lightDao.insert(light)
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        lux = event.values[0]
        luxCounterTextView.text = lux.toString()
        if(lux>=20){
            luxIndicatorImageView.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.light_color),
                android.graphics.PorterDuff.Mode.MULTIPLY)
        }
        else{
            luxIndicatorImageView.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.neutral_gray),
                android.graphics.PorterDuff.Mode.MULTIPLY)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //Nothing to do here
    }

    override fun onResume() {
        super.onResume()
        mLight?.also { light ->
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}