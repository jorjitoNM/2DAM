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
public final class UpdateEventUseCase_Factory implements Factory<UpdateEventUseCase> {
  private final Provider<EventsRepository> eventsRepositoryProvider;

  public UpdateEventUseCase_Factory(Provider<EventsRepository> eventsRepositoryProvider) {
    this.eventsRepositoryProvider = eventsRepositoryProvider;
  }

  @Override
  public UpdateEventUseCase get() {
    return newInstance(eventsRepositoryProvider.get());
  }

  public static UpdateEventUseCase_Factory create(
      Provider<EventsRepository> eventsRepositoryProvider) {
    return new UpdateEventUseCase_Factory(eventsRepositoryProvider);
  }

  public static UpdateEventUseCase newInstance(EventsRepository eventsRepository) {
    return new UpdateEventUseCase(eventsRepository);
  }
}
