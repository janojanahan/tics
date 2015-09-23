# tics - Threadsafe Immutable Collections.
(documentation is work in progress)
## Introduction
The purpose of this project is to create a collections library inspired by the function of the Scala immutable collections. It is designed to bring some of the idioms of that library to Plain Java, as well as any other JVM language such as Groovy.

It differs from other immutable libraries by allowing modification, however, modifications return a new instance of the collection with the modifications. Therefore the instance itself is immutable and will not change (not too disimilar to the way Strings work), inheritantly making it threadsafe. It does NOT implement the JDK Collection interface, however it does implement Iterable, and by contract, instances should be able to be created from any collection implementing Iterable (including JDK collections).

It requires at least Java 1.8 (language and API).

## History
TICS was born from my engineering experience in Big Data and Threadsafe development projects where I was constantly in need of a "copy on modify" type collection library to help solve certain problems. Having used ML and other languages with a LISP like list functionality and patterns (head/tail, copy on write, etc) being used to solve some of the problems inherent in using recursion to process lists where it would be highly undesirable to , I began to wonder if the "copy on write" model can also be used to solve similar problems that appear on multi-threaded systems, as well as functional basied projects, to prevent unintended modification of collections, such as different threads modifying another thread's view on a collection, whilst still allowing for the collection to be manipulated.

It appears the developers of the Scala language and API agree, for their collections (immutable versions) use similar lisp like patterns and idioms. It makes it's use on recursive functions simple and clean, whilst preserving safety. In addition it can be used in closures with the assurance that the instance provided cannot change.

Not everyone is willing to work in Scala just yet, and nor is Scala's collections compatible with Java code (requiring expensive conversions back and forth from scala to JDK collections whenever program execution switches between the two languages. Tics intends to provide a suitable and performant threadsafe and immutable collections library for Java and other JDK languages whilst also providing a good enough implementation for JDK Languages that do have such collections, but which are not compatible directly with other languages (such as Scala)


