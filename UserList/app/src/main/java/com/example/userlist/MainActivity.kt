package com.example.userlist

import model.UserModel
import model.UserAdapter
import android.os.Bundle
import android.widget.ListView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = generateUsers()

        val adapter = UserAdapter(this, users)
        val listView: ListView = findViewById(R.id.user_list)
        listView.adapter = adapter
    }

    private fun generateUsers(): List<UserModel> {
        val avatars = listOf(
            R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3,
            R.drawable.avatar4, R.drawable.avatar5
        )
        val firstNames = listOf("Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Harry", "Ivy", "Jack")
        val lastNames = listOf("Johnson", "Smith", "Brown", "Taylor", "Lee", "White", "Harris", "Martin", "Garcia", "Wilson")
        val countriesAndCities = listOf(
            Pair("USA", listOf("New York", "Los Angeles", "Chicago", "Houston", "Miami")),
            Pair("UK", listOf("London", "Manchester", "Birmingham", "Leeds", "Bristol")),
            Pair("Canada", listOf("Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa"))
        )

        val random = java.util.Random()
        val users = mutableListOf<UserModel>()

        repeat(100) {
            val avatar = avatars[random.nextInt(avatars.size)]
            val firstName = firstNames[random.nextInt(firstNames.size)]
            val lastName = lastNames[random.nextInt(lastNames.size)]
            val age = (14..99).random().toLong()
            val (country, cities) = countriesAndCities[random.nextInt(countriesAndCities.size)]
            val city = cities[random.nextInt(cities.size)]

            users.add(UserModel(avatar, firstName, lastName, age, country, city))
        }

        return users
    }
}