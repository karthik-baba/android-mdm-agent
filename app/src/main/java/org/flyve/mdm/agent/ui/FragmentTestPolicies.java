/*
 * Copyright Teclib. All rights reserved.
 *
 * Flyve MDM is a mobile device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @copyright Copyright Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/android-mdm-agent
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */

package org.flyve.mdm.agent.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import org.flyve.mdm.agent.R;
import org.flyve.mdm.agent.data.PoliciesData;
import org.flyve.mdm.agent.services.PoliciesConnectivity;
import org.flyve.mdm.agent.services.PoliciesDeviceManager;
import org.flyve.mdm.agent.utils.ConnectionHTTP;
import org.flyve.mdm.agent.utils.FlyveLog;
import org.flyve.mdm.agent.utils.Helpers;
import org.flyve.mdm.agent.utils.StorageFolder;

public class FragmentTestPolicies extends Fragment {

    private PoliciesData cache;
    private PoliciesDeviceManager mdm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test_policies, container, false);

        cache = new PoliciesData(FragmentTestPolicies.this.getContext());
        mdm = new PoliciesDeviceManager(FragmentTestPolicies.this.getContext());

        Switch swGPS = v.findViewById(R.id.swGPS);

        swGPS.setChecked(cache.getDisableGPS());
        swGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableGPS(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableGps(isChecked);
                }
            }
        });

        Switch swAirplane = v.findViewById(R.id.swAirplane);
        swAirplane.setChecked(cache.getDisableAirplaneMode());
        swAirplane.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableAirplaneMode(isChecked);
                PoliciesConnectivity.disableAirplaneMode(isChecked);
            }
        });

        Switch swBluetooth = v.findViewById(R.id.swBluetooth);
        swBluetooth.setChecked(cache.getDisableBluetooth());
        swBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableBluetooth(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableBluetooth(isChecked);
                }
            }
        });

        Switch swWifi = v.findViewById(R.id.swWifi);
        swWifi.setChecked(cache.getDisableWifi());
        swWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableWifi(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableWifi(isChecked);
                }
            }
        });

        Switch swNFC = v.findViewById(R.id.swNFC);
        swNFC.setChecked(cache.getDisableNFC());
        swNFC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableNFC(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableNFC(isChecked);
                }
            }
        });

        Switch swHostpot = v.findViewById(R.id.swHostpot);
        swHostpot.setChecked(cache.getDisableHostpotTethering());
        swHostpot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableHostpotTethering(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableHostpotTethering(isChecked);
                }
            }
        });

        Switch swMobileLine = v.findViewById(R.id.swMobileLine);
        swMobileLine.setChecked(cache.getDisableMobileLine());
        swMobileLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableMobileLine(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableMobileLine(isChecked);
                }
            }
        });

        Button btnLock = v.findViewById(R.id.btnLock);
        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdm.lockScreen();
            }
        });

        Switch swDisableCamera = v.findViewById(R.id.swDisableCamera);
        swDisableCamera.setChecked(cache.getDisableCamera());
        swDisableCamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setDisableCamera(isChecked);
                mdm.disableCamera(isChecked);
            }
        });

        Switch swStorageEncryptionDevice = v.findViewById(R.id.swStorageEncryptionDevice);
        swStorageEncryptionDevice.setChecked(cache.getStorageEncryption());
        swStorageEncryptionDevice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setStorageEncryption(isChecked);
                mdm.storageEncryptionDevice(isChecked);
            }
        });

        Button btnReboot = v.findViewById(R.id.btnReboot);
        btnReboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdm.reboot();
            }
        });

        Button btnClearMQTT = v.findViewById(R.id.btnCleatMQTT);
        btnClearMQTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Helpers.deleteMQTTCache(FragmentTestPolicies.this.getContext());
                } catch (Exception ex) {
                    FlyveLog.e(ex.getMessage());
                }
            }
        });

        final EditText edtPasswordLength = v.findViewById(R.id.edtPasswordLength);
        final EditText edtPasswordMinimumLetters = v.findViewById(R.id.edtPasswordMinimumLetters);
        final EditText edtPasswordMinimumLowerCase =  v.findViewById(R.id.edtPasswordMinimumLowerCase);
        final EditText edtPasswordMinimumUpperCase =  v.findViewById(R.id.edtPasswordMinimumUpperCase);
        final EditText edtPasswordMinimumNonLetter = v.findViewById(R.id.edtPasswordMinimumNonLetter);
        final EditText edtPasswordMinimumNumeric = v.findViewById(R.id.edtPasswordMinimumNumeric);
        final EditText edtPasswordMinimumSymbols = v.findViewById(R.id.edtPasswordMinimumSymbols);
        final EditText edtMaximumFailedPasswordsForWipe = v.findViewById(R.id.edtMaximumFailedPasswordsForWipe);
        final EditText edtMaximumTimeToLock = v.findViewById(R.id.edtMaximumTimeToLock);

        Button btnPassword = v.findViewById(R.id.btnPassword);
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int minimumLength = Integer.parseInt(edtPasswordLength.getText().toString());
                int minimumLetters = Integer.parseInt(edtPasswordMinimumLetters.getText().toString());
                int minimumUpperCase = Integer.parseInt(edtPasswordMinimumUpperCase.getText().toString());
                int minimumLowerCase = Integer.parseInt(edtPasswordMinimumLowerCase.getText().toString());
                int minimumNonLetter = Integer.parseInt(edtPasswordMinimumNonLetter.getText().toString());
                int minimumNumeric = Integer.parseInt(edtPasswordMinimumNumeric.getText().toString());
                int minimumSymbols = Integer.parseInt(edtPasswordMinimumSymbols.getText().toString());
                int maximumFailedPasswordsForWipe = Integer.parseInt(edtMaximumFailedPasswordsForWipe.getText().toString());
                int maximumTimeToLock = Integer.parseInt(edtMaximumTimeToLock.getText().toString());

                cache.setPasswordMinimumLength(minimumLength);
                cache.setPasswordMinimumLetters(minimumLetters);
                cache.setPasswordMinimumUpperCase(minimumUpperCase);
                cache.setPasswordMinimumLowerCase(minimumLowerCase);
                cache.setPasswordMinimumNonLetter(minimumNonLetter);
                cache.setPasswordMinimumNumeric(minimumNumeric);
                cache.setPasswordMinimumSymbols(minimumSymbols);
                cache.setMaximumFailedPasswordsForWipe(maximumFailedPasswordsForWipe);
                cache.setMaximumTimeToLock(maximumTimeToLock);

                mdm.setPasswordLength(minimumLength);
                mdm.setPasswordMinimumLetters(minimumLetters);
                mdm.setPasswordMinimumUpperCase(minimumUpperCase);
                mdm.setPasswordMinimumLowerCase(minimumLowerCase);
                mdm.setPasswordMinimumNonLetter(minimumNonLetter);
                mdm.setPasswordMinimumNumeric(minimumNumeric);
                mdm.setPasswordMinimumSymbols(minimumSymbols);
                mdm.setMaximumFailedPasswordsForWipe(maximumFailedPasswordsForWipe);
                mdm.setMaximumTimeToLock(maximumTimeToLock);
            }
        });

        Button btnPasswordEnable = v.findViewById(R.id.btnPasswordEnable);
        btnPasswordEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cache.setPasswordEnabled(true);
                mdm.enablePassword();
            }
        });

        Button btnDownloadFile = v.findViewById(R.id.btnDownloadFile);
        btnDownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        //
                        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/CHANGELOG.md";
                        FlyveLog.d(path);
                        ConnectionHTTP.getSyncFile("https://raw.githubusercontent.com/flyve-mdm/android-mdm-agent/develop/CHANGELOG.md", path);
                    }
                }).start();


            }
        });

        Button btnDownloadAPK = v.findViewById(R.id.btnDownloadAPK);
        btnDownloadAPK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = new StorageFolder(getContext()).getDocumentsDir() + "/flyve-apk.apk";
                FlyveLog.d(path);
                ConnectionHTTP.getSyncFile("https://f-droid.org/repo/org.flyve.inventory.agent_37960.apk", path);
            }
        });

        Button btnEnablePassword = v.findViewById(R.id.btnEnablePassword);
        btnEnablePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helpers.sendToNotificationBar(getContext(), 1009, "MDM Agent", "Please create a new password", true, MainActivity.class, "TestPolicies");
            }
        });

        Button btnInstallSilently = v.findViewById(R.id.btnInstallSilently);
        btnInstallSilently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = new StorageFolder(FragmentTestPolicies.this.getContext()).getDownloadDir() + "/test.apk";
                Helpers.installApkSilently(path);
            }
        });

        return v;
    }
}
