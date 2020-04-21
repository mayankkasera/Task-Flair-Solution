package com.example.task_flair_solution.helpers

import android.view.View

object Utils {
    fun setViewVisibility(view: View, delay: Int) {
        view.postDelayed({ view.visibility = View.VISIBLE }, delay.toLong())
    }
}