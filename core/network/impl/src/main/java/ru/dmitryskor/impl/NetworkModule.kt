package ru.dmitryskor.impl

import dagger.Module
import ru.dmitryskor.di.scopes.AppScope

@Module
@AppScope
class NetworkModule {

//    @AppScope
//    @Provides
//    fun providerOkHttpConfig(): OkHttpConfig.() -> Unit {
//        return {}
//    }
//
//    @AppScope
//    @Provides
//    fun providerKtorEngine(block: OkHttpConfig.() -> Unit): HttpClientEngine {
//        return OkHttp.create(block)
//    }
//
//    fun providerHttpClientConfig(): HttpClientConfig<*>.() -> Unit {
//        return {}
//    }
//
//    @AppScope
//    @Provides
//    fun providerKtorClient(
//        engine: HttpClientEngine,
//        block: HttpClientConfig<*>.() -> Unit
//    ): HttpClient {
//        return HttpClient(engine, block)
//    }
}