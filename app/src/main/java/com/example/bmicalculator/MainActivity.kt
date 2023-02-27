package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.TextViewOnReceiveContentListener
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)

        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)

        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)

        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener {
            if(editTextWeight.text.isBlank()){
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }
            if(editTextHeight.text.isBlank()){
                editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()
            var bmi = weight / (height/100).pow(2)

            textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
            if(bmi < 18.5){//underweight
                imageViewBMI.setImageResource(R.drawable.under)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.under))
            }else if(bmi < 25){
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
            }else{
                imageViewBMI.setImageResource(R.drawable.over)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.over))
            }
        }

        buttonReset.setOnClickListener {
            imageViewBMI.setImageResource(R.drawable.empty)
            editTextWeight.text.clear()
            editTextHeight.text.clear()
            textViewBMI.text = ""
            textViewStatus.text = ""
        }
    }

}