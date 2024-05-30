import java.util.Arrays;

public class appThread {
    private static final int SIZE=10000000;
    private static final int H=SIZE/2;

    public static void main(String[] args) {
        float[] arr =new float[SIZE];
        Arrays.fill(arr,1);
        method1(arr);
        method2(arr);
        System.out.println();
    }
    public static void method2(float[] arr){
        long a =System.currentTimeMillis();
        float[] a1=new float[H];
        float[] a2=new float[H];
        System.arraycopy(arr,0,a1,0,H);
        System.arraycopy(arr,H,a2,0,H);
        Thread t1= new Thread(()->{
            for (int i = 0; i < H; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread t2= new Thread(()->{
            for (int i = H; i < SIZE; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.arraycopy(a1,0,arr,0,H);
        System.arraycopy(a2,0,arr,H,H);
        System.out.println(System.currentTimeMillis()-a);
    }
    public static void method1(float[] arr){
        long a =System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }
}
