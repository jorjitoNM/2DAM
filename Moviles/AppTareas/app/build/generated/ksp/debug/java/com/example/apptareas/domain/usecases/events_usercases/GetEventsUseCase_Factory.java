package com.example.apptareas.domain.usecases.events_usercases;

import com.example.apptareas.data.remote.EventsRepository;
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
public final class GetEventsUseCase_Factory implements Factory<GetEventsUseCase> {
  private final Provider<EventsRepository> eventsRepositoryProvider;

  public GetEventsUseCase_Factory(Provider<EventsRepository> eventsRepositoryProvider) {
    this.eventsRepositoryProvider = eventsRepositoryProvider;
  }

  @Override
  public GetEventsUseCase get() {
    return newInstance(eventsRepositoryProvider.get());
  }

  public static GetEventsUseCase_Factory create(
      Provider<EventsRepository> eventsRepositoryProvider) {
    return new GetEventsUseCase_Factory(eventsRepositoryProvider);
  }

  public static GetEventsUseCase newInstance(EventsRepository eventsRepository) {
    return new GetEventsUseCase(eventsRepository);
  }
}
