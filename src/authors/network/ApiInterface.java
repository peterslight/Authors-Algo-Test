package authors.network;

import authors.models.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("article_users/search")
    Call<Data> getUsers(@Query("page") int page);
}
