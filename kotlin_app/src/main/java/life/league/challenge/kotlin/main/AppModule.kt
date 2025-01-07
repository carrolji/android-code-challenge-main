package life.league.challenge.kotlin.main

import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.usecase.GetPostsUseCase
import life.league.challenge.kotlin.usecase.LoginUseCase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val NAMED_OKHTTP_WITH_AUTH: String = "OkHttp:WithAuth"
private const val HOST = "https://engineering.league.dev/challenge/api/"

val appModule = module {
    single<SessionManager> {
        SessionManager(androidContext())
    }

    factory<OkHttpClient>(named(NAMED_OKHTTP_WITH_AUTH)) {
        OkHttpClient.Builder()
            .addInterceptor(AuthHeaderInterceptor(get()))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<Api> {
        Retrofit.Builder()
            .client(get(named(NAMED_OKHTTP_WITH_AUTH)))
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    single<PostRepository> {
        PostRepositoryImpl(get(), get())
    }

    single<GetPostsUseCase> {
        GetPostsUseCase(get(), get())
    }

    single<LoginUseCase> {
        LoginUseCase(get())
    }

    viewModel {
        MainViewModel(get(), get())
    }
}