import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/6967_testbed.html')

List<String> contents = CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getElementTextAsList'('//select[@name="facility"]/option')
def expectedContents = [
		"Tokyo CURA Healthcare Center",
		"Hongkong CURA Healthcare Center",
		"Seoul CURA Healthcare Center" ]
for (int i = 0; i < contents.size(); i++) {
	WebUI.verifyEqual(contents[i], expectedContents[i])
}

List<String> values = CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getAttributeTextAsList'('//select[@name="facility"]/option', 'value')
def expectedValues = [
	"Tokyo",
	"Hongkong",
	"Seoul" ]
for (int i = 0; i < values.size(); i++) {
	WebUI.verifyEqual(values[i], expectedValues[i])
}

WebUI.closeBrowser()