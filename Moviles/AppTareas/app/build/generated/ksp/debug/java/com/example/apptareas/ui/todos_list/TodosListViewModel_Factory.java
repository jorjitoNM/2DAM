package com.example.apptareas.ui.todos_list;

import com.example.apptareas.domain.usecases.todo_usercases.FilterTodosUseCase;
import com.example.apptareas.domain.usecases.todo_usercases.GetUserTodosUseCase;
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
public final class TodosListViewModel_Factory implements Factory<TodosListViewModel> {
  private final Provider<GetUserTodosUseCase> getUserTodosProvider;

  private final Provider<FilterTodosUseCase> filterTodosUseCaseProvider;

  public TodosListViewModel_Factory(Provider<GetUserTodosUseCase> getUserTodosProvider,
      Provider<FilterTodosUseCase> filterTodosUseCaseProvider) {
    this.getUserTodosProvider = getUserTodosProvider;
    this.filterTodosUseCaseProvider = filterTodosUseCaseProvider;
  }

  @Override
  public TodosListViewModel get() {
    return newInstance(getUserTodosProvider.get(), filterTodosUseCaseProvider.get());
  }

  public static TodosListViewModel_Factory create(
      Provider<GetUserTodosUseCase> getUserTodosProvider,
      Provider<FilterTodosUseCase> filterTodosUseCaseProvider) {
    return new TodosListViewModel_Factory(getUserTodosProvider, filterTodosUseCaseProvider);
  }

  public static TodosListViewModel newInstance(GetUserTodosUseCase getUserTodos,
      FilterTodosUseCase filterTodosUseCase) {
    return new TodosListViewModel(getUserTodos, filterTodosUseCase);
  }
}
