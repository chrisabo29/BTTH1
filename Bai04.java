import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai04 {
    static List<Integer> longestSubArray = new ArrayList<>();
    static void findLongestSubArray(int[] arr, int k, int currentIndex, List<Integer> currentSubArray) {
        if (k == 0) {
            if (currentSubArray.size() > longestSubArray.size()) {
                longestSubArray = new ArrayList<>(currentSubArray);
            }
            return;
        }

        for (int i = currentIndex; i < arr.length; i++) {
            if (arr[i] <= k) {
                currentSubArray.add(arr[i]);
                findLongestSubArray(arr, k - arr[i], i + 1, currentSubArray);
                currentSubArray.remove(currentSubArray.size() - 1);
            }
        }    
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so phan tu cua mang: ");
        int n = sc.nextInt();
        System.out.print("Nhap so k: ");
        int k = sc.nextInt();
        int [] arr = new int[n];
        System.out.print("Nhap cac phan tu cua mang: ");
        for (int i =0; i<n; i++)
        {
            arr[i] = sc.nextInt();
        }
        System.out.print("Day con dai nhat co tong bang k la: ");
        findLongestSubArray(arr, k, 0, new arrayList<>());

        sc.close();
    }
}
