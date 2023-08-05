# Mini date and time multiplatform parser

This is a Kotlin multiplatform library that uses [Parsus](https://github.com/alllex/parsus) framework to parse date and time strings.
It is intentionally very limited for educational purposes.

It supports only two date and time formats:

```kotlin
fun main() {
    fun parse(s: String) = MiniDateTimeGrammar().parseOrThrow(s)
    println(parse("09/01/2007 9:42 am"))
    println(parse("2077-12-10 5:25"))
}
```
