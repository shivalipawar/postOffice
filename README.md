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

**Thought Process**

- This solution is driven from test.  

- In app we need to populate PostOffices, also User create a Postcard for offline delivery.

- PostOffices serve couple of province/area and Post Service finds appropriate destination Post Office and if not found throws exception.
 
- Post Service also identifies eligibility of post being sent with valid stamp if not exception is thrown.

- Eligible post cards are then sent to destination address.
 
