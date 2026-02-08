class Shop {

    int stock; //재고
    boolean soldOut; //품절 여부
    int Price; //가격

    int revenue; //총 매출
    int soldOutcount; //총 판매수량
    int waitCount; //대기자 수

    //초기 설정- 재고 있는 상태, 가격설정, 매출/수량/대기자수=0
    Shop(int initialStock, int Price) {
        this.stock = initialStock;
        this.Price = Price;
        this.revenue = 0;
        this.soldOutcount = 0;
        this.waitCount = 0;

        if (initialStock == 0) {
            this.soldOut = true;
        } else {
            this.soldOut = false;

        }
    }

    //주문
    String order(int qty) {
        //품절인 경우
        if (soldOut == true) {
            waitCount = waitCount + 1;
            return "품절: (대기자 =" + waitCount + ")";
        }

        //판매가능한 경우
        if (stock >= qty) {
            stock = stock - qty;
            soldOutcount = soldOutcount + qty;
            revenue = revenue + (qty * Price);
        }

        //재고=0 --> 품절처리
        if (stock == 0) {
            soldOut = true;
        }
        return "주문 성공: " + qty + "개 구매(재고=" + stock + ")";

        //재고 부족 --> 대기자 등록
        waitCount++;
        return "재고 부족: 대기자 등록 (대기자=" + waitCount + ")";
    }

    //숫자 출력 및 상태 확인
    String status(){
        String result = "==========가게 현황==========\n";
        result=result+"재고: "+stock+"개 \n";
        result=result+"판매수량: "+soldOutcount+"개 \n";
        result=result+"총 매출: "+revenue+"원 \n";
        result=result+"대기자: "+waitCount+"명 \n";
        return result;
    }
}
public class Main{
    public void main(String[] args) {
        Shop shop =new Shop(15,6000);

        System.out.println(shop.order(2));
        System.out.println(shop.order(3));
        System.out.println(shop.order(1));
        System.out.println(shop.order(2));
        System.out.println(shop.order(3));
        System.out.println(shop.order(2));
        System.out.println(shop.order(1));
        System.out.println(shop.order(2));
        System.out.println(shop.order(2));
        System.out.println(shop.order(1));
        System.out.println(shop.order(1));

        System.out.println();
        System.out.println(shop.status()); //최종 확인



    }
}