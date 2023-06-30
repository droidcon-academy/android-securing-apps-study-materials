package com.preemptive.accountmanager;

import java.util.HashMap;

public class TestAccounts {

    public static HashMap<String, TestAccount> accounts = new HashMap<>();

    static {
        TestAccount account = new TestAccount();
        account.firstName = "Foo";
        account.lastName = "Bitz";
        account.userName = "foo@example.com";
        account.accountId = "1111-2222-3333-4444";
        accounts.put(account.userName, account );

        account = new TestAccount();
        account.firstName = "Bar";
        account.lastName = "None";
        account.userName = "bar@example.com";
        account.accountId = "5555-6666-7777-8888";
        accounts.put(account.userName, account );

        account = new TestAccount();
        account.firstName = "Bobby";
        account.lastName = "Tables";
        account.userName = "null@example.com";
        account.accountId = "9999-8888-7777-6666";
        accounts.put(account.userName, account );

    }
}
