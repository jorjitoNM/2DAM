package com.example.apptareas.data.remote;

import com.example.apptareas.data.remote.datasource.TodosDataSource;
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
public final class TodosRepository_Factory implements Factory<TodosRepository> {
  private final Provider<TodosDataSource> todosDataSourceProvider;

  public TodosRepository_Factory(Provider<TodosDataSource> todosDataSourceProvider) {
    this.todosDataSourceProvider = todosDataSourceProvider;
  }

  @Override
  public TodosRepository get() {
    return newInstance(todosDataSourceProvider.get());
  }

  public static TodosRepository_Factory create(Provider<TodosDataSource> todosDataSourceProvider) {
    return new TodosRepository_Factory(todosDataSourceProvider);
  }

  public static TodosRepository newInstance(TodosDataSource todosDataSource) {
    return new TodosRepository(todosDataSource);
  }
}
