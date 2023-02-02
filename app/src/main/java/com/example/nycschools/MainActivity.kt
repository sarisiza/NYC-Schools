package com.example.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nycschools.di.SchoolsApp

const val TAG = "NYC Schools"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SchoolsApp.schoolsComponent.inject(this)
    }
}