// 初めてのKotlin////
package sample

class Rational(val n: Int, val d: Int){

    init { // イニシャライザ
        require(d != 0, {"denominator must not be null"}) // 例外
    }

    private val g by lazy {gcd(Math.abs(n), Math.abs(d))}
    val numerator: Int by lazy{n / g}
    val denominator: Int by lazy {d / g}


    operator fun plus (that: Rational): Rational = // 演算子オーバーロード
            Rational(
            numerator * that.denominator + that.numerator * denominator,
            denominator * that.denominator
            )

    operator fun plus (n: Int): Rational = // メソッドplusのオーバーロード
            Rational(numerator + n * denominator, denominator)

    override fun toString(): String = "${numerator}/${denominator} "

    tailrec private fun gcd(a: Int, b: Int): Int =
            if (b == 0) a
            else gcd(b, a % b)
}

operator fun Int.plus(r: Rational): Rational = r + this

fun main(args: Array<String>) {
    val half = 3 + Rational(2, 2 ) + 2
    println(half)
}
