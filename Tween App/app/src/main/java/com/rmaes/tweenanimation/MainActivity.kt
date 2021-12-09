package com.rmaes.tweenanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var theImageView: ImageView
    private var theAnimAlpha:Animation?=null
    private var theAnimScale:Animation?=null
    private var theAnimTranslate:Animation?=null
    private var theAnimRotate:Animation?=null
    private var theAnimView:Animation?=null
    private var theAnimation:Animation?=null
    private var theAnimation1:Animation?=null
    private var theAnimAccel:Animation?=null
    private var theAnimAccelDecel:Animation?=null
    private var theAnimDecel:Animation?=null
    private var theAnimAnticipate:Animation?=null
    private var theAnimCycle:Animation?=null
    private var theAnimLin:Animation?=null
    private var theAnimOveshoot:Animation?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        theImageView=findViewById(R.id.imageView)
        theAnimAlpha =  AnimationUtils.loadAnimation(this,R.anim.anime_alpha)
        theAnimScale =  AnimationUtils.loadAnimation(this,R.anim.anim_scale)
        theAnimTranslate =  AnimationUtils.loadAnimation(this,R.anim.anim_translate)
        theAnimRotate =  AnimationUtils.loadAnimation(this,R.anim.anim_rotate)
        theAnimView =  AnimationUtils.loadAnimation(this,R.anim.anim_view)
        theAnimation1=AnimationUtils.loadAnimation(this, R.anim.anim_view1)
        theAnimAccel=AnimationUtils.loadAnimation(this, R.anim.anim_accel)
        theAnimAccelDecel=AnimationUtils.loadAnimation(this, R.anim.anim_accel_decel)
    }

    fun onClick_Alpha(view: View) { theImageView.startAnimation(theAnimAlpha) }
    fun onClick_Scale(view: View) { theImageView.startAnimation(theAnimScale) }
    fun onClick_Rotate(view: View) {  theImageView.startAnimation(theAnimRotate) }
    fun onClick_Translate(view: View) { theImageView.startAnimation(theAnimTranslate) }
    fun onClick_view(view: View) { theImageView.startAnimation(theAnimView) }


}