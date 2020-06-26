package com.formationandroid.memokotlin.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.formationandroid.memokotlin.R
import com.formationandroid.memokotlin.fragments.DetailFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val intent = intent
        val memo = intent.getStringExtra("memo")

        Log.i("tag", memo)

        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString("memo", memo)
        fragment.setArguments(bundle)

        val fragmentManager = supportFragmentManager

        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.conteneur_detail, fragment, "detail")
        fragmentTransaction.commit()
    }
}
