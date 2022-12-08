import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//TODO use<> command (basically like import)
//TODO help<> command
public class Main {



    static Scanner sc = new Scanner(System.in);
    static boolean usingTime;
    static boolean ynInput;
    static String os = System.getProperty("os.name").toLowerCase();
    static String code = "";
    static boolean debug = false;
    static HashMap<String, Integer> intVariables = new HashMap<String, Integer>();
    static HashMap<String, String> functions = new HashMap<String, String>();
    public static void main(String[] args) {
        if(debug)
            System.out.println("DEBUG: Program started");
        try{
            FileReader flRead = new FileReader(args[0]);
            int insertedCodeInt = flRead.read();

            while(insertedCodeInt != -1){
                code += (char) insertedCodeInt;
                insertedCodeInt = flRead.read();
                if(debug)
                    System.out.println(code);
            }
            flRead.close();
        } catch (IOException e) {
            System.out.println("Ioexception");
        }
            code = code.replaceAll("@>", "ޝ");
            long fileReadDone = System.currentTimeMillis();
            int oldLen = code.length();
            for (int i = 0; i < oldLen; i++) {
                //System.out.println(insertedCode);
                //do not use useCheck in infinite loop
                useCheck(0, code);
                if (debug)
                    System.out.println("DEBUG: character: " + i);
                if (debug){
                    System.out.println("DEBUG: "+code);
                    System.out.println();
                }

                checkPrint(0, code);
                checkPrintLine(0, code);
                checkPrintInt(0, code);
                defInt(0, code);
                addCheck(0, code);
                helpCheck(0, code);
                foreverCheck(0, code);
                openInBrowserCheck(0, code);
                ynInputCheck(0, code);
                funcCheck(0, code);
                doCheck(0, code);
                //Clear <>s
                clearArrows(0, code);
                //delete character
                code = code.substring(1);



            }
            if(usingTime)
                System.out.println("Took " + (System.currentTimeMillis() - fileReadDone)+"ms from when the file had been read to run.");
        }

