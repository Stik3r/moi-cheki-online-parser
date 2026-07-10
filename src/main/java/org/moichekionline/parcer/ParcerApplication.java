package org.moichekionline.parcer;

import org.moichekionline.parcer.models.dto.moiChekiOnline.ReceiptOrderBy;
import org.moichekionline.parcer.models.dto.moiChekiOnline.ReceiptSearchRequest;
import org.moichekionline.parcer.service.MoiChekiOnlineService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ParcerApplication {

    public static void main(String[] args) {
        var app = SpringApplication.run(ParcerApplication.class, args);

        MoiChekiOnlineService service = app.getBeanFactory().getBean(MoiChekiOnlineService.class);
        service.refreshToken();
        var res = service.getReceipts(new ReceiptSearchRequest(
                5,
                0,
                null,
                null,
                ReceiptOrderBy.CREATED_DATE_DESC.value,
                null,
                null
        ));

        var res2 = service.getFiscalData(List.of(res.receipts().get(0).key(), res.receipts().get(1).key()
        , res.receipts().get(2).key(), res.receipts().get(3).key(), res.receipts().get(4).key()));

        System.out.println(res2);


    }

}
