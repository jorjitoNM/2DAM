package com.example.apptareas.data.remote.datasource;

import com.example.apptareas.data.remote.api_service.UserService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class UsersDataSource_Factory implements Factory<UsersDataSource> {
  private final Provider<UserService> userServiceProvider;

  public UsersDataSource_Factory(Provider<UserService> userServiceProvider) {
    this.userServiceProvider = userServiceProvider;
  }

  @Override
  public UsersDataSource get() {
    return newInstance(userServiceProvider.get());
  }

  public static UsersDataSource_Factory create(Provider<UserService> userServiceProvider) {
    return new UsersDataSource_Factory(userServiceProvider);
  }

  public static UsersDataSource newInstance(UserService userService) {
    return new UsersDataSource(userService);
  }
}
