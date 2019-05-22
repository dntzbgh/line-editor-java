import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Editor {
    public static final String DEFAULT_FILENAME="NO NAME";

    private List<String> fileLines;
    private ListIterator<String> linesIterator;
    private boolean modified;
    private String fileName;

    public Editor() {
        // Initialize empty params
        this.fileLines=new ArrayList<String>();
        this.linesIterator=fileLines.listIterator();
    }

    public Editor(String filename) throws IOException {
        // Initialize the array list that will
        // contain all the file lines
        this.fileLines=new ArrayList<String>();

        // Start reading the file
        readFile(filename);

        // Initialize the list iterator
        // and assign name to the file
        this.linesIterator=fileLines.listIterator();
        this.fileName=filename;
    }

    public void saveFile() throws IOException {
        Path pathToFile = Paths.get(this.fileName);
        BufferedWriter bufferedWriter= Files.newBufferedWriter(pathToFile);
        for (String line: this.fileLines){
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        this.modified=false;
        this.linesIterator=this.fileLines.listIterator();
    }

    public void readFile() throws IOException {
        // Clear the contents of the old file
        this.fileLines.clear();
        // Read file
        readFile(this.fileName);
        this.modified=false;
        this.linesIterator=this.fileLines.listIterator();

    }

    private void readFile(String filename) throws IOException {
        Path pathToFile = Paths.get(filename);
        BufferedReader bufferedReader = Files.newBufferedReader(pathToFile);
        String line ;
        while ((line = bufferedReader.readLine()) != null) {
            this.fileLines.add(line);
        }
        bufferedReader.close();
    }

    public String toString(){
        int lineCounter=1;
        StringBuilder result=new StringBuilder();
        ListIterator<String> auxIter=this.fileLines.listIterator();
        if (!auxIter.hasNext()){
            return result.append("-- Empty file -- \n").toString();
            //return result.append(lineCounter+">").toString();
        }
        while(auxIter.hasNext()){
            result.append((lineCounter++)+
                    (linesIterator.nextIndex()==auxIter.nextIndex()?">":" ")+
                    auxIter.next()+"\n");

        }
        if (auxIter.nextIndex()==linesIterator.nextIndex()){
            result.append(lineCounter+">\n");
        }
        return result.toString();
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.modified=true;
    }

    public void nextLine(){
        if (this.linesIterator.hasNext()){
            this.linesIterator.next();
        }
    }

    public void previousLine(){
        if (this.linesIterator.hasPrevious()){
            this.linesIterator.previous();
        }
    }

    public void insertLine(String newLine){
        this.linesIterator.add(newLine);
        this.modified=true;
    }

    public void removeLine(){
        if (this.linesIterator.hasNext()){
            this.linesIterator.remove();
            this.modified=true;
        }

    }

    public String getCurrentLine(){
        return this.linesIterator.hasNext()?this.fileLines.get(linesIterator.nextIndex()):null;
    }

    public void replaceLine(String newLine){
        if(this.linesIterator.hasNext()){
            this.linesIterator.set(newLine);
        }else{
            this.linesIterator.add(newLine);
        }
        this.modified=true;
    }

    public void goToLine(int lineNumber){
        ListIterator<String> auxIter;
        if (lineNumber<=1){
            this.linesIterator=fileLines.listIterator();
        }else{
            if (lineNumber>=this.fileLines.size()){
                auxIter=this.fileLines.listIterator();
                while (auxIter.hasNext()){
                    auxIter.next();
                }
            }else{
                auxIter=this.fileLines.listIterator();
                while(auxIter.hasNext()){
                    if (auxIter.nextIndex()==lineNumber){
                        break;
                    }
                }
            }
            this.linesIterator=auxIter;
        }
    }

    public boolean getState(){
        //
        return this.modified;
    }

    public void clearState(){
        this.modified=false;
    }

    public String infoString(){
        //TO DO
        return null;
    }






}
