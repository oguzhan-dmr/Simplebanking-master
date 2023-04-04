BANKA REST APİ PROJE

Bu ödev banka hesapları ve işlemleri için oluşturulmuştur.

- Bu ödevde “Model”,”Controller”,”Servis” ve “Test” katmanlanları oluşturulmuştur.

MODEL

- Model katmanında “Account” sınıfında hesap bilgileri mevcuttur ve bunlar (id , Owner , Account Number, Balance)'dır. Yani banka hesabı münhasır olarak hesap sahibinin adını, hesap numarasını ve hesap bakiyesini korumakla ilgilenir.
- Model katmanında “Transaction” sınıfı işlem için oluşturulmuş ve (id, Date, Amount) 'dır. Yani bankada hesabı olan münhasır kişi işlem yaptığında bilgileri korumak için ve bu işlemler ile ilgilenen sınıftır. Bu sınıfın “Child” ALT SINIFLARI vardır. Bunlar “Deposit Transaction”, “Withdrawal Transaction” ve “Bill Payment Transaction” 'lardır.
- Deposit Transaction: Hesaba yatırılan + bakiyedir.
- Withdrawal Transaction: Hesaptan çıkan - bakiyedir.
- Bill Payment Transation: Hesap üzerinden çıkan - fatura ödemeleridir.
- Bu üç sınıfın ortak üzellikleri işlem olarak bir tutardır (Amount).
- Model katmanında “Transaction Status” ve “Transaction Type” olarak iki adet daha işlem için oluşturulmuş sınıf vardır.
- Transaction Status: İşlem yapılınca geri dönüş almak için oluşturulmuş “Durum” sınıfıdır.
- Transaction Type: Yapılan işleme göre işlem ataması için oluşturulmuş sınıftır. (Enum)

`              `“Credit” ,“Debit ” , “Bill” dir.

- Model katmanında “AccountNotFoundException” ve “InsufficientBalanceException” olarak iki adet HATA sınıfı oluşturulmuştur.

CONTROLLER

Controller katmanında “Account Controller” ve “Bill Payment Controller” adında iki sınıf oluşturulmuştur.

Account Controller: Bu sınıfta ihtiyaca yönelik bilgileri almak için “REST API” lar vardır. 

- Bunlar: Hesap oluşturma (Save Account) 
- Hesap bilgilerini isteme (Account Number)
- Mevcut olan hesaba para yatırma (Credit)
- Mevcut olan hesaptan para çekme (Debit) dir.

Bill Payment Controller: Bu sınıfta ödeneme işlemleri için oluşturulmuş “REST APİ” vardır.

- Hesabı olan kişi hesap numarası üzerinden fatura ödeme işlemi yapabilir.

SERVİCE

- Servis katmanında Controller dan gelen bilgilerin şartları sağladığı durumda ve veri tabanında istenilen bölümlere bilgilerin kayıt edilmesi için oluşturulmuş Logic (mantık) tarafıdır.
- Burada Controllerdan gelen bilgileri kontrol edip veri tabanına kayıt işlemleri yapıyoruz (H2).
- Veri tabanına kayıt işlemleri için “Account Repository” ve “Transaction Repository” Interface leri oluşturulmuş olup bu interface lere “Jpa  Repository” (extends) edilmiştir. Ve bu sayede hazır olan “save”, “FindBy..” gibi hazır metot'lar kullanılmıştır.

TEST

- Bu projede olan Controller katmanındaki “Account Controller” ve “Bİll Payment Controller” sınıflarının UNİT TEST leri yapılmıştır.



