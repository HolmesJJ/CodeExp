package com.example.codeexp.backend.commands;

import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.Job;

public class OfferJobCommand {
    private EnterpriseProfile enterprise;
    private Job job;
    private IndividualProfile offeredTo;

    public OfferJobCommand(EnterpriseProfile enterprise, Job job, IndividualProfile offeredTo) {
        this.enterprise = enterprise;
        this.job = job;
        this.offeredTo = offeredTo;
        enterprise.addNewOffer(offeredTo);
    }
