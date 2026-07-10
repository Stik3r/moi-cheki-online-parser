package org.moichekionline.parcer.controllers;

import lombok.RequiredArgsConstructor;
import org.moichekionline.parcer.models.dto.moiChekiOnline.FiscalDataResponse;
import org.moichekionline.parcer.models.dto.moiChekiOnline.ReceiptSearchRequest;
import org.moichekionline.parcer.models.dto.moiChekiOnline.ReceiptSearchResponse;
import org.moichekionline.parcer.service.MoiChekiOnlineService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParcerController {

    private final MoiChekiOnlineService service;

    @PostMapping("/setToken")
    public void setRefreshToken(@RequestBody String refreshToken) {
        service.setRefreshToken(refreshToken);
    }

    @PostMapping("/setSourceDeviceId")
    public void setSourceDeviceId(@RequestBody String sourceDeviceId) {
        service.setSourceDeviceId(sourceDeviceId);
    }

    @PostMapping("/getReceipts")
    public ReceiptSearchResponse getReceipts(@RequestBody ReceiptSearchRequest receiptSearchRequest) {
        return service.getReceipts(receiptSearchRequest);
    }

    @PostMapping("/getFiscalData")
    public List<FiscalDataResponse> getFiscalData(@RequestBody List<String> keys) {
        return service.getFiscalData(keys);
    }

    @PostMapping("/getJsonFile")
    public ResponseEntity<byte[]> getJsonFile(@RequestBody List<FiscalDataResponse> fiscalDataResponses) {
        byte[] data = service.saveToJson(fiscalDataResponses);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"fiscalData.json\"")
                .body(data);
    }
}
