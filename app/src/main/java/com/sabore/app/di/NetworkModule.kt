package com.sabore.app.di

import com.sabore.app.BuildConfig
import com.sabore.app.data.local.RecipeLocalDataSource
import com.sabore.app.data.local.dao.RecipeDao
import com.sabore.app.data.network.api.AuthApi
import com.sabore.app.data.network.api.AiApi
import com.sabore.app.data.network.api.CookbookApi
import com.sabore.app.data.network.api.ErrorApi
import com.sabore.app.data.network.api.RecipesApi
import com.sabore.app.data.network.api.SubscriptionsApi
import com.sabore.app.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideRecipesApi(retrofit: Retrofit): RecipesApi = retrofit.create(RecipesApi::class.java)

    @Provides
    @Singleton
    fun provideCookbookApi(retrofit: Retrofit): CookbookApi = retrofit.create(CookbookApi::class.java)

    @Provides
    @Singleton
    fun provideAiApi(retrofit: Retrofit): AiApi = retrofit.create(AiApi::class.java)

    @Provides
    @Singleton
    fun provideSubscriptionsApi(retrofit: Retrofit): SubscriptionsApi =
        retrofit.create(SubscriptionsApi::class.java)

    @Provides
    @Singleton
    fun provideErrorApi(retrofit: Retrofit): ErrorApi = retrofit.create(ErrorApi::class.java)

    @Provides
    @Singleton
    fun provideRecipeLocalDataSource(recipeDao: RecipeDao): RecipeLocalDataSource {
        return RecipeLocalDataSource(recipeDao)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipesApi: RecipesApi,
        localDataSource: RecipeLocalDataSource
    ): RecipeRepository {
        return RecipeRepository(recipesApi, localDataSource)
    }
}
