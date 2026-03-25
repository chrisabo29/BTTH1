import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai04 {
    static List<Integer> longestSubArray = new ArrayList<>();
    static void findLongestSubArray(int[] arr, int k, int currentIndex, List<Integer> currentSubArray) {
        //Kiểm tra xem phần còn lại có bằng không hay không, nếu bằng 0 thì kiểm tra xem subarray hiện tại có dài hơn subarray dài nhất đã tìm được hay không
        if (k == 0) {
            if (currentSubArray.size() > longestSubArray.size()) {
                longestSubArray = new ArrayList<>(currentSubArray);
            }
            return;
        }

        //Duyệt từng phần tử của mảng, nếu phần tử đó nhỏ hơn hoặc bằng k thì thêm vào subarray hiện tại và gọi đệ quy để tìm phần còn lại. 
        for (int i = currentIndex; i < arr.length; i++) {
            if (arr[i] <= k) {
                currentSubArray.add(arr[i]);
                findLongestSubArray(arr, k - arr[i], i + 1, currentSubArray);
                //Nếu không tìm thấy subarray nào có tổng bằng k thì loại bỏ phần tử cuối cùng của subarray hiện tại
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
        findLongestSubArray(arr, k, 0, new ArrayList<>());
        if (longestSubArray.isEmpty()) {
            System.out.println("Khong tim thay day con nao co tong bang k.");
        } else {
            System.out.print("Day con dai nhat co tong bang k la: ");
            for (int num : longestSubArray) {
                System.out.print(num + " ");
            }
        }
        sc.close();
    }
}
