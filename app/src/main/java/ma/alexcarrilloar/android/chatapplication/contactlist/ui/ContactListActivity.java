package ma.alexcarrilloar.android.chatapplication.contactlist.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ma.alexcarrilloar.android.chatapplication.R;
import ma.alexcarrilloar.android.chatapplication.contactlist.ContactListPresenter;
import ma.alexcarrilloar.android.chatapplication.contactlist.ContactListPresenterImpl;
import ma.alexcarrilloar.android.chatapplication.contactlist.adapters.ContactListAdapter;
import ma.alexcarrilloar.android.chatapplication.contactlist.adapters.OnItemClickListener;
import ma.alexcarrilloar.android.chatapplication.entities.User;
import ma.alexcarrilloar.android.chatapplication.lib.GlideImageLoader;
import ma.alexcarrilloar.android.chatapplication.lib.ImageLoader;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private ContactListPresenter presenter;
    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
        presenter = new ContactListPresenterImpl(this);
        presenter.onCreate();
        setupToolbar();

    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplication());
        /*User user = new User();
        user.setOnline(false);
        user.setEmail("alexcarrilloar@gmail.com");*/

        adapter = new ContactListAdapter(new ArrayList<User>(),loader,this);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @OnClick(R.id.fab)
    public void addContact()
    {

    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(User user) {

        presenter.removeContact(user.getEmail());
  }
}
