package com.example.apptareas.data.remote.datasource;

import com.example.apptareas.data.remote.api_service.EventsService;
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
public final class EventsDataSource_Factory implements Factory<EventsDataSource> {
  private final Provider<EventsService> eventsServiceProvider;

  public EventsDataSource_Factory(Provider<EventsService> eventsServiceProvider) {
    this.eventsServiceProvider = eventsServiceProvider;
  }

  @Override
  public EventsDataSource get() {
    return newInstance(eventsServiceProvider.get());
  }

  public static EventsDataSource_Factory create(Provider<EventsService> eventsServiceProvider) {
    return new EventsDataSource_Factory(eventsServiceProvider);
  }

  public static EventsDataSource newInstance(EventsService eventsService) {
    return new EventsDataSource(eventsService);
  }
}
