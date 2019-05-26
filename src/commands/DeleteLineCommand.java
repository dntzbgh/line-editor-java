package commands;

import core.Editor;

import java.util.ArrayList;

public class DeleteLineCommand extends Command implements ISpecificExecution{
    private static final char COMMAND_OPTION='d';
    private static final String COMMAND_DESCRIPTION=Command.COMMAND_STRING_DELETE;
    private static final boolean COMMAND_REQUIRES_PARAMETERS =false;

    public DeleteLineCommand() {
        super(COMMAND_OPTION, null, COMMAND_DESCRIPTION, COMMAND_REQUIRES_PARAMETERS);
    }

    @Override
    public void executeCommand(Editor editor) throws Exception{
        this.executeAndClearParameters(editor);
    }

    @Override
    public void executeAndClearParameters(Editor editor) {
        editor.removeLine();
    }
}
