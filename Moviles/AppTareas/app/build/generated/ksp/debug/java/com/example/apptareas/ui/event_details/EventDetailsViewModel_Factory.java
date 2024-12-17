package com.example.apptareas.ui.event_details;

import com.example.apptareas.domain.usecases.events_usercases.GetEventUseCase;
import com.example.apptareas.domain.usecases.events_usercases.UpdateEventUseCase;
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
public final class EventDetailsViewModel_Factory implements Factory<EventDetailsViewModel> {
  private final Provider<UpdateEventUseCase> updateEventUseCaseProvider;

  private final Provider<GetEventUseCase> getEventUseCaseProvider;

  public EventDetailsViewModel_Factory(Provider<UpdateEventUseCase> updateEventUseCaseProvider,
      Provider<GetEventUseCase> getEventUseCaseProvider) {
    this.updateEventUseCaseProvider = updateEventUseCaseProvider;
    this.getEventUseCaseProvider = getEventUseCaseProvider;
  }

  @Override
  public EventDetailsViewModel get() {
    return newInstance(updateEventUseCaseProvider.get(), getEventUseCaseProvider.get());
  }

  public static EventDetailsViewModel_Factory create(
      Provider<UpdateEventUseCase> updateEventUseCaseProvider,
      Provider<GetEventUseCase> getEventUseCaseProvider) {
    return new EventDetailsViewModel_Factory(updateEventUseCaseProvider, getEventUseCaseProvider);
  }

  public static EventDetailsViewModel newInstance(UpdateEventUseCase updateEventUseCase,
      GetEventUseCase getEventUseCase) {
    return new EventDetailsViewModel(updateEventUseCase, getEventUseCase);
  }
}
