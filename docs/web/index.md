# Version: v1

---
## Getting Started

### Base URL:
> <b>Staging server:</b> 
[https://pocket-id-backend.herokuapp.com](https://pocket-id-backend.herokuapp.com) (It uses [AION mastery](https://mastery.aion.network/#/)) <br>
> <b>Production server: </b>
[https://pocketid-221015.appspot.com](https://pocketid-221015.appspot.com) (It uses [AION mainnet](https://mainnet.aion.network/#/dashboard))

Our API has predictable resource-oriented URLs, accepts [form-encoded](https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms) request bodies, returns [JSON-encoded](http://www.json.org/) responses, and uses standard HTTP response codes, authentication, and verbs.

!!! info "Note"

    PocketID requires `AppId` header parameter to be part of every single web api request.

    For staging server api, sample AppId can be used: `nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)`<br/>
    For production server, please [contact us](mailto:vikas@serindlabs.com) to generate a production AppId.

---
## APIs

!!! info "Note"

    To learn about the order of APIs you should call for particular actions, please follow this guide. [Click here](/web#guide)


### 1. Auth

#### 1.1. Register a new user

##### Url: 
`POST: /api/v1/register`

##### Description: 
Registers a new user on PocketID.

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/api/v1/register \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'username=testuser&password=123123123&email=testuser@pocketidapp.com&country_code=1&phone_number=4379999999&otp_code=111111&client_id=sdhjshj74hduh7cnu3n$2enjn@'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `username` : <i>(Required)</i> New user's username 
* `password` : <i>(Required)</i> New user's password 
* `email` : <i>(Required)</i> Email address to use for user (non unique email address works too) 
* `country_code` : <i>(Required)</i> Country code of user's phone number
* `phone_number` : <i>(Required)</i> User's Phone number (This needs to be unique) 
* `otp_code` : <i>(Required)</i> OTP code sent to user's phone number
* `client_id` : <i>(Required)</i> Use `sdhjshj74hduh7cnu3n$2enjn@` as hardcoded value

##### Example response:
    {
        "message": "User created successfully!",
        "errors": [],
        "data": {
            "access_token": "9455a5bb52c33e8a8c31a2b7c74cf5731f519e80",
            "token_type": "Bearer",
            "expires_in": 345599,
            "refresh_token": "dd6fb873a6e871125337e36f877fd4606b1906f2",
            "refresh_token_expires_in": 1727999,
            "client_id": "sdhjshj74hduh7cnu3n$2enjn@"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `access_token` : Generated access token for user
    * `token_type` : Token type 
    * `expires_in` : Access token's expiry in seconds
    * `refresh_token` : Generated refresh token for user
    * `refresh_token_expires_in` : Refresh token's expiry in seconds
    * `client_id` : Client id used for generating tokens

<br/>
#### 1.2. Login user

##### Url: 
`POST: /oauth/token`

##### Description: 
Generates access and refresh token for PocketID user.

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/oauth/token \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'username=testuser&password=123123123&grant_type=password&client_id=sdhjshj74hduh7cnu3n$2enjn@'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `username` : <i>(Required)</i> New user's username 
* `password` : <i>(Required)</i> New user's password 
* `grant_type` : <i>(Required)</i> Use `password` as hardcoded value
* `client_id` : <i>(Required)</i> Use `sdhjshj74hduh7cnu3n$2enjn@` as hardcoded value

##### Example response:
    {
        "message": "User logged in successfully!",
        "errors": [],
        "data": {
            "access_token": "9455a5bb52c33e8a8c31a2b7c74cf5731f519e80",
            "token_type": "Bearer",
            "expires_in": 345599,
            "refresh_token": "dd6fb873a6e871125337e36f877fd4606b1906f2",
            "refresh_token_expires_in": 1727999,
            "client_id": "sdhjshj74hduh7cnu3n$2enjn@"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `access_token` : Generated access token for user
    * `token_type` : Token type 
    * `expires_in` : Access token's expiry in seconds
    * `refresh_token` : Generated refresh token for user
    * `refresh_token_expires_in` : Refresh token's expiry in seconds
    * `client_id` : Client id used for generating tokens

<br/>
#### 1.3. Refresh tokens

##### Url: 
`POST: /oauth/token`

##### Description: 
Refreshes access and refresh token using their existing refresh token.

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/oauth/token \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'grant_type=refresh_token&refresh_token=dd6fb873a6e871125337e36f877fd4606b1906f0&client_id=sdhjshj74hduh7cnu3n$2enjn@'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `grant_type` : <i>(Required)</i> Use `refresh_token` as hardcoded value
* `refresh_token` : <i>(Required)</i> User's refresh token
* `client_id` : <i>(Required)</i> Use `sdhjshj74hduh7cnu3n$2enjn@` as hardcoded value

##### Example response:
    {
        "message": "User logged in successfully!",
        "errors": [],
        "data": {
            "access_token": "9455a5bb52c33e8a8c31a2b7c74cf5731f519e80",
            "token_type": "Bearer",
            "expires_in": 345599,
            "refresh_token": "dd6fb873a6e871125337e36f877fd4606b1906f2",
            "refresh_token_expires_in": 1727999,
            "client_id": "sdhjshj74hduh7cnu3n$2enjn@"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `access_token` : Generated access token for user
    * `token_type` : Token type 
    * `expires_in` : Access token's expiry in seconds
    * `refresh_token` : Generated refresh token for user
    * `refresh_token_expires_in` : Refresh token's expiry in seconds
    * `client_id` : Client id used for generating tokens

<br/>
#### 1.4. Username availability

##### Url: 
`GET: /api/v1/users/username/{{username}}`

##### Description: 
Checks thr availability of username

##### Example request:
    curl -X GET https://pocket-id-backend.herokuapp.com/api/v1/users/username/{{username} \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `username` (path): <i>(Required)</i> Username to check availability for

##### Example response:
Status code 200:
```
    {
        "message": "username is available",
        "errors": [],
        "data": null
    }
```
Status code 400:
```
    {
        "message": "username is occupied",
        "errors": [],
        "data": null
    }
```

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>null</i>

---
### 2. Reset Password
#### 2.1. Reset password using phone number

##### Url: 
`PUT: /api/v1/user/password`

##### Description: 
Reset password using phone number

##### Example request:
    curl -X PUT https://pocket-id-backend.herokuapp.com/api/v1/user/password \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'country_code=1&phone_number=9999999999&password=123123123&otp_code=111111&client_id=sdhjshj74hduh7cnu3n$2enjn@'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `country_code` : <i>(Required)</i> Country code of user's phone number
* `phone_number` : <i>(Required)</i> Phone number of existing user
* `password` : <i>(Required)</i> New password to set
* `otp_code` : <i>(Required)</i> OTP code sent to user's phone number
* `client_id` : <i>(Required)</i> Use `sdhjshj74hduh7cnu3n$2enjn@` as hardcoded value

##### Example response:
    {
        "message": "Password updated successfully!",
        "errors": [],
        "data": {
            "access_token": "9455a5bb52c33e8a8c31a2b7c74cf5731f519e80",
            "token_type": "Bearer",
            "expires_in": 345599,
            "refresh_token": "dd6fb873a6e871125337e36f877fd4606b1906f2",
            "refresh_token_expires_in": 1727999,
            "client_id": "sdhjshj74hduh7cnu3n$2enjn@"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `access_token` : Generated access token for user
    * `token_type` : Token type 
    * `expires_in` : Access token's expiry in seconds
    * `refresh_token` : Generated refresh token for user
    * `refresh_token_expires_in` : Refresh token's expiry in seconds
    * `client_id` : Client id used for generating tokens

<br/>
#### 2.2. Reset password using username

##### Url: 
`PUT: /api/v1/user/password`

##### Description: 
Reset password using phoe number

##### Example request:
    curl -X PUT https://pocket-id-backend.herokuapp.com/api/v1/user/password \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'username=testuser&password=123123123&otp_code=111111&client_id=sdhjshj74hduh7cnu3n$2enjn@'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `username` : <i>(Required)</i> Username of existing user
* `password` : <i>(Required)</i> New password to set
* `otp_code` : <i>(Required)</i> OTP code sent to user's phone number
* `client_id` : <i>(Required)</i> Use `sdhjshj74hduh7cnu3n$2enjn@` as hardcoded value

##### Example response:
    {
        "message": "Password updated successfully!",
        "errors": [],
        "data": {
            "access_token": "9455a5bb52c33e8a8c31a2b7c74cf5731f519e80",
            "token_type": "Bearer",
            "expires_in": 345599,
            "refresh_token": "dd6fb873a6e871125337e36f877fd4606b1906f2",
            "refresh_token_expires_in": 1727999,
            "client_id": "sdhjshj74hduh7cnu3n$2enjn@"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `access_token` : Generated access token for user
    * `token_type` : Token type 
    * `expires_in` : Access token's expiry in seconds
    * `refresh_token` : Generated refresh token for user
    * `refresh_token_expires_in` : Refresh token's expiry in seconds
    * `client_id` : Client id used for generating tokens


---
### 3. Send OTP
#### 3.1. Send OTP using phone number

##### Url: 
`POST: /api/v1/otp/send`

##### Description: 
Send OTP using phone number

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/api/v1/otp/send \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'country_code=1&phone_number=9999999999&new_user=1'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `country_code` : <i>(Required)</i> Country code of user's phone number
* `phone_number` : <i>(Required)</i> Phone number of existing user
* `new_user` : <i>(Required)</i> Possible values - (0, 1). <br/>
    0 - This is an existing user (to be used for Forgot password). <br/>
    1 - This is a new user (to be used while registering)

##### Example response:
    {
        "message": "OTP sent successfully!",
        "errors": [],
        "data": null
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>null</i>

<br/>
#### 3.2. Send OTP using username

##### Url: 
`POST: /api/v1/otp/send`

##### Description: 
Send OTP using username

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/api/v1/otp/send \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'username=testuser&new_user=0'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `username` : <i>(Required)</i> Username of existing user
* `new_user` : <i>(Required)</i> Possible values - (0, 1). <br/>
    0 - This is an existing user (to be used for Forgot password). <br/>
    1 - This is a new user (throws an error)

##### Example response:
    {
        "message": "OTP sent successfully!",
        "errors": [],
        "data": null
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>null</i>



---
### 4. User
#### 4.1. Get user personal information

##### Url: 
`GET: /api/v1/me`

##### Description: 
Get user personal information using access token

##### Example request:
    curl -X GET https://pocket-id-backend.herokuapp.com/api/v1/me \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`

##### Example response:
    {
        "message": "The request was successful.",
        "errors": [],
        "data": {
            "username": "testuser",
            "countryCode": 1,
            "phoneNumber": 9999999999,
            "email": "testuser@pocketidapp.com"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `username` : User's username
    * `countryCode` : Country code of user's phone number
    * `phoneNumber` : User's Phone number (This field is unique across users) 
    * `email` : User's email address

<br/>
#### 4.2. Update user personal information

##### Url: 
`PUT: /api/v1/me`

##### Description: 
Update user personal (email, first name, last name) information 

##### Example request:
    curl -X PUT https://pocket-id-backend.herokuapp.com/api/v1/me \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'email=testuser@pocketidapp.com'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `email` : <i>(Optional)</i> User's new email address 
* `first_name` : <i>(Optional)</i> New last name of user
* `last_name` : <i>(Optional)</i> New first name of user

##### Example response:
    {
        "message": "User information updated successfully!",
        "errors": [],
        "data": null
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>null</i>


<br/>
#### 4.3. Update user phone number

##### Url: 
`PUT: /api/v1/me`

##### Description: 
Update user phone number 

##### Example request:
    curl -X PUT https://pocket-id-backend.herokuapp.com/api/v1/me \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'country_code=1&phone_number=4379951534&otp_code=770047'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `country_code` : <i>(Required)</i> Country code of user's new phone number
* `phone_number` : <i>(Required)</i> User's new phone number (this should be unique)
* `otp_code` : <i>(Required)</i> OTP code sent to user's new phone number

##### Example response:
    {
        "message": "User information updated successfully!",
        "errors": [],
        "data": null
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>null</i>

<br/>
#### 4.4. Update password

##### Url: 
`PUT: /api/v1/me/password`

##### Description: 
Update password using old password and access token

##### Example request:
    curl -X PUT https://pocket-id-backend.herokuapp.com/api/v1/me/password \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'old_password=123456789&new_password=987654321'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `old_password` : <i>(Required)</i> Current password of user's account
* `new_password` : <i>(Required)</i> New password to set for user's account

##### Example response:
    {
        "message": "Password updated successfully!",
        "errors": [],
        "data": null
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>null</i>


<br/>
#### 4.5. Logout user

##### Url: 
`POST: /api/v1/me/logout`

##### Description: 
Logs user out of application by expiring tokens

##### Example request:
    curl -X POST https://pocket-id-backend.herokuapp.com/api/v1/me/logout \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`

##### Example response:
    {
        "message": "User logged out successfully",
        "errors": [],
        "data": null
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>null</i>

---
### 5. Wallet 
#### 5.1. Get wallet balance

##### Url: 
`GET: /api/v1/balance`

##### Description: 
Get user wallet address and their AION & ATS balances. In the current version, users can only have 1 wallet but in future multiple wallets will be supported.

##### Example request:
    curl -X GET https://pocket-id-backend.herokuapp.com/api/v1/balance \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`

##### Example response:
    {
        "message": "Balance fetched successfully!",
        "errors": [],
        "data": {
            "balance": {
                "addresses": {
                    "0xa097769c231467f65de02df701645ac8f27bbd7b428f00d2b573f751cff230d1": {
                        "total": 49.92727643353506,
                        "tokens": [
                            {
                                "balance": 49.92727643353506,
                                "name": "AION",
                                "symbol": "AION"
                            }
                        ]
                    }
                }
            },
            "defaultWallet": "0xa097769c231467f65de02df701645ac8f27bbd7b428f00d2b573f751cff230d1"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `defaultWallet` : <i>(String)</i> Default wallet address. If user has multiple wallets then this will be considered as default for receiving AION tokens.
    * `balance` : <i>(Object)</i>
        * `{{wallet address}}` : <i>(Object)</i>
            * `total` : Total AION amount user wallet contains
            * `tokens` : <i>(Array)</i>
                * `balance` : <i>(String)</i> `AION` balance or balance of ATS token
                * `name` : <i>(String)</i> `AION` or name of the ATS token
                * `symbol` : <i>(String)</i> `AION` or symbol of ATS token


<br/>
#### 5.2. Get wallet transactions

##### Url: 
`GET: /api/v1/transactions`

##### Description: 
Get user wallet transactions using access token. It only shows the transactions made through PocketID.

##### Example request:
    curl -X GET https://pocket-id-backend.herokuapp.com/api/v1/transactions \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`

##### Example response:
    {
        "message": "Transactions fetched successfully!",
        "errors": [],
        "data": {
            "transactions": [
                {
                    "fiatAmount": {
                        "currency": "cad"
                    },
                    "cryptoDetails": {
                        "name": "AION",
                        "logoUrl": "https://i.imgur.com/ffhi4AM.png",
                        "aionAmount": 1,
                        "feesAmountInAion": 0.000252
                    },
                    "status": "Success",
                    "username": "testuser",
                    "toUsername": "testuser1",
                    "txType": "Send",
                    "txHash": "0x36441a30245d100075f4fadb48482ade5a422f6985580d48650d0fea5f2704d0",
                    "createdAt": "2019-04-16T23:19:20.045Z",
                    "web3": null
                },
                {
                    "fiatAmount": {
                        "currency": "cad"
                    },
                    "cryptoDetails": {
                        "name": "AION",
                        "logoUrl": "https://i.imgur.com/ffhi4AM.png",
                        "aionAmount": 1,
                        "feesAmountInAion": 0.000252
                    },
                    "status": "Success",
                    "username": "testuser",
                    "toUsername": "testuser1",
                    "txType": "Send",
                    "txHash": "0x36441a30245d100075f4fadb48482ade5a422f6985580d48650d0fea5f2704d1",
                    "createdAt": "2019-04-16T23:19:20.045Z",
                    "web3": {
                        "nrgPrice": "0x2cb417800",
                        "blockHash": "0x3edaf48a0faae3ce74edf32155861b3f62336a7baf86d325fe3b57915b79ddfe",
                        "nrg": 21000,
                        "transactionIndex": 0,
                        "nonce": 65,
                        "input": "0x",
                        "blockNumber": 2214267,
                        "gas": 21000,
                        "from": "0xA097769C231467f65de02df701645AC8F27BBd7b428f00d2b573f751CFF230d1",
                        "to": "0xA078E890a4b99b3CCC96262C8431aA2C1274Ea3461207D07e62932CEb6c6Cb46",
                        "value": "1000000000000000000",
                        "hash": "0x36441a30245d100075f4fadb48482ade5a422f6985580d48650d0fea5f2704d1",
                        "gasPrice": "12000000000",
                        "timestamp": 1555456785
                    }
                },
            ]
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `transactions` : <i>(Array)</i> Sorted - latest first
        * `fiatAmount` : <i>(Object)</i> Fiat current details
        * `cryptoDetails` : <i>(Object)</i> Contains crypto currency, amount user bought/transferred details
        * `status` : <i>(String)</i> Transaction status (Possible values - [`Success`, `Failed`])
        * `username` : <i>(String)</i> User who is transferring amount
        * `toUsername` : <i>(String)</i> Amount transferred to user
        * `txType` : <i>(String)</i> Transaction type (Possible values - [`Buy`, `Send`])
            Buy - Amount bought by user
            Send - Amount transferred by/to another user
        * `txHash` : <i>(String)</i> Transaction hash
        * `createdAt` : <i>(String)</i> Transaction started at
        * `web3` : Either `null` or contains `object`
            `null` - Transaction isn't completed on blockchain protocol
            `object` - Information provided by blockchain protocol for transaction hash

<br/>
#### 5.3. Get send token transfer fee

##### Url: 
`GET: /api/v1/transfer/fee`

##### Description: 
Get fee for transfering tokens for AION coin & ATS tokens linked with PocketID app

##### Example request:
    curl -X GET https://pocket-id-backend.herokuapp.com/api/v1/transfer/fee \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`

##### Example response:
    {
        "message": "Gas estimates fetched successfully!",
        "errors": [],
        "data": {
            "message": "Gas estimate",
            "errors": [],
            "data": {
                "slow": {
                    "gasPriceInAion": "0.00000001"
                },
                "normal": {
                    "gasPriceInAion": "0.000000012"
                },
                "fast": {
                    "gasPriceInAion": "0.000000014"
                },
                "estimates": {
                    "AION": {
                        "unit": "aion",
                        "gas": 21000
                    }
                },
                "conversions": {
                    "aionToAmp": "0.000000001",
                    "aionToNAmp": "0.000000000000000001"
                }
            }
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `message` : <i>(String)</i>
    * `errors` : <i>(Array)</i>
    * `data` : <i>(Object)</i>
        * `slow` : <i>(Object)</i> Contains gas price in aion for a slow transaction
        * `normal` : <i>(Object)</i> Contains gas price in aion for a normal transaction
        * `fast` : <i>(Object)</i> Contains gas price in aion for a fast transaction
        * `estimates` : <i>(Object)</i>
            * `{{ AION or token name }}` : <i>(Object)</i> `AION` balance or balance of ATS token
                * `unit` : <i>(Integer)</i> Minimum gas units to be used for making a transaction
                * `gas` : <i>(String)</i> `aion` or symbol of ATS token
        * `conversions` : <i>(Object)</i> AION to amp and namp conversions chart

<br/>
#### 5.4. Transfer amount from one user's wallet to another

##### Url: 
`PUT: /api/v1/transfer`

##### Description: 
Transfer AION coin or ATS tokens from one user's wallet to another PocketID user

##### Example request:
    curl -X PUT https://pocket-id-backend.herokuapp.com/api/v1/transfer \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'to_username=testuser1&amount_in_aion=1&gas_price_in_aion=0.000000015&crypto_name=AION&gas=21000'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `to_username` : <i>(Required)</i> Transfer amount to this PocketID user
* `amount_in_aion` : <i>(Required)</i> Amount to transfer (for native token - in `AION`, for ATS token - in its own symbol).
* `gas_price_in_aion` : <i>(Required)</i> Gas price in AION. This price is paid by sender
* `gas` : <i>(Required)</i> Amount of gas to use for this transaction
* `crypto_name` : <i>(Required)</i> Name of the crypto to transfer. Use `AION` as hardcoded value for now until ATS tokens are added to PocketID. `AION` is for native token.

##### Example response:
        {
        "message": "Token amount transfer started",
        "errors": [],
        "data": {
            "transaction": {
                "hash": "0x31244a0e17a6c51844d8d39a0042aefa3e14fc311a20d3e7bfa3caabe52b4248"
            }
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `transaction` : <i>(Object)</i>
        * `hash` : transaction hash generated on blockchain protocol


---
### 6. Smart contracts 
#### 6.1. Encode smart contract method with parameters 

##### Url: 
`POST: /api/v1/contract/data-encode`

##### Description: 
Encode smart contract method with parameters

##### Example request:
    curl -X POST  https://pocket-id-backend.herokuapp.com/api/v1/contract/data-encode \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'contract_address=0xa0c90ef3cd44e6f8d9f863a6ad7cac2e0aa4c19346b3fce23758c735d5513c29&contract_abi=[{"outputs":[],"constant":false,"payable":false,"inputs":[{"name":"newString","type":"string"}],"name":"setString","type":"function"},{"outputs":[{"name":"","type":"string"}],"constant":false,"payable":false,"inputs":[],"name":"getString","type":"function"},{"outputs":[],"payable":false,"inputs":[],"name":"","type":"constructor"}]&method_name=setString(string)&method_params[]=HelloAVM'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `contract_address` : <i>(Required)</i> Contract address
* `contract_abi` : <i>(Required)</i> Generated contract ABI
* `method_name` : <i>(Required)</i> Method name with parameters or signature
* `method_params` : <i>(Required)</i> Array - Method params

##### Example response:
    {
        "message": "Data encoded successfully!",
        "errors": [],
        "data": {
            "encodedData": "0x7fcaf666000000000000000000000000000000100000000000000000000000000000000948656c6c6f41564d360000000000000000000000000000000000000000000000"
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `encodedData` : <i>(String)</i> Encoded ABI byte code to send via a call or send API

<br/>
#### 6.2. Estimate gas for smart contract method

##### Url: 
`GET: /api/v1/contract/gas-estimate`

##### Description: 
Encode smart contract method with parameters

##### Example request:
    curl -X GET 'https://pocket-id-backend.herokuapp.com/api/v1/contract/gas-estimate?contract_address=0xa0c90ef3cd44e6f8d9f863a6ad7cac2e0aa4c19346b3fce23758c735d5513c29&encoded_data=0x7fcaf666000000000000000000000000000000100000000000000000000000000000000948656c6c6f41564d360000000000000000000000000000000000000000000000' \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `contract_address` (query) : <i>(Required)</i> Contract address
* `encoded_data` (query) : <i>(Required)</i> Encoded ABI byte code

##### Example response:
    {
        "message": "Estimated gas successfully!",
        "errors": [],
        "data": 39429
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Integer)</i> Minimum amount of gas to be used for this transaction

<br/>
#### 6.3. Read from smart contract

##### Url: 
`GET: /api/v1/contract/call`

##### Description: 
Encode smart contract method with parameters

##### Example request:
    curl -X POST  https://pocket-id-backend.herokuapp.com/api/v1/contract/call \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'contract_address=0xa0c90ef3cd44e6f8d9f863a6ad7cac2e0aa4c19346b3fce23758c735d5513c29&encoded_data=0x89ea642f&decode_to=string'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `contract_address` : <i>(Required)</i> Contract address
* `encoded_data` : <i>(Required)</i> Encoded ABI byte code
* `decode_to` : <i>(Optional)</i> Response message to be decoded in. [Possible values](https://solidity.readthedocs.io/en/develop/types.html#) (Default - `string`)


##### Example response:
    {
        "message": "Read call successful!",
        "errors": [],
        "data": "abc"
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Integer)</i> Decoded response

<br/>
#### 6.4. Send a transaction to smart contract

##### Url: 
`GET: /api/v1/contract/send`

##### Description: 
Send a transaction to the smart contract. Note this can alter the smart contract state.

##### Example request:
    curl -X POST  https://pocket-id-backend.herokuapp.com/api/v1/contract/send \
        -H 'AppId: nh(DyBAlOlVWugK_ezmqN!qEHBiKYVF)' \
        -H 'Authorization: Bearer 9455a5bb52c33e8a8c31a2b7c74cf5731f519e80' \
        -H 'Content-Type: application/x-www-form-urlencoded' \
        -d 'contract_address=0xa0c90ef3cd44e6f8d9f863a6ad7cac2e0aa4c19346b3fce23758c735d5513c29&encoded_data=0x7fcaf666000000000000000000000000000000100000000000000000000000000000000948656c6c6f41564d360000000000000000000000000000000000000000000000&gas_price_in_aion=0.000000011&gas=39429'

###### Request params:
* `AppId` (header): <i>(Required)</i> Your AppId provided by PocketID
* `Authorization` (header): <i>(Required)</i> `Bearer {{access_token}}`
* `contract_address` : <i>(Required)</i> Contract address
* `encoded_data` : <i>(Required)</i> Encoded ABI byte code
* `gas_price_in_aion` : <i>(Optional)</i> Gas price in AION. This price is paid by sender. (Default = `0.00000001` (10 Amp)) 
* `gas` : <i>(Optional)</i> Amount of gas to use for this transaction. (Default = `50000`)

##### Example response:
    {
        "message": "Estimated gas successfully!",
        "errors": [],
        "data": {
            "transaction": {
                "hash": "0x8694124a11519ca22cf63831f5bce14ac6f5724931b02b73330c1354d999f271"
            }
        }
    }

###### Response params:
* `message` : <i>(String)</i> 
* `errors` : <i>(Array)</i>
* `data` : <i>(Object)</i>
    * `transaction` : <i>(Object)</i>
        * `hash` : transaction hash generated on blockchain protocol


---
## Guide

### 1. Register user
1. (Optional) Check for username availability using [username availablility api](/web#14-user-availability)
2. Send an OTP to this user's phone number using <i>[Send OTP using phone number API](/web#31-send-otp-using-phone-number)</i>. <br/>
Note: `new_user` parameter should be `1` while calling this API.
3. Call [register API](/web#11-register-a-new-user) with all the params including OTP sent to user's phone number

### 2. Login user
1. Call [login API](/web#12-login-user) with all the required parameters

### 3. Read from contract
1. Encode smart contract method with parameters using [this API](/web#61-encode-smart-contract-method-with-parameters).
2. Call [read from contract API](/web#63-read-from-smart-contract) with encoded data provided by previous API.

### 4. Write to contract
1. Encode smart contract method with parameters using [this API](/web#61-encode-smart-contract-method-with-parameters).
2. (Optional) Estimate gas price for the encoded method using [gas estimate API](/web#62-estimate-gas-for-smart-contract-method).
3. Call [send transaction contract API](/web#64-send-a-transaction-to-smart-contract) with encoded data provided by previous API.
