import arrow.core.continuations.Effect
import arrow.core.continuations.effect

data class User(val name: String, val age: Int)

fun interface FetchUserEffect {

    fun askUserInformation(): Effect<String, User>
}

context(FetchUserEffect)
fun fetchUser(id: String?): Effect<String, User> = effect {
    if (id == null) askUserInformation().bind()
    else User("Daniel", 1000)
}

fun printUser(id: String?, fetchUserEffect: FetchUserEffect): Effect<String, Unit> = effect {
    println("User: ${with(fetchUserEffect) { fetchUser(id = id).bind() }}")
}

fun computeUser(id: String?, fetchUserEffect: FetchUserEffect): Effect<String, Unit> = effect {
    printUser(id = id, fetchUserEffect).bind()
}

val fetchUserEffect: FetchUserEffect = FetchUserEffect {
    effect { User(name = "Bloder", age = 24) }
}

suspend fun main() {
    computeUser(id = null, fetchUserEffect = fetchUserEffect).fold({}, { println("computation finished!") })
}