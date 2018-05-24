import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/6967_testbed.html')

def expectedContents = [
	"Tokyo CURA Healthcare Center",
	"Hongkong CURA Healthcare Center",
	"Seoul CURA Healthcare Center" ]

for (int i = 0; i < 3; i++) {
    def text = WebUI.getText(findTestObject('facility_option_indexed', [('index') : i + 1]))
    WebUI.verifyEqual(text, expectedContents[i])
}

WebUI.closeBrowser()



