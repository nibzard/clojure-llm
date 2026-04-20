(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (let [chars (vec s)]
        (loop [i (dec n)
               dp (vec (repeat n 0))]
          (if (neg? i)
            (dp (dec n))
            (let [dp' (loop [j i
                             prev 0
                             curr dp]
                        (if (= j n)
                          curr
                          (if (= i j)
                            (recur (inc j) 0 (assoc curr j 1))
                            (let [temp (curr j)]
                              (if (= (chars i) (chars j))
                                (recur (inc j) temp (assoc curr j (+ 2 prev)))
                                (recur (inc j) temp (assoc curr j (max (curr j) (curr (dec j)))))))))]
              (recur (dec i) dp')))))))