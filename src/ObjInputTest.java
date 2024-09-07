public class ObjInputTest {
    public static void main(String[] args) {

        SafeInputObj stringTest = new SafeInputObj();
        stringTest.getNonZeroLenString("Enter text here");
        System.out.println(stringTest);

        SafeInputObj intTest = new SafeInputObj();
        intTest.getRangedInt("Enter integer between 0-1000",0,1000);
        System.out.println(intTest);

        SafeInputObj intTestTwo = new SafeInputObj();
        intTestTwo.getInt("Enter integer");
        System.out.println(intTestTwo);

        SafeInputObj doubleTest = new SafeInputObj();
        doubleTest.getRangedDouble("Enter any decimal number between 0.0 - 9999.9",0,10000);
        System.out.println(doubleTest);

        SafeInputObj doubleTestTwo = new SafeInputObj();
        doubleTestTwo.getDouble("Enter decimal");
        System.out.println(doubleTestTwo);

        SafeInputObj ynTest = new SafeInputObj();
        ynTest.getYNConfirm("enter y or n");
        System.out.println(ynTest);

        SafeInputObj regTest = new SafeInputObj();
        regTest.getRegExString("enter a string","a");
        System.out.println(regTest);
    }
}
