package com.example.jetpack_compose_p.map

data class DirectionsResponse(
    val routes: List<Route>? = emptyList(),
    val status: String? = "",
    val error_message: String? = ""
)

data class Route(
    val overview_polyline: OverviewPolyline? = null,
    val legs: List<Leg>? = emptyList()
)

data class OverviewPolyline(
    val points: String? = ""
)

data class Leg(
    val distance: Distance? = null,
    val duration: Duration? = null,
    val steps: List<Step>? = emptyList()
)

data class Distance(
    val text: String? = "",
    val value: Int? = 0
)

data class Duration(
    val text: String? = "",
    val value: Int? = 0
)

data class Step(
    val polyline: StepPolyline? = null
)

data class StepPolyline(
    val points: String? = ""
)