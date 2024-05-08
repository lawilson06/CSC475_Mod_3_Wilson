package com.example.csc475_mod_3_wilson

import android.app.Application

class ToDoApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }

}