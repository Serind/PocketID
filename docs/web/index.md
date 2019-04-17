## Version
Web APIs -> v1

---
## Getting Started

Base URL:
> Staging server: 
https://pocket-id-backend.herokuapp.com (It uses [AION mastery](https://mastery.aion.network/#/)) <br>
> Production server: 
https://pocketid-221015.appspot.com (It uses [AION mainnet](https://mainnet.aion.network/#/dashboard))

Our API has predictable resource-oriented URLs, accepts [form-encoded](https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms) request bodies, returns [JSON-encoded](http://www.json.org/) responses, and uses standard HTTP response codes, authentication, and verbs.

<br/>
<b>Note:</b>    

PocketID requires AppID to be part of every single web api request.

For staging server api, sample AppID can be used: `nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)`<br/>
For production server, please [contact us](mailto:vikas@seribdlabs.com) to generate a production AppID.

:mod:`flask.ext.restplus`
-------------------------

.. automodule:: flask_restplus
    :members:

## APIs

### Auth

#### 1. Register a new user<br/><br/>

##### Url: 
`POST: /api/v1/register`<br/><br/>

##### Description: 
Registers a new user on PocketID.<br/><br/>

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/api/v1/register \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'username=testuser&password=123123123&email=testuser@serindlabs.com&country_code=1&phone_number=4379999999&otp_code=111111&client_id=sdhjshj74hduh7cnu3n$2enjn@'

###### Request params:
* `username` : <i>(Required)</i> New user's username 
* `password` : <i>(Required)</i> New user's password 
* `email` : <i>(Required)</i> Email address to use for user (non unique email address works too) 
* `country_code` : <i>(Required)</i> User's phone number's country code 
* `phone_number` : <i>(Required)</i> User's Phone number (This needs to be unique) 
* `otp_code` : <i>(Required)</i> OTP code sent to user's phone number
* `client_id` : <i>(Required)</i> Use - `sdhjshj74hduh7cnu3n$2enjn@` as hardcoded value

##### Example response:
    {
        "message": "User created successfully!",
        "errors": [],
        "data": {
            "access_token": "f6ee0424267a319d2ea0cfed03595fc89df4997b",
            "token_type": "Bearer",
            "expires_in": 345599,
            "refresh_token": "dd6fb873a6e871125337e36f877fd4606b1906f1",
            "refresh_token_expires_in": 1727999,
            "client_id": "sdhjshj74hduh7cnu3n$2enjn@"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i> New user's password 
* `data` : <i>(Object)</i>
    * `access_token` : Generated access token for user
    * `token_type` : Token type 
    * `expires_in` : Access token's expiry in seconds
    * `refresh_token` : Generated refresh token for user
    * `refresh_token_expires_in` : Refresh token's expiry in seconds
    * `client_id` : Client id used for generating tokens


#### 1. Register a new user<br/><br/>

##### Url: 
`POST: /api/v1/register`<br/><br/>

##### Description: 
Registers a new user on PocketID.<br/><br/>

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/api/v1/register \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'username=testuser&password=123123123&email=testuser@serindlabs.com&country_code=1&phone_number=4379999999&otp_code=111111&client_id=sdhjshj74hduh7cnu3n$2enjn@'

###### Request params:
* `username` : <i>(Required)</i> New user's username 
* `password` : <i>(Required)</i> New user's password 
* `email` : <i>(Required)</i> Email address to use for user (non unique email address works too) 
* `country_code` : <i>(Required)</i> User's phone number's country code 
* `phone_number` : <i>(Required)</i> User's Phone number (This needs to be unique) 
* `otp_code` : <i>(Required)</i> OTP code sent to user's phone number
* `client_id` : <i>(Required)</i> Use - `sdhjshj74hduh7cnu3n$2enjn@` as hardcoded value

##### Example response:
    {
        "message": "User created successfully!",
        "errors": [],
        "data": {
            "access_token": "f6ee0424267a319d2ea0cfed03595fc89df4997b",
            "token_type": "Bearer",
            "expires_in": 345599,
            "refresh_token": "dd6fb873a6e871125337e36f877fd4606b1906f1",
            "refresh_token_expires_in": 1727999,
            "client_id": "sdhjshj74hduh7cnu3n$2enjn@"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i> New user's password 
* `data` : <i>(Object)</i>
    * `access_token` : Generated access token for user
    * `token_type` : Token type 
    * `expires_in` : Access token's expiry in seconds
    * `refresh_token` : Generated refresh token for user
    * `refresh_token_expires_in` : Refresh token's expiry in seconds
    * `client_id` : Client id used for generating tokens



    

Once you have the client SDK, you’re ready to integrate it with your (d)App.

1. Add the `pocketid-sdk-release-1.0.aar` file to your project’s `/libs` folder 
1. Update your project dependencies to import the sdk
    
    in your `app/build.gradle`, add the following under `dependencies {}`
  
        implementation(name : 'pocketid-sdk-release-1.0', ext : 'aar')
        implementation 'com.google.code.gson:gson:2.8.5'
        implementation 'com.squareup.retrofit2:retrofit:2.5.0'
        implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
        implementation 'com.squareup.okhttp3:logging-interceptor:3.9.1'
        implementation 'com.hbb20:ccp:2.2.3'
        implementation 'com.android.support.constraint:constraint-layout:1.1.3'
        implementation 'com.android.support:cardview-v7:28.0.0'
        implementation 'com.android.support:appcompat-v7:28.0.0'
        implementation 'com.stripe:stripe-android:8.2.0'
        implementation 'com.github.bumptech.glide:glide:4.8.0'

    > <font color="red"><b>Upgrading?</b></font> please update your dependencies.<br/>
    
1. Add `flatDir` to your `app/build.gradle`
      
        repositories {
            flatDir {
                dirs 'libs'
            }
        }    

1. Sync your project with gradle
<br>

> If the above steps don't work for your project, follow the alternate [approach](https://developer.android.com/studio/projects/android-library#AddDependency) on how to import `.aar` files.

---
## Initialization

PocketID requires this mandatory step in order to initialize the sdk before any of its features to be consumed. 
You will find the class `PocketIDSdk` is the main source of your interaction with the sdk. 
To initialize the SDK, you’ll need to call `PocketIDSdk.getInstance().initialize()` in your application startup and pass in your `AppID`.

> In development environment use this sample AppID:<br>
> `nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)`<br>
> For production, please contact us to generate a prod AppID

```java
public class TestdApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PocketIDSdk.getInstance().initialize(this, "nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)");
    }
}

```
<br>

---
## Basic Guide

PocketID integration makes it very simple for your users to adopt blockchain without having the need or the pressure to fully understand it. 
Your users will appreciate when your onboarding process is similar to what they are already used to, ie username and password. 
In addition, PocketID SDK will allow (d)Apps to make crypto transactions. Logged-in users can `buy` with their credit card and `send` tokens to other PocketID users simply by their username.

<br>

### Login

Add the `PocketIDButton` to your activity's layout file.
```xml 
<com.serindlabs.pocketid.sdk.widget.PocketIDButton
    android:id="@+id/btnLogin" 
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

Override `onActivityResult()` in your activity and handle the successfully logged-in user.
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == PocketIDRequestCode.AUTHENTICATION && resultCode == RESULT_OK) {
        // code here
    } else {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
```
> `PocketIDSdk.getInstance().requiresLogin()` can be used to show or hide the login button</br>

> Prerequisites:</br>
> - [Getting Started](#getting-started)</br>
> - [Initialization](#initialization)



### Buy

To trigger the `buy` token flow:

```java
@Override
protected void onClick(View v) {
    PocketIDSdk.getInstance().buyToken(this);
}
```

Override `onActivityResult()` in your activity and handle the `buy` result.
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == PocketIDRequestCode.BUY_TOKEN && resultCode == RESULT_OK) {
        // code here
    } else {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
```

> Prerequisites:</br>
> User needs to be logged in

### Send

To trigger the `send` token flow:

```java
@Override
protected void onClick(View v) {
    PocketIDSdk.getInstance().sendToken(this);
}
```

Override `onActivityResult()` in your activity and handle the `send` result.
```java
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == PocketIDRequestCode.SEND_TOKEN && resultCode == RESULT_OK) {
        // code here
    } else {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
```

> Prerequisites:</br>
> User needs to be logged in

</br>

---
##Events

Most of the SDK functionality will have associated events that will be broadcast to notify of progress and result. 
To listen for these events, you may implement the `PocketIDListener` and pass it to `PocketIDSdk.getInstance().registerListener(PocketIDListener)`. 

In order to stop receiving the events, you can pass the same instance of your `PocketIDListener` to `PocketIDSdk.getInstance().unregisterListener(PocketIDListener)`.

```java 
public class SampleActivity extends Activity implements PocketIDListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PocketIDSdk.getInstance().registerListener(this);
    }
    
    @Override
    public void onEvent(String event, Bundle data) {
        switch (event)  {
            case EventType.EVENT_LOGIN_SUCCESS:
                 // code
                break;
        }
    }
    
    @Override
    protected void onDestroy() {
        PocketIDSdk.getInstance().unregisterListener(this);
        super.onDestroy();
    }
}
```

<br>

## Authentication 

If you'd like more control over when to trigger the authentication flow instead of using the ['Login with PocketID'](#login) button, 
all that is needed is to call `PocketIDSdk.getInstance().login()` and our client sdk will handle all the detailed work needed to generate an account.

```java 
public class MyActivity extends Activity {

    @Override
    public void onClick(View view) {
        PocketIDSdk.getInstance().login(this, false);
    }
}
```

Pass `true` for `defaultToRegister` if you want the initial screen to default to the `Register` flow instead of the `Login` flow.

<br>

**Handling Result**

Regardless of which approach taken there's 2 ways to handle the results of the `authentication` flow:

1. using `onActivityResult()` 
        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if (requestCode == PocketIDRequestCode.AUTHENTICATION && resultCode == RESULT_OK) {
                // code here
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
        
    
    This is the assured way of knowing when the user is fully authenticated and that the sdk
    has finished the flow and given the results back to the calling (d)App.
    
    > Errors will not be returned here. <br/>
    > The end result of registration is a logged-in user.
    

1. using registered [Events](#eventtype)


        @Override
        public void onEvent(String event, Bundle bundle) {
            switch (event) {
                case PocketIDEventType.EVENT_LOGIN_SUCCESS:
                    // code
                    break;
                case PocketIDEventType.EVENT_LOGIN_FAILED:
                    // code
                    break;
                case PocketIDEventType.EVENT_ACCOUNT_REGISTERED:
                    // code
                    break;
                case PocketIDEventType.EVENT_REGISTER_FAILED:
                    // code
                    break;
            }
        }   
        
    This approach is ideal for logging, analytics or for updating `non-flow` ui components of your (d)App.

    > Please see how to implement global [Event listener](#events)

##User

After the user has logged in, you can get the account detail by calling `PocketIDSdk.getInstance().getUser()` which will return a `User` object. 
   
```java 
public class MyActivity extends Activity {

    @Override
    public void onClick(View view) {
        User user = PocketIDSdk.getInstance().getUser();
    }
}
```

> Prerequisite:<br/>
> User must be logged-in or will return `null`

<br>


##Logout

The logged-in user will have a stored session in the client sdk.
In order to log the user out, just call `PocketIDSdk.getInstance().logout()`.
This will clear the session from the sdk and `PocketIDSdk.getInstance().requiresLogin()` will return `true`. 

> The `PocketIDSdk.getInstance().getUser()` will return `null`.

The SDK will broadcast the following events:

* `EVENT_LOGGED_OUT`

<br>

##Forgot Password

PocketID client sdk’s onboarding process comes built-in with forgot and reset password feature. 
This process have been simplified and is very seamless as the user will not have to go outside of the app at all to reset their password. 
This flow is built-in to the login process therefore the developer don’t have to do anything extra to support this feature. 

> The end result of forgot/reset password will be a logged-in user after successful reset.

After login, the SDK will broadcast the following events:

* `EVENT_LOGIN_SUCCESS`
* `EVENT_LOGIN_FAILED`

<br>

##Balance

PocketID SDK provides access to a logged-in user's account balance.
  
```java 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (PocketIDSdk.getInstance().getBalance() == null) {
            PocketIDSdk.getInstance().fetchBalance();
        } else {
            BalanceResponse balanceRsp = PocketIDSdk.getInstance().getBalance();
        }
    }
    
    @Override
    public void onEvent(String event, Bundle bundle) {
        switch (event) {
            case PocketIDEventType.EVENT_GET_BALANCE_SUCCESS:
                BalanceResponse balanceRsp = PocketIDSdk.getInstance().getBalance();
                break;
            case PocketIDEventType.EVENT_GET_BALANCE_FAILED:
                // show error
                break;
        }
    }
```

Fetch Balance will broadcast the following events:

* `EVENT_GET_BALANCE_SUCCESS`
* `EVENT_GET_BALANCE_FAILED`

> Prerequisite:<br/>
> User must be logged-in<br/>
> Global [Event listener](#events)

<br>

##Transactions

PocketID SDK provides access to a logged-in user's transactions.
  
```java 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (PocketIDSdk.getInstance().getTransactions() == null) {
            PocketIDSdk.getInstance().fetchTransactions();
        } else {
            TransactionsResponse transactionsRsp = PocketIDSdk.getInstance().getTransactions();
        }
    }
    
    @Override
    public void onEvent(String event, Bundle bundle) {
        switch (event) {
            case PocketIDEventType.EVENT_GET_TRANSACTIONS_SUCCESS:
                TransactionsResponse transactionsRsp = PocketIDSdk.getInstance().getTransactions();
                break;
            case PocketIDEventType.EVENT_GET_TRANSACTIONS_FAILED:
                // show error
                break;
        }
    }
```

Fetch Transactions will broadcast the following events:

* `EVENT_GET_TRANSACTIONS_SUCCESS`
* `EVENT_GET_TRANSACTIONS_FAILED`

> Prerequisite:<br/>
> User must be logged-in<br/>
> Global [Event listener](#events)

<br>

##Customization
   
We understand every application is unique with it’s own design and functional needs. PocketID sdk provides the necessary tools for customization for the respective platforms. 
On Android, `Customize` is the main source of sdk customization. You can get a reference to it by calling `PocketIDSdk.getInstance().customize()`. 
It provides method chaining for easy usage.
For a full list of supported customization, please see the [Reference](#customize).

```java 
public class TestdApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PocketIDSdk.getInstance().customize()
                    .setLogo(R.drawable.logo);
    }
}
```

<br>

##Theme

As part of the customization, the SDK comes built-in with 2 standard themes.
With clean and modern design, along with a `LIGHT` and `DARK` theme, the SDK will blend in with your (d)App perfectly.

```java 
public class TestdApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PocketIDSdk.getInstance().customize()
                    .setTheme(PocketIDTheme.DARK);
    }
}
```

> The `DARK` theme is only supported for `authentication` flows at the moment. 

---
##Reference

<br>
#### PocketIDButton

* Static Button for Logging in with PocketID
* Handles it’s own click event
* Launches the Login Flow

<br>

#### Customize

Customize the SDK features

* `setTheme(theme)`
* `setLogo(logo)`
* `setShowLoginBackButton(show)`
* `setShowRegisterBackButton(show)`
* `setAppName(appName)`**\*\*New**
* `setFiatDecimalPoints(numOfPoints)`**\*\*New**
* `setTokenDecimalPoints(numOfPoints)`**\*\*New**

<br>

#### PocketIDListener

Listens for global events

* `onEvent(event, data)`

<br>

#### PocketIDTheme

Contains the supported Theme Constants

* `LIGHT`
* `DARK`

<br>

#### PocketIDSdk

Main interactions with the sdk is through this class. Used as Singleton.

* `initialize()`
    * Validates the state of the sdk
    * If User is already logged in on the device, will set state to logged in
* `requiresLogin()`
    * True or False
* `customize()`
    * Customize the SDK
* `defaults()`**\*\*New**
    * Load to default state of SDK
* `registerListener(PocketIDListener)`
    * Registers a global event listener
* `unregisterListener(PocketIDListener)`
    * Unregisters the global event listener
* `login(fragment/activity, defaultToRegister)`
    * Manually triggers the Login Flow
    * defaultToRegister will start with the registration screen instead of login screen
* `buy(fragment/activity)`**\*\*New**
    * Triggers the `buy` token flow
* `send(fragment/activity)`**\*\*New**
    * Triggers the `send` token flow
* `logout(context)`
    * Logs out the current user and clears the global session
* `getUser()`
    * Returns: user details
* `fetchBalance()`**\*\*New**
    * Load the balance response
* `getBalance()`**\*\*New**
    * returns `BalanceResponse`
* `fetchTransactions()`**\*\*New**
    * Load the transactions response
* `getTransactions()`**\*\*New**
    * returns `TransactionsResponse`

<br>

#### User

Class represents the user's data.

* `getFirstName()`
    * User's first name
* `getLastName()`
    * User's last name
* `getUsername()`
    * User's username
* `getCountryCode()`
    * User's phone country code
* `getPhoneNumber()`
    * User's phone number
* `getEmail()`
    * User's email address
    
<br>

#### PocketIDEventType (formerly EventType)

Contains all the type that are sent to `PocketIDListener`

* `EVENT_LOGIN_SUCCESS`
* `EVENT_LOGIN_FAILED`
* `EVENT_ACCOUNT_REGISTERED`
* `EVENT_REGISTER_FAILED`
* `EVENT_LOGGED_OUT`
* `EVENT_GET_BALANCE_SUCCESS`**\*\*New**
* `EVENT_GET_BALANCE_FAILED`**\*\*New**
* `EVENT_SEND_SUCCESS`**\*\*New**
* `EVENT_SEND_CANCELLED`**\*\*New**
* `EVENT_BUY_SUCCESS`**\*\*New**
* `EVENT_BUY_CANCELLED`**\*\*New**
* `EVENT_GET_TRANSACTIONS_SUCCESS`**\*\*New**
* `EVENT_GET_TRANSACTIONS_FAILED`**\*\*New**

<br>

#### PocketIDUiUtil

SDK UI Utility class **\*\*New**

* static `formatFiatString(amount)`
    * formats a fiat currency string according to sdk settings
* static `formatTokenString(amount)`
    * formats a token string according to sdk settings
    
<br>

#### BalanceResponse

Class represents user's balance data **\*\*New**

* `getDefaultWalletId()`
    * Returns the id of the default wallet
* `getDefaultWallet()`
    * Returns the user's default `Wallet` object
* `getAllWallets()`
    * Returns all the user's `Wallet` accounts.
    
<br>

#### TransactionsResponse

Class represents user's transaction data **\*\*New**

* `getTransactions()`
    * Returns a list of `Transaction`
    
<br>

---
##Coming Soon
- The sdk will be hosted in Maven Repository<br/>
- `DARK` theme support for `buy` and `send` flows