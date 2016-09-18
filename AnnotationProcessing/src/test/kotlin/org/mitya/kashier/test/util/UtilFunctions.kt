package org.mitya.kashier.test.util

internal inline fun <T> T.apply(block: (T) -> Unit): T {
    block(this)
    return this
}