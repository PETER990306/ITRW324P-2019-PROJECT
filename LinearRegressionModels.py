import pickle
with open('end-part1_df.pkl', 'rb') as fp:
    df = pickle.load(fp)

import pandas as pd
df = pd.read_csv('end-part2_df.csv').set_index('date')

ŷ = β0 + β1 * x1 + β2 * x2 + ... + β(p-n) x(p-n) + Ε

df.corr()[['meantempm']].sort_values('meantempm')

predictors = ['meantempm_1',  'meantempm_2',  'meantempm_3', 
              'mintempm_1',   'mintempm_2',   'mintempm_3',
              'maxtempm_1',   'maxtempm_2',   'maxtempm_3']
df2 = df[['meantempm'] + predictors]

import matplotlib
import matplotlib.pyplot as plt
import numpy as np

%matplotlib inline

plt.rcParams['figure.figsize'] = [16, 22]

fig, axes = plt.subplots(nrows=6, ncols=3, sharey=True)

arr = np.array(predictors).reshape(6, 3)

for row, col_arr in enumerate(arr):
    for col, feature in enumerate(col_arr):
        axes[row, col].scatter(df2[feature], df2['meantempm'])
        if col == 0:
            axes[row, col].set(xlabel=feature, ylabel='meantempm')
        else:
            axes[row, col].set(xlabel=feature)
plt.show()

X = df2[predictors]
y = df2['meantempm']

X = sm.add_constant(X)
X.ix[:5, :5]

alpha = 0.05

model = sm.OLS(y, X).fit()

model.summary()


X = X.drop('meandewptm_3', axis=1)
 
model = sm.OLS(y, X).fit()

model.summary()

model = sm.OLS(y, X).fit()
model.summary()

from sklearn.model_selection import train_test_split

X = X.drop('const', axis=1)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=12)

from sklearn.linear_model import LinearRegression

regressor = LinearRegression()


regressor.fit(X_train, y_train)


prediction = regressor.predict(X_test)


from sklearn.metrics import mean_absolute_error, median_absolute_error
print("The Explained Variance: %.2f" % regressor.score(X_test, y_test))
print("The Mean Absolute Error: %.2f degrees celsius" % mean_absolute_error(y_test, prediction))
print("The Median Absolute Error: %.2f degrees celsius" % median_absolute_error(y_test, prediction))

#The Explained Variance: 0.90
#The Mean Absolute Error: 2.69 degrees celsius
#The Median Absolute Error: 2.17 degrees celsius
	
