package com.example.apptareas.ui.user_profile;

import com.example.apptareas.domain.usecases.user_usercases.GetUserUseCase;
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
public final class ProfileFragmentViewModel_Factory implements Factory<ProfileFragmentViewModel> {
  private final Provider<GetUserUseCase> getUserUseCaseProvider;

  public ProfileFragmentViewModel_Factory(Provider<GetUserUseCase> getUserUseCaseProvider) {
    this.getUserUseCaseProvider = getUserUseCaseProvider;
  }

  @Override
  public ProfileFragmentViewModel get() {
    return newInstance(getUserUseCaseProvider.get());
  }

  public static ProfileFragmentViewModel_Factory create(
      Provider<GetUserUseCase> getUserUseCaseProvider) {
    return new ProfileFragmentViewModel_Factory(getUserUseCaseProvider);
  }

  public static ProfileFragmentViewModel newInstance(GetUserUseCase getUserUseCase) {
    return new ProfileFragmentViewModel(getUserUseCase);
  }
}
