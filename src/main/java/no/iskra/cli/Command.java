// Copyright (c) 2019 Joakim Skogø Langvand (jlangvand@gmail.com)
//
// GNU GENERAL PUBLIC LICENSE
//    Version 3, 29 June 2007
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package no.iskra.cli;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Command class
 */
class Command {
  protected final String cmd;
  protected final List<String> aliases;
  protected final String helpText;
  protected final CLIFunctions functionsObject;
  protected final Method function;

  /**
   * Construct a new Command.
   *
   * @param cmd             Command name.
   * @param helpText        Description and usage.
   * @param functionsObject Instance of class holding function methods.
   * @param function        Method to be run when called.
   */
  protected Command(String cmd, String helpText, List<String> aliases, CLIFunctions functionsObject)
      throws NoSuchMethodException {
    this.cmd = cmd.substring(3).toLowerCase();
    this.aliases = aliases;
    this.helpText = helpText;
    this.functionsObject = functionsObject;
    this.function = functionsObject.getClass().getDeclaredMethod(cmd, List.class, Map.class);
    aliases = new ArrayList<String>();
  }

  /**
   * Run command method and return the result as a String.
   *
   * @param params Parameters(s)/subcommand(s).
   * @param args   Arguments to be passed to the method.
   * @return The result.
   */
  protected String exec(List<String> params, List<String> flags, Map<String, String> args)
      throws IllegalAccessException, InvocationTargetException {
    return (String) function.invoke(functionsObject, params, flags, args);
  }

  /**
   * Add an alias the Command should respond to.
   *
   * @param alias Alternative name.
   */
  protected void addAlias(String alias) {
    aliases.add(alias);
  }

  /**
   * Chech whether the command responds to the given name.
   *
   * @param cmd Command name.
   * @return True if command responds to the name.
   */
  protected boolean respondsToCommand(String cmd) {
    return this.cmd.equals(cmd) || aliases.contains(cmd);
  }
}
