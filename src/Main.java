import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//TODO use<> command (basically like import)
//TODO help<> command
//TODO yesNo<ifYes:ifNo>
public class Main {



    static Scanner sc;
    static boolean usingTime;
    static boolean ynInput;
    static String os = System.getProperty("os.name").toLowerCase();
    static String code = "";
    static boolean debug = false;
    static HashMap<String, Integer> intVariables = new HashMap<String, Integer>();
    public static void main(String[] args) {

        if(debug)
            System.out.println("DEBUG: Program started");
        try{
            FileReader flRead = new FileReader(args[0]);
            int codeInt = flRead.read();

            while(codeInt != -1){
                code += (char) codeInt;
                codeInt = flRead.read();
                if(debug)
                    System.out.println(code);
            }
            flRead.close();
        } catch (IOException e) {
            System.out.println("Ioexception");
        }
            code = code.replaceAll("@>", "ޝ");
            long fileReadDone = System.currentTimeMillis();
            for (int i = 0; i < code.length(); i++) {

                useCheck(0);
                if (debug)
                    System.out.println("DEBUG: character: " + i);
                if (debug)
                    System.out.println();
                checkPrint(0);
                checkPrintLine(0);
                checkPrintInt(0);
                defInt(0);
                addCheck(0);
                helpCheck(0);
                foreverCheck(0);
                openInBrowserCheck(0);

                //Clear <>s
                clearArrows(0);
                //delete character
                code = code.substring(1);



            }
            if(usingTime)
                System.out.println("Took " + (System.currentTimeMillis() - fileReadDone)+"ms from when the file had been read to run.");
        }

