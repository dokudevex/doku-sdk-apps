# Doku Software Development Kit (SDK) Android

Doku SDK is an android plugin merchant who want to connect with Doku

## Instruction
To connect with doku, follow the steps : 

### Add Doku Repository
Add repository to **build.gradle** :
```
import com.doku.android.sdk.main.DokuPayVa;

repositories {
        maven {
            url  "https://dl.bintray.com/dokudevex/doku"
        }
    }
```
### Implement Package
Implement package in **build.gradle** :
```
implementation 'com.doku.android:doku-sdk-apps:0.0.1'
```

### Check External Library
Check your package library, you can see Aar Lib : 
com.doku.android:doku-sdk-apps:0.0.1@aar

### Use Doku Plugin
Using doku plugin you must generate words and build data, your data will proccesed to our system by doku-sdk-plugin. Follow the steps : 
#### Generate Words
To generate words you must prepare data below :
* clientId = merchant integration identity
* customerName = name of merchant customer
* amount = amount of invoice
* invoiceNumber = number of invoice
* expiredTime = limit time of invoice until expired by minutes format, ex : 60 as 60 minutes
* reusableStatus = flag of your invoice can multiple use or not
* sharedKey = Your Credential Access from Doku

```
<!-- Make a words componenets-->
String dataWords = clientid + customerEmail + customerName + amount + invoiceNumber + expiredTime + reusableStatus + sharedKey

<!--Hash to SHA26-->
String words = Utils.SHA256(dataWords);
```

#### Call Doku Component

```
new DokuPayVa.Builder(this)
	.clientId(Preferences.getClientId(PaymentPage.this))
    .customerEmail(customerEmail)
    .customerName(customerName)
    .dataAmount(amount)
    .dataWords(words)
    .expiredTime(60)
    .invoiceNumber(invoiceNumber)
    .isProduction(Preferences.getEnvironmentServer(PaymentPage.this))
    .reusableStatus(reusableStatus)
    .usePageResult(Preferences.getUsePageResult(PaymentPage.this))
    .connect();
```

And your pluggin was installed
