package mmc;

public class Main {

    public static void main(String[] args) throws ParseException {
        ModalParser mp = new ModalParser("nu X. <tau>X\n");
        Formula f = mp.parse();
    }
}
