package com.example.graphingcalculator

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : Activity() {

    private lateinit var inputField: EditText
    private var currentInput = ""
    private var operator = ""
    private var firstOperand = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputField = findViewById(R.id.inputField)

        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonClear: Button = findViewById(R.id.buttonClear)

        setNumberClickListener(button0, "0")
        setNumberClickListener(button1, "1")
        setNumberClickListener(button2, "2")
        setNumberClickListener(button3, "3")
        setNumberClickListener(button4, "4")
        setNumberClickListener(button5, "5")
        setNumberClickListener(button6, "6")
        setNumberClickListener(button7, "7")
        setNumberClickListener(button8, "8")
        setNumberClickListener(button9, "9")

        setOperatorClickListener(buttonAdd, "+")
        setOperatorClickListener(buttonSubtract, "-")
        setOperatorClickListener(buttonMultiply, "*")
        setOperatorClickListener(buttonDivide, "/")

        buttonEquals.setOnClickListener {
            if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
                val secondOperand = currentInput.toDoubleOrNull() ?: return@setOnClickListener
                val result = calculate(firstOperand, secondOperand, operator)
                inputField.setText(result.toString())
                currentInput = result.toString()
                operator = ""
                firstOperand = 0.0
            }
        }

        buttonClear.setOnClickListener {
            inputField.setText("")
            currentInput = ""
            operator = ""
            firstOperand = 0.0
        }
    }

    private fun setNumberClickListener(button: Button, number: String) {
        button.setOnClickListener {
            currentInput += number
            inputField.setText(currentInput)
        }
    }

    private fun setOperatorClickListener(button: Button, op: String) {
        button.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                firstOperand = currentInput.toDoubleOrNull() ?: return@setOnClickListener
                operator = op
                currentInput = ""
            }
        }
    }

    private fun calculate(firstOperand: Double, secondOperand: Double, operator: String): Double? {
        return try {
            when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> {
                    if (secondOperand == 0.0) {
                        throw ArithmeticException("Деление на ноль")
                    }
                    firstOperand / secondOperand
                }
                else -> throw IllegalArgumentException("Неподдерживаемая операция")
            }
        } catch (e: ArithmeticException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}