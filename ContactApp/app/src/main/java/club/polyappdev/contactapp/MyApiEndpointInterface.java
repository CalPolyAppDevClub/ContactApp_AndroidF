package club.polyappdev.contactapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @POST("tables/UserItem/")
    @Headers({"zumo-api-version : 2.0.0"})
    Call<ResponseBody> addUser(@Body Student student);

}
