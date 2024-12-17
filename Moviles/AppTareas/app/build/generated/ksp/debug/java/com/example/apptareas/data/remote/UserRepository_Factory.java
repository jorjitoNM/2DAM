package com.example.apptareas.data.remote;

import com.example.apptareas.data.remote.datasource.UsersDataSource;
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
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<UsersDataSource> usersDataSourceProvider;

  public UserRepository_Factory(Provider<UsersDataSource> usersDataSourceProvider) {
    this.usersDataSourceProvider = usersDataSourceProvider;
  }

  @Override
  public UserRepository get() {
    return newInstance(usersDataSourceProvider.get());
  }

  public static UserRepository_Factory create(Provider<UsersDataSource> usersDataSourceProvider) {
    return new UserRepository_Factory(usersDataSourceProvider);
  }

  public static UserRepository newInstance(UsersDataSource usersDataSource) {
    return new UserRepository(usersDataSource);
  }
}
