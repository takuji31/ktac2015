package jp.takuji31.ktac2015

/**
 * Created by takuji on 2015/12/02.
 */
interface A {
    fun hoge()
    val prop : String
}
class AImpl() : A {
    override val prop : String
        get() = "Delegation is pretty!"
    override fun hoge() {
        println("Hello delegation!")
    }
}

class B(a : A) : A by a
class C(val a : A) : A by a {
    override fun hoge() {
        a.hoge()
        println("Override method!")
    }
}

fun main(args : Array<String>) {
    val a = AImpl()
    val b = B(a)

    b.hoge()
    println(b.prop)

    println("----")

    val c = C(a)
    c.hoge()
    println(c.prop)
}