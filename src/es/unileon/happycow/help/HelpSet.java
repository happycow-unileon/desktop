/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.help;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSetException;

/**
 *
 * @author amdiaz8
 */
public class HelpSet {

    private final HelpBroker hb;
    private javax.help.HelpSet helpset;

    public HelpSet() {
        URL hsURL = javax.help.HelpSet.findHelpSet(getClass().getClassLoader(), "configuration/help_set.hs");
        try {
            helpset = new javax.help.HelpSet(getClass().getClassLoader(), hsURL);
        } catch (HelpSetException ex) {
            Logger.getLogger(HelpSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        hb = helpset.createHelpBroker();
    }

    public void setHelp(java.awt.Component component, String id) {
        hb.enableHelpKey(component, id, helpset);
    }

    public void setHelpOnButton(java.awt.Component component, String id) {
            hb.enableHelpOnButton(component, id, helpset);
    }
}
