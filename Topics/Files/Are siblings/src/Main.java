import java.io.File;

class Siblings {

    public static boolean areSiblings(File f1, File f2) {
        String fil1 = f1.getParent();
        String fil2 = f2.getParent();

        return fil1.equals(fil2) ? true : false;
    }
}