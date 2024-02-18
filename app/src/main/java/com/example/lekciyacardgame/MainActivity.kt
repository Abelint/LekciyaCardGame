package com.example.lekciyacardgame

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {
    companion object
    {
        var cardViewList: ArrayList<CardView> = ArrayList()
    }
    //lateinit var cardView: CardView
    lateinit var cardViewTable: TableLayout
    //private lateinit var animHide : Animation
    private lateinit var tableRow: TableRow
    //lateinit var animShow : Animation
    @SuppressLint("UseCompatLoadingForDrawables", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //cardView = findViewById(R.id.cardView)
        cardViewTable = findViewById(R.id.card_view_table)

        val cardViewCount = 8
        for(i in 1..cardViewCount) {
            if ((i - 1) % 2 == 0) {
                tableRow = TableRow(this)
                cardViewTable.addView(tableRow)
            }
            val cV = LayoutInflater.from(this).inflate(R.layout.card_view_item, null) as CardView
            cV.tag = "back"
            cardViewList.add(cV)
            cV.setOnClickListener {
                Log.e("1", "Я жмякнул 1")
                var i = 0
                for(item in cardViewList)
                {
                    if (item.tag != "back")
                    {
                        i++
                    }
                }
                if(cV.tag == "back" && i < 2)
                {
                    changeCard(cV)
                    Log.e("1", "Я жмякнул 2")
                }
            }
            tableRow?.addView(cV)
        }
    }
    fun changeCard(cardView: CardView)
    {
        var animHide = AnimationUtils.loadAnimation(this, R.anim.hide)
        var animShow = AnimationUtils.loadAnimation(this, R.anim.show)
        cardView.isEnabled = false
        val timer = object :CountDownTimer(5000,5000)
        {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish()
            {
                cardView.startAnimation(animHide)
            }
        }
        animShow.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?)
            {
                if(cardView.tag == "back")
                {
                    cardView.isEnabled = true
                    Log.e("1", "Я жмякнул 3")
                }
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        animHide.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?)
            {
                if(cardView.tag == "back")
                {
                    cardView.tag = "front"
                }
                else
                {
                    cardView.tag = "back"
                }
            }
            override fun onAnimationEnd(animation: Animation?)
            {
                if(cardView.tag == "front")
                {
                    cardView.foreground = resources.getDrawable(R.drawable.frontcard,null)
                }
                else
                {
                    cardView.foreground = resources.getDrawable(R.drawable.rubashka,null)
                }
                cardView.startAnimation(animShow)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        cardView.startAnimation(animHide)
        timer.start()
    }
}