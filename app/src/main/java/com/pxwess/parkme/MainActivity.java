package com.pxwess.parkme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.listview);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.list_item, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.CYAN);
                Toast.makeText(MainActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void generateListContent() {
        for(int i = 0; i < 10; i++) {
            data.add("This is row number " + i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

        //return super.onOptionsItemSelected(item);
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {


                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.imageView);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.fullButton = (Button) convertView.findViewById(R.id.FullBtn);
                viewHolder.availButton = (Button) convertView.findViewById(R.id.AvailBtn);
                viewHolder.favBtn = (Button) convertView.findViewById(R.id.favBtn);
                //viewHolder.button = (Button)vwParentRow.getChildAt(1);
                convertView.setTag(viewHolder);


            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.fullButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int c = Color.CYAN;

                    RelativeLayout vwParentRow = (RelativeLayout) v.getParent();
                    vwParentRow.setBackgroundColor(c);
                    vwParentRow.refreshDrawableState();


                    Toast.makeText(getContext(), "You Reported Lot Full ", Toast.LENGTH_SHORT).show();


                }
            });

            mainViewholder.availButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int c = Color.CYAN;

                    RelativeLayout vwParentRow = (RelativeLayout) v.getParent();
                    vwParentRow.setBackgroundColor(c);
                    vwParentRow.refreshDrawableState();


                    Toast.makeText(getContext(), "You Reported Lot Available ", Toast.LENGTH_SHORT).show();


                }
            });

            mainViewholder.favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int c = Color.CYAN;

                    RelativeLayout vwParentRow = (RelativeLayout) v.getParent();
                    vwParentRow.setBackgroundColor(c);
                    vwParentRow.refreshDrawableState();


                    Toast.makeText(getContext(), "Added to Favorites ", Toast.LENGTH_SHORT).show();
                }

            });

            mainViewholder.title.setText(getItem(position));


            return convertView;
        }
    }


    public class ViewHolder {

        ImageView thumbnail;
        TextView title;
        Button availButton;
        Button fullButton;
        Button favBtn;
    }
}