package com.pedro.doggos.core.presentation

interface UIState {
    object LoadingState : UIState
    class ErrorState(val message: String, val resourceID: Int? = null) : UIState
}