[![Release](https://jitpack.io/v/no.iskra/CLI-Tool.svg)](https://jitpack.io/#no.iskra/CLI-Tool)

# CLI Tool

A library for building a simple CLI for terminal Java applications. Don't expect a full fledged terminal emulator. Do expect a (hopefully) simple and intuitive way to build a basic CLI.

The code is a little unpolished still, but I'll try to tidy it up and improve the documentation a bit.

Javadoc hosted by Jitpack can be found [here](https://javadoc.jitpack.io/no/iskra/CLI-Tool/1.0.2/javadoc/)

## Importing

The library is hosted on [Jitpack](https://jitpack.io/#no.iskra/CLI-Tool). Gradle example:

    allprojects {
      repositories {
        jcenter()
        maven { url "https://jitpack.io" }
      }
    }
    dependencies {
      implementation 'no.iskra:CLITool:v2.0'
    }

## Usage

* Create a class which extends `CLIFunctions`.
* Declare any fields your "command methods" need to access.
* Declare one method for every main "function", or command, you want to make available in the CLI.
  * Every "command method" _must_ be prefixed with `cmd`.
  * All `cmd`-prefixed methods _must_ have the parameters `List<String> params, List<String> flags, Map<String, String> args` and return a String, which will be passed back to the CLI, then printed to System.out.
  * Example: the method `cmdList(List<String> params, List<String> flags, Map<String, String> options) { ... }` will represent a command `list`.
* Instantiate a new CLI, passing it an instance of your 'functions' class: `myCli = new CLI(new myFunctions())`.
* Start the CLI with `myCli.cli()`. This method is blocking. `exit` will close the CLI.

`CLI.parse(String in)` is public and can be used to parse input without starting the CLI for whatever reason, like passing command line arguments from 'main', or implementing your own user input method.

The CLI will accept any input after the command. Single words will be interpreted as a `param`. `--key=value` and `--key2="value 2"` will be passed as key, value pairs in `args`. Flags are not yet used, so `-f` will simply be ignored. The parameter is included for future backwards compatibility.
