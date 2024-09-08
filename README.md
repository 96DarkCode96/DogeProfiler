# DogeProfiler for Java

## 📖 Features

* Manually report errors
* Automatically reports unhandled errors
* Add custom meta-data to error-report
* Custom metrics (timings-implemented)
* Endpoint customization
* Sender customization

## 🏃‍♂️ Getting started

You can use this snippet for quick startup.

```java
public static void main(String[] args) {
    // Create DogeProfiler instance with your API_TOKEN
    DogeProfiler dogeProfiler = new DogeProfiler("YOUR_API_TOKEN");

    // Associate packages to your project (used for better filtering)
    dogeProfiler.getConfig().addProjectPackage("your.project");

    // Highly suggested to set this. It's useful for debugging.
    dogeProfiler.getConfig().setAppVersion("1.0.0");
}
```

You can manually report the error using this snippet:

```java
import eu.darkcode.dogeprofiler.DogeProfiler;

private static final DogeProfiler dogeProfiler = ...;

public static void someMethod() {
    try {
        ...
        throw new RuntimeException("Some error");
        ...
    } catch (Exception exception) {
        dogeProfiler.notify(exception);
        // or
        dogeProfiler.notify(exception, report -> {
            // here you can edit report (add metadata etc...)
            report.addMetaData("test", true);
        });
    }
}
```