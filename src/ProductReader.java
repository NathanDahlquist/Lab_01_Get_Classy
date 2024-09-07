import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args)
    {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<Product> product = new ArrayList<>();


        final int FIELDS_LENGTH = 4;

        String splitId, splitName, splitDescription;
        double splitCost;
        String[] split;

        try
        {

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedWriter around a lower level BufferedOutputStream
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Finally we can read the file LOL!
                int line = 0;  // if we want to keep track of the line numbers
                while(reader.ready())
                {
                    rec = reader.readLine();
                    split = rec.split(",");
                    if(split.length == FIELDS_LENGTH)
                    {
                        splitName = split[0].trim();
                        splitDescription = split[1].trim();
                        splitId = split[2].trim();
                        splitCost = Double.parseDouble(split[3].trim());
                        Product newProduct = new Product(splitName,splitDescription,splitId,splitCost);
                        System.out.println(newProduct);
                        product.add(newProduct);

                    }
                    else
                    {
                        System.out.println("Found a record that may be corrupt: ");
                    }
                    line++;
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");

                for(Product p : product){
                    //System.out.printf("\n%-8s%-25s%-25s%-6s%6d", id, firstName, lastName, title, yob);
                    System.out.printf("\n%-8s%-25s%-50s%6.1f", p.getID(), p.getName(), p.getDescription(), p.getCost());
                }
            }
            else  // user closed the file dialog without choosing
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }  // end of TRY
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
