package com.example.myapplication.domain.usecases;

import com.example.myapplication.data.Repository;

public class GetBooks (private val repository :Repository) {
    operator fun invoke() = repository.getBooks()
}