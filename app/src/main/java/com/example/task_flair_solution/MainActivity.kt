package com.example.task_flair_solution

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.task_flair_solution.helpers.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.circular_reveal.*

class MainActivity : AppCompatActivity() {

    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        circularReveal.visibility = View.INVISIBLE
        bottomNavigationView.itemIconTintList = Constants.MAIN_COLOR_STATE

        bottomNavigationView.setOnNavigationItemSelectedListener{
            if (it.itemId == R.id.add) {
                handleAddClick()
            }
            return@setOnNavigationItemSelectedListener true;
        }
    }

    private fun handleAddClick() {
        bottomNavigationView.itemIconTintList = Constants.CENTER_ITEM_CLICK_COLOR_STATE
        if (flag) {
            hideCircularReveal()
        } else {
            showCircularReveal()
        }
    }

    private fun showCircularReveal() {
        addIcon.animate().rotation(405f).setDuration(500).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                addIcon.setVisibility(View.VISIBLE)
            }
        })
        flag = true
        animate(circularReveal, Constants.Type.SHOW)
        circularReveal.setVisibility(View.VISIBLE)
        background.setVisibility(View.VISIBLE)
    }

    private fun hideCircularReveal() {
        flag = false
        animate(circularReveal, Constants.Type.HIDE)
        addIcon.animate().rotation(0f).setDuration(500).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                addIcon.visibility = View.INVISIBLE

                bottomNavigationView.itemIconTintList = Constants.MAIN_COLOR_STATE
            }
        })
    }

    private fun animate(view: View, type: Constants.Type) {
        val cx = view.width / 2
        val cy = view.height / 2
        if (type === Constants.Type.HIDE) {
            val animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, Math.hypot(cx.toDouble(), cy.toDouble()).toFloat(), 0f)
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.INVISIBLE
                    background.setVisibility(View.INVISIBLE)
                }
            })
            animator.duration = 800
            animator.start()
        } else {
            val animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, Math.hypot(cx.toDouble(), cy.toDouble()).toFloat())
            animator.duration = 800
            animator.start()
        }
    }


}
