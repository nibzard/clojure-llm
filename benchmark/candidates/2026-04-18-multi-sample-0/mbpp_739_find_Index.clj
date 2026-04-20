(defn find_Index
  "	Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (when (and (integer? n) (pos? n))
    (let [target (if (= n 1)
                   1N
                   (reduce *' 1N (repeat (dec n) 10N)))]
      (loop [k 1N]
        (if (>= (/ (*' k (inc k)) 2) target)
          k
          (recur (inc k)))))))