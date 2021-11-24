package mca.myapplication

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var gameactive: Boolean = true
    lateinit var btn0 : Button
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button

    var player1 = 0
    var player2 = 1
    var winPlayer = -1
    var activityplayer = player1
    var filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)

    lateinit var txtplayer : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0=findViewById(R.id.btn0)
        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)
        btn6=findViewById(R.id.btn6)
        btn7=findViewById(R.id.btn7)
        btn8=findViewById(R.id.btn8)

        txtplayer = findViewById(R.id.textView2)

        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        txtplayer.setTextColor(Color.parseColor("#ff99cc00"))
    }

    override fun onClick(p0: View?) {
        if (!gameactive)
            return
        var btn_click = findViewById<Button>(p0!!.id)
        var clicked_tag = btn_click.tag.toString().toInt()

        if (filledPos[clicked_tag] != -1)
            return

        filledPos[clicked_tag] = activityplayer
        if (activityplayer == player1) {
            btn_click.setText("O")
            activityplayer = player2
            txtplayer.setText("Player 2 Turn")
            txtplayer.setTextColor(Color.parseColor("#ffffbb33"))
            btn_click.setTextColor(Color.BLACK)
            btn_click.backgroundTintList = getColorStateList(android.R.color.holo_green_light)
        }
        else {
            btn_click.setText("X")
            activityplayer = player1
            txtplayer.setText("Player 1 Turn")
            txtplayer.setTextColor(Color.parseColor("#ff99cc00"))
            btn_click.backgroundTintList = getColorStateList(android.R.color.holo_orange_light)
            btn_click.setTextColor(Color.BLACK)
        }
        ckeckForWin()
    }

    private fun ckeckForWin() {
        var winPos = arrayOf(intArrayOf(0,1,2),intArrayOf(3,4,5),intArrayOf(6,7,8),
                    intArrayOf(0,3,6),intArrayOf(1,4,7),intArrayOf(2,5,8),
                    intArrayOf(0,4,8),intArrayOf(2,4,6))
        for (i in 0 until winPos.size){
            var val0 = winPos[i][0]
            var val1 = winPos[i][1]
            var val2 = winPos[i][2]

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if (filledPos[val0] != -1) {
                    gameactive = false
                    if (filledPos[val0] == player1) {
                        winPlayer = player1
                        showMessage("Player 1 is Winner")
                    } else {
                        winPlayer = player2
                        showMessage("Player 2 is Winner")
                    }
                    return
                }
            }
        }
        var count = 0
        for (i in 0 until filledPos.size){
            if (filledPos[i] == -1)
                count++
        }
        if (count==0){
            showMessage("It's Draw")
            return
        }
    }

    private fun showMessage(s: String) {
        AlertDialog.Builder(this).setTitle("Tic Tac Toe - Winner")
            .setMessage(s)
            .setPositiveButton("OK",DialogInterface.OnClickListener{dialogInterface, i ->
            restart()
        }
            .show()
    }

    private fun restart() {
        gameactive =true
        if (winPlayer == player1) {
            activityplayer = player1
            txtplayer.setText("Player 1 Turn")
            txtplayer.setTextColor(Color.parseColor("#ff99cc00"))
        }
        else {
            activityplayer = player2
            txtplayer.setText("Player 2 Turn")
            txtplayer.setTextColor(Color.parseColor("#ffffbb33"))
        }

        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        btn0.setText("")
        btn1.setText("")
        btn2.setText("")
        btn3.setText("")
        btn4.setText("")
        btn5.setText("")
        btn6.setText("")
        btn7.setText("")
        btn8.setText("")

        btn0.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn1.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn2.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn3.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn4.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn5.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn6.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn7.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
        btn8.backgroundTintList = getColorStateList(R.color.design_default_color_primary)
    }

}