package IterableExt

fun<T> Iterable<T>.random(): T = this.shuffled().first()