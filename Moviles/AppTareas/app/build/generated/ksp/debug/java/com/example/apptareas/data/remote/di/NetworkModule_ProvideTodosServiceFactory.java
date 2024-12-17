package com.example.apptareas.data.remote.di;

import com.example.apptareas.data.remote.api_service.TodosService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideTodosServiceFactory implements Factory<TodosService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideTodosServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public TodosService get() {
    return provideTodosService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideTodosServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideTodosServiceFactory(retrofitProvider);
  }

  public static TodosService provideTodosService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTodosService(retrofit));
  }
}
