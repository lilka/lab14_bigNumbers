import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.RoundingMode.*;

public class CubicEquaction {



        private static final double TWO_PI = 2.0 * Math.PI;
        private static final double FOUR_PI = 4.0 * Math.PI;


        public BigInteger nRoots;


        public BigDecimal x1;

        public BigDecimal x2;


        public BigDecimal x3;

        public BigDecimal zero=new BigDecimal(0.0);

        public double zeroInDouble=zero.doubleValue();


        public CubicEquaction()
        {
        }




        public void solve
        (BigDecimal a,
         BigDecimal b,
         BigDecimal c,
         BigDecimal d)
        {

            if (a.equals(zero))
            {
                throw new RuntimeException ("Cubic.solve(): a = 0");
            }


            BigDecimal denom = a;
            a = b.divide(denom, BigDecimal.ROUND_HALF_EVEN);
            b = c.divide(denom, BigDecimal.ROUND_HALF_EVEN);
            c = d.divide(denom, BigDecimal.ROUND_HALF_EVEN);

            BigDecimal three=new BigDecimal(3.0);

            // Commence solution.
            BigDecimal a_over_3 = a.divide(three, BigDecimal.ROUND_HALF_EVEN);


            BigDecimal tmp=new BigDecimal(3.0);
            BigDecimal Q = tmp.multiply(b).subtract(a.multiply(a)).divide(new BigDecimal(9.0),HALF_UP);

            BigDecimal Q_CUBE = Q.pow(3);
            tmp=new BigDecimal(9.0);
            BigDecimal tmp1=tmp.multiply(a).multiply(b);
            tmp=new BigDecimal(27.0);
            BigDecimal tmp2= tmp.multiply(c);
            tmp=new BigDecimal(2.0);
            BigDecimal tmp3=a.pow(3).multiply(tmp);
            tmp=new BigDecimal(54.0);
            BigDecimal tmp4 = tmp1.subtract(tmp2).subtract(tmp3);
            BigDecimal R=tmp4.divide(tmp, HALF_UP);
            BigDecimal R_SQR = R.pow(2);
            BigDecimal D = Q_CUBE.add(R_SQR);




            if (D.doubleValue() < zero.doubleValue())
            {
                // Three unequal real roots.
                nRoots = new BigInteger("3");
                double Q_CUBE_double=Q_CUBE.doubleValue();
                double R_double=R.doubleValue();
                double Q_double=Q.doubleValue();

                double theta_double = Math.acos (R_double/ Math.sqrt (-Q_CUBE_double));
                BigDecimal theta=new BigDecimal(theta_double);
                double SQRT_Q_Double = Math.sqrt (-Q_double);
                BigDecimal SQRT_Q=new BigDecimal(SQRT_Q_Double);
                double x1_cos = Math.cos (theta_double/3.0);
                double x2_cos=Math.cos ((theta_double+TWO_PI)/3.0);
                double x3_cos= Math.cos ((theta_double+FOUR_PI)/3.0);
                BigDecimal x1_cos_Big=new BigDecimal(x1_cos);
                BigDecimal x2_cos_Big=new BigDecimal(x2_cos);
                BigDecimal x3_cos_Big=new BigDecimal(x3_cos);
                tmp=new BigDecimal(2.0);
                x1=tmp.multiply(SQRT_Q).multiply(x1_cos_Big).subtract(a_over_3).setScale(12,BigDecimal.ROUND_HALF_EVEN);
                x2=tmp.multiply(SQRT_Q).multiply(x2_cos_Big).subtract(a_over_3).setScale(12,BigDecimal.ROUND_HALF_EVEN);
                x3=tmp.multiply(SQRT_Q).multiply(x3_cos_Big).subtract(a_over_3).setScale(12,BigDecimal.ROUND_HALF_EVEN);

                sortRoots();
            }
            else if (D.doubleValue()> zeroInDouble)
            {
                // One real root.
                nRoots=new BigInteger("1");
                double SQRT_D_double = Math.sqrt (D.doubleValue());
                double S_double = Math.cbrt (R.doubleValue() + SQRT_D_double);
                double T_double= Math.cbrt (R.doubleValue() - SQRT_D_double);
                BigDecimal S=new BigDecimal(S_double);
                BigDecimal T=new BigDecimal(T_double);
                tmp=S.add(T);
                x1 =tmp.subtract(a_over_3);
                double x_Nan= Double.NaN;
                x2=null;
                x3 = null;
            }
            else
            {

                nRoots =new BigInteger("3");
                double CBRT_R_double = Math.cbrt (R.doubleValue());
                tmp=new BigDecimal(2);
                BigDecimal CBRT_R=new BigDecimal(CBRT_R_double);
                x1=CBRT_R.multiply(tmp).subtract(a_over_3);
                x2 = x3 = CBRT_R.subtract(a_over_3);
                sortRoots();
            }
        }



        private void sortRoots()
        {
            if (x1.doubleValue() < x2.doubleValue())
            {
                BigDecimal tmp = x1; x1 = x2; x2 = tmp;
            }
            if (x2.doubleValue() < x3.doubleValue())
            {
                BigDecimal tmp = x2; x2 = x3; x3 = tmp;
            }
            if (x1.doubleValue() < x2.doubleValue())
            {
                BigDecimal tmp = x1; x1 = x2; x2 = tmp;
            }
        }

//        public static void main(String[] args) throws Exception {
//
//            BigDecimal a = new BigDecimal(1).setScale(12, HALF_DOWN);
//            BigDecimal b = new BigDecimal(0).setScale(12, HALF_DOWN);
//            BigDecimal c=new BigDecimal(0).setScale(12,HALF_DOWN);
//            BigDecimal d=new BigDecimal(0).setScale(12,HALF_DOWN);
//          //  a=a.negate();
//         //   c=c.negate();
//            CubicEquaction cubic = new CubicEquaction();
//            cubic.solve(a, b, c, d);
//            System.out.println("x1 = " + cubic.x1);
//            if (cubic.nRoots.doubleValue() == 3) {
//                System.out.println("x2 = " + cubic.x2);
//                System.out.println("x3 = " + cubic.x3);
//            }
//        }

    }

