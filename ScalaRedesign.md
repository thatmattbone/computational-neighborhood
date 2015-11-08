# Introduction #
[Scala](http://scala-lang.org) is a relatively new, multi-paradigm
programming language that runs on the JVM and emphasizes a functional
approach.  I propose a redesign of the CN framework in Scala.

# Motivation #
Though much of the original code base has been removed, I have for
sometime felt that CN is showing its age and borders on irrelevance.
While some may argue that redesigning CN in a strange functional (not
quite) language will only decrease its relevance, Scala's complete
interoperability with Java makes this a moot point.  Let me be clear,
though the framework itself will be rewritten (or, perhaps more
accurately, refactored) in Scala, client programs will still be
written in Java, with a Scala option forthcoming.

Since Scala is a new (maybe even 'up and coming') language, we may be
able to draw some interest in the project from the Scala community.
However, Scala interest aside, CN will still be distributed as a
regular Java application and users need not know or care about the
implementation language.  Moreover, I believe that the flexibility of
Scala will allow us to adopt CN so that appeals to the wider Java
community.

# A New Hope #
Scala has lovingly stolen the [Erlang](http://erlang.org) concurrency
model.  Threads (or event based actors) are built such that they share
no global data and instead communicate via message passing only.  What
is interesting is that this is very similar to the current job/task
model we maintain in CN.  However, we do not, at the moment, 'eat our
own dog food' and practice message passing within the framework
itself.  I propose that we adopt this style for CN internals.  Much of
my time lately has been spent chasing down strange concurrency issues,
and it seems that this approach could help solve that problem.  Also,
this would promote a high degree of orthogonality; CN itself would
look like a CN job.

Though support for distributed message passing is already part of the
Scala distribution, its goals (like Erlang's goals) are much different
than ours.  More specifically, there is no 'ad-hoc' nature to the
distribution.  Instead, you start up your code on a remote machine and
specifically request messages to be sent to that particular machine.

I propose the addition of a layer underneath the existing Scala
message passing framework.  As before, jobs will run on one CN server
and request that tasks be started.  These tasks will, as always, be
distributed equitably across available nodes.  However, the tasks will
leverage the Scala messaging syntax (task ! message) to talk to other
tasks.  So while task A may message task B, task A will not need to be
aware of the location of B.  Instead, the framework will handle these
details.

As a future goal, I would like to add collective messaging to the
existing Scala messaging syntax.  This could even be implemented over
multicast.

# Implementation #
**Everything below here is still a work in progress**

I suggest switching to the shared nothing approach in the framework
itself.  Consider, for instance, the component that maintains a copy
of the neighborhood state on each node and selects the 'most available'
server for task distribution.  Instead of explicitly calling a method
in the component to update a server load or request a new server for
a task.  We would simply send the component a message.  In the case
of updating server load, we need only redirect the message from the
thread that handles multicast communications to the neighborhood state
component.

## Messages ##
Messages will be implemented with the [Scala case class mechanism](http://www.scala-lang.org/intro/caseclasses.html).
Internally these will simply be passed around from one message box to
another.  However, we can take the same message, serialize it, and
send it over the wire (broadcast even) when we need to distribute
things.  This will eliminate the dissonance between multicasting
individual packets and doing complicated RPC calls.