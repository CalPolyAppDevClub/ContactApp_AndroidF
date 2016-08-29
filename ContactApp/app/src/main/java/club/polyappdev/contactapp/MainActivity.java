package club.polyappdev.contactapp;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EmailFragment.OnFragmentInteractionListener,
NameFragment.OnFragmentInteractionListener,
ClassFragment.OnFragmentInteractionListener,
PlatformSelectFragment.OnFragmentInteractionListener,
FavoritePizzaFragment.OnFragmentInteractionListener,
FavoriteSodaFragment.OnFragmentInteractionListener,
DoneFragment.OnFragmentInteractionListener {

    protected Student s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        s = new Student();
        Fragment fragment = new MainActivityFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void EmailFragmentListener(String email) {
        s.setEmail(email);
    }

    @Override
    public void NameFragmentListener(String first, String last) {
        s.setFirstName(first);
        s.setLastName(last);
    }

    @Override
    public void ClassFragmentListener(String classlevel){
        s.setYear(classlevel);
    }

    @Override
    public void PlatformSelectListener(boolean ios, boolean android, boolean windows){
        s.setInterestedAndroid(android);
        s.setInterestediOS(ios);
        s.setInterestedWindows(windows);
    }

    @Override
    public void FavoritePizzaListener(String favPizza){
        s.setPizza(favPizza);
    }

    @Override
    public void FavoriteSodaListener(String favSoda) {
        s.setSoda(favSoda);
    }

    @Override
    public void DoneListener() {
        Toast.makeText(this, s.getFirstName(),
                Toast.LENGTH_LONG).show();
    }

}
