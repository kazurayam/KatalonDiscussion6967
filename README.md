How to run a Katalon script using a Chrome executable installed in a non-standard location
=====

# What is this?

This is a simple Katalon Studio for demonstration purpose.
You can clone this out on you PC and execute it with your Katalon Stduio.

This project is developed to propose a solution for the following discussion:

- https://forum.katalon.com/discussion/6944/test-execution-running-katalon-scripts-in-same-browser-with-different-version

Question raised there was:

>Hi, Can we run Katalon scripts in same browser with different versions.
Say i have two different google chrome versions installed (v66, v65), i wanted to run Testcase1 in v66 and Testcase 2 in v65.

# Solution proposed

With a few lines of code in a Katalon Test Case, you can start a Chrome browser using a chrome.exe installed in a non-standard location. This demo shows how to do it.

# description

read code of the [Test case script](https://github.com/kazurayam/KatalonDiscussion6944/blob/master/Scripts/TC1/Script1527037412367.groovy)

# References

- [chromedriver document/ capabilities and options](http://chromedriver.chromium.org/capabilities)
- [Katalon Forum/how to get chomedriver.log](https://forum.katalon.com/discussion/6736/getting-chromedriverlog-when-you-run-recordspy-web-to-investigate-why-ks-failed-to-start-chrome)
