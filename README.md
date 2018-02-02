Automate everything involving MarkLogic with Gradle
=========

ml-gradle is a [Gradle](https://gradle.org/) [plugin](https://docs.gradle.org/current/userguide/plugins.html) that automates deploying an 
application to [MarkLogic](https://www.marklogic.com/) along with any other MarkLogic-specific task you can think of.

You can start using ml-gradle right away with the brief tutorial below, or learn more:

- The [ml-gradle Wiki](https://github.com/marklogic-community/ml-gradle/wiki) links to all of the ml-gradle documentation
- Read the [Getting Started guide](https://github.com/marklogic-community/ml-gradle/wiki/Getting-started) for more details on setting up a new project
- Browse the [example projects](https://github.com/marklogic-community/ml-gradle/tree/master/examples)

Start using ml-gradle
=========

Assuming you have a [JVM](https://java.com/en/download/) and [MarkLogic 8 or 9](https://developer.marklogic.com/products) 
installed, you're just a couple minutes away from using ml-gradle to stub out a new application and deploy it. 

First, [install Gradle](https://gradle.org/install/).

Then, in an empty directory, create a file named "build.gradle" with your favorite text editor and enter the following:

    plugins { id "com.marklogic.ml-gradle" version "3.4.0" }
    
Then run:

    gradle mlNew

This starts a project wizard to stub out files for your new application. You can accept all the defaults, but be sure to
enter a valid port number for the "REST API port" question. ml-gradle will then print the following logging:

    Updating build.gradle so that the Gradle properties plugin can be applied
    Writing: build.gradle
    Writing: gradle.properties
    Writing: gradle-dev.properties
    Writing: gradle-local.properties
    Writing: gradle-qa.properties
    Writing: gradle-prod.properties
    Making directory: src/main/ml-config
    Making directory: src/main/ml-modules
    Writing project scaffolding files

You now have an ml-gradle project stubbed out with support for deploying to multiple environments via the 
[Gradle properties plugin](https://github.com/stevesaliman/gradle-properties-plugin). 

Now deploy it!

    gradle mlDeploy
    
And you should see more ml-gradle logging like this:

    :mlDeleteModuleTimestampsFile
    :mlPrepareRestApiDependencies
    :mlDeployApp
    :mlPostDeploy UP-TO-DATE
    :mlDeploy
    BUILD SUCCESSFUL

And once that's complete, you can go to the MarkLogic Admin UI on port 8001 to see the resources that have been created 
(the names of these resources start with the application name you selected in the project wizard, which defaults to myApp):

- Under App Servers, a new REST server named myApp on the port you chose
- Under Databases, a new content datase named myApp-content and a new modules database named myApp-modules
- Under Forests, 3 new forests for myApp-content and 1 new forest for myApp-modules
- Under Security/Users, 3 new users, each prefixed with myApp
- Under Security/Roles, 5 new roles, each prefixed with myApp

Congratulations! You've used ml-gradle to stub out a new project and deploy its application to MarkLogic. You're now 
ready to start adding more resources and modules to your project. See the links above this tutorial to learn
more about using ml-gradle. 
