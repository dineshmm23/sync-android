/*
 * Copyright © 2016 IBM Corp. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.cloudant.android;

import android.content.Context;

import com.cloudant.sync.replication.WifiPeriodicReplicationReceiver;
import com.cloudant.sync.replication.PeriodicReplicationService;

public class TestReplicationService extends PeriodicReplicationService {

    private static final String TASKS_DATASTORE_NAME = "tasks";
    private static final String DATASTORE_MANGER_DIR = "data";
    private static final String TAG = "TestReplicationService";

    /** The period between replications for our test PeriodicReplicationService when there are no
     * components bound to the service. The default is 120 seconds (2 minutes), but the value
     * may be modified by calling {@link #setUnboundIntervalSeconds(int)}.
     */
    private int mUnboundIntervalSeconds = 120;

    class TestReceiver extends WifiPeriodicReplicationReceiver<TestReplicationService> {
        public TestReceiver() {
            super(TestReplicationService.class);
        }
    }

    public TestReplicationService() {
        super(TestReceiver.class);
    }

    /* Only used for test purposes. */
    public TestReplicationService(Context baseContext) {
        this();
        attachBaseContext(baseContext);
    }

    protected int getBoundIntervalInSeconds() {
        return 60;
    }

    protected int getUnboundIntervalInSeconds() {
        return mUnboundIntervalSeconds;
    }

    protected boolean startReplicationOnBind() {
        return true;
    }

    public void setUnboundIntervalSeconds(int seconds) {
        mUnboundIntervalSeconds = seconds;
    }
}
