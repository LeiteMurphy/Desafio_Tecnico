package com.example.desafio_tecnico.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object MaskEditUtil {
    fun mask(ediTxt: EditText): TextWatcher {
        return object : TextWatcher {
            var isUpdating: Boolean = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = unmask(s.toString())
                var mascara = ""
                val mask = "#####-###"
                if (isUpdating) {
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && count > before) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }
            override fun afterTextChanged(s: Editable?) {}
        }
    }
    fun unmask(s: String): String {
        return s.replace("-", "")
    }
}