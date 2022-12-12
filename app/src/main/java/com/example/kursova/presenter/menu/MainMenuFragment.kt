package com.example.kursova.presenter.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.kursova.R
import com.example.kursova.presenter.accelerometer.AccelerometerDataFragment
import com.example.kursova.presenter.accelerometer.AccelerometerFragment
import com.example.kursova.presenter.light.DataFragment
import com.example.kursova.presenter.light.LightSensorFragment

class MainMenuFragment : Fragment() {

    private lateinit var acceleratorButton: Button
    private lateinit var acceleratorDataButton: Button
    private lateinit var lightButton: Button
    private lateinit var lightDataButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        acceleratorButton = view.findViewById(R.id.acceleratorButton)
        acceleratorDataButton = view.findViewById(R.id.axisDataButton)
        lightButton = view.findViewById(R.id.lightButton)
        lightDataButton = view.findViewById(R.id.lightDataButton)

        acceleratorButton.setOnClickListener{
            parentFragmentManager.commit{
                addToBackStack(null)
                replace(R.id.fragment_container_view, AccelerometerFragment())
            }
        }
        acceleratorDataButton.setOnClickListener{
            parentFragmentManager.commit{
                addToBackStack(null)
                replace(R.id.fragment_container_view, AccelerometerDataFragment())
            }
        }
        lightButton.setOnClickListener{
            parentFragmentManager.commit{
                addToBackStack(null)
                replace(R.id.fragment_container_view, LightSensorFragment())
            }
        }

        lightDataButton.setOnClickListener{
            parentFragmentManager.commit{
                addToBackStack(null)
                replace(R.id.fragment_container_view, DataFragment())
            }
        }
    }

}