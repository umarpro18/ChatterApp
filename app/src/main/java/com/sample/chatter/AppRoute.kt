package com.sample.chatter

import kotlinx.serialization.Serializable

interface AppRoute {
    @Serializable
    data object SignInRoute : AppRoute

    @Serializable
    data object SignUpRoute : AppRoute

    @Serializable
    data object HomeRoute : AppRoute

    @Serializable
    data object TidBookTimerRoute : AppRoute
}