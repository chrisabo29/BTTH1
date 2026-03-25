import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;

public class Bai03 {
    //Hàm tính hướng giữa 3 điểm a, b, c. Trả về 1 nếu a->b->c là ngược chiều kim đồng hồ, -1 nếu cùng chiều kim đồng hồ, 0 nếu thẳng hàng
    static int orientation(int[] a, int[] b, int[] c) {
        int val = (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
        if (val > 0) return 1;
        else if (val < 0) return -1;
        return 0;
    }

    //Hàm tính khoảng cách giữa 2 điểm a và b
    static double distance(int[] a, int[] b) {
        return Math.sqrt(
            (a[0] - b[0]) * (a[0] - b[0]) +
            (a[1] - b[1]) * (a[1] - b[1])
        );
    }

    static ArrayList<int[]> findConvexHull(int[][] points) {
        int len = points.length;

        //Tìm điểm có tung độ nhỏ nhất (nếu có nhiều điểm có cùng tung độ nhỏ nhất thì chọn điểm có hoành độ nhỏ nhất) để làm điểm gốc
        int minY_index = 0;
        for (int i = 0; i < len; i++) {
            if (points[i][1] < points[minY_index][1] || (points[i][1] == points[minY_index][1] && points[i][0] < points[minY_index][0])) {
                minY_index = i;
            }
        }
        int[] minY = points[minY_index];
        
        //Dùng để tính góc giữa điểm gốc và các điểm còn lại
        double[][] angleList = new double[len][2];
        for (int i = 0; i < len; i++) {
            if (points[i][0] == minY[0] && points[i][1] == minY[1]) {
                angleList[i][0] = -1; // cho điểm gốc đứng đầu
                angleList[i][1] = i;
                continue;
            }

            double dx = points[i][0] - minY[0];
            double dy = points[i][1] - minY[1];

            double angle = Math.atan2(dy, dx);

            angleList[i][0] = angle;
            angleList[i][1] = i;
        }

        //Sắp xếp các điểm theo góc với điểm gốc tăng dần
        Arrays.sort(angleList, (a, b) -> Double.compare(a[0], b[0]));
        
        //Lọc ra các điểm có cùng góc với điểm gốc, chỉ giữ lại điểm xa nhất với điểm gốc
        ArrayList<double[]> filtered = new ArrayList<>();
        boolean shouldAdd = true;
        for (int i = 0; i < angleList.length; i++) {
            if (!filtered.isEmpty()) {
                double[] last = filtered.get(filtered.size() - 1);

                //dùng epsilon để so sánh số thực
                if (Math.abs(last[0] - angleList[i][0]) < 1e-9) {
                    int idx1 = (int) last[1];
                    int idx2 = (int) angleList[i][1];

                    double dist1 = distance(points[idx1], minY);
                    double dist2 = distance(points[idx2], minY);

                    // giữ điểm xa hơn
                    if (dist2 > dist1) {
                        filtered.remove(filtered.size() - 1);
                    } else {
                        shouldAdd = false;
                    }
                } 
            }

            if (shouldAdd) {
                filtered.add(angleList[i]);
            }
        }

        //Dùng stack để xây dựng bao convex hull
        Stack<int[]> stack = new Stack<>();
        stack.push(points[(int)filtered.get(0)[1]]);
        stack.push(points[(int)filtered.get(1)[1]]);
        for (int i = 2; i < filtered.size(); i++) {
            int[] next = points[(int)filtered.get(i)[1]];

            while (stack.size() >= 2) {
                int[] current = stack.pop();
                int[] previous = stack.peek();

                if (orientation(previous, current, next) == 1 || orientation(previous, current, next) == 0) {
                    stack.push(current);
                    break;
                }
            }
            stack.push(next);
        }

        return new ArrayList<>(stack);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so diem: ");
        int n = sc.nextInt();
        int[][] points = new int[n][2];
        System.out.print("Nhap cac diem (x, y):");
        for (int i = 0; i<n; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }
        ArrayList<int[]> result = findConvexHull(points);

        System.out.println("Cac diem tren convex hull la:");
        for (int[] point : result) {
            System.out.println(point[0] + " " + point[1]);
        }

        sc.close();
    }
}
