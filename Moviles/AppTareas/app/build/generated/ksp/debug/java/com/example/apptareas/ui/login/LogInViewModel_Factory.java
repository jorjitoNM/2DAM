package com.example.apptareas.ui.login;

import com.example.apptareas.domain.usecases.user_usercases.LogInUseCase;
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
public final class LogInViewModel_Factory implements Factory<LogInViewModel> {
  private final Provider<LogInUseCase> logInUseCaseProvider;

  public LogInViewModel_Factory(Provider<LogInUseCase> logInUseCaseProvider) {
    this.logInUseCaseProvider = logInUseCaseProvider;
  }

  @Override
  public LogInViewModel get() {
    return newInstance(logInUseCaseProvider.get());
  }

  public static LogInViewModel_Factory create(Provider<LogInUseCase> logInUseCaseProvider) {
    return new LogInViewModel_Factory(logInUseCaseProvider);
  }

  public static LogInViewModel newInstance(LogInUseCase logInUseCase) {
    return new LogInViewModel(logInUseCase);
  }
}
