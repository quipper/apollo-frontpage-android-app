package com.quipper.android.apollofrontpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PostListFragment.newInstance())
                    .commitNow()
        }
    }
}
