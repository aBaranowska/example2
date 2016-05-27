package com.recglobal.example;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Tree.TargetItemAllowsChildren;

@SuppressWarnings("serial")
public class TreeDropHandler implements DropHandler {

    public void drop(DragAndDropEvent event) {
    }

    public AcceptCriterion getAcceptCriterion() {
        return TargetItemAllowsChildren.get();
    }

}
