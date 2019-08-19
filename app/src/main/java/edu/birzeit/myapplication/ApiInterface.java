package edu.birzeit.myapplication;


import edu.birzeit.myapplication.models.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ApiInterface {

    @GET("{username}")
    Call<List<UserInfo>> getUsers(@Path("username") String username);

    @GET("/api/user/getAllUsers")
    Call<List<UserInfo>> getAllUsers();

    @GET("/api/user/getuser/{id}")
    Call<UserInfo> getUserInfo(@Path("id") long id);

    @POST("/api/user/PostuserInfo") ////
    String createUser(@Body UserInfo user);

    @GET("/api/user/regester") ////
    int login(@Body Login login);

    @GET("/api/users/{userID}") /////////
    long initCase(@Path("userID") long userID);

    @GET("/api/unknown/{recordID}") ///
    String getFirstStep(@Path("recordID") long recordID);

    @POST("/api/Answer")
    Call<Question> postAnswer(@Body QustionAnswer answer);
    /*
        @GET("/api/unknown")
        Call<MultipleResource> getInstructionCare();
    */
    @GET("/api/user/getUserRecord/{userID}")
    Call<List<UserRecord>> getUserRecords(@Path("userID") long userID);

    @GET("{mocky}")
        // @GET("/api/user/getTrustedContact/{userID}")
  //  Call<List<TrustedContact>> getUserTrustedContact(@Path("userID") long userID);
     Call<List<TrustedContact>> getUserTrustedContact(@Path("mocky") String mocky);

    @GET("{mocky}")
        // @GET("/api/user/getTrustedContact/{userID}")
        //  Call<List<TrustedContact>> getUserTrustedContact(@Path("userID") long userID);
    Call<String> deleteUserTrustedContact(@Path("mocky") String mocky);

    @DELETE("/api/user/PuttrustedContact/{trustedContactID}")
    String deleteUserTrustedContact(@Path("trustedContactID") long trustedConactID);



    @GET("{username}")
    Call<List<TrustedContact>> getTrusted(@Path("username") String username);

    @POST("/api/user/PosttrustedContact")
    String addUserTrustedContact(@Body TrustedContact newTrustedContact);

    @PUT("/api/user/PuttrustedContact/{trustedContactID}")
    String UpdateUserTrustedContact(@Path("trustedContactID") long trustedConactID, @Body TrustedContact newTrustedContact);

    @PUT("/api/user/PutuserInfo/{userID}")
    String UpdateUserInfo(@Path("userID") long userID, @Body UserInfo userInfo);

}