    static void clearArrows(int i, String insertedCode){
        if(insertedCode.indexOf("<", i) == i) {
            int startIndex = insertedCode.indexOf("<", i);
            int endIndex = insertedCode.indexOf(">", i);
            String replacement = "";
            String toBeReplaced = insertedCode.substring(startIndex+1, endIndex);

            insertedCode = insertedCode.replaceFirst(toBeReplaced, "");
        }
        if(insertedCode.indexOf(">:", i) == i) {
            int startIndex = insertedCode.indexOf(">:", i);
            int endIndex = insertedCode.indexOf(">", i+1);
            String replacement = "";
            String toBeReplaced = insertedCode.substring(startIndex+1, endIndex);

            insertedCode = insertedCode.replaceFirst(toBeReplaced, "");
        }
        if(insertedCode.indexOf(":", i) == i) {
            int startIndex = insertedCode.indexOf(":", i);
            int endIndex = insertedCode.indexOf(">", i+1);
            String replacement = "";
            String toBeReplaced = insertedCode.substring(startIndex+1, endIndex);

            insertedCode = insertedCode.replaceFirst(toBeReplaced, "");
        }


    }
    static void checkPrint(int i, String insertedCode){
        if(insertedCode.indexOf("print<", i) == i){
            if(debug){
                System.out.println("DEBUG: print detected");
            }


            int index = insertedCode.indexOf("print", i);


                if(debug)
                    System.out.println("DEBUG: \"print:\"detected");
                String toPrint = "";
                int tempIndex = index + 4;
                toPrint="";
                while (insertedCode.charAt(tempIndex+1) != '>'){

                    tempIndex++;
                    toPrint += insertedCode.charAt(tempIndex);

                }
                toPrint = toPrint.substring(1)  ;
                System.out.print(toPrint.replaceAll("@nl", "\n").replaceAll("ޝ", ">"));
            }
        }
    static void checkPrintLine(int i, String insertedCode){
        if(insertedCode.indexOf("println<", i) == i){
            if(debug){
                System.out.println("DEBUG: println detected");
            }


            int index = insertedCode.indexOf("println", i);


            if(debug)
                System.out.println("DEBUG: \"println:\"detected");
            String toPrint = "";
            int tempIndex = index + 6;
            toPrint="";
            while (insertedCode.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += insertedCode.charAt(tempIndex);

            }
            toPrint = toPrint.substring(1);
            System.out.println(toPrint.replaceAll("@nl", "\n").replaceAll("ޝ", ">"));
        }
    }
    static void checkPrintInt(int i, String insertedCode){
        if(insertedCode.indexOf("printInt<", i) == i){
            if(debug){
                System.out.println("DEBUG: printInt detected");
            }


            int index = insertedCode.indexOf("printInt", i);


            if(debug)
                System.out.println("DEBUG: \"printInt:\"detected");
            String toPrint = "";
            int tempIndex = index + 8;
            toPrint="";
            while (insertedCode.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += insertedCode.charAt(tempIndex);
                if (debug)
                    System.out.println("DEBUG: toPrint: "+toPrint);

            }
            if(debug)
                System.out.println("DEBUG: toPrint: "+toPrint);

            System.out.print(intVariables.get(toPrint));
        }
    }
    static void defInt(int i, String insertedCode){
        if(insertedCode.indexOf("integer<", i) == i){
            if(debug){
                System.out.println("DEBUG: integer: detected");
            }


            int index = insertedCode.indexOf("int", i);




                String toSetStr = "";
                int tempIndex = index + 7;
                String varName = "";

                while (insertedCode.charAt(tempIndex+1) != ':'){

                    tempIndex++;
                    varName += insertedCode.charAt(tempIndex);

                }
                while (insertedCode.charAt(tempIndex+1) != '>' ){
                    tempIndex++;
                    toSetStr += insertedCode.charAt(tempIndex);
                    toSetStr=toSetStr.replace(':', '0');
                }
                if(debug)
                    System.out.println(intVariables.get("BOB"));
                intVariables.put(varName, Integer.parseInt(toSetStr));
                if (debug)
                    System.out.println("DEBUG: Value of "+varName+" = "+intVariables.get(varName));
            }
    }
    static void foreverCheck(int i, String insertedCode) {
        if(insertedCode.indexOf("forever<", i) == i){
            if(debug)
                System.out.println("DEBUG: forever detected");
            int index = insertedCode.indexOf("forever", i);


            if(debug)
                System.out.println("DEBUG: \"forever\"detected");
            String toPrint = "";
            int tempIndex = index + 6;
            toPrint="";
            while (insertedCode.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += insertedCode.charAt(tempIndex);

            }
            long whileCounter = -9223372036854775807L;
            while (whileCounter < 9223372036854775807L){
                whileCounter++;
                if (debug)
                    System.out.println("DEBUG: forever-loop iteration number " +whileCounter);
                for (int ind = 0; ind < insertedCode.length(); ind++) {

                    checkPrint(0, code);
                    checkPrintLine(0, code);
                    checkPrintInt(0, code);
                    defInt(0, code);
                    addCheck(0, code);
                    helpCheck(0, code);
                    openInBrowserCheck(0, code);
                    ynInputCheck(0, code);
                    funcCheck(0, code);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
    }} //basically finished (could make infinite)
    static void helpCheck(int i, String insertedCode){
        if(insertedCode.indexOf("help<>", i) == i){
            System.out.println("Help coming soon");
        }
    }//
    static void openInBrowserCheck(int i, String insertedCode){
        if(insertedCode.indexOf("openInBrowser<", i) == i){
            if(debug){
                System.out.println("DEBUG: openInBrowser detected");
            }
            int index = insertedCode.indexOf("openInBrowser", i);
            String toPrint = "";
            int tempIndex = index + 12;
            toPrint="";
            while (insertedCode.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += insertedCode.charAt(tempIndex);

            }
            toPrint = toPrint.substring(1)  ;
            if(debug)
                System.out.print("DEBUG: trying to open "+toPrint.replaceAll("@nl", "\n")+ " in standard webbrowser");
            if (os.indexOf("win") >= 0){//if on Windows
                Runtime rt = Runtime.getRuntime();
                try{
                    rt.exec("rundll32 url.dll,FileProtocolHandler " + toPrint);
                }catch (Exception e){
                    System.out.println("ERROR: " + e);
                }

            }else if(os.indexOf("mac") >= 0){//if on Mac
                Runtime rt = Runtime.getRuntime();
                try{
                    rt.exec("open " + toPrint);
                }catch (Exception e){
                    System.out.println("ERROR: " + e);
                }

            }else if (os.indexOf("nix") >=0 || os.indexOf("nux") >=0){
                Runtime rt = Runtime.getRuntime();
                String[] browsers = { "google-chrome", "firefox", "mozilla", "epiphany", "konqueror",
                        "netscape", "opera", "links", "lynx" };

                StringBuffer cmd = new StringBuffer();
                for (int ind = 0; ind < browsers.length; ind++)
                    if(ind == 0)
                        cmd.append(String.format(    "%s \"%s\"", browsers[ind], toPrint));
                    else
                        cmd.append(String.format(" || %s \"%s\"", browsers[ind], toPrint));
                // If the first didn't work, try the next browser and so on
                try{
                    rt.exec(new String[] { "sh", "-c", cmd.toString() });
                }catch (Exception e){
                    System.out.println("ERROR: " + e);
                }

            }
        }
    }
    static void useCheck(int i, String insertedCode){

        if(insertedCode.indexOf("use<", i) == i){


            int index = insertedCode.indexOf("use", i);



            String toPrint = "";
            int tempIndex = index + 2;
            toPrint="";
            while (insertedCode.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += insertedCode.charAt(tempIndex);

            }
            toPrint = toPrint.substring(1).replaceAll("ޝ", ">");
            if(toPrint.equals("time")){
                usingTime = true;
                if(debug)
                    System.out.println("DEBUG: usingTime set to true");
            }else if(toPrint.equals("yn_input")){
                sc = new Scanner(System.in);
                ynInput = true;
            }

            else {
                System.out.println("ERROR: use argument is not a valid option");
            }

    }}
    static void addCheck(int i, String insertedCode){
        if(insertedCode.indexOf("add<", i) == i){
            int index = insertedCode.indexOf("add<", i);
            String option1 = "";
            String option2 = "";
            String option3 = "";
            String option4 = "";
            String toSetStr = "";
            int tempIndex = index + 4;

            while (insertedCode.charAt(tempIndex+1) != ':'){

                tempIndex++;
                option1 += insertedCode.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println(tempIndex);
                }

            }
            while (insertedCode.charAt(tempIndex+1) != ':'){

                tempIndex++;
                option2 += insertedCode.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println(tempIndex);
                }
            }
            while (insertedCode.charAt(tempIndex+1) != ':'){

                tempIndex++;
                option3 += insertedCode.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println(tempIndex);
                }

            }
            while (insertedCode.charAt(tempIndex+1) != '>'){

                tempIndex++;
                option4 += insertedCode.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println("DEBUG: "+   tempIndex);
                }
            }
            if(debug)
                System.out.println(option1 + " "+option2+ " " +option3+ " "+option4);
            while (insertedCode.charAt(tempIndex+1) != '>' ){
                tempIndex++;
                toSetStr += insertedCode.charAt(tempIndex);
                toSetStr=toSetStr.replace(':', '0');
            }

        }
    }
    static void ynInputCheck(int i, String insertedCode) {
        if (insertedCode.indexOf("yesNo<", i) == i) {
            if(debug)
                System.out.println("DEBUG: yesNo<> detected");

            int index = insertedCode.indexOf("yesNo", i);


            //read insertedCode
            String ifYes = "";
            int tempIndex = index + 5;
            String ifNo = "";

            while (insertedCode.charAt(tempIndex + 1) != ':') {

                tempIndex++;
                ifYes += insertedCode.charAt(tempIndex);

            }
            while (insertedCode.charAt(tempIndex + 1) != '>') {
                tempIndex++;
                ifNo += insertedCode.charAt(tempIndex);
                ifNo = ifNo.replaceFirst(":", "");
            }
            ifNo = ifNo+ ">";
            //System.out.println(ifYes + ifNo);
            //get user input
            String choise = sc.nextLine();
            if (choise.equals("Y") || choise.equals("y") || choise.equals("yes") || choise.equals("Yes") || choise.equals("YEs") || choise.equals("YES") || choise.equals("yEs") || choise.equals("yES") || choise.equals("yeS")) {
                for (int ind = 0; ind < ifYes.length()+5; ind++) {
                    useCheck(0, code);
                    if (debug)
                        System.out.println("DEBUG: character: " + i);
                    if (debug)
                        System.out.println();
                    checkPrint(0, code);
                    checkPrintLine(0, code);
                    checkPrintInt(0, code);
                    defInt(0, code);
                    addCheck(0, code);
                    helpCheck(0, code);
                    foreverCheck(0, code);
                    openInBrowserCheck(0, code);

                    //delete character
                    insertedCode = insertedCode.substring(1);



                        //run ifNot
                    }
                }
         else if(choise.equals("n") || choise.equals("N")|| choise.equals("no")|| choise.equals("NO")|| choise.equals("nO")){
        insertedCode = insertedCode.replaceFirst(ifYes, "");
        for (int ind = 0; ind < ifYes.length()+6; ind++) {
            useCheck(0, code);
            if (debug)
                System.out.println("DEBUG: character: " + i);
            if (debug)
                System.out.println();
            checkPrint(0, code);
            checkPrintLine(0, code);
            checkPrintInt(0, code);
            defInt(0, code);
            addCheck(0, code);
            helpCheck(0, code);
            foreverCheck(0, code);
            openInBrowserCheck(0, code);

            //delete character
            insertedCode = insertedCode.substring(1);
        }
}}}
    static void funcCheck(int i, String insertedCode){
        if (insertedCode.indexOf("func<", i) == i) {

            int index = insertedCode.indexOf("func", i);
            String name = "";
            int tempIndex = index + 4;
            String execinsertedCode =  "";

            while (insertedCode.charAt(tempIndex + 1) != ':') {

                tempIndex++;
                name += insertedCode.charAt(tempIndex);

            }
            while (insertedCode.charAt(tempIndex) != '>') {
                tempIndex++;
                execinsertedCode += insertedCode.charAt(tempIndex);

            }
            execinsertedCode = execinsertedCode.replaceFirst(":", "");
            functions.put(name, execinsertedCode);
        }
    }
    static void doCheck(int i, String insertedCode){
        if(insertedCode.indexOf("do<", i) == i){

            int index = insertedCode.indexOf("do", i);
            
            String toPrint = "";
            int tempIndex = index + 1;
            toPrint="";
            while (insertedCode.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += insertedCode.charAt(tempIndex);

            }
            toPrint = toPrint.substring(1);
            String codeToUse = functions.get(toPrint).replaceAll("ޝ", ">");
            int oldLength = code.length();
            for (int ind = 0; i < oldLength; i++) {
            useCheck(0, codeToUse);
            if (debug)
                System.out.println("DEBUG: character: " + i);
            if (debug)
                System.out.println();
            checkPrint(0, codeToUse);
            checkPrintLine(0, codeToUse);
            checkPrintInt(0, codeToUse);
            defInt(0, codeToUse);
            addCheck(0, codeToUse);
            helpCheck(0, codeToUse);
            foreverCheck(0, codeToUse);
            openInBrowserCheck(0, codeToUse);
            doCheck(0, codeToUse);
            //delete character
            codeToUse = codeToUse.substring(1);
            
        }}
    }

}

