**Post Office App**
Install [gradle](https://gradle.org/install/) and make sure you have JDK installed on box.

*Tasks*

 1. Compile App
```sh
gradle clean compileJava
```
	
 2. Run Unit Tests
```sh
gradle clean test
```
This will run all Unit tests belong to this appication.

If you are linux distribution , 

Use ```./gradlew```  wrapper instead of ```gradlew.bat```