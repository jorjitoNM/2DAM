package com.example.apptareas.ui.events_list;

import com.example.apptareas.domain.usecases.events_usercases.DeleteEventUseCase;
import com.example.apptareas.domain.usecases.events_usercases.FilterEventsUseCase;
import com.example.apptareas.domain.usecases.events_usercases.GetEventsUseCase;
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
public final class EventListViewModel_Factory implements Factory<EventListViewModel> {
  private final Provider<GetEventsUseCase> getEventsUseCaseUserCaseProvider;

  private final Provider<DeleteEventUseCase> deleteEventUseCaseProvider;

  private final Provider<FilterEventsUseCase> filterEventsUseCaseProvider;

  public EventListViewModel_Factory(Provider<GetEventsUseCase> getEventsUseCaseUserCaseProvider,
      Provider<DeleteEventUseCase> deleteEventUseCaseProvider,
      Provider<FilterEventsUseCase> filterEventsUseCaseProvider) {
    this.getEventsUseCaseUserCaseProvider = getEventsUseCaseUserCaseProvider;
    this.deleteEventUseCaseProvider = deleteEventUseCaseProvider;
    this.filterEventsUseCaseProvider = filterEventsUseCaseProvider;
  }

  @Override
  public EventListViewModel get() {
    return newInstance(getEventsUseCaseUserCaseProvider.get(), deleteEventUseCaseProvider.get(), filterEventsUseCaseProvider.get());
  }

  public static EventListViewModel_Factory create(
      Provider<GetEventsUseCase> getEventsUseCaseUserCaseProvider,
      Provider<DeleteEventUseCase> deleteEventUseCaseProvider,
      Provider<FilterEventsUseCase> filterEventsUseCaseProvider) {
    return new EventListViewModel_Factory(getEventsUseCaseUserCaseProvider, deleteEventUseCaseProvider, filterEventsUseCaseProvider);
  }

  public static EventListViewModel newInstance(GetEventsUseCase getEventsUseCaseUserCase,
      DeleteEventUseCase deleteEventUseCase, FilterEventsUseCase filterEventsUseCase) {
    return new EventListViewModel(getEventsUseCaseUserCase, deleteEventUseCase, filterEventsUseCase);
  }
}
