package cn.youcute.jy.materiDesignDemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private final String[] data = {"Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",
            "Hello,Word!", "Hello,Android!", "Hello,Material design!",};

    private FloatingActionButton floatBtn;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置ToolBar作为ActionBar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置ActionBar左上角按钮
            actionBar.setHomeAsUpIndicator(R.drawable.ic_nav);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        floatBtn = (FloatingActionButton) findViewById(R.id.float_btn);
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Float Button", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.menu_1:
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv;

            ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(data[holder.getAdapterPosition()]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
    }
}
