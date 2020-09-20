package com.tutorialsbuzz.toolbarspinner

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val item = menu?.findItem(R.id.spinner)
        val spinner = item?.actionView as AppCompatSpinner

        var arrayAdapter =
            ArrayAdapter(this@MainActivity, R.layout.layout_drop_title, getCountries())
        arrayAdapter.setDropDownViewResource(R.layout.layout_drop_list)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                showToast(getCountries()[position] + " selected")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        return true
    }

    private fun getCountries(): MutableList<String> {
        val mdList = mutableListOf<String>()
        for (countryISO in Locale.getISOCountries()) {
            val locale = Locale("", countryISO)
            if (!locale.displayCountry.isEmpty()) {
                mdList.add(locale.displayCountry + "  " + counrtyFlag(countryISO))
            }
        }
        return mdList
    }

    private fun counrtyFlag(countryCode: String): String {
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
        return (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
    }

    private fun showToast(msg: String) {
        Toast.makeText(
            this@MainActivity, msg, Toast.LENGTH_SHORT
        ).show()
    }
}