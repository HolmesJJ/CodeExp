package com.example.codeexp.backend.model;

public class Administrator {
    List<EnterpriseProfile> enterprises;
    List<IndividiualPRofile> individuals;

    void addNewIndividual(IndividualProfile individual) {
        individuals.add(individual);
    }

    void removeIndividual(IndividualProfile individual) {
        individuals.remove(individual);
    }

    void addNewEnterprise(EnterpriseProfile enterprise) {
        enterprises.add(enterprise);
    }

    void removeEnterprise(EnterpriseProfile enterprise) {
        enterprises.remove(enterprise);
    }
}
