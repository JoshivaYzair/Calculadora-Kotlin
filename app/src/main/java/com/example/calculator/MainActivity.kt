package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var oper : Int = 0
    var numero : Double = 0.0
    lateinit var tv_num1 : TextView
    lateinit var tv_num2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_num1 = findViewById(R.id.tv_num1)
        tv_num2 = findViewById(R.id.tv_num2)

        val btnBorrar: Button = findViewById(R.id.btn_borrar)
        val btnIgual : Button = findViewById(R.id.btn_equ)


        tv_num2.addTextChangedListener(object : TextWatcher {
            private var isEditing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                    val text = s.toString()
                    if (text.contains(".") && text.lastIndexOf(".") != text.indexOf(".")) {
                        s?.delete(s.length - 1, s.length)
                    }
            }
        })

        btnIgual.setOnClickListener {
            if (!tv_num1.text.isNullOrEmpty() && !tv_num2.text.isNullOrEmpty()){
                if (!tv_num2.text.toString().equals(".")){
                    var resp : Double = 0.0
                    var numero2 : Double = tv_num2.text.toString().toDouble()
                    when(oper){
                        1 -> resp = numero + numero2
                        2 -> resp = numero - numero2
                        3 -> resp = numero * numero2
                        4 -> resp = numero / numero2
                    }
                    tv_num2.setText(resp.toString())
                    tv_num1.setText("")
                }
            }
        }

        btnBorrar.setOnClickListener {
            tv_num1.setText("")
            tv_num2.setText("")
            numero = 0.0
            oper = 0
        }
    }

    fun presionarDig (view: View){
        var num2: String = tv_num2.text.toString()
        when(view.id){
            R.id.btn_0 -> tv_num2.setText(num2 + "0")
            R.id.btn_1 -> tv_num2.setText(num2 + "1")
            R.id.btn_2 -> tv_num2.setText(num2 + "2")
            R.id.btn_3 -> tv_num2.setText(num2 + "3")
            R.id.btn_4 -> tv_num2.setText(num2 + "4")
            R.id.btn_5 -> tv_num2.setText(num2 + "5")
            R.id.btn_6 -> tv_num2.setText(num2 + "6")
            R.id.btn_7 -> tv_num2.setText(num2 + "7")
            R.id.btn_9 -> tv_num2.setText(num2 + "8")
            R.id.btn_9 -> tv_num2.setText(num2 + "9")
            R.id.btn_dot -> tv_num2.setText(num2 + ".")
        }
    }

    fun operacion(view: View){

        if (!tv_num2.text.isNullOrEmpty()){
            numero = tv_num2.text.toString().toDouble()
            var num2_text : String = tv_num2.text.toString()
            tv_num2.setText("")
            when(view.id){
                R.id.btn_plus -> {
                    tv_num1.setText(num2_text + "+")
                    oper = 1
                }
                R.id.btn_sub -> {
                    tv_num1.setText(num2_text + "-")
                    oper = 2
                }
                R.id.btn_mul -> {
                    tv_num1.setText(num2_text + "*")
                    oper = 3
                }
                R.id.btn_div -> {
                    tv_num1.setText(num2_text + "/")
                    oper = 4
                }
            }
        }

    }
}