package com.quipper.android.apollofrontpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.post_list_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container,
                    PostListFragment.newInstance()
                )
            }
        }
    }
}
