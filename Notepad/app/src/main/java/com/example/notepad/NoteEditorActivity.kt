package com.example.notepad

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NoteEditorActivity : AppCompatActivity() {
    private lateinit var titleEdit: EditText
    private lateinit var contentEdit: EditText
    private var noteId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        titleEdit = findViewById(R.id.titleEdit)
        contentEdit = findViewById(R.id.contentEdit)

        noteId = intent.getLongExtra(MainActivity.EXTRA_NOTE_ID, -1)
        if (noteId != -1L) {
            titleEdit.setText(intent.getStringExtra(MainActivity.EXTRA_NOTE_TITLE))
            contentEdit.setText(intent.getStringExtra(MainActivity.EXTRA_NOTE_CONTENT))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.editor_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        val resultIntent = Intent().apply {
            putExtra(MainActivity.EXTRA_NOTE_ID, noteId)
            putExtra(MainActivity.EXTRA_NOTE_TITLE, titleEdit.text.toString())
            putExtra(MainActivity.EXTRA_NOTE_CONTENT, contentEdit.text.toString())
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}