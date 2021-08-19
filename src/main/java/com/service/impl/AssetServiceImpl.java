package com.service.impl;

import com.dao.IAssetDao;
import com.dao.impl.AssetDaoImpl;
import com.model.Asset;
import com.service.IAssetService;
import com.utils.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AssetServiceImpl implements IAssetService {
    private static int pageSize=5;
    @Override
    public List<Asset> allList(int index, String assetName) {
        int offset = (index-1)*pageSize;
        int limit = pageSize;
        IAssetDao iad = new AssetDaoImpl();
        return iad.getAllAsset(offset, limit, assetName);
    }

    @Override
    public Integer listCount(String assetName) {
        IAssetDao iad = new AssetDaoImpl();
        int count =  iad.listCount(assetName);
        return count % pageSize == 0? count/pageSize : count/pageSize +1;
    }

    @Override
    public List<Asset> allMyList(int index, String userId, String assetName) {
        int offset = (index-1)*pageSize;
        int limit = pageSize;
        IAssetDao iad = new AssetDaoImpl();
        return iad.getMyAsset(offset, limit, userId, assetName);
    }

    @Override
    public Integer myListCount(String userId, String assetName) {
        IAssetDao iad = new AssetDaoImpl();
        int count =  iad.myListCount(userId, assetName);
        return count % pageSize == 0? count/pageSize : count/pageSize +1;
    }

    @Override
    public boolean addAsset(Asset asset) {
        IAssetDao iad = new AssetDaoImpl();
        Connection con = DBManager.getConnection();
        boolean flag = false;
        try{
            con.setAutoCommit(false);
            iad.addProducts(con, asset);
            iad.addFinance(con, asset);
            iad.addAssets(con, asset);
            con.commit();
            flag = true;
        }catch(Throwable e){
            if(con!=null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean addSomeAssets(List<Asset> assets, String accountName) {
        IAssetDao iad = new AssetDaoImpl();
        Connection con = DBManager.getConnection();
        boolean flag = false;
        try{
            con.setAutoCommit(false);
            for(Asset asset: assets) {
                asset.setAccountName(accountName);
                iad.addProducts(con, asset);
                iad.addFinance(con, asset);
                iad.addAssets(con, asset);
            }
            con.commit();
            flag = true;
        }catch(Throwable e){
            if(con!=null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean editAsset(Asset asset) {
        IAssetDao iad = new AssetDaoImpl();
        Connection con = DBManager.getConnection();
        boolean flag = false;
        try{
            con.setAutoCommit(false);
            int id = asset.getAssetId();
            int typeId = iad.findTypeId(con, asset.getType());
            iad.updateAssets(con, id, "text", asset.getText());
            iad.updateAssets(con, id, "type_id", typeId);
            iad.updateProducts(con, id, "name", asset.getAssetName());
            iad.updateProducts(con, id, "model", asset.getModel());
            iad.updateProducts(con, id, "price", asset.getPrice());
            iad.updateProducts(con, id, "buy_time", asset.getBuyTime());
            con.commit();
            flag = true;
        }catch(Throwable e){
            if(con!=null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean deleteAsset(int assetId) {
        IAssetDao iad = new AssetDaoImpl();
        Connection con = DBManager.getConnection();
        boolean flag = false;
        try{
            con.setAutoCommit(false);
            iad.updateAssets(con, assetId, "state", -1);
            iad.updateAssets(con, assetId, "keeper_id", 1);
            iad.updateAssets(con, assetId, "user_id", 1);
            iad.updateFinance(con, assetId,"state",0);
            con.commit();
            flag = true;
        }catch(Throwable e){
            if(con!=null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean claimAsset(int assetId, String userId) {
        IAssetDao iad = new AssetDaoImpl();
        return iad.claimAsset(assetId,userId);
    }

    @Override
    public boolean giveBackAsset(int assetId) {
        IAssetDao iad = new AssetDaoImpl();
        Connection con = DBManager.getConnection();
        boolean flag = false;
        try{
            con.setAutoCommit(false);
            iad.updateAssets(con, assetId, "keeper_id", 2);
            iad.updateAssets(con, assetId, "user_id", 1);
            iad.updateAssets(con, assetId, "state", 0);
            con.commit();
            flag = true;
        }catch(Throwable e){
            if(con!=null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return flag;
    }

}
