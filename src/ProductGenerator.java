import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator
{
    public static void main(String[] args)
    {
        ArrayList<Product> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;
        String ID = "";
        String name = "";
        String description = "";
        double cost = 0;

        // uses a fixed known path:
        //  Path file = Paths.get("c:\\My Documents\\data.txt");

        // use the toolkit to get the current working directory of the IDE
        // will create the file within the Netbeans project src folder
        // (may need to adjust for other IDE)
        // Not sure if the toolkit is thread safe...
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTextData.txt");

        do {

            ID = SafeInput.getNonZeroLenString(in, "Enter your product ID 000001");
            name = SafeInput.getNonZeroLenString(in, "Enter your product name");
            description = SafeInput.getNonZeroLenString(in, "Enter your product description");
            cost = SafeInput.getDouble(in, "Enter your product cost 1000.0");

            //rec = ID + ", " + fName + ", " + lName + ", " + title + ", " + yob;

            Product newProduct = new Product(name,description,ID,cost);

            System.out.println(newProduct);

            recs.add(newProduct);

            doneInput = SafeInput.getYNConfirm(in, "Would you like to quit?");

        }while(!doneInput);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Product r : recs)
            {
                String s = r.toCVS();
                writer.write(s, 0, s.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
