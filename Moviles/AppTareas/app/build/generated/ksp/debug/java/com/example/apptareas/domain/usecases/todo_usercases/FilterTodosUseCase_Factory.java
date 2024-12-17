package com.example.apptareas.domain.usecases.todo_usercases;

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
public final class FilterTodosUseCase_Factory implements Factory<FilterTodosUseCase> {
  @Override
  public FilterTodosUseCase get() {
    return newInstance();
  }

  public static FilterTodosUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FilterTodosUseCase newInstance() {
    return new FilterTodosUseCase();
  }

  private static final class InstanceHolder {
    private static final FilterTodosUseCase_Factory INSTANCE = new FilterTodosUseCase_Factory();
  }
}
