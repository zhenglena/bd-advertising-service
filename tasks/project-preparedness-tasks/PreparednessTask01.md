## Project Preparedness Task 1: Use the Source, Again, and Again, and Again

### Milestone 1: Setup your workspace

**Reviewer: None**

Run `toolbox update` in your terminal to ensure your command line tools are up to date before continuing.
Create a local Brazil workspace using the `ATACurriculum-C2020July/AdvertisingService-<alias>` version set. 
Checkout the `ATACurriculumAdvertisingService` package in this new workspace.
In your new workspace, you will need to run `brazil setup platform-support`. It will tell you that it is configuring the 
workspace to use 'overlay' mode, which requires cleaning the workspace and ask if you want to proceed. Enter `y`. We 
haven't built anything yet so there is nothing to clean! This has completed correctly if you see the message below.

```
The workspace is configured to use 'overlay' mode.

You must now re-build all your packages, which can also be done via running:

    brazil-recursive-cmd --allPackages brazil-build`
```

You don't need to rebuild anything now (don't run `brazil-recursive-cmd`), because we are about to build it all for the 
first time!
Now inside your `ATACurriculumAdvertisingService` Brazil package, run `rde stack provision` to complete setting up your 
workspace. You should now also have the package `ATACurriculumAdvertisingServiceTests` in your workspace.

To verify everything is working correctly let's go ahead and run the default RDE workflow, `rde wflow run`. This 
workflow will build the code and start your service (this does NOT run any TCTs). The first time it runs, this process 
may take a while - up to 20 minutes. The workflow should end with the line below (timestamp may vary, that's okay :) ).

```
[13:43:48-I] Finish running workflow [OK]`
```

Unfortunately for all RDE workflows run in this project you will see the warning message below. This is expected and 
you do not need to take any action.

```
If you are exposing Java debug ports from your RDE application(s) please ask for an exception to avoid getting SEV2'd by InfoSec:
https://sim.amazon.com/issues/ASOC-CM-PUB-2`
```


Finally, similar to your previous projects, you'll be making calls to operations via `curl`.

This project's framework requires you to include a couple of headers with the curl command.
In previous units, the Coral Explorer actually added these headers to our requests for us!

Coral requires the 'Content-Type' and 'Content-Encoding' headers.
The `Amz-Target` header indicates which API you want to call.
We've included some templates and examples in the [README](../../README.md) of this package that you can 
reference when you're testing your code.

Make sure your service is up by calling the `GenerateAdvertisement` operation with the following `curl` command.
The first time you call, the response may take a minute or two.

```
curl -X GET -sS 'http://localhost:1186/advertisement/1?customerId=10' -H 'Content-Encoding: amz-1.0' -H 'Content-Type: application/x-amz-json-1.1' -H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.GenerateAdvertisement'
```

You should see a response that looks like the line below. The values of content and id may vary. 

```
{"advertisement":{"content":"¡Obtenga una carrera como Ingeniero de Software! Unase al programa de Amazon Technical Academy.","id":"32377ea7-9640-40fe-847f-a31547ebe05e"}}
```

If your `rde wflow run` fails or you receive an error when running the `curl` command above,
please open a CQA with what you think might be the problem, and come to office hours if necessary.


### Milestone 2: Setup your IntelliJ project

**Reminder:** Be sure to complete the previous milestone and build your packages **before** setting up your IntelliJ 
project.

Open your new workspace in IntelliJ. Don't forget you can reference the steps in the [Open Your Workspace in IntelliJ](https://w.amazon.com/bin/view/Amazon_Technical_Academy/Internal/HowTos/Open_Your_Workspace_In_IntelliJ/) 
How-To. Let's ensure everything is configured in your IDE to build correctly. In IntelliJ, click on the "Build" menu and 
then select "Build Project."

You may see an error message pop up saying the SDK is not specified. If you do, select OK. The Project Structure menu 
should open. If it doesn't select "File" > "Project Structure..." In the Project SDK drop down select "1.8". Ensure the 
Project language level drop down reads "8 - Lambdas, type annotations etc." Click "Apply" and "OK". Once again select 
the "Build" > "Build Project" menu option.

**Exit Checklist:**

* You are able to successfully run `rde wflow run`
* You are able to successfully call the service via `curl`
* You have set up your project in IntelliJ
