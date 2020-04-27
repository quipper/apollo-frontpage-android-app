package com.quipper.android.apollofrontpage.model

data class Posts(var id: Int, var title: String, var votes: Int, var author: Author)

data class Author(var firstName: String, var lastName: String)