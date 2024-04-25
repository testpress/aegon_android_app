package in.testpress.testpress;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiServiceFactory {
    public static Apiservice getApiservie() {
//        https://lmsdemo.testpress.in/api/
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).readTimeout
                (2, TimeUnit.MINUTES).writeTimeout(30, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://aegonlearning.testpress.in/api/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        return retrofit.create(Apiservice.class);
    }

    public interface Apiservice {
        @Multipart
        @POST("v2.5/auth/generate-otp/")
        Call<ResponseBody> singIn(@Part("country_code") RequestBody countryCode, @Part("phone_number") RequestBody phoneNumber);

        @Multipart
        @POST("v2.5/auth/otp-login/")
        Call<ResponseBody> signInWithOtp(@Part("phone_number") RequestBody countryCode, @Part("otp") RequestBody phoneNumber);

        @GET("v2.4/dashboard/")
        Call<ResponseBody> getDashboard(@Header("Authorization") String token, @Header("User-Agent") String agent);
//        v2.5/products/
        @GET("v3/products/")
        Call<ResponseBody> getProducts(@Header("Authorization") String token, @Header("User-Agent") String agent, @Query("page")String page);
        @GET("v2.5/me/")
        Call<ResponseBody> getUserDetails(@Header("Authorization") String token, @Header("User-Agent") String agent);
        @GET("v3/products/{productId}")
        Call<ResponseBody> getProductcontentDetails(@Header("Authorization") String token, @Header("User-Agent") String agent,@Path("productId") String pid);
    }

    public static boolean isNetworkAvailable(Context loginActivityBase) {
        ConnectivityManager connectivityManager = (ConnectivityManager) loginActivityBase.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
