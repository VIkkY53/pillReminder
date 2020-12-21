package android.example.idp.ui.home;

import android.content.SharedPreferences;
import android.example.idp.ListItemObject;
import android.example.idp.ReminderObject;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.idp.R;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.upcomingrecyclerview);
        ArrayList<ListItemObject> objects=new ArrayList<>();
        Time time=new Time(10,50,00);
        ListItemObject listItemObject=new ListItemObject();
        listItemObject.setName("pill");
        listItemObject.setTimings(0,time);
        objects.add(0,listItemObject);
        objects.add(1,listItemObject);
        objects.add(2,listItemObject);
        objects.add(3,listItemObject);
        objects.add(4,listItemObject);
        objects.add(5,listItemObject);
        objects.add(6,listItemObject);
        objects.add(7,listItemObject);
        objects.add(8,listItemObject);
        objects.add(9,listItemObject);
        objects.add(10,listItemObject);
        objects.add(11,listItemObject);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new adapter(objects));
        return view;
    }
    public ArrayList<ListItemObject> loadMainList(ArrayList<ListItemObject> MainList){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MainList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("MainList", null);
        Type type = new TypeToken<ArrayList<ListItemObject>>() {}.getType();
        MainList = gson.fromJson(json, type);
        return MainList;
    }
    private  class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        TextView texttime ;
                ArrayList<ListItemObject> list;
        public viewholder(LayoutInflater inflater, ViewGroup parent,ArrayList<ListItemObject> list) {

            super(inflater.inflate(R.layout.reminderitem_view,parent,false));
            this.list=list;
            textView=(TextView)itemView.findViewById(R.id.textView);
            texttime = (TextView) itemView.findViewById(R.id.texttime);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }
        public void bind(int i){
            ListItemObject current=list.get(0);
            System.out.println(i);
            textView.setText(current.getName());
            texttime.setText(current.getTiming(0).toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT).show();
        }
    }
    private class adapter extends RecyclerView.Adapter<viewholder>{
        ArrayList<ListItemObject> list;
        public adapter(ArrayList<ListItemObject> list){
            this.list=list;
        }
        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());

            return new viewholder(inflater,parent,list);
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder holder, int position) {

            System.out.println(position);
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}