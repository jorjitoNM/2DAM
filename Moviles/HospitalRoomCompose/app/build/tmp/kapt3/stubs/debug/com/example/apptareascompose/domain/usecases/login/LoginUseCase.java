package com.example.apptareascompose.domain.usecases.login;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/apptareascompose/domain/usecases/login/LoginUseCase;", "", "repositoryLocal", "Lcom/example/apptareascompose/data/RepositoryLocal;", "(Lcom/example/apptareascompose/data/RepositoryLocal;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Result;", "Lcom/example/primeraapp/data/local/modelo/UserEntity;", "username", "", "password", "app_debug"})
public final class LoginUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.example.apptareascompose.data.RepositoryLocal repositoryLocal = null;
    
    @javax.inject.Inject()
    public LoginUseCase(@org.jetbrains.annotations.NotNull()
    com.example.apptareascompose.data.RepositoryLocal repositoryLocal) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<com.example.primeraapp.data.local.modelo.UserEntity>> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
}