class FirstRepeatingCharacter {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No argument provided!");
            return;
        }
        getFirstRepeatingCharacter(args[0]);
    }

    private static void getFirstRepeatingCharacter(String string) {
        int len = 256; 
        int strLen = string.length();

        int [] chars = new int[len];
        for (int i = 0; i < len; i++) {
            chars[i] = 0;
        }
        for (int i = 0; i < strLen; i++) {
            char c = string.charAt(i);
            if(chars[c] == 1){
                System.out.println(c);
                return;
            }
            chars[c]++;
        }
        System.out.println("No repeating character found!");
    }

}