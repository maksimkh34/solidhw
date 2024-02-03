import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Logger {


    LoggerType thisLoggerType;
    BufferedWriter loggerOutputFile;

    public enum LoggerType {
        File,
        Console,
        FileAndConsole,
        Skip
    }

    public enum MsgType {
        Error,
        Info,
        System
    }

    private void createFile(String fileName) {
        if (Objects.equals(fileName, "")) fileName = new SimpleDateFormat("yyyy.MM.dd_HH.mm")
                .format(Calendar.getInstance().getTime()) + ".log";
        try {
            loggerOutputFile = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Error creating logger file: " + e.getLocalizedMessage());
        }
    }

    Logger(LoggerType loggerType, String fileName) {
        this.thisLoggerType = loggerType;
        if (loggerType == LoggerType.File || loggerType == LoggerType.FileAndConsole) {
            createFile(fileName);
        }
        this.log("[class] Hello from Logger! ", MsgType.System);
    }

    Logger(LoggerType loggerType) {
        this(loggerType, "");
    }

    public void log(String text, MsgType msgType) {
        if (this.thisLoggerType == LoggerType.Skip) return;

        String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        String color;
        String outputString = switch (msgType) {
            case Error:
                color = "\u001B[31m";
                yield "[" + time + "] [ERR] " + text;

            case Info:
                color = "\u001B[34m";
                yield "[" + time + "] [INF] " + text;

            case System:
                color = "\u001B[32m";
                yield "[" + time + "] [SYS] " + text;
        };


        if (this.thisLoggerType != LoggerType.Console) {
            try {
                loggerOutputFile.write(outputString + '\n');
            } catch (IOException e) {
                System.out.println("Logger error: " + e.getLocalizedMessage());
            }
            if(this.thisLoggerType == LoggerType.File) return;
        }

        System.out.println(color + outputString);
    }

    public void exitLogger() {
        try {
            this.log("[class] Bye! ", MsgType.System);
            if(this.thisLoggerType == LoggerType.File || this.thisLoggerType == LoggerType.FileAndConsole)
                this.loggerOutputFile.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getLocalizedMessage());
        }
    }

    public void switchType(LoggerType newLoggerType, String fileName) {
        if (newLoggerType != this.thisLoggerType) {
            this.thisLoggerType = newLoggerType;
            if(newLoggerType == LoggerType.File || newLoggerType == LoggerType.FileAndConsole) {
                createFile(fileName);
            }
        }
    }

    public void switchType(LoggerType newLoggerType) {
        switchType(newLoggerType, "");
    }
}
