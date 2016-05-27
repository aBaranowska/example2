package com.recglobal.example;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

@SuppressWarnings("serial")
public class TreeItemClickListener implements ItemClickListener {

    public void itemClick(ItemClickEvent event) {
        Object itemId = event.getItemId();
        if (itemId instanceof TestBean) {
            TestBean bean = (TestBean) itemId;
            System.out.println("itemClick: " + bean.getName());
        }
    }

}
