package com.example.apptareas.domain.usecases.user_usercases;

import com.example.apptareas.data.remote.UserRepository;
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
public final class LogInUseCase_Factory implements Factory<LogInUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public LogInUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public LogInUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static LogInUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new LogInUseCase_Factory(userRepositoryProvider);
  }

  public static LogInUseCase newInstance(UserRepository userRepository) {
    return new LogInUseCase(userRepository);
  }
}
