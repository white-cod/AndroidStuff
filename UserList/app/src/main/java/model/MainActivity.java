package model;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userlist.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<UserModel> users = generateUsers();

        UserAdapter adapter = new UserAdapter(this, users);
        ListView listView = findViewById(R.id.user_list);
        listView.setAdapter(adapter);
    }

    private List<UserModel> generateUsers() {
        int[] avatars = {R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4, R.drawable.avatar5};
        String[] firstNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Harry", "Ivy", "Jack"};
        String[] lastNames = {"Johnson", "Smith", "Brown", "Taylor", "Lee", "White", "Harris", "Martin", "Garcia", "Wilson"};
        String[][] countriesAndCities = {
                {"USA", "New York", "Los Angeles", "Chicago", "Houston", "Miami"},
                {"UK", "London", "Manchester", "Birmingham", "Leeds", "Bristol"},
                {"Canada", "Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa"}
        };

        Random random = new Random();
        List<UserModel> users = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int avatar = avatars[random.nextInt(avatars.length)];
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            long age = 14 + random.nextInt(86); // Random age between 14 and 99
            int countryIndex = random.nextInt(countriesAndCities.length);
            String country = countriesAndCities[countryIndex][0];
            String city = countriesAndCities[countryIndex][random.nextInt(5) + 1];

            users.add(new UserModel(avatar, firstName, lastName, age, country, city));
        }

        return users;
    }
}