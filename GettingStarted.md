# Requirements & Download #
Download [this CN bundle](http://neighborhood.googlecode.com/files/cn-everything-0.00.3.bz2).

Requirements:
  * Linux, Mac OS X (Windows support coming soon)
  * JDK 1.5 or better is required
  * Python (Temporary requirement to properly construct the classpath)
  * Familiarity with the command line (GUI is planned)

Now extract the bundle using the following command:
```
tar -xvjf cn-everything-0.00.3.bz2 
```

# Running the Server #
Now we will start a CN server.  You can try CN out by running one or two servers on your local machine.  Or you can start servers on other machines on your network (they must be on the same multicast enabled subnet).

Change to the appropriate directory:
```
cd cn-everything-0.00.3/cn-server-0.00.3/
```

And start the server:
```
./runserver.sh 
```

To run a second server, open a new terminal, change directories to the same one as before and:
```
./runserver2.sh 
```

# Running the Samples #
Make sure at least one machine on your network (or your local machine) is running an instance of the server.  Change to the `cn-everything-0.00.3/cn-examples-0.00.3` directory and:
```
./runpi.sh 
```

# Issues #
  * The only the way to stop the server is with a Ctrl-C.
  * The sample jobs will not exit on completion. Stop them with Ctrl-C.
  * Sample job output is displayed in the server window, not the client window.