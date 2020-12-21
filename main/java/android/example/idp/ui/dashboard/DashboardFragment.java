package android.example.idp.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.idp.ListItemDetailView;
import android.example.idp.ListItemObject;
import android.example.idp.ReminderObject;
import android.example.idp.ui.home.HomeFragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.example.idp.R;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DashboardFragment extends Fragment {

    private static final String storedListName="MainListName";
    Integer SizeOfList;
    public  ArrayList<ListItemObject> MainList=new ArrayList<ListItemObject>();
    public void loadMainList(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(storedListName, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(storedListName, null);
      //  SizeOfList=sharedPreferences.getInt("SizeOfList",0);
        Type type = new TypeToken<ArrayList<ListItemObject>>() {}.getType();
        MainList = gson.fromJson(json, type);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return updateList(inflater,container,savedInstanceState);
    }
    public void addNewItem(){
        Intent intent=new Intent(getContext(),ListItemDetailView.class);
        intent.putExtra("ifNew",true);
        startActivity(intent);
    }
    public View updateList(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_dashboard,container,false);
        FloatingActionButton floatingActionButton=view.findViewById(R.id.addNewItemButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewItem();
            }
        });
    //    createArray();
      //  loadMainList();
        ListView listView = (ListView) view.findViewById(R.id.upcomingListView);
        Time time=new Time(10,30,00);
        ListItemObject listItemObject=new ListItemObject();
        listItemObject.setName("paracetomol");
        MainList.add(0,listItemObject);
        MainList.add(1,listItemObject);
        //loadMainList();
        customAdapter adapter=new customAdapter(getContext(),0,MainList);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchDetailItemView(position);
            }
        });
        return view;
    }
    public void launchDetailItemView(Integer position){
        Intent intent=new Intent(getContext(), ListItemDetailView.class);
        intent.putExtra("ifNew",false);
        intent.putExtra("position",position);
        startActivity(intent);
    }
    private class customAdapter extends ArrayAdapter<ListItemObject> {
        public customAdapter(@NonNull Context context, int resource, @NonNull List objects) {
            super(context, 0, objects);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.mylistitem_view, parent, false);
            }
            ListItemObject currentObject = getItem(position);
            TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(currentObject.getName().toString());
            return view;
        }
    }
    void createArray(){
        for (int i=0;i<SizeOfList;i++){
            MainList.add(i,new ListItemObject());
        }
    }
}