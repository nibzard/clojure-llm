(defn check_monthnumb_number
  "	Write a function to check whether the given month number contains 31 days or not."
  [monthnum2]
  (let [monthnum (when (number? monthnum2) (long monthnum2))]
    (and monthnum
         (<= 1 monthnum 12)
         (contains? #{1 3 5 7 8 10 12} monthnum))))