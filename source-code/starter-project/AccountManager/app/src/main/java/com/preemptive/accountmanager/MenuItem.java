package com.preemptive.accountmanager;

import android.view.View;

public class MenuItem {

    public String name;
    public Boolean enabled;
    public View.OnClickListener onClick;

    public MenuItem(String name, Boolean enabled, View.OnClickListener onClick) {
        this.name = name;
        this.enabled = enabled;
        this.onClick = onClick;
    }

    @Override
    public String toString(){
        return name;
    }
}
