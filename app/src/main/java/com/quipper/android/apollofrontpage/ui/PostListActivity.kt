package com.quipper.android.apollofrontpage.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.quipper.android.apollofrontpage.R

class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.post_list_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container, PostListFragment.newInstance())
            }
        }
    }
}
