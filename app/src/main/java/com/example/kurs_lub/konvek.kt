package com.example.kurs_lub

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class konvek : AppCompatActivity() {

    private val editTextIds = arrayOf(
        R.id.editTextNumber100, R.id.editTextNumber101, R.id.editTextNumber102, R.id.editTextNumber103,
        R.id.editTextNumber104, R.id.editTextNumber105, R.id.editTextNumber106, R.id.editTextNumber107,
        R.id.editTextNumber108, R.id.editTextNumber109, R.id.editTextNumber110, R.id.editTextNumber111,
        R.id.editTextNumber112, R.id.editTextNumber113, R.id.editTextNumber114
    )

    private val coefficientsMap = mapOf(
        R.id.editTextNumber100 to listOf(1f, 0.001f, 9.806f, 10f, 1000f, 6.10483f, 61.0483f, 2441.93206f, 78141.82452f, 234425.47056f, 2679.2289f, 32150.764917f, 257206.11328f, 771618.34984f, 15432366.5968f),
        R.id.editTextNumber101 to listOf(1000f,1f,9806.652f,10000f,1000000f,6104.8266f,61048.266f,2441930.7f,78141781f,234425340f,2679228.9f,32150747f,257205970f,771617920f,15432358000f),
        R.id.editTextNumber102 to listOf(0.10197f,0.0001f,1f,1.01972f,100f,0.61048f,6.1048f,244.19201f,7814.14418f,23442.43224f,267.92173f,3215.06078f,25720.48564f,77161.45792f,1543229.1184f),

        R.id.editTextNumber103 to listOf(0.10197f,0.0001f,0.98067f,1f,100f,0.61048f,6.1048f,244.19201f,7814.14418f,23442.43224f,267.92173f,3215.06078f,25720.48564f,77161.45792f,1543229.1184f),

        R.id.editTextNumber104 to listOf(0.001f,0.000001f,0.00981f,0.01f,1f,0.0061f,0.06105f,2.44193f,78.14176f,234.42528f,2.67923f,32.15076f,257.20607f,771.61822f,15432.364f),

        R.id.editTextNumber105 to listOf(0.1638f,0.00016f,1.60638f,1.63805f,163.80482f,1f,10f,400.00001f,12800.00009f,38399.99978f,438.8706f,5266.44723f,42131.57686f,126394.73222f,2527894.57888f),

        R.id.editTextNumber106 to listOf(0.01638f,0.00002f,0.16064f,0.1638f,16.38048f,0.1f,1f,40f,1279.99998f,3839.99989f,43.88706f,526.64472f,4213.15767f,12639.47317f,252789.45685f),

        R.id.editTextNumber107 to listOf(0.00041f,0.00000049512f,0.00402f,0.0041f,0.40951f,0.0025f,0.025f,1f,32f,96f,1.09718f,13.16612f,105.32894f,315.98682f,6319.73626f),

        R.id.editTextNumber108 to listOf(0.00001f,0.000000012797f,0.00013f,0.00013f,0.0128f,0.00008f,0.00078f,0.03125f,1f,3f,0.03429f,0.41144f,3.29152f,9.87456f,197.49119f),

        R.id.editTextNumber109 to listOf(0.000004f,0.0000000042657f,0.00004f,0.00004f,0.00427f,0.00003f,0.00026f,0.01042f,0.33333f,1f,0.01143f,0.13715f,1.09718f,3.29154f,65.8308f),


        R.id.editTextNumber110 to listOf(0.00037f,0.00000037324f,0.00366f,0.00373f,0.37324f,0.00228f,0.02279f,0.91143f,29.16577f,87.49732f,1f,12f,96f,288f,5759.99983f),

        R.id.editTextNumber111 to listOf(0.00003f,0.0000000311f,0.00031f,0.00031f,0.0311f,0.00019f,0.0019f,0.07595f,2.43048f,7.29144f,0.08333f,1f,8f,24f,479.99998f),

        R.id.editTextNumber112 to listOf(0.000004f,0.0000000038879f,0.00004f,0.00004f,0.00389f,0.00002f,0.00024f,0.00949f,0.30381f,0.91143f,0.01042f,0.125f,1f,3f,60f),

        R.id.editTextNumber113 to listOf(0.000001f,0.0000000012959f,0.00001f,0.00001f,0.0013f,0.00001f,0.00008f,0.00316f,0.10127f,0.30381f,0.00347f,0.04167f,0.33333f,1f,20f),

        R.id.editTextNumber114 to listOf(0.000000064798f,0.000000000064798f,0.00000063546f,0.00000064798f,0.00006f,0.00000039558f,0.000004f,0.00016f,0.00506f,0.01519f,0.00017f,0.00208f,0.01667f,0.05f,1f)

    )

    private var isProgrammaticChange = false

    fun Back(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.konvek)

        for (editTextId in editTextIds) {
            val editText = findViewById<EditText>(editTextId)

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    try {
                        val floatValue = s.toString().toFloat()
                        Log.d("EditText", "Type: Float, Value: $floatValue")

                        // После ввода числа в текущий EditText обновляем значения в остальных EditText
                        if (!isProgrammaticChange) {
                            updateOtherEditTexts(editTextId, floatValue)
                        }

                    } catch (e: NumberFormatException) {
                        Log.e("EditText", "Error parsing to float: ${e.message}")
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun updateOtherEditTexts(currentEditTextId: Int, value: Float) {
        val coefficients = coefficientsMap[currentEditTextId]

        if (coefficients != null) {
            for ((index, editTextId) in editTextIds.withIndex()) {
                if (editTextId != currentEditTextId) {
                    val editText = findViewById<EditText>(editTextId)

                    // Check if the index is within bounds of the coefficients list
                    if (index < coefficients.size) {
                        // Умножаем значение на соответствующий коэффициент
                        val resultValue = value * coefficients[index]

                        // Устанавливаем значение в EditText
                        isProgrammaticChange = true
                        editText.setText(resultValue.toString())
                        isProgrammaticChange = false
                    } else {
                        Log.e("UpdateEditTexts", "Index out of bounds for coefficients list.")
                    }
                }
            }
        } else {
            Log.e("UpdateEditTexts", "Coefficients not found for EditTextId: $currentEditTextId")
        }
    }
}
