# Current Status #
Computational Neighborhood (CN) has been kicked around in one form or another for several years.  However, it is now starting to come of age.

On a multicast enabled subnet, CN creates an ad-hoc cluster (or a 'neighborhood') of compute nodes.  The nodes need only run the CN server, a simple user-land application written in Java.  Client applications are submitted to the neighborhood for execution.  Conceptually similar to MPI programs, these client applications are divided by the programmer into tasks.  Each task is distributed to an available node, and these tasks can communicate among themselves via a messaging system in order to complete their work.

## Goals ##
My goals for CN this semester are twofold:
  1. Improvements to the existing code base.
  1. Improvements to existing messaging system & implementation of Google's MapReduce on top of CN.

### Improvements to Existing Code base ###
Several improvements must be made to the existing CN code base for it to become usable in real world applications.  First off, reliability and redundency needs to be improved. Though a neighborhood will continue to accept client applications for execution when a node fails, at the moment, applications already running on the neighborhood will not complete properly.  Actually the fix here is relatively simple with the existing code base, and this improvement is of utmost importance.

Also, the client programming interface needs to be improved.  As the client side code is much simpler (really just a set of interfaces to program against), it has largely been ignored throughout improvements to CN.  This should change; CN's primary goal is to be easy to use.  Along these same lines, the existing code should be partitioned into server, client and code samples distributions.  These distributions should not only be provided on Google Code for download, but should also be submitted to the official Maven repositories.


### Implementation of Other Messaging Schemes ###
CN is very modular, however, there is still some integration between the 'node discovery' and the 'node messaging' code. These two should be divorced completely.  If this is done, CN can still support the traditional message passing model, while implementing other distributed computing schemes on top of the node discovery code.  Reading [Google's MapReduce Paper](http://labs.google.com/papers/mapreduce.html), there are actually many similarities between their system and the existing CN code.  Implementing MapReduce on top of the CN node discovery would not be an insurmountable task and is the ultimate goal for the semester. Supporting two differing computational techniques (message passing and MapReduce) would be new, unique and (hopefully) useful.

# Other Ideas #
If anyone else wants to work on this project, just let me (Matt Bone) know.  There are many other ideas besides the ones presented here that can be worked on, too.