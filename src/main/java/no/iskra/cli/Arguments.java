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

import java.util.Map;
import java.util.List;

class Arguments {
  private Map<String, String> kv;
  private List<String> subCommands;
  private List<Character> flags;

  public String getValue(String key) {
    return this.kv.get(key);
  }

  public boolean flagIsSet(Character c) {
    return this.flags.contains(c);
  }

  public List<String> getSubcommands() {
    return this.subCommands;
  }

  public boolean subCommandIsPresent(String subCommand) {
    return this.subCommands.contains(subCommand);
  }

  protected void addKeyValuePair(String key, String value) {
    kv.putIfAbsent(key, value);
  }

  protected void addSubCommand(String subCommand) {
    subCommands.add(subCommand);
  }
}
