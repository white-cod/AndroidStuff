package com.example.notepad

data class Note(
    var id: Long = 0,
    var title: String = "",
    var content: String = "",
    var timestamp: Long = System.currentTimeMillis()
)