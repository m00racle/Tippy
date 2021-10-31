package com.m00racle.tippy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "info log"
private const val INITIAL_TIP_PERCENTAGE = 15

class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip : SeekBar
    private lateinit var tvTipPercent : TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercent = findViewById(R.id.tvTipPercentageLabel)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)

//        put the initial percentage on tv percentage and set the seekbar to initial percentage
        seekBarTip.progress = INITIAL_TIP_PERCENTAGE
        tvTipPercent.text = "$INITIAL_TIP_PERCENTAGE%"

        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
//                Log.i(TAG, "onProgress Changed: $progress")
//                now I want this to update the tv tip percent label
                tvTipPercent.text = "$progress%"
                computeAll()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

//        now for the edit text I need to add callback when user change the text
       etBaseAmount.addTextChangedListener(object : TextWatcher{
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

           }

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

           }

           override fun afterTextChanged(p0: Editable?) {
//               Log.i(TAG, "after text change: $p0")
               computeAll()
           }

       })
    }

    private fun computeAll() {
        if (etBaseAmount.text.isBlank()) {
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
        } else {
            val baseAmount: Double = etBaseAmount.text.toString().toDouble()
            val tipPercent: Int = seekBarTip.progress
//        compute the tip and total
            val tipAmount = baseAmount * tipPercent/100
//        update the UI
            tvTipAmount.text = "%.2f".format(tipAmount)
//        compute teh total bill
            val totalAmount = baseAmount + tipAmount
//        update the UI
            tvTotalAmount.text = "%.2f".format(totalAmount)
        }

    }
}