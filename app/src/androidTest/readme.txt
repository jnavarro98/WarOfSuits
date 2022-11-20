Test are set up but activity isn't able to launch, maybe there are some incompatibility issues
that I couldn't solve in the time given. Test seem to pass but the app isn't launched.

I've used kaspresso with cucumber which has worked in older projects.
I like these frameworks because they let you write readable test cases and use kotlin.

In the features folder you can find features, which are linked to the tests by the instrumentation
runner in the test folder with annotations.

Since I couldn't run them I decided to do a little of everything to showcase how it would be used.

If you want to try to run them, create an androidTestInstrumentation runconfig pointing to the
features folder (located in assets).