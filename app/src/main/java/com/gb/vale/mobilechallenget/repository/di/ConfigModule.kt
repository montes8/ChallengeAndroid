package com.gb.vale.mobilechallenget.repository.di

import android.content.Context
import androidx.room.Room
import com.gb.vale.mobilechallenget.BuildConfig
import com.gb.vale.mobilechallenget.application.ChallengeApplication
import com.gb.vale.mobilechallenget.repository.db.ChallengeDataBase
import com.gb.vale.mobilechallenget.repository.db.DefaultBDCallback
import com.gb.vale.mobilechallenget.repository.db.dao.EstablishmentDao
import com.gb.vale.mobilechallenget.repository.network.ChallengeService
import com.gb.vale.mobilechallenget.utils.CONTENT_TYPE
import com.gb.vale.mobilechallenget.utils.MY_CONTENT_TYPE
import com.gb.vale.mobilechallenget.utils.MY_TIME_ON
import com.gb.vale.mobilechallenget.utils.URL_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun providerMyDatabase(@ApplicationContext context: Context,
         provider: Provider<EstablishmentDao>
    ) =
        Room.databaseBuilder(context, ChallengeDataBase::class.java, "challenge_mobile.db").
        addCallback(DefaultBDCallback(provider)).build()

    @Singleton
    @Provides
    fun providerUserDao(db: ChallengeDataBase) =
        db.establishmentDao()

}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideBaseUrl() = URL_BASE


    @Singleton
    @Provides
    fun provideContext(application: ChallengeApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(MY_TIME_ON, TimeUnit.SECONDS)
            .writeTimeout(MY_TIME_ON, TimeUnit.SECONDS)
            .readTimeout(MY_TIME_ON, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideChallengeService(retrofit: Retrofit) = retrofit.create(ChallengeService::class.java)


    @Singleton
    @Provides
    fun providerHeaderInterceptor(): Interceptor {
        return ApiInterceptor()
    }


}

class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
            .addHeader(MY_CONTENT_TYPE, CONTENT_TYPE)
        request = builder.build()
        return chain.proceed(request)

    }

}




