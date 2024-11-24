package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userlist.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<UserModel> users;

    public UserAdapter(Context context, List<UserModel> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        }

        ImageView avatar = convertView.findViewById(R.id.user_avatar);
        TextView name = convertView.findViewById(R.id.user_name);
        TextView details = convertView.findViewById(R.id.user_details);

        UserModel user = users.get(position);

        avatar.setImageResource(user.getAvatarId());
        name.setText(user.getFirstName() + " " + user.getLastName());
        details.setText(user.getAge() + " years old, " + user.getCity() + ", " + user.getCountry());

        return convertView;
    }
}