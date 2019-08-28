package com.company.sample.web.screens;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;

@SuppressWarnings("unused")
@UiController("sample_TestScreen")
@UiDescriptor("test-screen.xml")
public class TestScreen extends Screen {

    @Inject
    private UiComponents uiComponents;

    private int number;
    private boolean truth;
    private String text;

    private InstanceContainer<User> userInstance;
    private Label<String> label;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTruth(boolean truth) {
        this.truth = truth;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserInstance(InstanceContainer<User> userInstance) {
        this.userInstance = userInstance;
    }

    public void setLabel(Label<String> label) {
        this.label = label;
    }

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        addLabel("Passed number: " + number);
        addLabel("Passed boolean: " + truth);
        addLabel("Passed text: " + text);
        addLabel("Demo label text: " + label.getValue());
        addLabel("Container item: " + userInstance.getItem().getName());

        Label spacer = addLabel("");
        getWindow().setExpandRatio(spacer, 1);
    }

    private Label addLabel(String text) {
        Label<String> label = uiComponents.create(Label.TYPE_STRING);
        label.setValue(text);
        getWindow().add(label);
        return label;
    }
}