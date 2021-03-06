from tkinter.filedialog import askopenfilename
import pandas as pd
import tkinter
import os
from sklearn import preprocessing
import xgboost as xgb
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import VotingClassifier
from sklearn.svm import LinearSVC

tr_data = pd.read_csv('tr_data.csv')
tr_ans = pd.read_csv('tr_ans.csv')
tr_ans = tr_ans.iloc[:, 0]
ts_data = pd.read_csv('ts_data.csv')


cate = [key for key in dict(tr_data.dtypes) if dict(tr_data.dtypes)[key] in ['bool', 'object']]
le = preprocessing.LabelEncoder()
for i in cate:
    le.fit(tr_data[i])
    tr_data[i] = le.transform(tr_data[i])

cate = [key for key in dict(ts_data.dtypes) if dict(ts_data.dtypes)[key] in ['bool', 'object']]
le = preprocessing.LabelEncoder()
for i in cate:
    le.fit(ts_data[i])
    ts_data[i] = le.transform(ts_data[i])


LR = LogisticRegression(C=1.0)
SVC = LinearSVC(penalty='l2', C=10.0)

VC = VotingClassifier(estimators=[('lr', LR), ('svc',SVC)], voting='hard')
VC.fit(tr_data, tr_ans)
y_pred = VC.predict(ts_data)

pred_df = pd.DataFrame(y_pred)

pred_df.to_csv("output.csv", mode='w')

def accuracy(real, predict):
    return sum(real == predict) / float(real.shape[0])

ts_ans = pd.read_csv('ts_ans.csv')
ts_ans = ts_ans.iloc[:, 0]

print(accuracy(ts_ans, y_pred))
