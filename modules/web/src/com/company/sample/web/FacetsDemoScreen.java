package com.company.sample.web;

import com.company.sample.web.screens.TestScreen;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;

@UiController("sample_FacetsDemoScreen")
@UiDescriptor("facets-demo-screen.xml")
public class FacetsDemoScreen extends Screen {

    @Inject
    protected Notification notification;

    @Inject
    protected MessageDialog messageDialog;

    @Inject
    protected OptionDialog optionDialog;

    @Inject
    protected ScreenFacet<TestScreen> testScreen;

    @Inject
    protected Notifications notifications;

    @Inject
    protected UserSession userSession;

    @Inject
    protected InstanceContainer<User> userDc;

    @Subscribe
    private void onInit(InitEvent event) {
        userDc.setItem(userSession.getCurrentOrSubstitutedUser());
    }

    @Subscribe("showNotification")
    private void onShowNotificationClick(Button.ClickEvent event) {
        notification.show();
    }

    @Subscribe("showMsgDialog")
    private void onShowMsgDialogClick(Button.ClickEvent event) {
        messageDialog.show();
    }

    @Subscribe("showOptDialog")
    private void onShowOptDialogClick(Button.ClickEvent event) {
        optionDialog.show();
    }

    @Subscribe("showScreen")
    private void onShowScreenClick(Button.ClickEvent event) {
        TestScreen screen = testScreen.show();

        screen.addAfterCloseListener(evt ->
                notifications.create(Notifications.NotificationType.HUMANIZED)
                        .withCaption("TestScreen closed")
                        .show());
    }

    @Install(subject = "actionHandler", to = "optionDialog.dialogOk")
    private void onDialogOk(OptionDialog.DialogActionPerformedEvent evt) {
        notifications.create(Notifications.NotificationType.HUMANIZED)
                .withCaption("Dialog Action Performed")
                .withDescription("Action ID: " + evt.getDialogAction().getId())
                .show();
    }

    @Install(subject = "actionHandler", to = "optionDialog.dialogCancel")
    private void onDialogCancel(OptionDialog.DialogActionPerformedEvent evt) {
        notifications.create(Notifications.NotificationType.HUMANIZED)
                .withCaption("Dialog Action Performed")
                .withDescription("Action ID: " + evt.getDialogAction().getId())
                .show();
    }
}