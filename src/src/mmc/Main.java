package mmc;

import mmc.aldebaran.LtsBuilder;
import mmc.modal.formula.Formula;
import mmc.modal.ModalParser;
import mmc.modal.ParseException;
import mmc.models.Lts;

public class Main {

    public static void main(String[] args) throws ParseException {
        ModalParser mp = new ModalParser("nu X. <tau>X\n");
        Formula f = mp.parse();
        LtsBuilder lb = new LtsBuilder();
        // http://www.mcrl2.org/web/user_manual/language_reference/lts.html#id1
        Lts lts = lb.build("des (0,12,10)\n(0,\"lock(p2, f2)\",1)\n(0,\"lock(p1, f1)\",2)\n(1,\"lock(p1, f1)\",3)\n(1,\"lock(p2, f1)\",4)\n(2,\"lock(p2, f2)\",3)\n(2,\"lock(p1, f2)\",5)\n(4,\"eat(p2)\",6)\n(5,\"eat(p1)\",7)\n(6,\"free(p2, f2)\",8)\n(7,\"free(p1, f1)\",9)\n(8,\"free(p2, f1)\",0)\n(9,\"free(p1, f2)\",0)");
        System.out.println(lts);
    }
}
