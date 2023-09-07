package com.example.stdmanager.util

import android.app.Application
import com.example.stdmanager.model.UserQualificationModel

class StateClass : Application() {

    companion object{
        var globalQualifications : Array<UserQualificationModel> = emptyArray()
    }
}