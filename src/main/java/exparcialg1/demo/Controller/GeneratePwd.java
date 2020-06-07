package exparcialg1.demo.Controller;

public class GeneratePwd {

    static String getAlphaString(int n) {

        String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaString.length()
                    * Math.random());
            sb.append(AlphaString
                    .charAt(index));
        }
        return sb.toString();
    }


    static String getAlphaNumeric(int n) {

        String AlphaNumeric = "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumeric.length()
                    * Math.random());
            sb.append(AlphaNumeric
                    .charAt(index));
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        int n = 8;
        int m = 2;

        System.out.println(GeneratePwd
                .getAlphaString(n) + GeneratePwd.getAlphaNumeric(m));
    }
}
