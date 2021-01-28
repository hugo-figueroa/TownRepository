package com.android.towndirectory

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.towndirectory.databinding.ActivityMainBinding
import com.android.towndirectory.town.ui.TownDirectoryFragment

class BaseActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var progress: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val fragment = TownDirectoryFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, TownDirectoryFragment::class.java.simpleName).commit()
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hey Hero")
        builder.setMessage("Do you want to go out?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            super.onBackPressed()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.cancel()
        }
        builder.show()

    }

    fun showProgress() {
        binding.llProgressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        binding.llProgressBar.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            showAlertDialog()
        }
    }
}