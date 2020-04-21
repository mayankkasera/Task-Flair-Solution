package com.example.task_flair_solution

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.task_flair_solution.helpers.Constants
import com.example.task_flair_solution.helpers.Utils
import com.podcopic.animationlib.library.AnimationType
import com.podcopic.animationlib.library.StartSmartAnimation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.circular_reveal.*

class MainActivity : AppCompatActivity() {

    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        circularReveal.visibility = View.INVISIBLE
        bottomNavigationView.itemIconTintList = Constants.MAIN_COLOR_STATE
        setRevealViewInvisible()


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
                setRevealViewVisibility()
                setRevealViewAnimation()
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
                setRevealViewInvisible()
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

    private fun setRevealViewInvisible() {
        img_moment.visibility = View.INVISIBLE
        text_moment.visibility = View.INVISIBLE
        button_moment.visibility = View.INVISIBLE
        img_reminder.visibility = View.INVISIBLE
        text_reminder.visibility = View.INVISIBLE
        button_reminder.visibility = View.INVISIBLE
        line.setVisibility(View.INVISIBLE)
    }

    private fun setRevealViewVisibility() {
        Utils.setViewVisibility(img_moment, 550)
        Utils.setViewVisibility(text_moment, 650)
        Utils.setViewVisibility(button_moment, 750)
        Utils.setViewVisibility(line, 750)
        Utils.setViewVisibility(img_reminder, 850)
        Utils.setViewVisibility(text_reminder, 950)
        Utils.setViewVisibility(button_reminder, 1050)
    }

    private fun setRevealViewAnimation() {
        StartSmartAnimation.startAnimation(img_moment, AnimationType.SlideInUp, 450, 500, true)
        StartSmartAnimation.startAnimation(text_moment, AnimationType.SlideInUp, 400, 600, true)
        StartSmartAnimation.startAnimation(button_moment, AnimationType.SlideInUp, 350, 700, true)
        StartSmartAnimation.startAnimation(line, AnimationType.SlideInUp, 350, 700, true)
        StartSmartAnimation.startAnimation(img_reminder, AnimationType.SlideInUp, 300, 800, true)
        StartSmartAnimation.startAnimation(text_reminder, AnimationType.SlideInUp, 250, 900, true)
        StartSmartAnimation.startAnimation(button_reminder, AnimationType.SlideInUp, 200, 1000, true)
    }


}
