package com.example.vishalrajput.splitthebill;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import splitthebill.dto.Item;

public class MainActivity extends AppCompatActivity {

    GridView gv;
    ArrayList<Item> category = new ArrayList<>();
    MyAdapter adapter;
    double amount=0,amtEach;
    int person=0;
    EditText editText1,editText2;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView) findViewById(R.id.gvid);

        category.add(new Item("Food", R.drawable.dining));
        category.add(new Item("Movie", R.drawable.movie));
        category.add(new Item("Travel", R.drawable.travel));
        category.add(new Item("Gift", R.drawable.gift));
        category.add(new Item("Home Rent", R.drawable.home));
        category.add(new Item("Others", R.drawable.others));

        adapter = new MyAdapter();
        gv.setAdapter(adapter);

        editText1 = (EditText)findViewById(R.id.amount);

        editText2 = (EditText)findViewById(R.id.person);
        //person = Integer.parseInt(editText2.getText().toString());

        text = (TextView)findViewById(R.id.eachAmount);
        TextWatcher AWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().equals("")) {
                    amount = Double.parseDouble(editText1.getText().toString());
                    person = Integer.parseInt(s.toString());
                    amtEach = (double) amount / person;
                    text.setText("Rs " + amtEach + " per person");
                } else {
                    text.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        editText2.addTextChangedListener(AWatcher);

        Button bt = (Button) findViewById(R.id.btshareid);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message1 = "I just split the <";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "I just split the <"+adapter.clickedItem + "> bill using my taskapp. It is Rs " + amount+ " per person");
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }


    public class MyAdapter extends BaseAdapter {

        public String clickedItem;
        private int previousSelectedElement = -1;

        @Override
        public int getCount() {
            return category.size();
        }

        @Override
        public Object getItem(int position) {
            return category.get(position);
        }

        @Override
        public long getItemId(int position) {
            return category.get(position).getImgid();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            View view = inflater.inflate(R.layout.gridelements, null);

            final ImageView iv = (ImageView)view.findViewById(R.id.catimgid);
            final TextView tv  = (TextView)view.findViewById(R.id.tvnameid);

            //final Item obj = category.get(position);

            iv.setImageResource(category.get(position).getImgid());
            tv.setText(category.get(position).getName());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedItem = category.get(position).getName();
                    previousSelectedElement++;
                    if (previousSelectedElement == 0) {

                        v.setBackgroundColor(Color.parseColor("#607D8B"));
                        tv.setTextColor(Color.parseColor("#ffffff"));
                    }

                }
            });

            return view;
        }
    }


}
