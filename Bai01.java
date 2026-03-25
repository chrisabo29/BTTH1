import java.util.Scanner;

public class Bai01 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ban kinh r: ");
        double r = sc.nextDouble(); // đường tròn tâm O(0,0), bán kính r
        int n = 10_000;   // số đoạn chia (càng lớn càng chính xác)
        double step = r/n;
        long totalPoints = n * n; // tổng số điểm trong 1/4 hình vuông
        long count = 0; // đếm số điểm trong 1/4 hình tròn
        for (double i = 0; i <= r; i += step)
        {
            double x = i;
            double y = Math.sqrt(r*r - x*x);
            count += (int)(y / step);
        }
        double S_square = (2*r) * (2*r);
        double S_circle = ((double)count / totalPoints) * S_square;
        System.out.println("Dien tich hinh tron: " + S_circle);

        sc.close();
    }
}