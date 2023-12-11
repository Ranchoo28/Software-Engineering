package it.unical.demacs.FundasticServer.PatternProxy.NormalObject;

import it.unical.demacs.FundasticServer.PatternProxy.ActionToProtect;

public class OperaComeMod implements ActionToProtect {
    @Override
    public void protectAction() {
        System.out.println("Sei Moderatore, puoi fare ciò che vuoi.");
    }
}
