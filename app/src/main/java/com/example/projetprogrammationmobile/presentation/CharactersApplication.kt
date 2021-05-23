package com.example.projetprogrammationmobile.presentation

import android.app.Application
import android.content.Context

class CharactersApplication : Application() {
    companion object{
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}