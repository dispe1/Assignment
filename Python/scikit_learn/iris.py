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
from sklearn.linear_model import LogisticRegression, LinearRegression
from sklearn.neighbors import KNeighborsClassifier
from sklearn.naive_bayes import MultinomialNB
from sklearn.svm import LinearSVC
from sklearn.ensemble import RandomForestClassifier, AdaBoostClassifier, GradientBoostingClassifier,ExtraTreesClassifier, VotingClassifier, BaggingClassifier
from sklearn.tree import DecisionTreeClassifier

from sklearn.metrics import accuracy_score

df = pd.read_csv('./Iris_Data.csv')
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
y = df['species']
df = df.drop(['species'],axis=1)

'''
clf = xgb.XGBClassifier(max_depth=7, n_estimators=200, colsample_bytree=0.8,
                        subsample=0.8, nthread=10, learning_rate=0.1)
clf.fit(df, y)
# plot the important features #
fig, ax = plt.subplots(figsize=(12,18))
xgb.plot_importance(clf, max_num_features=50, height=0.8, ax=ax)
plt.show()
'''
xtrain, xvalid, ytrain, yvalid = train_test_split(df, y,
                                                  stratify=y,
                                                  random_state=42,
                                                  test_size=0.8, shuffle=True)
#print(xtrain.shape, xvalid.shape, ytrain.shape, yvalid.shape)

LR = LogisticRegression(C=1.0)
LR.fit(xtrain, ytrain)
predictions = LR.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
lr = [LR.__class__,accuracy_score(yvalid, predictions)]
algo = pd.DataFrame([lr])


XGB = xgb.XGBClassifier(max_depth=7, n_estimators=200, colsample_bytree=0.8,
                        subsample=0.8, nthread=10, learning_rate=0.1)
XGB.fit(xtrain, ytrain)
predictions = XGB.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
xg = [XGB.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([xg])

MNB = MultinomialNB()
MNB.fit(xtrain, ytrain)
predictions = MNB.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
mnb = [MNB.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([mnb])

ADC = AdaBoostClassifier()
ADC.fit(xtrain, ytrain)
predictions = ADC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
abc = [ADC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([abc])

KNC = KNeighborsClassifier()
KNC.fit(xtrain, ytrain)
predictions = KNC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
knc = [KNC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([knc])

GBC = GradientBoostingClassifier()
GBC.fit(xtrain, ytrain)
predictions = GBC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
gbc = [GBC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([gbc])

ETC = ExtraTreesClassifier()
ETC.fit(xtrain, ytrain)
predictions = ETC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
etc = [ETC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([etc])

DTC = DecisionTreeClassifier()
DTC.fit(xtrain, ytrain)
predictions = DTC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
dtc = [DTC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([dtc])

RFC = RandomForestClassifier()
RFC.fit(xtrain, ytrain)
predictions = RFC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
rfc = [RFC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([rfc])

SVC = LinearSVC(penalty='l2', C=10.0)
SVC.fit(xtrain, ytrain)
predictions = SVC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
svc = [SVC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([svc])

BC = BaggingClassifier(n_estimators=50)
BC.fit(xtrain, ytrain)
predictions = BC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
bc = [BC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([bc])


VC = VotingClassifier(estimators=[('mnb',MNB), ('gbc', GBC),('dtc', DTC), ('knc',KNC), ('etc',ETC), ('bc',BC), ('xgb',XGB)], voting='hard')
VC.fit(xtrain,ytrain)
predictions = VC.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
vc = [VC.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([vc])


print(algo.sort_values([1], ascending=[False]))
