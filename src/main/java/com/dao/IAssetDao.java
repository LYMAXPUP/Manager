package com.dao;

import com.model.Asset;

import java.sql.Connection;
import java.util.List;

public interface IAssetDao {
    List<Asset> getAllAsset(int offset, int limit, String assetName);

    Integer listCount(String assetName);

    List<Asset> getMyAsset(int offset, int limit, String userId, String assetName);

    Integer myListCount(String userId, String assetName);

    int findTypeId(Connection con, String type);

    boolean updateAssets(Connection con, int assetId, String column, Object value);

    boolean updateProducts(Connection con, int assetId, String column, Object value);

    boolean updateFinance(Connection con, int assetId, String column, Object value);

    boolean claimAsset(int assetId, String userId);

    boolean addAssets(Connection con, Asset asset);

    boolean addProducts(Connection con, Asset asset);

    boolean addFinance(Connection con, Asset asset);

}
