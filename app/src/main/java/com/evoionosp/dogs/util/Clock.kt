package com.evoionosp.dogs.util

import javax.inject.Inject

interface Clock {
    fun nowEpochMs(): Long
}

class SystemClock @Inject constructor() : Clock {
    override fun nowEpochMs(): Long = System.currentTimeMillis()
}
