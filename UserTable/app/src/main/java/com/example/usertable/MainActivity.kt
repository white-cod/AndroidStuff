package com.example.usertable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val names = mutableListOf<NameModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeNames()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, NamesListFragment())
                .commit()
        }
    }

    private fun initializeNames() {
        names.addAll(listOf(
            NameModel("Emma", "April 15", "Emma is a creative and ambitious software developer with a love for painting and outdoor adventures. She enjoys hiking, photography, and spending time at the beach."),
            NameModel("Liam", "August 22", "Liam is a dedicated teacher who loves working with children. He spends his free time writing poetry, playing guitar, and exploring new cuisines."),
            NameModel("Olivia", "March 8", "Olivia is an aspiring entrepreneur with a passion for technology and innovation. In her spare time, she enjoys reading science fiction novels and practicing yoga."),
            NameModel("Noah", "July 17", "Noah is a wildlife enthusiast and a professional photographer. He often travels to remote locations to capture the beauty of nature, and he loves sharing his experiences through storytelling."),
            NameModel("Ava", "March 7", "Ava is a skilled graphic designer with a keen eye for detail. She loves experimenting with digital art, baking, and gardening in her downtime.")
        ))
    }

    fun findNameModel(name: String): NameModel? {
        return names.find { it.name == name }
    }
}