Custom Katalon Keyword: FindElementsByXpath.getElementContentsAsList(String xpath4elements)
=====

# What is this?

This is a simple Katalon Studio for demonstration purpose.
You can clone this out on you PC and execute it with your Katalon Stduio.

This project is developed to propose a solution for the following discussion:

- https://forum.katalon.com/discussion/6967/get-text-of-multiple-div-elements

Question raised there was:

> I want to get the text of specific elements (names), store them in an array and compare them to an other array.

# Problem to solve

I have a target web page like this:
![Target web page](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/docs/6967_testbed.png)

I am interested in the list of `<option>` elements in this page.

I want to verify the contents of these displayed `<option>` elements against other list of Strings (`'Tokyo CURA Healthcare Center'`, `'Hongkong CURA Healthcare Center'`, `'Seoul CURA Healthcare Center'`). Not only the content:`<option>content</option>`, I also want to verify the value of attributes:`<option value="value">`

In Katalon Studio, we can make [Test Object](https://docs.katalon.com/display/KD/Manage+Test+Object). Using [findTestObject](https://api-docs.katalon.com/studio/v4.7.0/api/com/kms/katalon/core/testobject/ObjectRepository.html) method we can grasp a single `<option>` element amongst the set of `<option>`s. The sample Test Case [TC_by_builtin_keyword](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Scripts/TC_by_builtin_keywords/Script1527139026235.groovy) shows how to get access the content string of `<option>` elements.

However I find a primitive shortage:

- I want to get the varying size of `<option>` element list. It is mandatory to control `for (int i=0; i<SIZE; i++)`. But there is no Katalon's build-in keyword.

All I could do is as follows:

```
def expectedContents = [
	"Tokyo CURA Healthcare Center",
	"Hongkong CURA Healthcare Center",
	"Seoul CURA Healthcare Center" ]

for (int i = 0; i < expectedContents.size(); i++) {
    def text = WebUI.getText(findTestObject('facility_option_indexed', [('index') : i + 1]))
    WebUI.verifyEqual(text, expectedContents[i])
}
```

This code works, but ... something kinky. I want to know the size of `<options>` list, but I cannot. I do not like to naively assume that the size of `<options>` list would be the same as the size of `expectedConents`.

# Solution proposed

I have developed a Groovy class.
- [`com.kazurayam.ksbackyard.com.kazurayam.ksbackyard.FindElementsByXPath`](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Keywords/com/kazurayam/ksbackyard/FindElementsByXPath.groovy)

This class provides 2 methods (Keyword)
- `getElementContentsAsList(String xpath4elements)``
- `getElementAttributesAsList(String xpath4elements, String attributeName)`


# Description

A test case [TC_by_custome_keywords](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Scripts/TC_by_custom_keywords/Script1527139045725.groovy) shows you how to use the custome keywords.
