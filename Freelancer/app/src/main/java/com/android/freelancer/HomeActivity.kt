package com.android.freelancer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun hireFreelancer(view: View) {
        val intent = Intent(this, HireFreelancer::class.java)
        startActivity(intent)
    }

    fun myProjects(view: View) {
        val intent = Intent(this, MyProjects::class.java)
        startActivity(intent)
    }
    fun findJobs(view: View) {
        val intent = Intent(this, FindJobs::class.java)
        startActivity(intent)
    }
    fun myJobs(view: View) {
        val intent = Intent(this, MyJobs::class.java)
        startActivity(intent)
    }
    fun myProfile(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }
}