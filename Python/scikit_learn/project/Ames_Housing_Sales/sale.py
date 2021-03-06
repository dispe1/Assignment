from tkinter.filedialog import askopenfilename
import pandas as pd
import tkinter
import os
from sklearn import preprocessing
import numpy as np
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.linear_model import Lasso, Ridge, LinearRegression
from sklearn.ensemble import BaggingRegressor, RandomForestRegressor, GradientBoostingRegressor
from mlxtend.regressor import StackingRegressor
import xgboost as xgb

tr_data = pd.read_csv('tr_data.csv')
tr_ans = pd.read_csv('tr_ans.csv')
tr_ans = tr_ans.iloc[:, 0]
ts_data = pd.read_csv('ts_data.csv')

tr_data = tr_data.drop(['Exterior2nd','EnclosedPorch', 'RoofMatl', 'PoolQC', 'BsmtHalfBath', 'RoofStyle', 'PoolArea', 'MoSold', 'Alley', 'Fence', 'LandContour', 'MasVnrType', '3SsnPorch', 'LandSlope'],axis=1)
ts_data = ts_data.drop(['Exterior2nd','EnclosedPorch', 'RoofMatl', 'PoolQC', 'BsmtHalfBath', 'RoofStyle', 'PoolArea', 'MoSold', 'Alley', 'Fence', 'LandContour', 'MasVnrType', '3SsnPorch', 'LandSlope'],axis=1)


#Delete String columns
string_columns = tr_data.dtypes
string_boolidx = string_columns == np.object

tr_data_num = tr_data.drop(tr_data.columns[string_boolidx], axis=1)
ts_data_num = ts_data.drop(ts_data.columns[string_boolidx], axis=1)


#delete NaN columns
tr_nan_idx = tr_data_num.isna().any()
ts_nan_idx = ts_data_num.isna().any()

total_nan_idx = np.logical_or(tr_nan_idx, ts_nan_idx)

tr_data_pre = tr_data_num.drop(tr_data_num.columns[total_nan_idx], axis=1)
ts_data_pre = ts_data_num.drop(ts_data_num.columns[total_nan_idx], axis=1)


XGB = xgb.XGBRegressor(max_depth=20, n_estimators=300, colsample_bytree=0.4, subsample=0.95, nthread=10, learning_rate=0.07, gamma=0.045,min_child_weight=1.5,reg_alpha=0.65,reg_lambda=0.45)
LR = LinearRegression()
LS = Lasso()
RD = Ridge()
BR = BaggingRegressor()
GBR = GradientBoostingRegressor()
RFR = RandomForestRegressor()


STR = StackingRegressor(regressors=[XGB,GBR,BR, RFR, RD, LS], meta_regressor=LR)
STR.fit(tr_data_pre, tr_ans)
y_pred = STR.predict(ts_data_pre)


pred_df = pd.DataFrame(y_pred)

pred_df.to_csv("output.csv", mode='w')

def rmse(ytrue, ypredicted):
    return np.sqrt(mean_squared_error(ytrue, ypredicted))

ts_ans = pd.read_csv('ts_ans.csv')
ts_ans = ts_ans.iloc[:, 0]

print(rmse(ts_ans, y_pred))
print(r2_score(ts_ans, y_pred))
