package com.example.apptareas.domain.usecases.todo_usercases;

import com.example.apptareas.data.remote.TodosRepository;
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
public final class GetUserTodosUseCase_Factory implements Factory<GetUserTodosUseCase> {
  private final Provider<TodosRepository> todosRepositoryProvider;

  public GetUserTodosUseCase_Factory(Provider<TodosRepository> todosRepositoryProvider) {
    this.todosRepositoryProvider = todosRepositoryProvider;
  }

  @Override
  public GetUserTodosUseCase get() {
    return newInstance(todosRepositoryProvider.get());
  }

  public static GetUserTodosUseCase_Factory create(
      Provider<TodosRepository> todosRepositoryProvider) {
    return new GetUserTodosUseCase_Factory(todosRepositoryProvider);
  }

  public static GetUserTodosUseCase newInstance(TodosRepository todosRepository) {
    return new GetUserTodosUseCase(todosRepository);
  }
}
