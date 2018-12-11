from tkinter.filedialog import askopenfilename
import pandas as pd
import tkinter
import os
from sklearn import preprocessing
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier,ExtraTreesClassifier, VotingClassifier, BaggingClassifier
import xgboost as xgb

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

ETC = ExtraTreesClassifier()
XGB = xgb.XGBClassifier(max_depth=20, n_estimators=300, colsample_bytree=0.4, subsample=0.95, nthread=10, learning_rate=0.07, gamma=0.045,min_child_weight=1.5,reg_alpha=0.65,reg_lambda=0.45)
GBC = GradientBoostingClassifier()
RFC = RandomForestClassifier()
BC = BaggingClassifier(n_estimators=50)

VC = VotingClassifier(estimators=[('bc', BC),('xgb', XGB), ('etc', ETC), ('rfc',RFC), ('gbc',GBC)], voting='hard')

VC.fit(tr_data, tr_ans)
y_pred = VC.predict(ts_data)


pred_df = pd.DataFrame(y_pred)

pred_df.to_csv("output.csv", mode='w')

def accuracy(real, predict):
    return sum(real == predict) / float(real.shape[0])

ts_ans = pd.read_csv('ts_ans.csv')
ts_ans = ts_ans.iloc[:, 0]

print(accuracy(ts_ans, y_pred))
