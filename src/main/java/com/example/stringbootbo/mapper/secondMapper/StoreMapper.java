package com.example.stringbootbo.mapper.secondMapper;

import com.example.stringbootbo.bean.StoreDetails;

public interface StoreMapper {
    StoreDetails findStoreDetails(StoreDetails storeDetails);

    StoreDetails findStoreDetailsNoCoord(StoreDetails storeDetails);
}
