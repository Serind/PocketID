package com.serindlabs.androidsample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.serindlabs.androidsample.databinding.ActivityMainBinding;
import com.serindlabs.pocketid.sdk.PocketIDSdk;
import com.serindlabs.pocketid.sdk.base.PocketIDListener;
import com.serindlabs.pocketid.sdk.constants.PocketIDArgumentKey;
import com.serindlabs.pocketid.sdk.constants.PocketIDEventType;
import com.serindlabs.pocketid.sdk.constants.PocketIDRequestCode;
import com.serindlabs.pocketid.sdk.contract.ContractHandler;
import com.serindlabs.pocketid.sdk.domain.account.BalanceResponse;
import com.serindlabs.pocketid.sdk.utils.PocketIDUiUtil;

public class MainActivity extends AppCompatActivity implements PocketIDListener {

    private static final String ABI = "[{\"outputs\":[],\"constant\":false,\"payable\":false,\"inputs\":[{\"name\":\"newString\",\"type\":\"string\"}],\"name\":\"setString\",\"type\":\"function\"},{\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":false,\"payable\":false,\"inputs\":[],\"name\":\"getString\",\"type\":\"function\"},{\"outputs\":[],\"payable\":false,\"inputs\":[],\"name\":\"\",\"type\":\"constructor\"}]";
    private static final String CONTRACT = "0xa0c90ef3cd44e6f8d9f863a6ad7cac2e0aa4c19346b3fce23758c735d5513c29";
    private static final String GET_STRING_METHOD = "getString";
    private static final String SET_STRING_METHOD = "setString";

    private ActivityMainBinding binding;
    private String setStringEncoded;
    private String getStringEncoded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        PocketIDSdk.getInstance().registerListener(this);
        PocketIDSdk.getInstance().getContractHandler().init(ABI, CONTRACT);
        init();
    }

    private void init() {
        setUiState();
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        MenuItem logout = binding.toolbar.getMenu().add("Logout");
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PocketIDSdk.getInstance().logout(MainActivity.this);
                return true;
            }
        });
        binding.updateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PocketIDSdk.getInstance().getContractHandler()
                        .encode(MainActivity.this, SET_STRING_METHOD, binding.updateValueEdit.getText().toString());
            }
        });
        binding.refreshValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentValue();
            }
        });
    }

    private void updateValue(String string) {
        binding.currentValue.setText(string);
    }

    private void login() {
        PocketIDSdk.getInstance().login(this, true);
    }

    private void setUiState() {
        if (PocketIDSdk.getInstance().requiresLogin()) {
            binding.stateLoggedIn.setVisibility(View.GONE);
            binding.stateNotLoggedIn.setVisibility(View.VISIBLE);
            binding.balance.setText("");
            binding.currentValue.setText("");
            binding.updateValueEdit.setText("");
            binding.updateValueStatus.setText("");
        } else {
            binding.stateLoggedIn.setVisibility(View.VISIBLE);
            binding.stateNotLoggedIn.setVisibility(View.GONE);
            binding.balance.setText("");
            binding.currentValue.setText("");
            binding.updateValueEdit.setText("");
            binding.updateValueStatus.setText("");
            setupLoggedInState();
        }
    }

    private void setupLoggedInState() {
        BalanceResponse balanceResponse = PocketIDSdk.getInstance().getBalance();
        if (balanceResponse != null) {
            updateBalance(balanceResponse);
        } else {
            PocketIDSdk.getInstance().fetchBalance();
        }
        getCurrentValue();
    }

    private void getCurrentValue() {
        PocketIDSdk.getInstance().getContractHandler().encode(this, GET_STRING_METHOD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PocketIDRequestCode.AUTHENTICATION && resultCode == RESULT_OK) {
            setUiState();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onEvent(String s, Bundle bundle) {
        switch (s) {
            case PocketIDEventType.EVENT_LOGGED_OUT:
                onLoggedOut();
                break;
            case PocketIDEventType.EVENT_GET_BALANCE_SUCCESS:
                updateBalance(PocketIDSdk.getInstance().getBalance());
                break;
            case PocketIDEventType.EVENT_TR_ENCODE_SUCCESS:
                encodedDataFetched(bundle);
                break;
            case PocketIDEventType.EVENT_TR_CALL_SUCCESS:
                callRequestSuccess(bundle);
                break;
            case PocketIDEventType.EVENT_TR_SEND_SUCCESS:
                sendRequestSuccess(bundle);
        }
    }

    private void sendRequestSuccess(Bundle bundle) {
        String trHash = bundle.getString(PocketIDArgumentKey.KEY_TRANSACTION_HASH);
        binding.updateValueStatus.setText("Update Request Submitted. Press refresh on current value.\n"
                + "Transaction Hash: " + trHash);
    }

    private void callRequestSuccess(Bundle bundle) {
        updateValue(bundle.getString(PocketIDArgumentKey.KEY_DATA_STRING));
    }

    private void encodedDataFetched(Bundle bundle) {
        String methodName = bundle.getString(PocketIDArgumentKey.KEY_METHOD_NAME);
        if (methodName.equals(GET_STRING_METHOD)) {
            getStringEncodeDataFetched(bundle);
        } else if (methodName.equals(SET_STRING_METHOD)) {
            setStringEncodeDataFetched(bundle);
        }
    }

    private void setStringEncodeDataFetched(Bundle bundle) {
        setStringEncoded = bundle.getString(PocketIDArgumentKey.KEY_ENCODED_DATA);
        PocketIDSdk.getInstance().getContractHandler().send(this, setStringEncoded);
    }

    private void getStringEncodeDataFetched(Bundle bundle) {
        getStringEncoded = bundle.getString(PocketIDArgumentKey.KEY_ENCODED_DATA);
        PocketIDSdk.getInstance().getContractHandler().call(this, getStringEncoded);
    }

    private void onLoggedOut() {
        setUiState();
    }

    @SuppressLint("SetTextI18n")
    private void updateBalance(BalanceResponse balanceResponse) {
        binding.balance.setText(PocketIDUiUtil.formatTokenString(balanceResponse.getDefaultWallet().getTotal()) + " AION");
    }
}
