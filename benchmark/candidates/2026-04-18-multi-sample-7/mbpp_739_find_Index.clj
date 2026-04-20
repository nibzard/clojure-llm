(defn find_Index
  "Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (long (or n 0))]
    (if (<= n 0)
      nil
      (let [target (bigint (if (= n 1)
                             1
                             (reduce *' (concat [1N] (repeat (dec n) 10N)))))
            disc   (+ 1N (*' 8N target))
            root   (bigint
                    (loop [lo 0N
                           hi disc]
                      (if (> lo hi)
                        hi
                        (let [mid (quot (+ lo hi) 2)
                              sq  (*' mid mid)]
                          (cond
                            (= sq disc) mid
                            (< sq disc) (recur (inc mid) hi)
                            :else       (recur lo (dec mid)))))))
            k      (quot (dec root) 2)
            tri    (quot (*' k (inc k)) 2)]
        (if (>= tri target) k (inc k))))))