    static void clearArrows(int i){
        if(code.indexOf("<", i) == i) {
            int startIndex = code.indexOf("<", i);
            int endIndex = code.indexOf(">", i);
            String replacement = "";
            String toBeReplaced = code.substring(startIndex+1, endIndex);

            code = code.replaceFirst(toBeReplaced, "");
        }

    }
    static void checkPrint(int i){
        if(code.indexOf("print<", i) == i){
            if(debug){
                System.out.println("DEBUG: print detected");
            }


            int index = code.indexOf("print", i);


                if(debug)
                    System.out.println("DEBUG: \"print:\"detected");
                String toPrint = "";
                int tempIndex = index + 4;
                toPrint="";
                while (code.charAt(tempIndex+1) != '>'){

                    tempIndex++;
                    toPrint += code.charAt(tempIndex);

                }
                toPrint = toPrint.substring(1)  ;
                System.out.print(toPrint.replaceAll("@nl", "\n").replaceAll("ޝ", ">"));
            }
        }
    static void checkPrintLine(int i){
        if(code.indexOf("println<", i) == i){
            if(debug){
                System.out.println("DEBUG: println detected");
            }


            int index = code.indexOf("println", i);


            if(debug)
                System.out.println("DEBUG: \"println:\"detected");
            String toPrint = "";
            int tempIndex = index + 6;
            toPrint="";
            while (code.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += code.charAt(tempIndex);

            }
            toPrint = toPrint.substring(1);
            System.out.println(toPrint.replaceAll("@nl", "\n").replaceAll("ޝ", ">"));
        }
    }
    static void checkPrintInt(int i){
        if(code.indexOf("printInt<", i) == i){
            if(debug){
                System.out.println("DEBUG: printInt detected");
            }


            int index = code.indexOf("printInt", i);


            if(debug)
                System.out.println("DEBUG: \"printInt:\"detected");
            String toPrint = "";
            int tempIndex = index + 8;
            toPrint="";
            while (code.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += code.charAt(tempIndex);
                if (debug)
                    System.out.println("DEBUG: toPrint: "+toPrint);

            }
            if(debug)
                System.out.println("DEBUG: toPrint: "+toPrint);

            System.out.print(intVariables.get(toPrint));
        }
    }
    static void defInt(int i){
        if(code.indexOf("integer<", i) == i){
            if(debug){
                System.out.println("DEBUG: integer: detected");
            }


            int index = code.indexOf("int", i);




                String toSetStr = "";
                int tempIndex = index + 7;
                String varName = "";

                while (code.charAt(tempIndex+1) != ':'){

                    tempIndex++;
                    varName += code.charAt(tempIndex);

                }
                while (code.charAt(tempIndex+1) != '>' ){
                    tempIndex++;
                    toSetStr += code.charAt(tempIndex);
                    toSetStr=toSetStr.replace(':', '0');
                }
                if(debug)
                    System.out.println(intVariables.get("BOB"));
                intVariables.put(varName, Integer.parseInt(toSetStr));
                if (debug)
                    System.out.println("DEBUG: Value of "+varName+" = "+intVariables.get(varName));
            }
    }
    static void foreverCheck(int i) {
        if(code.indexOf("forever<", i) == i){
            if(debug)
                System.out.println("DEBUG: forever detected");
            int index = code.indexOf("forever", i);


            if(debug)
                System.out.println("DEBUG: \"forever\"detected");
            String toPrint = "";
            int tempIndex = index + 6;
            toPrint="";
            while (code.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += code.charAt(tempIndex);

            }
            long whileCounter = -9223372036854775807L;
            while (whileCounter < 9223372036854775807L){
                whileCounter++;
                if (debug)
                    System.out.println("DEBUG: forever-loop iteration number " +whileCounter);
                for (int ind = 0; ind < code.length(); ind++) {
                    checkPrint(ind);
                    checkPrintLine(ind);
                    checkPrintInt(ind);
                    defInt(ind);
                    addCheck(ind);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
    }} //basically finished (could make infinite)
    static void helpCheck(int i){
        if(code.indexOf("help<>", i) == i){
            System.out.println("Help coming soon");
        }
    }//
    static void openInBrowserCheck(int i){
        if(code.indexOf("openInBrowser<", i) == i){
            if(debug){
                System.out.println("DEBUG: openInBrowser detected");
            }
            int index = code.indexOf("openInBrowser", i);
            String toPrint = "";
            int tempIndex = index + 12;
            toPrint="";
            while (code.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += code.charAt(tempIndex);

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
    static void useCheck(int i){

        if(code.indexOf("use<", i) == i){


            int index = code.indexOf("use", i);



            String toPrint = "";
            int tempIndex = index + 2;
            toPrint="";
            while (code.charAt(tempIndex+1) != '>'){

                tempIndex++;
                toPrint += code.charAt(tempIndex);

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
    static void addCheck(int i){
        if(code.indexOf("add<", i) == i){
            int index = code.indexOf("add<", i);
            String option1 = "";
            String option2 = "";
            String option3 = "";
            String option4 = "";
            String toSetStr = "";
            int tempIndex = index + 4;

            while (code.charAt(tempIndex+1) != ':'){

                tempIndex++;
                option1 += code.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println(tempIndex);
                }

            }
            while (code.charAt(tempIndex+1) != ':'){

                tempIndex++;
                option2 += code.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println(tempIndex);
                }
            }
            while (code.charAt(tempIndex+1) != ':'){

                tempIndex++;
                option3 += code.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println(tempIndex);
                }

            }
            while (code.charAt(tempIndex+1) != '>'){

                tempIndex++;
                option4 += code.charAt(tempIndex);
                if(debug){
                    System.out.println("DEBUG: "+option3);
                    System.out.println("DEBUG: "+   tempIndex);
                }
            }
            if(debug)
                System.out.println(option1 + " "+option2+ " " +option3+ " "+option4);
            while (code.charAt(tempIndex+1) != '>' ){
                tempIndex++;
                toSetStr += code.charAt(tempIndex);
                toSetStr=toSetStr.replace(':', '0');
            }

        }
    }

}

