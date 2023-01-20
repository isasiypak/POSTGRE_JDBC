import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "152869");
        Statement st = con.createStatement();

        /*

       PreparedStatemen  interface birden cok kez calistirilabilen onceden derlenmis bir sql kodunu temsil eder.
       kendimize gore kalip olsuturup onu surekli kullanma islemis
      --- paremetrelendiirilmis sql sorgulari (query) ile calisir. bu sorguyu 0 veya daha fazla parametre kullanabiliriz.

         */

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        // kaliplanmis bir seyi hazirlayacagiz

  // 1. adim prepared statement querysini olusturacagiz.
      String sql1 ="update companies set number_of_employees = ?  where company = ? ";

      // 2.ADIM PREPARED STATEMENT  OBJESINI OLUSTUR.
        PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
        // 3.adim  setInt ()  data tipi neyse onu kullnarak soru iodaretleri yerlerine deger atiyoruz

        preparedStatement1.setInt(1,9999);
        preparedStatement1.setString(2,"IBM");

        // 4. ADIM : EXECUTEQUERY CALISTIRALIM

        int guncellenenSatirSayisi = preparedStatement1.executeUpdate(); // burd INT KACTANE UPDATE OLDUGUNU SAYI DEGERI VERECEGI ICIN YAZDIK
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 =" SELECT * FROM companies";
        ResultSet rst1 = st.executeQuery(sql2);
       while (rst1.next()){

           System.out.println(rst1.getInt(1)+"   "+rst1.getString(2)+"  "+rst1.getInt(3));

        }
        System.out.println();

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.


        preparedStatement1.setInt(1,5555);
        preparedStatement1.setString(2,"GOOGLE");

        int guncellenenSatirSayisi2 = preparedStatement1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet rst2 = st.executeQuery(sql2);
        while (rst2.next()){

            System.out.println(rst2.getInt(1)+"   "+rst2.getString(2)+"  "+rst2.getInt(3));

        }


        con.close();
        st.close();
        rst1.close();
        rst2.close();






    }

}
