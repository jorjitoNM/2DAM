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
public final class GetUserUseCase_Factory implements Factory<GetUserUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public GetUserUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public GetUserUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static GetUserUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new GetUserUseCase_Factory(userRepositoryProvider);
  }

  public static GetUserUseCase newInstance(UserRepository userRepository) {
    return new GetUserUseCase(userRepository);
  }
}
