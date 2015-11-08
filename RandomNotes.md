# Notes #

Distributed Hashtables:
  * Could be used to locate nodes for messaging
  * Many (all?) aren't using IP multicast.  Can we use IP multicast to speed things up?  Do we modify an existing strategy or create a new one?
  * Can load balancing be implemented on top of a DHT?
  * http://en.wikipedia.org/wiki/Distributed_hash_table
  * http://en.wikipedia.org/wiki/Chord_project

# Research Notes #
Notes on papers/books/journals that could be useful to us. (We need sinciput)


---


## Load balancing in homogeneous broadcast distributed systems ##
  * http://portal.acm.org/citation.cfm?id=1010631.801689

Though this papers is quite old (older than me), it does have value.  Although CN does, in fact, support heterogeneous nodes, we can, at some level consider them to be homogeneous since all run the JVM.

We have to ask ourselves on what kind of networks CN will run.  If we're keeping everything on the same multicast enabled subnet, we can, as the paper states, consider all the nodes to be a "uniform distance" apart, and we can use multicast to broadcast server state.  This paper is built upon these assumptions.

### State Broadcast Algorithm ###
This is the current approach of CN.  Whenever a node starts a job or a task, it modifies its current load and multicasts this load to the neighborhood.  Each and every node maintains "a global and updated picture of the system state".

This approach is not quite perfect, though.  When a node is distributing a task or job, it uses it's local snapshot of the neighborhood, finds the least busy server, and selects this server to distribute the task to.  What do we do with this server in between the time we start the task or job and the time we receive the newly updated server state.  This is particularly important in two cases:
  1. Degenerate case: Only one server in the neighborhood.
  1. More than one node in the neighborhood distributing tasks.  Since the select and start job on the server are not atomic, we have the chance for stale data.

Furthermore, as the paper suggests, their may be an upper limit on the
number of nodes in the neighborhood using this approach.  Though this
is not a problem yet, we should keep this problem in the back of our
minds.  Scaling up should ultimately be a goal of ours.

### Broadcast Idle Algorithm ###
In this approach the nodes broadcast a message when they are idle.
Unfortunately, this done not seem very well suited to CN as we aren't
so much concerned with idle nodes as busy ones.  I can certainly
forsee situations in large computations where no nodes would ever be
idle during the duration of a job.  Though we could 'redefine' idle
for multiprocessor machines, I still do not think this is the best
approach.  However, we could always perform some testing as this
algorithm would not be difficult to implement in CN.

### Poll When Idle Algorithm ###
Here, the nodes request more work when their are idle.  Again we have
the same problem that we had with the broadcast idle algorithm.  What
does 'idle' mean in CN?  Probably not much.


---


## Functional Pearls: Pickler Combinators ##
  * http://research.microsoft.com/~akenn/fun/

This article could be useful in understanding the Scala library outlined [here](http://scala.sygneca.com/libs/io).  If we do pursue a Scala version of the CN backend, these picklers will be quite useful for sending messages back and forth.

## Toward Scalable Parallel Software An Active Object Model and Library to Support von Neumann Languages ##
  * by George Thiruvathukal
  * http://www.etl.luc.edu/gkt/papers/enhanced-actors/enhanced_actors_hipc94.pdf

## Concurrent Object Oriented Programming ##
  * by Gul Agha
  * http://portal.acm.org/citation.cfm?id=84528&coll=portal&dl=ACM

## Actors: A Model of Concurrent Computation in Distributed Systems ##
  * by Gul Agha

Sending a message (Agha calls it a task) means that we have to know where we're sending it.  Right now this is handled in CN by just going to job server and using it to forward the task to its destination, however, we could do this in the future by some distributed hash table technique like chord. (pg 22)

"the unprocessed tasks in a system of actors are the driving force behind computation in the system" (pg 22)

Code is executed in response the to the receipt of a task (pg 21).  where is this code kept?  In the task itself, or is the task an id of sorts for a particular chunk of code?

"all computation in an actor system is the result of processing communications" (pg 23)

The behavior of an actor "is a function of the communication accepted" (pg 24)...does this mean we define multiple behaviors for an actor...each one 'activated' by a communication?

"No assumption should be made about an actor machine being sequential" (pg 24), but would it be a good idea to keep it sequential...if there is a real need for concurrency inside an actor, then should it become more than one actor?
