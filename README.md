Custom Katalon Keyword: getting HTML elements as list
=====

# Part 1

The following part was written at 24/May/2018

### What is this?

This is a simple Katalon Studio project for demonstration purpose.
You can clone this out on you PC and execute it with your Katalon Studio.

This project is developed to propose a solution for the following discussion:

- https://forum.katalon.com/discussion/6967/get-text-of-multiple-div-elements

Question raised there was:

> I want to get the text of specific elements (names), store them in an array and compare them to an other array.

### Problem to solve

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


### Solution proposed

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

### Description

A test case [TC_by_custome_keywords](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Scripts/TC_by_custom_keywords/Script1527139045725.groovy) shows you how to use the custome keywords.


---


# Part 2

The following part was written at 26/June/2018.

### What is this?

This is a simple Katalon Studio project for demonstration purpose.
You can clone this out on you PC and execute it with your Katalon Studio.

This project is developed to propose a solution for the following discussion:

- https://forum.katalon.com/discussion/7520/the-xpath-for-the-url-is-correct-but-not-visible-if-added-via-addproperty

Question raised there was:

>Emails sent from the .csv in this order:
> - email 1
> - email 2
> - email n
>
>Emails shown in opposite order in the Inbox:
> - email n
> - email 2
> - email 1
>
>I used the same .csv and same loop. Just have 2 variables. One starts from the 1st row of the file and increments all the way to the last record in the file. The other variable works in opposite direction.

### Rewording Problem to resolve

- I have a Web page to test. The target page has a list of web elements. Let me name it as **the found elements**. The way how the web elements are marked up is not significant. I will specify appropriate XPath expression to select the elements out of the target HTML. Let me suppose I have the following elements, for example:
```
<select id="combo_facility" name="facility" class="form-control">
    <option value="Tokyo">Tokyo CURA Healthcare Center</option>
    <option value="Hongkong">Hongkong CURA Healthcare Center</option>
    <option value="Seoul">Seoul CURA Healthcare Center</option>
</select>
```
- My test code has a list of expected texts to be displayed in the target pages. Let me name it as **the expected texts**. Let me suppose I have the following literal in my test case, for example:
```
List<Map<String,String>> data =  [
    ["text":"Hongkong CURA Healthcare Center"],
    ["text":"Seoul CURA Healthcare Center"],
    ["text":"Tokyo CURA Healthcare Center"],
	["text":"New York CURA Healthcare Center"]
]
```
- The two lists roughly corresponds, but strictly speaking not equivalent. This means:
  - The size of **the found elements** is not necessarily equal to the size of **the expected texts**. Making one-to-one correspondence may results remainders on both.  
  - The sorting order of **the found elements** and the sorting order of **the expected texts** may differ. Even more, both may be unsorted at all.
- I want to iterate over **the expected texts** to find out if each expected text is displayed in **the target page**. In order to find it I want to perform a nested iteration over **the found elements** for each of the expected texts to try matching.
- I want to show 'yes' or 'no' for each items of **the expeted texts**. This is the final output from my test case.

I think that this is a Frequently Asked Quetion in the Katalon forum.
- https://forum.katalon.com/discussion/6967/get-text-of-multiple-div-elements
- https://forum.katalon.com/discussion/7520/the-xpath-for-the-url-is-correct-but-not-visible-if-added-via-addproperty

### Technical issues : shortage of Katalon Studio



### Solution proposed

Here is the code of my test case '[TC_getWebElementsAsList](https://github.com/kazurayam/KatalonDiscussion6967/blob/master/Scripts/TC_getWebElementsAsList/Script1529976999972.groovy)':
