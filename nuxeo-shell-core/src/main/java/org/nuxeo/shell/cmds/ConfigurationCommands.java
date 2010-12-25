/*
 * (C) Copyright 2006-2010 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     bstefanescu
 */
package org.nuxeo.shell.cmds;

import org.nuxeo.shell.CommandRegistry;
import org.nuxeo.shell.Shell;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
public class ConfigurationCommands extends CommandRegistry {

    public final static ConfigurationCommands INSTANCE = new ConfigurationCommands();

    public ConfigurationCommands() {
        super(GlobalCommands.INSTANCE, "config");
        addAnnotatedCommand(Settings.class);
    }

    @Override
    public String getTitle() {
        return "Configuration Commands";
    }

    @Override
    public String getDescription() {
        return "Commands for configuring the shell.";
    }

    @Override
    public String getPrompt(Shell shell) {
        return "config> ";
    }
}
