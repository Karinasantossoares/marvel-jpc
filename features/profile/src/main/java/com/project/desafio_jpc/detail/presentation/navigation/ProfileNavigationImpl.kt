package com.project.desafio_jpc.detail.presentation.navigation

import android.content.Context
import android.content.Intent
import com.project.desafio_jpc.detail.presentation.ProfileScreenActivity
import com.project.desafio_jpc.navigation.ProfileNavigation

class ProfileNavigationImpl : ProfileNavigation {
    override fun goToProfileModule(context: Context) {
        val intent = Intent(context, ProfileScreenActivity::class.java)
        context.startActivity(intent)
    }
}