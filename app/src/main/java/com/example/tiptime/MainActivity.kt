package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCal.setOnClickListener{calculateTip()}
        binding.plainTextInput.setOnKeyListener{view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }
                     
    fun calculateTip() {
        val textField = binding.plainTextInput.text.toString()
        val cost = textField.toDouble()
        val selectedId = binding.rd1.checkedRadioButtonId
        val persen = when(selectedId){
            R.id.twenty -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = persen * cost
        val roundUp = binding.swtch.isChecked

        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val numFormat = NumberFormat.getCurrencyInstance()
        val formattedTip = numFormat.format(tip)
        binding.tip.text = getString(R.string.tip_amount, formattedTip)

    }
    private fun handleKeyEvent(view: View, keycode: Int): Boolean{
        if(keycode == KeyEvent.KEYCODE_ENTER){
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}
