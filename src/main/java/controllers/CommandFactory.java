package controllers;

import controllers.command.Command;

class CommandFactory {

    private CommandFactory() {
    }

    static Command getCommand(String commendKey) {
        Command command = CommandEnum.getCommand(commendKey);
        return command;
    }
}
