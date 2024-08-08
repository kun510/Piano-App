package com.kun510.piano

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import com.kun510.pianoview.entity.AutoPlayEntity
import com.kun510.pianoview.entity.Piano
import com.kun510.pianoview.listener.OnLoadAudioListener
import com.kun510.pianoview.listener.OnPianoAutoPlayListener
import com.kun510.pianoview.listener.OnPianoListener
import com.kun510.pianoview.utils.AutoPlayUtils
import com.kun510.pianoview.view.PianoView
import java.io.IOException

class PlayActivity : Activity(), OnPianoListener, OnLoadAudioListener,
    SeekBar.OnSeekBarChangeListener, View.OnClickListener, OnPianoAutoPlayListener {
    private val CONFIG_FILE_NAME = "exp_simple_song"
    private val USE_CONFIG_FILE = true
    private lateinit var pianoView: PianoView
    private lateinit var seekBar: SeekBar
    private lateinit var leftArrow: Button
    private lateinit var rightArrow: Button
    private lateinit var btnMusic: Button
    private var scrollProgress = 0
    private val SEEKBAR_OFFSET_SIZE = -12f
    private var isPlay = false
    private var litterStarList: ArrayList<AutoPlayEntity>? = null
    private val LITTER_STAR_BREAK_SHORT_TIME = 500L
    private val LITTER_STAR_BREAK_LONG_TIME = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //view
        pianoView = findViewById(R.id.pv)
        pianoView.setSoundPollMaxStream(10)
        seekBar = findViewById(R.id.sb)
        seekBar.thumbOffset = convertDpToPixel(SEEKBAR_OFFSET_SIZE).toInt()
        leftArrow = findViewById(R.id.iv_left_arrow)
        rightArrow = findViewById(R.id.iv_right_arrow)
        btnMusic = findViewById(R.id.iv_music)

        //listener
        pianoView.setPianoListener(this)
        pianoView.setAutoPlayListener(this)
        pianoView.setLoadAudioListener(this)
        seekBar.setOnSeekBarChangeListener(this)
        rightArrow.setOnClickListener(this)
        leftArrow.setOnClickListener(this)
        btnMusic.setOnClickListener(this)

        //init
        if (USE_CONFIG_FILE) {
            val assetManager = assets
            try {
                litterStarList = AutoPlayUtils.getAutoPlayEntityListByCustomConfigInputStream(
                    assetManager.open(CONFIG_FILE_NAME)
                )
            } catch (e: IOException) {
                Log.e("TAG", e.message ?: "Error reading config file")
            }
        } else {
            initLitterStarList()
        }
    }

    private fun initLitterStarList() {
        litterStarList = ArrayList<AutoPlayEntity>().apply {
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 0, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 0, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 5, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 5, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_LONG_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 1, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 1, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 0, LITTER_STAR_BREAK_LONG_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 1, LITTER_STAR_BREAK_LONG_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 1, LITTER_STAR_BREAK_LONG_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 0, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 0, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 5, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 5, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 4, LITTER_STAR_BREAK_LONG_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 3, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 2, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 1, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 1, LITTER_STAR_BREAK_SHORT_TIME))
            add(AutoPlayEntity(Piano.PianoKeyType.WHITE, 4, 0, LITTER_STAR_BREAK_LONG_TIME))
        }
    }

    override fun onPianoInitFinish() {
        // handle piano init finish
    }

    override fun onPianoClick(
        type: Piano.PianoKeyType,
        voice: Piano.PianoVoice,
        group: Int,
        positionOfGroup: Int,
    ) {
        // handle piano click
    }

    override fun loadPianoAudioStart() {
        Toast.makeText(applicationContext, "loadPianoMusicStart", Toast.LENGTH_SHORT).show()
    }

    override fun loadPianoAudioFinish() {
        Toast.makeText(applicationContext, "loadPianoMusicFinish", Toast.LENGTH_SHORT).show()
    }

    override fun loadPianoAudioError(e: Exception) {
        Toast.makeText(applicationContext, "loadPianoMusicError", Toast.LENGTH_SHORT).show()
    }

    override fun loadPianoAudioProgress(progress: Int) {
        Log.e("TAG", "progress: $progress")
    }

    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
        pianoView.scroll(i)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        // handle start tracking touch
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        // handle stop tracking touch
    }
    override fun onPianoAutoPlayStart() {
        Toast.makeText(this, "onPianoAutoPlayStart", Toast.LENGTH_SHORT).show()
    }

    override fun onPianoAutoPlayEnd() {
        Toast.makeText(this, "onPianoAutoPlayEnd", Toast.LENGTH_SHORT).show()
        isPlay = false
    }
    override fun onResume() {
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        super.onResume()
    }

    override fun onClick(view: View) {
        if (scrollProgress == 0) {
            scrollProgress = (pianoView.layoutWidth * 100) / pianoView.pianoWidth
        }
        val progress = when (view.id) {
            R.id.iv_left_arrow -> {
                if (scrollProgress == 0) {
                    0
                } else {
                    (seekBar.progress - scrollProgress).coerceAtLeast(0)
                }
            }

            R.id.iv_right_arrow -> {
                if (scrollProgress == 0) {
                    100
                } else {
                    (seekBar.progress + scrollProgress).coerceAtMost(100)
                }
            }

            R.id.iv_music -> {
                if (!isPlay) {
                    pianoView.autoPlay(litterStarList)
                }
                return
            }

            else -> return
        }
        seekBar.progress = progress
    }

    private fun convertDpToPixel(dp: Float): Float {
        val resources = this.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }



    override fun onDestroy() {
        super.onDestroy()
        pianoView.releaseAutoPlay()
    }
}

