# tics
Threadsafe Immutable Collections.

The purpose of this project is to create a collections library inspired by the function of the Scala immutable collections. It is designed to bring some of the idioms of that library to Plain Java, as well as any other JVM language such as Groovy.

It differs from other immutable libraries by allowing modification, however, modifications return a new instance of the collection with the modifications. Therefore the instance itself is immutable and will not change (not too disimilar to the way Strings work), inheritantly making it threadsafe. It does NOT implement the JDK Collection interface, however it does implement Iterable, and by contract, instances should be able to be created from any collection implementing Iterable (including JDK collections).

It requires at least Java 1.8 (language and API)

History

