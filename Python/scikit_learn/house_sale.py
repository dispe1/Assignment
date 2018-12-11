import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import os
print(os.listdir("./"))

from sklearn import preprocessing
from sklearn.preprocessing import LabelEncoder

from sklearn.model_selection import train_test_split

import matplotlib
import matplotlib.pyplot as plt
from IPython.display import display, HTML
import seaborn as sns

import xgboost as xgb
from sklearn.linear_model import Lasso, Ridge, LinearRegression
from sklearn.svm import SVR
from sklearn.ensemble import BaggingRegressor, RandomForestRegressor, GradientBoostingRegressor, AdaBoostRegressor
from sklearn.neighbors import KNeighborsRegressor
from sklearn.tree import DecisionTreeRegressor

from mlxtend.regressor import StackingRegressor


from sklearn.metrics import mean_squared_error, r2_score, explained_variance_score
from math import sqrt


df = pd.read_csv('./Ames_Housing_Sales.csv')
'''
print(df.head(5))

print(df.describe())

print(df.isnull().sum())

print("------  Data Types  ----- \n",df.dtypes)
print("------  Data type Count  ----- \n",df.dtypes.value_counts())
'''
#Delete String columns
string_columns = df.dtypes
string_boolidx = string_columns == np.object

tr_data_num = df.drop(df.columns[string_boolidx], axis=1)


cate = [key for key in dict(df.dtypes) if dict(df.dtypes)[key] in ['bool', 'object']]
le = preprocessing.LabelEncoder()
for i in cate:
    le.fit(df[i])
    df[i] = le.transform(df[i])

'''
corrmat = df.corr(method='pearson')
f, ax = plt.subplots(figsize=(8, 8))

# Draw the heatmap using seaborn
sns.heatmap(corrmat, vmax=1., square=True)
plt.title("Important variables correlation map", fontsize=15)
plt.show()
'''
y = df['SalePrice']
df = df.drop(['SalePrice', '3SsnPorch', 'Alley', 'BsmtHalfBath', 'EnclosedPorch', 'Exterior2nd', 'Fence', 'LandContour', 'LandSlope', 'MasVnrType', 'MoSold', 'PoolArea', 'PoolQC', 'RoofMatl', 'RoofStyle'],axis=1)




xtrain, xvalid, ytrain, yvalid = train_test_split(df, y,
                                                  random_state=42,
                                                  test_size=0.8, shuffle=True)
#print(xtrain.shape, xvalid.shape, ytrain.shape, yvalid.shape)

LR = LinearRegression()
LR.fit(xtrain, ytrain)
predictions = LR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
lr = [LR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = pd.DataFrame([lr])

LS = Lasso()
LS.fit(xtrain, ytrain)
predictions = LS.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
lasso = [LS.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([lasso])

RD = Ridge()
RD.fit(xtrain, ytrain)
predictions = RD.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
lidge = [RD.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([lidge])

XGB = xgb.XGBRegressor(max_depth=20, n_estimators=300, colsample_bytree=0.4,
                        subsample=0.95, nthread=10, learning_rate=0.07, gamma=0.045,min_child_weight=1.5,reg_alpha=0.65,reg_lambda=0.45)
XGB.fit(xtrain, ytrain)
'''
fig, ax = plt.subplots(figsize=(12,18))
xgb.plot_importance(XGB, max_num_features=80, height=0.8, ax=ax)
plt.show()
'''
predictions = XGB.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
xg = [XGB.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([xg])

BR = BaggingRegressor()
BR.fit(xtrain, ytrain)
predictions = BR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
br = [BR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([br])

RFR = RandomForestRegressor()
RFR.fit(xtrain, ytrain)
predictions = RFR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
rfr = [RFR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([rfr])

KNR = KNeighborsRegressor()
KNR.fit(xtrain, ytrain)
predictions = KNR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
knr = [KNR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([knr])

GBR = GradientBoostingRegressor()
GBR.fit(xtrain, ytrain)
predictions = GBR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
gbr = [GBR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([gbr])

SVR = SVR(kernel='rbf')
SVR.fit(xtrain, ytrain)
predictions = SVR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
svr = [SVR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([svr])

DTR = DecisionTreeRegressor()
DTR.fit(xtrain, ytrain)
predictions = DTR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
dtr = [DTR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([dtr])

ABR = AdaBoostRegressor()
ABR.fit(xtrain, ytrain)
predictions = ABR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
abr = [ABR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([abr])


STR = StackingRegressor(regressors=[XGB,GBR,BR, RFR, RD, LS], meta_regressor=LR)
STR.fit(xtrain, ytrain)
predictions = STR.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
str = [STR.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([str])



print(algo.sort_values([1], ascending=[True]))
