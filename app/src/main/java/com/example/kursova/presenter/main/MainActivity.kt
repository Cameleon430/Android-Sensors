package com.example.kursova.presenter.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.kursova.R
import com.example.kursova.presenter.menu.MainMenuFragment

class MainActivity : AppCompatActivity(){

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainMenuFragment>(R.id.fragment_container_view)
            }
        }
    }

}