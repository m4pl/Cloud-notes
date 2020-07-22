package com.mapl.cloudnotes

import android.content.Context
import android.preference.PreferenceManager

class Prefs(appContext: Context) {
    private var prefs = PreferenceManager.getDefaultSharedPreferences(appContext)
}