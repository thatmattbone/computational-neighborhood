# Introduction #
## About this document ##
This guide is intended for this interested in working on Computational Neighborhood itself.  If you are looking to write an application using the framework, please see [WritingCNApplications](WritingCNApplications.md).

## Overview ##
CN is built with the [Maven project management software](http://maven.apache.org/) and active development is done with the [eclipse IDE](http://eclipse.org).  This guide is written from that perspective, however, if you'd like to use other tools, please feel free to do so.

# Getting Started #
## Getting the code and dependencies ##
  1. Download and install Eclipse
  1. Download and install Maven
  1. Install the [Maven plugin for eclipse](http://maven.apache.org/eclipse-plugin.html)
    1. Help-->Software Updates-->Find and Install-->Search for new features to install
    1. Add 'New Remote Site' with Name 'Maven' and URL: http://m2eclipse.codehaus.org/
    1. Finish the Installation, install the plugin and restart Eclipse
  1. Checkout the CN Project from Google code