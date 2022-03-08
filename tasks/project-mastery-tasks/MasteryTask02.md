## Mastery Task 2 - Concurrent Tasks

The product detail page team has strict latency requirements for any content rendered “above the fold“, which refers to 
any part of a product detail page that you see when the page first loads without scrolling down.

![Figure 1](../../src/resources/mastery-task2-abovethefold-example.png)

*Figure 1: Screenshot of an Amazon product detail page, with a red horizontal line across the image to indicate "the 
fold" in "above the fold". We consider any content on the detail page above the red line "above the fold".*

We've been looking at our latency graphs, and we've discovered that our latency increases almost linearly with the
number of `TargetingPredicate`s in a `TargetingGroup`. It's probably because each `TargetingPredicate` calls 
the DAOs it needs on its own.

You recall from the `KindlePublishingService` project that when `RecommendationsService` was slowing us down, you cached 
the calls using an in-memory cache. However, since our service runs on Lambda, our activities can run on
different hosts every time, so any data we save in an in-memory cache will not be there in subsequent calls.

We *have* learned to perform multiple I/O calls at the same time with an `ExecutorService`.
By running all the predicates in a targeting group concurrently,
we can choose an ad quickly enough to meet the latency requirements!

Use an `ExecutorService` to concurrently call the predicates in each group.
You may use a `Future` to store the results, but that's not required.
You also don't have to use lambda expressions, but they will probably make your code more readable.

Note: As with MT01, you'll see there is a constant boolean in the file that is currently set to `false`.
This time it's named `IMPLEMENTED_CONCURRENCY`.
Once you've made your changes, please also update that boolean flag to `true`,
classes, otherwise your MT02 tests won't pass. :)

Run the `tct-task2` workflow to verify that your implementation works,
then submit a CR labeled with "[MT02]" to `Unit-5-Instructor-Reviewers`.

**Exit Checklist:**

* You've implemented your new functionality with unit tests
* `rde wflow run tct-task2` passes
* Your CR implementing your new functionality is approved by an ATA Instructor and pushed.
* Mastery Task 2 TCTs are passing in your pipeline.
