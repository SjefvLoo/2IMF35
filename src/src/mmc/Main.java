package mmc;

import mmc.aldebaran.LtsBuilder;
import mmc.modal.visitors.FormulaVisitor;
import mmc.modal.visitors.TrivialFormulaVisitor;
import mmc.modal.formulas.Formula;
import mmc.modal.ModalParser;
import mmc.modal.ParseException;
import mmc.models.Lts;
import mmc.models.State;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws ParseException {
        if(args.length < 2)
        {
            System.err.println("2 arguments required: [lts file] [modal formula file]");
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

            FormulaVisitor tfv = new TrivialFormulaVisitor(lts);
            try {
                System.out.println("Result for \"" + modalcontent + "\": ");
                Set<State> result = tfv.calculate(f);
                System.out.println(result);
            } catch (UnsupportedOperationException e) {
                System.out.println(f.getClass());
            }
        } catch (IOException e) {
            System.err.println("Unable to read input files");
            System.exit(2);
            return;
        }
    }
}
