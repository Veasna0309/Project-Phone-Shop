package com.sna.project.phonephop.PhoneShop.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
    int DEFUALT_PAGE_lIMITE=5;
    int DEFUALT_PAGE_NUMBER=1;
    String PAGE_LINIT="_limit";
    String PAGE_NUMBER="_page";

    static Pageable getPageable(int pageNumber, int pageSize){
        if(pageNumber<1){
            pageNumber=DEFUALT_PAGE_NUMBER;
        }
        if(pageSize<1){
            pageSize=DEFUALT_PAGE_lIMITE;
        }
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);//pageNumber-1 ព្រោះprogramming ចាប់ផ្តើមពី 1


        return pageable;
    }

}
