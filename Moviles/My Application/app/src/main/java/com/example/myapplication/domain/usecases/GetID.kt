package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository

class GetID() {
    operator fun invoke () : Int {
        return Repository.getId()
    }
}