package com.android.freelancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class HireFreelancer : AppCompatActivity() {
    val categories = arrayOf("Website", "Logo Designing", "Content Writing", "Marketing")

    lateinit var spinnerCategory: Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hire_freelancer)

        spinnerCategory = findViewById(R.id.spinner_category)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter
    }
}