(defn find_Index
  "Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (long n)]
    (cond
      (<= n 1) 1
      :else
      (let [pow10 (long (Math/pow 10 (dec n)))
            disc  (Math/sqrt (inc (* 8.0 pow10)))
            idx   (long (Math/ceil (/ (dec disc) 2.0)))]
        idx))))