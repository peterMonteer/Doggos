package com.pedro.doggos.core.presentation

interface UIState {
    object LoadingState : UIState
    class ErrorState(val message: String?) : UIState
}