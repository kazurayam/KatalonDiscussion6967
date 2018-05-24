Custom Katalon Keyword: get contents of HTML elements as list
=====

# What is this?

This is a simple Katalon Studio project for demonstration purpose.
You can clone this out on you PC and execute it with your Katalon Studio.

This project is developed to propose a solution for the following discussion:

- https://forum.katalon.com/discussion/6967/get-text-of-multiple-div-elements

Question raised there was:

> I want to get the text of specific elements (names), store them in an array and compare them to an other array.

# Problem to solve

I have a target web page like this:
![Target web page](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/docs/6967_testbed.png)

I am interested in the list of `<option>` elements in this page.

I want to verify the contents of these displayed `<option>` elements against other list of Strings (`'Tokyo CURA Healthcare Center'`, `'Hongkong CURA Healthcare Center'`, `'Seoul CURA Healthcare Center'`). Not only the content (`<option>content</option>`), I want to verify the value of attributes (`<option value="value">`) as well.

In Katalon Studio, we can make [Test Object](https://docs.katalon.com/display/KD/Manage+Test+Object). Using [findTestObject](https://api-docs.katalon.com/studio/v4.7.0/api/com/kms/katalon/core/testobject/ObjectRepository.html) method we can grasp a single `<option>` element amongst the set of `<option>`s. The sample Test Case [TC_by_builtin_keyword](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Scripts/TC_by_builtin_keywords/Script1527139026235.groovy) shows how to get access the content string of `<option>` elements.

However I find a primitive shortage: I want to get the varying size of `<option>` element list. It is mandatory to control `for` loop. But there is no Katalon's build-in keyword. For example,

```
for (int i = 0; i < ????.size(); i++) {
    def text = WebUI.getText(findTestObject('facility_option_indexed',
                                 [('index') : i + 1]))
    WebUI.comment("${text}")
}
```
I can not resolve `????` above.


# Solution proposed

I have developed a Groovy class.
- [`com.kazurayam.ksbackyard.com.kazurayam.ksbackyard.FindElementsByXPath`](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Keywords/com/kazurayam/ksbackyard/FindElementsByXPath.groovy)

This class provides 2 methods (Keyword) which returns a List of Strings.
- `List<String> getElementContentsAsList(String xpath4elements)`
- `List<String> getElementAttributesAsList(String xpath4elements, String attributeName)`

```
def expectedContents = [
	"Tokyo CURA Healthcare Center",
	"Hongkong CURA Healthcare Center",
	"Seoul CURA Healthcare Center" ]
List<String> cs = CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getElementContentsAsList'('//select[@name="facility"]/option')
for (int i = 0; i < cs.size(); i++) {
	WebUI.verifyEqual(cs[i], expectedContents[i])
}
```

```
def expectedValues = [
	"Tokyo",
	"Hongkong",
	"Seoul" ]
List<String> vs = CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getAttributeValuesAsList'('//select[@name="facility"]/option', 'value')
    for (int i = 0; i < vs.size(); i++) {
    	WebUI.verifyEqual(vs[i], expectedValues[i])
    }
```

# Description

A test case [TC_by_custome_keywords](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Scripts/TC_by_custom_keywords/Script1527139045725.groovy) shows you how to use the custome keywords.
