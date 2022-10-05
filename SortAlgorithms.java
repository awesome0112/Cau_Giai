import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class SortAlgorithms {
    private static boolean isLess(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static final void selectionSort(Comparable[] a) {
        int min = 0;
        for(int i = 0; i < a.length; i++) {
            min = i;
            for(int j = i + 1; j < a.length; j++) {
                if(i != 0 && a[j] == a[i - 1]) {
                    min = j;
                    break;
                } else if(isLess(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    public static final void insertionSort(Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j > 0; j--) {
                if(isLess(a[j], a[j - 1])) swap(a, j, j - 1);
                else break;
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] tmp, int left, int mid, int right) {
        // Mảng tmp sẽ copy giá trị của mảng a từ index left đến index right.
        // Tức là thay vì merge bằng cách đổi giá trị của mảng a thì ta sẽ sử dụng mảng tmp song song với mảng a
        // Để copy giá trị của mảng tmp vào mảng a ở đùng index
        // => mảng tmp được tạo ra từ đầu để tránh việc phải khai báo lại nhiều lần làm tốn bộ nhớ.

        for(int i = left; i <= right; i++) {
            tmp[i] = a[i]; // Copy.
        }
        // Merge
        int i = left;
        int j = mid + 1;
        int k = left;
        while(i <= mid && j <= right) {
            if(isLess(tmp[i], tmp[j])) a[k++] = tmp[i++];
            else a[k++] = tmp[j++];
        }
        while(i <= mid) a[k++] = tmp[i++];
        while(j <= right) a[k++] = tmp[j++];

        /* ANOTHER WAY TO MERGE */
//        for(int i = left; i <= right; i++) {
//            tmp[i] = a[i];
//        }
//        int i = left, j = mid + 1;
//        for(int k = left; k <= right; k++) {
//            if(i > mid) a[k] = tmp[j++];
//            else if(j > right) a[k] = tmp[i++];
//            else if(isLess(tmp[j], tmp[i])) a[k] = tmp[j++];
//            else a[k] = tmp[i++];
//        }

    }
    private static void mergeSort(Comparable[] a, Comparable[] tmp, int left, int right) {
        if(right <= left) return;
        int mid = left + (right - left) / 2;
        mergeSort(a, tmp, left, mid);
        mergeSort(a, tmp, mid + 1, right);
        merge(a, tmp, left, mid, right);
    }
    public static final void mergeSort(Comparable[] a) {
        Comparable[] tmp = new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    private static void print(Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }





    private static int partition(Comparable[] a, int left, int right) {
        // Quá trình này là cho số có index left vào đúng vị trí của nó trong mảng
        // (Tức là xếp nó vào vị trí sao cho các số đứng trước nó đều nhỏ hơn hoặc bằng số đó và các số đứng sau lớn hơn hoặc bằng)
        // Ta sẽ check từ i = left + 1 đến cuối.
        // có 2 con trỏ là i chạy từ left + 1 đến cuối và j chạy từ right đến đầu. (sẽ dừng khi i >= j)
        // i đại diện cho số nhỏ hơn số cần xếp đúng vị trí và j đại diện cho số lớn hơn.
        // đầu tiên là cho chạy i, điều kiện là a[i] nhỏ hơn a[left].
        // đến khi tìm được a[i] > a[left] (tức là tìm thấy 1 số đứng sai vị trí vì số đứng bên trái left phải nhỏ hơn left).    (1)
        // tiếp theo cho chạy j từ right về, điều kiện là a[left] nhỏ hơn a[j].
        // đến khi tìm được a[j] < a[left] (tức là đã tìm thấy 1 số đứng sai vị trí vì số đứng bên phải left phải lớn hơn left).   (2)
        // Từ (1) và (2) => khi này tìm được 2 số đứng sai vị trí nên ta đổi chỗ 2 số này thì nó sẽ đứng đúng vị trí theo quy tắc trái nhỏ hơn, phải lớn hơn.
        // khi đổi xong ta đã có tất cả các số có index <= i đã nhỏ hơn left và các số có index >= j đã lớn hơn left
        // sau đó tiếp tục làm cho đến khi đúng vị trí.
        int i = left, j = right + 1;
        while(true) {
            while(isLess(a[++i], a[left])) {
                if(i == right) break;
            }
            while(isLess(a[left], a[--j])) {
                if(j == left) break;
            }
            if(i >= j) break;
            swap(a, i, j);
        }
        swap(a, j, left);
        return j;
    }
    private static void medianOfThree(Comparable[] a, int left, int mid, int right) {
        if(isLess(a[right], a[left])) swap(a, left, right);
        if(isLess(a[right], a[mid])) swap(a, right, mid);
        swap(a, left, mid);
    }
    private static int[] threeWayPartition(Comparable[] a, int left, int right) {
        int lessThan = left, greaterThan = right;
        Comparable pivot = a[left];
        int i = left;
        while(i <= greaterThan) {
            int compare = a[i].compareTo(pivot);
            if(compare < 0) {
                swap(a, i, lessThan);
                i++;
                lessThan++;
            } else if(compare > 0) {
                swap(a, i, greaterThan);
                i++;
                greaterThan--;
            } else i++;
        }
        return new int[] {lessThan, greaterThan};
    }
    private static void quickSort(Comparable[] a, int left, int right) {
        int CUTOFF = 10;
        if(right <= left + CUTOFF - 1) {
            insertionSort(a);
            return;
        }
        medianOfThree(a, left, left + (right - left) / 2, right);

        /* 3 WAY PARTITION SOLUTION */
        int[] js = threeWayPartition(a, left, right);
        int lt = js[0];
        int gt = js[1];
        quickSort(a, left, lt - 1);
        quickSort(a, gt + 1, right);

        /* 2 WAY PARTITION SOLUTION */
//        int mid = partition(a, left, right);
//        quickSort(a, left, mid - 1);
//        quickSort(a, mid + 1, right);
    }

    public static final void quickSort(Comparable[] a) {
//        Collections.shuffle(List.of(a));
        quickSort(a, 0, a.length - 1);
    }

    public static Comparable theKthSmallest(Comparable[] a, int k) {
        int left = 0, right = a.length - 1;
        while(left <= right) {
            int j = partition(a, left, right);
            if(k < j) right = j - 1;
            else if(k > j) left = j + 1;
            else return a[k];
        }
        // Trường hợp xấu nhất là phải sắp xếp lại toàn bộ mảng và trả về a[k].
        return a[k];
    }
}
