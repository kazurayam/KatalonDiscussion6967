import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def expectedContents = [
	"Tokyo CURA Healthcare Center",
	"Hongkong CURA Healthcare Center",
	"Seoul CURA Healthcare Center" ]

def expectedValues = [
	"Tokyo",
	"Hongkong",
	"Seoul" ]

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/6967_testbed.html')

List<String> cs = CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getElementContentsAsList'('//select[@name="facility"]/option')
for (int i = 0; i < cs.size(); i++) {
	WebUI.verifyEqual(cs[i], expectedContents[i])
}

List<String> vs = CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getAttributeValuesAsList'('//select[@name="facility"]/option', 'value')
for (int i = 0; i < vs.size(); i++) {
	WebUI.verifyEqual(vs[i], expectedValues[i])
}

WebUI.closeBrowser()