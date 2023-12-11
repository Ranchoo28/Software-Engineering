package it.unical.demacs.FundasticServer.PatternProxy.NormalObject;

import it.unical.demacs.FundasticServer.PatternProxy.ActionToProtect;

public class PubblicaProgetto implements ActionToProtect {
    @Override
    public void protectAction() {
        System.out.println("Sei publisher, puoi pubblicare.");
    }
}
