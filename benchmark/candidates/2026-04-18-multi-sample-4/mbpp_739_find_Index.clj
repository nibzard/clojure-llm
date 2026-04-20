(defn find_Index
  "	Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (long (or n 0))]
    (if (<= n 0)
      0
      (let [target (bigint (if (= n 1)
                             1
                             (reduce *' 1N (concat [1N] (repeat (dec n) 10N)))))
            disc (+ 1.0 (* 8.0 (double target)))
            k (long (Math/ceil (/ (- (Math/sqrt disc) 1.0) 2.0)))]
        (loop [i (max 1 k)]
          (if (>= (/ (*' i (inc i)) 2) target)
            i
            (recur (inc i))))))))