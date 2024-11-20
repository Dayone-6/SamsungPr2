package ru.dayone.test.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.SimpleAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import ru.dayone.test.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val checkBoxes = arrayOf(binding.checkBox1, binding.checkBox2, binding.checkBox3, binding.checkBox4, binding.checkBox5, binding.checkBox6)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.spinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.tips,
            android.R.layout.simple_spinner_item
        )

        binding.button.setOnClickListener {
            val selected = checkBoxes.filter { it.isChecked }
                .joinToString(separator = ", ") { it.text.toString() }
            val delivery = findViewById<RadioButton>(binding.radiogroup.checkedRadioButtonId).text.toString()
            val tips = binding.spinner.selectedItem as String
            val withPapers = binding.sw.isChecked
            Snackbar.make(
                binding.root,
                "Выбранные продукты:\n"
                        + selected
                        + "\nДоставка:\n"
                        + delivery
                        + "\nЧаевые:\n"
                        + tips
                        + "\nС салфетками:\n"
                        + if(withPapers) { "да" } else { "нет" },
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}