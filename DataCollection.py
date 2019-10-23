from datetime import datetime, timedelta
import time
from collections import namedtuple
import pandas as pd
import requests
import matplotlib.pyplot as plt

API_KEY = 
BASE_URL = 

target_date = datetime(2019, 10, 1)
features = ["date", "meantempm", "maxhumidity", "minhumidity", "maxtempm",
            "mintempm"]
			
DailySummary = namedtuple("DailySummary", features)

def extract_weather_data(url, api_key, target_date, days):
    records = []
    for _ in range(days):
        request = BASE_URL.format(API_KEY, target_date.strftime('%Y%m%d'))
        response = requests.get(request)
        if response.status_code == 200:
            data = response.json()['history']['dailysummary'][0]
            records.append(DailySummary(
                date=target_date,
                meantempm=data['meantempm'],
                maxhumidity=data['maxhumidity'],
                minhumidity=data['minhumidity'],
                maxtempm=data['maxtempm'],
                mintempm=data['mintempm']))
        time.sleep(6)
        target_date += timedelta(days=1)
    return records
	
	records = extract_weather_data(BASE_URL, API_KEY, target_date, 500)
	
# if you closed our terminal or Jupyter Notebook, reinitialize your imports and
# variables first and remember to set your target_date to datetime(2016, 5, 16)
records += extract_weather_data(BASE_URL, API_KEY, target_date, 500)

df = pd.DataFrame(records, columns=features).set_index('date')

tmp = df[['meantempm']].head(10)
tmp

# 1 day prior
N = 1

# target measurement of mean temperature
feature = 'meantempm'


rows = tmp.shape[0]


nth_prior_measurements = [None]*N + [tmp[feature][i-N] for i in range(N, rows)]

col_name = "{}_{}".format(feature, N)
tmp[col_name] = nth_prior_measurements
tmp

def derive_nth_day_feature(df, feature, N):
    rows = df.shape[0]
    nth_prior_measurements = [None]*N + [df[feature][i-N] for i in range(N, rows)]
    col_name = "{}_{}".format(feature, N)
    df[col_name] = nth_prior_measurements
	
	for feature in features:
    if feature != 'date':
        for N in range(1, 4):
            derive_nth_day_feature(df, feature, N)
			
			df.columns
			
	   Index(['meantempm', 'maxhumidity',
       'minhumidity', 'maxtempm', 'mintempm',
      
       'maxhumidity_1', 'maxhumidity_2', 'maxhumidity_3', 'minhumidity_1',
       'minhumidity_2', 'minhumidity_3', 'maxtempm_1', 'maxtempm_2',
       'maxtempm_3', 'mintempm_1', 'mintempm_2', 'mintempm_3', 'maxdewptm_1'],
       dtype='object')
	   
	   

to_remove = [feature 
             for feature in features 
             if feature not in ['meantempm', 'mintempm', 'maxtempm']]

# make a list of columns to keep
to_keep = [col for col in df.columns if col not in to_remove]

# select only the columns in to_keep and assign to df
df = df[to_keep]
df.columns

Index(['meantempm', 'maxtempm', 'mintempm', 'meantempm_1', 'meantempm_2',
       'meantempm_3','maxhumidity_1', 'maxhumidity_2', 'maxhumidity_3', 'minhumidity_1',
       'minhumidity_2', 'minhumidity_3', 'maxtempm_1', 'maxtempm_2',
       'maxtempm_3', 'mintempm_1', 'mintempm_2', 'mintempm_3',],
      dtype='object') 

df.info()
<class 'pandas.core.frame.DataFrame'>
DatetimeIndex: 1000 entries, 2015-01-01 to 2017-09-27
Data columns (total 39 columns):
meantempm          1000 non-null object
maxtempm           1000 non-null object
mintempm           1000 non-null object
meantempm_1        999 non-null object
meantempm_2        998 non-null object
meantempm_3        997 non-null object

maxhumidity_1      999 non-null object
maxhumidity_2      998 non-null object
maxhumidity_3      997 non-null object
minhumidity_1      999 non-null object
minhumidity_2      998 non-null object
minhumidity_3      997 non-null object
maxtempm_1         999 non-null object
maxtempm_2         998 non-null object
maxtempm_3         997 non-null object
mintempm_1         999 non-null object
mintempm_2         998 non-null object
mintempm_3         997 non-null object


dtypes: object(39)
memory usage: 312.5+ KB


df = df.apply(pd.to_numeric, errors='coerce')
df.info()
<class 'pandas.core.frame.DataFrame'>
DatetimeIndex: 1000 entries, 2015-01-01 to 2017-09-27
Data columns (total 39 columns):
meantempm          1000 non-null int64
maxtempm           1000 non-null int64
mintempm           1000 non-null int64
meantempm_1        999 non-null float64
meantempm_2        998 non-null float64
meantempm_3        997 non-null float64

maxhumidity_1      999 non-null float64
maxhumidity_2      998 non-null float64
maxhumidity_3      997 non-null float64
minhumidity_1      999 non-null float64
minhumidity_2      998 non-null float64
minhumidity_3      997 non-null float64

maxtempm_1         999 non-null float64
maxtempm_2         998 non-null float64
maxtempm_3         997 non-null float64
mintempm_1         999 non-null float64
mintempm_2         998 non-null float64
mintempm_3         997 non-null float64

dtypes: float64(36), int64(3)
memory usage: 312.5 KB

spread = df.describe().T

IQR = spread['75%'] - spread['25%']


spread['outliers'] = (spread['min']<(spread['25%']-(3*IQR)))|(spread['max'] > (spread['75%']+3*IQR))

spread.ix[spread.outliers,]

%matplotlib inline
plt.rcParams['figure.figsize'] = [14, 8]
df.maxhumidity_1.hist()
plt.title('Distribution of maxhumidity_1')
plt.xlabel('maxhumidity_1')
plt.show()


df = df.dropna()

	