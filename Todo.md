# Todo #
## Pending Shorterm ##
  * Write some basic usage documentation
  * Convert the n-body job
  * Larger scale test on the cluster

## Pending Longterm ##
  * Simplify message passing
  * Sample program in Scala
  * Working webapp
  * More sample apps

## Completed ##
  * Write installation docs
  * Switch to maven?
  * Switch `Hashtable` instances to `HashMaps` or the concurrent map implementation in the java.util.concurrent package-- probably a lot of unnecessary synchronization going on
  * Create an interface for `UnicastManager` so that we can switch between implementations
  * Finish fixing inter-package dependencies so there is a clean break between the server code and the client code
  * Switch to latest version of xmlrpc (only switched to xmlrpc2, not 3)
  * Move xmlrpc dependencies from the `multicast`, `taskmanager`, and `workmanager` classes all into the `UnicastManager` facade so that we have a single point to change the unicast implementation if we choose to do so later.