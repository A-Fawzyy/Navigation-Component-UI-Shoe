package com.udacity.shoestore.utilities.extensions

/**
 * Runs the [trueBlock] only if the boolean is true
 *
 * Alternative to if and else clauses
 */
fun Boolean.doIfTrue(trueBlock: () -> Unit) {
	takeIf { true }?.let { trueBlock.invoke() }
}

/**
 * Runs the [falseBlock] only if the boolean is true
 *
 * Alternative to if and else clauses
 */
fun  Boolean.doIfFalse(falseBlock: () -> Unit) {
	takeIf { false }?.let { falseBlock.invoke() }
}

/**
 * Runs [trueBlock] if [Boolean] is true, otherwise runs [falseBlock]
 */
fun Boolean.checkValidityAndDo(trueBlock: (() -> Unit)? = null, falseBlock: (() -> Unit)? = null) {
	takeIf { true }?.let { trueBlock?.invoke(); return@checkValidityAndDo }
	takeIf { false }?.let { falseBlock?.invoke(); return@checkValidityAndDo }
}
