(defn find_Index
  " Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (bigint n)]
    (if (or (nil? n) (<= n 0))
      nil
      (let [target (if (= n 1)
                     1N
                     (bigint (.pow (biginteger 10) (int (dec n)))))
            disc   (+ 1N (* 8N target))
            root   (bigint (Math/floor (Math/sqrt (double disc))))
            idx    (quot (dec root) 2)]
        (loop [k (max 1N idx)]
          (let [t (/ (* k (inc k)) 2)]
            (if (>= t target)
              k
              (recur (inc k)))))))))