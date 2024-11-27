package org.example.mhsd.Service;

import org.example.mhsd.Mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecodedCodeService {
    private final DecodedCodeMapper decodedCodeMapper;
    private final CodeAddressMapper codeAddressMapper;

    private final SourceCodeMapper sourceCodeMapper;

    private final CarrierCodeMapper carrierCodeMapper;

    private final DisasterCodeMapper disasterCodeMapper;

    @Autowired
    public DecodedCodeService(DecodedCodeMapper decodedCodeMapper, CodeAddressMapper codeAddressMapper, SourceCodeMapper sourceCodeMapper, CarrierCodeMapper carrierCodeMapper, DisasterCodeMapper disasterCodeMapper) {
        this.decodedCodeMapper = decodedCodeMapper;
        this.codeAddressMapper = codeAddressMapper;
        this.sourceCodeMapper = sourceCodeMapper;
        this.carrierCodeMapper = carrierCodeMapper;
        this.disasterCodeMapper = disasterCodeMapper;
    }

    public void decodeAndSave(String code) {
        String Code = code;
        Code = Code.substring(0,Code.length()-1);
        String location = decodeLocation(code.substring(0, 12));
        String date = decodeDate(code.substring(12,26));
        String source = decodeSource(code.substring(26,29));
        String carrier = decodeCarrier(code.substring(29,30));
        String disaster=getDisasterFromCode(code.substring(30,36));
        String category=disaster.split("-")[0];
        String subcategory=disaster.split("-")[1];
        String indicator=disaster.split("-")[2];

        decodedCodeMapper.insertDecodedCode(Code,location, date, source, carrier,category,subcategory,indicator);
    }

    private String decodeCarrier(String code){return carrierCodeMapper.getCarrierByCode(code);}

    private String decodeSource(String code) {return sourceCodeMapper.getSourceByCode(code);}

    private String decodeLocation(String code) {
        return codeAddressMapper.getLocationByCode(code);
    }

    private String getDisasterFromCode(String code) {
        String disaster = disasterCodeMapper.getDisasterByCode(code);
        return disaster != null ? disaster : "";
    }

    private String decodeDate(String code) {
        String year = code.substring(0, 4);
        String month = code.substring(4, 6);
        String day = code.substring(6, 8);
        String hour = code.substring(8, 10);
        String minute = code.substring(10, 12);
        String second = code.substring(12, 14);

        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }
}
