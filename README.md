# selenium_amazon_automation
### Automating login and product purchase for Amazon using Selenium.

## Test Data
The test data is provided to test methods using DataProvider methods.
### Valid test data
the test data used for valid tests is stored inside validTestDataProperties.properties located [here](https://github.com/KBaria/selenium_amazon_automation/tree/master/src/test/java/com/selenium/amazon_automation/config).
replace the key and values as per requirements.
```
// amazon credentials
username=your amazon username
password=your amazon password

// product to be searched during ProductTest test methods
product=product to be searched

// address to be used during ProductTest test methods
fullname=your fullname
phone=your phone
pincode=your pincode
address_line=your address
city=your city
state=your state
```

### Invalid Test data
the test data for invalid tests is stored inside excel files located [here](https://github.com/KBaria/selenium_amazon_automation/tree/master/test-data). 
To read the excel files a helper class is used located [here](https://github.com/KBaria/selenium_amazon_automation/blob/master/src/test/java/com/selenium/amazon_automation/util/ExcelReader.java) this class uses Apache-POI.

## Test Reports
Test results are reflected in .html files using Extent Reports. The test methods use test listeners to log test results, the TestListener classes are located [here](https://github.com/KBaria/selenium_amazon_automation/tree/master/src/test/java/com/selenium/amazon_automation/util).
The test result files are generated inside the extent_report folder.
