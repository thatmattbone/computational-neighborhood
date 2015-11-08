# Introduction #

Maven really is a good project, but it can be a bit tricky at times.  This page is meant to help developers and prospective developers get the relevant portions under control.


# Deploying to the Maven Repository #
Deploy the CN artifact to the Maven repository on Xenon:
```
mvn deploy
```

# Cutting a release #
This will tag the release in the subversion repository and create a bundle that can be uploaded to Google code (we need a maven plugin to do this for us).
```
mvn release:prepare -DdryRun=true
mvn release:prepare
```

Deploy the newly tagged release (see above):
```
mvn release:perform
```

For more information see: http://maven.apache.org/guides/mini/guide-releasing.html