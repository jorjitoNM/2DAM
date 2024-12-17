package com.example.apptareas.ui.events_list

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class EventsListFragmentArgs(
  public val userId: Int = 1,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("userId", this.userId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("userId", this.userId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): EventsListFragmentArgs {
      bundle.setClassLoader(EventsListFragmentArgs::class.java.classLoader)
      val __userId : Int
      if (bundle.containsKey("userId")) {
        __userId = bundle.getInt("userId")
      } else {
        __userId = 1
      }
      return EventsListFragmentArgs(__userId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): EventsListFragmentArgs {
      val __userId : Int?
      if (savedStateHandle.contains("userId")) {
        __userId = savedStateHandle["userId"]
        if (__userId == null) {
          throw IllegalArgumentException("Argument \"userId\" of type integer does not support null values")
        }
      } else {
        __userId = 1
      }
      return EventsListFragmentArgs(__userId)
    }
  }
}
