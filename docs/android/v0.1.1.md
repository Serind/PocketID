## Version
Android -> v0.1.1

## Getting Started

Start by downloading the client SDK from the website.

Once you have the client SDK, you’re ready to integrate it with your app.

1. Add the `pocketid-sdk-release.aar` file to your project’s `/libs` folder 
1. Update your project dependencies to import the sdk
    
    in your `app/build.gradle`, add the following under `dependencies {}`

        implementation(name : ‘pocketid-sdk-release’, ext : ’aar’)
        implementation 'com.google.code.gson:gson:2.8.5'
        implementation 'com.squareup.retrofit2:retrofit:2.5.0'
        implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
        implementation 'com.squareup.okhttp3:logging-interceptor:3.9.1'
        implementation 'com.hbb20:ccp:2.2.3'


1. Sync your project with gradle
<br>

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
        PocketIDSdk.getInstance().initialize(this, “nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)”);
    }
}

```
<br>

## Events

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

## Customization
   
We understand every application is unique with it’s own design and functional needs. PocketID sdk provides the necessary tools for customization for the respective platforms. 
On Android, `Customize` is the main source of sdk customization. You can get a reference to it by calling `PocketIDSdk.getInstance().customize()`. 
It provides method chaining for easy usage.
For a full list of supported customization, please see the [Reference](#reference).

```java 
public class TestdApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PocketIDSdk.getInstance().customize()
                    .setLogLevel(LogLevel.VERBOSE)
                    .setTheme(PocketIDTheme.LIGHT);
    }
}
```
<br>

## Login/Registration (Button)

The easiest and recommended way to initiate the login/registration process is through our dedicated ‘Login with PocketID’ button. This is a great way to onboard both users who are new to your (d)App or users who may already have a PocketID account. All that’s needed is to add to your Android layout.

```xml 
<com.serindlabs.pocketid.sdk.widget.PocketIDButton
   	android:layout_width="wrap_content"
  	android:layout_height="wrap_content" />
```
<br>

## Login/Registration (Manual)

PocketID integration makes it very simple for your users to adopt blockchain without having the need or the pressure to fully understand it. 
Your users will appreciate when your onboarding process is similar to what they are already used to, ie username and password. 
All that is needed is to call `PocketIDSdk.getInstance().login()` and our client sdk will handle all the detailed work needed to generate an account.

```java 
public class MyActivity extends Activity {

    @Override
    public void onClick(View view) {
        PocketIDSdk.getInstance().login(this, false);
    }
}
```

Pass `true` for `defaultToRegister` if you want the initial screen to default to the Register flow instead of the Login flow.

> Note that the end result of registration will be a logged-in user.

After login, the SDK will broadcast the following events:

* EVENT_LOGIN_SUCCESS
* EVENT_LOGIN_FAILED
	
After registration, the SDK will broadcast the following:

* EVENT_ACCOUNT_REGISTERED
* EVENT_ACCOUNT_REGISTER_FAILED

<br>

## User

After the user has logged in, you can get the account detail by calling `PocketIDSdk.getInstance().getUser()` which will return a `User` object. 
   
```java 
public class MyActivity extends Activity {

    @Override
    public void onClick(View view) {
        User user = PocketIDSdk.getInstance().getUser();
    }
}
```
<br>

## SSO (single sign-on)

Just like the major single sign-on platforms such as Facebook and Google, our login/onboarding process is meant to make it very easy and convenient for users to login to your app. 
The great aspect of SSO is that the user don’t have to re-login to your app every time and as a side benefit, user’s don’t need to login to other apps that support PocketID as well. 
This creates a level of comfort for users and will create a sphere of convenience. 
As a developer, you don’t have to do anything extra to enable this feature, it works by default on the respective platform.

<br>

## Logout

The logged-in user will have a stored session in the client sdk.
In order to log the user out, just call `PocketIDSdk.getInstance().logout()`.
This will clear the session from the sdk and `PocketIDSdk.getInstance().requiresLogin()` will return `true`. 

> Note that if the user is not logged in, `PocketIDSdk.getInstance().getUser()` will return `null`.

The SDK will broadcast the following events:

* EVENT_LOGGED_OUT

<br>

## Forgot Password

PocketID client sdk’s onboarding process comes built-in with forgot and reset password feature. 
This process have been simplified and is very seamless as the user will not have to go outside of the app at all to reset their password. 
This flow is built-in to the login process therefore the developer don’t have to do anything extra to support this feature. 

> Note that the end result of forgot/reset password will be a logged-in user after successful reset.

After login, the SDK will broadcast the following events:

* EVENT_LOGIN_SUCCESS
* EVENT_LOGIN_FAILED

<br>

## Reference
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
* `setLogLevel(level)`

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
* `registerListener(PocketIDListener)`
    * Registers a global event listener
* `unregisterListener(PocketIDListener)`
    * Unregisters the global event listener
* `login(context, defaultToRegister)`
    * Manually triggers the Login Flow
    * defaultToRegister will start with the registration screen instead of login screen
* `logout(context)`
    * Logs out the current user and clears the global session
* `getUser()`
    * Returns: user details

<br>

#### EventType

Contains all the event type that are sent to `PocketIDListener`

* `EVENT_LOGIN_SUCCESS`
* `EVENT_LOGIN_FAILED`
* `EVENT_ACCOUNT_REGISTERED`
* `EVENT_REGISTER_FAILED`
* `EVENT_LOGGED_OUT`
