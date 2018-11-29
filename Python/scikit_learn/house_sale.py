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

from sklearn.metrics import mean_squared_error, r2_score, explained_variance_score
from math import sqrt


df = pd.read_csv('./Ames_Housing_Sales.csv')
print(df.head(5))

print(df.describe())

print(df.isnull().sum())

print("------  Data Types  ----- \n",df.dtypes)
print("------  Data type Count  ----- \n",df.dtypes.value_counts())

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
df = df.drop(['SalePrice', 'Exterior2nd','EnclosedPorch', 'RoofMatl', 'PoolQC', 'BsmtHalfBath', 'RoofStyle', 'PoolArea', 'MoSold', 'Alley', 'Fence', 'LandContour', 'MasVnrType', '3SsnPorch', 'LandSlope'],axis=1)

xtrain, xvalid, ytrain, yvalid = train_test_split(df, y,
                                                  random_state=42,
                                                  test_size=0.3, shuffle=True)
#print(xtrain.shape, xvalid.shape, ytrain.shape, yvalid.shape)

clf = LinearRegression()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
lr = [clf.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = pd.DataFrame([lr])

clf = Lasso()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
lasso = [clf.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([lasso])

clf = Ridge()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
lidge = [clf.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([lidge])

clf = xgb.XGBRegressor(max_depth=20, n_estimators=300, colsample_bytree=0.4,
                        subsample=0.95, nthread=10, learning_rate=0.07, gamma=0.045,min_child_weight=1.5,reg_alpha=0.65,reg_lambda=0.45)
clf.fit(xtrain, ytrain)
'''
fig, ax = plt.subplots(figsize=(12,18))
xgb.plot_importance(clf, max_num_features=80, height=0.8, ax=ax)
plt.show()
'''
predictions = clf.predict(xvalid)
print("root_mean_squared_error",sqrt(mean_squared_error(yvalid, predictions)))
print('r2 score: %.2f' % r2_score(yvalid, predictions))
print('explained_variance_score: %.2f' % explained_variance_score(yvalid, predictions))
xg = [clf.__class__,sqrt(mean_squared_error(yvalid, predictions))]
algo = algo.append([xg])


print(algo.sort_values([1], ascending=[False]))
