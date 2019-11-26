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
      implementation 'no.iskra:CLITool:2.0+'
    }

## Usage

Methods with the `@Cmd(name = "commandname")` annotation can be run as commands in the created CLI. All methods you want to call from the CLI must reside in the same class. As for now, they all need to accept the parameters `List<String> params, List<String> flags, Map<String, String> args`, nothing more, nothing less. They also need to return a String, which should contain a human readable result of the operation.

The parameter `List<String> flags` is, for now, ignored. This feature will be implemented soon.

Example:

    // App.java

    public static void main(String[] args) {
      CLI<App> cli = new CLI<App>(new App());
      cli.cli();
    }

    @Cmd(name = "hello")
    String helloWorldExampleCommand(List<String> params, List<String> flags, Map<String, String> args) {
      return "Hello, World!";
    }

The CLI would now accept the command 'hello', to which it would respond with the output of `helloWorldExampleCommand`.

`CLI.parse(String in)` is public and can be used to parse input without starting the CLI for whatever reason, like passing command line arguments from 'main', or implementing your own user input method.

The CLI will accept any input after the command. Single words will be interpreted as a `param`. `--key=value` and `--key2="value 2"` will be passed as key, value pairs in `args`. Flags are not yet used, so `-f` will simply be ignored. The parameter is included for future backwards compatibility.
