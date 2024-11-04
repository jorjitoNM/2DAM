package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import javax.inject.Inject

class GetID @Inject constructor() {
    operator fun invoke () : Int {
        return Repository.getId()
    }
}