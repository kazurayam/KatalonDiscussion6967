import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/6967_testbed.html')

def expectedContents = [
	"Tokyo CURA Healthcare Center",
	"Hongkong CURA Healthcare Center",
	"Seoul CURA Healthcare Center" ]
// check if getElementContentsAsList() functions well
List<String> cs = 
    CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getElementContentsAsList'('//select[@name="facility"]/option')
for (int i = 0; i < cs.size(); i++) {
	WebUI.verifyEqual(cs[i], expectedContents[i])
}

def expectedValues = [
	"Tokyo",
	"Hongkong",
	"Seoul" ]
// check if getAttributeValuesAsList functions well
List<String> vs = 
    CustomKeywords.'com.kazurayam.ksbackyard.FindElementsByXPath.getAttributeValuesAsList'('//select[@name="facility"]/option', 'value')
for (int i = 0; i < vs.size(); i++) {
	WebUI.verifyEqual(vs[i], expectedValues[i])
}


WebUI.closeBrowser()