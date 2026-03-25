

public class Bai02 {
    public static void main(String[] args) {
        int r = 1; // đường tròn tâm O(0,0), bán kính r=1
        int n = 10_000;   // số đoạn chia (càng lớn càng chính xác)
        double step = 1.0/n;
        long totalPoints = n * n; // tổng số điểm trong 1/4 hình vuông
        long count = 0; // tong số điểm trong 1/4 hình tròn
        for (double i=0; i<= r; i += step) {
            double x = i;
            double y = Math.sqrt(r*r - x*x);
            count += (int)(y / step);
        }
        double S_square = (2*r) * (2*r);
        double S_circle = ((double)count / totalPoints) * S_square;
        double pi = S_circle / (r*r);
        System.out.println("Gia tri xap xi cua pi: " + pi);
    }
}