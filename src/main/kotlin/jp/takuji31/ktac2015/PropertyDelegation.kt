package jp.takuji31.ktac2015

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.properties.getValue
import kotlin.properties.setValue
import kotlin.reflect.KProperty

/**
 * Created by takuji on 2015/12/02.
 */
class DelegatedClass() {
    var nonNullString : String by Delegates.notNull()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val mode = args[0]

            val delegatedClass = DelegatedClass()
            if (mode == "null") {
                println(delegatedClass.nonNullString)
            } else if (mode == "notNull") {
                delegatedClass.nonNullString = "this is not null!!"
                println(delegatedClass.nonNullString)
            }
        }
    }
}

class MapVarProperty(val map : MutableMap<String, String>, val key : String? = null) : ReadWriteProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        val k = key ?: property.name
        return map[k]!!
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        val k = key ?: property.name
        map[k] = value
    }
}

class MapModel(val map : MutableMap<String, String>) {
    var hoge : String by MapVarProperty(map)
    companion object {
        @JvmStatic
fun main(args: Array<String>) {
    val mode = args[0]

    val mapModel = MapModel(hashMapOf())

    if (mode == "null") {
        println(mapModel.hoge)
    } else if (mode == "notNull") {
        mapModel.hoge = "This map key is not null!!!!"
        println(mapModel.hoge)
    }
}
    }
}

class MapByModel(val map : MutableMap<String, String>) {
    var hoge : String by map

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val mode = args[0]

            val mapBy = MapByModel(hashMapOf())

            if (mode == "null") {
                println(mapBy.hoge)
            } else if (mode == "notNull") {
                mapBy.hoge = "This map key is not null!!!!"
                println(mapBy.hoge)
            }
        }
    }
}

