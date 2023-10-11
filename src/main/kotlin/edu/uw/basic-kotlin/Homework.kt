package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFN(arg: Any): String {
    return when (arg) {
        "Hello" -> "world"
        0 -> "zero"
        1 -> "one"
        is Int -> {
            if (arg in 2..10) {
                "low number"
            } else {
                "a number"
            }
        }
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values

fun add(int1: Int, int2: Int): Int {
    return int1 + int2;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values

fun sub(int1: Int, int2: Int): Int {
    return int1 - int2;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments

fun mathOP(int1: Int, int2: Int, op: MathOperation): Int {
    return op(int1, int2)
}

// write a class "Person" with first name, last name and age

class Person(val firstName: String, val lastName: String, var age: Int) {
    val debugString: String = "[Person firstname:$firstName lastname:$lastName age:$age]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator

class Money(private var amount: Int, private var currency: String) {

    init {
        require(amount >= 0) { "Amount cannot be less than zero" }
        require(currency in setOf("USD", "EUR", "CAN", "GBP")) { "Invalid currency" }
    }

    fun convert(targetCurrency: String): Money {
        val convertedAmount = when (currency) {
            "USD" -> when (targetCurrency) {
                "GBP" -> amount / 2
                "EUR" -> amount * 3 / 2
                "CAN" -> amount * 5 / 4
                else -> amount
            }
            "GBP" -> when (targetCurrency) {
                "USD" -> amount * 2
                "EUR" -> amount * 3
                "CAN" -> amount * 5 / 2
                else -> amount
            }
            "EUR" -> when (targetCurrency) {
                "USD" -> amount * 2 / 3
                "GBP" -> amount / 3
                "CAN" -> amount * 5 / 6
                else -> amount
            }
            "CAN" -> when (targetCurrency) {
                "USD" -> amount * 4 / 5
                "GBP" -> amount * 2 / 5
                "EUR" -> amount * 6 / 5
                else -> amount
            }
            else -> amount
        }
        return Money(convertedAmount, targetCurrency)
    }

    operator fun plus(other: Money): Money {
        if (currency == other.currency) {
            return Money(amount + other.amount, currency)
        } else {
            val convertedAmount = when (currency) {
                "USD" -> other.convert(currency).amount + amount
                else -> this.convert(other.currency).amount + other.amount
            }
            return Money(convertedAmount, currency)
        }
    }

    override fun toString(): String {
        return "$amount $currency"
    }
}