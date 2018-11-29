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
from sklearn.ensemble import RandomForestClassifier, AdaBoostClassifier, GradientBoostingClassifier,ExtraTreesClassifier
from sklearn.tree import DecisionTreeClassifier

from sklearn.metrics import accuracy_score

df = pd.read_csv('./Human_Resources_Employee_Attrition.csv')
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
y = df['salary']
df = df.drop(['salary'],axis=1)

'''
clf = xgb.XGBClassifier(max_depth=20, n_estimators=300, colsample_bytree=0.4,
                        subsample=0.95, nthread=10, learning_rate=0.07, gamma=0.045,min_child_weight=1.5,reg_alpha=0.65,reg_lambda=0.45)
clf.fit(df, y)
# plot the important features #
fig, ax = plt.subplots(figsize=(12,18))
xgb.plot_importance(clf, max_num_features=50, height=0.8, ax=ax)
plt.show()
'''
xtrain, xvalid, ytrain, yvalid = train_test_split(df, y,
                                                  stratify=y,
                                                  random_state=42,
                                                  test_size=0.3, shuffle=True)
#print(xtrain.shape, xvalid.shape, ytrain.shape, yvalid.shape)

clf = LogisticRegression(C=1.0)
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
lr = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = pd.DataFrame([lr])


clf = xgb.XGBClassifier(max_depth=20, n_estimators=300, colsample_bytree=0.4,
                        subsample=0.95, nthread=10, learning_rate=0.07, gamma=0.045,min_child_weight=1.5,reg_alpha=0.65,reg_lambda=0.45)
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
xg = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([xg])

clf = MultinomialNB()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
mnb = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([mnb])

clf = AdaBoostClassifier()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
abc = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([abc])

clf = KNeighborsClassifier()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
knc = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([knc])

clf = GradientBoostingClassifier()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
gbc = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([gbc])

clf = ExtraTreesClassifier()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
etc = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([etc])

clf = DecisionTreeClassifier()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
dtc = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([dtc])

clf = RandomForestClassifier()
clf.fit(xtrain, ytrain)
predictions = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
rfc = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([rfc])

clf = LinearSVC(penalty='l2', C=10.0)
clf.fit(xtrain, ytrain)
y_predict = clf.predict(xvalid)
print("accuracy_score",accuracy_score(yvalid, predictions))
svc = [clf.__class__,accuracy_score(yvalid, predictions)]
algo = algo.append([svc])


print(algo.sort_values([1], ascending=[False]))
