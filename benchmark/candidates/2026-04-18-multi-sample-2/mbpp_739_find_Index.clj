(defn find_Index
  "	Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (long (or n 0))]
    (if (<= n 0)
      0
      (let [target (bigint (if (= n 1)
                             1
                             (reduce *' 9N (repeat (dec n) 10N))))
            disc   (+ (*' 8N target) 1N)
            root   (bigint (.sqrt (biginteger disc)))
            k      (quot (dec root) 2)]
        (if (>= (quot (*' k (inc k)) 2) target)
          k
          (inc k))))))