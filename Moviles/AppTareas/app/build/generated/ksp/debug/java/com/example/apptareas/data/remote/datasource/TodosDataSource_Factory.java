package com.example.apptareas.data.remote.datasource;

import com.example.apptareas.data.remote.api_service.TodosService;
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
public final class TodosDataSource_Factory implements Factory<TodosDataSource> {
  private final Provider<TodosService> todosServiceProvider;

  public TodosDataSource_Factory(Provider<TodosService> todosServiceProvider) {
    this.todosServiceProvider = todosServiceProvider;
  }

  @Override
  public TodosDataSource get() {
    return newInstance(todosServiceProvider.get());
  }

  public static TodosDataSource_Factory create(Provider<TodosService> todosServiceProvider) {
    return new TodosDataSource_Factory(todosServiceProvider);
  }

  public static TodosDataSource newInstance(TodosService todosService) {
    return new TodosDataSource(todosService);
  }
}
