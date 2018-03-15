package mmc;

import mmc.aldebaran.LtsBuilder;
import mmc.modal.formulas.Formula;
import mmc.modal.ModalParser;
import mmc.modal.ParseException;
import mmc.models.Lts;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws ParseException {
        if(args.length < 2)
        {
            System.out.println("2 arguments required: [lts file] [modal formula file]");
            System.exit(1);
            return;
        }
        try {
            String ltscontent = new String(Files.readAllBytes(Paths.get(args[0])));
            String modalcontent = new String(Files.readAllBytes(Paths.get(args[1])));

            LtsBuilder lb = new LtsBuilder();
            // http://www.mcrl2.org/web/user_manual/language_reference/lts.html#id1
            Lts lts = lb.build(ltscontent);


            ModalParser mp = new ModalParser(modalcontent);
            Formula f = mp.parse();


            System.out.println(lts);
            System.out.println(f);
        } catch (IOException e) {
            System.out.println("Unable to read input files");
            System.exit(2);
            return;
        }
    }
}
