package com.example.isuusclient.controller;

import com.example.isuusclient.service.AssessmenService;
import com.example.isuusclient.service.ErrorAlertService;
import com.example.isuusclient.service.GroupService;

public class AssessmenListController {
    private final ErrorAlertService alertService = new ErrorAlertService();
    private final AssessmenService service = new AssessmenService();
    private boolean addFlag = true;
}
