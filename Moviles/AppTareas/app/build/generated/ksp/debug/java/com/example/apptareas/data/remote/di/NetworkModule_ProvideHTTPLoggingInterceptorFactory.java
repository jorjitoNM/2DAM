package com.example.apptareas.data.remote.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.logging.HttpLoggingInterceptor;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class NetworkModule_ProvideHTTPLoggingInterceptorFactory implements Factory<HttpLoggingInterceptor> {
  @Override
  public HttpLoggingInterceptor get() {
    return provideHTTPLoggingInterceptor();
  }

  public static NetworkModule_ProvideHTTPLoggingInterceptorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HttpLoggingInterceptor provideHTTPLoggingInterceptor() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideHTTPLoggingInterceptor());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideHTTPLoggingInterceptorFactory INSTANCE = new NetworkModule_ProvideHTTPLoggingInterceptorFactory();
  }
}
