<p align="center">
  <img width="50%" height="100%" src="https://files.readme.io/24c4101-bunnynet-logo-dark-small.png">
  <br>
  <a href="https://github.com/terminalsin/bunnycdn-storage-java/issues"><img alt="GitHub issues" src="https://img.shields.io/github/issues/terminalsin/bunnycdn-storage-java"></a>
  <a href="https://github.com/terminalsin/bunnycdn-storage-java/network"><img alt="GitHub forks" src="https://img.shields.io/github/forks/terminalsin/bunnycdn-storage-java"></a>
  <a href="https://github.com/terminalsin/bunnycdn-storage-java/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/terminalsin/bunnycdn-storage-java"></a>
  <a href="https://github.com/terminalsin/bunnycdn-storage-java"><img alt="GitHub license" src="https://img.shields.io/github/license/terminalsin/bunnycdn-storage-java"></a>
</p>
 

## What is BunnyCDN Edge Storage? 
Edge Storage is a cloud storage solution provided by bunny.net that automatically replicates your data to multiple regions around the world. It integrates tightly with the bunny.net CDN and was designed to be the fastest performing global storage solution thanks to smart geographical load balancing.
## How to use the API?
It's very simple, here's an example (SandboxStoreTest.java)

```java
private static final String DUMMY_TEST_KEY = "344c04e4-9f81-4496-b63a-d79f6ac21e46";

public void order1Test() {
    final StorageAPI bunny = new BunnyJavaClient(DUMMY_TEST_KEY).getStorageAPI(null);
    bunny.uploadFile("testZone", "test/path/cool", "testfile.jar", new File("mytest.jar"));
    bunny.downloadFile("testZone", "test/path/cool", "testfile.jar");
    bunny.deleteFile("testZone", "test/path/cool", "testfile.jar");
}
```

## How to download?
With maven:
```xml
<!-- Step one, add this -->
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>


<!-- Step 2, add this in your dependencies tab -->
<dependency>
    <groupId>com.github.terminalsin</groupId>
    <artifactId>bunnycdn-storage-java</artifactId>
    <version>1.0.2</version>
</dependency>
```

With gradle:

```javascript
// Add this first
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

// Then this


dependencies {
    implementation 'com.github.terminalsin:bunnycdn-storage-java:1.0.2'
}
```

## Dependencies
None :) (Okay fine we use JUnit for testing but shhh)

## Sponsors
![Your kit](https://www.yourkit.com/images/yklogo.png)

YourKit supports open source projects with innovative and intelligent tools
for monitoring and profiling Java and .NET applications.
YourKit is the creator of <a href="https://www.yourkit.com/java/profiler/">YourKit Java Profiler</a>,
<a href="https://www.yourkit.com/.net/profiler/">YourKit .NET Profiler</a>,
and <a href="https://www.yourkit.com/youmonitor/">YourKit YouMonitor</a>.

## Contact
Issues tab. Won't be offering direct support.
