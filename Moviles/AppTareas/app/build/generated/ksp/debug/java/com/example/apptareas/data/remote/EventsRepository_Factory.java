package com.example.apptareas.data.remote;

import com.example.apptareas.data.remote.datasource.EventsDataSource;
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
public final class EventsRepository_Factory implements Factory<EventsRepository> {
  private final Provider<EventsDataSource> eventsDataSourceProvider;

  public EventsRepository_Factory(Provider<EventsDataSource> eventsDataSourceProvider) {
    this.eventsDataSourceProvider = eventsDataSourceProvider;
  }

  @Override
  public EventsRepository get() {
    return newInstance(eventsDataSourceProvider.get());
  }

  public static EventsRepository_Factory create(
      Provider<EventsDataSource> eventsDataSourceProvider) {
    return new EventsRepository_Factory(eventsDataSourceProvider);
  }

  public static EventsRepository newInstance(EventsDataSource eventsDataSource) {
    return new EventsRepository(eventsDataSource);
  }
}
