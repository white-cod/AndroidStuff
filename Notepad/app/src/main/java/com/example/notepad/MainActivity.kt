package com.example.notepad

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private val notes = mutableListOf<Note>()
    private var selectedNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = NoteAdapter(notes) { note, view ->
            selectedNote = note
            view.showContextMenu()
            true
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        registerForContextMenu(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_note -> {
                startActivityForResult(
                    Intent(this, NoteEditorActivity::class.java),
                    ADD_NOTE_REQUEST
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                selectedNote?.let {
                    val intent = Intent(this, NoteEditorActivity::class.java)
                    intent.putExtra(EXTRA_NOTE_ID, it.id)
                    intent.putExtra(EXTRA_NOTE_TITLE, it.title)
                    intent.putExtra(EXTRA_NOTE_CONTENT, it.content)
                    startActivityForResult(intent, EDIT_NOTE_REQUEST)
                }
                true
            }
            R.id.action_delete -> {
                selectedNote?.let {
                    notes.remove(it)
                    adapter.notifyDataSetChanged()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                ADD_NOTE_REQUEST -> {
                    val title = data?.getStringExtra(EXTRA_NOTE_TITLE) ?: ""
                    val content = data?.getStringExtra(EXTRA_NOTE_CONTENT) ?: ""
                    val note = Note(System.currentTimeMillis(), title, content)
                    notes.add(note)
                    adapter.notifyItemInserted(notes.size - 1)
                }
                EDIT_NOTE_REQUEST -> {
                    val id = data?.getLongExtra(EXTRA_NOTE_ID, -1) ?: -1
                    val title = data?.getStringExtra(EXTRA_NOTE_TITLE) ?: ""
                    val content = data?.getStringExtra(EXTRA_NOTE_CONTENT) ?: ""

                    val index = notes.indexOfFirst { it.id == id }
                    if (index != -1) {
                        notes[index] = Note(id, title, content)
                        adapter.notifyItemChanged(index)
                    }
                }
            }
        }
    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
        const val EXTRA_NOTE_ID = "extra_note_id"
        const val EXTRA_NOTE_TITLE = "extra_note_title"
        const val EXTRA_NOTE_CONTENT = "extra_note_content"
    }
}