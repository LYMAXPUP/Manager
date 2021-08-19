package com.service;

import com.model.Asset;

import java.util.List;

public interface IAssetService {
    List<Asset> allList(int index, String assetName);

    Integer listCount(String assetName);

    List<Asset> allMyList(int index, String userId, String assetName);

    Integer myListCount(String userId, String assetName);

    boolean addAsset(Asset asset);

    boolean addSomeAssets(List<Asset> assets, String accountName);

    boolean editAsset(Asset asset);

    boolean deleteAsset(int assetId);

    boolean claimAsset(int assetId, String userId);

    boolean giveBackAsset(int assetId);
}
