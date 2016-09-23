package club.polyappdev.contactapp;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity implements EmailFragment.OnFragmentInteractionListener,
NameFragment.OnFragmentInteractionListener,
ClassFragment.OnFragmentInteractionListener,
PlatformSelectFragment.OnFragmentInteractionListener,
FavoritePizzaFragment.OnFragmentInteractionListener,
FavoriteSodaFragment.OnFragmentInteractionListener,
DoneFragment.OnFragmentInteractionListener {

    protected Student curStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        curStudent = new Student();
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
        curStudent.setEmail(email);
    }

    @Override
    public void NameFragmentListener(String first, String last) {
        curStudent.setFirstName(first);
        curStudent.setLastName(last);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void ClassFragmentListener(String classlevel){
        curStudent.setYear(classlevel);
    }

    @Override
    public void PlatformSelectListener(boolean ios, boolean android, boolean windows){
        curStudent.setInterestedAndroid(android);
        curStudent.setInterestediOS(ios);
        curStudent.setInterestedWindows(windows);
    }

    @Override
    public void FavoritePizzaListener(String favPizza){
        curStudent.setPizza(favPizza);
    }

    @Override
    public void FavoriteSodaListener(String favSoda) {
        curStudent.setSoda(favSoda);
    }

    @Override
    public void DoneListener() {
        Toast.makeText(this, curStudent.getFirstName(),
                Toast.LENGTH_LONG).show();

        int statusCode = 0;
        String BASE_URL = "https://polyapp.azurewebsites.net/";


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);

        Call<ResponseBody> call = apiService.addUser(curStudent);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("http success", "1");
                Log.i("http", response.message());
                curStudent = new Student();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("http fail", t.getMessage());
                finish();
                startActivity(getIntent());
            }
        });

    }

}

