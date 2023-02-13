package com.example.nycschools.utils

sealed class ViewIntent {
    object SCHOOLS : ViewIntent()
    object SAT_SCORES : ViewIntent()
    data class SCHOOL_SCORE(val dbn: String) : ViewIntent()
}
