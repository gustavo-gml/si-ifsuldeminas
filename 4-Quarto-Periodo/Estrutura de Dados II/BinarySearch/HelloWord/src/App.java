import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        int[] ints = {1,2,4,5,7,9,11};

        System.out.println(binarysearch(ints, 9));
        System.out.println(Arrays.binarySearch(ints, 9));
    }

    private static int binarysearch(int[] numbers, int valueToFind){
        int low = 0;
        int high = numbers.length - 1; //ponteiros do dos indices da busca

        while (low <= high) {
            int middlePosition = (low + high) / 2;
            int middleNumber = numbers[middlePosition];

            if (valueToFind == middleNumber) {
                return  middlePosition;
            }

            if (valueToFind < middleNumber) {
                high = middlePosition - 1;
            }else{ //maior
                low = middlePosition + 1;
            }
        }

        return - 1;
    }
}
