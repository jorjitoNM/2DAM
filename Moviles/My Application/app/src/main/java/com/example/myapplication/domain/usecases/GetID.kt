package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository

class GetID {
    operator fun invoke (name : String, author : String) : Int {
        return Repository.getId(name,author)
    }
}