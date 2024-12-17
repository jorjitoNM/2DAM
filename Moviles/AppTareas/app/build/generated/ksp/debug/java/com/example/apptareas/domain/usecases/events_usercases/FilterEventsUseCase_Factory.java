package com.example.apptareas.domain.usecases.events_usercases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class FilterEventsUseCase_Factory implements Factory<FilterEventsUseCase> {
  @Override
  public FilterEventsUseCase get() {
    return newInstance();
  }

  public static FilterEventsUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FilterEventsUseCase newInstance() {
    return new FilterEventsUseCase();
  }

  private static final class InstanceHolder {
    private static final FilterEventsUseCase_Factory INSTANCE = new FilterEventsUseCase_Factory();
  }
}
